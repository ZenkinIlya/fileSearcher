package ru.sber.zenkin;

import ru.sber.zenkin.Data.Data;
import ru.sber.zenkin.Utils.Save.ListFileDirectoriesSaver;
import ru.sber.zenkin.Utils.Search.ExecutorStarter;
import ru.sber.zenkin.Init.FileNameInitializer;
import ru.sber.zenkin.Init.NotTrackedPathsInitializer;
import ru.sber.zenkin.Init.NotTrackedFilesInitializer;
import ru.sber.zenkin.Init.StartPathsInitializer;

import java.nio.file.Path;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        //Инициализация корневых директорий
        List<Path> starterPaths = new StartPathsInitializer().init();
        Data.setStartedPaths(starterPaths);
        //Инициализация списка не сканируемых каталогов
        List<Path> ignorePaths = new NotTrackedPathsInitializer().init();
        Data.setIgnorePaths(ignorePaths);
        //Инициализация списка не сканируемых файлов
        List<String> ignoreFiles = new NotTrackedFilesInitializer().init();
        Data.setIgnoreFiles(ignoreFiles);
        //Инициализация имени файла в который будут сохраняться найденные файлы в в сканируемом каталоге
        String nameFile = FileNameInitializer.setNameFile();
        Data.setNameFile(nameFile);

        //Поиск файлов
        ExecutorStarter.initSearch(Data.getStartedPaths());

        //Сохранение в файле всех найденных файлов, удовлетворяющих условиям
        ListFileDirectoriesSaver listFileDirectoriesSaver = new ListFileDirectoriesSaver();
        listFileDirectoriesSaver.saveListInFile(Data.getAllFitPaths(), System.getProperty("user.dir") + "/" + Data.getNameFile());
    }
}
