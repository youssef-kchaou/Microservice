package com.example.courzeloproject.Service;

import com.example.courzeloproject.Entite.Profile;
import com.example.courzeloproject.Entite.User;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IProfileService {
    public Profile addProfile(Profile p) ;
    public void deleteProfile(String id) ;
    public Profile updateProfile(String id , Profile p) ;
    public List<Profile> getAllProfile () ;
    public Optional<Profile> getProfileByIdUser (String id_user) ;
    public Profile getProfileByid (String id) ;
    Profile storeFile(MultipartFile file, String Pid);

    Resource loadFileAsResource(String fileName);
}
