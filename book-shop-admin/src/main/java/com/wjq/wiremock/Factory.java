package com.wjq.wiremock;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * <p>Description : 工厂类
 * <p>Date : 2018/1/26 10:32
 * <p>@author : wjq
 */
public class Factory {
    //将配置文件json数据转换成string类型
    public static String getContent(String moduleFile) throws IOException {
        //读取配置文件
        ClassPathResource resource = new ClassPathResource("mock/response/" + moduleFile + ".json");
        return StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
    }

    public static void getRequest(String url, String moduleFile) throws IOException {
        stubFor(post(urlPathEqualTo(url))
                .willReturn(aResponse()
                .withBody(getContent(moduleFile))
                .withHeader("Content-Type", "application/json")
                .withStatus(200)));
    }
    public static void deleteRequest(String url, String moduleFile) throws IOException {
        stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(getContent(moduleFile)).withHeader("Content-Type", "application/json").withStatus(200)));
    }
    public static void putRequest(String url,String moduleFile) throws IOException {
        stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(getContent(moduleFile)).withHeader("Content-Type", "application/json").withStatus(200)));
    }
    public static void postRequest(String url,String moduleFile) throws IOException {
        stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(getContent(moduleFile)).withHeader("Content-Type", "application/json").withStatus(200)));
    }

}
