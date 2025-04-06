package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileComparer();
    }
        public static void FileComparer() {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите путь к первому файлу: ");
            String path1 = scanner.nextLine();

            System.out.print("Введите путь ко второму файлу: ");
            String path2 = scanner.nextLine();

            try (
                    BufferedReader reader1 = new BufferedReader(new FileReader(path1));
                    BufferedReader reader2 = new BufferedReader(new FileReader(path2))
            ) {
                String line1, line2;
                int lineNumber = 1;
                boolean allMatch = true;

                while ((line1 = reader1.readLine()) != null & (line2 = reader2.readLine()) != null) {
                    if (!line1.equals(line2)) {
                        allMatch = false;
                        System.out.println("Строка " + lineNumber + " не совпадает:");
                        System.out.println("Файл 1: " + line1);
                        System.out.println("Файл 2: " + line2);
                    }
                    lineNumber++;
                }

                if (reader1.readLine() != null || reader2.readLine() != null) {
                    System.out.println("Файлы имеют разное количество строк.");
                    allMatch = false;
                }

                if (allMatch) {
                    System.out.println("Все строки совпадают.");
                }

            } catch (IOException e) {
                System.out.println("Ошибка при чтении файлов: " + e.getMessage());
            }
        }
}