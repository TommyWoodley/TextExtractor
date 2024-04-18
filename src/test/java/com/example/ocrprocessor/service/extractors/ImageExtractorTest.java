package com.example.ocrprocessor.service.extractors;

import net.sourceforge.tess4j.Tesseract;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

class ImageExtractorTest {

    @Test
    void testPngExtraction() throws IOException {
        String filePath = "image.png";
        byte[] fileContent = readFileFromResources(filePath);

        MultipartFile mockFile = new MockMultipartFile(
                "image.png",
                "image.png",
                "image/png",
                fileContent
        );

        ImageExtractor extractor = new ImageExtractor(new Tesseract());

        String extractedContent = extractor.extractContent(mockFile);

        System.out.println(extractedContent);

    }

    private byte[] readFileFromResources(String fileName) throws IOException {
        // Obtain InputStream from the classpath
        InputStream inputStream = getClass().getResourceAsStream("/" + fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        }

        // Read all bytes from an InputStream
        byte[] fileBytes = inputStream.readAllBytes();

        // Close the InputStream
        inputStream.close();

        return fileBytes;
    }
}