import java.util.Random;

public class Stream {
    private Random random= new Random();

    private int streamID;
    private int time = 2;
    private int workTime = random.nextInt(10) + 1;

    public Stream(int streamID) {
        this.streamID = streamID;
    }

    public boolean startStream() {
        System.out.print("Поток " + streamID + " начинает выполнение." + '\n');
        if (workTime > time) {
            System.out.print("Поток " + streamID + " приостановлен через " + time + " мс" + '\n');
            workTime = workTime - time;
            return false;
        }
        System.out.print("Поток " + streamID + " выполнен за " + workTime + " мс" + '\n');
        return true;
    }

    public int getWorkTime() {
        if (workTime > time) {
            return time;
        }
        return workTime;
    }
}