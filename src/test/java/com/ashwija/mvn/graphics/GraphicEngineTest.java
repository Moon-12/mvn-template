package com.ashwija.mvn.graphics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphicEngineTest {
    private static final String BORDER_STRING = "*".repeat(80); // 80 asterisks
    private static final String EMPTY_LINE = "*" + " ".repeat(78) + "*"; // * + 78 spaces + *

    // Helper method to check common properties
    private void assertCommonHeaderProperties(String[] lines, String title, int expectedLines, int titleLineIndex) {
        assertAll("Header block properties",
                () -> assertEquals(expectedLines, lines.length, "Number of lines should match expected"),
                () -> assertEquals(BORDER_STRING, lines[0], "First line should be the border"),
                () -> assertEquals(BORDER_STRING, lines[lines.length - 1], "Last line should be the border"),
                () -> assertTrue(lines[titleLineIndex].contains(title), "Title should be present in the expected line"),
                () -> assertTrue(lines[titleLineIndex].trim().length() == 80, "Title line should be 80 characters"),
                // Check all lines are 80 characters
                () -> assertAll("All lines should be 80 characters",
                        () -> {
                            for (int i = 0; i < lines.length; i++) {
                                assertEquals(80, lines[i].length(), "Line " + (i + 1) + " should be 80 characters");
                            }
                        }
                )
        );
    }

    // Helper method to check padding lines
    private void assertPaddingLines(String[] lines, int paddingAbove, int titleLineIndex) {
        // Check padding lines above
        for (int i = 1; i <= paddingAbove; i++) {
            assertEquals(EMPTY_LINE, lines[i], "Padding line above " + i + " should be empty");
        }
        // Check padding lines below
        for (int i = titleLineIndex + 1; i < lines.length - 1; i++) {
            assertEquals(EMPTY_LINE, lines[i], "Padding line below " + (i - titleLineIndex) + " should be empty");
        }
    }

    @Test
    void testPrintHeaderBlock() {
        String title = "Title";
        String actualOutput = GraphicEngine.printHeaderBlock(1, title);
        String[] lines = actualOutput.split("\n");

        // Expected: 5 lines (border, 1 padding, title, 1 padding, border)
        assertCommonHeaderProperties(lines, title, 5, 2);
        assertPaddingLines(lines, 1, 2);
    }

    @Test
    void testPrintHeaderBlockWithZeroPadding() {
        String title = "Title";
        String actualOutput = GraphicEngine.printHeaderBlock(0, title);
        String[] lines = actualOutput.split("\n");

        // Expected: 3 lines (border, title, border)
        assertCommonHeaderProperties(lines, title, 3, 1);
        // No padding lines to check with padding = 0
    }

    @Test
    void testPrintHeaderBlockWithLongTitle() {
        String title = "This is a Longer Title";
        String actualOutput = GraphicEngine.printHeaderBlock(2, title);
        String[] lines = actualOutput.split("\n");

        // Expected: 7 lines (border, 2 padding, title, 2 padding, border)
        assertCommonHeaderProperties(lines, title, 7, 3);
        assertPaddingLines(lines, 2, 3);
    }
}