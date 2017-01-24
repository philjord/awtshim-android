package javaawt;

import android.graphics.Typeface;

/**
 * Created by phil on 12/13/2016.
 */

public class VMFont extends Font
{
	private Typeface typeface =  Typeface.DEFAULT;

	public Object getDelegate()
	{
		return typeface;
	}
	@Override
	public int getSize()
	{
		//What the hell to do here? typeface doesn't have a size?
		//textSize is an attribute of teh TExtView thign, not typeface??

		//ok so textsize is held in the  paint, so not exactly sure what to do with this guy now, hold it and get it out with any paints I use probably
		return 12;
	}
}
