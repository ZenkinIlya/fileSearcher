package ru.sber.zenkin.Init;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FilesFormatterTest {

    //Тест форматирования файлов
    @Test
    public void formatFiles() {
        List<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("qwerty.txt");
        stringArrayList.add("heLLo world.txt");
        stringArrayList.add("world.db");
        stringArrayList.add("Driver.dll");

        assertEquals(stringArrayList, FilesFormatter.formatFiles("   qwerty.txt \"  "
                + "heLLo world.txt" + "\" world.db \"" + "Driver.dll"));
    }
}