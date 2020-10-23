package lesson1;

import kotlin.NotImplementedError;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     *
     * Пример:
     *
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     * Время - O(n log(n)), память - O(n).
     */
    static public void sortTimes(String inputName, String outputName) throws IOException {
        ArrayList<Integer> am = new ArrayList<>();
        ArrayList<Integer> pm = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(inputName))) {
            String line;
            int seconds;
            String[] parts;
            while ((line = reader.readLine()) != null) {
                parts = line.split("[: ]");
                seconds = Integer.parseInt(parts[0]) % 12 * 3600 +
                        Integer.parseInt(parts[1]) * 60 +
                        Integer.parseInt(parts[2]);
                if (parts[3].equals("AM")) am.add(seconds);
                else pm.add(seconds);
            }
        }
        Collections.sort(am);
        Collections.sort(pm);
        String currentTime;
        for (int time : am) {
            if (time / 3600 == 0) time += 3600 * 12;
            result.add(String.format("%02d:%02d:%02d AM", time / 3600, time / 60 % 60, time % 60));
        }
        for (int time : pm) {
            if (time / 3600 == 0) time += 3600 * 12;
            result.add(String.format("%02d:%02d:%02d PM", time / 3600, time / 60 % 60, time % 60));
        }
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputName))) {
            for (String time : result) {
                writer.write(time);
                writer.newLine();
            }
        }
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     * Время - O(n), память - O(n).
     */
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        ArrayList<Integer> temps = new ArrayList<>();
        int minTemp = -2730;
        int maxTemp = 5000;
        int limit = maxTemp - minTemp;
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(inputName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                temps.add((int) (Double.parseDouble(line) * 10) - minTemp);
            }
        }
        int[] result = new int[temps.size()];
        for (int i = 0; i < temps.size(); i++) result[i] = temps.get(i);
        result = Sorts.countingSort(result, limit);
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputName))) {
            for (int temp : result) {
                writer.write(String.valueOf(((double) (temp + minTemp)) / 10));
                writer.newLine();
            }
        }
    }


    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     * Время - O(n), память - O(n).
     */
    static public void sortSequence(String inputName, String outputName) throws IOException {
        ArrayList<Integer> result = new ArrayList<>();
        SortedMap<Integer, Integer> numbers = new TreeMap<>();
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(inputName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(Integer.parseInt(line));
                numbers.merge(Integer.parseInt(line), 1, Integer::sum);
            }
        }
        int max = 0;
        int num = 0;
        for (Map.Entry<Integer, Integer> entry : numbers.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                num = entry.getKey();
            }
            }
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputName))) {
            for (int i : result) {
                if (i != num) {
                    writer.write(String.valueOf(i));
                    writer.newLine();
                }
            }
            for (int i = 0; i < max; i++) {
                writer.write(String.valueOf(num));
                writer.newLine();
            }
        }
    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
