package com.ljt.study.protocol;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

/**
 * @author LiJingTang
 * @date 2021-04-27 17:52
 */
@Slf4j
@RestController
@RequestMapping("/sse")
public class SseController {

    private static final Map<String, SseEmitter> SSE_CACHE = new ConcurrentHashMap<>();

    @GetMapping(produces = TEXT_EVENT_STREAM_VALUE)
    public String index() {
        log.info("来了 老弟 {}", Thread.currentThread().getName());

        return "data:" + LocalDateTime.now().toString() + " \n\n";
    }


    @GetMapping(path = "/subscribe/{id}")
    public SseEmitter subscribe(@PathVariable String id) {
        // 超时时间设置为1小时
        SseEmitter sseEmitter = new SseEmitter(3600000L);
        SSE_CACHE.put(id, sseEmitter);
        // 超时回调 触发
        sseEmitter.onTimeout(() -> SSE_CACHE.remove(id));
        // 结束之后的回调触发
        sseEmitter.onCompletion(() -> log.info("完成！！！"));
        return sseEmitter;
    }

    @GetMapping(path = "/push/{id}")
    public String push(@PathVariable String id, String content) throws IOException {
        SseEmitter sseEmitter = SSE_CACHE.get(id);
        if (Objects.nonNull(sseEmitter)) {
            // 发送消息
            sseEmitter.send(content);
        }

        return "SseEmitter:push";
    }

    @GetMapping(path = "/over/{id}")
    public String over(@PathVariable String id) {
        SseEmitter sseEmitter = SSE_CACHE.get(id);
        if (Objects.nonNull(sseEmitter)) {
            // 执行完毕，断开连接
            sseEmitter.complete();
            SSE_CACHE.remove(id);
        }

        return "SseEmitter:over";
    }

    @GetMapping(path = "/push-all")
    public String pushAll(String content) {
        SSE_CACHE.forEach((k, v) -> {
            try {
                v.send(content);
            } catch (IOException e) {
                log.error("异常", e);
            }
        });

        return "SseEmitter:pushAll";
    }

}
