package com.ljt.study.socketio.client;

import com.corundumstudio.socketio.Configuration;

import java.io.InputStream;


/**
 * @author LiJingTang
 * @date 2022-04-01 16:53
 */
class SslServer extends AbstractServer {

    public static void main(String[] args) {
        SslServer sslServer = new SslServer();
        sslServer.start();
    }

    @Override
    protected Configuration getConfig() {
        Configuration config = new Configuration();
        config.setPort(10443);

        config.setKeyStorePassword("test1234");
        InputStream stream = SslServer.class.getResourceAsStream("/socketio/keystore.jks");
        config.setKeyStore(stream);

        return config;
    }
}
