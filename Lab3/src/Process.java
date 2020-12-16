import java.util.*;

public class Process {
    //экземпляр процесса
    Random random = new Random();

    private PageTabel pageTabel;

    public PageTabel getTable() {
        return pageTabel;
    }

    private List<Page> pages = new LinkedList<Page>();
    private int ID;
    private int faultFrequency;
    private int quantityPages = random.nextInt(10) + 1;

    Process(int ID) {
        this.ID = ID;
        createPages();
        pageTabel = new PageTabel(this);
    }

    private void createPages() {
        for (int i = 0; i < quantityPages; i++) {
            int pageFaultFrequency = random.nextInt(5) + 1;
            Page page = new Page(ID, i, "processID: " + ID + ", pageID:  " + i, pageFaultFrequency);
            faultFrequency += pageFaultFrequency;
            pages.add(page);
        }
    }

    public int getID() {
        return ID;
    }

    public int getFaultFrequency() {
        return faultFrequency;
    }

    public List<Page> getPages() {
        return pages;
    }

    public Page getPage(int index) {
        return pages.get(index);
    }

    public void work() {
        System.out.println("Процесс с ID " + ID + " выполняет работу...");
    }

}