import java.util.*;

public class MyDisk {
    //тут хрянятся все процессы
    private List<Process> proceses = new LinkedList<Process>();

    public void addProcess(Process process) {
        proceses.add(process);
    }

    public Process getProcess(int index) {
        return proceses.get(index);
    }
}