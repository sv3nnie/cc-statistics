package application.tests;

import application.utils.ValidationUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateToolsTest {
//    * @subcontract 31 days in month {
//     * @requires (month = = 1 | | month = = 3 | | month = = 5 | | month = = 7 | |
//                *month = = 8 | | month = = 10 | | month = = 12) && 1 <= day <= 31;
//     * @ensures \result = true;
//     * }

    @Test
    public void testValidateDateRequiresMonth1day10EnsuresTrue() {

        //arrange

        int year = 0;

        //act
        boolean result1 = ValidationUtils.validateDate(10, 1, year); //this should be true
        boolean result2 = ValidationUtils.validateDate(11, 3, year); //this should be true
        boolean result3 = ValidationUtils.validateDate(31, 5, year); //this should be true
        boolean result4 = ValidationUtils.validateDate(1, 7, year); //this should be true

        //assert
        assertEquals(true, result1);
        assertEquals(true, result2);
        assertEquals(true, result3);
        assertEquals(true, result4);

    }

    //      * @subcontract 30 days in month {
//     * @requires (month = = 4 | | month = = 6 | | month = = 9 | | month = = 11) &&
//     * 1 <= day <= 30;
//     * @ensures \result = true;
//     * }
    @Test
    public void testValidateDateRequiresMonth4Day5EnsuresTrue() {
        //arrange

        int year = 0;

        //act
        boolean result1 = ValidationUtils.validateDate(5, 4, year); //this should be true
        boolean result2 = ValidationUtils.validateDate(30, 6, year); //this should be true
        boolean result3 = ValidationUtils.validateDate(19, 9, year); //this should be true
        boolean result4 = ValidationUtils.validateDate(1, 11, year); //this should be true

        //assert
        assertEquals(true, result1);
        assertEquals(true, result2);
        assertEquals(true, result3);
        assertEquals(true, result4);
    }

    //* @subcontract 29 days in month {
//     * @requires month == 2 && 1 <= day <= 29 &&
//     * (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
//     * @ensures \result = true;
//     * }
    @Test
    public void testValidateDateRequiresMonth2Day28Year2020EnsuresTrue() {
        //arrange
        int day = 28;
        int month = 2;
        int year = 2020;

        //act
        boolean result = ValidationUtils.validateDate(day, month, year);//this should be true
        boolean result2 = ValidationUtils.validateDate(10, month, 2024);//this should be true

        //assert
        assertEquals(true, result);
        assertEquals(true, result2);
    }
//     * @subcontract 28 days in month {
//     * @requires month == 2 && 1 <= day <= 28 &&
//     * (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0));
//     * @ensures \result = true;
//     * }
    @Test
    public void testValidateDateRequiresMonth2Day27Year2019EnsuresTrue(){
        //arrange
        int day = 27;
        int month = 2;
        int year = 2019;

        //act
        boolean result = ValidationUtils.validateDate(day, month,year);//this should be true

        //assert
        assertEquals(true, result);
    }
//        * @subcontract all other cases {
//     * @requires no other accepting precondition;
//     * @ensures \result = false;
//     * }
    @Test
    public void testValidateDateRequiresInvalidEnsuresFalse(){
        //arrange


        //act
        boolean result1 = ValidationUtils.validateDate(-1, 13, 2021);//this should be false
        boolean result2 = ValidationUtils.validateDate(-5, 9, 2019);//this should be false
        boolean result3 = ValidationUtils.validateDate(30, 2, 2021);//this should be false
        boolean result4 = ValidationUtils.validateDate(29, 2, 2019);//this should be false

        //assert
        assertEquals(false, result1);
        assertEquals(false, result2);
        assertEquals(false, result3);
        assertEquals(false, result4);
    }
}