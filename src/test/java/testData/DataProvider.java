package testData;

public class DataProvider {

    @org.testng.annotations.DataProvider(name = "wrongEmailFormat")
    public static Object[][] wrongEmailFormatData() {
        return new Object[][] {
                { "invalidemail.com" },      // Nedostaje @ simbol
                { "invalid@domain" },         // Nedostaje domen
                { "invalid@domain." },        // Nedostaje ekstenzija domena
                { "@no-local-part.com" },     // Nedostaje lokalni deo (pre @)
                { "user@.com" },              // Nedostaje ime domena
                { "user@domain..com" },       // Dvostruka tačka u domenu
                { "user@domain@com.com" },    // Dva @ simbola
                { "user@domain#com.com" }     // Nevalidan karakter (#)
        };
    }
}
