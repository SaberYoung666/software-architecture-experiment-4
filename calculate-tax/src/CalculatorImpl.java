import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace="Calculator")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CalculatorImpl implements Calculator {
	@Override
	public double calculate(double income) {
		double taxableIncome = income - 5000; // 减去起征点
		double tax = 0;

		if (taxableIncome <= 0) {
			return 0; // 不需要缴纳个人所得税
		} else if (taxableIncome <= 3000) {
			tax = taxableIncome * 0.03;
		} else if (taxableIncome <= 12000) {
			tax = 3000 * 0.03 + (taxableIncome - 3000) * 0.1;
		} else if (taxableIncome <= 25000) {
			tax = 3000 * 0.03 + 9000 * 0.1 + (taxableIncome - 12000) * 0.2;
		} else if (taxableIncome <= 35000) {
			tax = 3000 * 0.03 + 9000 * 0.1 + 13000 * 0.2 + (taxableIncome - 25000) * 0.25;
		} else if (taxableIncome <= 55000) {
			tax = 3000 * 0.03 + 9000 * 0.1 + 13000 * 0.2 + 10000 * 0.25 + (taxableIncome - 35000) * 0.3;
		} else if (taxableIncome <= 80000) {
			tax = 3000 * 0.03 + 9000 * 0.1 + 13000 * 0.2 + 10000 * 0.25 + 20000 * 0.3 + (taxableIncome - 55000) * 0.35;
		} else {
			tax = 3000 * 0.03 + 9000 * 0.1 + 13000 * 0.2 + 10000 * 0.25 + 20000 * 0.3 + 25000 * 0.35 + (taxableIncome - 80000) * 0.45;
		}

		return tax;
	}
}
