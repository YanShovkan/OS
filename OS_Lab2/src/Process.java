import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Process {
    Random random = new Random();
    private Stream stream;

    private Queue<Stream> streamsQ = new LinkedList<>();

    private int processID;
    private int maxTime = 10;
    private int quantity = random.nextInt(10) + 1;

    Process(int processID) {
        this.processID = processID;
        createStreams();
    }

    public void createStreams() {
        for (int i = 1; i <= quantity; i++) {
            stream = new Stream(i);
            streamsQ.add(stream);
        }
    }

    public Queue<Stream> getStreamsQ() {
        return streamsQ;
    }

    public int getProcessID() {
        return processID;
    }

    public int getMaxTime() {
        return maxTime;
    }
}