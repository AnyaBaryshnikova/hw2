package com.company;


import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String path = "newfile.txt";
        ArrayList<String> fileStrings = readFile(path);

        // строки без лишних символов, слова разделены одним пробелом
        for(int i = 0; i < fileStrings.size(); ++i) {
            fileStrings.set(i, changeString(fileStrings.get(i)));
        }

        // Все слова из файла
        ArrayList<String> words = new ArrayList<>();
        for(int i = 0; i < fileStrings.size(); ++i){
            String[] stringWords = fileStrings.get(1).split(" ");
            Collections.addAll(words, stringWords);
        }

        Collections.sort(words);
        System.out.println("Слова в алфавитном порядке: ");
        for (String word : words) System.out.println(word);

        Map<String, Integer> statistics = new HashMap<>();

        // добавить слова в словарь из массива строк
        for (String word : words) {
            statistics.put(word, statistics.containsKey(word) ? statistics.get(word) + 1 : 1);
        }

        System.out.println("\n\nСтатистика слов:");
        printDict(statistics);

        System.out.println("\n\n");
        findMax(statistics);
    }


    // Убираем из строки лишние символы и возвращаем  массив слов
    public static String changeString(String str) {
        str = str.toLowerCase(Locale.ROOT);
        ArrayList<String> words = new ArrayList<>();
        char[] temp = str.toCharArray();


        for (int i = 0; i < temp.length; ++i) {
            if ((int) temp[i] > 122 | (int) temp[i] < 97)
                temp[i] = ' ';
        }

        String newStr = new String(temp);
        newStr = newStr.replaceAll("\\s+", " ");
        return newStr;
    }

    // печать словаря
    public static void printDict(Map<String, Integer> dict) {
        int total = 0;
        for (String k : dict.keySet()) {
            total += dict.get(k);
        }
        double finalTotal = total;
        dict.forEach((key, value) -> System.out.println("Слово: " + key + " количество повторений: " + value + " процент от общего количества слов: " + Math.round((value / finalTotal * 100)) + "%"));
    }

    // максимальное value в словаре
    public static void findMax(Map<String, Integer> dict) {
        int maxValueInMap = (Collections.max(dict.values()));
        System.out.println("Слова, которые встречается максимальное число раз (" + maxValueInMap + "): ");

        for (Map.Entry<String, Integer> entry : dict.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
                System.out.println(entry.getKey());
            }
        }
    }

    public static ArrayList<String> readFile(String path){
        ArrayList<String> fileStrs = new ArrayList<>();
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            fileStrs.add(line);
            while (line != null) {
                line = reader.readLine();
                fileStrs.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileStrs.removeAll(Collections.singleton(null));
        return fileStrs;
    }

}


