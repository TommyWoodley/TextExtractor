package com.example.ocrprocessor.service.extractors;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ContentExtractor {
    String extractContent(MultipartFile file) throws IOException;
}
