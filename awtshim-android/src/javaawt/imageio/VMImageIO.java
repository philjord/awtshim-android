package javaawt.imageio;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javaawt.image.VMBufferedImage;
import javaawt.image.BufferedImage;

public class VMImageIO extends ImageIO
{

	public static BufferedImage read(InputStream in)
	{
		//Possibly I'm getting 565 formats back?
		//BitmapFactory.Options bitmapLoadingOptions = new BitmapFactory.Options();
		//bitmapLoadingOptions.inPreferredConfig = Bitmap.Config.RGB_565;

		Bitmap bm = BitmapFactory.decodeStream(in);//,null,bitmapLoadingOptions);
		Bitmap.Config c = bm.getConfig(); //ARGB_8888 looks like all of them

		if (c != Bitmap.Config.ARGB_8888)
			System.out.println("retrn Bitmap.Config not expected : " + c);

		//In fact if I like this TextureLoader could go with BufferedImage.TYPE_USHORT_565_RGB

		//ImageCompoennt would need a new value for 565
		//ImageComponentRetained
		// and Texture too  
		//pipeline updateTexture2DImage

		//Note that the opaque tests in sweethome, including the pixels chack for non opaue would need to be fixed
		// in fact the pixel test could probably be short circuited easily

		return new VMBufferedImage(bm);

	}

	public static BufferedImage read(String url) throws MalformedURLException, IOException
	{
		InputStream is = (InputStream) new URL(url).getContent();
		return read(is);
	}

	public static void write(BufferedImage image, String type, File imageFile)
	{

		Bitmap.CompressFormat format = Bitmap.CompressFormat.PNG;
		// PNG is a lossless format, the compression factor (100) is ignored

		if (type.equalsIgnoreCase("jpg") || type.equalsIgnoreCase("jepg"))
			format = Bitmap.CompressFormat.JPEG;

		Bitmap bmp = (Bitmap) image.getDelegate();

		FileOutputStream out = null;
		try
		{
			out = new FileOutputStream(imageFile);
			bmp.compress(format, 100, out); // bmp is your Bitmap instance				
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (out != null)
				{
					out.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}
}
