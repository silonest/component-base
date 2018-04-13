package pers.silonest.component.excel.bind;

/**
 * 伪装类的接口.如果用户需要对某些属性做伪装，那么需要给处理器提供伪装方法。
 * 
 * @author 陈晨
 * @time 2017年12月6日 上午9:56:50
 * @since v1.0.0
 * @version v0.0.1
 * @param <T>
 */
public interface Disguised<T> {
  /**
   * 属性值通过该方法将内容转换成客户需要的样子.比如0 >> 失败，1 >> 成功。
   * 
   * @param param
   * @return
   */
  public T execute(Object param);
}
