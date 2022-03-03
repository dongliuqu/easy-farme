package com.lihao.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * rabbit 配置
 *
 * @author lch
 */
@Configuration
public class RabbitConfig {
    /**
     * 定义队列名
     */
    public static final String TODO_QUEUE_NAME = "todo_queue";
    public static final String TODO_EXCHANGE = "todoExchange";


    /**
     * 创建队列
     */
    @Bean
    public Queue createTodoQueue() {
        return new Queue(TODO_QUEUE_NAME);
    }

    /**
     * 创建交换机
     */
    @Bean
    public FanoutExchange defTodoExchange() {
        return new FanoutExchange(TODO_EXCHANGE);
    }

    /**
     * 队列与交换机绑定
     */
    @Bean
    Binding bindingTodo() {
        return BindingBuilder.bind(createTodoQueue()).to(defTodoExchange());
    }
}
