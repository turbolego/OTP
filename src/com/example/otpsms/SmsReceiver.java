package com.example.otpsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class SmsReceiver extends BroadcastReceiver{
	

	//dekrypt-kode:
	
    static String decrypt(String text, final String key) {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            res += (char)((c - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }

    //innkommende sms kode:
	
	public void onReceive(Context context, Intent intent)
	{

		Bundle bundle=intent.getExtras();
		
		Object[] messages=(Object[])bundle.get("pdus");
		SmsMessage[] sms=new SmsMessage[messages.length];
		
		for(int n=0;n<messages.length;n++){
			sms[n]=SmsMessage.createFromPdu((byte[]) messages[n]);
		}
		
		for(SmsMessage msg:sms){
			

			String tlfString = msg.getOriginatingAddress();
			String smsString = msg.getMessageBody();
			
			
			String key = null;
			
			//tekstfil-leser kode:
			
			InputStream fis = null;
		    try {
		        fis = context.getResources().openRawResource(R.raw.keyfile1);
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
			
	        //Dekrypterer innkommende melding med "VIGENERECIPHER" som er den neste n¿kkelen i lista "keyfile1.txt"
	        //og lagrer dekryptert melding i databasen

			SmsDbHandler db = new SmsDbHandler(context);

	        String encryptedtext = smsString;
	        System.out.println(decrypt(encryptedtext, key)); //dekryptert melding
	        String decrypted = decrypt(encryptedtext, key);

	        db.addWin(new Sms(tlfString, "keyfile1.txt", decrypted));

	        
   		 
             
		}
	}
}