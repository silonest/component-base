package pers.silonest.component.util;

import java.util.UUID;

/**
 * 用来生成UUID的工具类.
 * 
 * @author 陈晨
 * @time 2018年6月1日 上午10:26:41
 * @since v1.0.0
 * @version v0.0.1
 */
public abstract class UuidUtils {
  /**
   * 获取不带“-”的16位UUID。使用java自带的UUID。randomUUID()对象。
   * 
   * @return 生成的UUID。
   */
  public static String getUuid() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }
}
