import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Program {
    public static void main (String [] args) {

    }

    private static String BuildOutputString() {
        String outputString = "";

        return outputString;
    }

    private static void ReadInput(String name) {
        String inputPath = "C:\\Users\\jasoncfinley\\IdeaProjects\\Polyglots2021\\InputFiles\\" + name + ".txt";
        System.out.println(inputPath);

        try {
            File myObj = new File(inputPath);
            Scanner myReader = new Scanner(myObj);

            // Gather first line data
            String firstLine = myReader.nextLine();

            rows = Integer.parseInt(firstLine.split(" ")[0]);
            slots = Integer.parseInt(firstLine.split(" ")[1]);
            unavailableSlotCount = Integer.parseInt(firstLine.split(" ")[2]);
            poolCount = Integer.parseInt(firstLine.split(" ")[3]);
            serverCount = Integer.parseInt(firstLine.split(" ")[4]);

            int unavailableSlots[][] = new int[unavailableSlotCount][2];

            for (int i = 0; i < unavailableSlotCount; i++) {
                String data = myReader.nextLine();
                unavailableSlots[i][0] = Integer.parseInt(data.split(" ")[0]);
                unavailableSlots[i][1] = Integer.parseInt(data.split(" ")[1]);
            }

            serverRoom = new ServerRoom(rows, slots);
            pools = new Pool[poolCount];

            serverRoom.addUnavailableSlots(unavailableSlots);

            servers = new Server[serverCount];

            for (int i = 0; i < serverCount; i++) {
                String data = myReader.nextLine();
                int size = Integer.parseInt(data.split(" ")[0]);
                int capacity = Integer.parseInt(data.split(" ")[1]);

                Server server = new Server(size, capacity);
                servers[i] = server;
            }

            for (int i = 0; i < serverCount; i++) {
                servers[i].printStats();
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    private static void WriteOutput(String name, String str) {
        String outputPath = "C:\\Users\\jasoncfinley\\IdeaProjects\\Polyglots2021\\OutputFiles\\" + name + ".txt";

        try {
            System.out.println(outputPath);
            File file = new File(outputPath);

            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists!");
            }

            try(FileWriter writer = new FileWriter(outputPath)) {
                writer.write(str);
                writer.flush();
            } catch (IOException e) {
                System.out.println("An error occured.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }
}