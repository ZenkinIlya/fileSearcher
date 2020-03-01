package ru.sber.zenkin.Init;

import java.util.List;

public interface Initializer {

    //Вывод в консоль списка
    default void view(List list, String name){
        System.out.println("------------------------" +name+ "--------------------------");
        for (Object object: list){
            System.out.println(object);
        }
        System.out.println("-----------------------------------------------------------------");
    }

    List init();
}
