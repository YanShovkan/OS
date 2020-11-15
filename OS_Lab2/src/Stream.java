import java.util.Random;

public class Stream {
    private Random random = new Random();

    private int streamID;
    private int time = 2;
    private int workTime = random.nextInt(3) + 1;

    public Stream(int streamID) {
        this.streamID = streamID;
    }

    public String startStream() {
        if (workTime > time) {
            workTime = workTime - time;
            return ("Время потока  " + streamID + " вышло, прошло " + time + " такт(ов)\n");
        }
        return ("Поток " + streamID + " выполнен за " + workTime + " такт(ов)\n");
    }

    public int getTime() {
        return time;
    }

    public void changeTime(int time) {
        this.time = time;
    }

    public void returnDefaultTime() {
        this.time = 2;
    }

    public int getStreamID() {
        return streamID;
    }

    public int getWorkTime() {
        if (workTime > time) {
            return time;
        }
        return workTime;
    }
}