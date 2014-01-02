package com.example.otpsms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewSms extends Activity implements OnClickListener {
	
	// Tilbake til smslist dersom "back" trykkes
			public void onBackPressed() {
			    
			    
			    Intent startActivity = new Intent(NewSms.this, SmsList.class);
		        startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        startActivity(startActivity);
				
				finish();
			}
			//

	EditText meld;
    EditText nr;
	Button lukk;
	Button sendSms;
	
	//
	//
	//
	
    static String encrypt(String text, final String key) {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            res += (char)((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }
	
	//
	//
	//



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsms);
		
		nr = (EditText) findViewById(R.id.EditText01);
        meld = (EditText) findViewById(R.id.editText1);

		
		//
		//Close button start
		sendSms = (Button) findViewById(R.id.send);
		sendSms.setOnClickListener(new OnClickListener ()
		{
			@Override
			public void onClick (View v)
			{
				
				//
				             
	                String nummer = nr.getText().toString();
	                String melding = meld.getText().toString();                 
	                if (nr.length()>0 && meld.length()>0)  {
	                	
	                	
	                	//
	                	//
	                	//
	                	String key = null;
	        			
	        			//
	        			InputStream fis = null;
	        		    try {
	        		        fis = v.getResources().openRawResource(R.raw.keyfile1);
	        		        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

	        		        String line = br.readLine();

	        		        System.out.println("LINE:" + line);
	        				key = line;

	        		    
	        		} catch (Exception e) {
	        		    e.printStackTrace();
	        		} finally {
	        		    if (fis != null) {
	        		        try { fis.close(); }
	        		        catch (IOException ignored) {}
	        		    }
	        		}
	        			//
	        		  //String key = "VIGENERECIPHER"; //neste key i lista

	        			
	        	        //String encryptedtext = "YMXT";
	        	        String encryptedtext = melding;
	        	        System.out.println(encrypt(encryptedtext, key)); //dekryptert melding
	        	        String encrypted = encrypt(encryptedtext, key);
	        		    //
	                	
	                	SmsManager smsManager = SmsManager.getDefault();
	      		  smsManager.sendTextMessage(nummer, null, encrypted, null, null);
	      		  
	      		//Intent startActivity = new Intent(NewSms.this, SmsList.class);
	      		Intent startActivity = new Intent(NewSms.this, SmsList.class);
		        startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        startActivity(startActivity);
				
				finish();
	                }
	                	
	                	else
	                    Toast.makeText(getBaseContext(), 
	                        "Please enter both phone number and message.", 
	                        Toast.LENGTH_SHORT).show();
	            
	               
				
				
			}
		});
		//Close button slutt
		//
		lukk = (Button) findViewById(R.id.lukk);
		lukk.setOnClickListener(new OnClickListener ()
		{
			@Override
			public void onClick (View v)
			{
				Intent startActivity = new Intent(NewSms.this, SmsList.class);
		        startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        startActivity(startActivity);
				
				finish();
			}
		});
	


}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
}
