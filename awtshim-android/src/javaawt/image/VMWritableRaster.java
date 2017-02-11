package javaawt.image;

import android.support.v4.graphics.BitmapCompat;

import java.lang.ref.WeakReference;
import java.nio.IntBuffer;

import javaawt.image.WritableRaster;

public class VMWritableRaster extends VMRaster implements WritableRaster
{	
	private WeakReference<int[]> buff;

	public VMWritableRaster(android.graphics.Bitmap delegate)
	{
		super(delegate);

	}

	@Override
	public android.graphics.Bitmap getDelegate()
	{
		return delegate;
	}

	@Override
	public int[] getDataElements(int i, int j, int width, int height, Object object)
	{

		if (buff != null && buff.get() != null)
		{
			return buff.get();
		}
		
		// check parents dataBufferRef
		if(dataBufferRef !=null && dataBufferRef.get()!=null)
		{
			int[] data = dataBufferRef.get().getData();
			buff = new WeakReference<int[]>(data);
			return data;
		}
		else
		{
			IntBuffer buffer1 = IntBuffer.allocate(BitmapCompat.getAllocationByteCount(delegate) / 4);
			delegate.copyPixelsToBuffer(buffer1);
			int[] data = buffer1.array();
			buff = new WeakReference<int[]>(data);
			return data;
		}
	}

}
