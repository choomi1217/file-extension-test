package cho.ym.madrascheck.extension.custom.repository.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "custom_file_extension")
@Entity
public class CustomFileExtensionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String extension;

    public CustomFileExtensionEntity(long id, String extension) {
        this.id = id;
        this.extension = extension;
    }

    public CustomFileExtensionEntity(String extension) {
        this.extension = extension;
    }

    public static CustomFileExtensionEntity from(String extension) {
        return new CustomFileExtensionEntity(extension);
    }
}
