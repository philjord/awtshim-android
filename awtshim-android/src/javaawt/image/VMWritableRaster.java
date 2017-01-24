package javaawt.image;

import android.support.v4.graphics.BitmapCompat;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javaawt.image.WritableRaster;

public class VMWritableRaster extends VMRaster implements WritableRaster
{
 	public VMWritableRaster(android.graphics.Bitmap delegate)
	{
		super(delegate);

	}

	@Override
	public android.graphics.Bitmap getDelegate()
	{
		return delegate;
	}

	public int[] getDataElements(int i, int j, int width, int height, Object object)
	{
		//TODO: possibly garbage?
		IntBuffer buffer1 = IntBuffer.allocate(BitmapCompat.getAllocationByteCount(delegate)/4);
		delegate.copyPixelsToBuffer(buffer1);
		return buffer1.array();
		//throw new UnsupportedOperationException();//return (int[]) delegate.getDataElements(i, j, width, height, object);
	}

}
