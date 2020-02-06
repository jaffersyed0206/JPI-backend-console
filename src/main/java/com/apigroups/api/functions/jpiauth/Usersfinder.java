package com.apigroups.api.functions.jpiauth;

import java.util.List;

import com.apigroups.api.requests.authentication.users.Projectusers;

public class Usersfinder {
    public static Projectusers GetProjectEmailandPassUser(List<Projectusers> projectusers, String email, String password) {
        for (Projectusers docs: projectusers) {
            if (docs.getEmail().equals(email) == true && docs.getPassword().equals(password) == true) {
                return docs;
            }
        }
        return null;
    }
    public static Projectusers GetProjectUsernameandPassUser(List<Projectusers> projectusers , String username, String password) {
        for (Projectusers docs: projectusers) {
            if (docs.getUsername().equals(username) == true && docs.getPassword().equals(password) == true) {
                return docs;
            }
        }
        return null;
    }
    public static Projectusers GetProjectUseruid(List<Projectusers> projectusers, String useruid) {
        for (Projectusers docs: projectusers) {
            if (docs.getUseruid().equals(useruid) == true) {
                return docs;
            }
        }
        return null;
    }
}