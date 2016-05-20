package com.psi_stud.arturas.ggdb;

/**
 * Created by Arturas on 2015-12-14.
 */
public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String email;
    private String wishlist;
    private String gamelist;
    boolean isLogedIn;

    public User(String username, String password, String firstName, String lastName, String birthDate, String email, String gamelistID, String wishlistID){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.gamelist = gamelist;
        this.wishlist = wishlist;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {

        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setUsername(String username) {
        this.username = username;

    }
    public boolean isLogedIn() {
        return isLogedIn;
    }

    public void setIsLogedIn(boolean isLogedIn) {
        this.isLogedIn = isLogedIn;
    }

    public String getWishlist() {
        return wishlist;
    }

    public String getGamelist() {
        return gamelist;
    }
}
