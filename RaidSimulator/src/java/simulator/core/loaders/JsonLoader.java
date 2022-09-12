package simulator.core.loaders;

import java.io.*;

public class JsonLoader {

    private static String getBaseDir() {
        String baseDir = System.getProperty("user.dir");
        if (!baseDir.contains("RaidSimulator")) {
            // If you run this in Intellij, it may not include the actual Java Project.
            baseDir += "/RaidSimulator";
        }
        return baseDir;
    }

    public static String loadJson(String filename) {

        String rv = "";

        // Putting this in the try means that it will be closed automatically, so no resource leak
        try (BufferedReader reader = new BufferedReader(new FileReader(getBaseDir() + "/src/resources/json/" + filename))) {
            String line = reader.readLine();
            while (line != null) {
                rv += line.trim();
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return rv;
    }

    public static void writeJson(String json, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getBaseDir() + "/src/resources/json/" + filename))) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
