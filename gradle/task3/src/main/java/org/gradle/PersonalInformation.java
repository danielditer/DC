package org.gradle;

/**
 * Class that represents the current personal information of a Person.
 */
public class PersonalInformation {
    private CivilStatus civilStatus;
    private String address;
    private int ci;
    private int phoneNumber;
    private String occupation;

    /**
     * Class constructor.
     *
     * @param civilStatus the civil status.
     * @param address     the current address.
     * @param ci          the 'CI'.
     * @param phoneNumber the current phone number.
     * @param occupation  the current occupation.
     */
    public PersonalInformation(CivilStatus civilStatus, String address, int ci, int phoneNumber, String occupation) {
        this.civilStatus = civilStatus;
        this.address = address;
        this.ci = ci;
        this.phoneNumber = phoneNumber;
        this.occupation = occupation;
    }

    /**
     * Getter for civil status.
     *
     * @return the civil status of the person.
     */
    public CivilStatus getCivilStatus() {
        return civilStatus;
    }

    /**
     * Getter for address.
     *
     * @return the address of the person.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter for 'CI'.
     *
     * @return the 'CI' of the person.
     */
    public int getCi() {
        return ci;
    }

    /**
     * Getter for phone number.
     *
     * @return the phone number of the person.
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Getter for occupation.
     *
     * @return the current occupation of the person.
     */
    public String getOccupation() {
        return occupation;
    }
}
