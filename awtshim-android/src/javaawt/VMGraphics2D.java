package javaawt;

import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;

import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Map;

import javaawt.RenderingHints.Key;
import javaawt.geom.AffineTransform;
import javaawt.geom.Arc2D;
import javaawt.geom.Area;
import javaawt.geom.Ellipse2D;
import javaawt.geom.GeneralPath;
import javaawt.geom.Line2D;
import javaawt.geom.Path2D;
import javaawt.geom.PathIterator;
import javaawt.geom.Rectangle2D;
import javaawt.image.BufferedImage;
import javaawt.image.BufferedImageOp;
import javaawt.image.ImageObserver;
import javaawt.image.RenderedImage;


public class VMGraphics2D extends VMGraphics implements Graphics2D
{
	// used by the setpaint ready for the next fill method of
	protected android.graphics.Paint fillPaint = null;

	private Composite currentComposite = null;

	public VMGraphics2D(android.graphics.Bitmap bitmap)
	{
		this(new Canvas(bitmap));
	}

	public VMGraphics2D(android.graphics.Canvas delegate)
	{
		super(delegate);
		fillPaint = new android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG);
		fillPaint.setStyle(android.graphics.Paint.Style.FILL);
	}


	public Graphics2D create()
	{
		delegate.save();
		return this;
	}

	@Override
	public void dispose()
	{
		//TODO: this should have a create saes count as it tends to be 1 not 0, but I have both inti and create so I'm not sure
		if (delegate.getSaveCount() > 0)
		{
			delegate.restore();
		}
		else
			new Throwable("Uneven save/restore in VMGraphics2D").printStackTrace();
	}

	@Override
	public void drawImage(BufferedImage image, BufferedImageOp op, int x, int y)
	{
		//TODO: transcribe the op across too
		delegate.drawBitmap((android.graphics.Bitmap) image.getDelegate(), x, y, drawPaint);
	}

	@Override
	public void drawString(String s, float x, float y)
	{
		canvasPaint.setStyle(android.graphics.Paint.Style.FILL);
		delegate.drawText(s, x, y, canvasPaint);
	}

	/*	
		public void draw3DRect(int x, int y, int width, int height, boolean raised)
		{
			delegate.draw3DRect(x, y, width, height, raised);
		}*/

	/*	
		public void drawRect(int x, int y, int width, int height)
		{
			delegate.drawRect(x, y, width, height);
		}*/

	/*	
		public void fill3DRect(int x, int y, int width, int height, boolean raised)
		{
			delegate.fill3DRect(x, y, width, height, raised);
		}*/


	@Override
	public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void drawRenderedImage(RenderedImage img, AffineTransform xform)
	{
		if (img instanceof BufferedImage)
		{
			delegate.drawBitmap((android.graphics.Bitmap) img.getDelegate(), toMatrix(xform), drawPaint);
		}
		else
		{
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public void drawString(AttributedCharacterIterator iterator, float x, float y)
	{
		throw new UnsupportedOperationException();
		//delegate.drawString(iterator, x, y);
	}


	@Override
	public void setPaint(Paint paint)
	{
		if (paint == null || paint instanceof Color || paint instanceof TexturePaint)
		{
			currentFillPaint = paint;
		}
		else
		{
			throw new UnsupportedOperationException();
		}
	}

	private Paint currentFillPaint = null;

	@Override
	public void fill(Shape s)
	{
		fill(s, 1.0f);
	}

	/**
	 * Uniform scale for shape if needed to avoid blurry mess
	 * @param s
	 * @param scale
	 */
	public void fill(Shape s, float scale)
	{

		Shader shader = null;
		if (currentFillPaint == null || currentFillPaint instanceof Color)
		{
			if (currentFillPaint != null)
			{
				fillPaint.setColor(toColor((Color) currentFillPaint));
				int c = toColor((Color) currentFillPaint);
				shader = new LinearGradient(0f, 0f, 1f, 1f, c, c, Shader.TileMode.REPEAT);
			}
			else
			{
				// otherwise just use foreground
				fillPaint.setColor(currentColor);
				shader = new LinearGradient(0f, 0f, 1f, 1f, currentColor, currentColor, Shader.TileMode.REPEAT);
			}

		}
		else if (currentFillPaint instanceof TexturePaint)
		{
			TexturePaint tp = (TexturePaint) currentFillPaint;
			android.graphics.Bitmap bm = (android.graphics.Bitmap) tp.getImage().getDelegate();
			Rectangle2D anchor = tp.getAnchorRect();

			shader = new BitmapShader(bm, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

			Matrix matrix = new Matrix();
			matrix.reset();
			double sx = anchor.getWidth() / bm.getWidth();
			double sy = anchor.getHeight() / bm.getHeight();
			matrix.setScale((float) sx, (float) sy);
			matrix.preTranslate(0, 0);
			shader.setLocalMatrix(matrix);
		}

		Path p = null;

		if (s instanceof Rectangle2D.Double)
		{
			Rectangle2D.Double r = (Rectangle2D.Double) s;
			p = new Path();
			p.addRect(toRectF(r), Path.Direction.CW);
		}
		else if (s instanceof Rectangle2D.Float)
		{
			Rectangle2D.Float r = (Rectangle2D.Float) s;
			p = new Path();
			p.addRect(toRectF(r), Path.Direction.CW);
		}
		else if (s instanceof Ellipse2D.Double)
		{
			Ellipse2D.Double r = (Ellipse2D.Double) s;
			p = new Path();
			p.addOval(toRectF(r), Path.Direction.CW);
		}
		else if (s instanceof Ellipse2D.Float)
		{
			Ellipse2D.Float r = (Ellipse2D.Float) s;
			p = new Path();
			p.addOval(toRectF(r), Path.Direction.CW);
		}
		else if (s instanceof GeneralPath)
		{
			GeneralPath gp = (GeneralPath) s;
			p = toPath(gp, scale);
		}
		else if (s instanceof Area)
		{
			Area a = (Area) s;
			p = toPath(a, scale);
		}
		else
		{
			throw new UnsupportedOperationException("Unhandled fill(Shape s) " + s);
		}

		if (p != null)
		{
			PathShape shape = new PathShape(p, delegate.getWidth(), delegate.getHeight());
			ShapeDrawable sd = new ShapeDrawable(shape);

			//sd.setColorFilter(Color.argb(255, 50*(mLevel+1), 50*(mLevel+1), 50*(mLevel+1)), Mode.LIGHTEN);
			sd.getPaint().setShader(shader);
			sd.setBounds(0, 0, delegate.getWidth(), delegate.getHeight());
			sd.draw(delegate);
		}

	}

	@Override
	public void draw(Shape s)
	{
		draw(s, 1.0f);
	}

	/**
	 * Uniform scale for shape if needed to avoid blurry mess
	 * @param s
	 * @param scale
	 */
	public void draw(Shape s, float scale)
	{
		//TODO: does this in fact do anything, is this even vaguely right? must look this up
		if (currentComposite == null || !(currentComposite instanceof AlphaComposite) || ((AlphaComposite) currentComposite).getType() == AlphaComposite.SRC_IN)
		{
			//	canvasPaint.setAlpha(255);
		}
		else
		{
			//	canvasPaint.setAlpha((int)(255 * ((AlphaComposite)currentComposite).getAlpha()));
		}
		canvasPaint.setStyle(android.graphics.Paint.Style.STROKE);
		if (s instanceof Line2D.Double)
		{
			Line2D.Double l = (Line2D.Double) s;
			delegate.drawLines(toPnts(l), canvasPaint);
		}
		else if (s instanceof Line2D.Float)
		{
			Line2D.Float l = (Line2D.Float) s;
			delegate.drawLines(toPnts(l), canvasPaint);
		}
		else if (s instanceof Arc2D.Double)
		{
			Arc2D.Double a = (Arc2D.Double) s;
			delegate.drawArc(toRectF(a), (float) a.getAngleStart(), (float) a.getAngleExtent(), a.getArcType() == Arc2D.PIE, canvasPaint);
		}
		else if (s instanceof Arc2D.Float)
		{
			Arc2D.Float a = (Arc2D.Float) s;
			delegate.drawArc(toRectF(a), (float) a.getAngleStart(), (float) a.getAngleExtent(), a.getArcType() == Arc2D.PIE, canvasPaint);
		}
		else if (s instanceof Ellipse2D.Double)
		{
			Ellipse2D.Double a = (Ellipse2D.Double) s;
			delegate.drawOval(toRectF(a), canvasPaint);
		}
		else if (s instanceof Ellipse2D.Float)
		{
			Ellipse2D.Float a = (Ellipse2D.Float) s;
			delegate.drawOval(toRectF(a), canvasPaint);
		}
		else if (s instanceof Rectangle2D.Double)
		{
			Rectangle2D.Double a = (Rectangle2D.Double) s;
			delegate.drawRect(toRectF(a), canvasPaint);
		}
		else if (s instanceof Rectangle2D.Float)
		{
			Rectangle2D.Float a = (Rectangle2D.Float) s;
			delegate.drawRect(toRectF(a), canvasPaint);
		}
		else if (s instanceof Area)
		{
			Area a = (Area) s;
			Path p = toPath(a, scale);
			//delegate.drawPath(p, canvasPaint);
			drawPath(delegate, p, canvasPaint);
		}
		else if (s instanceof GeneralPath)
		{
			GeneralPath gp = (GeneralPath) s;
			Path p = toPath(gp, scale);
			//delegate.drawPath(p, canvasPaint);
			drawPath(delegate, p, canvasPaint);
		}
		else
		{
			System.out.println("Unhandled draw(Shape s) " + s);
		}
	}

	@Override
	public void setStroke(Stroke s)
	{
		if (s instanceof BasicStroke)
		{
			BasicStroke bs = (BasicStroke) s;
			canvasPaint.setStrokeWidth(bs.getLineWidth());

			canvasPaint.setStrokeCap(bs.getEndCap() == BasicStroke.CAP_ROUND ? Cap.ROUND : bs.getEndCap() == BasicStroke.CAP_SQUARE ?
					Cap.SQUARE : Cap.BUTT);
			canvasPaint.setStrokeMiter(bs.getMiterLimit());
			canvasPaint.setStrokeJoin(bs.getLineJoin() == BasicStroke.JOIN_ROUND ? Join.ROUND : bs.getLineJoin() == BasicStroke.JOIN_BEVEL ?
					Join.BEVEL : Join.MITER);
			// possibly dash might be useful?
		}
		else
		{
			System.out.println("Unhandled setStroke(Stroke s) " + s);
		}
	}

	@Override
	public boolean hit(Rectangle rect, Shape s, boolean onStroke)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public GraphicsConfiguration getDeviceConfiguration()
	{
		throw new UnsupportedOperationException();
	}


	/**
	 * accepted but all ignored
	 *
	 * @param hintKey
	 * @param hintValue
	 */
	@Override
	public void setRenderingHint(Key hintKey, Object hintValue)
	{
		//throw new UnsupportedOperationException();
		//these are sometimes things like use antialiasing, which I just set in the constructor anyway
	}

	@Override
	public Object getRenderingHint(Key hintKey)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setRenderingHints(Map<?, ?> hints)
	{
		throw new UnsupportedOperationException();

	}

	@Override
	public void addRenderingHints(Map<?, ?> hints)
	{
		throw new UnsupportedOperationException();

	}

	@Override
	public RenderingHints getRenderingHints()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * theta is in radians, counter clockwise
	 *
	 * @param theta
	 */
	@Override
	public void rotate(double theta)
	{
		float deg = (float) (theta * 180 / Math.PI);
		delegate.rotate(deg);
	}

	/**
	 * theta is in radians, counter clockwise
	 *
	 * @param theta
	 */
	@Override
	public void rotate(double theta, double x, double y)
	{
		float deg = (float) (theta * 180 / Math.PI);
		delegate.rotate(deg, (float) x, (float) y);
	}

	@Override
	public void scale(double sx, double sy)
	{
		delegate.scale((float) sx, (float) sy);
	}

	@Override
	public void shear(double shx, double shy)
	{
		delegate.skew((float) shx, (float) shy);
	}

	@Override
	public void transform(AffineTransform Tx)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Get and set transform will now ONLY call save restore on the delegate
	 * Note this saves the matrix only
	 *
	 * @param Tx ignored
	 */
	@Override
	public void setTransform(AffineTransform Tx)
	{

		AffineTransform stackAT = txStack.remove(txStack.size() - 1);
		//TODO: m00 holds the save count, I could check it now for certainty, it should equals the save count
		if (Tx != stackAT)
		{
			System.err.println("setTransform not paired with getTransform!!!!! " + (new Throwable().getStackTrace()[1]));
		}

		delegate.restore();
	}

	private ArrayList<AffineTransform> txStack = new ArrayList<AffineTransform>();

	/**
	 * Get and set transform will now ONLY call save restore on the delegate
	 * Note this saves the matrix only
	 *
	 * @returns null
	 */
	@Override
	public AffineTransform getTransform()
	{
		delegate.save(Canvas.MATRIX_SAVE_FLAG);
		AffineTransform at = new AffineTransform();
		at.setTransform(delegate.getSaveCount(), 0, 0, 0, 0, 0);
		txStack.add(at);
		return at;
	}


	@Override
	public void translate(int tx, int ty)
	{
		delegate.translate((float) tx, (float) ty);
	}

	@Override
	public void translate(double tx, double ty)
	{
		delegate.translate((float) tx, (float) ty);
	}

	@Override
	public Paint getPaint()
	{
		throw new UnsupportedOperationException();
	}


	@Override
	public void setBackground(Color color)
	{
		throw new UnsupportedOperationException();//delegate.setBackground(toColor(color));
	}

	@Override
	public Color getBackground()
	{
		throw new UnsupportedOperationException();//return fromColor(delegate.getBackground());
	}

	@Override
	public Stroke getStroke()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void clip(Shape s)
	{
		//TODO: should I not ignore these now? , Region.Op.REPLACE); is part of my issue
		// see http://stackoverflow.com/questions/29100772/android-canvas-cliprect-removing-existing-clip
		//ignored for now
	}

	@Override
	public Composite getComposite()
	{
		return currentComposite;
	}

	@Override
	public void setComposite(Composite composite)
	{
		currentComposite = composite;
	}

	//Note not used due to matrix set/get bugs in canvas (from hardware accel)
	public static AffineTransform fromMatrix(Matrix m)
	{
		float[] mat = new float[9];
		m.getValues(mat);
		//http://stackoverflow.com/questions/3534642/how-to-map-javas-affinetransform-to-androids-matrix
		AffineTransform at = new AffineTransform(mat[0], mat[4], mat[1], mat[3], mat[2], mat[5]);
		return at;
	}

	public static Matrix toMatrix(AffineTransform at)
	{
		//http://stackoverflow.com/questions/3534642/how-to-map-javas-affinetransform-to-androids-matrix
		// just believe me...
		float[] fmat = new float[]
				{
						(float) at.getScaleX(), (float) at.getShearX(), (float) at.getTranslateX(),
						(float) at.getScaleY(), (float) at.getShearY(), (float) at.getTranslateY(), 0.0f, 0.0f, 1.0f
				};

		Matrix ret = new Matrix();
		ret.setValues(fmat);
		return ret;
	}

	public static android.graphics.RectF toRectF(Rectangle2D.Double r)
	{
		return new android.graphics.RectF((float) r.getX(), (float) r.getY(), (float) (r.getX() + r.getWidth()), (float) (r.getY() + r.getHeight()));
	}

	public static android.graphics.RectF toRectF(Rectangle2D.Float r)
	{
		return new android.graphics.RectF((float) r.getX(), (float) r.getY(), (float) (r.getX() + r.getWidth()), (float) (r.getY() + r.getHeight()));
	}

	public static android.graphics.RectF toRectF(Arc2D.Double r)
	{
		return new android.graphics.RectF((float) r.getX(), (float) r.getY(), (float) (r.getX() + r.getWidth()), (float) (r.getY() + r.getHeight()));
	}

	public static android.graphics.RectF toRectF(Arc2D.Float r)
	{
		return new android.graphics.RectF((float) r.getX(), (float) r.getY(), (float) (r.getX() + r.getWidth()), (float) (r.getY() + r.getHeight()));
	}

	public static android.graphics.RectF toRectF(Ellipse2D.Float r)
	{
		return new android.graphics.RectF((float) r.getX(), (float) r.getY(), (float) (r.getX() + r.getWidth()), (float) (r.getY() + r.getHeight()));
	}

	public static android.graphics.RectF toRectF(Ellipse2D.Double r)
	{
		return new android.graphics.RectF((float) r.getX(), (float) r.getY(), (float) (r.getX() + r.getWidth()), (float) (r.getY() + r.getHeight()));
	}

	public static float[] toPnts(Line2D.Double l)
	{
		return new float[]{(float) l.getX1(), (float) l.getY1(), (float) l.getX2(), (float) l.getY2()};
	}

	public static float[] toPnts(Line2D.Float l)
	{
		return new float[]{(float) l.getX1(), (float) l.getY1(), (float) l.getX2(), (float) l.getY2()};
	}

	public static Path toPath(Path2D path2d)
	{
		return toPath(path2d.getPathIterator(null), path2d.getWindingRule(), 1.0f);
	}

	public static Path toPath(Area a)
	{
		return toPath(a.getPathIterator(null), Path2D.WIND_NON_ZERO, 1.0f);
	}

	public static Path toPath(Path2D path2d, float scale)
	{
		return toPath(path2d.getPathIterator(null), path2d.getWindingRule(), scale);
	}

	public static Path toPath(Area a, float scale)
	{
		return toPath(a.getPathIterator(null), Path2D.WIND_NON_ZERO, scale);
	}

/**
 * scale to avoid blurry mess
 */
	public static Path toPath(PathIterator pi, int windingRule, float scale)
	{
		//https://developer.android.com/reference/android/graphics/Path.html
		Path path = new Path();
		path.setFillType(windingRule == Path2D.WIND_NON_ZERO ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);

		float[] fs = new float[6];
		while (!pi.isDone())
		{
			int segType = pi.currentSegment(fs);
			if (segType == PathIterator.SEG_MOVETO)
			{
				path.moveTo(fs[0] * scale, fs[1] * scale);
			}
			else if (segType == PathIterator.SEG_LINETO)
			{
				path.lineTo(fs[0] * scale, fs[1] * scale);
			}
			else if (segType == PathIterator.SEG_QUADTO)
			{
				path.quadTo(fs[0] * scale, fs[1] * scale, fs[2] * scale, fs[3] * scale);
			}
			else if (segType == PathIterator.SEG_CUBICTO)
			{
				path.cubicTo(fs[0] * scale, fs[1] * scale, fs[2] * scale, fs[3] * scale, fs[4] * scale, fs[5] * scale);
			}
			else if (segType == PathIterator.SEG_CLOSE)
			{
				path.close();
			}
			else
			{
				throw new RuntimeException("ArrrghHH! bad seg type " + segType);
			}

			pi.next();
		}


		return path;
	}
	
	private android.graphics.Paint pen2 = new android.graphics.Paint();
	private  Matrix inv = new Matrix();
	//see http://stackoverflow.com/questions/16090607/blurry-offset-paths-when-canvas-is-scaled-under-hardware-acceleration
	private void drawPath(Canvas canvas, Path path, final android.graphics.Paint pen) {
	    canvas.save();

	    // get the current matrix
	    Matrix mat = canvas.getMatrix();

	    // reverse the effects of the current matrix	   
	    mat.invert(inv);
	    canvas.concat(inv);

	    // transform the path
	    path.transform(mat);

	    // get the scale for transforming the Paint
	    float[] pts = {0, 0, 1, 0}; // two points 1 unit away from each other
	    mat.mapPoints(pts);
	    float scale = (float) Math.sqrt(Math.pow(pts[0]-pts[2], 2) + Math.pow(pts[1]-pts[3], 2));

	    // copy the existing Paint	   
	    pen2.set(pen);

	    // scale the Paint
	    pen2.setStrokeMiter(pen.getStrokeMiter()*scale);
	    pen2.setStrokeWidth(pen.getStrokeWidth()*scale);

	    // draw the path
	    canvas.drawPath(path, pen2);

	    canvas.restore();
	}


}
