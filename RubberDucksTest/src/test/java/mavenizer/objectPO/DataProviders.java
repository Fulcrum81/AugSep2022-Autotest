package mavenizer.objectPO;

import org.testng.annotations.DataProvider;

public class DataProviders {

    private String testUserEmail = "5555@tut.by";
    private String testUserWrongEmail = "aaa@gmail.com";
    private String testUserPassword = "5555";
    private String testUserWrongPassword = "7777";


    @DataProvider(name = "ValidCredentials")
    public Object[][] dataForLognInWithValidCredentials () {
        return new Object[][]{
                {testUserEmail, testUserPassword}
        };
    }


    @DataProvider(name = "WrongCredentials")
    public Object[][] dataForLognInWithWrongCredentials () {
        return new Object[][]{
                {testUserWrongEmail, testUserPassword},
                {testUserEmail, testUserWrongPassword},
                {testUserWrongEmail, testUserWrongPassword},
        };
    }


}
