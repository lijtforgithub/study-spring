package com.ljt.study.socketio.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiJingTang
 * @date 2022-04-01 14:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    private String userName;
    private String message;

}
