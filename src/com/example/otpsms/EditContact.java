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

public class EditContact extends Activity {
	
	// Tilbake til editsms dersom "back" trykkes
		public void onBackPressed() {
		    
		    
		    Intent startActivity = new Intent(EditContact.this, ContactList.class);
	        startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        startActivity(startActivity);
			
			finish();
		}
		//

	Button buttonClose;
	Button buttonSave;
	Button buttonDelete;

	
	int ContactID = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editsms);
		
		//
		Intent intent = getIntent();
		String contactSelect = intent.getExtras().getString("contactselection");
		System.out.println("id (long): " + contactSelect);
		//
		
		//
		//
		
		int idOfContact = 0;

		try {
			idOfContact = Integer.parseInt(contactSelect.toString());
		} catch(NumberFormatException nfe) {
		   System.out.println("Kunne ikke parse id-string! " + nfe);
		} 
		
		//
		 
        
		
		//
		ContactDbHandler db = new ContactDbHandler(EditContact.this);
  		 
        
        List<Contact> scores = db.getAllScores();       
 
        for (Contact cn : scores) {
        	if (cn.getID() == idOfContact)
        	{
            String log = "Id: "+cn.getID()+" ,Tlf: " + cn.getTlf() + " , Keyfile: " + cn.getKeyfile() + " , Navn: " + cn.getNavn();
                // Writing Contacts to log

            
            ContactID = cn.getID();
            
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
                		ContactDbHandler db = new ContactDbHandler(EditContact.this);
                        //db.updateContact(new Keyfile(ContactID,text2.getText().toString(),text3.getText().toString(),text.getText().toString()));
                		
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
                		
                		
                        db.updateContact(new Contact(ContactID,tlfString, datoString, text.getText().toString()));

                        
                        //
        				Intent startActivity = new Intent(EditContact.this, ContactList.class);
        		        startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        		        startActivity(startActivity);
        				
        				finish();
                        //
                    }
                });
            //
            //DELETE TEST START
            //
            //
            buttonDelete = (Button)findViewById(R.id.buttonDelete);
            buttonDelete.setOnClickListener(
            		
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Log.v("EditText", text.getText().toString());
                		ContactDbHandler db = new ContactDbHandler(EditContact.this);
                		
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
                		
                		
                        db.deleteContact(new Contact(ContactID,tlfString, datoString, text.getText().toString()));
                        db.updateSequence();
                        
   
                        //
        				Intent startActivity = new Intent(EditContact.this, ContactList.class);
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
				Intent startActivity = new Intent(EditContact.this, ContactList.class);
		        startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        startActivity(startActivity);
				
				finish();
			}
		});
		//Close button slutt
	}

}
