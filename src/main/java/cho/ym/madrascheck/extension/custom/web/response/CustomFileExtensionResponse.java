package cho.ym.madrascheck.extension.custom.web.response;

import cho.ym.madrascheck.extension.custom.repository.domain.CustomFileExtensionEntity;

public record CustomFileExtensionResponse(String extension, long id) {
    public static CustomFileExtensionResponse from(CustomFileExtensionEntity entity) {
        return new CustomFileExtensionResponse(entity.getExtension(), entity.getId());
    }
}
