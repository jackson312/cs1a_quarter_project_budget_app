package final_project;
import java.io.*;
import java.util.Scanner;

import static final_project.Budget.BillCategory_t.Food;

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
    private double MonthlySalary;

    private Scanner input = new Scanner(System.in);

    final String BudgetValues = "BudgetValues.dat";
    final String ActualValues = "ActualValues.dat";
    final String MonthlySalaryfile = "MonthlySalary.dat";

    // Using an enum type so that we can easily map the location of the category and its name.
    public enum BillCategory_t
    {
        Rent(0), Bills(1), Food(2), Gas(3), Other(4), Savings(5);

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
        if (new File(BudgetValues).isFile())
        {
            readvalues(clientBudget, BudgetValues);
        }

        if (new File(ActualValues).isFile())
        {
            readvalues(clientActuals, ActualValues);
        }

        readinMonthlySalary();
    }

    public double GetMonthlySalary()
    {

        return MonthlySalary;
    }

    public void saveMonthlyIncome(String income)
    {
        try
        {
            double tempdbl;
            String tempstr;
            PrintWriter fout = new PrintWriter(MonthlySalaryfile);
            fout.println(income);
            fout.close();
        }
        catch (IOException e)
        {
            System.out.println("IOException in savevalues: " + e);
        }
    }

    public void readinMonthlySalary()
    {
        try
        {
            String tempstr;
            if (new File(MonthlySalaryfile).isFile())
            {
                BufferedReader fin = new BufferedReader(new FileReader(MonthlySalaryfile));
                tempstr = fin.readLine();
                if (tempstr != "")
                {
                    MonthlySalary = Double.parseDouble(tempstr);
                }
                fin.close();
            }
        }
        catch (IOException e)
        {
            System.out.println("IOException in readvalues: " + e);
        }
        //catch (FileNotFoundException e1)
        //{
          //  System.out.println("FileNotFoundException in ReadinMonthlySalary: " + e1);
        //}
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
            double tempdbl;
            String tempstr;
            PrintWriter fout = new PrintWriter(filename);

            for (BillCategory_t i : BillCategory_t.values())
            {
                tempdbl = dataToSave.billList[i.ordinal()];
                tempstr = Double.toString(tempdbl);
                fout.println(tempstr);
            }
            fout.close();
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
            int i = 0;
            String tempstr;
            BufferedReader fin = new BufferedReader(new FileReader (filename));

            while (((tempstr = fin.readLine()) != null) && (i < BillCategory_t.values().length))
            {
                dataToRead.billList[i] = Double.parseDouble(tempstr);
                i++;
            }

            fin.close();
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