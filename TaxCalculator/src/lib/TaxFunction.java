package lib;

public class TaxFunction {

	private static final int BASIC_NTKP = 54000000;
	private static final int MARRIED_NTKP = 4500000;
	private static final int CHILD_NTKP = 1500000;
	private static final int MAX_CHILDREN = 3;

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}

		int netIncome = calculateNetIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking, deductible);
		int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);
		int taxableIncome = netIncome - nonTaxableIncome;

		int tax = (int) Math.round(0.05 * taxableIncome);
		return Math.max(tax, 0);
	}

	private static int calculateNetIncome(int monthlySalary, int otherMonthlyIncome, int months, int deductible) {
		return (monthlySalary + otherMonthlyIncome) * months - deductible;
	}

	private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
		int total = BASIC_NTKP;
		if (isMarried) {
			total += MARRIED_NTKP;
		}
		total += CHILD_NTKP * Math.min(numberOfChildren, MAX_CHILDREN);
		return total;
	}
}
