package com.ashwija.mvn;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MavenTemplateMainTest {

    static MavenTemplateMain mavenTemplateMain;

    @BeforeAll
    static void beforeAll(){
        mavenTemplateMain = new MavenTemplateMain();
    }

    @Test
    void sum() {
        assertEquals(7,mavenTemplateMain.sum(4,3));
    }
}