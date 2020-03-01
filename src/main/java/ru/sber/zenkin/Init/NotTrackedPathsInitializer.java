package ru.sber.zenkin.Init;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NotTrackedPathsInitializer implements Initializer{

    //Ининциализация игнорируемых каталогов
    @Override
    public List<Path> init(){
        Scanner in = new Scanner(System.in);
        List<Path> ignoreDirs = new ArrayList<>();
        boolean flag = true;
        String enter;  //Введенные данные в консоль
        System.out.print("Enter not tracked directories (press Enter to skip): ");
        while (flag){
            enter = in.nextLine();
            if (enter.equals("")) break;  //Пропуск инициализации игнорирумых каталогов
            ignoreDirs = PathsFormatter.formatPaths(enter);  //форматирование введенных данных
            for (Path somePath: ignoreDirs){
                if (!Files.isDirectory(somePath)){  //Проверка на директорию
                    System.out.print("'" +somePath+ "' is not directory, try again: ");
                    flag = true;
                    break;
                }else {
                    flag = false;
                }
            }
        }
        view(ignoreDirs, "NotTrackedPaths");
        return ignoreDirs;
    }
}
