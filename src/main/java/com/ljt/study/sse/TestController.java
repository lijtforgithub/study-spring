package com.ljt.study.sse;

import com.ljt.study.entity.Log;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * @author jtli3
 * @date 2022-01-26 13:57
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/get/{key}")
    public String get(@PathVariable String key, String param, Log log) {
        return GetMapping.class.getSimpleName();
    }

    @PostMapping("/post")
    public String post(@RequestBody Log log) {
        return PostMapping.class.getSimpleName();
    }

    @PutMapping("/put")
    public String put() {
        return PutMapping.class.getSimpleName();
    }

    @RequestMapping("/req")
    public String req(MultipartFile file) {
        return RequestMapping.class.getSimpleName();
    }


    @GetMapping("/pdf")
    public void imageToPdf(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String image = "D:\\李敬堂\\test.png";
        final String pdf = "D:\\李敬堂\\ImageToPdf.pdf";

        imageToPdf(new FileInputStream(image), response.getOutputStream());
    }

    private static void imageToPdf(InputStream imageInput, OutputStream pdfOutput) throws IOException {
        Objects.requireNonNull(imageInput, "imageInput");
        Objects.requireNonNull(pdfOutput, "pdfOutput");

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, pdfOutput);
        document.open();

        byte[] bytes = IOUtils.toByteArray(imageInput);
        Image image = Image.getInstance(bytes);
        image.setAlignment(Element.ALIGN_CENTER);

        float f = Math.min(1, PageSize.A4.getWidth() / image.getWidth());
        f = BigDecimal.valueOf(f).setScale(2, RoundingMode.DOWN).floatValue();
        image.scalePercent(f * 100);

        float width = image.getScaledWidth();
        float height = image.getScaledHeight();
        int pageNum = Math.round(height / PageSize.A4.getHeight());

        if (pageNum > 1) {
            PdfContentByte canvas = writer.getDirectContentUnder();

            for (int i = 1; i <= pageNum; i++) {
                float h = -(PageSize.A4.getHeight() * (pageNum - i));
                document.newPage();
                canvas.addImage(image, width, 0, 0, height, 0, h - i);
            }
        } else {
            document.newPage();
            document.add(image);
        }

        document.close();
    }

}
