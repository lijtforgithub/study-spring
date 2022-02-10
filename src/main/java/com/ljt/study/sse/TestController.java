package com.ljt.study.sse;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * @author jtli3
 * @date 2022-01-26 13:57
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/pdf")
    public void imageToPdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String image = "D:\\李敬堂\\离职证明.jpg";
        final String pdf = "D:\\李敬堂\\ImageToPdf.pdf";

        imageToPdf(new FileInputStream(image), response.getOutputStream());
    }

    private void imageToPdf(InputStream imageInput, OutputStream pdfOutput) throws Exception {
        Objects.requireNonNull(imageInput, "imageInput");
        Objects.requireNonNull(pdfOutput, "pdfOutput");

        Document document = new Document();
        PdfWriter.getInstance(document, pdfOutput);
        document.open();

        byte[] bytes = IOUtils.toByteArray(imageInput);
        Image image = Image.getInstance(bytes);
        image.setAlignment(Element.ALIGN_CENTER);

        float f = Math.min(1, PageSize.A4.getWidth() / image.getWidth());
        f = BigDecimal.valueOf(f).setScale(2, RoundingMode.DOWN).floatValue();

        image.scalePercent(f * 95);

        document.add(image);
        document.close();
    }

}
