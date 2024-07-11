package cho.ym.madrascheck.extension.fixed.web.response;

public record FixedFileExtensionResponse(String extension, String name, boolean isChecked) {
    public static FixedFileExtensionResponse of(String extension, String name, boolean isChecked) {
        return new FixedFileExtensionResponse(extension, name, isChecked);
    }
}
