import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.text.DecimalFormat;

public class PayRoll {
    public static void main (String args []){

        int hours = getNumberOfHours("Please enter Number of Hours from this week");
        int dep = getNumberOfDependents("Please enter Number of Dependents as of this week");
        double pay = getRateOfPay("Please enter Rate of Pay as of this week");
        double gross = calculateGrossPay(hours, pay);
        double sstax = calculateSocialSecurityTax(gross);
		double fedtax = calculateFederalIncomeTax(gross);
		double statetax = calculateStateIncomeTax(gross);
        double health = getHealthCost(dep);
        double union = 10;
        double net = calculateNetPay(gross, sstax, fedtax, statetax, health, union);
        output(gross, sstax, fedtax, statetax, health, union, net);

        }

    public static int getNumberOfHours(String prompt) {
        int hours;
        do {
            hours = IO.getInt(prompt);
            if (hours < 0)
                JOptionPane.showMessageDialog(null, "Please enter a positive integer");
        }
        while (hours < 0);
        return hours;
    }

    public static int getNumberOfDependents(String prompt){
        int dependents;
        do {
            dependents = IO.getInt(prompt);
            if (dependents < 0)
                JOptionPane.showMessageDialog(null, "Please enter a positive integer");
        }
        while (dependents < 0);
        return dependents;
    }

    public static double getRateOfPay(String prompt){
        double rateOfPay;
        do {
            rateOfPay = IO.getDouble(prompt);
            if (rateOfPay < 0)
                JOptionPane.showMessageDialog(null, "Please enter a positive double");
        }
        while (rateOfPay < 0);
        return rateOfPay;
    }

    public static double calculateGrossPay(int numberOfHours, double payRate){
        double GrossPay;
        if (numberOfHours <= 40)
            GrossPay = numberOfHours * payRate;
        else
            GrossPay = (numberOfHours - 40) * 1.5 * payRate;
        return GrossPay;


    }

    public static double calculateSocialSecurityTax(double grossPay){
        double SocialSecurityTax = grossPay * 0.06;
        return SocialSecurityTax;
    }

    public static double calculateFederalIncomeTax(double grossPay){
        double FederalIncomeTax = grossPay * 0.14;
        return FederalIncomeTax;
    }

    public static double calculateStateIncomeTax(double grossPay){
        double StateIncomeTax = grossPay * 0.05;
        return StateIncomeTax;
    }

    public static double getHealthCost(int numberOfDependents){
        if(numberOfDependents >= 3)
            return 35;
        else
            return 0;
    }

    public static double calculateNetPay(double gross, double sstax, double fedtax, double statetax, double healthdues, double uniondues){
        double netPay = gross - sstax - fedtax - statetax - healthdues - uniondues;
        return netPay;
    }

    public static void output(double gross, double sstax, double fedtax, double statetax, double healthdues, double uniondues, double net){
        DecimalFormat twoDigits = new DecimalFormat("$0.00");
        System.out.println("Gross Pay: " + twoDigits.format(gross));
        System.out.println("Social Security Tax: " + twoDigits.format(sstax));
        System.out.println("Federal Income Tax: " + twoDigits.format(fedtax));
        System.out.println("State Income Tax: " + twoDigits.format(statetax));
        System.out.println("Health Dues: " + twoDigits.format((double)(healthdues)));
        System.out.println("Union Dues: " + twoDigits.format((double)(uniondues)));
        System.out.println("Net Pay: " + twoDigits.format(net));
    }
}
