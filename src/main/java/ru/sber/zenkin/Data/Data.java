package ru.sber.zenkin.Data;

import ru.sber.zenkin.Entity.FileRecord;

import java.nio.file.Path;
import java.util.List;
import java.util.TreeSet;

public class Data {

    //Использую интерфейс List чтобы добавлять любые списки типа имплементирующего List
    private static List<Path> startedPaths;
    private static List<Path> ignorePaths;
    private static List<String> ignoreFiles;
    //TreeSet - Так как нужен упорядочный список без повторов
    private static TreeSet<FileRecord> allFitPaths = new TreeSet<>();
    private static String nameFile;

    public static List<Path> getStartedPaths() {
        return startedPaths;
    }

    public static void setStartedPaths(List<Path> startedPaths) {
        Data.startedPaths = startedPaths;
    }

    public static List<Path> getIgnorePaths() {
        return ignorePaths;
    }

    public static void setIgnorePaths(List<Path> ignorePaths) {
        Data.ignorePaths = ignorePaths;
    }

    public static List<String> getIgnoreFiles() {
        return ignoreFiles;
    }

    public static void setIgnoreFiles(List<String> ignoreFiles) {
        //Привожу имена игнорируемых файлов к LowerCase
        //Подробное описание в методе getFitPaths в классе Searcher
        ignoreFiles.replaceAll(String::toLowerCase);
        Data.ignoreFiles = ignoreFiles;
    }

    public static String getNameFile() {
        return nameFile;
    }

    public static void setNameFile(String nameFile) {
        Data.nameFile = nameFile;
    }

    //Синхронизирую метод, но этого делать не обязательно так как помимо функции добавления
    //со списком allFitPaths ничего больше не происходит
    public static synchronized void fillAllFitPaths(List<FileRecord> allFitPathsEnter){
        allFitPaths.addAll(allFitPathsEnter);  //TreeSet
    }

    public static TreeSet<FileRecord> getAllFitPaths() {
        return allFitPaths;
    }

    //Очистка. Нужна для корректной работы тестов
    public static void clearAllFitPaths(){
        allFitPaths.clear();
    }
}
