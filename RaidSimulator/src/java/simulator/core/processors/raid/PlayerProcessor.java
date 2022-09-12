package simulator.core.processors.raid;

import simulator.core.logger.RaidLogger;
import simulator.core.types.general.ALiving;
import simulator.core.types.players.Player;
import simulator.core.types.raid.Raid;
import simulator.core.types.skills.ASkill;
import simulator.core.types.skills.DirectDamageSkill;
import simulator.core.types.skills.SkillData;

public class PlayerProcessor {

    public static void processTick(Player player, Raid raid) {


        for (ASkill skill : player.getAllSkills().values()) {
            if (player.getCooldown().containsKey(skill) && player.getCooldown().get(skill) == raid.getTick()) {
                // Update list of skills on cooldown
                player.getCooldown().remove(skill);
            }
        }

        if (!player.isCasting()) {
            for (ASkill skill : player.getAllSkills().values()) {
                if (player.getCooldown().containsKey(skill)) {
                    // Can't cast the skill, still on cooldown
                    continue;
                }
                ALiving target = raid.getBoss();
                player.setCasting(new SkillData(skill, raid.getTick() + skill.getSkillCastTime(), target));
                if (skill.getSkillCastTime() > 0) {
                    RaidLogger.logTick(raid.getTick(), "Player " + player.getLogName() + " starts casting " + skill.getLogName());
                }
            }
        }

        // The order of these statements is important. By first casting a new skill, then checking what skills are being cast,
        // we allow instant cast skills to work automatically, without needing extra complicated code.
        if (player.isCasting()) {
            SkillData casting = player.getCasting();
            Integer tick = casting.getTickCast();

            if (tick == raid.getTick()) {
                // Finished casting
                ASkill skill = casting.getSkill();
                ALiving target = casting.getTarget();
                if (skill instanceof DirectDamageSkill) {
                    DirectDamageSkill dds = (DirectDamageSkill) skill;
                    if (dds.getSkillCastTime() == 0) {
                        RaidLogger.logTick(raid.getTick(), "Player " + player.getLogName() + " casts " + skill.getLogName() + " at " + target.getLogName() + " for " + dds.getDirectDamage() + " damage.");
                    } else {
                        RaidLogger.logTick(raid.getTick(), "Player " + player.getLogName() + " finishes casting " + skill.getLogName() + " at " + target.getLogName() + " for " + dds.getDirectDamage() + " damage.");
                    }
                    target.changeHealthBy(dds.getDirectDamage() * -1);
                }
                player.setCasting(null);
                player.getCooldown().put(skill, raid.getTick() + skill.getSkillCooldown());
            }
        }

    }

}
