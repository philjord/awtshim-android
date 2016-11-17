package desktop.javaawt.image;

import javaawt.image.DataBufferInt;

public class VMDataBufferInt extends VMDataBuffer implements DataBufferInt
{
	private java.awt.image.DataBufferInt delegate = null;

	public VMDataBufferInt(java.awt.image.DataBufferInt delegate)
	{
		super(delegate);
		this.delegate = delegate;
	}

	@Override
	public java.awt.image.DataBufferInt getDelegate()
	{
		return delegate;
	}

	public int[] getData()
	{  
		return delegate.getData();
	}
}
