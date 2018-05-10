package test.pers.silonest.component.http;

import org.testng.annotations.Test;

import pers.silonest.component.http.HttpRequest;
import pers.silonest.component.http.WebResponse;

public class TestHttpRequest {
  // private static final String TOKEN = "c8cceb3bd1392d469840c2ce20650626550ccde7";
  // private static final String GET_GITHUB_USER_PROFILE_URL =
  // "https://api.github.com/user?access_token=";
  private static final String GET_DOUBAN_BOOK_PROFILE_URL = "https://api.douban.com/v2/book/1220562";

  @Test
  public void testDoGetWithAllParameters() {
    HttpRequest httpRequest = new HttpRequest();
    WebResponse<String> response = httpRequest.doGet(GET_DOUBAN_BOOK_PROFILE_URL, null);

    System.out.println(response.getContent());
  }
}
