package com.example.otpsms;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class Splash extends Activity{
	
//

		
		@Override
		public void onCreate (Bundle savedInstanceState)
		{
			
			
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_splash);
			
			new Handler ().postDelayed(new Runnable ()
			{
				@Override
				public void run ()
				{
					startActivity ( new Intent ( Splash.this, MainMenu.class));
					
					finish ();
				}
			}, 100 );
		}

	}
