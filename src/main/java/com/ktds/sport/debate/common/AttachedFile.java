package com.ktds.sport.debate.common;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.apache.commons.io.FilenameUtils.getExtension;
import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public class AttachedFile {
    private final String originalFileName;

    private final String contentType;

    private final byte[] bytes;

    public AttachedFile(String originalFileName, String contentType, byte[] bytes) {
        this.originalFileName = originalFileName;
        this.contentType = contentType;
        this.bytes = bytes;
    }

    private static boolean verify(MultipartFile multipartFile) {
        if (multipartFile != null && multipartFile.getSize() > 0 && multipartFile.getOriginalFilename() != null) {
            String contentType = multipartFile.getContentType();
            // 첨부파일 타입을 확인하고 이미지인 경우 처리
            return isNotEmpty(contentType) && contentType.toLowerCase().startsWith("image");
        }
        return false;
    }

    public static Optional<AttachedFile> toAttachedFile(MultipartFile multipartFile) {
        try {
            if (verify(multipartFile)) {
                return of(
                    new AttachedFile(multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getBytes())
                );
            }
        } catch (IOException ignored) {
        }
        return empty();
    }

    public String extension(String defaultExtension) {
        return defaultIfEmpty(getExtension(originalFileName), defaultExtension);
    }

    public String randomName(String defaultExtension) {
        return randomName(null, defaultExtension);
    }

    public String randomName(String basePath, String defaultExtension) {
        String name = isEmpty(basePath) ? UUID.randomUUID().toString() : basePath + "/" + UUID.randomUUID().toString();
        return name + "." + extension(defaultExtension);
    }

    public InputStream inputStream() {
        return new ByteArrayInputStream(bytes);
    }

    public long length() {
        return bytes.length;
    }

    public String getContentType() {
        return contentType;
    }
}