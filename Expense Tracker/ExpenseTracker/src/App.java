import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArrayList<Expense> expenses = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("--- Expense Tracker ---");
// The main loop to keep asking for expenses until the user decides to stop
        while (keepRunning) {
            // Pick up the expense details from the user
            System.out.print("\nDescription: ");
            String desc = scanner.nextLine();
            
            System.out.print("Amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Important: Clear the "Enter" from the buffer
            
            System.out.print("Category: ");
            String cat = scanner.nextLine();

            // Create and add the expense to the list
            expenses.add(new Expense(desc, amount, cat));

            // Ask if the user wants to add another expense
            System.out.print("\nDo you want to add another expense? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            
            if (response.equals("no")) {
                keepRunning = false;
            }
        }

        // Show the final summary
        System.out.println("\n--- Summary of Expenses ---");
        double total = 0;
        for (Expense e : expenses) {
            System.out.println(e);
            total += e.getAmount();
        }
        
        System.out.println("\nTotal Spent: $" + total);
        System.out.println("Thanks for using the Tracker!");
        
        scanner.close();
    }
}

