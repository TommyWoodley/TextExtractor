package com.example.ocrprocessor.service.extractors;

import com.example.ocrprocessor.utils.TesseractOcrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageExtractor implements ContentExtractor {
    final Tesseract tesseract;

    @Override
    public String extractContent(MultipartFile file) throws IOException {
        return doOcr(file);
    }

    private String doOcr(MultipartFile image) throws IOException {
        try {
            return tesseract.doOCR(TesseractOcrUtil.createImageFromBytes(image.getBytes()));
        } catch (TesseractException e) {
            log.error("Tesseract error: " + e.getMessage());
        }
        return null;
    }
}
