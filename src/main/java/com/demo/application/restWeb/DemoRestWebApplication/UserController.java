package com.demo.application.restWeb.DemoRestWebApplication;

import com.demo.application.restWeb.DemoRestWebApplication.Beans.Response;
import com.demo.application.restWeb.DemoRestWebApplication.Beans.Users;
import com.demo.application.restWeb.DemoRestWebApplication.ErrorHandling.BadRequestException;
import com.demo.application.restWeb.DemoRestWebApplication.UserService.EmailService;
import com.demo.application.restWeb.DemoRestWebApplication.UserService.FileStorageService;
import com.demo.application.restWeb.DemoRestWebApplication.UserService.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    private EmailService emailService;

    @Autowired
    private Users user;

    @Autowired
    private FileStorageService fileStorageService;


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/Users")
    public ResponseEntity<Users> createUsers(@RequestBody Users users) {
        Users createdUser = service.createUsers(users);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/Users")
    public List<Users> getAllUsers() throws Exception {
        List<Users> userList = service.getAllUsers();
        if (userList == null) {
            throw new Exception("No Users found");
        }
        return userList;
    }

    @GetMapping("/Users/search")
    public List<Users> searchUserByFirstName(@RequestParam ("name") String firstname ) throws Exception{
        List<Users> FoundUser = service.findByFirstName(firstname);
        if(FoundUser.isEmpty()){
            throw new BadRequestException("Name is required");
        }
        return FoundUser;
    }
    @GetMapping("/Users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findUsersById(id));
    }

    @PutMapping("/Users/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable String id, @RequestBody Users users) {
        users.setId(id);
        return ResponseEntity.ok().body(this.service.updateUsers(users));
    }

    @DeleteMapping("/Users/{id}")
    public void deleteUser(@PathVariable String id) {
        this.service.deleteUsersById(id);

    }


    @RequestMapping("/Send-mail")
    public String sendmail()
    {
        user.setEmail("ab.7892649170@gmail.com");
        try {
            emailService.sendMail(user);
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Congratulations! Your mail has been send to the user.";
    }

    @PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new Response(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity < Resource > downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}


