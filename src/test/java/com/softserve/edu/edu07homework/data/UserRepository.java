package com.softserve.edu.edu07homework.data;


public final class UserRepository {

    private UserRepository() {
    }

    public static User getDefault() {
        return  getValid();
    }

    public static User getValid() {
        return new User()
                .setEmail("test01@gmail.com")
                .setPassword("MyTest2025!")
                //.setPassword(System.getenv("USER_PASSWORD"))
                .setUsername("Test01");
    }

    public static User getNonvalidUser() {
        return new User()
                .setEmail("test02@gmail.com")
                .setPassword("MyTest2025")
                .setUsername("Test01");
    }

    public static User getNonvalidEmail() {
        return new User()
                .setEmail("test02@gmail.com")
                .setPassword("MyTest2025!")
                .setUsername("Test01");
    }
    public static User getNonvalidPassword() {
        return new User()
                .setEmail("test01@gmail.com")
                .setPassword("MyTest2025")
                .setUsername("Test01");
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
