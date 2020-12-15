package Day4;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;


/**
 * ValidationTests
 */
class ValidationTests {





    @Test void testValidByrPasses() {
        assertEquals(true, Validations.validByr("1937"));
    }

    @Test void testValidIryPasses() {
        assertEquals(true, Validations.validIyr("2017"));
    }

    @Test void testValidEyrPasses() {
        assertEquals(true, Validations.validEyr("2020"));
    }

    @Test void testValidHgtPasses() {
        assertEquals(true, Validations.validHcl("183cm"));
    }

    @Test void testValidHclPasses() {
        assertEquals(true, Validations.validHcl("#fffffd"));
    }

    // @Test void testEcl() {
    //     assertEquals(true, Validations.validE(map1.get("ecl")));
    // }

    @Test void testValidPidPasses() {
        assertEquals(true, Validations.validPid("860033327"));
    }

    @Test void testValidPass() {
        HashMap<String,String> map1 = new HashMap<String,String>();
        map1.put("ecl","gry");
        map1.put("pid","860033327");
        map1.put("eyr","2020");
        map1.put("hcl","#fffffd");
        map1.put("byr","1937");
        map1.put("iyr","2017");
        map1.put("cid","147");
        map1.put("hgt","183cm");

        assertEquals(true, Validations.validPassport(map1));
    }
}