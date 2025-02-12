package Project.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;import org.springframework.http.HttpHeaders;



@RestController
public class User {

	private final Path root = Paths.get("uploads");
	
	@PostMapping("/u")
	public ResponseEntity<String> saveuser(@RequestBody User user) {
		System.out.println(user);
		
		return new ResponseEntity<String>("user aded", HttpStatus.OK);
		
	}
	
	@PostMapping("/file")
	public ResponseEntity<String> Fileuplload(@RequestParam("file") MultipartFile file) throws IOException{
		try {
		Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("File Uploaded", HttpStatus.OK);
	}
	
	// to add both file and user data
	@PostMapping("/uf")
	public ResponseEntity<String> addUserAndFile(@RequestParam("user") String user, @RequestParam("file") MultipartFile file){
		
		System.out.println(user);
		
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			return new ResponseEntity<String>(" Uploaded", HttpStatus.OK);
		
	}
	
	
// download file 
	@GetMapping("/download/{filename}")
	public ResponseEntity<Resource> dowloadFile(@PathVariable String filename){
		
		try {
            Path filepath = root.resolve(filename);
            Resource file = new UrlResource(filepath.toUri());

            if (file.exists() || file.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                        .body(file);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
		
	}
}
