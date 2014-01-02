package com.example.otpsms;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity{
	
	// Avslutter appen dersom "back" trykkes
	public void onBackPressed() {
		
		finish();
	}
	//
	
	Button buttonSms;
	Button buttonContacts;
	Button buttonAbout;
	Button buttonClose;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		//Read sms button start
		buttonSms = (Button) findViewById(R.id.buttonSms);
		buttonSms.setOnClickListener(new OnClickListener ()
		{
			@Override
			public void onClick (View v)
			{
				Intent startActivity = new Intent(MainMenu.this, SmsList.class);
		        startActivity(startActivity);
				
				finish();
			}
		});
		//Read sms button slutt
		
		
		//contacts button start
		buttonContacts = (Button) findViewById(R.id.buttonContacts);
		buttonContacts.setOnClickListener(new OnClickListener ()
		{
			@Override
			public void onClick (View v)
			{
				Intent startActivity = new Intent(MainMenu.this, ContactList.class);
		        startActivity(startActivity);
				
				finish();
			}
		});
		//contacts button slutt
		
		
		
		//contacts button start
		buttonAbout = (Button) findViewById(R.id.buttonAbout);
		buttonAbout.setOnClickListener(new OnClickListener ()
		{
			@Override
			public void onClick (View v)
			{
				Intent startActivity = new Intent(MainMenu.this, About.class);
		        startActivity(startActivity);
				
				finish();
			}
		});
		//contacts button slutt
		
		//Close button start
		buttonClose = (Button) findViewById(R.id.buttonQuit);
		buttonClose.setOnClickListener(new OnClickListener ()
		{
			@Override
			public void onClick (View v)
			{
				
				finish();
			}
		});
		//Close button slutt
	}


}

