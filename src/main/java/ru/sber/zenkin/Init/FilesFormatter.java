package ru.sber.zenkin.Init;

import java.util.ArrayList;
import java.util.LinkedHashSet;

class FilesFormatter {

    //Форматирование строки с файлами
    static ArrayList<String> formatFiles(String stringFiles){
        //LinkedHashSet для того чтобы сохранять порядок добавления файлов.
        //Первоначально стоял HashSet и в этом случае тест то проходил то нет.
        //В тесте можно было применить org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
        //но в задании сказано что нельзя применять сторонние библиотеки.
        LinkedHashSet<String> formatFilesSet = new LinkedHashSet<>();
        String[]arrFiles = stringFiles.split("\""); //как разделитель знак "
        for (String string: arrFiles){
            if (string.trim().length() != 0){  //проверка на пустую строчку
                formatFilesSet.add(string.trim());
            }
        }
        return new ArrayList<>(formatFilesSet);
    }
}
