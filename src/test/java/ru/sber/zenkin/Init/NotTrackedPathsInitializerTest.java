package ru.sber.zenkin.Init;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NotTrackedPathsInitializerTest {

    //Инициализация объекта временной директории
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    //Заполнение временной директории каталогами до самого выполнения теста
    @Before
    public void setFolders() throws IOException {
        temporaryFolder.newFolder("testFolder");
        temporaryFolder.newFolder("testFolder", "childFolder1");
        temporaryFolder.newFolder("testFolder", "childFolder2");
        temporaryFolder.newFolder("testFolder", "childFolder1", "childFolder3");
        temporaryFolder.newFolder("testFolder", "childFolder1", "childFolder4");
        temporaryFolder.newFolder("testFolder", "childFolder2", "childFolder3");
        temporaryFolder.newFolder("testFolder", "childFolder2", "childFolder4");
    }

    //Тест инициализации игнорирумых каталогов
    @Test
    public void init() {
        NotTrackedPathsInitializer notTrackedPathsInitializer= new NotTrackedPathsInitializer();

        List<Path> pathArrayList = new ArrayList<>();
        pathArrayList.add(Paths.get(temporaryFolder.getRoot().toString() + "\\testFolder"));
        pathArrayList.add(Paths.get(temporaryFolder.getRoot().toString() + "\\testFolder\\childFolder2"));

        String input = temporaryFolder.getRoot().toString() + "\\testFolder" + "\"" +temporaryFolder.getRoot().toString() + "\\testFolder\\childFolder2";

        //Ввод в консоль строки input
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals(pathArrayList, notTrackedPathsInitializer.init());
    }
}