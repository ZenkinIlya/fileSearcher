package ru.sber.zenkin.Utils.Search;

import ru.sber.zenkin.Data.Data;
import ru.sber.zenkin.Entity.FileRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Searcher implements Runnable {

    private String startedPath;

    Searcher(String startedPath) {
        this.startedPath = startedPath;
    }

    @Override
    public void run() {
        try {
            //Сканирование каталогов и получение списка всех файлов удовлетворящих условиям
            List<Path> fitPaths = getFitPaths(startedPath);
            //Получение списка файлов с параметрами: имя, дата изменения, размер
            List<FileRecord> allFitPaths = fillFitDirs(fitPaths);
            //Заполнение общего TreeSet для всех потоков
            //Элементы сортируются на основе метода compareTo реализованного в классе FileRecord
            Data.fillAllFitPaths(allFitPaths);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Изменение формата даты времени
    private String convertToDate(FileTime fileTime){
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        return dateFormat.format(fileTime.toMillis());
    }

    private List<FileRecord> fillFitDirs(List<Path> fitDirs) throws IOException {
        List<FileRecord> fileRecordList = new ArrayList<>();
        BasicFileAttributes attributes;
        for (Path path : fitDirs){
            //Получение атрибутов текущего файла
            attributes = Files.readAttributes(path, BasicFileAttributes.class);

            FileRecord fileRecord = null;
            if (attributes != null) {
                fileRecord = new FileRecord(path.toString(), convertToDate(attributes.creationTime()), attributes.size());
            }
            fileRecordList.add(fileRecord);
        }
        return fileRecordList;
    }

    private static List<Path> getFitPaths(String startedPath) throws IOException {
        return Files.walk(Paths.get(startedPath))
                .filter(Files::isRegularFile)  //Проверка на файл
                .filter(Files::isWritable)
                //Проверка на совпадение директории текущего файла с игнорируемыми директориями
                .filter(Files -> Data.getIgnorePaths().stream()
                        .noneMatch(Path -> Files.getParent().toString().contains(Path.toString())))
                //Проверка имени текущего файла на совпадение с игнорируемыми именами файлов
                //Для системы win7 или win10 файлы с именами a.txt и A.txt являются одинаковыми,
                //поэтому все имена файлов приводятся к LowerCase
                //метод contains чуствителен к регистру поэтому применяться метод toLowerCase
                .filter(Files -> !Data.getIgnoreFiles().contains(Files.getFileName().toString().toLowerCase()))
//                .peek(Files -> System.out.println(Data.getIgnorePaths() + " -> " + Files.getParent() + " -> " + Files.getFileName()))
                .collect(Collectors.toList());
    }
}
