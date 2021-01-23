package application.tests;

import application.utils.ValidationUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailToolsTest {
//     * @subcontract no mailbox part {
//     * @requires !mailAddress.contains("@") ||
//     * mailAddress.split("@")[0].length < 1;
//     * @ensures \result = false;
//     * }
    @Test
    public void testValidateEmailRequiresNoAtlanticEnsuresFalse(){
        String mailAddress = "lentysprangershotmail.com";

        boolean result = ValidationUtils.validateEmail(mailAddress);

        assertEquals(false, result);

    }

    @Test
    public void testValidateEmailRequiresMoreThanOneAtlanticOrDotEnsuresFalse(){
        String mailAddress1 = "lentysprangers@hotmail..com";
        String mailAddress2 = "lentysprangers@@hotmail.com";
        String mailAddress3 = "lenty.sprangers@hotmail.com";

        boolean result1 = ValidationUtils.validateEmail(mailAddress1);
        boolean result2 = ValidationUtils.validateEmail(mailAddress2);
        boolean result3 = ValidationUtils.validateEmail(mailAddress3);

        assertEquals(false, result1);
        assertEquals(false, result2);
        assertEquals(true, result3);
    }

}