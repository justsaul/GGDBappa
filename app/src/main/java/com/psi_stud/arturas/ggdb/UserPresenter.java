package com.psi_stud.arturas.ggdb;

public class UserPresenter {

    public boolean isUserSignedUp(int id) {
        boolean value = false;
        if(id > -1) {
            value = true;
        }
        return value;
    }

    public boolean isAppropriateAge(int id, int ageToCompare) {
        boolean value = false;
        if(id >= ageToCompare) {
            value = true;
        }
        return value;
    }
}
