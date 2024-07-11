package cho.ym.madrascheck.extension.fixed.repository;

import cho.ym.madrascheck.enums.FixedFileExtension;
import cho.ym.madrascheck.extension.fixed.repository.domain.FixedFileExtensionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FixedFileExtensionRepository extends JpaRepository<FixedFileExtensionEntity, Long> {

    boolean isChecked(String extension);

    @Query("update FixedFileExtensionEntity set isChecked = true where extension = :extension")
    void check(FixedFileExtension fixedFileExtension);

    @Query("update FixedFileExtensionEntity set isChecked = false where extension = :extension")
    void cancel(FixedFileExtension fixedFileExtension);
}
