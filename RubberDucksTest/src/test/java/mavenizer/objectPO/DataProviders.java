package mavenizer.objectPO;

import org.testng.annotations.DataProvider;

public class DataProviders {
    private String testUserEmail = "cwoyb@mailto.plus";
    private String testUserPassword = "12345";

    @DataProvider(name = "ValidCredentials")
    public Object[][] dataForLognInWithValidCredentials () {
        return new Object[][]{
                {testUserEmail, testUserPassword}
        };
    }

    @DataProvider(name = "WrongCredentials")
    public Object[][] dataForLognInWithWrongCredentials () {
        return new Object[][]{
                {"aaa@gmail.com", testUserPassword},
                {testUserEmail, "7777"},
        };
    }

}
