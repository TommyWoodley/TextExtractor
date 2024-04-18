package com.example.ocrprocessor.service.extractors;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class PDFExtractorTest {

    @Test
    void testPdfTextExtraction() throws IOException {
        String filePath = "file.pdf";
        byte[] fileContent = readFileFromResources(filePath);

        MultipartFile mockFile = new MockMultipartFile(
                "file.pdf",
                "file.pdf",
                "application/pdf",
                fileContent
        );

        ContentExtractor extractor = new PDFExtractor();

        String extractedContent = extractor.extractContent(mockFile);

        String expectedContent = "file\t\n" +
                "  format\t\n" +
                "  commons\t\n" +
                "  pdf\t\n" +
                "  \n" +
                "\t\n" +
                "  \n" +
                "01100110011010010110110001100101001000000110011001101111011100100\n" +
                "11011010110000101110100001000000110001101101111011011010110110101\n" +
                "1011110110111001110011\t\n" +
                "  \n";

        assertEquals(expectedContent, extractedContent);
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