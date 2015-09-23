package com.lucifer.razercamera.imagefilter;


import com.lucifer.razercamera.utils.ImageUtils;

import android.graphics.Bitmap;


//�ڰ�
public class BlackWhiteFilter implements IImageFilter
{

	@Override
	public Image process(Image image)
	{
		// TODO Auto-generated method stub
		int width = image.getWidth();
		int height = image.getHeight();
		int R, G, B, pixel;
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				R = image.getRComponent(x, y); // ��ȡRGB��ԭɫ
				G = image.getGComponent(x, y);
				B = image.getBComponent(x, y);

				// R = |g �C b + g + r| * r / 256;
				pixel = G - B + G + R;
				if (pixel < 0)
					pixel = -pixel;
				pixel = pixel * R / 256;
				if (pixel > 255)
					pixel = 255;
				R = pixel;

				// G = |b �C g + b + r| * r / 256;
				pixel = B - G + B + R;
				if (pixel < 0)
					pixel = -pixel;
				pixel = pixel * R / 256;
				if (pixel > 255)
					pixel = 255;
				G = pixel;

				// B = |b �C g + b + r| * g / 256;
				pixel = B - G + B + R;
				if (pixel < 0)
					pixel = -pixel;
				pixel = pixel * G / 256;
				if (pixel > 255)
					pixel = 255;
				B = pixel;
				image.setPixelColor(x, y, R, G, B);
			}
		}
		Bitmap bitmap = image.getDestImage();
		bitmap = ImageUtils.toGrayscale(bitmap); // ͼƬ�ҶȻ�����
		image = new Image(bitmap);
		return image;
	}

}
