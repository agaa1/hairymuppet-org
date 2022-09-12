package simulator.core.main;

import simulator.core.loaders.BossLoader;
import simulator.core.loaders.JobLoader;
import simulator.core.logger.RaidLogger;
import simulator.core.processors.raid.RaidProcessor;
import simulator.core.types.bosses.Boss;
import simulator.core.types.bosses.BossList;
import simulator.core.types.players.JobList;
import simulator.core.types.players.Player;

import java.util.ArrayList;
import java.util.List;

public class RunSimulator {

    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Chris", JobLoader.loadJob(JobList.WARRIOR)));
        players.add(new Player("Anthony", JobLoader.loadJob(JobList.WARRIOR)));

        Boss boss = BossLoader.loadBoss(BossList.OGRE);

        RaidProcessor.runRaid(players, boss);

        if (boss.isDead()) {
            RaidLogger.log("Players win!");
        } else {
            RaidLogger.log("Players lose!");
        }

    }
}
