package ru.sber.zenkin.Consol;

public class ConsoleWaiterThread extends Thread{

    //Текст, который будет отражаться в процессе выполнения ожидания
    private String waitText;

    public ConsoleWaiterThread(String waitText){
        this.waitText = waitText;
    }

    @Override
    public void run() {
        System.out.println(waitText);
        byte count = 0;
        try{
            while (!this.isInterrupted()) {
                if (count == 60) {
                    System.out.println("|");
                    count = 0;
                } else {
                    System.out.print(".");
                    count += 6;
                }
                Thread.sleep(6000);
            }
        } catch (InterruptedException e) {
            System.out.println("The wait has ended!");
        }
    }
}
