package final_project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Window
{

    private JFrame frame;
    private JPanel contentPane;
    private JPanel contentPane2;
    private JPanel contentPane3;
    private JLabel text;
    private JButton button;
    private JTable table;
    private JTable table2;
    private DefaultTableModel model2;
    private JLabel rent;
    private JLabel bills;
    private JLabel food;
    private JLabel gas;
    private JLabel other;
    private JLabel savings;
    private JButton showButton;
    private JLabel currentMonth;
    private JLabel currentMonth2;
    String month = null;
    String s = "";
    private JTable cp3table;
    private JTable cp3table2;
    private JLabel cp3rent;
    private JLabel cp3bills;
    private JLabel cp3food;
    private JLabel cp3gas;
    private JLabel cp3other;
    private JLabel cp3savings;
    private JButton showperLast;
    private JTable suggesttable;
    private JTable lastMonthtable2;
    private JTable thisMonthtable;
    private JButton compareButton;
    private JButton compareButton2;
    private JLabel lastMonth;
    private JLabel newMonth;
    private JLabel suggest;
    private JButton showperCurrent;
    private JLabel adjustments;
    private JButton save;
    private JTextArea salaryinput;

    ArrayList<String> possibilities = new ArrayList<String>(Arrays.asList("January", "February", "March",
                                                                      "April", "May", "June", "July",
                                                                      "August", "September", "October",
                                                                      "November", "December"));
    public Window()
    {
        double salarydb = 1;

        myBudget = new Budget();
        initialize();

        salaryinput.setText(Double.toString(myBudget.GetMonthlySalary()));

        for (Budget.BillCategory_t i : Budget.BillCategory_t.values())
        {

            table.setValueAt(String.format(" %10.2f", myBudget.clientBudget.billList[i.ordinal()]), i.ordinal(), 0);

            table2.setValueAt((String.format(" %10.2f%%", (myBudget.clientActuals.billList[i.ordinal()] * 100.0))),
                                  i.ordinal(), 0);
        }
    }

    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e1)
        {
            e1.printStackTrace();
        }

        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    Window window = new Window();
                    window.frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    private Budget myBudget;

    public void initialize()
    {
        frame = new JFrame("The Budget.");
        frame.setVisible(true);
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(1, 1));
        frame.setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane2 = new JPanel();
        contentPane2.setLayout(null);
        contentPane3 = new JPanel();
        contentPane3.setLayout(null);

        contentPane.setBackground(SystemColor.inactiveCaptionBorder);
        contentPane2.setBackground(SystemColor.inactiveCaptionBorder);
        contentPane3.setBackground(SystemColor.inactiveCaptionBorder);

        text = new JLabel();

        frame.getContentPane().add(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("Hello! :D");
        label.setMaximumSize(new Dimension(176, 20));
        label.setHorizontalTextPosition(SwingConstants.LEADING);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.DARK_GRAY);
        label.setFont(new Font("Tahoma", Font.PLAIN, 40));
        label.setAlignmentY(1.0f);
        label.setBounds(0, 24, 784, 49);
        contentPane.add(label);

        JLabel label2 = new JLabel("What would you like me to do?");
        label2.setBounds(0, 102, 784, 49);
        label2.setMaximumSize(new Dimension(176, 20));
        label2.setHorizontalTextPosition(SwingConstants.LEADING);
        label2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        label2.setForeground(Color.DARK_GRAY);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 40));
        contentPane.add(label2);

        //First Button & Pane2
        button = new JButton("Set up or modify budget");
        button.setFont(new Font("Tahoma", Font.PLAIN, 18));
        button.setBackground(Color.WHITE);
        button.setBounds(244, 252, 287, 90);

        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                while (month == null)
                {
                    month = (String) JOptionPane.showInputDialog(
                            frame,
                            "What month is it?\n", "Select Month",
                            JOptionPane.PLAIN_MESSAGE,
                            null, possibilities.toArray(),
                            "January");
                    currentMonth.setText(month);
                }

                frame.getContentPane().remove(contentPane);
                frame.getContentPane().add(contentPane2);
                frame.getContentPane().validate();
            }
        });

        contentPane.add(button);
        JLabel salary = new JLabel("Enter monthly income: ");
        salary.setFont(new Font("Tahoma", Font.BOLD, 13));
        salary.setBounds(412, 80, 150, 47);
        contentPane2.add(salary);

        salaryinput = new JTextArea();
        salaryinput.setBounds(588, 90, 78, 30);
        contentPane2.add(salaryinput);

        String[] columns1 = {"Amount"};
        Object[][] tableData = new Object[Budget.BillCategory_t.values().length][1];
        table = new JTable(tableData, columns1);

        table.setShowVerticalLines(false);
        table.setRowHeight(57);
        table.setColumnSelectionAllowed(true);
        table.setCellSelectionEnabled(true);
        table.setBounds(500, 134, 78, 337);
        contentPane2.add(table);

        String[] columns2 = {"Amount"};
        Object[][] table2Data = new Object[Budget.BillCategory_t.values().length][1];
        table2 = new JTable(table2Data, columns2);
        table2.setEnabled(false);
        table2.setShowVerticalLines(false);
        table2.setRowSelectionAllowed(false);
        table2.setRowHeight(57);
        table2.setBounds(588, 134, 78, 337);
        contentPane2.add(table2);

        int offset = 134;
        JLabel[] LabelList = new JLabel[Budget.BillCategory_t.values().length];
        for (Budget.BillCategory_t i : Budget.BillCategory_t.values())
        {
            LabelList[i.ordinal()] = new JLabel(i.toString());
            LabelList[i.ordinal()].setFont(new Font("Tahoma", Font.BOLD, 13));
            LabelList[i.ordinal()].setBounds(412, offset, 78, 47);
            contentPane2.add(LabelList[i.ordinal()]);
            offset += 58;
        }

        showButton = new JButton("Show %");
        showButton.setBounds(500, 485, 78, 36);
        contentPane2.add(showButton);
        showButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String salary;
                double salarydb;
                salary = salaryinput.getText();
                myBudget.saveMonthlyIncome(salary);
                salarydb = Double.parseDouble(salary);

                for (Budget.BillCategory_t i : Budget.BillCategory_t.values())
                {
                    myBudget.clientActuals.billList[i.ordinal()] = (myBudget.clientBudget.billList[i.ordinal()] / salarydb);
                    table2.setValueAt((String.format(" %10.2f%%", (myBudget.clientActuals.billList[i.ordinal()] * 100.0))), i.ordinal(), 0);
                }
                myBudget.savevalues(myBudget.clientActuals, myBudget.ActualValues);
            }
        });

        currentMonth = new JLabel();
        currentMonth.setFont(new Font("Tahoma", Font.PLAIN, 40));
        currentMonth.setBounds(44, 11, 400, 75);
        contentPane2.add(currentMonth);

        // Save Button Setup
        save = new JButton("Save");
        save.setBounds(648, 11, 89, 23);
        contentPane2.add(save);
        save.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String tmpstr;
                for (Budget.BillCategory_t i : Budget.BillCategory_t.values())
                {
                    tmpstr = (String) table.getValueAt(i.ordinal(), 0);
                    myBudget.clientBudget.billList[i.ordinal()] = Double.parseDouble(tmpstr);
                    myBudget.savevalues(myBudget.clientBudget, myBudget.BudgetValues);
                }
            }
        });

        //Second button & pane 3
        JButton button2 = new JButton("Increment month");
        button2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        button2.setBackground(Color.WHITE);
        button2.setBounds(244, 373, 287, 90);

        button2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.getContentPane().remove(contentPane);
                frame.getContentPane().add(contentPane3);
                frame.getContentPane().validate();
            }
        });
        contentPane.add(button2);

        //int income will pull the income input from the set up screen, this is a test value
        int income = 0;
        JLabel salary2 = new JLabel("Your monthly income: $" + income);
        salary2.setFont(new Font("Tahoma", Font.BOLD, 13));
        salary2.setBounds(265, 60, 170, 47);
        contentPane3.add(salary2);

        cp3table = new JTable(6, 1);
        cp3table.setEnabled(false);
        cp3table.setShowVerticalLines(false);
        cp3table.setRowHeight(57);
        cp3table.setColumnSelectionAllowed(true);
        cp3table.setCellSelectionEnabled(false);
        cp3table.setBounds(266, 134, 78, 337);
        contentPane3.add(cp3table);

        cp3table2 = new JTable(6, 1);
        cp3table2.setEnabled(false);
        cp3table2.setShowVerticalLines(false);
        cp3table2.setRowSelectionAllowed(false);
        cp3table2.setRowHeight(57);
        cp3table2.setBounds(354, 134, 78, 337);

        contentPane3.add(cp3table2);

        cp3rent = new JLabel("Rent");
        cp3rent.setFont(new Font("Tahoma", Font.BOLD, 13));
        cp3rent.setBounds(178, 134, 78, 47);
        contentPane3.add(cp3rent);

        cp3bills = new JLabel("Bills");
        cp3bills.setFont(new Font("Tahoma", Font.BOLD, 13));
        cp3bills.setBounds(178, 192, 78, 47);
        contentPane3.add(cp3bills);

        cp3food = new JLabel("Food");
        cp3food.setFont(new Font("Tahoma", Font.BOLD, 13));
        cp3food.setBounds(178, 250, 78, 47);
        contentPane3.add(cp3food);

        cp3gas = new JLabel("Gas");
        cp3gas.setFont(new Font("Tahoma", Font.BOLD, 13));
        cp3gas.setBounds(178, 308, 78, 47);
        contentPane3.add(cp3gas);

        cp3other = new JLabel("Other");
        cp3other.setFont(new Font("Tahoma", Font.BOLD, 13));
        cp3other.setBounds(178, 366, 78, 47);
        contentPane3.add(cp3other);

        cp3savings = new JLabel("Savings");
        cp3savings.setFont(new Font("Tahoma", Font.BOLD, 13));
        cp3savings.setBounds(178, 424, 78, 47);
        contentPane3.add(cp3savings);

        currentMonth2 = new JLabel(possibilities.get((possibilities.indexOf(month) + 1) % possibilities.size()));
        currentMonth2.setFont(new Font("Tahoma", Font.PLAIN, 40));
        currentMonth2.setBounds(44, 11, 400, 75);
        contentPane3.add(currentMonth2);

        thisMonthtable = new JTable(6, 1);
        thisMonthtable.setShowVerticalLines(false);
        thisMonthtable.setRowHeight(57);
        thisMonthtable.setColumnSelectionAllowed(true);
        thisMonthtable.setCellSelectionEnabled(false);
        thisMonthtable.setBounds(452, 134, 78, 337);
        contentPane3.add(thisMonthtable);

        lastMonthtable2 = new JTable(6, 1);
        lastMonthtable2.setShowVerticalLines(false);
        lastMonthtable2.setRowSelectionAllowed(false);
        lastMonthtable2.setRowHeight(57);
        lastMonthtable2.setEnabled(false);
        lastMonthtable2.setBounds(540, 134, 78, 337);
        contentPane3.add(lastMonthtable2);

        suggesttable = new JTable(6, 1);
        cp3table2.setEnabled(true);
        suggesttable.setShowVerticalLines(false);
        suggesttable.setRowSelectionAllowed(false);
        suggesttable.setRowHeight(57);
        suggesttable.setEnabled(true);
        suggesttable.setBounds(648, 134, 78, 337);
        contentPane3.add(suggesttable);


        lastMonth = new JLabel("Last Month");
        lastMonth.setHorizontalAlignment(SwingConstants.LEFT);
        lastMonth.setFont(new Font("Tahoma", Font.BOLD, 12));
        lastMonth.setBounds(266, 109, 166, 14);
        contentPane3.add(lastMonth);

        newMonth = new JLabel("This Month");
        newMonth.setHorizontalAlignment(SwingConstants.LEFT);
        newMonth.setFont(new Font("Tahoma", Font.BOLD, 12));
        newMonth.setBounds(452, 109, 78, 14);
        contentPane3.add(newMonth);

        suggest = new JLabel("Suggested");
        suggest.setHorizontalAlignment(SwingConstants.LEFT);
        suggest.setFont(new Font("Tahoma", Font.BOLD, 12));
        suggest.setBounds(648, 70, 78, 36);
        contentPane3.add(suggest);

        adjustments = new JLabel("Adjustments");
        adjustments.setHorizontalAlignment(SwingConstants.LEFT);
        adjustments.setFont(new Font("Tahoma", Font.BOLD, 12));
        adjustments.setBounds(648, 97, 101, 36);
        contentPane3.add(adjustments);

        showperCurrent = new JButton("Show %");
        showperCurrent.setBounds(452, 482, 78, 36);
        contentPane3.add(showperCurrent);

        compareButton = new JButton("Auto-adjust");
        compareButton.setBounds(638, 482, 100, 36);
        contentPane3.add(compareButton);

        compareButton2 = new JButton("Manually adjust");
        compareButton2.setBounds(626, 520, 120, 36);
        contentPane3.add(compareButton2);

        save = new JButton("Save");
        save.setBounds(648, 11, 89, 23);
        contentPane3.add(save);
    }
}
