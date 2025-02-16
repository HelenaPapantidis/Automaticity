package testData;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "wrongEmailFormat")
    public static Object[][] wrongEmailFormatData() {
        return new Object[][]{
                {"invalidemail.com"},      // Missing @ symbol
                {"invalid@domain"},         // Missing domain
                {"invalid@domain."},        // Missing domain extension
                {"@no-local-part.com"},     // Missing local part (before @)
                {"user@.com"},              // Missing domain name
                {"user@domain..com"},       // Double dot in the domain
                {"user@domain@com.com"},    // Two @ symbols
                {"user@domain#com.com"}     // Invalid character (#)
        };
    }
}
