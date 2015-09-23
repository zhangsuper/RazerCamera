package com.lucifer.razercamera.activity;

import java.io.File;
import java.util.List;

import com.lucifer.razercamera.MainActivity;
import com.lucifer.razercamera.R;
import com.lucifer.razercameralib.FileOperateUtil;
import com.lucifer.razercameralib.camera.CameraContainer;
import com.lucifer.razercameralib.camera.CameraContainer.TakePictureListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


/**
 * 录像界面功能：关闭界面，切换摄像头；对Camera的滤镜功能；录像，记录录像时间；找到录像文件列表，点击可以查看。
 * @author lawliet
 */
public class VideoActivity extends Activity implements OnClickListener, TakePictureListener
{
	public final static String TAG = "CameraAty";
	private boolean mIsRecordMode = false;
	private String mSaveRoot;
	private CameraContainer mContainer;
	// private FilterImageView mThumbView;
	// private ImageButton mCameraShutterButton;
	private ImageButton mRecordShutterButton;
	// private ImageView mFlashView;
	// private ImageButton mSwitchModeButton;
	private ImageView mSwitchCameraView;
	// private ImageView mSettingView;
	// private ImageView mVideoIconView;
	// private View mHeaderBar;
	RelativeLayout rl_playrecord_videorecord;
	
	
	
	private boolean isRecording = false;

	
	
	Handler handler = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			stopRecord();
		}

	};

	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.videorecord);

		// mHeaderBar=findViewById(R.id.camera_header_bar);
		mContainer = (CameraContainer) findViewById(R.id.container_videorecord);
		// mThumbView=(FilterImageView)findViewById(R.id.btn_thumbnail);
		// mVideoIconView=(ImageView)findViewById(R.id.videoicon);
		// mCameraShutterButton=(ImageButton)findViewById(R.id.btn_shutter_camera);
		mRecordShutterButton = (ImageButton) findViewById(R.id.btn_shutter_record);
		// mSwitchCameraView=(ImageView)findViewById(R.id.btn_switch_camera);
		// mFlashView=(ImageView)findViewById(R.id.btn_flash_mode);
		// mSwitchModeButton=(ImageButton)findViewById(R.id.btn_switch_mode);
		// mSettingView=(ImageView)findViewById(R.id.btn_other_setting);

		rl_playrecord_videorecord = (RelativeLayout) findViewById(R.id.rl_playrecord_videorecord);
		
		
		// mThumbView.setOnClickListener(this);
		// mCameraShutterButton.setOnClickListener(this);
		mRecordShutterButton.setOnClickListener(this);
		// mFlashView.setOnClickListener(this);
		// mSwitchModeButton.setOnClickListener(this);
		// mSwitchCameraView.setOnClickListener(this);
		// mSettingView.setOnClickListener(this);
		rl_playrecord_videorecord.setOnClickListener(this);
		
		
		mSaveRoot = "test";
		mContainer.setRootPath(mSaveRoot);
		// initThumbnail();
	}

	
	
	/**
	 * 加载缩略图
	 */
	// private void initThumbnail() {
	// String thumbFolder=FileOperateUtil.getFolderPath(this,
	// FileOperateUtil.TYPE_THUMBNAIL, mSaveRoot);
	// List<File> files=FileOperateUtil.listFiles(thumbFolder, ".jpg");
	// if(files!=null&&files.size()>0){
	// Bitmap
	// thumbBitmap=BitmapFactory.decodeFile(files.get(0).getAbsolutePath());
	// if(thumbBitmap!=null){
	// mThumbView.setImageBitmap(thumbBitmap);
	// //视频缩略图显示播放图案
	// if(files.get(0).getAbsolutePath().contains("video")){
	// mVideoIconView.setVisibility(View.VISIBLE);
	// }else {
	// mVideoIconView.setVisibility(View.GONE);
	// }
	// }
	// }else {
	// mThumbView.setImageBitmap(null);
	// mVideoIconView.setVisibility(View.VISIBLE);
	// }
	//
	// }
	
	
	
	

	@Override
	public void onClick(View view)
	{
		// TODO Auto-generated method stub
		switch (view.getId())
		{
		// case R.id.btn_shutter_camera:
		// mCameraShutterButton.setClickable(false);
		// mContainer.takePicture(this);
		// break;
		// case R.id.btn_thumbnail:
		// startActivity(new Intent(this,AlbumAty.class));
		// break;
		// case R.id.btn_flash_mode:
		// if(mContainer.getFlashMode()==FlashMode.ON){
		// mContainer.setFlashMode(FlashMode.OFF);
		// mFlashView.setImageResource(R.drawable.btn_flash_off);
		// }else if (mContainer.getFlashMode()==FlashMode.OFF) {
		// mContainer.setFlashMode(FlashMode.AUTO);
		// mFlashView.setImageResource(R.drawable.btn_flash_auto);
		// }
		// else if (mContainer.getFlashMode()==FlashMode.AUTO) {
		// mContainer.setFlashMode(FlashMode.TORCH);
		// mFlashView.setImageResource(R.drawable.btn_flash_torch);
		// }
		// else if (mContainer.getFlashMode()==FlashMode.TORCH) {
		// mContainer.setFlashMode(FlashMode.ON);
		// mFlashView.setImageResource(R.drawable.btn_flash_on);
		// }
		// break;
		// case R.id.btn_switch_mode:
		// if(mIsRecordMode){
		// // mSwitchModeButton.setImageResource(R.drawable.ic_switch_camera);
		// mCameraShutterButton.setVisibility(View.VISIBLE);
		// mRecordShutterButton.setVisibility(View.GONE);
		// //拍照模式下显示顶部菜单
		// // mHeaderBar.setVisibility(View.VISIBLE);
		// mIsRecordMode=false;
		// mContainer.switchMode(0);
		// stopRecord();
		// }
		// else {
		// // mSwitchModeButton.setImageResource(R.drawable.ic_switch_video);
		// mCameraShutterButton.setVisibility(View.GONE);
		// mRecordShutterButton.setVisibility(View.VISIBLE);
		// //录像模式下隐藏顶部菜单
		// // mHeaderBar.setVisibility(View.GONE);
		// mIsRecordMode=true;
		// mContainer.switchMode(5);
		// }
		// break;
		case R.id.btn_shutter_record:
			if (!isRecording)
			{
				isRecording = mContainer.startRecord();
				if (isRecording)
				{
					mRecordShutterButton
							.setBackgroundResource(R.drawable.btn_shutter_recording);
				}

				handler.sendEmptyMessageDelayed(111, 9000);
			} else
			{
				stopRecord();
			}
			break;
			
			
		case R.id.rl_playrecord_videorecord:
			
			break;
			
			
		// case R.id.btn_switch_camera:
		// mContainer.switchCamera();
		// break;
		// case R.id.btn_other_setting:
		// mContainer.setWaterMark();
		// break;
		default:
			break;
			
		}
	}

	
	
	
	public void stopRecord()
	{
		mContainer.stopRecord(this);
		isRecording = false;
		mRecordShutterButton
				.setBackgroundResource(R.drawable.btn_shutter_record);
	}
	
	
	
	

	@Override
	public void onTakePictureEnd(Bitmap thumBitmap)
	{
		// mCameraShutterButton.setClickable(true);
	}

	
	
	
	@Override
	public void onAnimtionEnd(Bitmap bm, boolean isVideo)
	{
		if (bm != null)
		{
			// 生成缩略图
			Bitmap thumbnail = ThumbnailUtils.extractThumbnail(bm, 213, 213);
			// mThumbView.setImageBitmap(thumbnail);
			// if(isVideo)
			// mVideoIconView.setVisibility(View.VISIBLE);
			// else {
			// mVideoIconView.setVisibility(View.GONE);
			// }
			Toast.makeText(getApplicationContext(), "录像结束了", 1000).show();
		}
	}

	
	
	
	
}
