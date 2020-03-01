package ru.sber.zenkin.Init;

import java.util.Scanner;

public class FileNameInitializer {

    //Инициализация имени файла в который будут сохраняться экземпляры класса FileRecord
    public static String setNameFile(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the file in which the scanned directories will be saved.");
        System.out.println("The file will be located in the application folder");
        System.out.print("Enter the name of the file: ");
        return in.nextLine();
    }
}
