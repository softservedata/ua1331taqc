package com.softserve.edu.projectstructureles2.pageobj.data;

public final class UserRepository {
    private UserRepository() {
    }

    public static User getDefault() {
        return  getValid();
    }

    public static User getValid() {
        //return new User("cicada32073@mailshan.com","Qwerty_1", "Qwerty1");
        return new User()
                .setEmail("cicada32073@mailshan.com")
                .setPassword("Qwerty_1")
                //.setPassword(System.getenv("USER_PASSWORD"))
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
