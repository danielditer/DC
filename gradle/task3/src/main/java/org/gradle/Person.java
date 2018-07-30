package org.gradle;

import org.apache.commons.collections.list.GrowthList;

/**
 * Class that represents a person.
 */
public class Person {
    private final String name;

    /**
     * Class constructor.
     *
     * @param name the name of the person.
     */
    public Person(String name) {
        this.name = name;
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
}
