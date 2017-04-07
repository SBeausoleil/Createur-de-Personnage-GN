package com.sb.cdp;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.sb.util.ConfirmationModel;
import com.sb.util.DraftModel;

public class User implements LibraryPermissionHolder {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String email;
    // Digested password will be accessed by reading it directly in the database. The access key for each password will be the encrypted email.

    // DATA FIELDS FOR ORGANISATIONS
    private Map<RPG, String> organisationNotes;
    private Map<String, DraftModel<PlayerCharacter>> characters;
    private Set<Library> allowedLibraries;

    public User(String firstName, String lastName, LocalDate birthday, String email) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.birthday = birthday;
	this.email = email;

	characters = new TreeMap<>();
	allowedLibraries = new HashSet<>();
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

    public Map<String, DraftModel<PlayerCharacter>> getCharacters() {
	return characters;
    }

    public void addAsConfirmed(PlayerCharacter pc) {
	DraftModel<PlayerCharacter> dm = characters.get(pc.getName());
	if (dm == null) {
	    dm = new DraftModel<>();
	    characters.put(pc.getName(), dm);
	}
	dm.setConfirmed(pc);
    }

    public void addAsPending(PlayerCharacter pc) {
	DraftModel<PlayerCharacter> dm = characters.get(pc.getName());
	if (dm == null) {
	    dm = new DraftModel<>();
	    characters.put(pc.getName(), dm);
	}
	dm.setPending(pc);
    }

    public void addAsDraft(PlayerCharacter pc) {
	DraftModel<PlayerCharacter> dm = characters.get(pc.getName());
	if (dm == null) {
	    dm = new DraftModel<>();
	    characters.put(pc.getName(), dm);
	}
	dm.setDraft(pc);
    }

    public void allow(Library library, String characterName) {
	ConfirmationModel<PlayerCharacter> character = characters.get(characterName);
	if (character != null) {
	    character.onSets((c) -> c.allow(library));
	    allowedLibraries.add(library);;
	} else
	    throw new IllegalArgumentException("This user has no character named \"" + characterName + "\"");
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

    /**
     * Returns the organisationNotes.
     * 
     * @return the organisationNotes
     */
    public String getOrganisationNotes(RPG rpg) {
	return organisationNotes.get(rpg);
    }

    /**
     * Sets the value of organisationNotes to that of the parameter.
     * 
     * @param organisationNotes
     *            the organisationNotes to set
     */
    public void setOrganisationNotes(RPG rpg, String organisationNotes) {
	this.organisationNotes.put(rpg, organisationNotes);
    }
}
