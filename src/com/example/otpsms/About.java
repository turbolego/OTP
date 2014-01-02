package com.example.otpsms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class About extends Activity {
	
	// Tilbake til hovedmenyen dersom "back" trykkes
		public void onBackPressed() {
		    
		    
		    Intent startActivity = new Intent(About.this, MainMenu.class);
	        startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        startActivity(startActivity);
			
			finish();
		}
		//
	
	Button buttonClose;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		//Close button start
				buttonClose = (Button) findViewById(R.id.buttonClose);
				buttonClose.setOnClickListener(new OnClickListener ()
				{
					@Override
					public void onClick (View v)
					{
						Intent startActivity = new Intent(About.this, MainMenu.class);
				        startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				        startActivity(startActivity);
						
						finish();
					}
				});
				//Close button slutt
	}

}
