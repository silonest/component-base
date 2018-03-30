package pers.silonest.component.base.courier;

/**
 * 分页参数.用于接收分页查询时传入的参数。
 *
 * @author silonest
 * @version v0.0.1
 * @email silonest@icloud.com
 * @time 2016年7月22日 下午1:58:09
 * @since v1.0.0
 */
public class Page {

  /*
   * 默认开始页。
   */
  private static final int PAGE_NUMBER = 1;
  /*
   * 默认每页的记录数。
   */
  private static final int PAGE_SIZE = 20;
  /*
   * 当前页.
   */
  private Integer pageNumber;
  /*
   * 当前页的记录数
   */
  private Integer pageSize;

  /**
   * 无参构造方法.
   */
  public Page() {}

  /**
   * 带参数的构造方法.
   *
   * @param pageNumber 当前页
   * @param pageSize 当前页的记录数
   */
  public Page(Integer pageNumber, Integer pageSize) {
    this.setPageNumber(pageNumber);
    this.setPageSize(pageSize);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Page [");
    sb.append("pageNumber").append(this.pageNumber).append(",");
    sb.append("pageSize").append(this.pageSize);
    sb.append("]");
    return super.toString();
  }

  /**
   * 获取页码.如果对象内的页码为空，则返回默认值1。
   *
   * @return 页码
   */
  public Integer getPageNumber() {
    if (pageNumber == null) {
      return PAGE_NUMBER;
    } else if (pageNumber.intValue() == 0) {
      return PAGE_NUMBER;
    } else {
      return pageNumber;
    }
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  /**
   * 获取页面容量.如果对象内的容量大小为空，则返回默认值20。
   *
   * @return 页面容量
   */
  public Integer getPageSize() {
    if (pageSize == null) {
      return PAGE_SIZE;
    } else {
      return pageSize;
    }
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  /**
   * 获取开始行数.可以判断从第几条记录开始分页。
   *
   * @return 分页开始位置
   */
  public Long getStartRow() {
    int pageNumber = getPageNumber();
    int pageSize = getPageSize();
    return ((long) pageNumber - (long) 1) * (long) pageSize;
  }

}
