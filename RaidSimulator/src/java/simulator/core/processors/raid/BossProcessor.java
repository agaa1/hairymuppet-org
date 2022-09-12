package simulator.core.processors.raid;

import simulator.core.logger.RaidLogger;
import simulator.core.types.bosses.Boss;
import simulator.core.types.general.ALiving;
import simulator.core.types.raid.Raid;
import simulator.core.types.skills.ASkill;
import simulator.core.types.skills.DirectDamageSkill;
import simulator.core.types.skills.SkillData;

public class BossProcessor {

    public static void processTick(Raid raid) {
        Boss boss = raid.getBoss();

        for (ASkill skill : boss.getAllSkills().values()) {
            if (boss.getCooldown().containsKey(skill) && boss.getCooldown().get(skill) == raid.getTick()) {
                // Update list of skills on cooldown
                boss.getCooldown().remove(skill);
            }
        }

        if (!boss.isCasting()) {
            for (ASkill skill : boss.getAllSkills().values()) {
                if (boss.getCooldown().containsKey(skill)) {
                    // Can't cast the skill, still on cooldown
                    continue;
                }
                ALiving target = raid.getLowestHealthPlayer();
                if (target == null) {
                    // All the players are dead. This shouldn't be possible, as the check at the start of the RaidProcessor
                    // should have finished the Raid before processing the Boss' turn!
                    RaidLogger.error("No targets left for boss");
                }
                boss.setCasting(new SkillData(skill, raid.getTick() + skill.getSkillCastTime(), target));
                if (skill.getSkillCastTime() > 0) {
                    RaidLogger.logTick(raid.getTick(), "Boss " + boss.getLogName() + " starts casting " + skill.getLogName());
                }
                // Make sure we only cast 1 skill
                break;
            }
        }

        // The order of these statements is important. By first casting a new skill, then checking what skills are being cast,
        // we allow instant cast skills to work automatically, without needing extra complicated code.
        if (boss.isCasting()) {
            SkillData casting = boss.getCasting();
            Integer tick = casting.getTickCast();

            if (tick == raid.getTick()) {
                // Finished casting
                ASkill skill = casting.getSkill();
                ALiving target = casting.getTarget();
                if (skill instanceof DirectDamageSkill) {
                    DirectDamageSkill dds = (DirectDamageSkill) skill;
                    if (dds.getSkillCastTime() == 0) {
                        RaidLogger.logTick(raid.getTick(), "Boss " + boss.getLogName() + " casts " + skill.getLogName() + " at " + target.getLogName() + " for " + dds.getDirectDamage() + " damage.");
                    } else {
                        RaidLogger.logTick(raid.getTick(), "Boss " + boss.getLogName() + " finishes casting " + skill.getLogName() + " at " + target.getLogName() + " for " + dds.getDirectDamage() + " damage.");
                    }
                    target.changeHealthBy(dds.getDirectDamage() * -1);
                }
                boss.setCasting(null);
                boss.getCooldown().put(skill, raid.getTick() + skill.getSkillCooldown());
            }
        }

    }
}
