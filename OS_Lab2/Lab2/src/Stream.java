import java.util.Random;

public class Stream {
    private Random random = new Random();

    private int streamID;
    private int time = 2;
    private int workTime = random.nextInt(10) + 1;

    public Stream(int streamID) {
        this.streamID = streamID;
    }

    public boolean startStream() {
        System.out.print("Поток " + streamID + " начинает выполнение\n");
        if (workTime > time) {
            System.out.print("Время потока  " + streamID + " вышло, можно было задействовать " + time + " такт(ов)\n");
            workTime = workTime - time;
            return false;
        }
        System.out.print("Поток " + streamID + " выполнен за " + workTime + " такт(ов)\n");
        return true;
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

    public int getWorkTime() {
        if (workTime > time) {
            return time;
        }
        return workTime;
    }
}