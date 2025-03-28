package org.taskphonebook;

/**
 * Класс, представляющий абонента телефонного справочника.
 * Содержит поля для имени и номера телефона абонента.
 */
public class Abonent {

    /**
     * Имя абонента.
     */
    private String name;

    /**
     * Номер телефона абонента.
     */
    private String phoneNumber;

    /**
     * Конструктор для создания нового абонента.
     *
     * @param name Имя абонента.
     * @param phoneNumber Номер телефона абонента.
     */
    public Abonent(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Возвращает имя абонента.
     *
     * @return Имя абонента.
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает новое имя для абонента.
     *
     * @param name Новое имя.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает номер телефона абонента.
     *
     * @return Номер телефона.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Устанавливает новый номер телефона для абонента.
     *
     * @param phoneNumber Новый номер телефона.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

