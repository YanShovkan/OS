import java.util.LinkedList;
import java.util.Queue;

public class Process {
    private Queue<Stream> streamsQ = new LinkedList<>();

    private int processID;
    private int maxTime = 20;

    Process(int processID) {
        this.processID = processID;
    }

    public Queue<Stream> getStreamsQ() {
        return streamsQ;
    }

    public void setStreamsQ(Queue<Stream> streamsQ) {
        this.streamsQ = streamsQ;
    }

    public int getProcessID() {
        return processID;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public int getTotalTime() {
        int totalTime = 0;
        for (int i = 0; i < streamsQ.size(); i++) {
            Stream stream = streamsQ.poll();
            totalTime += stream.getWorkTime();
            streamsQ.add(stream);
        }
        return totalTime;
    }
}
