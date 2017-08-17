package javaawt.image;

import android.graphics.Bitmap;

import javaawt.Graphics;
import javaawt.Graphics2D;
import javaawt.Image;
import javaawt.Point;
import javaawt.Rectangle;
import javaawt.Transparency;
import javaawt.VMGraphics2D;

import java.util.Vector;

public class VMBufferedImage extends BufferedImage
{
	private android.graphics.Bitmap delegate = null;

	public VMBufferedImage(int w, int h, int typeIntArgb)
	{
		this(Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888));
		if( typeIntArgb != TYPE_INT_ARGB)
		{
			throw new UnsupportedOperationException("Android only has 4 byte bitmaps! BufferedImage.TYPE_INT_ARGB");
		}
		//  sweethome uses at least
		//this.texture.getFormat() == Texture.RGBA ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
		//but bitmap only has rgb565 which will probably be worse so ignore, as sweethome tests transparency with FF000000

	}

	public VMBufferedImage(android.graphics.Bitmap delegate)
	{
		this.delegate = delegate;
	}

	@Override
	public Object getDelegate()
	{
		return delegate;
	}

	/**
	 * Returns the transparency.  Returns either OPAQUE, BITMASK,
	 * or TRANSLUCENT.
	 * @return the transparency of this <code>BufferedImage</code>.
	 * @see Transparency#OPAQUE
	 * @see Transparency#BITMASK
	 * @see Transparency#TRANSLUCENT
	 * @since 1.5
	 */
	@Override
	public int getTransparency()
	{
		//better to suggest transparency, as sweethome checks anyway
		return Transparency.TRANSLUCENT;
	}

	@Override
	public void releaseWritableTile(int tileX, int tileY)
	{

		throw new UnsupportedOperationException();//delegate.releaseWritableTile(tileX, tileY);

	}

	@Override
	public WritableRaster getWritableTile(int tileX, int tileY)
	{
		throw new UnsupportedOperationException();//return new VMWritableRaster(delegate.getWritableTile(tileX, tileY));
	}

	@Override
	public boolean hasTileWriters()
	{
		throw new UnsupportedOperationException();//return delegate.hasTileWriters();
	}

	@Override
	public Point[] getWritableTileIndices()
	{
		throw new UnsupportedOperationException();//return delegate.getWritableTileIndices();
	}

	@Override
	public boolean isTileWritable(int tileX, int tileY)
	{
		throw new UnsupportedOperationException();//return delegate.isTileWritable(tileX, tileY);
	}

	@Override
	public void coerceData(boolean isAlphaPremultiplied)
	{
		throw new UnsupportedOperationException();//delegate.coerceData(isAlphaPremultiplied);
	}

	@Override
	public boolean isAlphaPremultiplied()
	{
		throw new UnsupportedOperationException();//return delegate.isAlphaPremultiplied();
	}

	@Override
	public Graphics2D createGraphics()
	{
		return new VMGraphics2D(delegate);
	}

	@Override
	public Graphics getGraphics()
	{
		return new VMGraphics2D(delegate);
	}

	@Override
	public void setRGB(int startX, int startY, int w, int h, int[] rgbArray, int offset, int scansize)
	{
		delegate.setPixels(rgbArray, offset, scansize, startX, startY, w, h);
		//delegate.setRGB(startX, startY, w, h, rgbArray, offset, scansize);
	}

	@Override
	public void setRGB(int x, int y, int rgb)
	{
		delegate.setPixel(x, y, rgb);
	}

	@Override
	public int[] getRGB(int startX, int startY, int w, int h, int[] rgbArray, int offset, int scansize)
	{
		delegate.getPixels(rgbArray, offset, scansize, startX, startY, w, h);
		return rgbArray;
		//return delegate.getRGB(startX, startY, w, h, rgbArray, offset, scansize);
	}

	@Override
	public int getRGB(int x, int y)
	{
		return delegate.getPixel(x, y);
	}

	@Override
	public WritableRaster getAlphaRaster()
	{
		throw new UnsupportedOperationException();//return new VMWritableRaster(delegate.getAlphaRaster());
	}

	@Override
	public Raster getData(Rectangle rect)
	{
		return new VMRaster(Bitmap.createBitmap(delegate, rect.x, rect.y, rect.width, rect.height));
	}

	@Override
	public Raster getData()
	{
		return new VMRaster(delegate);
	}

	@Override
	public Raster getTile(int tileX, int tileY)
	{
		throw new UnsupportedOperationException();//return new VMRaster(delegate.getTile(tileX, tileY));
	}

	@Override
	public int getTileGridYOffset()
	{
		throw new UnsupportedOperationException();//return delegate.getTileGridYOffset();
	}

	@Override
	public int getTileGridXOffset()
	{
		throw new UnsupportedOperationException();//return delegate.getTileGridXOffset();
	}

	@Override
	public int getTileHeight()
	{
		throw new UnsupportedOperationException();//return delegate.getTileHeight();
	}

	@Override
	public int getTileWidth()
	{
		throw new UnsupportedOperationException();//return delegate.getTileWidth();
	}

	@Override
	public BufferedImage getSubimage(int x, int y, int w, int h)
	{
		return new VMBufferedImage(Bitmap.createBitmap(delegate, x, y, w, h));
	}

	@Override
	public String[] getPropertyNames()
	{
		throw new UnsupportedOperationException();//return delegate.getPropertyNames();
	}

	@Override
	public Object getProperty(String name)
	{
		throw new UnsupportedOperationException();//return delegate.getProperty(name);
	}

	@Override
	public Vector<RenderedImage> getSources()
	{
		throw new UnsupportedOperationException();//delegate.getSources();
	}

	@Override
	public int getMinTileY()
	{
		throw new UnsupportedOperationException();//return delegate.getMinTileY();
	}

	@Override
	public int getMinTileX()
	{
		throw new UnsupportedOperationException();//return delegate.getMinTileX();
	}

	@Override
	public int getNumYTiles()
	{
		throw new UnsupportedOperationException();//return delegate.getNumYTiles();
	}

	@Override
	public int getNumXTiles()
	{
		throw new UnsupportedOperationException();//return delegate.getNumXTiles();
	}

	@Override
	public int getMinY()
	{
		throw new UnsupportedOperationException();//return delegate.getMinY();
	}

	@Override
	public int getMinX()
	{
		throw new UnsupportedOperationException();//return delegate.getMinX();
	}

	@Override
	public SampleModel getSampleModel()
	{
		throw new UnsupportedOperationException();//return new VMSampleModel(delegate.getSampleModel());
	}

	@Override
	public ColorModel getColorModel()
	{
		throw new UnsupportedOperationException();//return new VMColorModel(delegate.getColorModel());
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

	/**
	 * Returns the image type.  If it is not one of the known types,
	 * TYPE_CUSTOM is returned.
	 * @return the image type of this <code>BufferedImage</code>.
	 * @see #TYPE_INT_RGB
	 * @see #TYPE_INT_ARGB
	 * @see #TYPE_INT_ARGB_PRE
	 * @see #TYPE_INT_BGR
	 * @see #TYPE_3BYTE_BGR
	 * @see #TYPE_4BYTE_ABGR
	 * @see #TYPE_4BYTE_ABGR_PRE
	 * @see #TYPE_BYTE_GRAY
	 * @see #TYPE_BYTE_BINARY
	 * @see #TYPE_BYTE_INDEXED
	 * @see #TYPE_USHORT_GRAY
	 * @see #TYPE_USHORT_565_RGB
	 * @see #TYPE_USHORT_555_RGB
	 * @see #TYPE_CUSTOM
	 */
	@Override
	public int getType()
	{

		// There is NO 3 byte bitmap only the 2 byte RGB_565
		////Bitmap.Config bmc = delegate.getConfig();
		return TYPE_INT_ARGB; // matches the bitmapfactory return of ARGB_8888
	}

	@Override
	public void setData(Raster r)
	{
		throw new UnsupportedOperationException();//delegate.setData((java.awt.image.Raster) r.getDelegate());
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
		return delegate.getHeight();
	}

	@Override
	public Object getProperty(String name, ImageObserver observer)
	{
		//TODO: propertys not supported; however ImageIcon looks for one called "comment"
		// returning null should be ok for all callers
		return null;//return delegate.getProperty(name, null);
	}

	@Override
	public ImageProducer getSource()
	{
		throw new UnsupportedOperationException();//	return new VMImageProducer(delegate.getSource());
	}

	@Override
	public int getWidth(ImageObserver observer)
	{
		return delegate.getWidth();
	}

	@Override
	public WritableRaster getRaster()
	{
		return new VMWritableRaster(delegate);
	}

	@Override
	public void flush()
	{
		throw new UnsupportedOperationException();//delegate.flush();
	}

	@Override
	public float getAccelerationPriority()
	{
		throw new UnsupportedOperationException();//return delegate.getAccelerationPriority();
	}

	@Override
	public Image getScaledInstance(int width, int height, int hints)
	{
		return new VMBufferedImage(Bitmap.createScaledBitmap(delegate, width, height, true));
	}

	@Override
	public void setAccelerationPriority(float priority)
	{
		throw new UnsupportedOperationException();//delegate.setAccelerationPriority(priority);
	}

	/*
	 * public ImageCapabilities getCapabilities(GraphicsConfiguration gc) { throw
	 * new UnsupportedOperationException(); }
	 */

}
