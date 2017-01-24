package javaawt;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.InvocationTargetException;

import javaawt.EventQueue;

public class VMEventQueue extends EventQueue
{
	public static Activity activity;

	public static void invokeAndWait(Runnable runnable) throws InvocationTargetException, InterruptedException
	{
		//java.awt.EventQueue.invokeAndWait(runnable);
		runOnUiThreadAndWait(runnable) ;
	}
	
	public static boolean isDispatchThread()
	{
		return Looper.getMainLooper().getThread() == Thread.currentThread();
		//return java.awt.EventQueue.isDispatchThread();
	}

	public static void invokeLater(Runnable runnable)
	{
		activity.runOnUiThread(runnable);
		//java.awt.EventQueue.invokeLater(runnable);
	}


		public static void runOnUiThreadAndWait(  final Runnable runnable) {
			synchronized (runnable) {
				activity.runOnUiThread(new Runnable() {
					public void run() {
						runnable.run();
						synchronized (runnable) {
							runnable.notify();
						}
					}
				});
				try {
					runnable.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

}
