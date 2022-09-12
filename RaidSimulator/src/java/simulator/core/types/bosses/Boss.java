package simulator.core.types.bosses;

import simulator.core.types.general.ALiving;
import simulator.core.types.skills.ASkill;

import java.util.HashMap;
import java.util.Map;

public class Boss extends ALiving {


    /**
     * This map is loaded by examining the corresponding json file for this class.
     * <p>
     * Different json files can be loaded in, depending on the simulation you want to run.
     */
    private final Map<String, ASkill> skillsMap = new HashMap<>();

    public Boss(String name, int startingHealth) {
        super(name, startingHealth);
    }

    public ASkill getSkillByName(String skillName) {
        return skillsMap.get(skillName);
    }

    public Map<String, ASkill> getAllSkills() {
        return skillsMap;
    }

    @Override
    public String getDebugString() {
        return this.name + "_" + this.health + "/" + this.maxHealth;
    }

}
