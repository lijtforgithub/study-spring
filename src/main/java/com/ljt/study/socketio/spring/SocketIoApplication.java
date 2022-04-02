package com.ljt.study.socketio.spring;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LiJingTang
 * @date 2022-04-02 13:55
 */
@Slf4j
@SpringBootApplication
class SocketIoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SocketIoApplication.class);
    }

    @Autowired
    private SocketIOServer server;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        server.start();
    }

}
