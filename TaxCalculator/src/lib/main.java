package lib;

public class Main {
	public static void main(String[] args) {
		// Membuat objek PersonalData
		PersonalData personalData = new PersonalData(
			"EMP001",
			"Budi",
			"Santoso",
			"1234567890",
			"Jl. Merdeka No.1",
			false, // WNI
			true   // Laki-laki
		);

		// Membuat objek Employee
		Employee employee = new Employee(personalData, 2023, 5, 15);
		employee.setMonthlySalary(2); // Grade 2 = 5.000.000
		employee.setAdditionalIncome(1000000); // Pendapatan tambahan bulanan
		employee.setAnnualDeductible(2000000); // Potongan tahunan

		int pajak = employee.getAnnualIncomeTax();
		System.out.println("Pajak tahunan: Rp" + pajak);
	}
}
