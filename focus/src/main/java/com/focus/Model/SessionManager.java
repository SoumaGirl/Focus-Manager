package com.focus.Model;

public class SessionManager {
    private static int currentUserId = 0; // Default value to represent no user logged in

    // Set the current user ID
    public static void setCurrentUserId(int userId) {
        currentUserId = userId;
        System.out.println("Session set for user ID: " + userId);
    }

    // Get the current user ID
    public static int getCurrentUserId() {
        return currentUserId;
    }

    // Check if there is a valid user session
    public static boolean isUserSignedIn() {
        System.out.println("Checking session: Is user signed in? " + (currentUserId != 0));
        return currentUserId != 0;
    }
}

