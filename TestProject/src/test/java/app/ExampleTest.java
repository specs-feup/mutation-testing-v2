package app;

import static org.junit.Assert.*;

import org.junit.Test;
import app.TestCases;

public class ExampleTest {

    @Test
    public void testDeclarationWithInitialization() {
        assertEquals(3, TestCases.declarationWithInitialization(1));		
    }

}
