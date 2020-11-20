import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Core {
    Random random = new Random();
    private Queue<Process> processesQ = new LinkedList<>();

    public void createProcesses() {
        int processQuantity = random.nextInt(10) + 1;

        for (int i = 1; i <= processQuantity; i++) {
            Process process = new Process(i);
            processesQ.add(process);
            int streamQuantity = random.nextInt(10) + 1;
            Stream stream;
            Queue<Stream> streamsQ = new LinkedList<>();
            for (int j = 1; j <= streamQuantity; j++) {
                stream = new Stream(j);
                streamsQ.add(stream);
            }
            process.setStreamsQ(streamsQ);
        }
    }

    private String startProcess(Process process) {
        Queue<Stream> streamsQ = process.getStreamsQ();
        Stream stream = streamsQ.poll();
        int totalTime = 0;
        while (stream != null) {
            if (totalTime < process.getMaxTime()) {
                if (stream.getTime() > process.getMaxTime() - totalTime) {
                    stream.changeTime(process.getMaxTime() - totalTime);
                }
                totalTime += stream.getWorkTime();
                System.out.print("Поток " + stream.getStreamID() + " начинает выполнение\n");
                String result = stream.startStream();
                System.out.print(result);
                if (result.contains("вышло")) {
                    streamsQ.add(stream);
                }
                stream.returnDefaultTime();
                stream = streamsQ.poll();
            } else {
                break;
            }
        }
        if (stream != null) {
            streamsQ.add(stream);
            return ("Время процесса " + process.getProcessID() + " вышло, прошло " + totalTime + " такт(ов)\n\n");
        } else {
            return ("Процесс " + process.getProcessID() + " выполнен за " + totalTime + " такт(ов)\n\n");
        }
    }

    public void planning() {
        Process process = processesQ.poll();
        while (process != null) {
            System.out.print("Процесс " + process.getProcessID() + " начинает работать" + '\n');
            String result = startProcess(process);
            System.out.print(result);
            if (result.contains("вышло")) {
                processesQ.add(process);
            }
            process = processesQ.poll();
        }
    }
}