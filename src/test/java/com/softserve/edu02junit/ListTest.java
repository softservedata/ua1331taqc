package com.softserve.edu02junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.softserve.edu.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

    @Test
    public void checkList() {
        List<Person> expected = List.of(new Person("Ivan", 21),
                new Person("Petro", 22));
        //
        List<Person> actual = new ArrayList<>();
        actual.add(new Person("Ivan", 21));
        actual.add(new Person("Petro", 22));
        //
        assertEquals(expected, actual);
        System.out.println("\t\t@checkList executed");
    }

}
