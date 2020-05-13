package com.example.clubhub;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Abstract class to be extended by anything that will be
 * added to the firebase database
 */
public abstract class DatabaseObject {

    private String path;
    private String firebaseID;

    /**
     * Default constructor for the DatabaseObject class
     * Creates path and firebaseID as empty Strings
     */
    public DatabaseObject(){
        path = "";
        firebaseID = "";
    }

    /**
     * Creates a new DatabaseObject with a given path and firebaseID
     * @param path the location of the object in the database
     * @param firebaseID the ID of the object in the database
     */
    public DatabaseObject(String path, String firebaseID){
        this.path= path;
        this.firebaseID = firebaseID;
    }

    /**
     * Can be called to update the data stored in the database with
     * the data currently stored in this object
     */
    public void updateObjectDatabase(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(path);
        ref.child(firebaseID).setValue(this);
    }

    /**
     * Removes this object from the firebase database
     */
    public void removeObjectDatabase(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(path);
        ref.child(firebaseID).removeValue();
    }

    //region Getters and setters

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the firebaseID
     */
    public String getFirebaseID() {
        return firebaseID;
    }

    /**
     * @param firebaseID the firebaseID to set
     */
    public void setFirebaseID(String firebaseID) {
        this.firebaseID = firebaseID;
    }
    //endregion
}
