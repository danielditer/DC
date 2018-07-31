package org.gradle;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing 'Person' class.
 */
public class PersonTest {

    private Person person;
    private PersonalInformation personalInformation;

    /**
     * Initializes variables.
     */
    @Before
    public void setUp() {
        final int ci = 6543210;
        final int phoneNumber = 77665544;
        personalInformation = new PersonalInformation(CivilStatus.MARRIED, "Av. Simon Lopez #2643",
                ci, phoneNumber, "Automation Engineer");
        final int age = 50;
        person = new Person("Larry", "Page", age, Gender.MALE, personalInformation);
    }

    /**
     * Method for testing 'getName()' method.
     */
    @Test
    public void canConstructAPersonWithAName() {

        assertEquals("Larry", person.getName());
    }

    /**
     * Method for testing 'getLastName()' method.
     */
    @Test
    public void canConstructAPersonWithALastName() {

        assertEquals("Page", person.getLastName());
    }

    /**
     * Method for testing 'getAge()' method.
     */
    @Test
    public void canConstructAPersonWithAge() {

        final int expected = 50;
        assertEquals(expected, person.getAge());
    }

    /**
     * Method for testing 'getGender()' method.
     */
    @Test
    public void canConstructAPersonWithGender() {

        assertEquals(Gender.MALE, person.getGender());
    }

    /**
     * Method for testing 'getCivilStatus()' method.
     */
    @Test
    public void canConstructAPersonWithCivilStatus() {

        assertEquals(CivilStatus.MARRIED, personalInformation.getCivilStatus());
    }

    /**
     * Method for testing 'getAddress()' method.
     */
    @Test
    public void canConstructAPersonWithAddress() {

        assertEquals("Av. Simon Lopez #2643", personalInformation.getAddress());
    }

    /**
     * Method for testing 'getCi()' method.
     */
    @Test
    public void canConstructAPersonWithCI() {

        final int expected = 6543210;
        assertEquals(expected, personalInformation.getCi());
    }

    /**
     * Method for testing 'getCi()' method.
     */
    @Test
    public void canConstructAPersonWithPhoneNumber() {

        final int expected = 77665544;
        assertEquals(expected, personalInformation.getPhoneNumber());
    }

    /**
     * Method for testing 'getCi()' method.
     */
    @Test
    public void canConstructAPersonWithOccupation() {

        assertEquals("Automation Engineer", personalInformation.getOccupation());
    }

    /**
     * Method for testing 'getFullName()' method.
     */
    @Test
    public void canGetAPersonFullName() {

        assertEquals("Larry Page", person.getFullName());
    }

    /**
     * Method for testing 'getSignature()' method.
     */
    @Test
    public void canGetAPersonSignature() {

        assertEquals("Larry Page CI: 6543210", person.getSignature());
    }
}
