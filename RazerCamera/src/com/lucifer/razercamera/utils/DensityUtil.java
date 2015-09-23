package com.lucifer.razercamera.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;


public class DensityUtil
{
	/**
	 * 将px值转换为dip或dp值，保证尺寸大小不变
	 * 
	 * @param pxValue
	 * @param scale
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int px2dip(Context context, float pxValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	
	
	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 * 
	 * @param dipValue
	 * @param scale
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int dip2px(Context context, float dipValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	
	
	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(Context context, float pxValue)
	{
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	
	
	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(Context context, float spValue)
	{
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	
	
	
	
	//文字处理
	// 返回指定笔和指定字符串的长度
	public static float getFontlength(Paint paint, String str)
	{
		return paint.measureText(str);
	}

	// 返回指定笔的文字高度
	public static float getFontHeight(Paint paint)
	{
		FontMetrics fm = paint.getFontMetrics();
		return fm.descent - fm.ascent;
	}

	// 返回指定笔离文字顶部的基准距离
	public static float getFontLeading(Paint paint)
	{
		FontMetrics fm = paint.getFontMetrics();
		return fm.leading - fm.ascent;
	}
		

}
