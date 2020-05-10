package com.example.clubhub;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// Class to hold information about club meetings
@SuppressWarnings("WeakerAccess")
public class MeetingInfo {

    //region Static day name Strings
    // Static name strings to prevent potential hardcoding in other classes
    public static final String MONDAY_STR = "Monday";
    public static final String TUESDAY_STR = "Tuesday";
    public static final String WEDNESDAY_STR = "Wednesday";
    public static final String THURSDAY_STR = "Thursday";
    public static final String FRIDAY_STR = "Friday";
    public static final String SATURDAY_STR = "Saturday";
    public static final String SUNDAY_STR = "Sunday";

    public static final ArrayList<String> allDays = new ArrayList<>(
            Arrays.asList(MONDAY_STR, TUESDAY_STR, WEDNESDAY_STR, THURSDAY_STR, FRIDAY_STR, SATURDAY_STR, SUNDAY_STR));
    //endregion

    private HashMap<String, Boolean> meetingDays;
    private String meetingStartTime;
    private String meetingEndTime;


    //region Constructors

    /**
     * Default constructor for the meeting info
     */
    public MeetingInfo(){
        meetingDays = new HashMap<>();
        initiateMeetingDays();
        meetingStartTime = "";
        meetingEndTime = "";
    }

    /**
     * Creates a new MeetingInfo with given days, beginning time, and end time
     * @param meetingDaysArray ArrayList of strings of meeting days
     * @param meetingStartTime String of the meeting start time
     * @param meetingEndTime String of the meeting end time
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public MeetingInfo(ArrayList<String> meetingDaysArray, String meetingStartTime, String meetingEndTime){
        this.meetingDays = new HashMap<>();
        initiateMeetingDays();
        addMeetingDaysFromArrayList(meetingDaysArray);
        this.meetingStartTime = meetingStartTime;
        this.meetingEndTime = meetingEndTime;
    }
    //endregion

    /**
     * Returns true if this club meets on a given day
     * false otherwise
     * @param dayStr the day to be check if this club meets
     * @return true if this club meets of a given dya, false if not
     */
    public boolean meetsOnDay(String dayStr){
        return meetingDays.keySet().contains(dayStr);
    }

    // TODO Will need firebase?

    /**
     * Sets if this club meets on a given day
     * @param dayStr the day's status to be set
     * @param state true if this club meets on the given day, false if not
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setDayStatus(String dayStr, boolean state){
        meetingDays.replace(dayStr, state);
    }

    //region Private methods

    /**
     * Used in initialization to add the given days as as meeting
     * @param newMeetingDays days to be added as meeting days
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addMeetingDaysFromArrayList(ArrayList<String> newMeetingDays){
        initiateMeetingDays();
        for(String day : newMeetingDays){
            if (allDays.contains(day)) {
                meetingDays.replace(day, true);
            }
        }
    }

    /**
     * Initializes the allDays HashMap to contain all days
     * with value false
     */
    private void initiateMeetingDays(){
        for(String day : allDays){
            meetingDays.put(day, false);
        }
    }
    // endregion

    //region Getters and Setters

    /**
     * @return the HashMap tracking meeting days
     */
    public HashMap<String, Boolean> getMeetingDays() {
        return meetingDays;
    }

    /**
     * @param meetingDays the HashMap of meeting days to set
     */
    public void setMeetingDays(HashMap<String, Boolean> meetingDays) {
        this.meetingDays = meetingDays;
    }

    /**
     * @return the meeting start time
     */
    public String getMeetingStartTime() {
        return meetingStartTime;
    }

    /**
     * @param meetingStartTime the meeting start time to set
     */
    public void setMeetingStartTime(String meetingStartTime) {
        this.meetingStartTime = meetingStartTime;
    }

    /**
     * @return the meeting end time
     */
    public String getMeetingEndTime() {
        return meetingEndTime;
    }

    /**
     * @param meetingEndTime the meeting end time
     */
    public void setMeetingEndTime(String meetingEndTime) {
        this.meetingEndTime = meetingEndTime;
    }

    /**
     * @return an ArrayList of the days this club meets
     */
    public ArrayList<String> onlyMeetingDays(){
        ArrayList<String> days = new ArrayList<>();
        for(String day : allDays){
            if(meetingDays.get(day) && meetingDays.get(day) != null)
                days.add(day);
        }
        return days;
    }

    //endregion
}
