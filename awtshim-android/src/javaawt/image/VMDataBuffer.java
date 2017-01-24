package javaawt.image;

import java.nio.Buffer;

public abstract class VMDataBuffer implements DataBuffer
{
	protected Buffer delegate = null;

	public VMDataBuffer(Buffer delegate)
	{
		this.delegate = delegate;
	}

	public Buffer getDelegate()
	{
		return delegate;
	}
}
