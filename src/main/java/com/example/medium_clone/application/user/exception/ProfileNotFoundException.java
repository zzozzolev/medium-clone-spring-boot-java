package com.example.medium_clone.application.user.exception;

import java.util.NoSuchElementException;

public class ProfileNotFoundException extends NoSuchElementException {

    public ProfileNotFoundException(String username) {
        super(username + " profile not found.");
    }
}
