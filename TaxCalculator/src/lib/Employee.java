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
	private boolean gender; // true = Laki-laki, false = Perempuan

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;

	public Employee(PersonalData personalData, int yearJoined, int monthJoined, int dayJoined) {
		this.employeeId = personalData.employeeId;
		this.firstName = personalData.firstName;
		this.lastName = personalData.lastName;
		this.idNumber = personalData.idNumber;
		this.address = personalData.address;
		this.isForeigner = personalData.isForeigner;
		this.gender = personalData.gender;

		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;

		childNames = new LinkedList<>();
		childIdNumbers = new LinkedList<>();
	}

	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya.
	 * Grade 1: 3.000.000, Grade 2: 5.000.000, Grade 3: 7.000.000
	 * Jika pegawai adalah warga negara asing, gaji diperbesar 50%.
	 */
	public void setMonthlySalary(int grade) {
		monthlySalary = calculateSalary(grade);
	}

	private int calculateSalary(int grade) {
		int baseSalary;
		switch (grade) {
			case 1:
				baseSalary = 3000000;
				break;
			case 2:
				baseSalary = 5000000;
				break;
			case 3:
				baseSalary = 7000000;
				break;
			default:
				baseSalary = 0;
		}

		if (isForeigner) {
			return (int) (baseSalary * 1.5);
		}
		return baseSalary;
	}

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = spouseIdNumber; 
	}

	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}

	public int getAnnualIncomeTax() {
		LocalDate date = LocalDate.now();
		int monthWorkingInYear = (date.getYear() == yearJoined)
				? date.getMonthValue() - monthJoined
				: 12;

		return TaxFunction.calculateTax(
			monthlySalary,
			otherMonthlyIncome,
			monthWorkingInYear,
			annualDeductible,
			spouseIdNumber.equals(""),
			childIdNumbers.size()
		);
	}
}
