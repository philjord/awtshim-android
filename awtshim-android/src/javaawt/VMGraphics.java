package javaawt;

import android.graphics.Path;
import android.graphics.Typeface;

import java.text.AttributedCharacterIterator;

import javaawt.image.ImageObserver;

public class VMGraphics implements Graphics
{
	protected int currentColor = 0xFFFFFFFF; // used to swap out on paint calls

	protected android.graphics.Canvas delegate = null;
	// holds the various paint operations gear for the canvas, public to allow measure text
	public android.graphics.Paint canvasPaint = null;
	//PJPJPJ ok so paint color is multiplied with drawimage color!!!
	protected android.graphics.Paint drawPaint = null;


	public VMGraphics(android.graphics.Canvas delegate)
	{
		this.delegate = delegate;
		canvasPaint = new android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG);
		drawPaint = new android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG);

		delegate.save();
	}

	@Override
	public Object getDelegate()
	{
		return delegate;
	}

	@Override
	public Graphics create()
	{
		delegate.save();
		return this;
	}

	@Override
	public void dispose()
	{
		delegate.restore();
	}


	/**
	 * Note not recommended as no get set on matrix for this class use VMGraphics2D instead
	 *
	 * @param x
	 * @param y
	 */
	@Override
	public void translate(int x, int y)
	{
		delegate.translate(x, y);
	}

	@Override
	public Color getColor()
	{
		return fromColor(currentColor);
	}

	@Override
	public void setColor(Color c)
	{
		currentColor = toColor(c);
		canvasPaint.setColor(currentColor);
	}

	@Override
	public void setPaintMode()
	{
		throw new UnsupportedOperationException();//delegate.setPaintMode();
	}

	@Override
	public void setXORMode(Color c1)
	{
		throw new UnsupportedOperationException();//delegate.setXORMode(toColor(c1));
	}

	@Override
	public Rectangle getClipBounds()
	{
		return fromRectangle(delegate.getClipBounds());
	}

	@Override
	public void clipRect(int x, int y, int width, int height)
	{
		//TODO: clipRect causing trouble!
		//delegate.clipRect(toRect(x, y, width, height));
		//delegate.clipRect(toRect(x, y, width, height), Region.Op.REPLACE);
	}

	@Override
	public void setClip(int x, int y, int width, int height)
	{
		//TODO: what is the difference between clipRect and setClip in core java?
		//delegate.clipRect(toRect(x, y, width, height));
	}

	@Override
	public Shape getClip()
	{
		//TODO: used in plan component!!!!  a save restore job!
		//ignored for now
		return null;
	}

	@Override
	public void setClip(Shape clip)
	{
		//TODO: used in plan component!!!!  a save restore job!
		// Notice one of these in fillShape is NOT a save/restore pair but 2 are
		//ignored for now, though should check for rectangles and call delegate.clipRect(toRect(x, y, width, height));
	}

	@Override
	public void copyArea(int x, int y, int width, int height, int dx, int dy)
	{
		throw new UnsupportedOperationException();//delegate.copyArea(toRect(x,y,width,height), dx, dy);
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2)
	{
		delegate.drawLine(x1, y1, x2, y2, canvasPaint);
	}

	@Override
	public void fillRect(int x, int y, int width, int height)
	{
		canvasPaint.setStyle(android.graphics.Paint.Style.FILL);
		delegate.drawRect(toRect(x, y, width, height), canvasPaint);
	}

	@Override
	public void clearRect(int x, int y, int width, int height)
	{
		//TODO: possibly background color or something?
		canvasPaint.setStyle(android.graphics.Paint.Style.FILL);
		delegate.drawRect(toRect(x, y, width, height), canvasPaint);
	}

	@Override
	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight)
	{
		canvasPaint.setStyle(android.graphics.Paint.Style.STROKE);
		delegate.drawRoundRect(toRectF(x, y, width, height), arcWidth, arcHeight, canvasPaint);
	}
	
	@Override
	public void drawRect(int x, int y, int width, int height)
	{
		canvasPaint.setStyle(android.graphics.Paint.Style.STROKE);
		delegate.drawRect(toRectF(x, y, width, height), canvasPaint);
	}

	@Override
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight)
	{
		canvasPaint.setStyle(android.graphics.Paint.Style.FILL);
		delegate.drawRoundRect(toRectF(x, y, width, height), arcWidth, arcHeight, canvasPaint);
	}

	@Override
	public void drawOval(int x, int y, int width, int height)
	{
		canvasPaint.setStyle(android.graphics.Paint.Style.STROKE);
		delegate.drawOval(toRectF(x, y, width, height), canvasPaint);
	}

	@Override
	public void fillOval(int x, int y, int width, int height)
	{
		canvasPaint.setStyle(android.graphics.Paint.Style.FILL);
		delegate.drawOval(toRectF(x, y, width, height), canvasPaint);
	}

	@Override
	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
	{
		canvasPaint.setStyle(android.graphics.Paint.Style.STROKE);
		delegate.drawArc(toRectF(x, y, width, height), startAngle, arcAngle, false, canvasPaint);
	}

	@Override
	public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle)
	{
		canvasPaint.setStyle(android.graphics.Paint.Style.FILL);
		delegate.drawArc(toRectF(x, y, width, height), startAngle, arcAngle, false, canvasPaint);
	}

	@Override
	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints)
	{
		canvasPaint.setStyle(android.graphics.Paint.Style.STROKE);

		Path p = new Path();
		for (int i = 0; i < nPoints; i++)
		{
			p.moveTo(xPoints[i], yPoints[i]);
		}
		p.lineTo(xPoints[0], yPoints[0]); // there is a setLastPoint action but i found it not to work as expected

		delegate.drawPath(p, canvasPaint);
	}

	@Override
	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints)
	{
		canvasPaint.setStyle(android.graphics.Paint.Style.STROKE);
		Path p = new Path();
		for (int i = 0; i < nPoints; i++)
		{
			p.moveTo(xPoints[i], yPoints[i]);
		}
		p.lineTo(xPoints[0], yPoints[0]); // there is a setLastPoint action but i found it not to work as expected

		delegate.drawPath(p, canvasPaint);
	}

	@Override
	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints)
	{
		//is this not part of the fill API?, not used so it's ok for now
		canvasPaint.setStyle(android.graphics.Paint.Style.FILL);
		Path p = new Path();
		for (int i = 0; i < nPoints; i++)
		{
			p.moveTo(xPoints[i], yPoints[i]);
		}
		p.lineTo(xPoints[0], yPoints[0]); // there is a setLastPoint action but i found it not to work as expected

		delegate.drawPath(p, canvasPaint);
	}

	@Override
	public void drawString(String s, int x, int y)
	{
		delegate.drawText(s, x, y, canvasPaint);
	}

	@Override
	public void drawString(AttributedCharacterIterator iterator, int x, int y)
	{
		throw new UnsupportedOperationException();
		//delegate.drawText(iterator, x, y, canvasPaint);
	}

	@Override
	public boolean drawImage(Image img, int x, int y, ImageObserver observer)
	{
		delegate.drawBitmap((android.graphics.Bitmap) img.getDelegate(), x, y, drawPaint);
		return true;
	}

	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer)
	{
		android.graphics.Bitmap bm = (android.graphics.Bitmap) img.getDelegate();
		delegate.drawBitmap(bm, new android.graphics.Rect(0, 0, bm.getWidth(), bm.getHeight()), toRectF(x, y, width, height), drawPaint);
		return true;
	}

	@Override
	public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer)
	{
		//bgcolor ignored, possibly paint can fix?
		delegate.drawBitmap((android.graphics.Bitmap) img.getDelegate(), x, y, drawPaint);
		return true;
	}

	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer)
	{
		//bgcolor ignored, possibly paint can fix?
		android.graphics.Bitmap bm = (android.graphics.Bitmap) img.getDelegate();
		delegate.drawBitmap(bm, new android.graphics.Rect(0, 0, bm.getWidth(), bm.getHeight()), toRect(x, y, width, height), drawPaint);
		return true;
	}

	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer)
	{
		delegate.drawBitmap((android.graphics.Bitmap) img.getDelegate(), new android.graphics.Rect(sx1, sy1, sx2, sy2), new android.graphics.RectF(dx1, dy1, dx2, dy2), drawPaint);
		return true;
	}

	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor,
							 ImageObserver observer)
	{
		delegate.drawBitmap((android.graphics.Bitmap) img.getDelegate(), new android.graphics.Rect(sx1, sy1, sx2, sy2), new android.graphics.RectF(dx1, dy1, dx2, dy2), drawPaint);
		return true;
	}


	private Font currentFont = new VMFont(Typeface.DEFAULT, 24);

	@Override
	public Font getFont()
	{
		return currentFont;
	}

	@Override
	public void setFont(Font f)
	{
		currentFont = f;
		canvasPaint.setTypeface((Typeface)((VMFont)f).getDelegate());
		canvasPaint.setTextSize(f.getSize());
	}


	public static int toColor(Color c)
	{
		return android.graphics.Color.argb(c.getAlpha(), c.getRed(), c.getGreen(), c.getBlue());
	}

	public static Color fromColor(int c)
	{
		return new Color(android.graphics.Color.red(c), android.graphics.Color.green(c), android.graphics.Color.blue(c), android.graphics.Color.alpha(c));
	}

	public static Rectangle fromRectangle(android.graphics.Rect r)
	{
		return new Rectangle(r.left, r.top, r.right - r.left, r.top - r.bottom);
	}

	public static android.graphics.Rect toRect(int x, int y, int width, int height)
	{
		return new android.graphics.Rect(x, y, x + width, y + height);
	}

	public static android.graphics.RectF toRectF(int x, int y, int width, int height)
	{
		return new android.graphics.RectF(x, y, x + width, y + height);
	}

}
