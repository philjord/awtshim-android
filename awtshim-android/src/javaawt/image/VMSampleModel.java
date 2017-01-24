package javaawt.image;

public class VMSampleModel extends SampleModel
{
	private Object delegate = null;

	public VMSampleModel(Object delegate)
	{
		this.delegate = delegate;
	}

	@Override
	public Object getDelegate()
	{
		return delegate;
	}
	
	
	@Override
	public int getNumBands()
	{
		throw new UnsupportedOperationException();//return delegate.getNumBands();
	}

	@Override
	public int getDataType()
	{
		throw new UnsupportedOperationException();//return delegate.getDataType();
	}
}
