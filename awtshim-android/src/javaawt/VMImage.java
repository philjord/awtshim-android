package javaawt;

import android.graphics.Bitmap;

import javaawt.image.ImageObserver;
import javaawt.image.ImageProducer;

public class VMImage extends Image
{
	private android.graphics.Bitmap delegate = null;

	public VMImage(android.graphics.Bitmap delegate)
	{
		this.delegate = delegate;
	}

	@Override
	public Object getDelegate()
	{
		return delegate;
	}
	
	@Override
	public void flush()
	{
		throw new UnsupportedOperationException();//delegate.flush();
	}

	@Override
	public float getAccelerationPriority()
	{
		throw new UnsupportedOperationException();//return delegate.getAccelerationPriority();
	}

	@Override
	public Graphics getGraphics()
	{
		return new VMGraphics2D(delegate);
	}

	@Override
	public int getHeight(ImageObserver observer)
	{
		return delegate.getHeight();
	}

	@Override
	public Object getProperty(String name, ImageObserver observer)
	{
		// called by ImageIcon just for "comment"
		 return null;
	}

	@Override
	public Image getScaledInstance(int width, int height, int hints)
	{
		return new VMImage(Bitmap.createScaledBitmap(delegate, width, height, true));
	}

	@Override
	public ImageProducer getSource()
	{
		throw new UnsupportedOperationException();//return new VMImageProducer(delegate.getSource());
	}

	@Override
	public int getWidth(ImageObserver observer)
	{
		return delegate.getWidth();
	}

	@Override
	public void setAccelerationPriority(float priority)
	{
		throw new UnsupportedOperationException();//delegate.setAccelerationPriority(priority);
	}

}
