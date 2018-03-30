package pers.silonest.component.base.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.DataBindingException;

/**
 * &#x65e5;&#x671f;&#x7684;&#x64cd;&#x4f5c;&#x7c7b;.&#x4f7f;&#x7528;&#x8be5;&#x7c7b;&#x53ef;&#x4ee5;&#x5b8c;&#x6210;&#x5e38;&#x7528;&#x7684;&#x65e5;&#x671f;&#x64cd;&#x4f5c;&#x3002;
 *
 * @author silonest
 * @since v1.0.0
 * @version v0.0.1
 */
public class DateUtils {

  /**
   * 向前计算日期.
   *
   * @param date 日期
   * @param interval 向前的天数
   * @return 计算后的日期
   */
  public static Date before(Date date, int interval) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int day = calendar.get(Calendar.DATE);
    calendar.set(Calendar.DATE, day - interval);
    return calendar.getTime();
  }

  /**
   * 向后计算日期.
   *
   * @param date 日期
   * @param interval 向后的天数
   * @return 计算后的日期
   */
  public static Date after(Date date, int interval) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int day = calendar.get(Calendar.DATE);
    calendar.set(Calendar.DATE, day + interval);
    return calendar.getTime();
  }

  /**
   * 向前计算日期.
   *
   * @param date 日期
   * @param interval 向前的天数
   * @return 计算后的日期
   */
  public static String before(String date, int interval) {
    try {
      Date dateResult = before(new SimpleDateFormat(DATE_FORMAT).parse(date), interval);
      String dayBefore = new SimpleDateFormat(DATE_FORMAT).format(dateResult);
      return dayBefore;
    } catch (ParseException ex) {
      throw new DataBindingException(ex);
    }
  }

  /**
   * 计算两个日期相差的天数.
   *
   * @param preDate 更早的日期
   * @param nextDate 更晚的日期
   * @return 相差的天数
   */
  public static int interval(Date preDate, Date nextDate) {
    int day = (int) ((nextDate.getTime() - preDate.getTime()) / (24 * 60 * 60 * 1000));
    return day;
  }

  /**
   * 计算两个日期相差的天数.在算日期的差时，需要对传入的日期格式化。
   *
   * @param preDate 更早的日期
   * @param nextDate 更晚的日期
   * @param forMat 格式化方式
   * @return 相差的天数
   */
  public static int interval(String preDate, String nextDate, String forMat) {
    SimpleDateFormat formatter = new SimpleDateFormat(forMat);
    try {
      return interval(formatter.parse(preDate), formatter.parse(nextDate));
    } catch (ParseException ex) {
      throw new DataBindingException(ex);
    }
  }

  /**
   * 计算两个日期相差的天数.在算日期的差时，使用默认“yyyy-MM-dd”的形式格式化日期。
   *
   * @param preDate 更早的日期
   * @param nextDate 更晚的日期
   * @return 相差的天数
   */
  public static int interval(String preDate, String nextDate) {
    SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
    try {
      return interval(formatter.parse(preDate), formatter.parse(nextDate));
    } catch (ParseException ex) {
      throw new DataBindingException(ex);
    }
  }

  /**
   * 计算两个日期相差的天数.在计算日期的差时，两个日期分别使用不同的格式化方法。
   *
   * @param preDate 更早的日期
   * @param preForMat 更早的日期的格式化方式
   * @param nextDate 更晚的日期
   * @param nextForMat 更晚日期的格式化方式
   * @return 相差天数
   */
  public static int interval(String preDate, String preForMat, String nextDate, String nextForMat) {
    SimpleDateFormat previousFormatter = new SimpleDateFormat(preForMat);
    SimpleDateFormat nextFormatter = new SimpleDateFormat(nextForMat);
    try {
      return interval(previousFormatter.parse(preDate), nextFormatter.parse(nextDate));
    } catch (ParseException ex) {
      throw new DataBindingException(ex);
    }
  }

  /**
   * 计算两个日期相差的月数.可以跨年使用，如2016-02-15和2015-01-15相差为13个月。
   *
   * @param preDate 更早的日期
   * @param nextDate 更晚的日期
   * @return 相差的月数
   */
  public static int intervalMonth(Date preDate, Date nextDate) {
    Calendar pre = Calendar.getInstance();
    Calendar next = Calendar.getInstance();
    pre.setTime(preDate);
    next.setTime(nextDate);
    int preYear = pre.get(Calendar.YEAR);
    int preMonth = pre.get(Calendar.MONTH);
    int nextYear = next.get(Calendar.YEAR);
    int nextMonth = next.get(Calendar.MONTH);
    int intervalYear = nextYear - preYear;
    int intervalMonth = nextMonth - preMonth;
    int result = intervalYear * 12 + intervalMonth;
    return result;
  }

  /**
   * 传入String获取Date对象的方法.使用默认的“yyyy-mm-dd”的形式格式化字符串。
   *
   * @param date 日期字符串
   * @return 生成的Date对象
   */
  public static Date getDate(String date) {
    return getDate(date, DATE_FORMAT);
  }

  /**
   * 传入String获取Date对象的方法.
   *
   * @param date 日期字符串
   * @param format 格式化字符串
   * @return 生成的Date对象
   */
  public static Date getDate(String date, String format) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    try {
      Date result = dateFormat.parse(date);
      return result;
    } catch (ParseException ex) {
      throw new DataBindingException(ex);
    }
  }

  /**
   * 传入Date获取String的方法.
   *
   * @param date 日期
   * @return 生成的日期字符串
   */
  public static String getDate(Date date) {
    String result = getDate(date, DATE_FORMAT);
    return result;
  }

  /**
   * 传入Date和格式化字符串获取String的方法.
   *
   * @param date 日期
   * @param format 格式化字符串
   * @return 生成的日期字符串
   */
  public static String getDate(Date date, String format) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    String result = dateFormat.format(date);
    return result;
  }

  /**
   * 日期时间转字符串.
   *
   * @param date 日期时间
   * @param format 格式化字符串 yyyy-MM-dd
   * @return 字符串
   */
  public static String dateToString(Date date, String format) {
    SimpleDateFormat formater = new SimpleDateFormat();
    formater.applyPattern(format);
    return formater.format(date);
  }

  /**
   * 重新格式化日期格式的字符串.
   *
   * @param date 日期格式的字符串
   * @param quondamFormat 原始数据的格式化模板
   * @param format 新的格式化模板
   * @return 格式化后的字符串
   */
  public static String format(String date, String quondamFormat, String format) {
    Date dateTemp = getDate(date, quondamFormat);
    String result = getDate(dateTemp, format);
    return result;
  }

  /**
   * 获取本地时间.
   *
   * @param format 格式化时间
   * @return 程序服务器的当前时间
   */
  public static String getCurrentTime(String format) {
    Date date = new Date();
    SimpleDateFormat formater = new SimpleDateFormat();
    formater.applyPattern(format);
    return formater.format(date);
  }

  /**
   * 时间戳转换成字符串.
   *
   * @param timestamp 时间戳
   * @param format 格式化字段
   * @return 格式化后的字符串
   */
  public static String getTime(Long timestamp, String format) {
    Date date = new Date(timestamp);
    return dateToString(date, format);
  }

  /**
   * 使用默认的格式化方式把时间戳转换成字符串.
   *
   * @param timestamp 时间戳
   * @return 格式化后的字符串
   */
  public static String getTime(Long timestamp) {
    return getTime(timestamp, DATETIME_FORMAT);
  }

  /**
   * 读取时间戳.
   *
   * @param date 字符串形式的时间
   * @param format 格式化参数
   * @return 时间戳
   */
  public static Long getTimestamp(String date, String format) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    try {
      Long result = getTimestamp(simpleDateFormat.parse(date));
      return result;
    } catch (ParseException ex) {
      throw new DataBindingException(ex);
    }
  }

  /**
   * 使用默认格式化方式读取时间戳.
   *
   * @param date 字符串形式的时间
   * @return 时间戳
   */
  public static Long getTimestamp(String date) {
    Long result = getTimestamp(date, DATETIME_FORMAT);
    return result;
  }

  /**
   * 日期转换成时间戳.
   *
   * @param date 日期
   * @return 时间戳
   */
  public static Long getTimestamp(Date date) {
    Long result = date.getTime();
    return result;
  }

  public final static String DATE_FORMAT = "yyyy-MM-dd";
  public final static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  public final static String DATETIME_FORMAT_WITH_MS = "yyyy-MM-dd HH:mm:ss.SSS";
}
