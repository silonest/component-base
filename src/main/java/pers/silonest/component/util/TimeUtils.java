package pers.silonest.component.util;

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
public class TimeUtils {

  /**
   * 获取指定日期是第几天
   * 
   * @param date 日期
   * @return
   */
  public static final int dayInYear(Date date) {
    return getCalendar(date).get(Calendar.DAY_OF_YEAR);
  }

  /**
   * 转换日期格式为yyyy-MM-dd
   * 
   * @param aDate 待转换的日期
   * @return
   */
  public static final String convertDate2String(Date date) {
    return convertDate2String(date, DATETIME_FORMAT);
  }

  /**
   * 转换格式为yyyy-mm-dd HH:mm:ss的日期字符串转化为指定格式的字符串
   * 
   * @param strDate 待转换的日期，格式为：yyyy-mm-dd HH:mm:ss
   * @param pattern 结果所需的格式
   * @return
   */
  public static final String convertDate2String(String strDate, String pattern) {
    return convertDate2String(strDate, DATETIME_FORMAT, pattern);
  }

  /**
   * 转换格式为yyyy-mm-dd HH:mm:ss的日期字符串转化为指定格式的字符串
   * 
   * @param strDate 待转换的日期，格式为：yyyy-mm-dd HH:mm:ss
   * @param pattern_Src 待转日期格式
   * @param pattern_Des 结果所需的格式
   * @return
   */
  public static final String convertDate2String(String strDate, String pattern_Src, String pattern_Des) {
    return convertDate2String(convertString2Date(strDate, pattern_Src), pattern_Des);
  }

  /**
   * 转换日期为指定的格式
   * 
   * @param aDate 待转换的日期对象
   * @param pattern 格式
   * @return
   */
  public static final String convertDate2String(Date date, String pattern) {
    SimpleDateFormat df = null;
    String returnValue = "";
    if (date != null) {
      df = new SimpleDateFormat(pattern);
      returnValue = df.format(date);
    }
    return returnValue;
  }

  /**
   * 转换yyyy-MM-dd格式的字符串转化为日期
   * 
   * @param aDate 待转换的字符串
   * @return
   */
  public static final Date convertString2Date(String strDate) {
    return convertString2Date(strDate, DATETIME_FORMAT);
  }

  /**
   * 转换指定的格式字符串转化为日期
   * 
   * @param aDate 待转换的字符串
   * @param pattern 格式
   * @return
   */
  public static final Date convertString2Date(String strDate, String pattern) {
    SimpleDateFormat df = null;
    Date date = null;

    if (strDate != null) {
      df = new SimpleDateFormat(pattern);
      try {
        date = df.parse(strDate);
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }

    return date;
  }

  /**
   * 获取日历
   * 
   * @return
   */
  public static Calendar getCalendar() {
    return Calendar.getInstance();
  }

  /**
   * 获取日历
   * 
   * @param date
   */
  public static Calendar getCalendar(Date date) {
    Calendar c = getCalendar();
    c.setTime(date);
    return c;
  }

  /**
   * 获取日期
   * 
   * @return
   */
  public static Date getDate(int year, int month, int day) {
    Calendar c = getCalendar();
    c.set(Calendar.YEAR, year);
    c.set(Calendar.MONTH, month);
    c.set(Calendar.DATE, day);
    return c.getTime();
  }

  /**
   * 
   * @author arron
   * @date 2015年12月23日 下午1:54:19
   * @version 1.0
   */
  public static class Lunar {
    /**
     * 支持转换的最小农历年份
     */
    private static final int MIN_YEAR = 1900;
    /**
     * 支持转换的最大农历年份
     */
    private static final int MAX_YEAR = 2049;

    // 农历信息1900-2099
    // 数据结构如下，共使用17位数据
    // 第17位：表示闰月天数，0表示29天 1表示30天
    // 第16位-第5位（共12位）表示12个月，其中第16位表示第一月，如果该月为30天则为1，29天为0
    // 第4位-第1位（共4位）表示闰月是哪个月，如果当年没有闰月，则置0
    private final static long[] lunarInfo = new long[] {0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2,
        0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40,
        0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950,
        0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0,
        0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0,
        0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6, 0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a,
        0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0,
        0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9,
        0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,
        0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577,
        0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0};

    // 天干、地支、属相
    private final static String[] Gan = new String[] {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    private final static String[] Zhi = new String[] {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    private final static String[] Animals = new String[] {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};

    // 月份日期
    private final static String chineseTen[] = {"初", "十", "廿", "卅"};
    private final static String chineseDay[] = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};
    private final static String chineseMonth[] = {"正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "腊月"};

    /**
     * 获取指定农历年的总天数
     * 
     * @param year
     * @return
     */
    public static int days4YearInLunar(int year) {
      int i, sum = 348;
      for (i = 0x8000; i > 0x8; i >>= 1) {
        if ((lunarInfo[year - MIN_YEAR] & i) != 0)
          sum += 1;
      }
      return (sum + days4LeapInLunar(year));
    }

    /**
     * 获取指定农历年闰月的天数
     * 
     * @param year
     * @return
     */
    public static int days4LeapInLunar(int year) {
      if (leapMonth(year) != 0) {
        if ((lunarInfo[year - MIN_YEAR] & 0x10000) != 0)
          return 30;
        else
          return 29;
      } else
        return 0;
    }

    /**
     * 获取指定农历年某月的总天数
     * 
     * @param year
     * @param month
     * @return
     */
    public static int days4MonthInLunar(int year, int month) {
      if ((lunarInfo[year - MIN_YEAR] & (0x10000 >> month)) == 0)
        return 29;
      else
        return 30;
    }

    /**
     * 获取指定农历年闰月月份，没闰月返回 0
     * 
     * @param year
     * @return
     */
    public static int leapMonth(int year) {
      return (int) (lunarInfo[year - MIN_YEAR] & 0xf);
    }

    // ########################################
    // ########### 公历 转 农历计算 ###############
    // ########################################
    // ====================================== 算出农历, 传入日期物件, 传回农历日期物件

    // 该物件属性有 .year .month .day .isLeap .yearCyl .dayCyl .monCyl

    public static String getLunar(Date date) {
      return getLunar(date, false);
    }

    public static String getLunar(Date date, boolean showTGDZ) {
      int year, month, day;// 记录农历日期
      int leap = 0; // 记录闰月
      int temp = 0;
      int yearCyl, monCyl, dayCyl;// 天干地支年月日

      int i;
      boolean isLeap = false;

      // 1900-01-31是农历1900年正月初一
      Date baseDate = getCalendar(convertString2Date("1900-01-31", "yyyy-mm-dd")).getTime();// 获取毫秒数

      // 计算当前日期距1900-01-31的天数
      int offset = (int) ((date.getTime() - baseDate.getTime()) / (24 * 60 * 60 * 1000)); // 天数
      // 1899-12-21是农历1899年腊月甲子日
      dayCyl = offset + 40;
      // 1898-10-01是农历甲子月
      monCyl = 14;

      // 计算当前日期距1900-01-31的年数
      for (i = MIN_YEAR; i <= MAX_YEAR && offset > 0; i++) {
        temp = days4YearInLunar(i); // 农历每年天数
        offset -= temp;
        monCyl += 12;
      }
      if (offset < 0) {
        offset += temp;
        i--;
        monCyl -= 12;
      }
      year = i;// 记录农历年
      yearCyl = year - MIN_YEAR + 36;
      leap = leapMonth(i); // 计算闰哪个月

      for (i = 1; i <= 12 && offset > 0; i++) {
        if (leap > 0 && i == (leap + 1) && isLeap == false) { // 是闰月
          --i;
          isLeap = true;
          temp = days4LeapInLunar(year);// 计算农历闰月天数
        } else {
          temp = days4MonthInLunar(year, i);// 计算农历某月天数
        }
        if (isLeap == true && i == (leap + 1)) { // 解除闰月
          isLeap = false;
        }
        offset -= temp;
        if (isLeap == false) {
          monCyl++;
        }
      }

      if (offset == 0 && leap > 0 && i == leap + 1) {// 正好是闰月
        if (isLeap) {
          isLeap = false;
        } else {
          isLeap = true;
          --i;
          --monCyl;
        }
      }
      if (offset < 0) {
        offset += temp;
        --i;
        --monCyl;
      }
      month = i; // 农历月份
      day = offset + 1; // 农历天份
      if (isLeap) {

      }
      return getChinaString(year, month, day, yearCyl, monCyl, dayCyl, isLeap, showTGDZ);
      // System.out.println(day);
    }

    // ########################################
    // ########### 生肖、天干、地支 ###############
    // ########################################

    /**
     * 获取农历 y年的生肖
     * 
     * @return
     */
    public static String animalsYear(int year) {
      return Animals[(year - 4) % 12];
    }

    /**
     * 传入月日的offset 获取干支, 0=甲子
     * 
     * @param num
     * @return
     */
    public static String cyclicalm(int num) {
      return (Gan[num % 10] + Zhi[num % 12]);
    }

    // #################################################
    // ########### 下面是转汉字部分 ###############
    // #################################################

    /**
     * 农历转汉字
     * 
     * @param year 农历年份
     * @param month 农历月份
     * @param day 农历日
     * @param monthCyl 天干地支月份
     * @param dayCyl 天干地支日
     * @param isLeap 是否是闰月
     * @param showTGDZ 是否显示天干地支
     * @return
     */
    private static String getChinaString(int year, int month, int day, int yearCyl, int monthCyl, int dayCyl, boolean isLeap, boolean showTGDZ) {
      StringBuffer res = new StringBuffer();
      if (showTGDZ) {// 显示天干地支
        res.append(cyclicalm(yearCyl)).append("年【").append(getYearInChinese(year)).append("】").append((isLeap ? "闰" : ""))
            .append(getMonthInChinese(month)).append(getDayInChinese(day)).append("（").append(cyclicalm(monthCyl)).append("月 ")
            .append(cyclicalm(dayCyl)).append("日").append("）");
      } else {
        res.append(getYearInChinese(year)).append((isLeap ? " 闰" : " ")).append(getMonthInChinese(month)).append(getDayInChinese(day));
      }
      return res.toString();
    }

    /**
     * 农历月份转汉字
     * 
     * @param year 农历年份
     * @param type 1：属相年，2：属相年，默认：天干地支+属相年
     * @return
     */
    private static String getYearInChinese(int year) {
      if (year <= MIN_YEAR || year > MAX_YEAR) {
        return "出错";
      }
      return animalsYear(year) + "年";
    }

    /**
     * 农历月份转汉字
     * 
     * @param month 农历月份
     * @return
     */
    private static String getMonthInChinese(int month) {
      if (month <= 0 || month > 12) {
        return "出错";
      }
      return chineseMonth[month - 1];
    }

    /**
     * 农历日期转汉字
     * 
     * @param day 农历日
     * @return
     */
    private static String getDayInChinese(int day) {
      int n = day % 10 == 0 ? 9 : day % 10 - 1;
      if (day > 30)
        return "出错";
      if (day == 10)
        return "初十";
      else
        return chineseTen[day / 10] + chineseDay[n];
    }
  }

  /*----------------------分割线----------------------------*/
  /*----------------------分割线----------------------------*/
  /*----------------------分割线----------------------------*/
  /*----------------------分割线----------------------------*/
  /*----------------------分割线----------------------------*/
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
