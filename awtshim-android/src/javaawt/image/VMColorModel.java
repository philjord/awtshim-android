package javaawt.image;

import javaawt.color.ColorSpace;

public class VMColorModel implements ColorModel
{
	private Object delegate = null;

	public VMColorModel(Object delegate)
	{
		this.delegate = delegate;
	}

	@Override
	public Object getDelegate()
	{
		return delegate;
	}

	@Override
	public ColorSpace getColorSpace()
	{
		return new VMColorSpace(null);//delegate.getColorSpace());
	}

	@Override
	public boolean isAlphaPremultiplied()
	{
		throw new UnsupportedOperationException();//return delegate.isAlphaPremultiplied();
	}

	@Override
	public int getRed(Object pixel)
	{
		throw new UnsupportedOperationException();//	return delegate.getRed(pixel);
	}

	@Override
	public int getGreen(Object pixel)
	{
		throw new UnsupportedOperationException();//return delegate.getGreen(pixel);
	}

	@Override
	public int getBlue(Object pixel)
	{
		throw new UnsupportedOperationException();//return delegate.getBlue(pixel);
	}

	@Override
	public int getAlpha(Object pixel)
	{
		throw new UnsupportedOperationException();//return delegate.getAlpha(pixel);
	}

	@Override
	public int getTransparency()
	{
		throw new UnsupportedOperationException();//return delegate.getTransparency();
	}
}
