package com.example.clubhub;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class partially developed by Amrita Thirumalai for their project that I assisted with debugging
 * Only used to generate times to pick from
 */
public class Time {
    private int hr;
    private int min;

    public Time() {
        hr = 0;
        min = 0;
    }

    public Time(int hr, int min) {
        this.hr = hr;
        this.min = min;
    }

    public Time(Time time) {
        this.hr = time.getHour();
        this.min = time.getMinute();
    }

    public int getHour() {
        return hr;
    }

    public int getMinute() {
        return min;
    }


    /**
     * Increments this time by 15 minutes forward
     */
    public void increment() {
        if (min == 45) {
            if (hr == 23) {
                hr = 0;
            }
            else {
                hr++;
            }
            min = 0;
        }
        else {
            min += 15;
        }
    }

    /**
     * Returns a string representation of this time in standard time
     * I added this method to the preexisting class
     * @return a string of this time in standard time
     */
    public String giveStringStandardTime(){
        String timeStr;
        if(hr < 12){
            timeStr = this.toString() + " AM";
        }
        else if(hr == 12){
            timeStr = this.toString() + " PM";
        }
        else{
            timeStr = new Time(hr-12, min) + " PM";
        }
        return timeStr;
    }

    /**
     * Helper class to generate all time separated by 15 minutes in military time
     * I added this method to the preexisting class
     * @param start
     * @param end
     * @return
     */
    public static ArrayList<String> giveListTimesBetween(Time start, Time end){

        ArrayList<String> allTimes = new ArrayList<String>();
        end.increment();
        do {
            allTimes.add(start.giveStringStandardTime());
            start.increment();
        }
        while(!start.equals(end));

        return allTimes;
    }


    public String toString() {
        String minutes = "";
        if (min < 15) {
            minutes = "00";
        }
        else {
            minutes = Integer.toString(min);
        }
        return Integer.toString(hr) + ":" + minutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return hr == time.hr &&
                min == time.min;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hr, min);
    }
}