package cho.ym.madrascheck.extension.custom.repository;

import cho.ym.madrascheck.extension.custom.repository.domain.CustomFileExtensionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomFileExtensionRepository extends JpaRepository<CustomFileExtensionEntity, Long> {
}
