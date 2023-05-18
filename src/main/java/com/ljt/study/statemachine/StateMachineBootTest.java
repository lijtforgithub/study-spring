package com.ljt.study.statemachine;

import com.ljt.study.statemachine.enums.EventEnum;
import com.ljt.study.statemachine.enums.StateEnum;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import reactor.core.publisher.Mono;

/**
 * @author LiJingTang
 * @date 2023-05-16 09:28
 */
@SpringBootTest
class StateMachineBootTest {

    @Autowired
    private StateMachine<StateEnum, EventEnum> stateMachine;

    @SneakyThrows
    @Test
    void test() {
        stateMachine.sendEvent(EventEnum.E1);

        Message<EventEnum> message = MessageBuilder.withPayload(EventEnum.E2).setHeader("xx", "oo").build();
        stateMachine.sendEvent(Mono.just(message)).subscribe();
    }

}
