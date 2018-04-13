package test.pers.silonest.component.http.rest.response;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pers.silonest.component.base.courier.Courier;
import pers.silonest.component.http.rest.response.RestResponse;

public class TestRestResponse {
  /**
   * 只有一个status属性时的测试.
   */
  @Test
  public void testResponseWithStatus() {
    Courier<String> courier = new Courier<String>();
    courier.setStatus(true);
    RestResponse response = RestResponse.builder().courier(courier).build();
    Assert.assertEquals(response.toString(), "{\"status\":\"SUCCESS\"}");
  }

  /**
   * 测试当不给status传值时，程序的容错.应默认为success处理。
   */
  @Test
  public void testResponseNoStatus() {
    Courier<String> courier1 = new Courier<String>();
    courier1.setCause("测试");
    RestResponse response1 = RestResponse.builder().courier(courier1).build();
    Assert.assertEquals(response1.toString(), "{\"status\":\"SUCCESS\",\"message\":{\"cause\":\"测试\"}}");
    Courier<String> courier2 = new Courier<String>();
    RestResponse response2 = RestResponse.builder().courier(courier2).build();
    Assert.assertEquals(response2.toString(), "{\"status\":\"SUCCESS\"}");
    Courier<String> courier3 = new Courier<String>();
    courier3.setCause("测试");
    courier3.setNotice("test");
    RestResponse response3 = RestResponse.builder().courier(courier3).build();
    Assert.assertEquals(response3.toString(), "{\"status\":\"SUCCESS\",\"message\":{\"notice\":\"test\",\"cause\":\"测试\"}}");
  }

  /**
   * 正常情况的测试.
   */
  @Test
  public void testResponseWithAllParameter() {
    Courier<String> courier = new Courier<String>();
    courier.setStatus(true);
    courier.setCode("123");
    courier.setCause("测试");
    courier.setNotice("test");
    courier.setContent("数据");
    RestResponse response = RestResponse.builder().courier(courier).build();
    Assert.assertEquals(response.toString(),
        "{\"status\":\"SUCCESS\",\"message\":{\"notice\":\"test\",\"cause\":\"测试\",\"code\":\"123\"},\"content\":\"数据\"}");
  }

  /**
   * 正常测试的数据.content内容是list。
   */
  @Test
  public void testResponseWithAllParameterAndListContent() {
    // 单个list的形式。
    List<String> content1 = new ArrayList<String>();
    content1.add("测试1");
    content1.add("测试2");
    Courier<List<String>> courier1 = new Courier<List<String>>();
    courier1.setStatus(true);
    courier1.setCode("123");
    courier1.setCause("测试");
    courier1.setNotice("test");
    courier1.setContent(content1);
    RestResponse response1 = RestResponse.builder().courier(courier1).build();
    Assert.assertEquals(response1.toString(),
        "{\"status\":\"SUCCESS\",\"message\":{\"notice\":\"test\",\"cause\":\"测试\",\"code\":\"123\"},\"content\":[\"测试1\",\"测试2\"]}");
  }

  @Test
  public void testResponseWithAllParameterAndMap() {
    Map<String, String> content1 = new HashMap<String, String>();
    content1.put("param1", "测试1");
    content1.put("param2", "测试2");
    Courier<Map<String, String>> courier1 = new Courier<Map<String, String>>();
    courier1.setStatus(true);
    courier1.setCode("123");
    courier1.setCause("测试");
    courier1.setNotice("test");
    courier1.setContent(content1);
    RestResponse response1 = RestResponse.builder().courier(courier1).build();
    Assert.assertEquals(response1.toString(),
        "{\"status\":\"SUCCESS\",\"message\":{\"notice\":\"test\",\"cause\":\"测试\",\"code\":\"123\"},\"content\":{\"param1\":\"测试1\",\"param2\":\"测试2\"}}");

    Map<String, Map<String, String>> content2 = new HashMap<String, Map<String, String>>();
    Map<String, String> map1 = new HashMap<String, String>();
    map1.put("object1-param2", "object1-测试2");
    map1.put("object1-param1", "object1-测试1");
    Map<String, String> map2 = new HashMap<String, String>();
    map2.put("object2-param2", "object2-测试2");
    map2.put("object2-param1", "object2-测试1");
    content2.put("object1", map1);
    content2.put("object2", map2);
    Courier<Map<String, Map<String, String>>> courier2 = new Courier<Map<String, Map<String, String>>>();
    courier2.setStatus(true);
    courier2.setCode("123");
    courier2.setCause("测试");
    courier2.setNotice("test");
    courier2.setContent(content2);
    RestResponse response2 = RestResponse.builder().courier(courier2).build();
    Assert.assertEquals(response2.toString(),
        "{\"status\":\"SUCCESS\",\"message\":{\"notice\":\"test\",\"cause\":\"测试\",\"code\":\"123\"},\"content\":{\"object1\":{\"object1-param2\":\"object1-测试2\",\"object1-param1\":\"object1-测试1\"},\"object2\":{\"object2-param2\":\"object2-测试2\",\"object2-param1\":\"object2-测试1\"}}}");
  }
}
