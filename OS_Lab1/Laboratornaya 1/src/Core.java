import java.util.HashMap;

public class Core {
    /** Создает экземпляр класса myStack */
    private MyStack myStack;
    /** Создает хешмап */
    private HashMap<Integer, SystemCall> sysHashMap;

    /** Метод вызова системных вызовов */
    public void callSysCall(int key) {
        try {
            if (sysHashMap.get(key) == null) {
                throw new KeyNotFoundException();
            }
            System.out.println("Try: System Call " + key);
            System.out.println("System Call work with:");
            for (int i = 0; i < sysHashMap.get(key).getParametersCount(); i++) {
                Object tmp = myStack.pop();
                if(tmp == null){
                    throw new ArgumentsErrorException();
                }
                switch (sysHashMap.get(key).getParameters()[i]) {
                    case "Integer":
                        int tryToCastInt = (int) tmp;
                        System.out.println("Integer");
                        System.out.println("Value: " + tryToCastInt);

                        break;
                    case "String":
                        String tryToCastString = (String) tmp;
                        System.out.println("String");
                        System.out.println("Value: " + tryToCastString);
                        break;
                    case "Double":
                        Double tryToCastDouble = (Double) tmp;
                        System.out.println("Double");
                        System.out.println("Value: " + tryToCastDouble);
                        break;
                }
            }
        } catch (ClassCastException e) {
            System.out.println("Call " + key + " return error:\ninvalid parameter type");
        } catch (ArgumentsErrorException e) {
            System.out.println("Call " + key + " return error:\nIncorrect number of input parameters");
        } catch (KeyNotFoundException e) {
            System.out.println("Call " + key + " return error:\nSystem Сall with identifier " + key + " not found");
        }
    }

    /** Заполняет хешмап */
    private void initSysCalls() {
        sysHashMap = new HashMap<>(5);
        sysHashMap.put(1, new SystemCall("Integer", "String"));
        sysHashMap.put(2, new SystemCall("String", "String", "Integer"));
        sysHashMap.put(3, new SystemCall("Integer", "String", "Double"));
        sysHashMap.put(4, new SystemCall("String", "Double"));
        sysHashMap.put(5, new SystemCall("String", "Integer"));
    }

    /** Вызывает методы для заполнения хешмапы иприсвоения стека */
    public Core(MyStack myStack) {
        initSysCalls();
        setMyStack(myStack);
    }
    /** Присвоение стека */
    public void setMyStack(MyStack myStack) {
        this.myStack = myStack;
    }
    /** Ошибка1 */
    static class KeyNotFoundException extends Exception {
    }
    /** Ошибка2 */
    static class ArgumentsErrorException extends Exception {
    }
}
    Ivan Ivanov
    Ivan Ivanov  2:15 pm

//@author Kuzmichev
public class SystemCall {
    //поле параметры
//list of parameters
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