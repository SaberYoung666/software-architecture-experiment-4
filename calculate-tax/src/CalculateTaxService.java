import javax.xml.ws.Endpoint;

public class CalculateTaxService {
	public static void main(String[] args) {
		Endpoint endpoint = Endpoint.publish("http://localhost:8080/calculator", new CalculatorImpl());
		System.out.println("WebService接口服务已经启动......");
	}
}
