package lesson7;

import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Время - О(n*m), память - О(n*m), где n u m — длины строк.
     */
    public static String longestCommonSubSequence(String first, String second) {
        int lFirst = first.length();
        int lSecond = second.length();
        int[][] matrix = new int[lFirst + 1][lSecond + 1];
        for (int i = 1; i <= lFirst; i++) {
            for (int j = 1; j <= lSecond; j++) {
                int n = first.charAt(i - 1);
                int m = second.charAt(j - 1);
                if (n == m) matrix[i][j] = matrix[i - 1][j - 1] + 1;
                else matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
            }
        }
        StringBuilder result = new StringBuilder();
         while (lFirst > 0 && lSecond > 0) {
             if (first.charAt(lFirst - 1) == second.charAt(lSecond - 1)) {
                 result.append(first.charAt(lFirst - 1));
                 lFirst --;
                 lSecond --;
             } else if (matrix[lFirst - 1][lSecond] > matrix[lFirst][lSecond - 1]) lFirst--;
             else  lSecond--;
         }
        return result.reverse().toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     * Время - О(nlogn), память - О(n).
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        ArrayList<Integer> result = new ArrayList<>();
        int[] a = new int[list.size()];
        int[] b = new int[list.size() + 1];
        int max = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            int count = 1;
            int m = max;
            while (count <= m) {
                int mid = (int) Math.ceil((count + m) / 2);
                if (list.get(b[mid]) <= list.get(i)) {
                    m = mid - 1;
                } else {
                    count = mid + 1;
                }
            }
            int newMax = count;
            a[i] = b[newMax - 1];
            b[newMax] = i;
            if (newMax > max) max = newMax;
        }
        int k = b[max];
        for (int i = max - 1; i >= 0; i--) {
            result.add(list.get(k));
            k = a[k];
        }
        return  result;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
