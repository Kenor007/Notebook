package com.example.notebook;

import java.util.*;
import java.util.stream.Collectors;

public class OperationWithNotebook {
    private Set<Notebook> notebooks;
    private List<Notebook> sortedNotebooks;
    private List<Criteria> criteriaList;
    private static Scanner scanner = new Scanner(System.in);

    public OperationWithNotebook(Set<Notebook> notebooks) {
        this.notebooks = notebooks;
        this.criteriaList = new ArrayList<>();
        this.sortedNotebooks = new ArrayList<>();
    }

    public void start() {
        boolean flag = true;
        while (flag) {
            switch (getOperations()) {
                case "1" -> showAllNotebooks();
                case "2" -> addCriteria();
                case "3" -> showAllNotebooksAfterFilter();
                case "4" -> clearFilter();
                case "5" -> {
                    flag = false;
                    scanner.close();
                }
                default -> System.out.println("Введите цифру от 1 до 5");
            }
        }
    }

    public String getOperations() {
        String text = "\tВыберите операцию:" +
                "\n1 Вывести список ноутбуков" +
                "\n2 Добавить критерий фильтрации" +
                "\n3 Вывести отфильтрованный список ноутбуков" +
                "\n4 Очистить критерии фильтрации" +
                "\n5 Завершить";
        System.out.println(text);
        return scanner.nextLine();
    }

    public void showAllNotebooks() {
        for (Notebook notebook : notebooks) {
            System.out.println(notebook);
        }
    }

    public void showAllCriteria() {
        Map<Integer, String> allCriteria = getAllCriteria();
        for (Map.Entry entry : allCriteria.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public Map<Integer, String> getAllCriteria() {
        Map<Integer, String> allCriteria = new HashMap<>();
        allCriteria.put(1, "Модель");
        allCriteria.put(2, "Цвет");
        allCriteria.put(3, "Операционная система");
        allCriteria.put(4, "ОЗУ");
        allCriteria.put(5, "Объём ЖД");
        allCriteria.put(6, "Цена");
        return allCriteria;
    }

    public String getCriteriaOperations() {
        System.out.println("\tВведите цифру, соответствующую необходимому критерию:");
        showAllCriteria();
        System.out.println("0 Вернуться в предыдущее меню");
        return scanner.nextLine();
    }

    public void addCriteria() {
        boolean flag = true;
        Criteria criteria;
        while (flag) {
            String property = getCriteriaOperations();
            switch (property) {
                case "0" -> flag = false;
                case "1" -> {
                    criteria = Criteria.startGetting(scanner, property, false);
                    criteriaList.add(criteria);
                }
                case "2" -> {
                    criteria = Criteria.startGetting(scanner, property, false);
                    criteriaList.add(criteria);
                }
                case "3" -> {
                    criteria = Criteria.startGetting(scanner, property, false);
                    criteriaList.add(criteria);
                }
                case "4" -> {
                    criteria = Criteria.startGetting(scanner, property, true);
                    criteriaList.add(criteria);
                }
                case "5" -> {
                    criteria = Criteria.startGetting(scanner, property, true);
                    criteriaList.add(criteria);
                }
                case "6" -> {
                    criteria = Criteria.startGetting(scanner, property, true);
                    criteriaList.add(criteria);
                }
                default -> System.out.println("Введите цифру от 0 до 6");
            }
        }
    }

    public void showAllNotebooksAfterFilter() {
        if (criteriaList.isEmpty()) {
            System.out.println("Критерии фильтрации отсутствуют");
        } else {
            sortedNotebooks = notebooks.stream().toList();
            for (Criteria criteria : criteriaList) {
                if (!criteria.isQuantitative()) {
                    filterString(criteria);
                } else {
                    filterNumber(criteria);
                }
            }
        }
        for (Notebook notebook : sortedNotebooks) {
            System.out.println(notebook);
        }
    }

    public void filterString(Criteria criteria) {
        switch (criteria.getProperty()) {
            case "1":
                if (criteria.isAscending()) {
                    sortedNotebooks = sortedNotebooks.stream()
                            .sorted(Comparator.comparing(Notebook::getModel))
                            .collect(Collectors.toList());
                } else {
                    sortedNotebooks = sortedNotebooks.stream()
                            .sorted(Collections.reverseOrder(Comparator.comparing(Notebook::getModel)))
                            .collect(Collectors.toList());
                }
                break;
            case "2":
                if (criteria.isAscending()) {
                    sortedNotebooks = sortedNotebooks.stream()
                            .sorted(Comparator.comparing(Notebook::getColor))
                            .collect(Collectors.toList());
                } else {
                    sortedNotebooks = sortedNotebooks.stream()
                            .sorted(Collections.reverseOrder(Comparator.comparing(Notebook::getColor)))
                            .collect(Collectors.toList());
                }
                break;
            case "3":
                if (criteria.isAscending()) {
                    sortedNotebooks = sortedNotebooks.stream()
                            .sorted(Comparator.comparing(Notebook::getOperatingSystem))
                            .collect(Collectors.toList());
                } else {
                    sortedNotebooks = sortedNotebooks.stream()
                            .sorted(Collections.reverseOrder(Comparator.comparing(Notebook::getOperatingSystem)))
                            .collect(Collectors.toList());
                }
                break;
        }
    }

    public void filterNumber(Criteria criteria) {
        switch (criteria.getProperty()) {
            case "4":
                if (criteria.getValue() != null) {
                    sortedNotebooks = sortedNotebooks.stream()
                            .filter(notebook -> notebook.getMemory() == criteria.getValue())
                            .collect(Collectors.toList());
                } else if (criteria.isAscending()) {
                    sortedNotebooks = sortedNotebooks.stream()
                            .sorted(Comparator.comparing(Notebook::getMemory))
                            .collect(Collectors.toList());
                } else if (!criteria.isAscending() && criteria.getValue() == null && criteria.getMinValue() == null) {
                    sortedNotebooks = sortedNotebooks.stream()
                            .sorted(Collections.reverseOrder(Comparator.comparing(Notebook::getMemory)))
                            .collect(Collectors.toList());
                } else if (criteria.getMinValue() != null && criteria.getMaxValue() != null) {
                    sortedNotebooks = sortedNotebooks.stream()
                            .filter(notebook -> notebook.getMemory() >= criteria.getMinValue() && notebook.getMemory() <= criteria.getMaxValue())
                            .collect(Collectors.toList());
                }
                break;
            case "5":
                if (criteria.getValue() != null) {
                    sortedNotebooks = sortedNotebooks.stream()
                            .filter(notebook -> notebook.getStorage() == criteria.getValue())
                            .collect(Collectors.toList());
                } else if (criteria.isAscending()) {
                    sortedNotebooks = sortedNotebooks.stream()
                            .sorted(Comparator.comparing(Notebook::getStorage))
                            .collect(Collectors.toList());
                } else if (!criteria.isAscending() && criteria.getValue() == null && criteria.getMinValue() == null) {
                    sortedNotebooks = sortedNotebooks.stream()
                            .sorted(Collections.reverseOrder(Comparator.comparing(Notebook::getStorage)))
                            .collect(Collectors.toList());
                } else if (criteria.getMinValue() != null && criteria.getMaxValue() != null) {
                    sortedNotebooks = sortedNotebooks.stream()
                            .filter(notebook -> notebook.getStorage() >= criteria.getMinValue() && notebook.getStorage() <= criteria.getMaxValue())
                            .collect(Collectors.toList());
                }
                break;
            case "6":
                if (criteria.getValue() != null) {
                    sortedNotebooks = sortedNotebooks.stream()
                            .filter(notebook -> notebook.getPrice() == criteria.getValue())
                            .collect(Collectors.toList());
                } else if (criteria.isAscending()) {
                    sortedNotebooks = sortedNotebooks.stream()
                            .sorted(Comparator.comparing(Notebook::getPrice))
                            .collect(Collectors.toList());
                } else if (!criteria.isAscending() && criteria.getValue() == null && criteria.getMinValue() == null) {
                    sortedNotebooks = sortedNotebooks.stream()
                            .sorted(Collections.reverseOrder(Comparator.comparing(Notebook::getPrice)))
                            .collect(Collectors.toList());
                } else if (criteria.getMinValue() != null && criteria.getMaxValue() != null) {
                    sortedNotebooks = sortedNotebooks.stream()
                            .filter(notebook -> notebook.getPrice() >= criteria.getMinValue() && notebook.getPrice() <= criteria.getMaxValue())
                            .collect(Collectors.toList());
                }
                break;
        }
    }

    public void clearFilter() {
        if (!criteriaList.isEmpty()) {
            criteriaList.clear();
            sortedNotebooks.clear();
        }
    }
}