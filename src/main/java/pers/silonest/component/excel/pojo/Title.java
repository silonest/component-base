package pers.silonest.component.excel.pojo;

/**
 * excel操作类的表头模型类.该类是对excel表头的抽象，title为表头内容，order为表头顺序
 * 
 * @author 陈晨
 * @time 2014年12月12日
 * @version v1.0.0
 * @since v1.0.0
 */
public class Title {
  private String title;
  private String order;

  public Title() {}

  public Title(String order, String title) {
    this.order = order;
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

}
