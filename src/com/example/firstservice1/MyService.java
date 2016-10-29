package com.example.firstservice1;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	private static final String TAG = "MyService";
	private int count=0;
	private boolean quit=false;
	private MyBinder myBinder=new MyBinder();
	public class MyBinder extends Binder {
		public MyBinder() {
			Log.i(TAG, "MyBinder Constructure invoked!");
		}
		public int getCount() {
			return count;
		}
	}
	public IBinder onBind(Intent arg0) {
		Log.i(TAG, "MyService onBind invoked!");
		return myBinder;
	}

	public void onCreate() {
		Log.i(TAG, "MyService onCreate invoked!");
		super.onCreate();
		new Thread(){
			public void run(){
				while(!quit){
					try{
						Thread.sleep(500);
						count++;
						System.out.println();
					}catch (Exception e) {
						e.printStackTrace();
					}
				}				
			}
		}.start();

	}

	public void onDestroy() {
		Log.i(TAG, "MyService onDestroy invoked!");
		super.onDestroy();
		quit=true;
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "MyService onStartCommand invoked!");
		return super.onStartCommand(intent, flags, startId);
	}

	public boolean onUnbind(Intent intent) {
		Log.i(TAG, "MyService onUnbind invoked!");
		return super.onUnbind(intent);
	}

	public void onRebind(Intent intent) {
		Log.i(TAG, "MyService onRebind invoked!");
		super.onRebind(intent);
	}
}
