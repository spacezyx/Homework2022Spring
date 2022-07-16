package com.example.dotdot.controller;

import com.example.dotdot.constant.Constant;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PaiController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 生成post请求的JSON请求参数
     *
     * @param params 需要发送的json数据
     * @return
     */
    protected HttpEntity<Map> generatePostJson(Map<String, Object> params) {
        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType type = MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.setContentType(type);
        HttpEntity<Map> httpEntity = new HttpEntity<>(params, httpHeaders);
        System.out.println(httpEntity);
        return httpEntity;
    }

    @RequestMapping("/switchLight")
    public void switchLight(@RequestBody Map<String, String> input){
        System.out.println("switchLight");
        JSONObject jsonObject = JSONObject.fromObject(input);
        String value = jsonObject.getString("value");
        Map<String, Object> params = new HashMap<>();
        String url = "http://124.70.197.186:8888/mqtt_connection/android";
        params.put("device_id","sDVhaDtB");
        params.put("command_name","Agriculture_Control_Light");
        params.put("action", value);
        System.out.println(params);
        ResponseEntity<String> myResponseEntity = restTemplate.postForEntity
                (
                        url,
                        generatePostJson(params),
                        String.class
                );
        System.out.println(myResponseEntity);
    }
    @RequestMapping("/switchFan")
    public void switchFan(@RequestBody Map<String, String> input){
        JSONObject jsonObject = JSONObject.fromObject(input);
        String value = jsonObject.getString("value");
        System.out.println("switchFan");
        //转发请求到华为云
        Map<String, Object> params = new HashMap<>();
        Map<String,String> tmp = new HashMap<>();
        tmp.put("motor", value);
        // String url = "https://iotda.cn-north-4.myhuaweicloud.com/v5/iot/0cec553e2700f44a2f03c00fa22dcaaf/devices/626e43a42d5e854503d2a088_senser/commands";
        String url = "http://124.70.197.186:8888/mqtt_connection/android";
        params.put("device_id","sDVhaDtB");
        params.put("command_name","Agriculture_Control_Motor");
        params.put("action", value);
//        params.put("paras",tmp);
        System.out.println(params);
        ResponseEntity<String> myResponseEntity = restTemplate.postForEntity
                (
                        url,
                        generatePostJson(params),
                        String.class
                );
        System.out.println(myResponseEntity);
    }
    @RequestMapping("/smartLight")
    public void smartLight(@RequestBody Map<String, String> input){
        JSONObject jsonObject = JSONObject.fromObject(input);
        String value = jsonObject.getString("value");
        System.out.println("smartLight");
        //转发请求到华为云
        Map<String, Object> params = new HashMap<>();
        Map<String,String> tmp = new HashMap<>();
//        tmp.put("lightauto", value);
        // String url = "https://iotda.cn-north-4.myhuaweicloud.com/v5/iot/0cec553e2700f44a2f03c00fa22dcaaf/devices/626e43a42d5e854503d2a088_senser/commands";
        String url = "http://124.70.197.186:8888/mqtt_connection/android";
        params.put("device_id","sDVhaDtB");
        params.put("command_name","Agriculture_Control_Light_Auto");
        params.put("action", value);
//        params.put("paras",tmp);
        System.out.println(params);
        ResponseEntity<String> myResponseEntity = restTemplate.postForEntity
                (
                        url,
                        generatePostJson(params),
                        String.class
                );
        System.out.println(myResponseEntity);
    }
    @RequestMapping("/smartFan")
    public void smartFan(@RequestBody Map<String, String> input){
        JSONObject jsonObject = JSONObject.fromObject(input);
        String value = jsonObject.getString("value");
        System.out.println("smartFan");
        //转发请求到华为云
        Map<String, Object> params = new HashMap<>();
        Map<String,String> tmp = new HashMap<>();
        tmp.put("motorauto", value);
        String url = "http://124.70.197.186:8888/mqtt_connection/android";
        params.put("device_id","sDVhaDtB");
        params.put("command_name","Agriculture_Control_Motor_Auto");
        params.put("action", value);
//        params.put("paras",tmp);
        System.out.println(params);
        ResponseEntity<String> myResponseEntity = restTemplate.postForEntity
                (
                        url,
                        generatePostJson(params),
                        String.class
                );
        System.out.println(myResponseEntity);
    }
}
