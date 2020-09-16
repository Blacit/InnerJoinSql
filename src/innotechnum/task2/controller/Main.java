package innotechnum.task2.controller;

import innotechnum.task2.entity.Entry;
import innotechnum.task2.entity.Result;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Выводим на консоль заголовок
        System.out.println("Объединение списков");

        String fileName1;
        String fileName2;

        try {
            // Считываем имена файлов из аргументов
            fileName1 = args[0];
            fileName2 = args[1];
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Не заполнены параметры запуска системы");
            return;
        }

        String[] array1;
        String[] array2;

        try {
            // Считываем данные из двух файлов и сохраняем их в массивы array1 и array2
            array1 = readFromFile(fileName1);
            array2 = readFromFile(fileName2);
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось открыть файл");
            return;
        } catch (IOException e) {
            System.out.println("Не удалось считать содержимое файла");
            return;
        }

        // Отображаем на консоли считанное содержимое файлов
        System.out.println("Содержимое файла " + fileName1 + ":");
        printArray(array1);

        System.out.println("\nСодержимое файла " + fileName2 + ":");
        printArray(array2);

        // Для считывания ввода пользователя
        Scanner s = new Scanner(System.in);
        int choice;

        // Отображаем меню
        System.out.println("\nВыберите алгоритм решения или команду: ");
        System.out.println("1 - ArrayList");
        System.out.println("2 - Отсортированный LinkedList");
        System.out.println("3 - HashMap");
        System.out.println("4 - Выход из программы");
        System.out.print("Введите значение: ");

        try {
            choice = s.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Неверный ввод. Необходимо ввести число от 1 до 4");
            return;
        }

        try {
            // В зависимости от выбора производим объединение массивов нужным способом
            switch (choice) {
                case 1:
                    System.out.println("Результат объединения с помощью ArrayList: ");
                    joinByArrayList(array1, array2);
                    break;
                case 2:
                    System.out.println("Результат объединения с помощью отсортированного LinkedList: ");
                    joinByLinkedList(array1, array2);
                    break;
                case 3:
                    System.out.println("Результат объединения с помощью HashMap: ");
                    joinByHashMap(array1, array2);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Неверный ввод. Необходимо ввести число от 1 до 4");
                    break;
            }
        } catch (NumberFormatException e){
            System.out.println("Неверный формат файла. Каждая строка файла должна начинаться с целого числа.");
            return;
        }
    }

    /**
     * Выводит на консоль содержимое массива построчно
     * @param array Строковый массив для вывода
     */
    private static void printArray(String[] array) {
        for (String s : array) {
            System.out.println(s);
        }
    }

    /**
     * Считывает строки из текстового файла и записывает их в массив строк
     * @param fileName Имя файла
     * @return Массив, содержащий строки из файла
     */
    private static String[] readFromFile(String fileName) throws IOException {

        // Создаём объект File, указывая ему имя файла,
        // а также объекты FileReader и BufferedReader
        // для его чтения
        File file = new File("resource/" + fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder stringBuilder = new StringBuilder();

        // Считываем сначала первую строку
        String line = bufferedReader.readLine();

        // Затем, пока файл не закончится, считываем остальные строки
        while (line != null) {
            stringBuilder.append(line).append("\n");
            line = bufferedReader.readLine();
        }

        // Помещаем считанные данные в массив, используя
        // в качестве разделителя символ перевода строки
        String[] array = stringBuilder.toString().split("\n");

        return array;
    }

    /**
     * Объединяет массивы с помощью ArrayList
     * @param array1 Первый массив
     * @param array2 Второй массив
     */
    private static void joinByArrayList(String[] array1, String[] array2) {
        // Инициализируем списки ArrayList для двух массивов и результата
        ArrayList<Entry> arrayList1 = convertToArrayList(array1);
        ArrayList<Entry> arrayList2 = convertToArrayList(array2);
        ArrayList<Result> arrayListResult = new ArrayList<>();

        /// Измерение времени выполнения
        long start = System.currentTimeMillis();
        //for (int i = 0; i < 1000000; i++) {
            /// Измерение времени выполнения

            arrayListResult.clear();
            // Выполняем обход двух списков
            for (Entry entry1 : arrayList1) {
                for (Entry entry2 : arrayList2) {
                    // Если id записи первого списка совпадает с id записи второго списка,
                    if (entry1.getId() == entry2.getId()) {
                        // создаём результирующую запись и добавляем её в список результатов
                        Result entryResult = new Result(entry1.getId(), entry1.getValue(), entry2.getValue());
                        arrayListResult.add(entryResult);
                    }
                }
            }

            /// Измерение времени выполнения
        //}
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        /// Измерение времени выполнения

        // Выводим результаты на консоль
        for (Result entryResult : arrayListResult) {
            System.out.println(entryResult);
        }

        /// Измерение времени выполнения
        System.out.println("Время выполнения: " + timeConsumedMillis + " мс");
        /// Измерение времени выполнения
    }

    /**
     * Преобразует массив строк в ArrayList записей (объектов Entry)
     * @param array Исходный массив строк
     * @return ArrayList объектов Entry, полученный из этого массива
     */
    private static ArrayList<Entry> convertToArrayList(String[] array){
        ArrayList<Entry> result = new ArrayList<>();

        for (String s : array) {
            String[] line = s.split(",");

            int id = Integer.parseInt(line[0]);
            String value = line[1];
            Entry entry = new Entry(id, value);
            result.add(entry);
        }

        return result;
    }

    /**
     * Объединяет массивы с помощью отсортированного LinkedList
     * @param array1 Первый массив
     * @param array2 Второй массив
     */
    private static void joinByLinkedList(String[] array1, String[] array2) {
        // Инициализируем списки LinkedList
        LinkedList<Entry> linkedList1 = convertToLinkedList(array1);
        LinkedList<Entry> linkedList2 = convertToLinkedList(array2);
        LinkedList<Result> resultLinkedList = new LinkedList<>();

        // Измерение времени выполнения
        long start = System.currentTimeMillis();

        // Инициализируем итераторы
        ListIterator<Entry> iterator1 = linkedList1.listIterator();
        ListIterator<Entry> iterator2 = linkedList2.listIterator();

        // Вложенного цикла здесь нет: перебор выполняется только для первого списка,
        // а итератор второго списка движется "параллельно" первому, смещаясь вперёд
        // или назад на нужное количество записей, в зависимости от ID
        while (iterator1.hasNext()){
            // Смещаемся на одну запись вперёд сразу для двух списков
            Entry entry1 = iterator1.next();
            Entry entry2 = iterator2.next();

            // Если ID записи в первом списке больше, чем ID соответствующей записи во втором,
            // перемещаем второй итератор вперёд до тех пор, пока второй ID не станет равен или больше первого
            while (entry1.getId() > entry2.getId()) {
                entry2 = iterator2.next();
            }

            // Если ID двух просматриваемых записей равны
            if (entry1.getId() == entry2.getId()) {
                // Количество совпадений ID просматриваемой записи в первом списке
                // с записями во втором списке
                int countMatches = 0;

                // Запускаем цикл while, поскольку одной записи в первом списке
                // могут соответствовать несколько записей во втором списке
                while (entry1.getId() == entry2.getId()) {
                    // Формируем результат и записываем его в список с результатами
                    Result entryResult = new Result(entry1.getId(), entry1.getValue(), entry2.getValue());
                    resultLinkedList.add(entryResult);

                    // Во втором списке перемещаемся на одну запись вперёд
                    entry2 = iterator2.next();

                    // Увеличиваем количество совпадений
                    countMatches++;
                }

                // После того как найдены все записи из второго списка,
                // соответствующие просматриваемой записи первого списка,
                // во втором списке возвращаемся на количество шагов,
                // равное количеству совпадений + 1. В этом случае итератор второго списка
                // окажется перед первой совпадающеё записью. Это нужно на тот случай,
                // если в первом списке тоже окажется больше одного такого ID.
                for (int i = 0; i < countMatches + 1; i++) {
                    if (iterator2.hasPrevious()) {
                        entry2 = iterator2.previous();
                    }
                }
            }

            // Если ID записи из первого списка меньше,
            // чем ID соответствующий ID из второго,
            // во втором списке возвращаемся на одну запись назад.
            if (entry1.getId() < entry2.getId()) {
                iterator2.previous();
            }
        }

        // Измерение времени выполнения
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;

        for (Result entryResult : resultLinkedList) {
            System.out.println(entryResult);
        }

        System.out.println("Время выполнения: " + timeConsumedMillis + " мс");
    }

    /**
     * Преобразует массив строк в LinkedList записей (объектов Entry)
     * @param array Исходный массив строк
     * @return LinkedList объектов Entry, полученный из этого массива
     */
    private static LinkedList<Entry> convertToLinkedList(String[] array) {
        LinkedList<Entry> result = new LinkedList<>();

        for (String s : array) {
            String[] line = s.split(",");

            int id = Integer.parseInt(line[0]);
            String value = line[1];
            Entry entry = new Entry(id, value);
            result.add(entry);
        }

        Collections.sort(result);

        return result;
    }

    /**
     * Объединяет массивы с помощью HashMap
     * @param array1 Первый массив
     * @param array2 Второй массив
     */
    private static void joinByHashMap(String[] array1, String[] array2) {
        // Инициализируем объекты hashMap и записываем в них данные из массивов
        HashMap<Integer, ArrayList<String>> hashMap1 = convertToHashMap(array1);
        HashMap<Integer, ArrayList<String>> hashMap2 = convertToHashMap(array2);

        // Перебираем все записи из первого HashMap
        for (Map.Entry entry : hashMap1.entrySet()){

            // Получаем ключ текущей записи
            int key = (int) entry.getKey();

            // Если второй HashMap содержит текущий ключ,
            if (hashMap2.containsKey(key)){

                // Получаем списки значений из двух этих объектов HashMap,
                // формируем из них пары и выводим на консоль
                ArrayList<String> arrayList1 = hashMap1.get(key);
                ArrayList<String> arrayList2 = hashMap2.get(key);

                for (String value1 : arrayList1) {
                    for (String value2 : arrayList2) {
                        System.out.println(key + " " + value1 + " " + value2);
                    }
                }
            }
        }
    }

    /**
     * Преобразует массив строк в HashMap. При этом ключом является ID записи (число),
     * а в качестве значения - список (ArrayList) строк, которые соответствуют этому ID
     * @param array Исходный массив строк
     * @return Объект HashMap, полученный из этого массива
     */
    private static HashMap<Integer, ArrayList<String>> convertToHashMap(String[] array){
        HashMap<Integer, ArrayList<String>> hashMap = new HashMap<>();

        for (String s : array) {
            String[] line = s.split(",");

            int id = Integer.parseInt(line[0]);
            String value = line[1];

            if (hashMap.containsKey(id)) {
                hashMap.get(id).add(value);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(value);
                hashMap.put(id, list);
            }
        }

        return hashMap;
    }
}
