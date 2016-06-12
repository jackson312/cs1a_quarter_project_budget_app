import java.util.Scanner;

public class Budget
{
    // Class, to be used as a storage class.
    public class Bill
    {
        public double Amount = 0.0;
        public String Name = "";
    }

    private Scanner input = new Scanner(System.in);

    // Using an enum type so that we can easily map the location of the category and its name.
    private enum BillCategory_t
    {
        Food, Gas, Rent, Bills, Extra, Savings
    }

    private Bill[] budget = new Bill[BillCategory_t.values().length];
    private Bill[] actuals = new Bill[BillCategory_t.values().length];


    // Class Constructor, Initializes the two arrays with the storage class and sets the category names
    Budget()
    {
        // Create class for budget and actuals and initialize them
        for (BillCategory_t i : BillCategory_t.values())
        {
            budget[i.ordinal()] = new Bill();
            budget[i.ordinal()].Name = i.toString();

            actuals[i.ordinal()] = new Bill();
            actuals[i.ordinal()].Name = i.toString();
        }
    }


    public void clientEstimates()
    {
        //This method creates an array inputed with the Clients estimates for each category
        //categories we have : [0] -> food, [1] -> bills, [2] -> extras
        System.out.println("Please enter in the estimates for the categories asked below.");

        for (BillCategory_t i : BillCategory_t.values())
        {
            System.out.print("Enter in your monthly estimate for " + i.toString() + ": ");
            budget[i.ordinal()].Amount = input.nextDouble();
            System.out.println();
        }
    }

    private double calculateTotal(Bill[] list)
    {
        double total = 0.0;
        for (BillCategory_t i : BillCategory_t.values())
        {
            total += list[i.ordinal()].Amount;
        }
        return total;
    }

    public static void main(String[] args)
    {
        // We have to do this since the main is a statio function, we need at least one instance of the class
        // so here it is.
        Budget myBudget = new Budget();

        // Calls clientEstimates
        myBudget.clientEstimates();

        // This could be moved to inside another helper function
        for (BillCategory_t i : BillCategory_t.values())
        {
            System.out.printf("Category: %9s  Budgeted Amount: %8.2f  Actual is: %8.2f \n",
                              myBudget.budget[i.ordinal()].Name,
                              myBudget.budget[i.ordinal()].Amount,
                              myBudget.actuals[i.ordinal()].Amount);
        }

        System.out.printf("The total for the actuals is: %8.2f \n",
                          myBudget.calculateTotal(myBudget.budget));
    }
}


//Junk code, saving in case it has something useful


/*        double total;
        input = new Scanner(System.in);
        System.out.print("Please enter the number of bills you would like to add: ");
        bills = new Bill[input.nextInt()];
        input.nextLine();



        total = 0.0;
        for (Bill i: bills)
        {
            total += i.Amount;
        }
        System.out.println("The total amount for bills due is: $" + total);
    }

    public void getExpenses(BillCategory_t Category)
    {
        double Amount;

        switch (Cat)
        {
            case Rent:

                break;

            case 2:

                break;

            case 3:

                break;

            case 4:
                typeS = "food";
                break;

            case 5:
                typeS = "gas";
                break;

            case 6:

                break;
        }

        bills[count] = new Bill();
                count++;
        input.nextLine();
    }
*/