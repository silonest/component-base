package pers.silonest.component.excel.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel属性的伪装.如果需要将属性的额值伪装为其他含义，比如0是失败，1是成功，则需要在属性中增加此注解。这个注解可以通知处理器调用某个mask方法，来给属性做伪装。
 * 
 * @author 陈晨
 * @time 2017年12月6日 上午9:49:43
 * @since v1.0.0
 * @version v0.0.1
 */
@Target({java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ExcelDisguiser {
  /**
   * 字段的处理类.
   * 
   * @return 类的声明
   */
  public Class<?> using() default Void.class;
}
