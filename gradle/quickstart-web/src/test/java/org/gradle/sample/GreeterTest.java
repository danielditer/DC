package org.gradle.sample;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GreeterTest {
    @Test
    public void getGreetingTest() {
        Greeter greeter = new Greeter();
        try {
            assertEquals("Hello World!", greeter.getGreeting());
        }catch(Exception e) {}
    }
}
