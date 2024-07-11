package cho.ym.madrascheck.extension;

import cho.ym.madrascheck.extension.custom.web.response.CustomFileExtensionResponse;
import cho.ym.madrascheck.extension.fixed.web.response.FixedFileExtensionResponse;

public record ExtensionResponse(CustomFileExtensionResponse custom, FixedFileExtensionResponse fixed) {
}
