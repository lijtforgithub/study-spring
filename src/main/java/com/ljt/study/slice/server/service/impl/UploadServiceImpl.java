package com.ljt.study.slice.server.service.impl;

import com.ljt.study.slice.dto.UploadRequestDTO;
import com.ljt.study.slice.dto.UploadResponseDTO;
import com.ljt.study.slice.server.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * @author LiJingTang
 * @date 2022-11-30 10:44
 */
@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    private static final String TEMP_PATH = System.getProperty("user.home") + "/temp/car-log/slice";
    private static final String RW_MODE = "rw";

    @Value("${upload.queue.maxSize:100}")
    private Integer queueMaxSize;
    @Autowired
    private ThreadPoolTaskExecutor uploadTaskExecutor;

    private CompletionService<UploadResponseDTO> completionService;

    @PostConstruct
    void init() {
        completionService = new ExecutorCompletionService<>(uploadTaskExecutor.getThreadPoolExecutor(),
                new LinkedBlockingDeque<>(queueMaxSize));

        try {
            FileUtils.forceMkdir(new File(TEMP_PATH));
        } catch (IOException e) {
            log.error("创建目录失败" + TEMP_PATH, e);
        }
    }

    @Override
    public UploadResponseDTO upload(UploadRequestDTO requestDTO) {
        if (requestDTO.getChunkNum() > 0) {
            Function<UploadRequestDTO, UploadResponseDTO> fun = this::sliceUpload;
            completionService.submit(() -> fun.apply(requestDTO));
            try {
                return completionService.take().get();
            } catch (Exception e) {
                log.error("获取上传结果失败", e);
            }
        } else {
            // 一个块 即小文件 没有分片
        }

        return null;
    }

    private UploadResponseDTO sliceUpload(UploadRequestDTO requestDTO) {
        File tempFile = getTempFile(requestDTO.getId());

        try (RandomAccessFile randFile = new RandomAccessFile(tempFile, RW_MODE)) {
            long offset = requestDTO.getChunkSize() * requestDTO.getChunkNo();
            randFile.seek(offset);
            randFile.write(requestDTO.getFile().getBytes());

            UploadResponseDTO responseDTO = new UploadResponseDTO();
            responseDTO.setId(requestDTO.getId());
            responseDTO.setOriginName(requestDTO.getOriginName());
            responseDTO.setChunkNo(requestDTO.getChunkNo());
            responseDTO.setCompleted(isCompleted(requestDTO));

            if (Boolean.TRUE.equals(responseDTO.getCompleted())) {
                log.info("文件{} = {}上传完成", requestDTO.getId(), requestDTO.getOriginName());
                CompletableFuture.runAsync(() -> this.postCompleted(requestDTO.getId(), requestDTO.getOriginName()));
            }
            return responseDTO;
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return null;
        }
    }

    private File getTempFile(String id) {
        return new File(TEMP_PATH, id + ".temp");
    }

    private File getConfFile(String id) {
        return new File(TEMP_PATH, id + ".conf");
    }

    private boolean isCompleted(UploadRequestDTO requestDTO) {
        File confFile = getConfFile(requestDTO.getId());

        try (RandomAccessFile randFile = new RandomAccessFile(confFile, RW_MODE)) {
            randFile.setLength(requestDTO.getChunkNum());
            randFile.seek(requestDTO.getChunkNo());
            randFile.write(Byte.MAX_VALUE);
            log.info("文件 {} 的第 {} 部分写入完成", requestDTO.getId(), requestDTO.getChunkNo());

            byte[] bytes = FileUtils.readFileToByteArray(confFile);
            byte completed = Byte.MAX_VALUE;

            for (int i = 0; i < bytes.length && completed == Byte.MAX_VALUE; i++) {
                completed = (byte) (completed & bytes[i]);
                log.info("文件 {} 第 {} 部分完成 {}", requestDTO.getId(), i, bytes[i]);
            }

            return completed == Byte.MAX_VALUE;
        } catch (Exception e) {
            log.error("判断上传进度异常", e);
            return false;
        }
    }

    private void postCompleted(String id, String originName) {
        File tempFile = getTempFile(id);
        File confFile = getConfFile(id);

        if (tempFile.exists()) {
            File newFile = new File(TEMP_PATH, originName);
            boolean success = tempFile.renameTo(newFile);
            log.info("重命名文件{} => {} 成功：{}", tempFile.getName(), newFile.getName(), success);
        }

        FileUtils.deleteQuietly(confFile);
    }

}
