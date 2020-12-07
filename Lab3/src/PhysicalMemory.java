import java.util.LinkedList;
import java.util.List;

public class PhysicalMemory {
    //тут хранятся таблицы
    private List<Page> physicalTabel = new LinkedList<Page>();
    private LinkedList<Process> processes = new LinkedList<Process>();
    private int maxSize = 10;

    public PhysicalMemory(LinkedList<Process> processes){
        this.processes = processes;
    }
    public void fillIDList() {
        for (int i = 0; i < maxSize; i++) {
            physicalTabel.add(i, null);
        }
    }

    public void setInTabel(Page page) {
        if (physicalTabel.contains(page)) {
            System.out.print("Изменений в таблице физической памяти нет\n\n");
            return;
        }
        for (int i = 0; i < maxSize; i++) {
            if (physicalTabel.get(i) == null) {
                physicalTabel.add(i, page);
                processes.get(page.getProcessID()).getTable().setInPhysical(page.getID(), i);
                System.out.println("Изменение таблицы страниц");
                processes.get(page.getProcessID()).getTable().printTabel();
                System.out.println("Изменение таблицы физической памяти");
                printTabel();
                return;
            }
        }
       pageFaultFrequency(page);
    }

    //Алгоритм Page Fault Frequency
    private void pageFaultFrequency(Page page) {
        int pageID = 0;
        int minFaultFrequency = physicalTabel.get(pageID).getFaultFrequency();

        for (int i = 1; i < maxSize; i++) {
            if (physicalTabel.get(i).getFaultFrequency() < minFaultFrequency) {
                minFaultFrequency = physicalTabel.get(i).getFaultFrequency();
                pageID = i;

            }
        }
        processes.get(physicalTabel.get(pageID).getProcessID()).getTable().deleteFromPhysical(pageID);
        physicalTabel.remove(pageID);
        physicalTabel.add(pageID, page);
        processes.get(physicalTabel.get(pageID).getProcessID()).getTable().setInPhysical(page.getID(), pageID);
        System.out.println("Изменение таблицы страниц");
        processes.get(page.getProcessID()).getTable().printTabel();
        System.out.println("Изменение таблицы физической памяти");
        printTabel();
    }

    public void printTabel() {
        System.out.println("Physical tabel");
        System.out.println("Adress | Page data");
        for (int i = 0; i < maxSize; i++) {
            printTab(i);
            if (physicalTabel.get(i) != null) {
                System.out.println("    " + physicalTabel.get(i).getData() + ", Fault Frequency: " + physicalTabel.get(i).getFaultFrequency());
            } else {
                System.out.println("     -");
            }
        }
        System.out.print("\n" + "\n");
    }

    public void printTab(int digit) {
        if (digit < 10) {
            System.out.print("     " + digit);
        } else {
            System.out.print("    " + digit);
        }
    }
}