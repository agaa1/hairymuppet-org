package simulator.core.types.general;

import simulator.core.logger.RaidLogger;
import simulator.core.types.skills.ASkill;
import simulator.core.types.skills.SkillData;

import java.util.HashMap;
import java.util.Map;

public abstract class ALiving implements ILoggable {

    // The name of the entity
    protected final String name;

    // Max health
    protected int maxHealth;
    protected int health;

    /* Stores the skill this entity is currently casting
     *
     * Note this simulator assumes that an entity can only ever be casting one skill at a time
     */
    protected SkillData casting = null;

    // Stores all skills that are on cooldown for this entity
    protected final Map<ASkill, Integer> cooldown = new HashMap<>();

    public ALiving(String name, int maxHealth) {
        this.name = name;
        this.maxHealth = maxHealth;
        // We always assume the entity will be at full health at the start of the fight
        this.health = maxHealth;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getLogName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @param delta A positive or negative number that indicates healing or damage to be applied to this entity.
     */
    public void changeHealthBy(int delta) {
        if (health + delta > maxHealth) {
            health = maxHealth;
        } else if (health + delta <= 0) {
            health = 0;
            RaidLogger.log(getLogName() + " is dead!");
        } else {
            health += delta;
            RaidLogger.log(getLogName() + " is at " + health + "/" + maxHealth + " health.");
        }
    }

    public boolean isDead() {
        // Technically health should never be set to less than 0, but we check for less than 0 just to be safe.
        return health <= 0;
    }

    public boolean isFullHealth() {
        return health == maxHealth;
    }


    public Map<ASkill, Integer> getCooldown() {
        return cooldown;
    }

    public boolean isCasting() {
        return casting != null;
    }

    public SkillData getCasting() {
        return casting;
    }

    public void setCasting(SkillData casting) {
        this.casting = casting;
    }
}
