package javaawt.image;

import java.nio.IntBuffer;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;

public class VMRaster implements Raster
{
	protected android.graphics.Bitmap delegate = null;

	public VMRaster(android.graphics.Bitmap delegate)
	{
		this.delegate = delegate;
	}

	@Override
	public android.graphics.Bitmap getDelegate()
	{
		return delegate;
	}

	@Override
	public void getDataElements(int w, int h, Object pixel)
	{
		throw new UnsupportedOperationException();//delegate.getDataElements(w, h, pixel);
	}

	@Override
	public int getNumDataElements()
	{
		throw new UnsupportedOperationException();//	return delegate.getNumDataElements();
	}

	@Override
	public int getTransferType()
	{
		throw new UnsupportedOperationException();//	return delegate.getTransferType();
	}

	@Override
	public DataBuffer getDataBuffer()
	{		
		IntBuffer buffer1 = IntBuffer.allocate(getBitmapByteCount(delegate) / 4);			
		delegate.copyPixelsToBuffer(buffer1);
		VMDataBufferInt ret = new VMDataBufferInt(buffer1);
		return ret;

	}
	
	public static int getBitmapByteCount(android.graphics.Bitmap bitmap) {
	    if (VERSION.SDK_INT < VERSION_CODES.HONEYCOMB_MR1)
	        return bitmap.getRowBytes() * bitmap.getHeight();
	    if (VERSION.SDK_INT < VERSION_CODES.KITKAT)
	        return bitmap.getByteCount();
	    return bitmap.getAllocationByteCount();
	}
}
