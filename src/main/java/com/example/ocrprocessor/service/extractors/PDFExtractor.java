package com.example.ocrprocessor.service.extractors;

import com.example.ocrprocessor.utils.TextNormalisation;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class PDFExtractor implements ContentExtractor {
    @Override
    public String extractContent(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream();
             PDDocument document = PDDocument.load(inputStream)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            return TextNormalisation.normalise(text);
        }
    }
}
