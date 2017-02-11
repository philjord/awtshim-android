package javaawt.image;

import java.lang.ref.WeakReference;
import java.nio.IntBuffer;

import android.support.v4.graphics.BitmapCompat;

public class VMRaster implements Raster
{
	protected WeakReference<VMDataBufferInt> dataBufferRef;
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
		//Ok, so ImageComponent2DRetained has a getImage call...
		// which calls createBufferedImage
		// which creates a new BufferedImage, then calls ImageComponentRetained.copyByLine to get data into it!!
		// so it wants the returned Databuffer to actually be the backing int array of the image to write into it!
		// but obviously here I create a new buffer and copy my data into it, and that new buffer
		// backs nothing and is eventually dropped
		if (dataBufferRef != null && dataBufferRef.get() != null)
		{
			return dataBufferRef.get();
		}

		IntBuffer buffer1 = IntBuffer.allocate(BitmapCompat.getAllocationByteCount(delegate) / 4);
		delegate.copyPixelsToBuffer(buffer1);
		VMDataBufferInt ret = new VMDataBufferInt(buffer1);
		dataBufferRef = new WeakReference<VMDataBufferInt>(ret);
		return ret;

	}
}
