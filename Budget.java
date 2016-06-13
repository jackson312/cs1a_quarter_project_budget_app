package final_project;
import java.io.*;
import java.util.Scanner;

public class Budget
{
    // Class, to be used as a storage class.
    public class BillContainer_t
    {
        public double[] billList = new double[BillCategory_t.values().length];
        BillContainer_t()
        {
            for (BillCategory_t i : BillCategory_t.values())
            {
                billList[i.ordinal()] = 0.0;
            }
        }
    }

    private Scanner input = new Scanner(System.in);

    final String BudgetValues = "BudgetValues.dat";
    final String ActualValues = "ActualValues.dat";

    // Using an enum type so that we can easily map the location of the category and its name.
    public enum BillCategory_t
    {
        Food(0), Gas(1), Rent(2), Bills(3), Extra(4), Savings(5);

        private int value;

        private BillCategory_t(int value)
        {
            this.value = value;
        }
    }

    public BillContainer_t clientBudget = new BillContainer_t();
    public BillContainer_t clientActuals = new BillContainer_t();


    // Class Constructor, Initializes the two arrays with the storage class and sets the category names
    Budget()
    {

    }


    public void clientEstimates()
    {
        //This method creates an array inputed with the Clients estimates for each category
        //categories we have : [0] -> food, [1] -> bills, [2] -> extras
        System.out.println("Please enter in the estimates for the categories asked below.");

        for (BillCategory_t i : BillCategory_t.values())
        {
            System.out.print("Enter in your monthly estimate for " + i.toString() + ": ");
            clientBudget.billList[i.ordinal()] = input.nextDouble();
            System.out.println();
        }
    }

    public void savevalues(BillContainer_t dataToSave, String filename)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(filename);
            DataOutputStream dos = new DataOutputStream(fos);


            for (BillCategory_t i : BillCategory_t.values())
            {
                dos.writeDouble(dataToSave.billList[i.ordinal()]);
            }

        }
        catch (IOException e)
        {
            System.out.println("IOException in savevalues: " + e);
        }
    }

    public void readvalues(BillContainer_t dataToRead, String filename)
    {
        try
        {
            FileInputStream fis = new FileInputStream(filename);
            DataInputStream dis = new DataInputStream(fis);

            for (BillCategory_t i : BillCategory_t.values())
            {
                dataToRead.billList[i.ordinal()] = dis.readDouble();
            }
            dis.close();
        }
        catch (IOException e)
        {
            System.out.println("IOException in readvalues: " + e);
        }
    }

    private double calculateTotal(BillContainer_t list)
    {
        double total = 0.0;
        for (BillCategory_t i : BillCategory_t.values())
        {
            total += list.billList[i.ordinal()];
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

        myBudget.savevalues(myBudget.clientBudget, myBudget.BudgetValues);
        myBudget.readvalues(myBudget.clientBudget, myBudget.BudgetValues);

        // This could be moved to inside another helper function
        for (BillCategory_t i : BillCategory_t.values())
        {
            System.out.printf("Category: %9s  Budgeted Amount: %8.2f  Actual is: %8.2f \n",
                              i.toString(),
                              myBudget.clientBudget.billList[i.ordinal()],
                              myBudget.clientActuals.billList[i.ordinal()]);
        }

        System.out.printf("The total for the actuals is: %8.2f \n",
                          myBudget.calculateTotal(myBudget.clientBudget));
    }
}