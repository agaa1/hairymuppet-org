package simulator.core.types.skills;

import simulator.core.types.general.ILoggable;

public abstract class ASkill implements ILoggable {

    // The name of the skill
    protected final String name;

    /**
     * The amount of time it takes to cast the skill, 0 for instant.
     * <p>
     * If a skill is instant, the effects of the skill will be applied the same tick as it is cast.
     */
    protected int skillCastTime;

    /**
     * The amount of time after a skill has been activated before it can be cast again.
     * <p>
     * Note: It is probably a bad idea to have an instant cast skill with no cooldown. :P
     */
    protected int skillCooldown;

    /**
     * Not currently in use.
     */
    protected int skillDuration;

    public ASkill(String name, int skillCastTime, int skillCooldown) {
        this.name = name;
        this.skillCastTime = skillCastTime;
        this.skillCooldown = skillCooldown;
    }

    public String getName() {
        return name;
    }

    public int getSkillCastTime() {
        return skillCastTime;
    }

    public int getSkillCooldown() {
        return skillCooldown;
    }

    public int getSkillDuration() {
        return skillDuration;
    }

    @Override
    public String getLogName() {
        return name;
    }


    @Override
    public String getDebugString() {
        return name + "_ct" + skillCastTime + "_cd" + skillCooldown;
    }

}
