package simulator.core.types.players;

import simulator.core.types.general.ALiving;
import simulator.core.types.skills.ASkill;

import java.util.Map;

public class Player extends ALiving {

    private final Job job;

    public Player(String name, Job job) {
        super(name, job.getStartingHealth());
        this.job = job;
    }


    @Override
    public String getDebugString() {
        return this.name + "_" + this.job.getName() + "_" + this.health + "/" + this.maxHealth;
    }

    public Job getJob() {
        return job;
    }

    public Map<String, ASkill> getAllSkills() {
        return job.getAllSkills();
    }
}
