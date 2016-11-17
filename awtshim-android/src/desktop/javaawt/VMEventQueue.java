package desktop.javaawt;

import java.lang.reflect.InvocationTargetException;

import android.os.Handler;
import javaawt.EventQueue;

public class VMEventQueue extends EventQueue
{
	public static void invokeAndWait(Runnable runnable)  throws InvocationTargetException, InterruptedException
	{
		//Activity.runOnUiThread(runnable);// not static :(

		Handler h = new Handler();
		h.post(runnable);
	}
}
