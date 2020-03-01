package ru.sber.zenkin.Init;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

class PathsFormatter {

    //Форматирование строки с каталогами
    static List<Path> formatPaths(String stringPaths){
        //LinkedHashSet для того чтобы сохранять порядок добавления директорий.
        //Первоначально стоял HashSet и в этом случае тест то проходил то нет.
        //В тесте можно было применить org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
        //но в задании сказано что нельзя применять сторонние библиотеки.
        LinkedHashSet<Path> formatPathsSet = new LinkedHashSet<>();
        String[]arrPaths = stringPaths.split("\"");  //как разделитель знак "
        for (String string: arrPaths){
            if (string.trim().length() != 0){  //Проверка на пустую строчку
                formatPathsSet.add(Paths.get(string.trim()));
            }
        }
        return new ArrayList<>(formatPathsSet);
    }
}
