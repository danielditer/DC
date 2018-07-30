package org.gradle;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Class for testing 'Person' class.
 */
public class PersonTest {
    /**
     * Method for testing 'getName()' method.
     */
    @Test
    public void canConstructAPersonWithAName() {
        Person person = new Person("Larry");
        assertEquals("Larry", person.getName());
    }
}
