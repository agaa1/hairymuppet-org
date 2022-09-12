package simulator.core.raid.ogre;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/*
 * These tests represent top-level requirements, that should always remain valid over the life of the project. (Unless
 * we specifically update them in an issue) If you are updating these tests after making a code change, and the related
 * issue did not reference a change to these requirements, then either the issue is invalid, or you coded the changes
 * up incorrectly.
 *
 * The current requirements are:
 * - A single warrior will always lose to an Ogre
 * - Four warriors will always defeat an Ogre
 */
public class OgreTest {

    @BeforeEach
    public void setup() {
        RaidLogger.setLoggingEnabled(false);
    }

    @Test
    public void testSinglePlayer() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Noob", JobLoader.loadJob(JobList.WARRIOR)));

        Boss boss = BossLoader.loadBoss(BossList.OGRE);

        RaidProcessor.runRaid(players, boss);

        assertFalse(boss.isDead());
    }

    @Test
    public void test4Players() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Ace1", JobLoader.loadJob(JobList.WARRIOR)));
        players.add(new Player("Ace2", JobLoader.loadJob(JobList.WARRIOR)));
        players.add(new Player("Ace3", JobLoader.loadJob(JobList.WARRIOR)));
        players.add(new Player("Ace4", JobLoader.loadJob(JobList.WARRIOR)));

        Boss boss = BossLoader.loadBoss(BossList.OGRE);

        RaidProcessor.runRaid(players, boss);

        assertTrue(boss.isDead());
    }
}
