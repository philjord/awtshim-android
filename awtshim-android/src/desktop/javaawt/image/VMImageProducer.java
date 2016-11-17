package desktop.javaawt.image;

import javaawt.image.ImageProducer;

public class VMImageProducer implements ImageProducer
{
	private java.awt.image.ImageProducer delegate = null;

	public VMImageProducer(java.awt.image.ImageProducer delegate)
	{
		this.delegate = delegate;
	}
	
	public java.awt.image.ImageProducer getDelegate()
	{
		return delegate;
	}
}
