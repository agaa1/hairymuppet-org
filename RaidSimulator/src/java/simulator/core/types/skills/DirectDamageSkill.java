package simulator.core.types.skills;

public class DirectDamageSkill extends ADamageSkill {

    private int directDamage;

    public DirectDamageSkill(String name, int damage, int skillCastTime, int skillCooldown) {
        super(name, skillCastTime, skillCooldown);
        this.directDamage = damage;
    }

    public int getDirectDamage() {
        return directDamage;
    }
}
