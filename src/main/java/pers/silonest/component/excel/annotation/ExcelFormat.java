package pers.silonest.component.excel.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 格式化对象中的属性.常用于日期的格式化。
 * 
 * @author 陈晨
 * @time 2017年12月6日 上午9:48:26
 * @since v1.0.0
 * @version v0.0.1
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ExcelFormat {
  /**
   * 格式化参数.
   * 
   * @return 格式化参数
   */
  public String value() default "";
}
