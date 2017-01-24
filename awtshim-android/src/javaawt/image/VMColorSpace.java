package javaawt.image;

import javaawt.color.ColorSpace;

public class VMColorSpace implements ColorSpace
{
	//ColorMatrix or colorfilter perhaps??
	private Object delegate = null;

	public VMColorSpace(Object delegate)
	{
		this.delegate = delegate;
	}

	//@Override
	public Object getDelegate()
	{
		return delegate;
	}

	//@Override
	public boolean isCS_sRGB()
	{
		throw new UnsupportedOperationException();//return delegate.isCS_sRGB();
	}

	//@Override
	public float[] toRGB(float[] colorvalue)
	{

		throw new UnsupportedOperationException();//return delegate.toRGB(colorvalue);
	}

	//@Override
	public float[] fromRGB(float[] rgbvalue)
	{

		throw new UnsupportedOperationException();//return delegate.fromRGB(rgbvalue);
	}

	//@Override
	public float[] toCIEXYZ(float[] colorvalue)
	{

		throw new UnsupportedOperationException();//return delegate.toCIEXYZ(colorvalue);
	}

	//@Override
	public float[] fromCIEXYZ(float[] colorvalue)
	{

		throw new UnsupportedOperationException();//return delegate.fromCIEXYZ(colorvalue);
	}

	//@Override
	public int getType()
	{

		throw new UnsupportedOperationException();//return delegate.getType();
	}

	//@Override
	public int getNumComponents()
	{

		throw new UnsupportedOperationException();//return delegate.getNumComponents();
	}

	//@Override
	public String getName(int idx)
	{
		throw new UnsupportedOperationException();//return delegate.getName(idx);
	}

	//@Override
	public float getMinValue(int component)
	{

		throw new UnsupportedOperationException();//return delegate.getMinValue(component);
	}

	//@Override
	public float getMaxValue(int component)
	{

		throw new UnsupportedOperationException();//return delegate.getMaxValue(component);
	}
}
