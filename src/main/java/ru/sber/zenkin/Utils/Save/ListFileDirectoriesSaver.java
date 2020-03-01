package ru.sber.zenkin.Utils.Save;

import ru.sber.zenkin.Entity.FileRecord;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.TreeSet;

public class ListFileDirectoriesSaver implements FileSaver {

    //Сохранение списка найденных файлов
    @Override
    public void saveListInFile(TreeSet<FileRecord> treeSet, String pathFile) {
        System.out.println("Saving to file...");

        try (Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(pathFile), StandardCharsets.UTF_8))){
            for (FileRecord fileRecord: treeSet) {
                out.append("[\r\nfile = \\\\")
                        .append(fileRecord.getPathFile())
                        .append("\r\n")
                        .append("date = ")
                        .append(fileRecord.getDate())
                        .append("\r\n")
                        .append("size = ")
                        .append(String.valueOf(fileRecord.getSize()))
                        .append("]");
                //Запись в файл содержимого буффера и его очистка
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
