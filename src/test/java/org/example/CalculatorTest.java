package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {

    MyCalculator calc;

    @BeforeAll
    void beforeAll(){
        System.out.println("Before All");
    }

    @BeforeEach
    void setup(){
        calc = new MyCalculator();
        System.out.println("In BeforeEach");
        System.out.println("JUNIT_TEST_FLAG:"+System.getenv("JUNIT_TEST_FLAG"));
    }

    @AfterEach
    void cleanup(){
        System.out.println("Clean up");
    }

    @Test
    @DisplayName("Test Add")
    @EnabledOnOs(OS.WINDOWS)
    @EnabledIfEnvironmentVariable(named = "JUNIT_TEST_FLAG", matches = "....")
    void testAdd(){
        System.out.println("In Test Add");
        int expected = 5;
        int actual = calc.add(2,3);
        assertEquals(expected, actual, "Add Operation");
    }

    @Test
    void testDivide(){
        System.out.println("In Divide");
        assertThrows(java.lang.ArithmeticException.class, ()-> calc.divide(8,0), "Divide Error" );
    }


    @Nested
    @Tag("serial test")
    class AddTest {

        @Test
        void addTest(){
            int expected = 5;
            int output = calc.add(2,3);
            assertEquals(expected, output);
        }

        @Test
        void deleteTest(){
            int expected = 2;
            int output = calc.divide(4,2);
            assertEquals(expected, output);
        }

    }

    @Test
    @DisplayName("Test Assert All")
    void testAll(){
        assertAll(
                () -> assertEquals(3,calc.add(1,2)),
                () -> assertEquals(8,calc.divide(16,2))
        );
    }

}
