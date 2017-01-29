package javaawt;

import android.graphics.Typeface;

/**
 * Created by phil on 12/13/2016.
 */

public class VMFont extends Font
{
	private Typeface typeface;
	private int size = 12;

	public VMFont(Typeface typeface, int size)
	{
		this.typeface = typeface;
		this.size = size;
	}

	public Object getDelegate()
	{
		return typeface;
	}

	@Override
	public int getSize()
	{
		return size;
	}
}
