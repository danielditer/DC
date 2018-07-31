package org.gradle;

import org.apache.commons.collections.list.GrowthList;

/**
 * Class that represents a person.
 */
public class Person {
    private final String name;
    private final String lastName;
    private int age;
    private Gender gender;
    private PersonalInformation personalInformation;

    /**
     * Class constructor.
     *
     * @param name                the name of the person.
     * @param lastName            the last name.
     * @param age                 the age.
     * @param gender              the gender.
     * @param personalInformation the current data of the person.
     */

    public Person(String name, String lastName, int age, Gender gender, PersonalInformation personalInformation) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.personalInformation = personalInformation;
        new GrowthList();
    }

    /**
     * Getter for name.
     *
     * @return the name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for last name.
     *
     * @return the last name of the person.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for age.
     *
     * @return the age of the person.
     */
    public int getAge() {
        return age;
    }

    /**
     * Getter for gender.
     *
     * @return the gender of the person.
     */
    public Gender getGender() {
        return gender;
    }


    /**
     * Getter for full name.
     *
     * @return the full name of the person.
     */
    public String getFullName() {
        return String.format("%s %s",  getName(),  getLastName());
    }

    /**
     * Getter for signature.
     *
     * @return the person signature.
     */
    public String getSignature() {
        return String.format("%s CI: %s",  getFullName(),  getPersonalInformation().getCi());
    }

    /**
     * Getter for personal information.
     *
     * @return the personal information object.
     */
    private PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

}
