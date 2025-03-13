package com.ashwija.mvn;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MavenTemplateMainTest {


    @Test
    void sum() {
        assertEquals(7,MavenTemplateMain.sum(4,3));
    }

    @Test
    void mainTest() {
// Prepare test input (simulating user entering "5" and "3")
        String input = "5\n3\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());

        // Redirect System.in to our test input
        System.setIn(testIn);

        // Capture System.out output
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));

        // Run the main method
        MavenTemplateMain.main(new String[]{});

        // Restore System.in and System.out
        System.setIn(System.in);
        System.setOut(System.out);

        // Verify the output
        assertEquals("Sum is 8\n", testOut.toString());
    }
}