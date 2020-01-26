package common.solutions.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public final class FileUtils {

  private FileUtils(){

  }

  public static File createFileFromResource(Path source, String fileNamePrefix, String fileNameSuffix) throws IOException {
      Path tempFile = Files.createFile(Paths.get( "D:\\JAVA\\EPAM_SPB\\Training\\initdata\\temp\\" +
              RandomStringUtils.randomAlphabetic(8) + ".txt"));
      Files.copy(source, tempFile, REPLACE_EXISTING);
      return tempFile.toFile();
  }

}
