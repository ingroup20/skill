package com.t1.demo_error_message.reason_bundle;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Component
public class MessageDemo {

//    @Autowired
//    private MessageSource messageSource;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @PostConstruct
    public void printMessage() {
        try {
            String message = messageSource.getMessage("msg.text", null, Locale.US);
//            String message = messageSource.getMessage("msg.text", null, Locale.TRADITIONAL_CHINESE);
            System.out.println(message);
        } catch (NoSuchMessageException e) {
            System.err.println("No message found: " + e.getMessage());
        }
    }


    public void printFromConfigXml() {

        ApplicationContext context = new ClassPathXmlApplicationContext("message_source.xml");

        // 從 MessageSource 獲取國際化消息
        String messageUS = context.getMessage("msg.text", null, Locale.US);
        String messageTW = context.getMessage("msg.text", null, Locale.TAIWAN);

        System.out.println("Message (US): " + messageUS);
        System.out.println("Message (TW): " + messageTW);
    }

    public List<String> validDataWarning(Map<String,String> datas, Locale locale, ReloadableResourceBundleMessageSource messageSource) {
        Set<String> keySet = datas.keySet();
        List<String> warnList = new ArrayList<>();

        for(String key :keySet){
            warnList.add(
                    messagesList(datas.get(key), locale)
            );
        }

        return warnList;
    }


    public String messagesList(String keyWorld, Locale locale){
        String feedback = "";
        String msg00 = messageSource.getMessage("msg.warn.msg00", null, "預設訊息", locale);
        String msg01 = messageSource.getMessage("msg.warn.msg01", null, "結束", locale);
        String msg02 = messageSource.getMessage("msg.warn.msg02", null, "--細項x", locale);
        String msg03 = messageSource.getMessage("msg.warn.msg03", null, "--項項o", locale);

        switch (keyWorld){
            case "1":
                feedback = msg00+keyWorld+msg01+msg02;
                break;
            case "2":
                feedback =  msg00+keyWorld+msg01+msg03;
                break;
        }


        return feedback;

    }



}
