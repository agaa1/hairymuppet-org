package simulator.core.types.players;

import simulator.core.types.skills.ASkill;

import java.util.HashMap;
import java.util.Map;

/**
 * This is called Job instead of Class because Class is a protected word in Java.
 */
public class Job {

    private final String name;

    // Pulled out of the associated json file
    private int startingHealth;

    /**
     * This map is loaded by examining the corresponding json file for this class.
     * <p>
     * Different json files can be loaded in, depending on the simulation you want to run.
     */
    private final Map<String, ASkill> skillsMap = new HashMap<>();

    public Job(String name, int startingHealth) {
        this.name = name;
        this.startingHealth = startingHealth;
    }

    public String getName() {
        return name;
    }

    public ASkill getSkillByName(String skillName) {
        return skillsMap.get(skillName);
    }

    public Map<String, ASkill> getAllSkills() {
        return skillsMap;
    }

    public int getStartingHealth() {
        return startingHealth;
    }
}
