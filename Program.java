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

    private static ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    private static ArrayList<Order> orders = new ArrayList<Order>();

    public static void main (String[] args) {
        String fileName = args[0];
        ReadInput(fileName);
        Calculation();
        String outputString = BuildOutputString();
        WriteOutput(fileName, outputString);
    }

    public static void FillOrders(int teamSize) {
        System.out.println("Run");
        Order order = new Order(teamSize);

        order.addPizza(pizzas.get(0));
        pizzas.remove(0);

        for (int i = pizzas.size() - 1; i >= 0; i--) {
            if (!(order.comparePizza(pizzas.get(i)) == pizzas.get(i).getIngredients().size())) {
                order.addPizza(pizzas.get(i));
                pizzas.remove(i);
                i++;
            }
            if (order.getPizzas().size() == teamSize) {
                break;
            }
        }

        if (order.getPizzas().size() < teamSize) {
            while (true) {
                int diffIng = 1;

                for (int i = pizzas.size() - 1; i >= 0; i--) {
                    if (order.comparePizza(pizzas.get(i)) == pizzas.get(i).getIngredients().size() - i) {
                        order.addPizza(pizzas.get(i));
                        pizzas.remove(i);
                    }
                }

                if (order.getPizzas().size() == teamSize || diffIng == 4) {
                    break;
                }

                diffIng++;
            }
        }

        if (order.getPizzas().size() < teamSize) {
            int remaining = 4 - order.getPizzas().size();

            for (int i = 0; i < remaining; i++) {
                order.addPizza(pizzas.get(pizzas.size() - 1));
                pizzas.remove(pizzas.size() - 1);
            }
        }

        orders.add(order);
    }

    public static void Calculation() {
        Collections.sort(pizzas, new Comparator<Pizza>() {
            @Override public int compare(Pizza p1, Pizza p2) {
                return p2.getIngredients().size() - p1.getIngredients().size();
            }
        });

        int numberOfPlayers = teamsOfFour * 4 + teamsOfThree * 3 + teamsOfTwo * 2;

        if (numberOfPlayers > pizzaCount) {
            int rPizzaCount = pizzaCount;
            int rTeamsOfFour = teamsOfFour;
            int rTeamsOfThree = teamsOfThree;
            int rTeamsOfTwo = teamsOfTwo;

            if (rPizzaCount % 4 == 3) {
                System.out.println("Team3");
                FillOrders(3);

                rPizzaCount -= 3;
                rTeamsOfThree--;
            } else if (rPizzaCount % 4 == 2) {
                System.out.println("Team2");
                FillOrders(2);

                rPizzaCount -= 2;
                rTeamsOfTwo--;
            } else if (rPizzaCount % 4 == 1) {
                System.out.println("Team3&2");
                FillOrders(3);
                FillOrders(2);

                rPizzaCount -= 5;
                rTeamsOfThree--;
                rTeamsOfTwo--;
            }

            while (pizzas.size() >= 4 && rPizzaCount % 4 == 0 && rTeamsOfFour > 0) {
                System.out.println("Team4");
                FillOrders(4);

                rPizzaCount -= 4;
                rTeamsOfFour--;
            }
        }
    }

    private static String BuildOutputString() {
        String outputString = "";

        int numberOfOrders = orders.size();

        outputString += String.valueOf(numberOfOrders) + "\n";

        for (int i = 0; i < orders.size(); i++) {
            outputString += String.valueOf(orders.get(i).getTeamSize()) + " ";
            for (int j = 0; j < orders.get(i).getPizzas().size(); j++) {
                outputString += String.valueOf(orders.get(i).getPizzas().get(j).getId());

                if (j < orders.get(i).getPizzas().size() - 1) {
                    outputString += " ";
                }
            }
            if (i < orders.size() - 1) {
                outputString += "\n";
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

                for (int j = 1; j <= ingredientCount; j++) {
                    pizza.addIngredient(data.split(" ")[j]);
                }

                pizzas.add(pizza);
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