package desktop.javaawt.image;

import javaawt.image.DataBuffer;

public abstract class VMDataBuffer implements DataBuffer
{
	private java.awt.image.DataBuffer delegate = null;

	public VMDataBuffer(java.awt.image.DataBuffer delegate)
	{
		this.delegate = delegate;
	}
	
	public java.awt.image.DataBuffer getDelegate()
	{
		return delegate;
	}
}
