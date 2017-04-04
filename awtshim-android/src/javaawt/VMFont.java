package javaawt;

import android.graphics.Rect;
import android.graphics.Typeface;
import javaawt.geom.Rectangle2D;

/**
 * Created by phil on 12/13/2016.
 */

public class VMFont extends Font
{
	private android.graphics.Paint fontSizingPaint = new android.graphics.Paint();

	private Typeface typeface;
	private int size = 12;

	public VMFont(Typeface typeface, int size)
	{
		this.typeface = typeface;
		this.size = size;
		fontSizingPaint.setTypeface(typeface);
	}

	@Override
	public Object getDelegate()
	{
		return typeface;
	}

	@Override
	public int getSize()
	{
		return size;
	}

	@Override
	public void setSize(int fontSize)
	{
		this.size = fontSize;
	}

	@Override
	public Rectangle2D getStringBounds(String text)
	{
		Rect r = new Rect();
		synchronized(fontSizingPaint)
		{
			fontSizingPaint.setTextSize(getSize());		
			fontSizingPaint.getTextBounds(text, 0, text.length(), r);
		}
		//Rectangle2D textBounds = new Rectangle(r.left, r.bottom, r.right - r.left, r.top - r.bottom);
		//PJPJ on desktop the return should be say height +15, y -12
		// on android this is returned as height -18, y +4, I'm not sure why
		// so swap to desktop style
		Rectangle2D textBounds = new Rectangle2D.Float(r.left, r.top, (r.right - r.left), -(r.top - r.bottom));
		return textBounds;
	}
}
