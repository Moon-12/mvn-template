package com.ashwija.mvn;

import org.junit.jupiter.api.Test;

import java.io.*;

class MavenTemplateMainTest {

    @Test
    void mainTest() throws IOException {
// Prepare test input (simulating user entering "5" and "3")
//        InputStream resourceStream = MavenTemplateMainTest.class
//                .getClassLoader()
//                .getResourceAsStream("test1.txt");
//
//        if (resourceStream == null) {
//            throw new IllegalStateException("test1.txt not found in resources");
//        }
//
//        // Read the file content into a string
//        String fileContent = new String(resourceStream.readAllBytes());
//        resourceStream.close();
//
//        // Use file content as System.in input
//        ByteArrayInputStream testIn = new ByteArrayInputStream(fileContent.getBytes());
//        System.setIn(testIn);
//
//        // Capture System.out output
//        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
//        PrintStream originalOut = System.out;
//        System.setOut(new PrintStream(testOut));
//
//        // Run the main method
//        MavenTemplateMain.main(new String[]{});
//
//        // Restore System.in and System.out
//        System.setIn(System.in);
//        System.setOut(originalOut);
//
//        // Verify the output
//        assertNotNull(testOut.toString());
    }
}