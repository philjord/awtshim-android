package desktop.javaawt.image;

import java.util.Vector;

import desktop.javaawt.VMGraphics2D;
import desktop.javaawt.VMImage;
import javaawt.Graphics;
import javaawt.Graphics2D;
import javaawt.Image;
import javaawt.Point;
import javaawt.Rectangle;
import javaawt.image.BufferedImage;
import javaawt.image.ColorModel;
import javaawt.image.ImageObserver;
import javaawt.image.ImageProducer;
import javaawt.image.Raster;
import javaawt.image.RenderedImage;
import javaawt.image.SampleModel;
import javaawt.image.TileObserver;
import javaawt.image.WritableRaster;

public class VMBufferedImage extends BufferedImage
{
	private java.awt.image.BufferedImage delegate = null;

	public VMBufferedImage(int i, int j, int typeIntArgb)
	{
		this(new java.awt.image.BufferedImage(i, j, typeIntArgb));
	}

	public VMBufferedImage(java.awt.image.BufferedImage delegate)
	{
		this.delegate = delegate;
	}

	@Override
	public Object getDelegate()
	{
		return delegate;
	}

	@Override
	public int getTransparency()
	{

		return delegate.getTransparency();

	}

	@Override
	public void releaseWritableTile(int tileX, int tileY)
	{

		delegate.releaseWritableTile(tileX, tileY);

	}

	@Override
	public WritableRaster getWritableTile(int tileX, int tileY)
	{
		return new VMWritableRaster(delegate.getWritableTile(tileX, tileY));
	}

	@Override
	public boolean hasTileWriters()
	{
		return delegate.hasTileWriters();
	}

	@Override
	public Point[] getWritableTileIndices()
	{
		throw new UnsupportedOperationException();//return delegate.getWritableTileIndices();
	}

	@Override
	public boolean isTileWritable(int tileX, int tileY)
	{
		return delegate.isTileWritable(tileX, tileY);
	}

	@Override
	public void coerceData(boolean isAlphaPremultiplied)
	{
		delegate.coerceData(isAlphaPremultiplied);
	}

	@Override
	public boolean isAlphaPremultiplied()
	{
		return delegate.isAlphaPremultiplied();
	}

	@Override
	public Graphics2D createGraphics()
	{
		return new VMGraphics2D(delegate.createGraphics());
	}

	@Override
	public Graphics getGraphics()
	{
		return new VMGraphics2D((java.awt.Graphics2D) delegate.getGraphics());
	}

	@Override
	public void setRGB(int startX, int startY, int w, int h, int[] rgbArray, int offset, int scansize)
	{
		delegate.setRGB(startX, startY, w, h, rgbArray, offset, scansize);
	}

	@Override
	public void setRGB(int x, int y, int rgb)
	{
		delegate.setRGB(x, y, rgb);
	}

	@Override
	public int[] getRGB(int startX, int startY, int w, int h, int[] rgbArray, int offset, int scansize)
	{
		return delegate.getRGB(startX, startY, w, h, rgbArray, offset, scansize);
	}

	@Override
	public int getRGB(int x, int y)
	{
		return delegate.getRGB(x, y);
	}

	@Override
	public WritableRaster getAlphaRaster()
	{
		return new VMWritableRaster(delegate.getAlphaRaster());
	}

	@Override
	public Raster getData(Rectangle rect)
	{
		return new VMRaster(delegate.getData(new java.awt.Rectangle(rect.x, rect.y, rect.width, rect.height)));
	}

	@Override
	public Raster getData()
	{
		return new VMRaster(delegate.getData());
	}

	@Override
	public Raster getTile(int tileX, int tileY)
	{
		return new VMRaster(delegate.getTile(tileX, tileY));
	}

	@Override
	public int getTileGridYOffset()
	{
		return delegate.getTileGridYOffset();
	}

	@Override
	public int getTileGridXOffset()
	{
		return delegate.getTileGridXOffset();
	}

	@Override
	public int getTileHeight()
	{
		return delegate.getTileHeight();
	}

	@Override
	public int getTileWidth()
	{
		return delegate.getTileWidth();
	}

	@Override
	public BufferedImage getSubimage(int x, int y, int w, int h)
	{
		return new VMBufferedImage(delegate.getSubimage(x, y, w, h));
	}

	@Override
	public String[] getPropertyNames()
	{
		return delegate.getPropertyNames();
	}

	@Override
	public Object getProperty(String name)
	{
		return delegate.getProperty(name);
	}

	@Override
	public Vector<RenderedImage> getSources()
	{
		throw new UnsupportedOperationException();//delegate.getSources();
	}

	@Override
	public int getMinTileY()
	{
		return delegate.getMinTileY();
	}

	@Override
	public int getMinTileX()
	{
		return delegate.getMinTileX();
	}

	@Override
	public int getNumYTiles()
	{
		return delegate.getNumYTiles();
	}

	@Override
	public int getNumXTiles()
	{
		return delegate.getNumXTiles();
	}

	@Override
	public int getMinY()
	{
		return delegate.getMinY();
	}

	@Override
	public int getMinX()
	{
		return delegate.getMinX();
	}

	@Override
	public SampleModel getSampleModel()
	{
		return new VMSampleModel(delegate.getSampleModel());
	}

	@Override
	public ColorModel getColorModel()
	{
		return new VMColorModel(delegate.getColorModel());
	}

	@Override
	public int getHeight()
	{
		return delegate.getHeight();
	}

	@Override
	public int getWidth()
	{
		return delegate.getWidth();
	}

	@Override
	public int getType()
	{
		return delegate.getType();
	}

	@Override
	public void setData(Raster r)
	{
		delegate.setData((java.awt.image.Raster) r.getDelegate());
	}

	@Override
	public void addTileObserver(TileObserver to)
	{
		throw new UnsupportedOperationException();//delegate.addTileObserver(to);
	}

	@Override
	public void removeTileObserver(TileObserver to)
	{
		throw new UnsupportedOperationException();//delegate.removeTileObserver(to);
	}

	@Override
	public WritableRaster copyData(WritableRaster raster)
	{
		throw new UnsupportedOperationException();//return delegate.copyData(raster);
	}

	@Override
	public int getHeight(ImageObserver observer)
	{
		return delegate.getHeight((java.awt.image.ImageObserver) observer.getDelegate());
	}

	@Override
	public Object getProperty(String name, ImageObserver observer)
	{
		return delegate.getProperty(name, (java.awt.image.ImageObserver) observer.getDelegate());
	}

	@Override
	public ImageProducer getSource()
	{

		return new VMImageProducer(delegate.getSource());

	}

	@Override
	public int getWidth(ImageObserver observer)
	{
		return delegate.getWidth((java.awt.image.ImageObserver) observer.getDelegate());
	}

	@Override
	public WritableRaster getRaster()
	{
		return new VMWritableRaster(delegate.getRaster());
	}

	@Override
	public void flush()
	{
		delegate.flush();
	}

	@Override
	public float getAccelerationPriority()
	{
		return delegate.getAccelerationPriority();
	}

	@Override
	public Image getScaledInstance(int width, int height, int hints)
	{
		return new VMImage(delegate.getScaledInstance(width, height, hints));
	}

	@Override
	public void setAccelerationPriority(float priority)
	{
		delegate.setAccelerationPriority(priority);
	}

	/*
	 * public ImageCapabilities getCapabilities(GraphicsConfiguration gc) { throw
	 * new UnsupportedOperationException(); }
	 */

}
