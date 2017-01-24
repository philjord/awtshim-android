package javaawt.image;

public class VMImageProducer implements ImageProducer
{
	private Object delegate = null;

	public VMImageProducer(Object delegate)
	{
		this.delegate = delegate;
	}
	
	public Object getDelegate()
	{
		return delegate;
	}
}
