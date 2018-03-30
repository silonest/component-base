package pers.silonest.component.http.rest.response.test;

import org.junit.Assert;
import org.junit.Test;

import pers.silonest.component.base.courier.Courier;
import pers.silonest.component.http.rest.response.RestResponse;

public class RestResponseTester {
  @Test
  public void testResponse() {
    Courier<String> courier = new Courier<String>().status(true).cause("测试").notice("test").content("数据");
    RestResponse response = RestResponse.builder().courier(courier).build();
    Assert.assertEquals("{\"status\":\"SUCCESS\",\"message\":{\"notice\":\"test\",\"cause\":\"测试\"},\"content\":\"数据\"}", response.toString());
  }
}
