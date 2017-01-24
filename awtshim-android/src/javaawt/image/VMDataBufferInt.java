package javaawt.image;

import java.nio.IntBuffer;

import javaawt.image.DataBufferInt;

public class VMDataBufferInt extends VMDataBuffer implements DataBufferInt
{
	private IntBuffer delegate = null;

	/**
	 * must not be direct!
	 *
	 * @param delegate
	 */
	public VMDataBufferInt(IntBuffer delegate)
	{
		super(delegate);
		this.delegate = delegate;
	}

	@Override
	public IntBuffer getDelegate()
	{
		return delegate;
	}

	public int[] getData()
	{
		return delegate.array();
	}
}
