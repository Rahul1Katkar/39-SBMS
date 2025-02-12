package Project;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileUploadApplication implements CommandLineRunner {

	private final Path root = Paths.get("uploads");
			
	public static void main(String[] args) {
		SpringApplication.run(FileUploadApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Files.createDirectories(root);
		
	}

}
