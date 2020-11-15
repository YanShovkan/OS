import java.util.LinkedList;
import java.util.List;

public class PageTabel {
    //это экземпляр одной таблицы
    private Process process;
    private List<Page> pages;
    private int[] tabelArray;

    PageTabel(Process process) {
        this.process = process;
        this.pages = process.getPages();
        fillPhysicalIDList();
    }

    private void fillPhysicalIDList() {
        tabelArray = new int[pages.size()];
        for (int i = 0; i < pages.size(); i++) {
            tabelArray[i] = -1;
        }
    }

    public void setInPhysical(int index, int adress) {
        tabelArray[index] = adress;
    }

    public void deleteFromPhysical(int adress) {
        for (int i = 0; i < pages.size(); i++) {
            if (tabelArray[i] == adress) {
                tabelArray[i] = -1;
            }
        }
    }

    public void printTabel() {
        System.out.println("Process number " + process.getID() + " tabel");
        System.out.println("       Virtual         |       Physical        |   Fault Frequency     | Total Fault Frequency");
        for (int i = 0; i < pages.size(); i++) {
            printTab(i);
            System.out.print("|");
            if (tabelArray[i] != -1) {
                printTab(tabelArray[i]);
                System.out.print("|");
            } else {
                System.out.print("            -          |");
            }
            printTab(process.getPage(i).getFaultFrequency());
            System.out.print("|");
            printTab(process.getFaultFrequency());
            System.out.print("\n");
        }
    }

    public void printTab(int digit) {
        if (digit < 10) {
            System.out.print("            " + digit + "          ");
        } else {
            System.out.print("           " + digit + "          ");
        }
    }
}