package com.lucifer.razercamera.activity;

import java.util.ArrayList;
import org.kymjs.kjframe.utils.DensityUtils;
import com.lucifer.razercamera.R;
import com.lucifer.razercamera.bean.SaveImage;
import com.lucifer.razercamera.common.Constants;
import com.lucifer.razercamera.utils.ImageUtils;
import com.lucifer.razercamera.utils.ScreenUtils;
import com.lucifer.razercameralib.camera.CameraContainer;
import com.lucifer.razercameralib.camera.CameraContainer.TakePictureListener;
import com.lucifer.razercameralib.camera.CameraView.FlashMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * 相机页面功能：关闭界面，切换摄像头，开关闪光灯；对Camera的滤镜功能；切换模式，照相，相册选。
 * @author lawliet
 */
public class CameraAty extends Activity implements View.OnClickListener,
		TakePictureListener
{
	public final static String TAG = "CameraAty";
	private String mSaveRoot;
	String imageString;
	Bitmap bitmap;
	String from;
	
	//上部
	RelativeLayout rl_topmenu;
	ImageView iv_finish_top_camera;
	TextView tv_mode_top_camera;
	ImageView btn_switch_top_camera;
	ImageView btn_flash_top_camera;
	
	
	//下部
	RelativeLayout rl_bottommenu;
	ImageView iv_mode_bottom_camera;
	ImageButton btn_shutter_bottom_camera;
	TextView tv_gallery_bottom_camera;
	
	
	//左边
	RelativeLayout rl_leftmenu;
	ImageView iv_finish_left_camera;
	TextView tv_mode_left_camera;
	ImageView btn_switch_left_camera;
	ImageView btn_flash_left_camera;
	
	
	//右边
	RelativeLayout rl_rightmenu;
	ImageView iv_mode_right_camera;
	ImageButton btn_shutter_right_camera;
	TextView tv_gallery_right_camera;
	
	
	ImageView iv_normalbg_camera;
	RelativeLayout rl_filmbg_camera;
	private CameraContainer mContainer;
	
	RelativeLayout rl_pb_camera;
	ProgressBar pb_take_camera;
	RotateAnimation progressbar_rotate;
	
	
	LayoutInflater inflater;
	View popView;
	PopupWindow popupWindow;
	String mode = "大片";
	String fileName;
	
	boolean isshow = false;
	
	ArrayList<SaveImage> saveImages = null;
	
	
	ImageView iv_test;
	
	
	
	@SuppressLint("HandlerLeak")
	public Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			
			switch (msg.what)
			{
			//图像已经保存，再保存ArrayList后跳转。
			case Constants.SAVETAKEDPIC:
				pb_take_camera.clearAnimation();
				pb_take_camera.setVisibility(View.GONE);
				
				Intent intent = new Intent(CameraAty.this, ActivityFilm.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("saveImages", saveImages);
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
				break;

				
			default:
				break;
			}
		}
	};
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//去掉status bar
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
		
		setContentView(R.layout.camera);
		
		saveImages = (ArrayList<SaveImage>) getIntent().getSerializableExtra("saveImages");
		if(saveImages == null)
		{
			saveImages = new ArrayList<SaveImage>();
		}
		
		initView();
		
	}

	
	
	
	private void initView()
	{
		//上边
		rl_topmenu = (RelativeLayout) findViewById(R.id.rl_topmenu);
		iv_finish_top_camera = (ImageView) findViewById(R.id.iv_finish_top_camera);
		tv_mode_top_camera = (TextView) findViewById(R.id.tv_mode_top_camera);
		btn_switch_top_camera = (ImageView) findViewById(R.id.btn_switch_top_camera);
		btn_flash_top_camera = (ImageView) findViewById(R.id.btn_flash_top_camera);
		
		
		//下边
		rl_bottommenu = (RelativeLayout) findViewById(R.id.rl_bottommenu);
		iv_mode_bottom_camera = (ImageView) findViewById(R.id.iv_mode_bottom_camera);
		btn_shutter_bottom_camera = (ImageButton) findViewById(R.id.btn_shutter_bottom_camera);
		tv_gallery_bottom_camera = (TextView) findViewById(R.id.tv_gallery_bottom_camera);
		
		
		//左边
		rl_leftmenu = (RelativeLayout) findViewById(R.id.rl_leftmenu);
		iv_finish_left_camera = (ImageView) findViewById(R.id.iv_finish_left_camera);
		tv_mode_left_camera = (TextView) findViewById(R.id.tv_mode_left_camera);
		btn_switch_left_camera = (ImageView) findViewById(R.id.btn_switch_left_camera);
		btn_flash_left_camera = (ImageView) findViewById(R.id.btn_flash_left_camera);
		
		
		//右边
		rl_rightmenu = (RelativeLayout) findViewById(R.id.rl_rightmenu);
		iv_mode_right_camera = (ImageView) findViewById(R.id.iv_mode_right_camera);
		btn_shutter_right_camera = (ImageButton) findViewById(R.id.btn_shutter_right_camera);
		tv_gallery_right_camera = (TextView) findViewById(R.id.tv_gallery_right_camera);
		
		//镜头
		iv_normalbg_camera = (ImageView) findViewById(R.id.iv_normalbg_camera);
		rl_filmbg_camera = (RelativeLayout) findViewById(R.id.rl_filmbg_camera);
		mContainer = (CameraContainer) findViewById(R.id.container);
		mSaveRoot = "test";
		mContainer.setRootPath(mSaveRoot);
		mContainer.setZoom(0);
		
		progressbar_rotate = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.rotate_imageview);
		rl_pb_camera = (RelativeLayout) findViewById(R.id.rl_pb_camera);
		pb_take_camera = (ProgressBar) findViewById(R.id.pb_take_camera);
		
		
		//点击事件
		iv_finish_top_camera.setOnClickListener(this);
		btn_switch_top_camera.setOnClickListener(this);
		btn_flash_top_camera.setOnClickListener(this);
		
		iv_mode_bottom_camera.setOnClickListener(this);
		btn_shutter_bottom_camera.setOnClickListener(this);
		tv_gallery_bottom_camera.setOnClickListener(this);
		
		iv_finish_left_camera.setOnClickListener(this);
		btn_switch_left_camera.setOnClickListener(this);
		btn_flash_left_camera.setOnClickListener(this);
		
		iv_mode_right_camera.setOnClickListener(this);
		btn_shutter_right_camera.setOnClickListener(this);
		tv_gallery_right_camera.setOnClickListener(this);
		
		
		rl_filmbg_camera.setVisibility(View.VISIBLE);
		rl_filmbg_camera.setAlpha(40);
		rl_filmbg_camera.bringToFront();
		iv_normalbg_camera.setVisibility(View.GONE);
		btn_shutter_bottom_camera.setClickable(false);
	
	}
	
	
	
	
	
	@SuppressLint({ "RtlHardcoded", "ResourceAsColor" })
	@Override
	public void onClick(View view)
	{
		// TODO Auto-generated method stub
		switch (view.getId())
		{
		//上边
		case R.id.iv_finish_top_camera:
			finish();
			break;
		
		case R.id.btn_switch_top_camera:
			mContainer.switchCamera();
			break;
			
		case R.id.btn_flash_top_camera:
			if (mContainer.getFlashMode() == FlashMode.ON)
			{
				mContainer.setFlashMode(FlashMode.OFF);
				btn_flash_top_camera.setImageResource(R.drawable.btn_flash_off);
			} 
			else if (mContainer.getFlashMode() == FlashMode.OFF)
			{
				mContainer.setFlashMode(FlashMode.AUTO);
				btn_flash_top_camera.setImageResource(R.drawable.btn_flash_auto);
			} 
			else if (mContainer.getFlashMode() == FlashMode.AUTO)
			{
				mContainer.setFlashMode(FlashMode.TORCH);
				btn_flash_top_camera.setImageResource(R.drawable.btn_flash_torch);
			} 
			else if (mContainer.getFlashMode() == FlashMode.TORCH)
			{
				mContainer.setFlashMode(FlashMode.ON);
				btn_flash_top_camera.setImageResource(R.drawable.btn_flash_on);
			}
			break;
			
		
		//下边
		case R.id.iv_mode_bottom_camera:	
			inflater = getLayoutInflater();
			popView = inflater.inflate(R.layout.popupwindow_mode, null);
			ImageView iv_normal_popupwindow = (ImageView) popView.findViewById(R.id.iv_normal_popupwindow);
			TextView tv_normal_popupwindow = (TextView) popView.findViewById(R.id.tv_normal_popupwindow);
			ImageView iv_film_popupwindow = (ImageView) popView.findViewById(R.id.iv_film_popupwindow);
			TextView tv_film_popupwindow = (TextView) popView.findViewById(R.id.tv_film_popupwindow);
			if(mode.equals("普通"))
			{
				iv_normal_popupwindow.setImageResource(R.drawable.normal_n);
				tv_normal_popupwindow.setTextColor(getApplicationContext().getResources().getColor(R.color.blue_light));
				iv_film_popupwindow.setImageResource(R.drawable.film);
				tv_film_popupwindow.setTextColor(getApplicationContext().getResources().getColor(R.color.white_deep));
			}
			else if (mode.equals("大片")) 
			{
				iv_normal_popupwindow.setImageResource(R.drawable.normal);
				tv_normal_popupwindow.setTextColor(getApplicationContext().getResources().getColor(R.color.white_deep));
				iv_film_popupwindow.setImageResource(R.drawable.film_n);
				tv_film_popupwindow.setTextColor(getApplicationContext().getResources().getColor(R.color.blue_light));
			}
			popView.bringToFront();
			popupWindow = new PopupWindow(popView, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			
			popupWindow.setFocusable(true);// menu菜单获得焦点 如果没有获得焦点menu菜单中的控件事件无法响应
			popupWindow.setTouchable(true);
			popupWindow.setOutsideTouchable(true);
			popupWindow.setBackgroundDrawable(new BitmapDrawable());
			popupWindow.setTouchInterceptor(new OnTouchListener()
			{
				@Override
				public boolean onTouch(View v, MotionEvent event)
				{
					// TODO Auto-generated method stub
					if (event.getAction() == MotionEvent.ACTION_OUTSIDE) 
					{
						popupWindow.dismiss();
						return true;
					}
					
					return false;
				}
			});
			
			popView.findViewById(R.id.ll_film_popup).setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					mode = "大片";
					tv_mode_top_camera.setText(mode);
					popupWindow.dismiss();
					
					//能点击下边，说明现在是竖屏，大片模式必须横屏才能使用，不可以，所以显示屏蔽层
					rl_filmbg_camera.setVisibility(View.VISIBLE);
					rl_filmbg_camera.bringToFront();
					iv_normalbg_camera.setVisibility(View.GONE);
					btn_shutter_bottom_camera.setClickable(false);
					
				}
			});
			
			popView.findViewById(R.id.ll_normal_popup).setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					mode = "普通";
					tv_mode_top_camera.setText(mode);
					popupWindow.dismiss();
					
					//能点击下边，说明现在是竖屏，普通模式必须竖屏，当前可以，只显示遮挡层
					int width = ScreenUtils.getScreenWidth(CameraAty.this);
					int contentHeight = ScreenUtils.getScreenHeight(CameraAty.this) - DensityUtils.dip2px(getApplicationContext(), 120);
					int zhefu_normal_height = contentHeight - width;
					LayoutParams layoutParams = iv_normalbg_camera.getLayoutParams();
					layoutParams.width = width;
					layoutParams.height = zhefu_normal_height;
					iv_normalbg_camera.setLayoutParams(layoutParams);
					iv_normalbg_camera.setVisibility(View.VISIBLE);
					iv_normalbg_camera.bringToFront();
					rl_filmbg_camera.setVisibility(View.GONE);

					btn_shutter_bottom_camera.setClickable(true);
					
				}
			});
			
			popupWindow.showAtLocation(view, Gravity.LEFT | Gravity.BOTTOM, 5, 150);
			popupWindow.update();
			break;
			
		case R.id.btn_shutter_bottom_camera:
			btn_shutter_bottom_camera.setClickable(false);
			mContainer.takePicture(this);
			break;
			
		case R.id.tv_gallery_bottom_camera:
			Intent intent = new Intent(CameraAty.this, SelectPictureActivity.class);
			intent.putExtra("saveImages", saveImages);
			intent.putExtra("mode", mode);
			startActivity(intent);
			finish();
			break;
			
			
		//左边
		case R.id.iv_finish_left_camera:
			finish();
			break;
		
		case R.id.btn_switch_left_camera:
			mContainer.switchCamera();
			break;
			
		case R.id.btn_flash_left_camera:
			if (mContainer.getFlashMode() == FlashMode.ON)
			{
				mContainer.setFlashMode(FlashMode.OFF);
				btn_flash_left_camera.setImageResource(R.drawable.btn_flash_off);
			} 
			else if (mContainer.getFlashMode() == FlashMode.OFF)
			{
				mContainer.setFlashMode(FlashMode.AUTO);
				btn_flash_left_camera.setImageResource(R.drawable.btn_flash_auto);
			} 
			else if (mContainer.getFlashMode() == FlashMode.AUTO)
			{
				mContainer.setFlashMode(FlashMode.TORCH);
				btn_flash_left_camera.setImageResource(R.drawable.btn_flash_torch);
			} 
			else if (mContainer.getFlashMode() == FlashMode.TORCH)
			{
				mContainer.setFlashMode(FlashMode.ON);
				btn_flash_left_camera.setImageResource(R.drawable.btn_flash_on);
			}
			break;	
			
			
		//右边
		case R.id.iv_mode_right_camera:	
			inflater = getLayoutInflater();
			popView = inflater.inflate(R.layout.popupwindow_mode, null);
			ImageView iv_normal_popupwindow2 = (ImageView) popView.findViewById(R.id.iv_normal_popupwindow);
			TextView tv_normal_popupwindow2 = (TextView) popView.findViewById(R.id.tv_normal_popupwindow);
			ImageView iv_film_popupwindow2 = (ImageView) popView.findViewById(R.id.iv_film_popupwindow);
			TextView tv_film_popupwindow2 = (TextView) popView.findViewById(R.id.tv_film_popupwindow);
			if(mode.equals("普通"))
			{
				iv_normal_popupwindow2.setImageResource(R.drawable.normal_n);
				tv_normal_popupwindow2.setTextColor(getApplicationContext().getResources().getColor(R.color.blue_light));
				iv_film_popupwindow2.setImageResource(R.drawable.film);
				tv_film_popupwindow2.setTextColor(getApplicationContext().getResources().getColor(R.color.white_deep));
			}
			else if (mode.equals("大片")) 
			{
				iv_normal_popupwindow2.setImageResource(R.drawable.normal);
				tv_normal_popupwindow2.setTextColor(getApplicationContext().getResources().getColor(R.color.white_deep));
				iv_film_popupwindow2.setImageResource(R.drawable.film_n);
				tv_film_popupwindow2.setTextColor(getApplicationContext().getResources().getColor(R.color.blue_light));
			}
			popView.bringToFront();
			popupWindow = new PopupWindow(popView, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			
			popupWindow.setFocusable(true);// menu菜单获得焦点 如果没有获得焦点menu菜单中的控件事件无法响应
			popupWindow.setTouchable(true);
			popupWindow.setOutsideTouchable(true);
			popupWindow.setBackgroundDrawable(new BitmapDrawable());
			popupWindow.setTouchInterceptor(new OnTouchListener()
			{
				@Override
				public boolean onTouch(View v, MotionEvent event)
				{
					// TODO Auto-generated method stub
					if (event.getAction() == MotionEvent.ACTION_OUTSIDE) 
					{
						popupWindow.dismiss();
						return true;
					}
					
					return false;
				}
			});
			
			popView.findViewById(R.id.ll_film_popup).setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					mode = "大片";
					tv_mode_left_camera.setText(mode);
					popupWindow.dismiss();
					
					//能点击右边，说明现在是横屏，大片模式必须横屏才能使用，当前可以，不显示屏蔽层和遮挡层
					rl_filmbg_camera.setVisibility(View.GONE);
					iv_normalbg_camera.setVisibility(View.GONE);
					btn_shutter_right_camera.setClickable(true);
					
				}
			});
			
			popView.findViewById(R.id.ll_normal_popup).setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					mode = "普通";
					tv_mode_left_camera.setText(mode);
					popupWindow.dismiss();
					
					//能点击右边，说明现在是横屏，普通模式必须竖屏屏才能使用，当前不可以，显示屏蔽层
					iv_normalbg_camera.setVisibility(View.GONE);
					rl_filmbg_camera.setVisibility(View.VISIBLE);
					rl_filmbg_camera.bringToFront();

					btn_shutter_right_camera.setClickable(false);
				}
			});
			popupWindow.showAtLocation(view, Gravity.RIGHT | Gravity.TOP, 150, 60);
			popupWindow.update();
			break;
			
		case R.id.btn_shutter_right_camera:
			btn_shutter_right_camera.setClickable(false);
			mContainer.takePicture(this);
			break;
			
		case R.id.tv_gallery_right_camera:
			Intent intent2 = new Intent(CameraAty.this, SelectPictureActivity.class);
			intent2.putExtra("saveImages", saveImages);
			intent2.putExtra("mode", mode);
			startActivity(intent2);
			finish();
			break;
			
			
		default:
			break;
			
		}
	}

	

	
	@Override
	public void onTakePictureEnd(Bitmap thumBitmap)
	{
		rl_pb_camera.setVisibility(View.VISIBLE);
		rl_pb_camera.bringToFront();
		pb_take_camera.clearAnimation();
		pb_take_camera.startAnimation(progressbar_rotate);
		
		btn_shutter_bottom_camera.setClickable(true);
		btn_shutter_right_camera.setClickable(true);
		
	}

	
	
	
	@Override
	public void onAnimtionEnd(Bitmap bm, boolean isVideo)
	{
		if (bm != null)
		{
			if(mode.equals("普通"))
			{
				int width = bm.getWidth();
				int height = bm.getHeight();
				bitmap = ImageUtils.convert(bm, width, height);
				bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, width);
			}
			else if(mode.equals("大片"))
			{
				bitmap = bm;
			}
			
			
			/*iv_test.setVisibility(View.VISIBLE);
			iv_test.bringToFront();
			iv_test.setImageBitmap(thumBitmap);*/
			
			Thread thread = new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					// TODO Auto-generated method stub
					long time = System.currentTimeMillis();
					fileName = Long.toString(time)+".jpg";
					ImageUtils.saveBitmap(bitmap, Constants.path, fileName, 100);
					
					SaveImage currentSaveImage = new SaveImage();
					currentSaveImage.saveImagePath = Constants.path + fileName;
					currentSaveImage.subtitle = "";
					currentSaveImage.isZhefu = false;
					currentSaveImage.yinjiBitmap = null;
					currentSaveImage.x = 100;
					currentSaveImage.y = 100;
					
					saveImages.add(currentSaveImage);
					
					handler.sendEmptyMessage(Constants.SAVETAKEDPIC);
				}
			});
			thread.start();
		}
	}
	
	

	

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		//大片模式，横屏
		if(newConfig.orientation ==Configuration.ORIENTATION_LANDSCAPE)
		{
			rl_topmenu.setVisibility(View.GONE);
			rl_bottommenu.setVisibility(View.GONE);
			
			rl_leftmenu.setVisibility(View.VISIBLE);
			rl_rightmenu.setVisibility(View.VISIBLE);
			
			if(mode.equals("大片"))
			{
				rl_filmbg_camera.setVisibility(View.GONE);
				iv_normalbg_camera.setVisibility(View.GONE);
				btn_shutter_right_camera.setClickable(true);
			}
			else if (mode.equals("普通")) 
			{	
				iv_normalbg_camera.setVisibility(View.GONE);
				rl_filmbg_camera.setVisibility(View.VISIBLE);
				rl_filmbg_camera.bringToFront();

				btn_shutter_right_camera.setClickable(false);
			}
			
		}
		
		
		//普通模式，竖屏
		else if(newConfig.orientation ==Configuration.ORIENTATION_PORTRAIT)
		{
			rl_topmenu.setVisibility(View.VISIBLE);
			rl_bottommenu.setVisibility(View.VISIBLE);
			
			rl_leftmenu.setVisibility(View.GONE);
			rl_rightmenu.setVisibility(View.GONE);
			
			
			
			if(mode.equals("大片"))
			{
				rl_filmbg_camera.setVisibility(View.VISIBLE);
				rl_filmbg_camera.bringToFront();
				iv_normalbg_camera.setVisibility(View.GONE);
				btn_shutter_bottom_camera.setClickable(false);
			}
			else if (mode.equals("普通")) 
			{	
				iv_normalbg_camera.setVisibility(View.VISIBLE);
				iv_normalbg_camera.bringToFront();
				rl_filmbg_camera.setVisibility(View.GONE);

				btn_shutter_bottom_camera.setClickable(true);
			}
			
		}
		
		
	}




	
	
	
	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		
		if(bitmap != null)
		{
			if(!bitmap.isRecycled())
			{
				bitmap.recycle();
				bitmap = null;
			}
		}
	}
	
	
	
	
	
	
	
}