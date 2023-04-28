package com.example.notebook;

import java.util.Objects;

public class Notebook {
    private String model;
    private String color;
    private String operatingSystem;
    private int memory;
    private int storage;
    private int price;

    public Notebook(String model, String color, String operatingSystem, int memory, int storage, int price) {
        this.model = model;
        this.color = color;
        this.operatingSystem = operatingSystem;
        this.memory = memory;
        this.storage = storage;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public int getMemory() {
        return memory;
    }

    public int getStorage() {
        return storage;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notebook notebook = (Notebook) o;
        return memory == notebook.memory && storage == notebook.storage && price == notebook.price && Objects.equals(model, notebook.model) && Objects.equals(color, notebook.color) && Objects.equals(operatingSystem, notebook.operatingSystem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, color, operatingSystem, memory, storage, price);
    }

    @Override
    public String toString() {
        return "Ноутбук: " + "модель " + model +
                " , цвет " + color +
                " , операционная система " + operatingSystem +
                " , ОЗУ " + memory + "GB" +
                " , объём ЖД " + storage + "GB" +
                " , цена " + price + "$ ;";
    }
}