package mavenizer.objectPO;

import org.testng.annotations.DataProvider;

public class DataProviders {

    private String testUserEmail = "5555@tut.by";
    private String testUserPassword = "5555";


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
