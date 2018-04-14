package test.pers.silonest.component.http.rest.response;

import org.testng.Assert;
import org.testng.annotations.Test;

import pers.silonest.component.base.courier.Courier;
import pers.silonest.component.base.courier.CourierFactory;
import pers.silonest.component.util.JsonUtils;

public class TestCourierFactory {

  @Test
  public void testCreateWithAllParameter() {
    Courier<String> courier = CourierFactory.create(true, "test-code", "test-cause", "test-notice", "test-content");
    String json = JsonUtils.toJson(courier);
    Assert.assertEquals(json,
        "{\"status\":true,\"code\":\"test-code\",\"cause\":\"test-cause\",\"notice\":\"test-notice\",\"content\":\"test-content\"}");
  }

  @Test
  public void testCreateWithStatusCauseNoticeContent() {
    Courier<String> courier = CourierFactory.create(true, "test-cause", "test-notice", "test-content");
    String json = JsonUtils.toJson(courier);
    Assert.assertEquals(json, "{\"status\":true,\"cause\":\"test-cause\",\"notice\":\"test-notice\",\"content\":\"test-content\"}");
  }

  @Test
  public void testCreateWithStatusContent() {
    Courier<String> courier = CourierFactory.create(true, "test-content");
    String json = JsonUtils.toJson(courier);
    Assert.assertEquals(json, "{\"status\":true,\"content\":\"test-content\"}");
  }

  @Test
  public void testCreateWithStatusCauseNotice() {
    Courier<String> courier = CourierFactory.create(true, "test-cause", "test-notice");
    String json = JsonUtils.toJson(courier);
    Assert.assertEquals(json, "{\"status\":true,\"cause\":\"test-cause\",\"notice\":\"test-notice\"}");
  }

  @Test
  public void testCreateWithStatus() {
    Courier<String> courier = CourierFactory.create(true);
    String json = JsonUtils.toJson(courier);
    Assert.assertEquals(json, "{\"status\":true}");
  }

}
