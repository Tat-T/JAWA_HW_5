package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileComparer();
        findLongestLine();
        processArraysFromFile();
    }

    // файл 1.txt и 2.txt для проверки
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

    // файл 3.txt для проверки
    public static void findLongestLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к файлу: ");
        String path = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            String longestLine = "";
            int maxLength = 0;

            while ((line = reader.readLine()) != null) {
                if (line.length() > maxLength) {
                    maxLength = line.length();
                    longestLine = line;
                }
            }

            System.out.println("Длина самой длинной строки: " + maxLength);
            System.out.println("Сама строка: " + longestLine);

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    // файл 4.txt для проверки
    public static void processArraysFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к файлу: ");
        String path = scanner.nextLine();

        List<int[]> arrays = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                int[] nums = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    nums[i] = Integer.parseInt(parts[i]);
                }
                arrays.add(nums);
            }

            int globalMax = Integer.MIN_VALUE;
            int globalMin = Integer.MAX_VALUE;
            int totalSum = 0;

            for (int i = 0; i < arrays.size(); i++) {
                int[] arr = arrays.get(i);
                System.out.println("Массив " + (i + 1) + ": " + Arrays.toString(arr));

                int max = Arrays.stream(arr).max().getAsInt();
                int min = Arrays.stream(arr).min().getAsInt();
                int sum = Arrays.stream(arr).sum();

                System.out.println("Максимум: " + max);
                System.out.println("Минимум: " + min);
                System.out.println("Сумма: " + sum);
                System.out.println();

                globalMax = Math.max(globalMax, max);
                globalMin = Math.min(globalMin, min);
                totalSum += sum;
            }

            System.out.println("Общий максимум: " + globalMax);
            System.out.println("Общий минимум: " + globalMin);
            System.out.println("Общая сумма всех массивов: " + totalSum);

        } catch (IOException | NumberFormatException e) {
            System.out.println("Ошибка при обработке файла: " + e.getMessage());
        }
    }
}