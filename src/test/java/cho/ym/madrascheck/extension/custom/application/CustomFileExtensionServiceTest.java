package cho.ym.madrascheck.extension.custom.application;

import cho.ym.madrascheck.extension.custom.repository.CustomFileExtensionRepository;
import cho.ym.madrascheck.extension.custom.repository.domain.CustomFileExtensionEntity;
import cho.ym.madrascheck.extension.custom.web.request.CustomFileExtensionDeleteRequest;
import cho.ym.madrascheck.extension.custom.web.request.CustomFileExtensionRequest;
import cho.ym.madrascheck.extension.custom.web.response.CustomFileExtensionResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomFileExtensionServiceTest {

    @InjectMocks
    private CustomFileExtensionService fileExtensionService;

    @Mock
    private CustomFileExtensionRepository customFileExtensionRepository;

    @DisplayName("커스텀 확장자를 추가할 수 있습니다. ")
    @Test
    void addCustomFileExtension() {
        String expect = "test";
        CustomFileExtensionRequest request = new CustomFileExtensionRequest(expect);
        CustomFileExtensionEntity entity = new CustomFileExtensionEntity(1L, expect);
        when(customFileExtensionRepository.save(Mockito.any())).thenReturn(entity);

        CustomFileExtensionResponse actual = fileExtensionService.add(request);

        assertThat(actual).isNotNull();
        assertThat(actual.extension()).isEqualTo(expect);
    }

    @DisplayName("커스텀 확장자를 201개 저장하면 에러가 납니다. ")
    @Test
    void canNotAddOver200() {
        when(customFileExtensionRepository.count()).thenReturn(200L);

        CustomFileExtensionRequest request = new CustomFileExtensionRequest("test");

        assertThrows(IllegalArgumentException.class, () -> {
            fileExtensionService.add(request);
        });
    }


    @DisplayName("커스텀 확장자 리스트를 가져올 수 있습니다. ")
    @Test
    void findCustomFileExtensions() {
        List<CustomFileExtensionEntity> expect = List.of(new CustomFileExtensionEntity(1L, "test")
                , new CustomFileExtensionEntity(2L, "test2")
                , new CustomFileExtensionEntity(3L, "test3"));

        when(customFileExtensionRepository.findAll()).thenReturn(expect);

        List<CustomFileExtensionResponse> result = fileExtensionService.allCustomFileExtensions();
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(expect.size());
    }

    @DisplayName("하나의 커스텀 확장자를 가져올 수 있습니다. ")
    @Test
    void findOneCustomFileExtensions() {
        CustomFileExtensionEntity entity = new CustomFileExtensionEntity(1L, "test");
        when(customFileExtensionRepository.findById(Mockito.any())).thenReturn(java.util.Optional.of(entity));

        CustomFileExtensionResponse actual = fileExtensionService.findOne(1L);
        assertThat(actual).isNotNull();
        assertThat(actual.extension()).isEqualTo(entity.getExtension());
    }

    @DisplayName("커스텀 확장자를 삭제할 수 있습니다.")
    @Test
    void deleteCustomFileExtension() {
        String expect = "test";
        CustomFileExtensionEntity entity = new CustomFileExtensionEntity(1L, expect);
        when(customFileExtensionRepository.findById(1L)).thenReturn(Optional.of(entity));

        CustomFileExtensionDeleteRequest deleteRequest = new CustomFileExtensionDeleteRequest(entity.getId());
        fileExtensionService.delete(deleteRequest);

        when(customFileExtensionRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> {
            fileExtensionService.findOne(1L);
        });
    }


}