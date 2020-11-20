import java.util.LinkedList;
import java.util.List;

public class PhysicalMemory {
    //тут хранятся таблицы
    private List<PageTabel> tabels = new LinkedList<PageTabel>();
    private List<Page> physicalTabel = new LinkedList<Page>();
    private int maxSize = 10;

    public void addTabel(PageTabel pageTabel) {
        tabels.add(pageTabel);
    }

    public PageTabel getTabel(int index) {
        return tabels.get(index);
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
                tabels.get(page.getProcessID()).setInPhysical(page.getID(), i);
                System.out.println("Изменение таблицы страниц");
                getTabel(page.getProcessID()).printTabel();
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
        tabels.get(physicalTabel.get(pageID).getProcessID()).deleteFromPhysical(pageID);
        physicalTabel.remove(pageID);
        physicalTabel.add(pageID, page);
        tabels.get(page.getProcessID()).setInPhysical(page.getID(), pageID);
        System.out.println("Изменение таблицы страниц");
        getTabel(page.getProcessID()).printTabel();
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