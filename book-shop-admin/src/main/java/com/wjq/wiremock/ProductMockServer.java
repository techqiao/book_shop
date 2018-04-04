package com.wjq.wiremock;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;

/**
 * <p>Description : shuyang_south_west_street
 * <p>Date : 2018/1/26 9:30
 * <p>@author : wjq
 */
public class ProductMockServer {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        configureFor("127.0.0.1",8080);//模拟数据的映射地址
        removeAllMappings();//移除之前的映射
        //发送请求
        Factory.getRequest("/product/findDetail", "product/product-query");
        Factory.deleteRequest("/product/updateState","product/product-delete");
//        mock("/product/updateState", HttpMethod.DELETE, ModuleName.product.name(), "product-delete");
//        mock("/product/AddProduct", HttpMethod.POST, ModuleName.product.name(), "product-insert");
//        mock("/product/AddProduct", HttpMethod.PATCH, ModuleName.product.name(), "product-update");
    }

}
