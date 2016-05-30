import java.util.Scanner;

public class Budget
{
    public class Bill
    {
        public double Amount;
        public String Name;
    }

    enum BillCategory_t {Rent, Electricity, Water, Food, Gas};
    static private int count = 0;
    static private Bill[] bills;
    static private Scanner input;

    public static void main(String[] args)
    {
        Budget2 myBudget = new Budget2();
        double total;
        input = new Scanner(System.in);
        System.out.print("Please enter the number of bills you would like to add: ");
        bills = new Bill[input.nextInt()];
        input.nextLine();

        for (BillCategory_t i: while (count < bills.length)
        {
            myBudget.printMenu();
            myBudget.getExpenses(input.nextInt());

            input.nextLine(); // Again clearing the buffer
        }

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

    public void printMenu()
    {
        System.out.println("You will be prompted for the amount after you choose one of the following options:");
        System.out.println("1. Rent");
        System.out.println("2. Electricity");
        /*
         * Just keep printing them, I put them all on separate lines
         * for management purposes, the escape for a new line is \n.
         * You could do - "1. Rent\n2. Electricity\n3. ... " and so forth
	     */
    }
}