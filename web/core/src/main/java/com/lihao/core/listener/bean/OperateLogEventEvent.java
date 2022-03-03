package com.yscredit.ys.indchain.listener.bean;

import com.yscredit.ys.indchain.common.model.dto.OperateLog;
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
