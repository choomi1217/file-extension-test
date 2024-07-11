package cho.ym.madrascheck.enums.converter;

import cho.ym.madrascheck.enums.FixedFileExtension;
import jakarta.persistence.AttributeConverter;

public class FixedFileExtensionConverter implements AttributeConverter<FixedFileExtension, String> {
    @Override
    public String convertToDatabaseColumn(FixedFileExtension attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getExtension();
    }

    @Override
    public FixedFileExtension convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return FixedFileExtension.fromExtension(dbData);
    }
}
