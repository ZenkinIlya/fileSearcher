package ru.sber.zenkin.Init;

import java.util.List;
import java.util.Scanner;

public class NotTrackedFilesInitializer implements Initializer {

    //Ининциализация игнорируемых файлов
    @Override
    public List<String> init(){
        Scanner in = new Scanner(System.in);
        List<String> ignoreFiles;
        System.out.print("Enter not tracked files (press Enter to skip): ");
        ignoreFiles = FilesFormatter.formatFiles(in.nextLine());
        view(ignoreFiles, "NotTrackedFiles");
        return ignoreFiles;
    }
}
