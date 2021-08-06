package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.dto.FileDto;
import com.ssafy.study_with_us.service.ProfileService;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/study")
    public ResponseEntity<Resource> viewImg(@RequestParam Long id) throws IOException {
        System.out.println("id = " + id);
        Path path = new File(profileService.getProfile(id)).toPath();
        return getResponseEntity(path);
    }

    @GetMapping
    public ResponseEntity<Resource> viewImg() throws IOException {
        Path path = new File(profileService.getProfile(null)).toPath();
        return getResponseEntity(path);
    }

    private ResponseEntity<Resource> getResponseEntity(Path path) throws IOException {
        FileSystemResource resource = new FileSystemResource(path);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(Files.probeContentType(path)))
                .body(resource);
    }
}
