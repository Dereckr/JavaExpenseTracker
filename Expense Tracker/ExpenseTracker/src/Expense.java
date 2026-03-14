import java.time.LocalDate;

public class Expense {
    private String description;
    private double amount;
    private String category;
    private LocalDate date;


    public Expense(String description, double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        // present date
        this.date = LocalDate.now(); 
    }

    // Getters so the information can be analyze later
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
     // This method overrides the default Java behavior to define 
    // how this object should be displayed as text.
    @Override
    public String toString() {
        return date + " | " + category + " | $" + amount + " | " + description;
    }
}