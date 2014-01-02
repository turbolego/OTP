package com.example.otpsms;
import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class SmsDbHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "smsManager";
 
    // Smss table tlf
    static final String TABLE_SCORES = "scores";
 
    // Smss Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TLF = "tlf";
    private static final String KEY_LOSS = "keyfile";
    //
    private static final String KEY_NAVN = "navn";
    //
 
    public SmsDbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SCORES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TLF + " STRING,"
                + KEY_LOSS + " STRING," + KEY_NAVN + " STRING" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new tlf
    void addWin(Sms tlf) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_TLF, tlf.getTlf()); // Keyfile Name
        values.put(KEY_LOSS, tlf.getKeyfile()); // Keyfile Keyfile
        values.put(KEY_NAVN, tlf.getNavn()); // Keyfile Keyfile

        // Inserting Row
        db.insert(TABLE_SCORES, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
 
    // Getting single tlf
    Sms getSms(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_SCORES, new String[] { KEY_ID,
                KEY_TLF, KEY_LOSS, KEY_NAVN }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Sms tlf = new Sms(Integer.parseInt(cursor.getString(0)),
        				cursor.getString(1), 
        				cursor.getString(2),
        				cursor.getString(3));
        // return tlf
        return tlf;
    }
 
    // Getting All Smss
    public List<Sms> getAllScores() {
        List<Sms> smsList = new ArrayList<Sms>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SCORES;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Sms tlf = new Sms();
                tlf.setID(Integer.parseInt(cursor.getString(0)));
                tlf.setTlf((cursor.getString(1)));
                tlf.setKeyfile((cursor.getString(2)));
                tlf.setNavn((cursor.getString(3)));

                // Adding tlf to list
                smsList.add(tlf);
            } while (cursor.moveToNext());
        }
 
        // return tlf list
        return smsList;
    }
 
    // Updating single tlf
    public int updateSms(Sms tlf) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_TLF, tlf.getTlf());
        values.put(KEY_LOSS, tlf.getKeyfile());
        values.put(KEY_NAVN, tlf.getNavn());

 
        // updating row
        return db.update(TABLE_SCORES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(tlf.getID()) });
    }
 
    // Deleting single tlf
    public void deleteSms(Sms tlf) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SCORES, KEY_ID + " = ?",
                new String[] { String.valueOf(tlf.getID())});
        
        
        db.close();
    }
    
    // updating sequence
    public void updateSequence() {
        SQLiteDatabase db = this.getWritableDatabase();
       
        				db.execSQL("update SQLITE_SEQUENCE set seq = 0 where name ='TABLE_SCORES'");
        
		   System.out.println("sequence updated!");

        
        
        db.close();
    }
 
    // Getting scores Count
    public int getSmssCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SCORES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
 
}