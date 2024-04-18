package com.example.ocrprocessor.service;

import com.example.ocrprocessor.service.extractors.ContentExtractor;
import com.example.ocrprocessor.service.extractors.ImageExtractor;
import com.example.ocrprocessor.service.extractors.PDFExtractor;
import com.example.ocrprocessor.utils.MimeTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentExtractionFactory {
    @Autowired
    private ImageExtractor imageExtractor;

    @Autowired
    private PDFExtractor pdfExtractor;

    public ContentExtractor getExtractor(String contentType) {
        switch (MimeTypeUtils.valueOf(contentType)) {
            case IMAGE_PNG:
                return imageExtractor;
            case PDF:
                return pdfExtractor;
            default:
                throw new UnsupportedOperationException("File type not supported");
        }
    }
}
