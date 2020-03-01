package ru.sber.zenkin.Utils.Search;

import ru.sber.zenkin.Consol.ConsoleWaiterThread;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorStarter {

    //Поиск файлов в каталогах
    //Каждый стартовый каталог выполняется в отдельном потоке
    //Это сделано для ускорение работы программы в том случае если стартовых каталогов больше одного
    public static void initSearch(List<Path> starterPaths){
        //Создание фиксированного пула потоков
        //Если выбирать кешируемый пул, то в случае большого количества стартовых директорий
        //они будут выполняться одновременно. Это может сократить время обработки потоков но для этого
        //потребуется много ресурсов
        //Чаще всего я использовал примерно 10 стартовых директорий, поэтому поставил 10 потоков
        //Если меньше 10, то будет проигрыш по времени, так как некоторые потоки будут находится в очереди
        //Если больше 10, то некоторые потоки будут бездействовать, тем самым потребляя ресурсы
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ConsoleWaiterThread consoleWaiterThread = new ConsoleWaiterThread("Wait, progress search directory");
        try{
            //Запуск потока ожидания
            consoleWaiterThread.start();
            //Инициализация списка в который будут записываться результаты выполнения потоков
            //Это нужно для того, чтобы основной поток видел исключения возникаемые в дочерних потоках
            List<Future<String>>  futures = new ArrayList<>();
            //Запуск потоков
            for (Path path: starterPaths){
                futures.add((Future<String>) executorService.submit(new Searcher(path.toString())));
            }
            int numPath = 0;
            try {
                //Получаем результат выполнения потоков
                for (numPath = 0; numPath<starterPaths.size(); numPath++){
                    futures.get(numPath).get();
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
                //Отображение директории в которой произошло исключение
                System.out.println("Exception in start directory: " +starterPaths.get(numPath));
            }
            //Блокировка добавление задач на выполнение
            executorService.shutdown();
            //Ожидание выполнение всех задач.
            //Задачи находящиеся в очереди выполняются
            executorService.awaitTermination(20, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //Прерывание потока ожидания
            consoleWaiterThread.interrupt();
        }
    }
}
