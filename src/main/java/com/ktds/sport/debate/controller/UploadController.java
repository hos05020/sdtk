package com.ktds.sport.debate.controller;


import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.ktds.sport.debate.aws.S3Client;
import com.ktds.sport.debate.common.ApiResult;
import com.ktds.sport.debate.common.AttachedFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static com.ktds.sport.debate.common.ApiResult.*;
import static com.ktds.sport.debate.common.AttachedFile.toAttachedFile;
import static java.util.Optional.ofNullable;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UploadController {

    private final S3Client s3Client;

    @PostMapping("/uploadAjax")
    public String uploadFile(@RequestParam("image") MultipartFile image) {
        Optional<AttachedFile> attachedFileOpt = toAttachedFile(image);
        AtomicReference<String> profileImageUrl = new AtomicReference<>("");
        if (attachedFileOpt.isPresent()) {
            Optional<String> profileImageUrlOpt = uploadProfileImage(attachedFileOpt.get());
            profileImageUrlOpt.ifPresent(profileImageUrl::set);
        }
        return profileImageUrl.get();
    }


    public Optional<String> uploadProfileImage(AttachedFile profileFile) {
        String profileImageUrl = null;
        if (profileFile != null) {
            String key = profileFile.randomName("profiles", "jpeg");
            try {
                profileImageUrl = s3Client.upload(profileFile.inputStream(), profileFile.length(), key, profileFile.getContentType(), null);
            } catch (AmazonS3Exception e) {
                log.warn("Amazon S3 error (key: {}): {}", key, e.getMessage(), e);
            }
        }
        return ofNullable(profileImageUrl);
    }

}
