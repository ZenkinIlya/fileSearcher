package ru.sber.zenkin.Init;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class StartPathsInitializer implements Initializer {

    //Ининциализация стартовых каталогов
    @Override
    public List<Path> init(){
        Scanner in = new Scanner(System.in);
        List<Path> formatPathsList = new ArrayList<>();
        boolean flag = true;
        System.out.print("Enter start directories: ");
        while (flag){
            formatPathsList = PathsFormatter.formatPaths(in.nextLine());  //Форматирование каталогов
            if (formatPathsList.isEmpty()){
                System.out.print("Please, enter start directories: ");
            }
            for (Path somePath: formatPathsList){
                if (!Files.exists(somePath.toAbsolutePath())){ //Проверка на существование каталога
                    System.out.print("Directory - '" +somePath+ "' not exists, try again: ");
                    flag = true;
                    break;
                }else {
                    flag = false;
                }
            }
        }
        view(formatPathsList, "---StartPaths--");
        return formatPathsList;
    }
}
