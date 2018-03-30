package pers.silonest.component.base.courier;

public class Courier {

  private boolean status;
  private String code;
  private String cause;
  private String notice;
  private Object content;
  private String contentName;

  public boolean getStatus() {
    return status;
  }

  public Courier status(boolean status) {
    this.status = status;
    return this;
  }

  public String getCode() {
    return code;
  }

  public Courier code(String code) {
    this.code = code;
    return this;
  }

  public String getCause() {
    return cause;
  }

  public Courier setCause(String cause) {
    this.cause = cause;
    return this;
  }

  public String getNotice() {
    return notice;
  }

  public Courier notice(String notice) {
    this.notice = notice;
    return this;
  }

  public Object getContent() {
    return content;
  }

  public Courier content(Object content) {
    this.content = content;
    return this;
  }

  public String getContentName() {
    return contentName;
  }

  public Courier contentName(String contentName) {
    this.contentName = contentName;
    return this;
  }
}
