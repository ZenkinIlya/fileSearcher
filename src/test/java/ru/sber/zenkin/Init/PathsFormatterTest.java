package ru.sber.zenkin.Init;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PathsFormatterTest {

    //Тест форматирования каталогов
    @Test
    public void formatPaths() {
        List<Path> pathArrayList = new ArrayList<>();
        pathArrayList.add(Paths.get("C:\\Program Files\\Windows Kits"));
        pathArrayList.add(Paths.get("C:\\Program Files\\Microsoft Office"));

        assertEquals(pathArrayList, PathsFormatter.formatPaths(" C:\\Program Files\\Windows Kits  " +
                " \" " + "  " + "C:\\Program Files\\Microsoft Office \"" +"C:\\Program Files\\Microsoft Office"));
    }
}