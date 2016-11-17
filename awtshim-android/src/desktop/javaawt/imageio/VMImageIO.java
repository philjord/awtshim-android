package desktop.javaawt.imageio;

import java.io.IOException;
import java.io.InputStream;

import desktop.javaawt.image.VMBufferedImage;
import javaawt.image.BufferedImage;
import javaawt.imageio.ImageIO;

public class VMImageIO extends ImageIO
{

	public static BufferedImage read(InputStream in)
	{
		try
		{
			return new VMBufferedImage(javax.imageio.ImageIO.read(in));
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

}
