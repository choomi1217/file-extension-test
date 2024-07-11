package cho.ym.madrascheck.enums;

public enum FixedFileExtension {
    BAT(".bat"),
    CMD(".cmd"),
    COM(".com"),
    CPL(".cpl"),
    EXE(".exe"),
    SCR(".scr"),
    JS(".js");

    private final String extension;

    FixedFileExtension(String extension) {
        this.extension = extension;
    }

    public static FixedFileExtension fromExtension(String extension) {
        for (FixedFileExtension ext : FixedFileExtension.values()) {
            if (ext.getExtension().equals(extension)) {
                return ext;
            }
        }
        throw new IllegalArgumentException("Unknown extension: " + extension);
    }

    public String getExtension() {
        return extension;
    }

    public String getName() {
        return this.name();
    }

}

