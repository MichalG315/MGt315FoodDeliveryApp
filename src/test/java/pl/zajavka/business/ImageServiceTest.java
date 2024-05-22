package pl.zajavka.business;

import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

@ExtendWith(MockitoExtension.class)
class ImageServiceTest {

    @InjectMocks
    private ImageService imageService;

    @Test
    void saveImage() {
        // given
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "file.txt",
                ContentType.TEXT_PLAIN.toString(),
                "test text".getBytes()
        );
        // when
        String result = imageService.saveImage(file);

        // then
        Assertions.assertThat(result).contains("/images/foodImages/");
        Assertions.assertThat(result).contains(".png");
    }
}