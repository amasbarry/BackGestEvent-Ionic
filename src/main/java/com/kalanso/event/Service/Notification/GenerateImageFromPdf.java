package com.kalanso.event.Service.Notification;


import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@AllArgsConstructor
public class GenerateImageFromPdf {

    byte[] generateImageFromPdf(byte[] pdfBytes) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfBytes);
        PDDocument document = PDDocument.load(inputStream);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        BufferedImage image = pdfRenderer.renderImageWithDPI(0, 1000); // Render the first page with 4000 DPI

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        document.close();

        return outputStream.toByteArray();
    }
}
