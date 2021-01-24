package application.tests;

import application.utils.ValidationUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.xml.transform.Result;

public class URLFormatTest {
    // @subcontract Url is correctly formatted
    // @requires URL = https:// or http:// CharCount(Part1)>= 1 . CharCount(Part2)>=
    // 1.CharCount(Part3)>= 1
    // @ensures \result = true

    @Test

    public void testvalidateURLrequiresNoHttpEnsuresFalse() {
        String URL = "Youtube.com";

        boolean result = ValidationUtils.validateURL(URL);

        assertEquals(false, result);
    }

    @Test
    public void testvalidateURLCorectURLEnsuresTrue() {
        String URL = "https://www.youtube.com";

        boolean result = ValidationUtils.validateURL(URL);

        assertEquals(true, result);
    }

    @Test
    public void testValidateURLRequiresToplvlDomainNameEnsuresTrue() {
        String URL = "https://www.youtube";

        boolean result = ValidationUtils.validateURL(URL);

        assertEquals(false, result);
    }

    @Test
    public void testValidateURLNoDomainNameEnsuresFalse() {
        String URL = "https://www..com";

        boolean result = ValidationUtils.validateURL(URL);

        assertEquals(false, result);
    }
}