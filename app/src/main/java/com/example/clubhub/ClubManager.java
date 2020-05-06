package com.example.clubhub;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * Helper class to manager Club objects to create and get
 * Club objects from firebase
 */
public class ClubManager{

	// Local version of the clubs stored in the database to prevent asynch issues
	// and getting information
	private static HashMap<String, Club> clubs = new HashMap<>();

	private static FirebaseUser mUser;


	/**
	 * Creates a new Club object and stores it in the database with
	 * a random, unique key
	 * Assigns the club a club school ID
	 * @param name the name of the new club
	 * @param description the description of the new club
	 */
	public static void createClub(String name, String description, MeetingInfo meetingInfo) {

		mUser = FirebaseAuth.getInstance().getCurrentUser();

		DatabaseReference clubsRef = FirebaseDatabase.getInstance().getReference(Club.CLUB_PATH).push();
		String key = clubsRef.getKey();

		String schoolID = StudentManager.getStudent(mUser.getUid()).getSchoolID();
		String userID = mUser.getUid();

		clubsRef.setValue(new Club(name, key, schoolID, userID, description, meetingInfo));

		// Adds this club to the school's clubs in the database
		SchoolManager.getSchool(schoolID).addClubFirebase(key);

		// Adds this club to the leader club list of the creator in the database
		StudentManager.getStudent(userID).addUserToClubAsLeaderFirebase(key);

	}

	public static void deleteClub(String clubID){

		Club thisClub = clubs.get(clubID);

		// Clean up members
		for(String memberID : thisClub.getmIDs()){
			Student member = StudentManager.getStudent(memberID);
			member.removeClubFromMemberFirebase(clubID);
		}

		// Clean up leaders
		for(String leaderID : thisClub.getlIDs()){
			Student leader = StudentManager.getStudent(leaderID);
			leader.removeClubFromLeaderFirebase(clubID);
		}

		// Clean up teacher
		if(thisClub.hasTeacherAdviser()){
			Teacher teacher = TeacherManager.getTeacher(thisClub.getTeacherID());
			teacher.removeAdvisingClub(clubID);
		}


		// Clean up school
		School school = SchoolManager.getSchool(thisClub.getSchoolID());
		school.removeClubFirebase(clubID);

		// Delete club from data base
		thisClub.removeObjectDatabase();
		clubs.remove(clubID);
	}
	
	/**
	 * Returns a Club object with the given ID
	 * @param ID The ID used to find the Club
	 * @return The Club with the given IO
	 */
	public static Club getClub(String ID) {
		return clubs.get(ID);
	}

	/**
	 * Puts a club into the ClubManager's club HashMap
	 * Only to be used by the initiateClubs method
	 * @param ID
	 * @param s
	 */
	public static void putClub(String ID, Club s) {
		clubs.put(ID, s);
	}


}
