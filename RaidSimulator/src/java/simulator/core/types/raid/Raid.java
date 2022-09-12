package simulator.core.types.raid;

import simulator.core.types.bosses.Boss;
import simulator.core.types.general.ALiving;
import simulator.core.types.players.Player;

import java.util.List;

public class Raid {

    private final List<Player> players;
    private final Boss boss;

    // The measurement of time in the raid
    private int tick;

    public Raid(List<Player> players, Boss boss) {
        this.players = players;
        this.boss = boss;
        this.tick = 0;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Boss getBoss() {
        return boss;
    }


    public int getTick() {
        return tick;
    }

    public void incrementTick() {
        this.tick++;
    }

    public ALiving getLowestHealthPlayer() {
        int lowestHealth = Integer.MAX_VALUE;
        Player foundPlayer = null;
        for (Player player : players) {
            if (!player.isDead() && player.getHealth() < lowestHealth) {
                lowestHealth = player.getHealth();
                foundPlayer = player;
            }
        }
        return foundPlayer;
    }
}
