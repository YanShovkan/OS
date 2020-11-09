import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Core {
    Random random = new Random();
    private Queue<Process> processesQ = new LinkedList<>();

    public void planning() {
        Process process = processesQ.poll();
        while (process != null) {
            if (!startProcess(process)) {
                processesQ.add(process);
            }
            process = processesQ.poll();
        }
    }

    public void createProcesses() {
        int quantity = random.nextInt(10) + 1;
        for (int i = 1; i <= quantity; i++) {
            Process process = new Process(i);
            processesQ.add(process);
        }
    }

    public boolean startProcess(Process process) {
        Queue<Stream> StreamsQ = process.getStreamsQ();
        Stream stream = StreamsQ.poll();

        int totalTime = 0;

        System.out.print("Процесс " + process.getProcessID() + " начинает работать." + '\n');

        while (totalTime + stream.getWorkTime() <= process.getMaxTime() && stream != null) {
            totalTime += stream.getWorkTime();
            if (!stream.startStream()) {
                StreamsQ.add(stream);
            }
            stream = StreamsQ.poll();
            if (stream == null) {
                break;
            }
        }
        if (stream != null) {
            StreamsQ.add(stream);
            System.out.print("Процесс " + process.getProcessID() + " приостановлен через " + totalTime + " мс" + '\n' + '\n');
            return false;
        } else {
            System.out.print("Процесс " + process.getProcessID() + " выполнен за " + totalTime + " мс" + '\n' + '\n');
            return true;
        }
    }
}