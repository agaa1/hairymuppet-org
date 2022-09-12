package simulator.core.types.skills;

import simulator.core.types.general.ALiving;

public class SkillData {

    private final ASkill skill;
    private final int tickCast;
    private final ALiving target;

    public SkillData(ASkill skill, int tickCast, ALiving target) {
        this.skill = skill;
        this.tickCast = tickCast;
        this.target = target;
    }

    public ASkill getSkill() {
        return skill;
    }

    public int getTickCast() {
        return tickCast;
    }

    public ALiving getTarget() {
        return target;
    }
}
