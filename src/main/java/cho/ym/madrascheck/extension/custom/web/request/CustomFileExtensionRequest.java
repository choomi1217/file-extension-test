package cho.ym.madrascheck.extension.custom.web.request;

import jakarta.validation.constraints.Size;

public record CustomFileExtensionRequest(@Size(max = 20) String extension) {
}
