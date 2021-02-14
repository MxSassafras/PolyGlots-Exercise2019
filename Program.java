import Exercise2019Package.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.util.Comparator;
import java.util.Collections;

public class Program {
    private static int pizzaCount;
    private static int teamsOfTwo;
    private static int teamsOfThree;
    private static int teamsOfFour;

    private static ArrayList<Pizza> pizzas;
    private static ArrayList<Order> orders;

    public static void main (String[] args) {
        String fileName = args[0];
        ReadInput(fileName);
        String outputString = BuildOutputString();
        WriteOutput(fileName, outputString);
    }

    public static void Calculation() {
        Collections.sort(pizzas, new Comparator<Pizza>() {
            @Override public int compare(Pizza p1, Pizza p2) {
                return p1.getIngredients().size() - p2.getIngredients().size();
            }
        });
    }

    private static String BuildOutputString() {
        String outputString = "";

        int numberOfOrders = orders.size();

        outputString += String.valueOf(numberOfOrders) + "/n";

        for (int i = 0; i < orders.size(); i++) {
            for (int j = 0; j < orders.get(i).getPizzas().size(); j++) {
                outputString += String.valueOf(orders.get(i).getPizzas().get(j));
            }
            if (i < orders.size() - 1) {
                outputString += "/n";
            }
        }

        return outputString;
    }

    private static void ReadInput(String name) {
        String inputPath = "InputFiles\\" + name + ".txt";
        System.out.println(inputPath);

        try {
            File myObj = new File(inputPath);
            Scanner myReader = new Scanner(myObj);

            // Gather first line data
            String firstRow = myReader.nextLine();

            pizzaCount = Integer.parseInt(firstRow.split(" ")[0]);
            teamsOfTwo = Integer.parseInt(firstRow.split(" ")[1]);
            teamsOfThree = Integer.parseInt(firstRow.split(" ")[2]);
            teamsOfFour = Integer.parseInt(firstRow.split(" ")[3]);

            for (int i = 0; i < pizzaCount; i++) {
                Pizza pizza = new Pizza(i);

                String data = myReader.nextLine();

                int ingredientCount = Integer.parseInt(data.split(" ")[0]);

                System.out.println(ingredientCount);

                for (int j = 1; j <= ingredientCount; j++) {
                    System.out.println(data.split(" ")[j]);
                    pizza.addIngredient(data.split(" ")[j]);
                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    private static void WriteOutput(String name, String str) {
        String outputPath = "OutputFiles\\" + name + ".txt";

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