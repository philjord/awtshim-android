package desktop.javaawt.image;

import javaawt.image.DataBufferByte;

public class VMDataBufferByte extends VMDataBuffer implements DataBufferByte
{
	private java.awt.image.DataBufferByte delegate = null;

	public VMDataBufferByte(java.awt.image.DataBufferByte delegate)
	{
		super(delegate);
		this.delegate = delegate;
	}

	@Override
	public java.awt.image.DataBufferByte getDelegate()
	{
		return delegate;
	}

	public byte[] getData()
	{  
		return delegate.getData();
	}
}
