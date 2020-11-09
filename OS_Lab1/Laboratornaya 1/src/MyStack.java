public class MyStack {

    /**
     * Класс стека
     * @autor Бутырин Владислав
     */

    /** Поле массив объектов */
    private final Object[] massive;

    /** Поле размера */
    private int size;

    /**
     * Конструктор - создание нового объекта с пустым массивом
     */
    public MyStack() {
        massive = new Object[255];
        size = 0;
    }

    /**
     * Добавление элемента в стек
     */
    public void push(Object element) {
        if (size < massive.length) {
            massive[size] = element;
            size++;
        }
    }

    /**
     * Удаление элемента из стека
     */
    public Object pop() {
        if(size>0) {
            size--;
            return massive[size];
        }
        return null;
    }
}
