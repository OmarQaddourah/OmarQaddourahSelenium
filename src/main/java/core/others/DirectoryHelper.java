package core.others;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryHelper {

    private static final Path DIRECTORY = Path.of("src/test/resources/Downloads");

    public static void clearDirectory() {
        try {
            Files.walk(DIRECTORY).filter(path -> !path.equals(DIRECTORY)).forEach(path -> {
                try {
                    Files.delete(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException exception) {
            System.out.println("Dir is not empty");
        }
    }

    public static void downloadFile(String fileUrl) {
        String directoryPath = "src/test/resources/Downloads";
        try {
            URL url = new URL(fileUrl);
            Path directory = Paths.get(directoryPath);
            Path file = directory.resolve(url.getFile());
            Files.copy(url.openStream(), file);
            System.out.println("File downloaded successfully.");
        } catch (IOException ioException) {
            System.err.println("Error downloading file: " + ioException.getMessage());
        }
    }
}
