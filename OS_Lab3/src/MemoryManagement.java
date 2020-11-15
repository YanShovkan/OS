import java.util.Random;

public class MemoryManagement {
    //это основное рабочее пространство
    Random random = new Random();
    private int quantityProcess;
    MyDisk myDisk = new MyDisk();
    PhysicalMemory physicalMemory = new PhysicalMemory();

    public void createProsess(int quantityProcess) {
        //создание процессов и таблиц
        this.quantityProcess = quantityProcess;
        for (int i = 0; i < quantityProcess; i++) {
            Process newProcess = new Process(i);
            myDisk.addProcess(newProcess);
            PageTabel pageTabel = new PageTabel(newProcess);
            physicalMemory.addTabel(pageTabel);
        }
    }

    public void work(int quantity) {
        //работа программы
        physicalMemory.fillIDList();
        for (int i = 0; i < quantity; i++) {
            Process process = myDisk.getProcess(random.nextInt(quantityProcess));
            Page page = process.getPage(random.nextInt(process.getPages().size()));
            System.out.println("ОС запрашивает у процесса " + page.getProcessID() + " страницу " + page.getID());
            physicalMemory.setInTabel(page);
        }

        System.out.println("Финальные таблицы");
        for(int i = 0 ; i < quantityProcess;i++){
            physicalMemory.getTabel(i).printTabel();
        }
        physicalMemory.printTabel();
    }
}