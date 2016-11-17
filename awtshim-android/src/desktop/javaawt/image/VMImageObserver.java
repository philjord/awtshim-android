package desktop.javaawt.image;

import javaawt.Image;
import javaawt.image.ImageObserver;

public class VMImageObserver implements ImageObserver
{
	private java.awt.image.ImageObserver delegate = null;

	public VMImageObserver(java.awt.image.ImageObserver delegate)
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
