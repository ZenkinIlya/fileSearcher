package ru.sber.zenkin.Init;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NotTrackedFilesInitializerTest {

    //Тест инициализации игнорирумых файлов
    @Test
    public void init() {
        NotTrackedFilesInitializer notTrackedFilesInitializer = new NotTrackedFilesInitializer();

        String input = "   qwerty.txt \"  " + "heLLo world.txt" + "\" world.db \"" + "Driver.dll";

        //Ввод в консоль строки input
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        List<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("qwerty.txt");
        stringArrayList.add("heLLo world.txt");
        stringArrayList.add("world.db");
        stringArrayList.add("Driver.dll");

        assertEquals(stringArrayList, notTrackedFilesInitializer.init());
    }
}