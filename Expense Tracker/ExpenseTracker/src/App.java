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
        System.out.print("Enter a category to filter by (or press Enter to show all): ");
        String filter = scanner.nextLine().trim(); // .trim() removes any extra spaces from the input

        double categoryTotal = 0;
        System.out.println("\nResults for: " + filter);

        for (Expense e : expenses) {
            // Compare the category of each expense with the filter, ignoring case
            // sensitivity. If the filter is "all" or it is empty
            // or matches the expense's category, we display it and add to the total.
            if (filter.equalsIgnoreCase("all") || filter.isEmpty() || e.getCategory().equalsIgnoreCase(filter)) {
                System.out.println(e);
                categoryTotal += e.getAmount();
            }
        }
        System.out.println("---------------------------");
        System.out.println("Total for this view: $" + categoryTotal);

        scanner.close();
    }
}
