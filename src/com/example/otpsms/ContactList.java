package com.example.otpsms;

import java.util.List;


import com.example.otpsms.Contact;
import com.example.otpsms.ContactDbHandler;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

//

import java.util.ArrayList;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


//

public class ContactList extends ListActivity{
	
	// Tilbake til hovedmenyen dersom "back" trykkes
	public void onBackPressed() {
	    
	    
	    Intent startActivity = new Intent(ContactList.this, MainMenu.class);
        startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(startActivity);
		
		finish();
	}
	//
//
	private ArrayList<String> results = new ArrayList<String>();
    private String TABLE_SCORES = ContactDbHandler.TABLE_SCORES;
    private SQLiteDatabase newDB;
	//

   

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contactlist);
		
		//
		openAndQueryDatabase();
        
        displayResultList();
		//
		
		
        
	}
	
	//
	//
	//
	private void displayResultList() {
        TextView tView = new TextView(this);
        tView.setText("Personer som er registrert:");
        getListView().addHeaderView(tView);
        
        //
        //
        
        //
        final ArrayAdapter<String> aa;
        aa = new ArrayAdapter<String>(this,
        							android.R.layout.simple_list_item_1,
        							results);
        
        // Bind the array adapter to the listview
        setListAdapter(aa);
        //
        
       
        
        getListView().setTextFilterEnabled(true);
        
        //
        ListView listView = getListView();
		listView.setTextFilterEnabled(true);
 
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
			    Toast.makeText(getApplicationContext(),
				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			    
	            Log.d("done: ",(String) ((TextView) view).getText() );
	            System.out.println("Pos: " + position + " id: " + id);
	            System.out.println("Out: " + ((TextView) view).getText());

	            String ss = (String) ((TextView) view).getText();
	            String[] parts = ss.split("\\r?\\n");
	            System.out.println("RIKTIG ID: " + parts[3].trim());
	            
	         
	            String idToString = String.valueOf(parts[3].trim());

	            String contactSelect  = idToString;

	            	Intent i = new Intent(ContactList.this, EditContact.class);
	            	i.putExtra("contactselection", contactSelect);
	            	startActivity(i); 
	//
	            
	            

			}
		});
		//
         
    }
    private void openAndQueryDatabase() {
        
        	ContactDbHandler db = new ContactDbHandler(ContactList.this);
   		 
           
            List<Contact> scores = db.getAllScores();       
     
            for (Contact cn : scores) {
                String log = "Id: "+cn.getID()+" ,Tlf: " + cn.getTlf() + " , Keyfile: " + cn.getKeyfile() + " , Navn: " + cn.getNavn();
                    // Writing Contacts to log
                results.add("Navn: " + cn.getNavn() + "\nTlf: " + cn.getTlf() + "\nKeyfile: " + cn.getKeyfile() + "\n"+cn.getID());

            Log.d("done: ", log);
            }
 
    
    }
	//

    
    //
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mainontacts, menu);
		return true;
	}
	
//
	//
	//
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.action_nykontakt:
	    	
	    	
	      Toast.makeText(this, "Legg til ny kontakt...", Toast.LENGTH_SHORT)
	          .show();
	      Intent startActivity = new Intent(ContactList.this, AddContact.class);
	        startActivity(startActivity);
			
			finish();
	      break;
	      //
	    case R.id.action_off:
	    	
	    	
		      Toast.makeText(this, "Off...", Toast.LENGTH_SHORT)
		          .show();

				
				finish();
		      break;
	      //
	      
	    case R.id.action_mainmenu:
	    	
	    	Intent startActivity2 = new Intent(ContactList.this, MainMenu.class);
	        startActivity2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        startActivity(startActivity2);
			
			finish();
	    	

	    default:
	      break;
	    }

	    return true;
	  } 
	
	

}
