package simulator.core.loaders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import simulator.core.types.players.Job;
import simulator.core.types.players.JobList;
import simulator.core.types.skills.ASkill;

public class JobLoader {

    public static Job loadJob(JobList jobList) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ASkill.class, new SkillAdapter());

        Gson gson = gsonBuilder.create();

        String json = JsonLoader.loadJson("jobs/" + jobList.getFileName());

        Job job = gson.fromJson(json, Job.class);

        return job;
    }

    public static void saveJob(Job job, String filename) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ASkill.class, new SkillAdapter());
        gsonBuilder.setPrettyPrinting();

        Gson gson = gsonBuilder.create();

        String json = gson.toJson(job);

        JsonLoader.writeJson(json, filename);
    }

}
