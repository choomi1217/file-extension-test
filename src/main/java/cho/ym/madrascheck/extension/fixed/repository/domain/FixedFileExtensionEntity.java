package cho.ym.madrascheck.extension.fixed.repository.domain;

import cho.ym.madrascheck.enums.FixedFileExtension;
import cho.ym.madrascheck.enums.converter.FixedFileExtensionConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "fixed_file_extension")
@Entity
public class FixedFileExtensionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = FixedFileExtensionConverter.class)
    private FixedFileExtension extension;

    private boolean isChecked;



}
