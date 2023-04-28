package com.example.notebook;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Notebook> notebooks = new HashSet<>();
        notebooks.add(new Notebook("Asus", "Star Black", "Windows 10", 16, 512, 1200));
        notebooks.add(new Notebook("HP", "Silver", "Windows 10", 8, 128, 600));
        notebooks.add(new Notebook("Asus", "Titan", "Windows 10", 32, 1024, 2500));
        notebooks.add(new Notebook("Lenovo", "White", "Windows 8", 8, 256, 700));
        notebooks.add(new Notebook("Apple", "Space Gray", "Mac OS", 16, 256, 3000));

        OperationWithNotebook operation = new OperationWithNotebook(notebooks);
        operation.start();
    }
}