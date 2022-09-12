package simulator.core.types.skills;

public abstract class ADamageSkill extends ASkill {

    public ADamageSkill(String name, int skillCastTime, int skillCooldown) {
        super(name, skillCastTime, skillCooldown);
    }
}
