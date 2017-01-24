package javaawt.image;

import java.nio.ByteBuffer;

public class VMDataBufferByte extends VMDataBuffer implements DataBufferByte
{
	private ByteBuffer delegate = null;

	/**
	 * must not be direct!
	 *
	 * @param delegate
	 */
	public VMDataBufferByte(ByteBuffer delegate)
	{
		super(delegate);
		this.delegate = delegate;
	}

	@Override
	public ByteBuffer getDelegate()
	{
		return delegate;
	}

	public byte[] getData()
	{
		return delegate.array();
	}
}
