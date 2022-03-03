package com.lihao.core.listener;

import com.lihao.core.listener.bean.OperateLogEventEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IEventListener {


    @EventListener
    public void onApplicationEvent(OperateLogEventEvent operateLogEventEvent) {

    }
}
