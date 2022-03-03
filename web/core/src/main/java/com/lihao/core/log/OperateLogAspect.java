package com.lihao.core.log;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.lihao.core.listener.bean.OperateLogEventEvent;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Aspect
@Component
public class OperateLogAspect {

    @Autowired
    private ApplicationEventPublisher eventPublisher;


    /**
     * 请求跳过的日志
     */
    public static final List<String> requestSkipContentType = Lists.newArrayList("multipart/form-data");

    /**
     * 响应跳过的日志
     */
    public static final List<String> responseSkipContentType = Lists.newArrayList("application/octet-stream", "image");

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void apiOperate() {
    }


    @Around("apiOperate()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取目标方法的名称
        return saveLog(joinPoint);
    }

    /**
     * 保存日志
     *
     * @param joinPoint
     */
    @SneakyThrows
    private Object saveLog(ProceedingJoinPoint joinPoint) {
        OperateLog sysLog = new OperateLog();
        //获取请求url,ip,httpMethod
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String name = getUsername();
        sysLog.setUsername(name);
        String ip = request.getRemoteAddr();
        String httpMethod = request.getMethod();
        String url = request.getRequestURL().toString();
        sysLog.setOriginIp(ip);
        sysLog.setHttpMethod(httpMethod);
        sysLog.setRequestUrl(url);

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        sysLog.setOperateDatetime(SIMPLE_DATE_FORMAT.format(new Date()));
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        if (apiOperation != null) {
            //注解上的描述
            sysLog.setRemark(apiOperation.value());
        }
        //请求的 类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = className + "." + signature.getName();
        sysLog.setMethodName(methodName);
        //流数据不保存参数
        String contentType = request.getContentType();
        //请求参数过滤

        Map<String, Object> bodyParamMap = Maps.newLinkedHashMap();
        if (httpMethod.equalsIgnoreCase("get")) {
            bodyParamMap = getParamMap(request);
        } else {
            //post 以及其他
            //获取url的参数
            Map<String, Object> paramMap = getParamMap(request);
            bodyParamMap.putAll(paramMap);
            //获取body的参数
            if (contentType.contains("multipart")) {
                bodyParamMap = getMultipartBodyParamMap(request);
            } else if (contentType.contains("application/x-www-form-urlencoded")) {
                bodyParamMap = getParamMap(request);
            } else if (contentType.contains("application/json")) {
                //处理入参
                Object[] paramValues = joinPoint.getArgs();
                String[] paramNames = signature.getParameterNames();
                if (paramNames.length == 0) {
                    //do nothing
                } else {
                    for (int i = 0; i < paramNames.length; i++) {
                        //如果参数是http请求或者文件流则改参数不转json保存
                        if (paramValues[i] instanceof HttpServletResponse
                                || paramValues[i] instanceof MultipartFile
                                || paramValues[i] instanceof HttpServletRequest) {
                            continue;
                        }
                        bodyParamMap.put(paramNames[i], paramValues[i]);
                    }
                }
            }
        }

        sysLog.setRequestArgs(new Gson().toJson(bodyParamMap));
        Object proceed = joinPoint.proceed();
        //返回值插入
        //如果是minio返回的ResponseEntity流则不转json
        if (!(proceed instanceof ResponseEntity)) {
            sysLog.setResponseArgs(new Gson().toJson(proceed));
        }
        eventPublisher.publishEvent(new OperateLogEventEvent(sysLog));
        //增加事件驱动
        return proceed;
    }

    /**
     * 获取url/form参数
     *
     * @param request
     * @return
     */
    private Map<String, Object> getParamMap(HttpServletRequest request) {
        LinkedHashMap<String, Object> map = Maps.newLinkedHashMap();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String s = parameterNames.nextElement();
            String parameter = request.getParameter(s);
            map.put(s, parameter);
        }
        return map;
    }

    /**
     * 获取多表单参数
     *
     * @param request
     * @return
     */
    @SneakyThrows
    private Map<String, Object> getMultipartBodyParamMap(HttpServletRequest request) {
        Map<String, Object> stringMap = Maps.newHashMap();
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            String header = part.getHeader("content-disposition");
            if (StrUtil.isNotBlank(header) && header.contains("filename")) {
                //放行
                continue;
            } else {
                String name = part.getName();
                stringMap.put(name, IoUtil.read(part.getInputStream(), StandardCharsets.UTF_8));
            }
        }

        return stringMap;
    }


    public String getUsername() {
        // todo
        return "//todo";
    }
}
