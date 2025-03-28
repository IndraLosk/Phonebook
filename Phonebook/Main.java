/**
 * Главный класс для работы со справочником телефонных номеров
 * Задача: Создайте программу, хранящую в памяти справочник
 * телефонных номеров (имя, номер телефона). Заполните его 100 000 записями. Каждые N миллисекунд у случайного кол-ва
 * записей меняйте номер телефона на другой. Через T секунд с момента начала программы выведите в консоль записи,
 * которые находились в справочнике M секунд назад.
 *
 * Definition of Done:
 * язык программирования:Java 17
 * сборка приложения: Maven
 * документирование кода: JavaDoc
 * логирование приложения: одно из реализаций logger
 */

package org.taskphonebook;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        /**
         *  Начало работы программы, генерация случайных чисел, N, M, T, где 500<N<5000, 0<M<30, 60<T<300
         *  Создание и заполнение массивов и телефонного справочника.
         */

        final Logger logger = Logger.getLogger(Main.class.getName());
        logger.info("Программа запущена");

        Random random = new Random();
        int N = random.nextInt(501, 5000);
        int M = random.nextInt(0, 30);
        int T = random.nextInt(61, 300);
        logger.info("Сгенерировано значение N = " + N + "\nСгенерировано значение M = " + M + "\nСгенерировано значение T = " + T);

        NavigableMap<Long, List<Abonent>> history = new TreeMap<>();
        List<Abonent> phoneDirectory = new ArrayList<>();
        RandomGeneration randomGeneration = new RandomGeneration();

        randomGeneration.fillPhoneDirectory(phoneDirectory);
        history.put(System.currentTimeMillis(), phoneDirectory);
        logger.info("Справочник инициализирован: " + phoneDirectory.size() + " записей");

        /**
         * Каждые N миллисекунд у случайного кол-ва записей меняйте номер телефона на другой.
         * Для этого создается расписание, которое рекурсивно вызывает себя с разными задержками.
         *
         * Через T секунд с момента начала программы выведите в консоль записи, которые находились в справочнике M
         * секунд назад.
         * Для этого второе расписание высчитывает разницу во времени и использует метод printDirectory, выводящий
         * справочник
         */

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
                    newScheduleN(scheduler, randomGeneration, phoneDirectory, history);
                    logger.info("Номера телефонов сменились");
                },
                N,
                TimeUnit.MILLISECONDS);


        scheduler.schedule(() -> {
            Long timeLastM = System.currentTimeMillis() - M * 1000;
            List<Abonent> snapshot = history.floorEntry(timeLastM).getValue();
            printDirectory(snapshot);
            logger.info("Записи были выведены");

            scheduler.shutdown();
            logger.info("Программа закончила работу");
        }, T, TimeUnit.SECONDS);

    }

    /**
     * Функция вывода справочника в консоль
     * @param phoneDirectory Список абонентов для вывода
     */

    public static void printDirectory(List<Abonent> phoneDirectory) {
        for (Abonent abonent : phoneDirectory) {
            System.out.println(abonent.getName() + ": " + abonent.getPhoneNumber());
        }
    }

    /**
     * Функция вызова расписания с новым N
     * @param scheduler Планировщик задач для управления потоками
     * @param randomGeneration Генератор изменений для справочника
     * @param phoneDirectory Текущее состояние телефонного справочника
     * @param history Хранилище исторических состояний справочника
     */

    private static void newScheduleN(ScheduledExecutorService scheduler, RandomGeneration randomGeneration, List<Abonent> phoneDirectory, NavigableMap<Long, List<Abonent>> history) {
        Random random = new Random();
        final Logger logger = Logger.getLogger(Main.class.getName());
        randomGeneration.changePhoneDirectory(phoneDirectory);
        history.put(System.currentTimeMillis(), new ArrayList<>(phoneDirectory));
        int N = random.nextInt(501, 5000);
        scheduler.schedule(() -> {
                    newScheduleN(scheduler, randomGeneration, phoneDirectory, history);
                    logger.info("Значение N = " + N + "\nНомера телефонов сменились");
                },
                N,
                TimeUnit.MILLISECONDS);
    }
}

