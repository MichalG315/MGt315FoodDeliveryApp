package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class ImageService {

    static final String DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images/foodImages";

    public String saveImage(MultipartFile file) {
        String newPath = RandomStringUtils.randomAlphabetic(20) + ".png";
        Path fileNameAndPath = Paths.get(DIRECTORY, newPath);
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can not save image", e);
        }
        return "/images/foodImages/" + newPath;
    }
}
