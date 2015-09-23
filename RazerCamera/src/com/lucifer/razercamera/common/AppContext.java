package com.lucifer.razercamera.common;

import com.lucifer.razercamera.widget.AllDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;




public class AppContext extends Application
{
	private static AppContext context;
	
	
	//公共控件
	Dialog dialog;
	
	
	
	
	//生命周期创建函数
	@Override
	public void onCreate()
	{
		// TODO Auto-generated method stub
		super.onCreate();

		context = this;
			
	}

	
	
	//生命周期结束相关函数
	@Override
	public void onLowMemory()
	{
		// TODO Auto-generated method stub
		super.onLowMemory();
		System.gc();
	}
	
	
	
	public static synchronized AppContext getInstance()
	{
		return context;
	}
	
	
	
	//公共控件
	/**
	 * 显示Dialog
	 */
	public void showDialog(Context context)
	{
		if (dialog == null)
		{
			dialog = AllDialog.createLoadingDialog(context, "正在加载中...");
			dialog.show();
		}
	}

	
	
	public void closeDialog()
	{
		if (dialog != null)
		{
			if(dialog.isShowing())
			{
				dialog.dismiss();
				dialog = null;
			}
			
		}
	}
	
	
	
	 /** 
     * 获得屏幕高度 
     *  
     * @param context 
     * @return 
     */  
    public static int getScreenWidth()  
    {  
        WindowManager wm = (WindowManager) context  
                .getSystemService(Context.WINDOW_SERVICE);  
        DisplayMetrics outMetrics = new DisplayMetrics();  
        wm.getDefaultDisplay().getMetrics(outMetrics);  
        return outMetrics.widthPixels;  
    }  
  
    
    
    /** 
     * 获得屏幕宽度 
     *  
     * @param context 
     * @return 
     */  
    public static int getScreenHeight()  
    {  
        WindowManager wm = (WindowManager) context  
                .getSystemService(Context.WINDOW_SERVICE);  
        DisplayMetrics outMetrics = new DisplayMetrics();  
        wm.getDefaultDisplay().getMetrics(outMetrics);  
        return outMetrics.heightPixels;  
    }  
    
    
    
    public static int dip2px(float dipValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}


}
