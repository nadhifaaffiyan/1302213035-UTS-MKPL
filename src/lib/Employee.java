package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;

	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private boolean isForeigner;
	private boolean isMale; // true = Laki-laki, false = Perempuan

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	private String spouseName;
	private List<String> childNames;

	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
			int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean isMale) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.isMale = isMale;

		this.childNames = new LinkedList<>();
	}

	// Refactored setMonthlySalary method using enum
	public void setMonthlySalary(Grade grade) {
		if (grade != null) {
			monthlySalary = isForeigner ? (int) (grade.getBaseSalary() * 1.5) : grade.getBaseSalary();
		} else {
			throw new IllegalArgumentException("Invalid grade");
		}
	}

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setOtherMonthlyIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	public void setSpouse(String spouseName) {
		this.spouseName = spouseName;
	}

	public void addChild(String childName) {
		this.childNames.add(childName);
	}

	// Getters and other methods...

	// Inner enum to represent employee grade
	public enum Grade {
		GRADE_1(3000000),
		GRADE_2(5000000),
		GRADE_3(7000000);

		private final int baseSalary;

		Grade(int baseSalary) {
			this.baseSalary = baseSalary;
		}

		public int getBaseSalary() {
			return baseSalary;
		}
	}
}
