package com.ljt.study.slice.client;

import com.alibaba.fastjson.JSON;
import com.ljt.study.slice.dto.UploadResponseDTO;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author LiJingTang
 * @date 2022-11-30 14:50
 */
class SliceUploadClient {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    static {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) EXECUTOR_SERVICE;
        executorService.prestartAllCoreThreads();
    }

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\aichitech\\Desktop\\apache-tomcat-8.5.69-windows-x64.zip");
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel inChannel = inputStream.getChannel();
        final long chunkSize = 1024 * 1024 * 1;
        final int count = (int) (file.length() / chunkSize);
        List<File> fileList = new ArrayList<>();

        for (int i = 0; i <= count; i++) {
            File outFile = new File("C:\\Users\\aichitech\\Desktop\\slice\\", String.valueOf(i));

            try (FileOutputStream outputStream = new FileOutputStream(outFile);
                 FileChannel outChannel = outputStream.getChannel()) {
                long length = i != count ? chunkSize : file.length() - (i * chunkSize);
                inChannel.transferTo(chunkSize * i, length, outChannel);
                fileList.add(outFile);
            }
        }


        final String url = "http://localhost:8080/slice/upload";
        final String id = UUID.randomUUID().toString();
        final RestTemplate restTemplate = new RestTemplate();
        final CountDownLatch latch = new CountDownLatch(fileList.size());

        for (int i = 0; i < fileList.size(); i++) {
            File tempFile = fileList.get(i);

            // 1、封装请求头
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("multipart/form-data");
            headers.setContentType(type);
            headers.setContentLength(tempFile.length());
//            headers.setContentDispositionFormData("media", tempFile.getName());
            // 2、封装请求体
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            FileSystemResource resource = new FileSystemResource(tempFile);
            param.add("file", resource);
            param.add("originName", file.getName());
            param.add("id", id);
            param.add("chunkNum", fileList.size());
            param.add("chunkNo", i);
            param.add("chunkSize", chunkSize);
            // 3、封装整个请求报文
            HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<>(param, headers);
            // 4、发送请求
            int finalI = i;
            EXECUTOR_SERVICE.submit(() -> {
                UploadResponseDTO responseDTO = restTemplate.postForObject(url, formEntity, UploadResponseDTO.class);
                System.out.println(finalI + " => " + JSON.toJSONString(responseDTO));
                latch.countDown();
            });
        }


        latch.await();
        System.out.println("---------- OK");
        EXECUTOR_SERVICE.shutdown();
    }

}
