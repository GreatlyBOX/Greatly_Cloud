package com.redisson.redisson.utils;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

/**
 * 日期处理
 */
public class DateUtils {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_TIME_PATTERN);
    }

    public static Date format(String time) {
        return parse(time, DATE_TIME_PATTERN);
    }
    public static Date formatS(String time) {
        return parse(time, DATE_PATTERN);
    }

    public static String formatS(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    public static Date parse(String date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            try {
                return df.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //获得当前时期加一天
    public  static  Date  getDate(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();

    }

    public static Date getBeginTime(Date date,Integer timeSpan){
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date);//设置当前日期
        calendar.add(Calendar.MINUTE, -timeSpan);//加、减
        return calendar.getTime();
    }


    /**
     * 获取指定时间的后分钟的时间
     *
     * @param date 2018-01-01
     * @return 2018-01-01 ~ 2018-01-31
     * @author ThinkGem
     */
    public static String getBeforeTime(Date date, int minutes) {
        //当前时间加30分钟
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, minutes);//要增加什么，在这里写
        return sdf.format(nowTime.getTime());
    }

    //获取本周的周一的日期
    public  static Date  getWeekStart(){
        Calendar ca=Calendar.getInstance();
        int dayOfWeek = ca.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek==1) {
            dayOfWeek=7;
        }else{
            dayOfWeek--;
        }
        Calendar  cal=(Calendar)ca.clone();
        cal.add(Calendar.DATE,1-dayOfWeek);
        return cal.getTime();
    }

    //获取本周周日的日期
    public  static Date  getWeekEnd(){
        Calendar ca=Calendar.getInstance();
        int dayOfWeek = ca.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek==1) {
            dayOfWeek=7;
        }else{
            dayOfWeek--;
        }
        Calendar  cal=(Calendar)ca.clone();
        cal.add(Calendar.DATE,7-dayOfWeek);
         return  cal.getTime();
    }

    public static long compareDate(Date endDate,Date beginDate,int field){
        long diff = endDate.getTime() - beginDate.getTime();
        switch (field){
            case Calendar.DATE:
                return diff / (1000 * 24 * 60 * 60);
            case Calendar.HOUR:
                return diff /(1000*60*60);
            case Calendar.MINUTE:
                return diff /(1000*60);
            case Calendar.SECOND:
                return diff /(1000);
        }
        return diff;
    }
        public static long compareDate(Date endDate, Date nowDate) {
            long nd = 1000 * 24 * 60 * 60;
            long nh = 1000 * 60 * 60;
            long nm = 1000 * 60;
            // long ns = 1000;
            // 获得两个时间的毫秒时间差异
            long diff = endDate.getTime() - nowDate.getTime();
            // 计算差多少天
            long day = diff / nd;
            // 计算差多少小时
            long hour = diff % nd / nh;
            // 计算差多少分钟
            long min = diff % nd % nh / nm;
            // 计算差多少秒//输出结果
            // long sec = diff % nd % nh % nm / ns;
            return day ;
        }

    /**
     * 根据出生日期获取年龄
     * @param birthDate
     * @return
     */
    public static int getAge(Date birthDate){
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(new Date());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);

        int age = calendarNow.get(Calendar.YEAR) -  calendar.get(Calendar.YEAR);
        if(calendar.get(Calendar.DAY_OF_YEAR) < calendarNow.get(Calendar.DAY_OF_YEAR)){
            if(age > 0){
                age -= 1;
            }
        }
        return age;
    }

    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分
     *
     * @param date
     *            日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    public static  List<Date> getDateWeek(Date date) {
        List<Date> list = new ArrayList();
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, 7);
        for(int i=0;i<7;i++) {
            list.add(cal.getTime());
            cal.roll(Calendar.DAY_OF_YEAR,true);
        }
        return list;
    }

    /**
     * 获取当前是本年的第几周
     * @return
     */
    public static  Integer  getWeek(){
        Calendar cal = Calendar.getInstance();
       return cal.get(Calendar.WEEK_OF_YEAR);
    }
    /**
     * 获取指定时间是当年多少周
     * @return
     */
    public  static Integer  getNextWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        return    calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public  static Integer  getNextNextWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 7);
        return    calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取当前是那一年
     * @return
     */
    public static  Integer getYear(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }
    /**
     * 获取指定时间是哪一年
     * @return
     */
    public static  Integer getNextYear(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static  Integer getNextNextYear(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 7);
        return cal.get(Calendar.YEAR);
    }
    /**
     * 获取某个日期是星期几  0 表示星期日
     * @param date
     * @return
     * @throws Exception
     */
    public static Integer dayForWeek(Date date){
        Calendar cal = Calendar.getInstance();
        if ("".equals(date)) {
            cal.setTime(new Date(System.currentTimeMillis()));
        }else {
            cal.setTime(date);
        }

        Integer i = cal.get(Calendar.DAY_OF_WEEK);
        i=i-1;
        return i;
    }



    /**
     * 获取本周排班具体的时间
     * @param hour  eg: 09:20
     * @param param   周几 0 表示周日
     * @return
     * @throws ParseException
     */
    public static Date getOnTime(String hour,Integer  param) throws ParseException {
        Date date =null;
        String  time=null;
        List<String> weekDate = printWeekdays();
        time   = weekDate.get(param)+" "+hour+":00";
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sdf.parse(time);

        return date;
    }
    /**
     * 获取下周排班具体的时间
     * @param hour  eg: 09:20
     * @param param   周几 0 表示周日
     * @return
     * @throws ParseException
     */
    public static Date getNextTime(String hour,Integer  param) throws ParseException {
        Date date =null;
        String  time=null;
        List<String> weekDate = getNextWeekDate();
        time   = weekDate.get(param)+" "+hour+":00";
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sdf.parse(time);

        return date;
    }

    public static Date getNextNextTime(String hour,Integer  param,Date dates) throws ParseException {
        Date date =null;
        String  time=null;
        List<String> weekDate = getWeekDate(dates);
        time   = weekDate.get(param)+" "+hour+":00";
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sdf.parse(time);

        return date;
    }

    public static Date getNextNextTime1(String hour,Integer  param,Date dates) throws ParseException {
        Date date =null;
        String  time=null;
        List<String> weekDate = getWeekDate1(dates);
         time   = weekDate.get(param)+" "+hour+":00";
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sdf.parse(time);

        return date;
    }


    /**
     * 获取下周一周的时间
     * @return
     */
    public static  List<String> getWeekDate() {
        List<String> list = new ArrayList();
        Calendar cal = queryDate();
        SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<7;i++) {
            list.add(d.format(cal.getTime()));
            cal.roll(Calendar.DAY_OF_YEAR,true);
        }
        return list;
    }
    public static  List<String> getNextWeekDate() {
        List<String> list = new ArrayList();
        Calendar cal = queryDate();
        cal.add(Calendar.DATE, 7);
        SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<7;i++) {
            list.add(d.format(cal.getTime()));
            cal.roll(Calendar.DAY_OF_YEAR,true);
        }
        return list;
    }

    public static  List<String> getWeekDate(Date date) {
        List<String> list = new ArrayList();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 7);
        SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<7;i++) {
            list.add(d.format(cal.getTime()));
            cal.roll(Calendar.DAY_OF_YEAR,true);
        }
        return list;
    }

    public static  List<String> getWeekDate1(Date date) {
        List<String> list = new ArrayList();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<7;i++) {
            list.add(d.format(cal.getTime()));
            cal.roll(Calendar.DAY_OF_YEAR,true);
        }
        return list;
    }

    public static  List<Date> getNextWeek1() {
        List<Date> list = new ArrayList();
        Calendar cal = queryDate();
        cal.add(Calendar.DATE, 7);
        for(int i=0;i<7;i++) {
            list.add(cal.getTime());
            cal.roll(Calendar.DAY_OF_YEAR,true);
        }
        return list;
    }

    /**
     * 获取下周一周的日期Date格式
     * @return
     */
   // private static final int FIRST_DAYS = Calendar.SUNDAY;
    public static  List<Date> getNextWeek() {
        List<Date> list = new ArrayList();
        Calendar cal = queryDate();
       /* cal.add(Calendar.DATE, 7);*/
        for(int i=0;i<7;i++) {
            list.add(cal.getTime());
            cal.roll(Calendar.DAY_OF_YEAR,true);
        }
        return list;
    }
    /**
     * 获取明天的日期
     * @return
     */
    public static Date  getTomorrowDate(){
        Date currentEndDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentEndDate);
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.AM_PM, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return  cal.getTime();
    }

    private static Calendar queryDate() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_WEEK, (8-cal.get(Calendar.DAY_OF_WEEK))%7);
        return cal;
    }


    /**
     * 获取本周一周的日期
     */
    private static final int FIRST_DAY = Calendar.SUNDAY;

    public static List<String> printWeekdays() {
        List<String> week=new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        setToFirstDay(calendar);
        for (int i = 0; i < 7; i++) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            week.add(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        return week;
    }

    public static List<Date> getNewWeek() {
        List<Date> week=new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        setToFirstDay(calendar);
        for (int i = 0; i < 7; i++) {
            week.add(calendar.getTime());
            calendar.add(Calendar.DATE, 1);
        }
        return week;
    }

    private static void setToFirstDay(Calendar calendar) {
        while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
            calendar.add(Calendar.DATE, -1);
        }
    }



    /**
     * 日期转星期几（中文）
     *
     * @param date
     * @return
     */
    public static String dateToWeek(Date date) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
        {w = 0;}
        return weekDays[w];
    }


    public static List<Date> getAllweekDays(Date d) {//根据日期来获取其所在周的每一天
        Calendar c = Calendar.getInstance();
        List <Date>lst=new ArrayList();
        c.setTime(d);
        setToFirstDay(c);
        for (int i = 0; i < 7; i++) {
            Date date = c.getTime();
            lst.add(date);
            c.add(Calendar.DATE, 1);
        }
        return lst;
    }

    public static List<Date> getAllNextweekDays(Date d) {//根据日期来获取其所在周的每一天
        Calendar c = Calendar.getInstance();
        List <Date>lst=new ArrayList();
        c.setTime(d);
        c.add(Calendar.DATE, 7);
        setToFirstDay(c);
        for (int i = 0; i < 7; i++) {
            Date date = c.getTime();
            lst.add(date);
            c.add(Calendar.DATE, 1);
        }
        return lst;
    }
    public static Date dateToDate(Date date){
        String dates = formatS(date);
        return  formatS(dates);
    }




    /**
     * 根据身份证的号码算出当前身份证持有者的性别和年龄 18位身份证
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> getCarInfo(String CardCode)
             {
        Map<String, Object> map = new HashMap<String, Object>();
        String year = CardCode.substring(6).substring(0, 4);// 得到年份
        String yue = CardCode.substring(10).substring(0, 2);// 得到月份
        // String day=CardCode.substring(12).substring(0,2);//得到日
        String sex;
        if (Integer.parseInt(CardCode.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别
            sex = "女";
        } else {
            sex = "男";
        }
        Date date = new Date();// 得到当前的系统时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fyear = format.format(date).substring(0, 4);// 当前年份
        String fyue = format.format(date).substring(5, 7);// 月份
         String fday=format.format(date).substring(8,10);
        int age = 0;
        if (Integer.parseInt(yue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
            age = Integer.parseInt(fyear) - Integer.parseInt(year) + 1;
        } else {// 当前用户还没过生
            age = Integer.parseInt(fyear) - Integer.parseInt(year);
        }
        String birthDay = fyear+"-"+fyue+"-"+fday;
        map.put("sex", sex);
        map.put("age", age);
        map.put("birthDay",birthDay);
        return map;
    }

    /**
     * 15位身份证的验证
     *
     * @param
     * @throws Exception
     */
    public static Map<String, Object> getCarInfo15W(String card)
            {
        Map<String, Object> map = new HashMap<String, Object>();
        String uyear = "19" + card.substring(6, 8);// 年份
        String uyue = card.substring(8, 10);// 月份
        // String uday=card.substring(10, 12);//日
        String usex = card.substring(14, 15);// 用户的性别
        String sex;
        if (Integer.parseInt(usex) % 2 == 0) {
            sex = "女";
        } else {
            sex = "男";
        }
        Date date = new Date();// 得到当前的系统时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fyear = format.format(date).substring(0, 4);// 当前年份
        String fyue = format.format(date).substring(5, 7);// 月份
         String fday=format.format(date).substring(8,10);
        int age = 0;
        if (Integer.parseInt(uyue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
            age = Integer.parseInt(fyear) - Integer.parseInt(uyear) + 1;
        } else {// 当前用户还没过生
            age = Integer.parseInt(fyear) - Integer.parseInt(uyear);
        }
        String birthDay = fyear+"-"+fyue+"-"+fday;
        map.put("sex", sex);
        map.put("age", age);
        map.put("birthDay",birthDay);
        return map;
    }

    public static  String  getUTC(String dateTime){
//		String dateTime="2019-06-27T16:00:00.000Z";

        dateTime = dateTime.replace("Z", " UTC"); //2019-06-27T16:00:00.000 UTC

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//转换时区格式
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;//将Z时间格式转换成Date类型格式或换成毫秒
        try {
            date = format1.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//		long ldate = format1 .parse(dateTime ).getTime();
        String time= format2.format(date);//2019-06-28

        System.out.println(time);
        return time;
    }

    public static LocalDate getLocalDate(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
    }

    public static LocalDateTime getLocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime;
    }

    public static LocalTime getLocalTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalTime localTime = localDateTime.toLocalTime();
        return localTime;
    }
}
