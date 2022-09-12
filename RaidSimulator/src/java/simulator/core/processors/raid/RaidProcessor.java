package simulator.core.processors.raid;

import simulator.core.types.bosses.Boss;
import simulator.core.types.players.Player;
import simulator.core.types.raid.Raid;

import java.util.List;

public class RaidProcessor {

    public static void runRaid(List<Player> players, Boss boss) {

        Raid raid = new Raid(players, boss);

        while (true) {
            if (boss.isDead()) {
                // Players Win!
                return;
            }
            if (players.stream().filter(p -> p.isDead()).count() == players.size()) {
                // Players Lose!
                return;
            }

            BossProcessor.processTick(raid);

            for (Player player : players) {
                if (!player.isDead()) {
                    PlayerProcessor.processTick(player, raid);
                }
            }

            raid.incrementTick();
        }

    }
}
