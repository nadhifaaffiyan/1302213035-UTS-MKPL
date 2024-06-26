package lib;

import java.math.BigDecimal;

public class TaxFunction {

	public static class TaxCalculationInfo {
		private int monthlySalary;
		private int otherMonthlyIncome;
		private int numberOfMonthsWorking;
		private int annualDeductible;
		private boolean isMarried;
		private int numberOfChildren;

		public TaxCalculationInfo(int monthlySalary, int otherMonthlyIncome, int numberOfMonthsWorking,
				int annualDeductible, boolean isMarried, int numberOfChildren) {
			this.monthlySalary = monthlySalary;
			this.otherMonthlyIncome = otherMonthlyIncome;
			this.numberOfMonthsWorking = numberOfMonthsWorking;
			this.annualDeductible = annualDeductible;
			this.isMarried = isMarried;
			this.numberOfChildren = numberOfChildren;
		}

		public int getMonthlySalary() {
			return monthlySalary;
		}

		public int getOtherMonthlyIncome() {
			return otherMonthlyIncome;
		}

		public int getNumberOfMonthsWorking() {
			return numberOfMonthsWorking;
		}

		public int getAnnualDeductible() {
			return annualDeductible;
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
	public static final BigDecimal TAX_RATE = new BigDecimal("0.05");
	public static final int STANDARD_DEDUCTIBLE = 54000000;
	public static final int PER_CHILD_DEDUCTIBLE = 1500000;

	public static int calculateTax(TaxCalculationInfo taxInfo) {
		int monthlySalary = taxInfo.getMonthlySalary();
		int otherMonthlyIncome = taxInfo.getOtherMonthlyIncome();
		int numberOfMonthsWorking = taxInfo.getNumberOfMonthsWorking();
		int annualDeductible = taxInfo.getAnnualDeductible();
		boolean isMarried = taxInfo.isMarried();
		int numberOfChildren = Math.min(taxInfo.getNumberOfChildren(), MAX_CHILDREN_FOR_TAX);

		// Validate number of months working
		if (numberOfMonthsWorking > MAX_WORKING_MONTHS_PER_YEAR) {
			throw new IllegalArgumentException("Lebih dari 12 bulan bekerja dalam setahun");
		}

		// Calculate deductible for children
		int childrenDeductible = numberOfChildren * PER_CHILD_DEDUCTIBLE;

		// Calculate total income
		int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthsWorking;
		int totalDeductible = annualDeductible + STANDARD_DEDUCTIBLE + childrenDeductible;

		// Calculate taxable income
		int taxableIncome = Math.max(0, totalIncome - totalDeductible);

		// Calculate tax amount
		BigDecimal taxAmount = TAX_RATE.multiply(new BigDecimal(taxableIncome)).setScale(0, BigDecimal.ROUND_HALF_UP);

		return taxAmount.intValue();
	}
}
