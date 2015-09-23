package com.lucifer.razercamera.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.utils.PreferenceHelper;

import com.lucifer.razercamera.R;
import com.lucifer.razercamera.adapter.GridImageAdapter;
import com.lucifer.razercamera.common.APIContext;
import com.lucifer.razercamera.common.AppContext;
import com.lucifer.razercamera.utils.ImageUtils;
import com.lucifer.razercamera.utils.StringUtil;
import com.lucifer.razercamera.widget.togglebutton.togglebutton.ToggleButton;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



@SuppressLint("NewApi")
public class PostArticleActivity extends Activity implements OnClickListener
{
	private Context mContext;

	private RelativeLayout rl_back_activity_postarticle, rl_post_postarticle;

	GridView gv_pics_postartical;

	private EditText et_postarticle;

	TextView tv_linshibaocun_postarticle;

	TextView tv_weizhi_postarticle, tv_deslocation_postarticle;

	ToggleButton tb_sign_postarticle;
	ToggleButton tb_savelocal_postarticle;

	//接收到所有图片路径，用于合并多个图片
	ArrayList<String> selectedPaths = new ArrayList<String>();
	//所有缩略图
	ArrayList<Bitmap> horizontalBitmaps = new ArrayList<Bitmap>();
	GridImageAdapter gridImageAdapter = null;

	// 当前内存中的东西
	String mobile;
	Bitmap currentThumbnail;
	Bitmap currentBitmap;
	String nickName;
	//上传的所有图像文件信息
	StringBuilder picMeta = new StringBuilder();
	Bitmap logoBitmap;
	
	String paths;
	String content;
	

	
	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			// TODO Auto-generated method stub
			
			switch (msg.what)
			{
			case APIContext.SaveTempArticle:
				Toast.makeText(getApplicationContext(), "保存临时文件成功", 1000)
						.show();
				break;
				
			case APIContext.POSTSUC:
				AppContext.getInstance().closeDialog();
				Toast.makeText(getApplicationContext(), "上传成功", 1000)
				.show();
				PreferenceHelper.clean(getApplicationContext(), APIContext.PREFERENCE_NAME_TEMPARTICLE);
				if(!tb_savelocal_postarticle.getToggleStatus())
				{
					new Thread(new Runnable()
					{
						@Override
						public void run()
						{
							// TODO Auto-generated method stub
							for(int i=0;i<selectedPaths.size();i++)
							{
								File itemFile = new File(selectedPaths.get(i));
								if(itemFile.exists())
								{
									try
									{
										itemFile.delete();
										
									} catch (Exception e)
									{
										// TODO: handle exception
										e.printStackTrace();
									}
									
								}
								
							}
						}
					}).start();
				}

				finish();
				break;
				
			case APIContext.POSTFAL:
				AppContext.getInstance().closeDialog();
				Toast.makeText(getApplicationContext(), "上传失败", 1000)
				.show();
				break;

			default:
				break;
			}

		}

	};

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.postarticle);

		mContext = this;

		//如果已经有临时保存的图片和文字
		paths = PreferenceHelper.readString(getApplicationContext(), APIContext.PREFERENCE_NAME_TEMPARTICLE, APIContext.PREFERENCE_KEY_PATHS);
		content = PreferenceHelper.readString(getApplicationContext(), APIContext.PREFERENCE_NAME_TEMPARTICLE, APIContext.PREFERENCE_KEY_CONTENT);
		if(StringUtil.isStringAvailable(paths) && StringUtil.isStringAvailable(content))
		{
			String[] pathStrings = paths.split(",");
			for(int i = 0;i<pathStrings.length;i++)
			{
				selectedPaths.add(pathStrings[i]);
			}
		}
		//否则用Intent传过来的图片地址
		else 
		{
			selectedPaths = getIntent().getStringArrayListExtra("toPostList");
		}
		
		
		
		for(int i=0;i<selectedPaths.size();i++)
		{
			// 生成所有缩略图，防止多个大图加入导致OOM
			currentBitmap = ImageUtils.getImageFromLocal(selectedPaths.get(i));
			currentThumbnail = ThumbnailUtils.extractThumbnail(currentBitmap,
					80, 80);
			horizontalBitmaps.add(currentThumbnail);
			
		}
		
		mobile = PreferenceHelper.readString(mContext, APIContext.PREFERENCE_NAME_USER, APIContext.PREFERENCE_KEY_MOBILE, "18455550000");
		nickName = PreferenceHelper.readString(mContext, APIContext.PREFERENCE_NAME_USER, APIContext.PREFERENCE_KEY_NICKNAME, "nini");
		logoBitmap = ImageUtils.drawableToBitmapByBD(getResources().getDrawable(R.drawable.shuiyin));
		
		initView();

	}

	
	
	public void initView()
	{
		rl_back_activity_postarticle = (RelativeLayout) findViewById(R.id.rl_back_activity_postarticle);
		rl_post_postarticle = (RelativeLayout) findViewById(R.id.rl_post_postarticle);
		
		gv_pics_postartical = (GridView) findViewById(R.id.gv_pics_postartical);

		et_postarticle = (EditText) findViewById(R.id.et_postarticle);

		tv_linshibaocun_postarticle = (TextView) findViewById(R.id.tv_linshibaocun_postarticle);

		tv_weizhi_postarticle = (TextView) findViewById(R.id.tv_weizhi_postarticle);
		tv_deslocation_postarticle = (TextView) findViewById(R.id.tv_deslocation_postarticle);

		tb_sign_postarticle = (ToggleButton) findViewById(R.id.tb_sign_postarticle);
		tb_savelocal_postarticle = (ToggleButton) findViewById(R.id.tb_savelocal_postarticle);

		// setOnClickListener
		rl_back_activity_postarticle.setOnClickListener(this);
		rl_post_postarticle.setOnClickListener(this);

		
		gridImageAdapter = new GridImageAdapter(mContext, horizontalBitmaps);
		gv_pics_postartical.setAdapter(gridImageAdapter);

		et_postarticle.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s)
			{
				// TODO Auto-generated method stub
				int number = 140 - s.length();
			}
		});

		tv_linshibaocun_postarticle.setOnClickListener(this);

		tv_deslocation_postarticle.setText("北京");
		
		if(StringUtil.isStringAvailable(paths) && StringUtil.isStringAvailable(content))
		{
			et_postarticle.setText(content);
		}
	}

	
	
	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();

		gridImageAdapter.notifyDataSetChanged();

	}

	
	
	
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.rl_back_activity_postarticle:
			finish();
			break;

		case R.id.rl_post_postarticle:
			// 发表接口，如果有添加水印签名，则在每幅图像上绘制水印签名。然后上传图片和文字。
			// 如果同时保存到本地为false，则删除掉本地保存的图片。
			AppContext.getInstance().showDialog(mContext);
			//写文件信息，然后文件合并
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					// TODO Auto-generated method stub
					try
					{
						if(tb_sign_postarticle.getToggleStatus())
						{
							//水印
							for(int i=0;i<selectedPaths.size();i++)
							{
								Bitmap itemBitmap = ImageUtils.getImageFromLocal(selectedPaths.get(i)); 
								
								//水印文字
								int fontSize = 0;
								int logoXSize = 0;
								int logoYSize = 0;
								int maxSize = Math.max(itemBitmap.getWidth(), itemBitmap.getHeight());
								if(maxSize >= 800)
								{
									fontSize = 30;
									logoXSize = 40;
									logoYSize = 40;
								}
								else if (maxSize >= 600) 
								{
									fontSize = 25;
									logoXSize = 35;
									logoYSize = 35;
								}
								else if (maxSize >= 400) 
								{
									fontSize = 20;
									logoXSize = 30;
									logoYSize = 30;
								}
								else if (maxSize >= 200) 
								{
									fontSize = 15;
									logoXSize = 20;
									logoYSize = 20;
								}
								else 
								{
									fontSize = 12;
									logoXSize = 15;
									logoYSize = 15;
								}
								
								if(nickName.length() > 10)
								{
									nickName = nickName.substring(0, 10);
								}
								Paint paint = new Paint();
								paint.setTextSize(fontSize);
								int shuiyinFontSize = (int) paint.measureText(nickName);
								
								itemBitmap = ImageUtils.drawText2Bitmap(itemBitmap, nickName, itemBitmap.getWidth()-shuiyinFontSize, itemBitmap.getHeight()-15, fontSize, R.color.white_deep);
							
								
								//水印图片
								logoBitmap = ImageUtils.zoomBitmap(logoBitmap, logoXSize, logoYSize);
								itemBitmap = ImageUtils.watermark(itemBitmap, logoBitmap, itemBitmap.getWidth()-40-shuiyinFontSize, itemBitmap.getHeight()-40);
								File itemFile = new File(selectedPaths.get(i));
								ImageUtils.saveBitmap(itemBitmap, itemFile.getParent().toString(), itemFile.getName().toString(), 80);
							}
						}
						
						ImageUtils.unionMultiImages(APIContext.unionFile, selectedPaths);
						
						postArticle();
						
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
			break;


		case R.id.tv_linshibaocun_postarticle:
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					// TODO Auto-generated method stub
					tempSaveArticles();
					handler.sendEmptyMessage(APIContext.SaveTempArticle);
				}
			}).start();
			break;

		default:
			break;
		}
	}

	
	
	// 临时保存帖子
	private void tempSaveArticles()
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < selectedPaths.size(); i++)
		{
			stringBuilder.append(selectedPaths.get(i));
			stringBuilder.append(",");
		}
		PreferenceHelper.write(getApplicationContext(),
				APIContext.PREFERENCE_NAME_TEMPARTICLE,
				APIContext.PREFERENCE_KEY_PATHS, stringBuilder.toString());

		if (et_postarticle.getText() != null
				&& !et_postarticle.getText().toString().equals(""))
		{
			PreferenceHelper.write(getApplicationContext(),
					APIContext.PREFERENCE_NAME_TEMPARTICLE,
					APIContext.PREFERENCE_KEY_CONTENT, et_postarticle.getText()
							.toString());
		}

	}
	
	
	
	
	
	private void postArticle()
	{
		String baseUrl = APIContext.base_url + APIContext.discuss
				+ APIContext.addDiscuss;
		KJHttp kjHttp = new KJHttp();
		HttpParams params = new HttpParams();
		params.put("mobile", mobile);
		params.put("content", et_postarticle.getText().toString());
		
		for(int i=0;i<selectedPaths.size();i++)
		{
			File file = new File(selectedPaths.get(i));
			
			//图像所有元数据
			String name = file.getName();
			long fileSize = file.length();
			picMeta.append(name + "," + fileSize + ",");
		}
		picMeta.deleteCharAt(picMeta.length()-1);
		params.put("picInfo", picMeta.toString());
		
		try
		{
			params.put("picture", new File(APIContext.unionFile));
			kjHttp.post(baseUrl, params, new HttpCallBack()
			{
				@Override
				public void onSuccess(String response)
				{
					// TODO Auto-generated method stub
					super.onSuccess(response);
					
					handler.sendEmptyMessage(APIContext.POSTSUC);
				}

				@Override
				public void onFailure(Throwable t, int errorNo, String strMsg)
				{
					// TODO Auto-generated method stub
					super.onFailure(t, errorNo, strMsg);
					
					handler.sendEmptyMessage(APIContext.POSTFAL);
				}

			});
			
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	
	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		
		System.gc();
	}
	
	
	
	
}
