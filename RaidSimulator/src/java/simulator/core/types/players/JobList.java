package simulator.core.types.players;

// Technically we don't need this enum, but it makes it easier to manage all the file names
public enum JobList {
    WARRIOR("Warrior", "warrior.json");

    private String displayName;
    private String fileName;

    private JobList(String displayName, String fileName) {
        this.displayName = displayName;
        this.fileName = fileName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getFileName() {
        return fileName;
    }

    public JobList findByName(String name) {
        for (JobList job : JobList.values()) {
            if (job.getDisplayName().equalsIgnoreCase(name)) {
                return job;
            }
        }
        return null;
    }
}
