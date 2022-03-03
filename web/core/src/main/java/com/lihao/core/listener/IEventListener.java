package com.yscredit.ys.indchain.listener;

import com.alibaba.fastjson.JSON;
import com.yscredit.ys.indchain.common.model.dto.OperateLog;
import com.yscredit.ys.indchain.listener.bean.OperateLogEventEvent;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ConnectException;

@Component
@Slf4j
public class IEventListener {

    @Qualifier("localRestHighLevelClient")
    @Autowired
    private RestHighLevelClient client;


    @EventListener
    public void onApplicationEvent(OperateLogEventEvent operateLogEventEvent) {
        OperateLog operateLog = operateLogEventEvent.getOperateLog();
        IndexRequest indexRequest = new IndexRequest("service-uer-log");
        String source = JSON.toJSONString(operateLog);
        indexRequest.source(source, XContentType.JSON);
        try {
            client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (ConnectException e) {
            log.error("es connection exception");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
