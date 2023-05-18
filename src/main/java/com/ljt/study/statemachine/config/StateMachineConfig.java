package com.ljt.study.statemachine.config;

import com.ljt.study.statemachine.enums.EventEnum;
import com.ljt.study.statemachine.enums.StateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

/**
 * @author LiJingTang
 * @date 2023-05-16 09:07
 */
@Slf4j
@Configuration
@EnableStateMachine
class StateMachineConfig extends EnumStateMachineConfigurerAdapter<StateEnum, EventEnum> {


    /**
     * 自动启动 添加监听器
     */
    @Override
    public void configure(StateMachineConfigurationConfigurer<StateEnum, EventEnum> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

    /**
     * 状态初始化
     */
    @Override
    public void configure(StateMachineStateConfigurer<StateEnum, EventEnum> states) throws Exception {
        states.withStates()
                .initial(StateEnum.SI)
                .states(EnumSet.allOf(StateEnum.class));
    }

    /**
     * 状态迁移
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<StateEnum, EventEnum> transitions) throws Exception {
        transitions.withExternal()
                .source(StateEnum.SI).target(StateEnum.S1).event(EventEnum.E1)
                .and()
                .withExternal()
                .source(StateEnum.S1).target(StateEnum.S2).event(EventEnum.E2).action(action());
    }

    @Bean
    public StateMachineListener<StateEnum, EventEnum> listener() {
        return new StateMachineListenerAdapter<StateEnum, EventEnum>() {
            @Override
            public void stateChanged(State<StateEnum, EventEnum> from, State<StateEnum, EventEnum> to) {
                log.info("全局监听器 State change to {}", to.getId());
            }
        };
    }

    @Bean
    public Action<StateEnum, EventEnum> action() {
        return context -> {
            EventEnum event = context.getEvent();
            State<StateEnum, EventEnum> source = context.getSource();
            State<StateEnum, EventEnum> target = context.getTarget();
            log.info("动作 event={} source={} target={}", event, source.getId(), target.getId());
            log.info("头信息 {}", context.getMessageHeader("xx"));
        };
    }

}
