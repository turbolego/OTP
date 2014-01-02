package com.example.otpsms;
public class Sms {
	 
    //private variables
    int _id;
    String _tlf;
    String _keyfile;
    //
    String _navn;
    //
 
    // Empty constructor
    public Sms(){
 
    }
    // constructor
    public Sms(int id, String tlf, String _keyfile, String _navn){
        this._id = id;
        this._tlf = tlf;
        this._keyfile = _keyfile;
        this._navn = _navn;

    }
 
    // constructor
    public Sms(String tlf, String _keyfile, String _navn){
        this._tlf = tlf;
        this._keyfile = _keyfile;
        this._navn = _navn;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
 
    // setting id
    public void setID(int id){
        this._id = id;
    }
 
    // getting tlf
    public String getTlf(){
        return this._tlf;
    }
 
    // setting tlf
    public void setTlf(String tlf){
        this._tlf = tlf;
    }
 
    // getting keyfile
    public String getKeyfile(){
        return this._keyfile;
    }
 
    // setting keyfile
    public void setKeyfile(String keyfile){
        this._keyfile = keyfile;
    }
    
    //
    
 // getting keyfile
    public String getNavn(){
        return this._navn;
    }
 
    // setting keyfile
    public void setNavn(String navn){
        this._navn = navn;
    }
    
    //
}