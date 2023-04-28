package com.example.notebook;

import java.util.Scanner;

public class Criteria {
    private String property;
    private boolean isQuantitative;
    private boolean isAscending;
    private Integer value;
    private Integer minValue;
    private Integer maxValue;

    public Criteria(String property, boolean isQuantitative, boolean isAscending, Integer value, Integer minValue, Integer maxValue) {
        this.property = property;
        this.isQuantitative = isQuantitative;
        this.isAscending = isAscending;
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public static Criteria startGetting(Scanner scanner, String property, boolean isQuantitative) {
        Criteria criteria = null;
        if (isQuantitative) {
            String result = getOperationForNumber(scanner);
            switch (result) {
                case "1" -> {
                    System.out.println("Введите значение поиска:");
                    int value = scanner.nextInt();
                    criteria = new Criteria(property, true, false, value, null, null);
                }
                case "2" -> criteria = new Criteria(property, true, true, null, null, null);
                case "3" -> criteria = new Criteria(property, true, false, null, null, null);
                case "4" -> {
                    System.out.println("Введите минимальное значение поиска:");
                    int minValue = scanner.nextInt();
                    System.out.println("Введите максимальное значение поиска:");
                    int maxValue = scanner.nextInt();
                    criteria = new Criteria(property, true, false, null, minValue, maxValue);
                }
                default -> System.out.println("Введите корректное значение для фильтрации");
            }
        } else {
            String result = getOperationForString(scanner);
            switch (result) {
                case "1" -> criteria = new Criteria(property, false, true, null, null, null);
                case "2" -> criteria = new Criteria(property, false, false, null, null, null);
                default -> System.out.println("Введите корректное значение для фильтрации");
            }
        }
        return criteria;
    }

    public static String getOperationForNumber(Scanner scanner) {
        String text = "\tВыберите фильтрацию:" +
                "\n1 По значению" +
                "\n2 По возрастанию" +
                "\n3 По убыванию" +
                "\n4 По интервалу";
        System.out.println(text);
        return scanner.nextLine();
    }

    public static String getOperationForString(Scanner scanner) {
        String text = "\tВыберите фильтрацию:" +
                "\n1 По возрастанию" +
                "\n2 По убыванию";
        System.out.println(text);
        return scanner.nextLine();
    }

    public String getProperty() {
        return property;
    }

    public boolean isQuantitative() {
        return isQuantitative;
    }

    public boolean isAscending() {
        return isAscending;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }
}