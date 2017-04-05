package com.smurfs.console.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * 判断是否是日期格式
	 * @param str
	 * @param formatStr
	 * @return
	 */
	public static boolean isValidDate(String str, String formatStr) {
		boolean convertSuccess = true;
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		try {
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			convertSuccess = false;
		} catch (NullPointerException ex) {
			convertSuccess = false;
		}
		return convertSuccess;
	}
	
	/** 
     * 将日期转化为日期字符串。失败返回null。 
     * @param date 日期 
     * @param parttern 日期格式 
     * @return 日期字符串 
     */  
    public static String DateToString(Date date, String parttern) {  
        String dateString = null;  
        if (date != null) {  
            try {  
                dateString = getDateFormat(parttern).format(date);  
            } catch (Exception e) {  
            }  
        }  
        return dateString;  
    }  
    /** 
     * 获取SimpleDateFormat 
     * @param parttern 日期格式 
     * @return SimpleDateFormat对象 
     * @throws RuntimeException 异常：非法日期格式 
     */  
    private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {  
        return new SimpleDateFormat(parttern);  
    }  
    public static Date getBeforeNowMonth(int beforeMonthNum)
    {
    	Date dNow = new Date();   //当前时间
    	Date dBefore = new Date();
    	Calendar calendar = Calendar.getInstance(); //得到日历
    	calendar.setTime(dNow);//把当前时间赋给日历
    	calendar.add(calendar.MONTH,beforeMonthNum);  //设置为前3月
    	dBefore = calendar.getTime();   //得到前3月的时间
    	return dBefore;
    }

}
