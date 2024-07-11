package cho.ym.madrascheck.extension.fixed.application;

import cho.ym.madrascheck.enums.FixedFileExtension;
import cho.ym.madrascheck.extension.fixed.web.response.FixedFileExtensionResponse;
import cho.ym.madrascheck.extension.fixed.repository.FixedFileExtensionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FixedFileExtensionServiceTest {

    @InjectMocks
    private FixedFileExtensionService fileExtensionService;

    @Mock
    private FixedFileExtensionRepository fixedFileExtensionRepository;

    @DisplayName("전체 고정 확장자 리스트를 가져올 수 있습니다. ")
    @Test
    void findAllFixedFileExtensions() {
        List<FixedFileExtensionResponse> fixedFileExtensionResponses = fileExtensionService.allFixedFileExtensions();

        assertThat(fixedFileExtensionResponses).isNotNull();
        assertThat(fixedFileExtensionResponses.size()).isEqualTo(FixedFileExtension.values().length);
    }

    @DisplayName(" 고정 확장자의 기본 값은 unChecked 입니다. ")
    @Test
    void fixedFileExtensionDefaultIsUnChecked() {
        Arrays.stream(FixedFileExtension.values()).forEach(extension -> {
            assertThat(fileExtensionService.isChecked(extension.getExtension())).isFalse();
        });
    }

    @DisplayName("고정 확장자를 체크할 수 있습니다. ")
    @Test
    void checkingFixedExtension() {
        String comExtension = FixedFileExtension.COM.getExtension();
        when(fixedFileExtensionRepository.isChecked(comExtension)).thenReturn(true);

        fileExtensionService.check(comExtension);

        assertThat(fileExtensionService.isChecked(comExtension)).isTrue();
    }

    @DisplayName("고정 확장자의 체크를 취소 할 수 있습니다. ")
    @Test
    void cancelFixedExtension() {
        String comExtension = FixedFileExtension.COM.getExtension();
        when(fixedFileExtensionRepository.isChecked(comExtension)).thenReturn(false);

        fileExtensionService.cancel(comExtension);

        assertThat(fileExtensionService.isChecked(comExtension)).isFalse();
    }

}