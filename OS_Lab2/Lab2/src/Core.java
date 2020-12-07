import java.util.*;

public class Core {
    Random random = new Random();

    private HashMap<Integer, Process> prosessesMap;
    private LinkedList<Integer> lotteryTicket = new LinkedList<Integer>();


    public void createProcesses() {
        int processQuantity = 5;
        prosessesMap = new HashMap<Integer, Process>();

        for (int i = 1; i <= processQuantity; i++) {
            for (int count = 0; count < random.nextInt(10) + 1; count++) {
                lotteryTicket.add(i);
            }
            Process process = new Process(i);
            int streamQuantity = random.nextInt(10) + 1;
            Stream stream;
            Queue<Stream> streamsQ = new LinkedList<>();
            for (int j = 1; j <= streamQuantity; j++) {
                stream = new Stream(j);
                streamsQ.add(stream);
            }
            process.setStreamsQ(streamsQ);
            prosessesMap.put(i, process);
        }

    }

    public void printAllProcesses() {
        for (int i = 1; i <= prosessesMap.size(); i++) {
            Process process = prosessesMap.get(i);
            System.out.println("Процесс " + i + " затрачивает " + process.getTotalTime() + " такт(ов)");
            prosessesMap.put(i, process);
        }
        System.out.print("\n");
    }

    public void planning() {
        while (!prosessesMap.isEmpty()) {
            int index = random.nextInt(lotteryTicket.size());
            int loteryResult = lotteryTicket.get(index);

            if(prosessesMap.containsKey(loteryResult)) {
                Process process = prosessesMap.get(loteryResult);
                System.out.println("Процесс " + loteryResult + " начинает выполнение");
                String result = startProcess(process);
                System.out.println(result);
                if (!result.contains("вышло")) {
                    prosessesMap.remove(loteryResult);
                    for(int i = 0;i<lotteryTicket.size();i++){
                        if(lotteryTicket.get(i)==loteryResult){
                            lotteryTicket.remove(i);
                        }
                    }
                }
            }
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
                totalTime += stream.getRealWorkTime();
                System.out.print("Поток " + stream.getStreamID() + " начинает выполнение\n");
                String result = stream.work();
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

}