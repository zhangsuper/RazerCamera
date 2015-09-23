package com.lucifer.razercamera.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil
{
	
	//时间相关
	public static String changeTimerFormat(long paramLong)
	{
		long l1 = paramLong / 86400000L;
		long l2 = paramLong % 86400000L / 3600000L;
		long l3 = paramLong % 3600000L / 60000L;
		if ((l1 == 0L) && (l2 > 0L))
		{
			return l2 + "小时";
		}
		long l4 = paramLong % 60000L / 1000L;
		String str1 = String.valueOf(l3);
		String str2 = String.valueOf(l4);
		if (l3 < 10L)
		{
			str1 = "0" + l3;
		}
		if (l4 < 10L)
		{
			str2 = "0" + l4;
		}
		return str1 + ":" + str2;
	}
	
	
	
	public static String formatDuring(long mss) 
	{  
	    long days = mss / (1000 * 60 * 60 * 24);  
	    long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);  
	    long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);  
	    long seconds = (mss % (1000 * 60)) / 1000;  
	    
	    return days + " 天 " + hours + " 时 " + minutes + " 分 "  
	            + seconds + " 秒 ";  
	} 
	
	
	/**
	 * 指定格式返回当前系统时间
	 */
	public static String getDataTime(String format)
	{
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(new Date());
	}

	
	
	//计算时间，返回秒
	public static String internalTime(Date startDate, Date endDate)
	{
		long diff= endDate.getTime() - startDate.getTime();
		long seconds = (diff % (1000 * 60)) / 1000;
		String secondString = Long.toString(seconds);
		return secondString + "秒";
	}
	
	
	
	
	public static boolean isStringAvailable(String string)
	{
		if(string != null && !string.equals("") && !string.equals("null"))
		{
			return true;
		}
		else {
			return false;
		}
	}

	
	
	
	
	
}
