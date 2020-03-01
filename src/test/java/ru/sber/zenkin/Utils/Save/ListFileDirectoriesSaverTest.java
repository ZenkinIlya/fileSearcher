package ru.sber.zenkin.Utils.Save;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.sber.zenkin.Entity.FileRecord;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class ListFileDirectoriesSaverTest {

    //Инициализация объекта временной директории
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    //Заполнение временной директории файлами, каталогами и файлом для сохранения данных
    @Before
    public void setFolders() throws IOException {
        File testFolder = temporaryFolder.newFolder("testFolder");
        new File(testFolder, "a.txt").createNewFile();
        new File(testFolder, "b.txt").createNewFile();

        File saveFileFolder = temporaryFolder.newFolder("saveFolder");
        new File(saveFileFolder, "save.txt").createNewFile();
    }


    //Тест сохранения списка в файл save.txt
    @Test
    public void saveListInFile() {

        //Инициализация формата даты
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();

        //Инициализация упорядоченного множества и заполнение его экземплярами класс FileRecord
        TreeSet<FileRecord> fileRecordTreeSet = new TreeSet<>();
        FileRecord fileRecord = new FileRecord(temporaryFolder.getRoot().toString() +
                "\\testFolder\\a.txt", dateFormat.format(date), 0);
        fileRecordTreeSet.add(fileRecord);

        FileRecord fileRecord1 = new FileRecord(temporaryFolder.getRoot().toString() +
                "\\testFolder\\b.txt", dateFormat.format(date), 0);
        fileRecordTreeSet.add(fileRecord1);

        //Сохранение полученного множества в файл save.txt
        new ListFileDirectoriesSaver().saveListInFile(fileRecordTreeSet, temporaryFolder.getRoot().toString() + "\\saveFolder\\save.txt");

        //Считывание данных
        ArrayList<String> listFormFile = getArrayListFromFile(temporaryFolder.getRoot().toString() + "\\saveFolder\\save.txt");

        //Инициализация корректного списка данных который должен быть равен списку из файла save.txt
        ArrayList<String> correctListFile = new ArrayList<>();
        correctListFile.add("[");
        correctListFile.add("file = \\\\" +temporaryFolder.getRoot().toString() + "\\testFolder\\a.txt");
        correctListFile.add("date = " +dateFormat.format(date));
        correctListFile.add("size = 0][");
        correctListFile.add("file = \\\\" +temporaryFolder.getRoot().toString() + "\\testFolder\\b.txt");
        correctListFile.add("date = " +dateFormat.format(date));
        correctListFile.add("size = 0]");

        assertEquals(listFormFile, correctListFile);
    }

    //Считывание данных из файла (save.txt) в список
    private static ArrayList<String> getArrayListFromFile(String fileName){
        ArrayList<String> arrayList = new ArrayList<String>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            String sCurrentLine;
            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                arrayList.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}