package com.example.ocrprocessor.utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MimeTypeUtils {
    IMAGE_PNG("image/png"),
    PDF("application/pdf");

    private final String contentType;
}
