package com.t1.demo_error_message;

import com.t1.demo_error_message.reason_bundle.AppConfig;
import com.t1.demo_error_message.reason_bundle.MessageDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
public class DemoErrorMessageApplication {


    public static void main(String[] args) {

//        SpringApplication.run(DemoErrorMessageApplication.class, args);
        ApplicationContext context = SpringApplication.run(DemoErrorMessageApplication.class, args);

        MessageDemo messageDemo = context.getBean(MessageDemo.class);//main方法會繞過spring的生命週期，所以要手動獲取bean，不能單純new bean
        AppConfig appConfig = context.getBean(AppConfig.class);
        messageDemo.printFromConfigXml();
        Map<String,String> datas = new HashMap<>();
        datas.put("1","1");
        datas.put("2","2");
        List<String> warnList =  messageDemo.validDataWarning(datas, Locale.TAIWAN, appConfig.reloadableResourceBundleMessageSource());
        warnList.forEach(value -> System.out.println(value));

    }


}
