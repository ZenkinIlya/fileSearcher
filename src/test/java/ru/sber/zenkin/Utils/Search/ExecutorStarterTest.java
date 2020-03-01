package ru.sber.zenkin.Utils.Search;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.sber.zenkin.Data.Data;
import ru.sber.zenkin.Entity.FileRecord;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

public class ExecutorStarterTest {

    //Инициализация объекта временной директории
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    //Заполнение временной директории каталогами и файлами до самого выполнения теста
    @Before
    public void setFolders() throws IOException {
        ArrayList<File> arrayListFolder = new ArrayList<>();
        arrayListFolder.add(temporaryFolder.newFolder("testFolder"));
        arrayListFolder.add(temporaryFolder.newFolder("testFolder", "childFolder1"));
        arrayListFolder.add(temporaryFolder.newFolder("testFolder", "childFolder2"));
        arrayListFolder.add(temporaryFolder.newFolder("testFolder", "childFolder1", "childFolder3"));
        arrayListFolder.add(temporaryFolder.newFolder("testFolder", "childFolder1", "childFolder4"));
        arrayListFolder.add(temporaryFolder.newFolder("testFolder", "childFolder2", "childFolder3"));
        arrayListFolder.add(temporaryFolder.newFolder("testFolder", "childFolder2", "childFolder4"));
        for (File folder: arrayListFolder){
            new File(folder, "a.txt").createNewFile();
            new File(folder, "b.txt").createNewFile();
            new File(folder, "c.txt").createNewFile();
        }
        //Очистка setAllFitPaths перед тем как проходить тест
        Data.clearAllFitPaths();
    }

    //тест поиска файлов в нескольких директориях
    @Test
    public void initSearch() {
        //Список директорий в которых будет осуществляться поиск файлов отдельно друг от друга
        List<Path>starterPaths = new ArrayList<>();
        starterPaths.add(Paths.get(temporaryFolder.getRoot().toString() + "\\testFolder\\childFolder1"));
        starterPaths.add(Paths.get(temporaryFolder.getRoot().toString() + "\\testFolder\\childFolder2\\childFolder3"));
        starterPaths.add(Paths.get(temporaryFolder.getRoot().toString() + "\\testFolder"));
        starterPaths.add(Paths.get(temporaryFolder.getRoot().toString() + "\\testFolder\\childFolder2"));
        starterPaths.add(Paths.get(temporaryFolder.getRoot().toString() + "\\testFolder\\childFolder1\\childFolder4"));
        Data.setStartedPaths(starterPaths);

        //Список игнорируемых директорий
        List<Path> pathList = new ArrayList<>();
        pathList.add(Paths.get(temporaryFolder.getRoot().toString() + "\\testFolder\\childFolder1"));
        pathList.add(Paths.get(temporaryFolder.getRoot().toString() + "\\testFolder\\childFolder2\\childFolder3"));
        Data.setIgnorePaths(pathList);

        //Список игнорируемых файлов
        List<String> stringList = new ArrayList<>();
        stringList.add("b.txt");
        stringList.add("a.txt");
        stringList.add("notExist.txt");
        Data.setIgnoreFiles(stringList);

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();

        //множество файлов удовлетворяющие условиям и заполнение его экземплярами класс FileRecord
        TreeSet<FileRecord> fileRecordTreeSet = new TreeSet<>();
        FileRecord fileRecord = new FileRecord(temporaryFolder.getRoot().toString() +
                "\\testFolder\\c.txt", dateFormat.format(date), 0);
        fileRecordTreeSet.add(fileRecord);

        FileRecord fileRecord1 = new FileRecord(temporaryFolder.getRoot().toString() +
                "\\testFolder\\childFolder2\\c.txt", dateFormat.format(date), 0);
        fileRecordTreeSet.add(fileRecord1);

        FileRecord fileRecord2 = new FileRecord(temporaryFolder.getRoot().toString() +
                "\\testFolder\\childFolder2\\childFolder4\\c.txt", dateFormat.format(date), 0);
        fileRecordTreeSet.add(fileRecord2);

        //Выполнение поиска
        ExecutorStarter.initSearch(starterPaths);

        assertEquals(Data.getAllFitPaths(), fileRecordTreeSet);
    }
}