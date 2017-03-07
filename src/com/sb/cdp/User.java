package com.sb.cdp;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import com.sb.util.ConfirmationModel;

public class User implements LibraryPermissionHolder {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String email;

    private Map<String, ConfirmationModel<PlayerCharacter>> characters;

    private Set<Library> allowedLibraries;

    public User(String firstName, String lastName, LocalDate birthday, String email) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.birthday = birthday;
	this.email = email;
    }

    /**
     * Returns the firstName.
     * 
     * @return the firstName
     */
    public String getFirstName() {
	return firstName;
    }

    /**
     * Sets the value of firstName to that of the parameter.
     * 
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    /**
     * Returns the lastName.
     * 
     * @return the lastName
     */
    public String getLastName() {
	return lastName;
    }

    /**
     * Sets the value of lastName to that of the parameter.
     * 
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    /**
     * Returns the birthday.
     * 
     * @return the birthday
     */
    public LocalDate getBirthday() {
	return birthday;
    }

    /**
     * Sets the value of birthday to that of the parameter.
     * 
     * @param birthday
     *            the birthday to set
     */
    public void setBirthday(LocalDate birthday) {
	this.birthday = birthday;
    }

    /**
     * Returns the email.
     * 
     * @return the email
     */
    public String getEmail() {
	return email;
    }

    /**
     * Sets the value of email to that of the parameter.
     * 
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
	this.email = email;
    }

    public void addAsConfirmed(PlayerCharacter pc) {
	ConfirmationModel<PlayerCharacter> cm = new ConfirmationModel<>(pc);
	characters.put(pc.getName(), cm);
    }

    public void addAsPending(PlayerCharacter pc) {
	ConfirmationModel<PlayerCharacter> cm = new ConfirmationModel<>();
	cm.setPending(pc);
	characters.put(pc.getName(), cm);
    }

    public void allow(Library library, String characterName) {
	allowedLibraries.add(library);
	ConfirmationModel<PlayerCharacter> character = characters.get(characterName);
	if (character != null) {
	    character.getConfirmed().allow(library);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "User [firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday + ", email=" + email
		+ ", characters=" + characters + "]";
    }

    @Override
    public Set<Library> getAllowedLibraries() {
	return allowedLibraries;
    }

    @Override
    public void setAllowedLibraries(Set<Library> allowedLibraries) {
	this.allowedLibraries = allowedLibraries;
    }

    @Override
    public void allow(Library allowedLibrary) {
	allowedLibraries.add(allowedLibrary);
    }
}
