package com.example.courzeloproject.Controller;

import com.example.courzeloproject.Entite.Profile;
import com.example.courzeloproject.Service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    @Autowired
    IProfileService profileService ;

    @PostMapping("/addProfile")
    public Profile addProfile(@RequestBody Profile p){
        return profileService.addProfile(p) ;
    }

    @GetMapping("/getAllProfile")
    public List<Profile> getProfiles() {
        return profileService.getAllProfile();
    }
    @GetMapping("/getProfile/{profile-id}")
    public Profile getProfileById(@PathVariable("profile-id") String pId) {
        return profileService.getProfileByid(pId);
    }

    @GetMapping("/getProfileByUser/{user-id}")
    public Optional<Profile> getProfileByIdUser(@PathVariable("user-id") String uId) {
        return profileService.getProfileByIdUser(uId);
    }
    @DeleteMapping("/deleteProfile/{profile-id}")
    public void removeProfile(@PathVariable("profile-id") String bId) {
        profileService.deleteProfile(bId);
    }
    @PutMapping("/modify-profile/{id}")
    public Profile modifyBloc(@RequestBody Profile p,@PathVariable("id") String id) {
       return profileService.updateProfile(id,p);
    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName) {
        Resource resource = profileService.loadFileAsResource(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    //upload image
    @PostMapping("/upload/{id}")
    public Profile handleFileUpload(@RequestParam("photo") MultipartFile file,
                                    @PathVariable("id") String pid) {

        return profileService.storeFile(file,pid);
    }
}
