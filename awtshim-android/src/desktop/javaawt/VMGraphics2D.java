package desktop.javaawt;

import java.awt.image.BufferedImageOp;

import javaawt.Color;
import javaawt.Graphics2D;
import javaawt.Image;
import javaawt.image.BufferedImage;
import javaawt.image.ImageObserver;

public class VMGraphics2D implements Graphics2D
{
	private java.awt.Graphics2D delegate = null;

	public VMGraphics2D(java.awt.Graphics2D delegate)
	{
		this.delegate = delegate;
	}

	@Override
	public Object getDelegate()
	{
		return delegate;
	}

	@Override
	public void dispose()
	{
		delegate.dispose();
	}

	@Override
	public void drawImage(BufferedImage image, Object object, int i, int j)
	{
		delegate.drawImage((java.awt.image.BufferedImage) image.getDelegate(), (BufferedImageOp) object, i, j);
	}

	@Override
	public boolean drawImage(Image img, int x, int y, ImageObserver observer)
	{
		return delegate.drawImage((java.awt.image.BufferedImage) img.getDelegate(), x, y,
				(java.awt.image.ImageObserver) observer.getDelegate());
	}

	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer)
	{
		return delegate.drawImage((java.awt.image.BufferedImage) img.getDelegate(), x, y, width, height,
				(java.awt.image.ImageObserver) observer.getDelegate());
	}

	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer)
	{
		return delegate.drawImage((java.awt.image.BufferedImage) img.getDelegate(), x, y, width, height,
				new java.awt.Color(bgcolor.r, bgcolor.g, bgcolor.b), (java.awt.image.ImageObserver) observer.getDelegate());
	}

	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer)
	{
		return delegate.drawImage((java.awt.image.BufferedImage) img.getDelegate(), dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2,
				(java.awt.image.ImageObserver) observer.getDelegate());
	}

	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor,
			ImageObserver observer)
	{
		return delegate.drawImage((java.awt.image.BufferedImage) img.getDelegate(), dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2,
				new java.awt.Color(bgcolor.r, bgcolor.g, bgcolor.b), (java.awt.image.ImageObserver) observer.getDelegate());
	}

	@Override
	public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer)
	{
		return delegate.drawImage((java.awt.image.BufferedImage) img.getDelegate(), x, y,
				new java.awt.Color(bgcolor.r, bgcolor.g, bgcolor.b), (java.awt.image.ImageObserver) observer.getDelegate());
	}

	@Override
	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
	{
		delegate.drawArc(x, y, width, height, startAngle, arcAngle);
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2)
	{
		delegate.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void drawOval(int x, int y, int width, int height)
	{
		delegate.drawOval(x, y, width, height);
	}

	@Override
	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints)
	{
		delegate.drawPolygon(xPoints, yPoints, nPoints);
	}

	@Override
	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints)
	{
		delegate.drawPolyline(xPoints, yPoints, nPoints);
	}

	@Override
	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight)
	{
		delegate.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	@Override
	public void drawString(String s, float x, float y)
	{
		delegate.drawString(s, x, y);
	}

	@Override
	public void drawString(String s, int x, int y)
	{
		delegate.drawString(s, x, y);
	}

	@Override
	public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle)
	{
		delegate.fillArc(x, y, width, height, startAngle, arcAngle);

	}

	@Override
	public void fillOval(int x, int y, int width, int height)
	{
		delegate.fillOval(x, y, width, height);

	}

	@Override
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight)
	{
		delegate.fillRoundRect(x, y, width, height, arcWidth, arcHeight);

	}

	@Override
	public void setColor(Color c)
	{
		delegate.setColor(new java.awt.Color(c.r, c.g, c.b));

	}

	@Override
	public void draw3DRect(int x, int y, int width, int height, boolean raised)
	{
		delegate.draw3DRect(x, y, width, height, raised);
	}

	@Override
	public void drawRect(int x, int y, int width, int height)
	{
		delegate.drawRect(x, y, width, height);
	}

	@Override
	public void fill3DRect(int x, int y, int width, int height, boolean raised)
	{
		delegate.fill3DRect(x, y, width, height, raised);
	}

	@Override
	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints)
	{
		delegate.fillPolygon(xPoints, yPoints, nPoints);
	}

	@Override
	public void fillRect(int x, int y, int width, int height)
	{
		delegate.fillRect(x, y, width, height);
	}

}
