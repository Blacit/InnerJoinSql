package innotechnum.task2.entity;

/**
 * Исходная запись, считанная из текстового файла
 */
public class Entry implements Comparable<Entry>{
    /** Идентификатор (число) */
    private int id;

    /** Значение (строка) */
    private String value;

    /**
     * Инициализирует экземпляр класса
     * @param id Идентификатор (число)
     * @param value Значение (строка)
     */
    public Entry(int id, String value) {
        this.id = id;
        this.value = value;
    }

    /**
     * Возвращает идентификатор записи
     * @return Идетификатор записи
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает значение записи
     * @return Знчение записи
     */
    public String getValue() {
        return value;
    }

    /**
     * Сравнивает данную запись с другой по id
     * @param entry Объект Entry (запись), с которым сравнивается данный объект
     * @return Результат сравнения (положительное число в случае, если id текущей записи больше, чем id второй записи; 0 в случае, если они равны, и отрицательное число, если id текущей записи меньше).
     */
    @Override
    public int compareTo(Entry entry) {
        return id - entry.getId();
    }

    @Override
    public String toString() {
        return id + " " + value;
    }
}
