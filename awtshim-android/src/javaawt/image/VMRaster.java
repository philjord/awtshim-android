package javaawt.image;

import android.support.v4.graphics.BitmapCompat;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class VMRaster implements Raster
{
	protected android.graphics.Bitmap delegate = null;

	public VMRaster(android.graphics.Bitmap delegate)
	{
		this.delegate = delegate;
	}

	public android.graphics.Bitmap getDelegate()
	{
		return delegate;
	}

	public void getDataElements(int w, int h, Object pixel)
	{
		throw new UnsupportedOperationException();//delegate.getDataElements(w, h, pixel);
	}

	public int getNumDataElements()
	{
		throw new UnsupportedOperationException();//	return delegate.getNumDataElements();
	}

	public int getTransferType()
	{
		throw new UnsupportedOperationException();//	return delegate.getTransferType();
	}

	public boolean goint = true;
	public DataBuffer getDataBuffer()
	{


		//Ok, so ImageComponent2DRetained has a getImage call...
		// which calls createBufferedImage
		// which creates a new BufferedImage, then calls ImageComponentRetained.copyByLine to get data into it!!
		// so it wants the returned Databuffer to actually be the backing int array of the image to write into it!
		// but obviously here I create a new buffer and copy my data into it, and that new buffer
		// backs nothing and is eventually dropped

		if(goint)
		{
			IntBuffer buffer1 = IntBuffer.allocate(BitmapCompat.getAllocationByteCount(delegate)/4);
			delegate.copyPixelsToBuffer(buffer1);
			return new VMDataBufferInt(buffer1);
		}
		else
		{
			//ByteBuffer buffer1 = ByteBuffer.allocate(delegate.getHeight() * delegate.getRowBytes() / 4);
			ByteBuffer buffer1 = ByteBuffer.allocate(BitmapCompat.getAllocationByteCount(delegate));
			delegate.copyPixelsToBuffer(buffer1);
			return new VMDataBufferByte(buffer1);
		}



	}
}
