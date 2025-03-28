/**
 * Класс для генерации случайных данных для телефонного справочника.
 * Предоставляет методы для генерации случайных имен, номеров телефонов и заполнения справочника.
 */

package org.taskphonebook;

import java.util.List;
import java.util.Random;

public class RandomGeneration {

    Random random = new Random();

    /**
     * Возвращает случайное имя из списка доступных имен.
     * @return Имя
     */
    protected String generateRandomName(){
        String [] names = {"Иван", "Петр", "Александр", "Михаил", "Дмитрий"};
        return names[random.nextInt(names.length)];
    }

    /**
     * Генерирует случайный номер телефона в формате "+7 XXX XXX-XX-XX"
     * @return Номер телефона
     */
    protected String generateRandomNumber(){
        String phoneNumber = String.format("+7 %03d %03d-%02d-%02d",
                random.nextInt(100, 1000),
                random.nextInt(100, 1000),
                random.nextInt(10, 90),
                random.nextInt(10, 90));
        return phoneNumber;
    }

    /**
     * Заполняет телефонный справочник 100 000 записями с случайными именами и номерами телефонов
     * @param phoneDirectory Список для заполнения
     */
    protected void fillPhoneDirectory(List<Abonent> phoneDirectory) {
        for (int i = 0; i < 100000; i++) {
            String name = generateRandomName();
            String phoneNumber = generateRandomNumber();

            phoneDirectory.add(new Abonent(name, phoneNumber));
        }
    }

    /**
     * Принимает справочник, у рандомного количества строк меняет номер телефона, строки также выбираются рандомно
     * @param phoneDirectory Справочник для изменения
     */
    protected void changePhoneDirectory(List<Abonent> phoneDirectory){
        int count = random.nextInt(100000);
        for(int i = 0; i < count; i++){
            int index = random.nextInt(100000);
            Abonent abonent = phoneDirectory.get(index);
            abonent.setPhoneNumber(generateRandomNumber());
        }
    }

}
