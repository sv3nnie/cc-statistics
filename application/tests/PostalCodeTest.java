package application.tests;


import application.utils.ValidationUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostalCodeTest {
//     * @subcontract null postalCode {
//     * @requires postalCode == null;
//     * @signals (NullPointerException) postalCode == null;
//     * }
@Test
    public void testFormatPostalCodeRequiresPostalCodeNullSignalsNullPointerException() {
    Assertions.assertThrows(NullPointerException.class, ()->{
        throw new NullPointerException();

    });

    String postalCode = "";

    String result = ValidationUtils.formatPostalCode(postalCode);

}
//   * @subcontract valid postalCode {
//     * @requires Integer.valueOf(postalCode.trim ().substring(0, 4)) > 999 &&
//     * Integer.valueOf(postalCode.trim().substring(0, 4)) <= 9999 &&
//     * postalCode.trim().substring(4).trim().length == 2 &&
//     * 'A' <= postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z' &&
//     * 'A' <= postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z';
//     * @ensures \result = postalCode.trim().substring(0, 4) + " " +
//     * postalCode.trim().substring(4).trim().toUpperCase()
//                * }
    @Test
    public void testFormatPostalCodeRequiresValidPostalCodeEnsuresFormattedPostalCode(){
    String postalCode = "4578px";

    String result = ValidationUtils.formatPostalCode(postalCode);

    System.out.println(result);
    }
//      * @subcontract invalid postalCode {
//     * @requires no other valid precondition;
//     * @signals (IllegalArgumentException);
//     * }
    @Test
    public void testFormatPostalCodeRequiresInvalidPostalCodeSignalsIllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            throw new IllegalArgumentException();

        });
        String postalCode = "016x v";

        String result = ValidationUtils.formatPostalCode(postalCode);
    }
}