import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static final String FILE_NAME = "expenses.txt";

    public static void main(String[] args) {
        ArrayList<Expense> expenses = loadExpenses();// read expenses from file at the start of the program
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("--- Expense Tracker (File Enabled) ---");
        System.out.println("Loaded " + expenses.size() + " expenses from storage.");

        while (keepRunning) {
            // OPTION MENU
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Add New Expense");
            System.out.println("2. View All Expenses & Insights");
            System.out.println("3. Save and Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // LOGIC FOR ADDING EXPENSE (your validated code)
                    addNewExpense(expenses, scanner);
                    break;

                case "2":
                    // OPTION: LOGIC FOR VIEWING EXPENSES
                    showExpenses(expenses);
                    break;

                case "3":
                    // OPTION: LOGIC FOR SAVING AND EXITING
                    saveExpenses(expenses);
                    System.out.println("\nExpenses saved successfully. Goodbye!");
                    keepRunning = false;
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }
        scanner.close();
    }

    // --- METHOD FOR ADDING NEW EXPENSE ---Pick up the expense details from the
    // user
    private static void addNewExpense(ArrayList<Expense> expenses, Scanner scanner) {
        System.out.print("\nDescription: ");
        String desc = scanner.nextLine();

        double amount = 0;
        boolean validAmount = false;
        // This loop ensures that the user enters a valid number for the amount.
        // It keeps asking until a valid number is entered.
        while (!validAmount) {
            System.out.print("Amount (use . or , depending on your PC): ");
            try {
                String input = scanner.nextLine();
                // Replace comma with dot for decimal separator if needed, then parse the number
                amount = Double.parseDouble(input.replace(",", "."));
                validAmount = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount! Please enter a number.");
            }
        }

        System.out.print("Category: ");
        String cat = scanner.nextLine();
        // Create and add the expense to the list
        expenses.add(new Expense(desc, amount, cat));
        System.out.println("✔ Expense added!");
    }

    // --- METHOD FOR VIEWING EXPENSES (Insights) ---
    private static void showExpenses(ArrayList<Expense> expenses) {
        if (expenses.isEmpty()) {
            System.out.println("\nNo expenses recorded yet.");
            return;
        }

        System.out.println("\n--- List of Expenses ---");
        double total = 0;
        for (Expense e : expenses) {
            System.out.println(e); // UsestoString() method from Expense.java
            total += e.getAmount();
        }
        // After listing all expenses, we print the total amount spent.
        System.out.println("-----------------------");
        System.out.println("TOTAL SPENT: $" + total);
    }

    // --- METHODS FOR FILE HANDLING ---
    private static void saveExpenses(ArrayList<Expense> expenses) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenses) {
                writer.println(e.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    // This method reads the expenses from the file when the program starts.
    // It returns an ArrayList of Expense objects.
    private static ArrayList<Expense> loadExpenses() {
        ArrayList<Expense> loadedList = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists())
            return loadedList;

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    loadedList.add(new Expense(parts[0], Double.parseDouble(parts[1]), parts[2]));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading data.");
        }
        return loadedList;
    }
}
