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

public class AddContact extends Activity {
	
	// Tilbake til contactlist dersom "back" trykkes
	public void onBackPressed() {
	    
	    
	    Intent startActivity = new Intent(AddContact.this, ContactList.class);
        startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(startActivity);
		
		finish();
	}
	//

	Button buttonClose;
	Button buttonSave;
	
	int ContactID = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);

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
                		ContactDbHandler db = new ContactDbHandler(AddContact.this);
                		
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
                		
                        db.addWin(new Contact(tlfString, datoString, text.getText().toString()));

                        
                        //
        				Intent startActivity = new Intent(AddContact.this, ContactList.class);
        		        startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        		        startActivity(startActivity);
        				
        				finish();
                        //
                    }
                });
            
          
        
		
		//Close button start
		buttonClose = (Button) findViewById(R.id.buttonClose);
		buttonClose.setOnClickListener(new OnClickListener ()
		{
			@Override
			public void onClick (View v)
			{
				Intent startActivity = new Intent(AddContact.this, ContactList.class);
		        startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        startActivity(startActivity);
				
				finish();
			}
		});
		//Close button slutt
	}

}
