package com.example.otpsms;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditSms extends Activity {
	
	// Tilbake til smslist dersom "back" trykkes
	public void onBackPressed() {
	    
	    
	    Intent startActivity = new Intent(EditSms.this, SmsList.class);
        startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(startActivity);
		
		finish();
	}
	//

	Button buttonClose;
	Button buttonSave;
	Button buttonDelete;

	
	int SmsID = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editsms);
		
		//
		Intent intent = getIntent();
		String smsSelect = intent.getExtras().getString("smsselection");
		System.out.println("id (long): " + smsSelect);
		//
		
		//
		//
		
		int idOfSms = 0;

		try {
			idOfSms = Integer.parseInt(smsSelect.toString());
		} catch(NumberFormatException nfe) {
		   System.out.println("Kunne ikke parse id-string! " + nfe);
		} 
		
		//
		 
        
		
		//
		SmsDbHandler db = new SmsDbHandler(EditSms.this);
  		 
        
        List<Sms> scores = db.getAllScores();       
 
        for (Sms cn : scores) {
        	if (cn.getID() == idOfSms)
        	{
            String log = "Id: "+cn.getID()+" ,Tlf: " + cn.getTlf() + " , Keyfile: " + cn.getKeyfile() + " , Navn: " + cn.getNavn();
                // Writing Sms to log
            
          
            
            SmsID = cn.getID();
            
            EditText text = (EditText) findViewById(R.id.editText1);
            text.setText(cn.getNavn());
            
            EditText text2 = (EditText) findViewById(R.id.editText2);            
            text2.setText(String.valueOf(cn.getTlf()));
            
            EditText text3 = (EditText) findViewById(R.id.editText3);
            text3.setText(String.valueOf(cn.getKeyfile()));

        Log.d("done: ", log);
        
        
        	}
        	else
        	{
                System.out.println("nope");

        	}
            //
            buttonSave = (Button)findViewById(R.id.buttonSave);
            final EditText text   = (EditText)findViewById(R.id.editText1);
            final EditText text2   = (EditText)findViewById(R.id.editText2);
            final EditText text3  = (EditText)findViewById(R.id.editText3);


            buttonSave.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Log.v("EditText", text.getText().toString());
                		SmsDbHandler db = new SmsDbHandler(EditSms.this);
                		
                		String tlfString = "";

                		
                		try {
                			tlfString = text2.getText().toString();
                		} catch(NumberFormatException nfe) {
                		   System.out.println("Kunne ikke parse tlf-string! " + nfe);
                		} 
                		
                		String datoString = "";

                		
                		try {
                			datoString = text3.getText().toString();
                		} catch(NumberFormatException nfe) {
                		   System.out.println("Kunne ikke parse dato-string! " + nfe);
                		} 
                		
                		
                        db.updateSms(new Sms(SmsID,tlfString, datoString, text.getText().toString()));

                        
                        //
        				Intent startActivity = new Intent(EditSms.this, SmsList.class);
        		        startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        		        startActivity(startActivity);
        				
        				finish();
                        //
                    }
                });
           
            //
            buttonDelete = (Button)findViewById(R.id.buttonDelete);
            buttonDelete.setOnClickListener(
            		
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Log.v("EditText", text.getText().toString());
                		SmsDbHandler db = new SmsDbHandler(EditSms.this);
                		
                		String tlfString = "";

                		
                		try {
                			tlfString = text2.getText().toString();
                		} catch(NumberFormatException nfe) {
                		   System.out.println("Kunne ikke parse tlf-string! " + nfe);
                		} 
                		
                		String datoString = "";

                		
                		try {
                			datoString = text3.getText().toString();
                		} catch(NumberFormatException nfe) {
                		   System.out.println("Kunne ikke parse dato-string! " + nfe);
                		} 
                		
                		
                        db.deleteSms(new Sms(SmsID,tlfString, datoString, text.getText().toString()));
                        db.updateSequence();
                        
                        //
        				Intent startActivity = new Intent(EditSms.this, SmsList.class);
        		        startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        		        startActivity(startActivity);
        				
        				finish();
                        //
                    }
                });
           
        }    
 
        
		
		//Close button start
		buttonClose = (Button) findViewById(R.id.buttonClose);
		buttonClose.setOnClickListener(new OnClickListener ()
		{
			@Override
			public void onClick (View v)
			{
				Intent startActivity = new Intent(EditSms.this, SmsList.class);
		        startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        startActivity(startActivity);
				
				finish();
			}
		});
		//Close button slutt
	}


}
