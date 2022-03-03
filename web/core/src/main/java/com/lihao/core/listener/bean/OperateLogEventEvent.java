package com.lihao.core.listener.bean;

import com.lihao.core.log.OperateLog;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

@Slf4j
@Getter
@Setter
public class OperateLogEventEvent extends ApplicationEvent {

    private OperateLog operateLog;

    public OperateLogEventEvent(OperateLog operateLog) {
        super(operateLog);
        this.operateLog = operateLog;
    }


}
