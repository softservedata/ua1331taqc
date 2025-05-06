package com.softserve.homeworks.task13_14.data;

public final class UserRepository {

    private UserRepository() {
    }

    public static User getDefault() {
        return getUserValid();
    }

    public static User getUserValid() {
        //return new User("cicada32073@mailshan.com","Qwerty_1", "Qwerty1");
        return new User()
                .setEmail("samplestest@greencity.com")
                .setPassword("weyt3$Guew^")
                //.setPassword(System.getenv("USER_PASSWORD"))
                .setUsername("Test");
    }

    public static User getUserWrongCredentials() {
        return new User()
                .setEmail("samplestest@greencity.com")
                .setPassword("11weyt3$Guew^11")
                .setUsername("Qwerty1");
    }

    public static User getUserEmptyCredentials() {
        return new User()
                .setEmail("")
                .setPassword("")
                .setUsername("Qwerty1");
    }

    public static User getUserInvalidEmail() {
        return new User()
                .setEmail("samplestestgreencity.com")
                .setPassword("weyt3$Guew^")
                .setUsername("Qwerty1");
    }

    public static User getUserInvalidPassword() {
        return new User()
                .setEmail("samplestest@greencity.com")
                .setPassword("weyt")
                .setUsername("Qwerty1");
    }

    public static User getUserEmptyEmail() {
        return new User()
                .setEmail("")
                .setPassword("weyt3$Guew^")
                .setUsername("Qwerty1");
    }

    public static User getUserEmptyPassword() {
        return new User()
                .setEmail("samplestest@greencity.com")
                .setPassword("")
                .setUsername("Qwerty1");
    }

    /*
    public static List<User> fromList() {
        List<User> lst = new ArrayList<>();
        lst.add(getValidUserQwertyY());
        lst.add(getValidUserMyName());
        return lst;
    }

    public static List<User> fromCsv(String filename) {
        return User.getByLists(new CSVReader(filename).getAllCells());
    }

    public static List<User> fromCsv() {
        return fromCsv("users.csv");
    }

    public List<User> fromExcel(String filename) {
        return User.getByLists(new ExcelReader(filename).getAllCells());
    }

    public List<User> fromExcel() {
        return fromExcel("users.xlsx");
    }
    */
}