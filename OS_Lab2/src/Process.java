import java.util.LinkedList;
import java.util.Queue;

public class Process {
    private Queue<Stream> streamsQ = new LinkedList<>();

    private int processID;
    private int maxTime = 5;

    Process(int processID) {
        this.processID = processID;
    }

    public Queue<Stream> getStreamsQ() {
        return streamsQ;
    }

    public int getProcessID() {
        return processID;
    }

    public void setStreamsQ(Queue<Stream> streamsQ) {
        this.streamsQ = streamsQ;
    }

    public int getMaxTime() {
        return maxTime;
    }
}
