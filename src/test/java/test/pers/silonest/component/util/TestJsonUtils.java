package test.pers.silonest.component.util;

import com.fasterxml.jackson.databind.JsonNode;

import org.testng.Assert;
import org.testng.annotations.Test;

import pers.silonest.component.base.exception.JsonParserException;
import pers.silonest.component.util.JsonUtils;

public class TestJsonUtils {

  /**
   * 测试当json字符串中的值为空字符串时，是否可以将其赋值给对象中的属性。
   */
  @Test(description = "JsonUtils.toObject空值测试。")
  public void testEmptyString() {
    String jsonStr = "{\"column1\":\"1\",\"column2\":\"\"}";
    TestPojo result = JsonUtils.toObject(jsonStr, TestPojo.class);
    Assert.assertEquals(result.getColumn1(), "1");
    Assert.assertEquals(result.getColumn2(), "");
  }

  /**
   * 测试当json字符串中存在特殊字符的转义时，是否可以正确解析。
   */
  @Test(description = "JsonUtils.toObject特殊字符测试。")
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

  class User {
    private int userId;
    private String userName;
    private String userAge;

    public int getUserId() {
      return userId;
    }

    public void setUserId(int userId) {
      this.userId = userId;
    }

    public String getUserName() {
      return userName;
    }

    public void setUserName(String userName) {
      this.userName = userName;
    }

    public String getUserAge() {
      return userAge;
    }

    public void setUserAge(String userAge) {
      this.userAge = userAge;
    }
  }

  @Test
  public void testObjectToJsonNode() {
    User user = new User();
    user.setUserId(1);
    user.setUserName("测试");
    user.setUserAge("26");
    JsonNode result = JsonUtils.toJsonNode(user);
    Assert.assertEquals(result.get("userId").asInt(), 1);
    Assert.assertEquals(result.get("userName").asText(), "测试");
    Assert.assertEquals(result.get("userAge").asText(), "26");
  }

  @Test
  public void testJsonStrToJsonNode() {
    String data =
        "{\"rating\":{\"max\":10,\"numRaters\":371,\"average\":\"7.3\",\"min\":0},\"subtitle\":\"\",\"author\":[\"[日]片山恭一\"],\"pubdate\":\"2005-1\",\"tags\":[{\"count\":144,\"name\":\"片山恭一\",\"title\":\"片山恭一\"},{\"count\":70,\"name\":\"日本\",\"title\":\"日本\"},{\"count\":65,\"name\":\"日本文学\",\"title\":\"日本文学\"},{\"count\":40,\"name\":\"小说\",\"title\":\"小说\"},{\"count\":33,\"name\":\"满月之夜白鲸现\",\"title\":\"满月之夜白鲸现\"},{\"count\":17,\"name\":\"爱情\",\"title\":\"爱情\"},{\"count\":10,\"name\":\"純愛\",\"title\":\"純愛\"},{\"count\":9,\"name\":\"外国文学\",\"title\":\"外国文学\"}],\"origin_title\":\"\",\"image\":\"https://img3.doubanio.com\\/view\\/subject\\/m\\/public\\/s1747553.jpg\",\"binding\":\"平装\",\"translator\":[\"豫人\"],\"catalog\":\"\\n\",\"pages\":\"180\",\"images\":{\"small\":\"https://img3.doubanio.com\\/view\\/subject\\/s\\/public\\/s1747553.jpg\",\"large\":\"https://img3.doubanio.com\\/view\\/subject\\/l\\/public\\/s1747553.jpg\",\"medium\":\"https://img3.doubanio.com\\/view\\/subject\\/m\\/public\\/s1747553.jpg\"},\"alt\":\"https:\\/\\/book.douban.com\\/subject\\/1220562\\/\",\"id\":\"1220562\",\"publisher\":\"青岛出版社\",\"isbn10\":\"7543632608\",\"isbn13\":\"9787543632608\",\"title\":\"满月之夜白鲸现\",\"url\":\"https:\\/\\/api.douban.com\\/v2\\/book\\/1220562\",\"alt_title\":\"\",\"author_intro\":\"\",\"summary\":\"那一年，是听莫扎特、钓鲈鱼和家庭破裂的一年。说到家庭破裂，母亲怪自己当初没有找到好男人，父亲则认为当时是被狐狸精迷住了眼，失常的是母亲，但出问题的是父亲……。\",\"price\":\"15.00元\"}";
    JsonNode result = JsonUtils.toJsonNode(data);
    Assert.assertEquals(result.get("rating").get("max").asInt(), 10);
  }

  @Test(description = "JsonUtils.toJsonNode传入错误json格式的字符串，抛出异常。", expectedExceptions = JsonParserException.class)
  public void testJsonStrToJsonNodeThrowException() {
    String wrongData =
        "rating\":{\"max\":10,\"numRaters\":371,\"average\":\"7.3\",\"min\":0},\"subtitle\":\"\",\"author\":[\"[日]片山恭一\"],\"pubdate\":\"2005-1\",\"tags\":[{\"count\":144,\"name\":\"片山恭一\",\"title\":\"片山恭一\"},{\"count\":70,\"name\":\"日本\",\"title\":\"日本\"},{\"count\":65,\"name\":\"日本文学\",\"title\":\"日本文学\"},{\"count\":40,\"name\":\"小说\",\"title\":\"小说\"},{\"count\":33,\"name\":\"满月之夜白鲸现\",\"title\":\"满月之夜白鲸现\"},{\"count\":17,\"name\":\"爱情\",\"title\":\"爱情\"},{\"count\":10,\"name\":\"純愛\",\"title\":\"純愛\"},{\"count\":9,\"name\":\"外国文学\",\"title\":\"外国文学\"}],\"origin_title\":\"\",\"image\":\"https://img3.doubanio.com\\/view\\/subject\\/m\\/public\\/s1747553.jpg\",\"binding\":\"平装\",\"translator\":[\"豫人\"],\"catalog\":\"\\n\",\"pages\":\"180\",\"images\":{\"small\":\"https://img3.doubanio.com\\/view\\/subject\\/s\\/public\\/s1747553.jpg\",\"large\":\"https://img3.doubanio.com\\/view\\/subject\\/l\\/public\\/s1747553.jpg\",\"medium\":\"https://img3.doubanio.com\\/view\\/subject\\/m\\/public\\/s1747553.jpg\"},\"alt\":\"https:\\/\\/book.douban.com\\/subject\\/1220562\\/\",\"id\":\"1220562\",\"publisher\":\"青岛出版社\",\"isbn10\":\"7543632608\",\"isbn13\":\"9787543632608\",\"title\":\"满月之夜白鲸现\",\"url\":\"https:\\/\\/api.douban.com\\/v2\\/book\\/1220562\",\"alt_title\":\"\",\"author_intro\":\"\",\"summary\":\"那一年，是听莫扎特、钓鲈鱼和家庭破裂的一年。说到家庭破裂，母亲怪自己当初没有找到好男人，父亲则认为当时是被狐狸精迷住了眼，失常的是母亲，但出问题的是父亲……。\",\"price\":\"15.00元\"}";
    JsonUtils.toJsonNode(wrongData);
  }

  @Test(description = "JsonUtils.getJson正常用例。")
  public void testGetJson() {
    String data =
        "{\"path\":\"D:\\smartwater-dp-1.0.0\\sites\\analyser\",\"jarName\":\"analyser-1.0.0.jar\",\"className\":\"com.das.collector.core.AppMain\",\"resources\":{\"mq\":{\"password\":\"huizhong\",\"port\":\"5672\",\"dataQueueName\":\"mod\",\"host\":\"172.16.0.19\",\"modSize\":\"5\",\"virtualHost\":\"/\",\"userName\":\"root\"}}}";
    Assert.assertEquals(JsonUtils.getJson(data, "resources.mq"),
        "{\"password\":\"huizhong\",\"port\":\"5672\",\"dataQueueName\":\"mod\",\"host\":\"172.16.0.19\",\"modSize\":\"5\",\"virtualHost\":\"/\",\"userName\":\"root\"}");
  }
}
