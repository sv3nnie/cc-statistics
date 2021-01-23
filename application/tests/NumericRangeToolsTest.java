package application.tests;

import application.utils.ValidationUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumericRangeToolsTest {
    //         * @subcontract value within valid range {
//     *   @requires 0 <= percentage <= 100;
//     *   @ensures \result = true;
//     * }
    @Test
    public void testValidatePercentageRequiresPercentage5EnsuresTrue() {
        //arrange
        int percentage = 5;

        //act
        boolean result = ValidationUtils.validatePercentage(percentage);

        //assert
        assertEquals(true, result);


    }

    //    * @subcontract value out of range low {
//     * @requires percentage < 0;
//     * @ensures \result = false;
//     * }
    @Test
    public void testValidatePercentageRequiresNegativePercentageEnsuresFalse() {
        //arrange
        int percentage = -8;

        //act
        boolean result = ValidationUtils.validatePercentage(percentage);

        //assert
        assertEquals(false, result);
    }

    //     * @subcontract value out of range high {
//     * @requires percentage > 100;
//     * @signals \result = false;
//     * }
    @Test
    public void testValidatePercentageRequiresPercentage105EnsuresFalse() {
// arrange
        int percentage = 105;

        //act
        boolean result = ValidationUtils.validatePercentage(percentage);

        //assert
        assertEquals(false, result);
    }
}