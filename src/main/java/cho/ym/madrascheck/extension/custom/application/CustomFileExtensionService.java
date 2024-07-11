package cho.ym.madrascheck.extension.custom.application;

import cho.ym.madrascheck.extension.custom.repository.CustomFileExtensionRepository;
import cho.ym.madrascheck.extension.custom.repository.domain.CustomFileExtensionEntity;
import cho.ym.madrascheck.extension.custom.web.request.CustomFileExtensionDeleteRequest;
import cho.ym.madrascheck.extension.custom.web.request.CustomFileExtensionRequest;
import cho.ym.madrascheck.extension.custom.web.response.CustomFileExtensionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomFileExtensionService {

    private static final int MAX_EXTENSIONS = 200;
    private final CustomFileExtensionRepository repository;

    public CustomFileExtensionService(CustomFileExtensionRepository repository) {
        this.repository = repository;
    }

    public CustomFileExtensionResponse add(CustomFileExtensionRequest request) {
        CustomFileExtensionEntity entity = CustomFileExtensionEntity.from(request.extension());

        long count = repository.count();
        if (count>=MAX_EXTENSIONS){
            throw new IllegalArgumentException("커스텀 확장자는 200개까지만 등록할 수 있습니다.");
        }

        CustomFileExtensionEntity save = repository.save(entity);
        return CustomFileExtensionResponse.from(save);
    }

    public List<CustomFileExtensionResponse> allCustomFileExtensions() {
        List<CustomFileExtensionEntity> all = repository.findAll();
        return all.stream().map(CustomFileExtensionResponse::from).toList();
    }

    public CustomFileExtensionResponse findOne(long id) {
        CustomFileExtensionEntity entity = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("커스텀 확장자를 찾을 수 없습니다."));
        return CustomFileExtensionResponse.from(entity);
    }

    public void delete(CustomFileExtensionDeleteRequest deleteRequest) {
        repository.deleteById(deleteRequest.id());
    }
}
