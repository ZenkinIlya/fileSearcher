package ru.sber.zenkin;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.sber.zenkin.Init.*;
import ru.sber.zenkin.Utils.Save.ListFileDirectoriesSaverTest;
import ru.sber.zenkin.Utils.Search.ExecutorStarterTest;
import ru.sber.zenkin.Utils.Search.SearcherTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        FilesFormatterTest.class,
        NotTrackedFilesInitializerTest.class,
        NotTrackedPathsInitializerTest.class,
        PathsFormatterTest.class,
        StartPathsInitializerTest.class,
        ListFileDirectoriesSaverTest.class,
        ExecutorStarterTest.class,
        SearcherTest.class
})

//Выполнение всех тестов
public class TestSuite {
}
