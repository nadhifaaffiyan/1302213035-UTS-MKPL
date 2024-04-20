package lib;

public class TaxFunction {

	public static class TaxCalculationInfo {
		private int monthlySalary;
		private int otherMonthlyIncome;
		private int numberOfMonthWorking;
		private int deductible;
		private boolean isMarried;
		private int numberOfChildren;

		// Constructor
		public TaxCalculationInfo(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible,
				boolean isMarried, int numberOfChildren) {
			this.monthlySalary = monthlySalary;
			this.otherMonthlyIncome = otherMonthlyIncome;
			this.numberOfMonthWorking = numberOfMonthWorking;
			this.deductible = deductible;
			this.isMarried = isMarried;
			this.numberOfChildren = numberOfChildren;
		}

		// Getter methods
		public int getMonthlySalary() {
			return monthlySalary;
		}

		public int getOtherMonthlyIncome() {
			return otherMonthlyIncome;
		}

		public int getNumberOfMonthWorking() {
			return numberOfMonthWorking;
		}

		public int getDeductible() {
			return deductible;
		}

		public boolean isMarried() {
			return isMarried;
		}

		public int getNumberOfChildren() {
			return numberOfChildren;
		}
	}

	public static final int MAX_WORKING_MONTHS_PER_YEAR = 12;
	public static final int MAX_CHILDREN_FOR_TAX = 3;
	public static final int STANDARD_DEDUCTIBLE = 54000000;
	public static final int PER_CHILD_DEDUCTIBLE = 1500000;

	public static int calculateTax(TaxCalculationInfo taxInfo) {
		int tax = 0;
		int monthlySalary = taxInfo.getMonthlySalary();
		int otherMonthlyIncome = taxInfo.getOtherMonthlyIncome();
		int numberOfMonthWorking = taxInfo.getNumberOfMonthWorking();
		int deductible = taxInfo.getDeductible();
		boolean isMarried = taxInfo.isMarried();
		int numberOfChildren = Math.min(taxInfo.getNumberOfChildren(), MAX_CHILDREN_FOR_TAX);

		// Calculation logic
		if (numberOfMonthWorking > MAX_WORKING_MONTHS_PER_YEAR) {
			throw new IllegalArgumentException("Lebih dari 12 bulan bekerja dalam setahun");
		}

		if (numberOfChildren > MAX_CHILDREN_FOR_TAX) {
			numberOfChildren = MAX_CHILDREN_FOR_TAX;
		}

		if (isMarried) {
			tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible
					- (STANDARD_DEDUCTIBLE + (numberOfChildren * PER_CHILD_DEDUCTIBLE))));
		} else {
			tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible
					- STANDARD_DEDUCTIBLE));
		}

		return Math.max(0, tax);
	}
}