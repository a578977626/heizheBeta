package com.heizhe.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import org.apache.log4j.Logger;

import com.heizhe.constant.DateConstant;

public class DateUtil {

    private static final Logger logger = Logger.getLogger(DateUtil.class);

    public DateUtil() {
        super();
    }


    /**
     * @说明
     * 本类中测试了部分java8日期时间、还有很多共嫩更齐全的java8日期时间的用法此工具类就不一一测试了
     * 因为java8新的时间日期等用法都比较简单，此处就不一一赘述了，有兴趣或常用的请自行研究
     */


    /**
     * @第一部分：DateFormat method {@link DateFormat}
     * 例如：String转Date（参数：字符串+转换格式）
     *      Date转指定格式字符串（参数：日期+转换格式）
     *      获取指定格式的当前日期（参数：转换格式）
     *      获取指定格式的当前日期字符串（参数：转换格式）
     * 详细方法使用参看测试 test/java/com/share/tool/DateUtilTest
     */

    /**
     * 格式化字符串返回指定格式日期（可使用DateConstant中的format类型）
     *
     * @param date
     * @param format
     * @return Date
     */
    public static Date dateFormat(String date, String format) {
        logger.info("<==  方法dataFormate的参数::" + date + " , " + format + "   开始执行");
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            logger.info("<==  方法dataFormate执行结束");
            return dateFormat.parse(date);
        } catch (ParseException e) {
            logger.error("<==  格式化失败" + e.getMessage());
            return null;
        }
    }

    /**
     * 格式化日期返回指定格式字符串（可使用DateConstant中的format类型）
     *
     * @param date
     * @param format
     * @return String
     */
    public static String dateFormat(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * 获取指定格式的当前日期（可使用DateConstant中的format类型）
     *
     * @param format
     * @return Date
     */
    public static Date getNowDate(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateFormat.format(new Date()));
        } catch (ParseException e) {
            logger.error("<==  获取异常" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取指定格式的当前日期的字符串（可使用DateConstant中的format类型）
     *
     * @param format
     * @return String
     */
    public static String getNowDateString(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date());
    }

    /**
     * @第二部分：java8日期新特性
     * java8新增时间相关类的用法
     * <p>* LocalDate only for year/month/day {@link LocalDate}
     * LocalTime only for hour/minutes/second/nanoOfSecond {@link LocalTime}
     * <p>
     * Except this,there has another one : LocalDateTime {@link LocalDateTime}
     * It's contains LocalDate and LocalTime
     */

    /**
     * 获取当前年份
     *
     * @return int
     */
    public static int getYear() {
        return LocalDate.now().getYear();
    }

    /**
     * 获取当前月份
     *
     * @return int
     */
    public static int getMonthValue() {
        return LocalDate.now().getMonth().getValue();
    }

    /**
     * 获取当前月份对象
     *
     * @return Month
     */
    public static Month getMonth() {
        return LocalDate.now().getMonth();
    }

    /**
     * 获取某年中指定第几日的日期
     * <p>
     * 类似的还有获取某年某月中的第几天的日期、根据毫秒数获取某年某月的日期，此处不再赘述
     *
     * @param year
     * @param dayOfYear
     * @return
     */
    public static final LocalDate getDayOfYear(int year, int dayOfYear) {
        return LocalDate.ofYearDay(year, dayOfYear);
    }

    /**
     * 获取今日属于这年的第几天
     *
     * @return int
     */
    public static int getDayOfYear() {
        return LocalDate.now().getDayOfYear();
    }

    /**
     * 获取今日属于这月的第几天
     *
     * @return int
     */
    public static int getDayOfMonth() {
        return LocalDate.now().getDayOfMonth();
    }

    /**
     * 获取今日属于这周的第几天
     *
     * @return int
     */
    public static int getDayOfWeekValue() {
        return LocalDate.now().getDayOfWeek().getValue();
    }

    /**
     * 获取今日属于这周的周对象
     *
     * @return DayOfWeek
     */
    public static DayOfWeek getDayOfWeek() {
        return LocalDate.now().getDayOfWeek();
    }

    /**
     * 获取指定时区的当前时间
     *
     * @param zoneId
     * @return LocalDate
     */
    public static LocalDate getZoneDate(String zoneId) {
        return LocalDate.now(ZoneId.of(zoneId));
    }

    /**
     * 获取当前时分秒时间
     *
     * @return LocalTime
     */
    public static final LocalTime getNowTime() {
        return LocalTime.now();
    }

    /**
     * 获取指定时分时间
     *
     * @param hour
     * @param minutes
     * @return LocalTime
     */
    public static final LocalTime getTime(int hour, int minutes) {
        return LocalTime.of(hour, minutes);
    }

    /**
     * 获取指定时分秒时间
     *
     * @param hour
     * @param minutes
     * @param second
     * @return
     */
    public static final LocalTime getTime(int hour, int minutes, int second) {
        return LocalTime.of(hour, minutes, second);
    }

    /**
     * 获取指定时分秒纳秒时间
     *
     * @param hour
     * @param minutes
     * @param second
     * @param nanoOfSecond
     * @return LocalTime
     */
    public static final LocalTime getTime(int hour, int minutes, int second, int nanoOfSecond) {
        return LocalTime.of(hour, minutes, minutes, nanoOfSecond);
    }

    /**
     * java8新增时间相关类的用法
     * Instant  {@link Instant}
     * 机器可读的时间格式，Unix时间戳存储日期时间
     * <p>
     * 其他常用时间戳
     */

    /**
     * 获取当前时间戳对象
     *
     * @return
     */
    public static final Instant getInstant() {
        return Instant.now();
    }

    /**
     * 获取当前10位长度的时间戳，精确到秒
     *
     * @return
     */
    public static final Long getEpochSecond() {
        return Instant.now().getEpochSecond();
    }

    /**
     * 获取当前13位长度的时间戳，精确到毫秒
     *
     * @return
     */
    public static final Long getEpochMilli() {
        return Instant.now().toEpochMilli();
    }

    /**
     * 获取当前时间的纳秒数
     *
     * @return
     */
    public static final int getNano() {
        return Instant.now().getNano();
    }

    /**
     * @第三部分：日期计算相关 <p>
     * 给出两个毫秒值计算其天、小时、分、秒
     * <p>
     * diffType:日期格式转换中的字符串类型 yyyy MM dd HH mm ss
     */
    public static final long getDiffMilli(Long start, Long end, String diffType) {
        long milli = end - start;
        long result = 0L;
        switch (diffType) {
            case DateConstant.DAY:
                result = milli / 1000 / 3600 / 24;
                break;
            case DateConstant.HOUR:
                result = milli / 1000 % (3600 * 24) / 3600;
                break;
            case DateConstant.MINUTES:
                result = milli / 1000 % 3600 / 60;
                break;
            case DateConstant.SECOND:
                result = milli / 1000 % 60;
                break;
            default:
                result = milli;
        }
        return result;
    }


}
