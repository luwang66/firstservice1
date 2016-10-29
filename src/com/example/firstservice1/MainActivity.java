package com.example.firstservice1;

import com.example.firstservice1.MyService.MyBinder;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button start,stop,bind,unbind,getData;
	private final static String TAG="MyService";
	private MyBinder myBinder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=(Button)findViewById(R.id.start);
        stop=(Button)findViewById(R.id.stop);
        bind=(Button)findViewById(R.id.bind);
        unbind=(Button)findViewById(R.id.unbind);
        getData=(Button)findViewById(R.id.getData);
        final Intent intent=new Intent();
        intent.setAction("iet.jxufe.cn.android.MyService");
        start.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				startService(intent);				
			}
		});
        stop.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				stopService(intent);				
			}
		});
        bind.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				bindService(intent, conn,Service.BIND_AUTO_CREATE);	
			}
		}); 
        unbind.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				unbindService(conn);				
			}
		});
        getData.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {			
				Toast.makeText(MainActivity.this, "Count="+myBinder.getCount(), Toast.LENGTH_LONG).show();				
			}
		});
    }
    private ServiceConnection conn=new ServiceConnection() {		
		public void onServiceDisconnected(ComponentName name) {			
			Log.i(TAG,"MainActivity onServiceDisconnected invoked!");
		}		
		public void onServiceConnected(ComponentName name, IBinder service) {	
			Log.i(TAG,"MainActivity onServiceConnected invoked!");
			myBinder=(MyBinder)service;
		}
	};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
