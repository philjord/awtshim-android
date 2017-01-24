package javaawt.image;

import javaawt.Image;

public class VMImageObserver implements ImageObserver
{
	private Object delegate = null;

	public VMImageObserver(Object delegate)
	{
		this.delegate = delegate;
	}

	public Object getDelegate()
	{
		return delegate;
	}

	public boolean imageUpdate(Image img, int flags, int x, int y, int w, int h)
	{
		return false;
	}

}
