//@author Kuzmichev
public class SystemCall {
    /**
     *поле параметры
     *list of parameters
     * */
    private String[] parameters;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param parameters
     *
     */
    public SystemCall(String... parameters) {
        this.parameters = parameters;

    }
    /**
     * Функция получения значения поля {@link Product#maker}
     * @return возвращает название параметров
     */
    public String[] getParameters() {
        return parameters;
    }

    /**
     * Функция получения значения поля
     * @return возвращает количество
     */
    public int getParametersCount() {
        return parameters.length;
    }
}