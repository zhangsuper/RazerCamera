package com.lucifer.razercamera;

import com.lucifer.razercamera.activity.CameraAty;
import com.lucifer.razercamera.activity.VideoActivity;
import com.lucifer.razercamera.widget.RippleView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


/**
 * 产品改进：
 * 1.图像处理效果的一键实现，一个操作能够实现多步操作（如裁剪、滤镜、边框、文字等），达到效率和效果都要好。
 * 2.对已处理图像的还原操作。
 * 3.加入人脸识别功能，从而能够实现更方便的在五官上自动完成磨皮、祛斑、瘦脸、美妆功能。
 * 4.自动抠图功能：只需要选择一个区域就可以根据色彩信息抠出主体轮廓。
 * 5.全景拍摄。
 * 6.图像操作时的拖动放大，放大后进行处理。
 * 7.强度调整滑竿先隐藏，用到的时候才出现。
 * 8.减少图像处理，尽量让功能复合，只给用户几种处理的主题。
 * @author lawliet
 *
 */
public class MainActivity extends Activity implements OnClickListener
{
	RippleView rv_camera_main, rv_video_main;
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.aty_main);
		
		rv_camera_main = (RippleView) findViewById(R.id.rv_camera_main);
		rv_video_main = (RippleView) findViewById(R.id.rv_video_main);
		
		rv_camera_main.setOnClickListener(this);
		rv_video_main.setOnClickListener(this);
	}


	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.rv_camera_main:
			Intent intent1 = new Intent(MainActivity.this, CameraAty.class);
			startActivity(intent1);
			break;
			
		case R.id.rv_video_main:
			Intent intent2 = new Intent(MainActivity.this, VideoActivity.class);
			startActivity(intent2);
			break;

		default:
			break;
		}
	}

}
