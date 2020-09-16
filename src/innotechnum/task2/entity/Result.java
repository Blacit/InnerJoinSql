package innotechnum.task2.entity;

/**
 * Результирющая запись, полученная в результате объединения
 */
public class Result {
    /** Идентификатор (число) */
    private int id;

    /** Значение из первого списка */
    private String value1;

    /** Значение из второго списка */
    private String value2;

    /**
     * Инициализирует экземпляр класса
     * @param id Идентификатор (число)
     * @param value1 Значение из первого списка
     * @param value2 Значение из второго списка
     */
    public Result(int id, String value1, String value2) {
        this.id = id;
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return id + " " + value1 + " " + value2;
    }
}
