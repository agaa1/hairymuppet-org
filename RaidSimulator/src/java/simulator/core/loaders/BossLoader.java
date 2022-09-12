package simulator.core.loaders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import simulator.core.types.bosses.Boss;
import simulator.core.types.bosses.BossList;
import simulator.core.types.skills.ASkill;

public class BossLoader {

    public static Boss loadBoss(BossList bossList) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ASkill.class, new SkillAdapter());

        Gson gson = gsonBuilder.create();

        String json = JsonLoader.loadJson("bosses/" + bossList.getFileName());

        Boss boss = gson.fromJson(json, Boss.class);

        return boss;
    }

    public static void saveBoss(Boss boss, String filename) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ASkill.class, new SkillAdapter());
        gsonBuilder.setPrettyPrinting();

        Gson gson = gsonBuilder.create();

        String json = gson.toJson(boss);

        JsonLoader.writeJson(json, filename);
    }
}
