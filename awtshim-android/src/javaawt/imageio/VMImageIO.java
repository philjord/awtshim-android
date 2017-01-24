package javaawt.imageio;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javaawt.image.VMBufferedImage;
import javaawt.image.BufferedImage;


public class VMImageIO extends ImageIO
{

	public static BufferedImage read(InputStream in)
	{
		Bitmap bm = BitmapFactory.decodeStream(in);
		//Bitmap.Config c = bm.getConfig(); //ARGB_8888 looks like all of them



		return new VMBufferedImage(bm);

	}

	public static void write(BufferedImage imageBaseLightImage, String string, File imageFile)
	{
		throw new UnsupportedOperationException();
		//try
//		{
		//javax.imageio.ImageIO.write((RenderedImage) imageBaseLightImage.getDelegate(), string, imageFile);
//		}
		//	catch (IOException ex)
//		{
		//		ex.printStackTrace();
//		}

	}
}
