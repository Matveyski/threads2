package ru.jet_brains.projects;


import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    private final static int QUEUE = 200;

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        AtomicReference<Boolean> isInterrupt = new AtomicReference<>(false);
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingDeque<>(QUEUE);
        ExecutorService executor = Executors.newFixedThreadPool(6);




        Runnable producerTask = () -> {
            try {
                while (true) {
                    if (isInterrupt.get().booleanValue() == false) {

                    // Если в очереди элементов >= 100 производители спят
                    if (blockingQueue.remainingCapacity() <= 100) {
                        //Если элементов стало <= 80 производители просыпаются.
                        if (blockingQueue.remainingCapacity() >= 120) {
                            continue;
                        } else {
                            Thread.yield();
                        }

                    } else {
                        int value = random.nextInt(100);
                        blockingQueue.put(value);
                        System.out.println("Produced " + value);
                        System.out.println(blockingQueue.remainingCapacity());
                        Thread.sleep(500);
                    }
                    }else{
                        executor.shutdown();
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable producerTask1 = () -> {
            try {
                while (true) {
                    if (isInterrupt.get().booleanValue() == false) {

                        // Если в очереди элементов >= 100 производители спят
                        if (blockingQueue.remainingCapacity() <= 100) {
                            //Если элементов стало <= 80 производители просыпаются.
                            if (blockingQueue.remainingCapacity() >= 120) {
                                continue;
                            } else {
                                Thread.yield();
                            }

                        } else {
                            int value = random.nextInt(100);
                            blockingQueue.put(value);
                            System.out.println("Produced " + value);
                            System.out.println(blockingQueue.remainingCapacity());
                            Thread.sleep(500);
                        }
                    }else{
                        executor.shutdown();
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable producerTask2 = () -> {
            try {
                while (true) {
                    if (isInterrupt.get().booleanValue() == false) {

                        // Если в очереди элементов >= 100 производители спят
                        if (blockingQueue.remainingCapacity() <= 100) {
                            //Если элементов стало <= 80 производители просыпаются.
                            if (blockingQueue.remainingCapacity() >= 120) {
                                continue;
                            } else {
                                Thread.yield();
                            }

                        } else {
                            int value = random.nextInt(100);
                            blockingQueue.put(value);
                            System.out.println("Produced " + value);
                            //System.out.println(blockingQueue.remainingCapacity());
                            Thread.sleep(500);
                        }
                    }else{
                        executor.shutdown();
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable consumerTask = () -> {
            try {
                while (true) {
                    //если нет элементов в очереди - потребители спят
                    if (blockingQueue.remainingCapacity() == QUEUE){
                        Thread.yield();
                        //Thread.sleep(1000);
                    }else {
                        int value = blockingQueue.take();
                        System.out.println("Consume " + value);
                        //System.out.println(blockingQueue.remainingCapacity());
                        Thread.sleep(500);
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable consumerTask1 = () -> {
            try {
                while (true) {
                    //если нет элементов в очереди - потребители спят
                    if (blockingQueue.remainingCapacity() == QUEUE){
                        Thread.yield();
                    }else {
                        int value = blockingQueue.take();
                        System.out.println("Consume " + value);
                        //System.out.println(blockingQueue.remainingCapacity());
                        Thread.sleep(500);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable stopThreadsTask = () -> {
            while (true) {
                if (scanner.nextLine().equals("q")){
                    isInterrupt.set(true);
                }
            }
        };

        // Запускаем потоки
        executor.execute(producerTask);
        executor.execute(producerTask1);
        executor.execute(producerTask2);

        executor.execute(consumerTask);
        executor.execute(consumerTask1);

        executor.execute(stopThreadsTask);

        executor.shutdown();
    }
}