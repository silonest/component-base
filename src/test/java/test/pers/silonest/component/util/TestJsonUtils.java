package test.pers.silonest.component.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import pers.silonest.component.util.JsonUtils;

public class TestJsonUtils {

  /**
   * 测试当json字符串中的值为空字符串时，是否可以将其赋值给对象中的属性。
   */
  @Test
  public void testEmptyString() {
    String jsonStr = "{\"column1\":\"1\",\"column2\":\"\"}";
    TestPojo result = JsonUtils.toObject(jsonStr, TestPojo.class);
    Assert.assertEquals(result.getColumn1(), "1");
    Assert.assertEquals(result.getColumn2(), "");
  }

  /**
   * 测试当json字符串中存在特殊字符的转义时，是否可以正确解析。
   */
  @Test
  public void testBackslashEscaping() {
    String jsonStr = "{\"column1\":\"1\",\"column2\":\"\\\"\"}";
    TestPojo result = JsonUtils.toObject(jsonStr, TestPojo.class);
    Assert.assertEquals(result.getColumn1(), "1");
    Assert.assertEquals(result.getColumn2(), "\"");
  }

  /**
   * 测试当json字符串中存在java注释时，是否可以正确解析。
   */
  @Test(expectedExceptions = RuntimeException.class)
  public void testComments() {
    String jsonStr1 = "{\"column1\":\"1\"/* comment */,\"column2\":\"2\"}";
    TestPojo result1 = JsonUtils.toObject(jsonStr1, TestPojo.class);
    Assert.assertEquals(result1.getColumn1(), "1");
    Assert.assertEquals(result1.getColumn2(), "2");
    String jsonStr2 = "{\"column1\":\"1\"// 测试,\"column2\":\"2\"}";
    JsonUtils.toObject(jsonStr2, TestPojo.class);
  }

}
