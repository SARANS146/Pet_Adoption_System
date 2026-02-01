package com.pet.adoption.util;

public class ActiveAdoptionException extends Exception {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "CANNOT REMOVE PET: ACTIVE ADOPTION EXISTS";
    }
}
