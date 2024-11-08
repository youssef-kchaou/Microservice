package com.example.courzeloproject.Service;
import java.net.MalformedURLException;
import java.nio.file.Files;
import com.example.courzeloproject.Entite.Profile;
import com.example.courzeloproject.Entite.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.example.courzeloproject.Repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import org.springframework.core.io.UrlResource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements IProfileService{
    @Autowired
    ProfileRepo repo;
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public Profile addProfile(Profile p) {
        return repo.save(p);
    }

    @Override
    public void deleteProfile(String id) {
         repo.deleteById(id);
    }

    @Override
    public Profile updateProfile(String id , Profile profile) {
        Profile existingProfile = this.repo.findById(id).get();

        existingProfile.setFirstName(profile.getFirstName());
        existingProfile.setLastName(profile.getLastName());
        existingProfile.setAddress(profile.getAddress());
        existingProfile.setPhone(profile.getPhone());
        existingProfile.setUser(profile.getUser());
        return this.repo.save(existingProfile) ;
    }

    @Override
    public List<Profile> getAllProfile() {
        return repo.findAll();
    }

    @Override
    public Optional<Profile> getProfileByIdUser(String id_user) {
        return repo.findByIdUser(id_user);
    }

    @Override
    public Profile getProfileByid(String id) {
        return repo.findById(id).get();
    }

    @Override
    public Profile storeFile(MultipartFile file, String Pid) {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String newFileName = generateNewFileName(originalFileName);

        Path uploadPath = Paths.get(uploadDir);

        try {
            if (Files.notExists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(newFileName);
            Files.copy(file.getInputStream(), filePath);


            Optional<Profile> p = repo.findById(Pid);
            p.get().setPhoto(newFileName);
            return repo.save(p.get());
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + newFileName, e);
        }
    }
    private String generateNewFileName(String originalFileName) {
        // You can customize this method to generate a unique file name.
        // For example, appending a timestamp or using a UUID.
        String timestamp = String.valueOf(System.currentTimeMillis());
        return timestamp + "_" + originalFileName;
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("File not found: " + fileName, e);
        }
    }
}
