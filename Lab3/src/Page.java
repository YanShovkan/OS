public class Page {
    //экземпляр страницы
    private int ID;
    private int processID;
    private String data;
    private int faultFrequency;

    public Page(int processID, int ID, String data, int faultFrequency) {
        this.processID = processID;
        this.ID = ID;
        this.data = data;
        this.faultFrequency = faultFrequency;
    }

    public String getData() {
        return data;
    }

    public int getID() {
        return ID;
    }

    public int getProcessID() {
        return processID;
    }

    public int getFaultFrequency() {
        return faultFrequency;
    }
}