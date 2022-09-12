package simulator.core.types.bosses;

// Technically we don't need this enum, but it makes it easier to manage all the file names
public enum BossList {
    OGRE("Ogre", "ogre.json");

    private String displayName;
    private String fileName;

    private BossList(String displayName, String fileName) {
        this.displayName = displayName;
        this.fileName = fileName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getFileName() {
        return fileName;
    }

    public BossList findByName(String name) {
        for (BossList boss : BossList.values()) {
            if (boss.getDisplayName().equalsIgnoreCase(name)) {
                return boss;
            }
        }
        return null;
    }
}
