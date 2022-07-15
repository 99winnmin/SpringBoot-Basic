package JUnit;

import org.yaml.snakeyaml.error.Mark;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello JUnit");
        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);
        // 이런식으로 main에서 작성해서 테스트하지 않는다!

        System.out.println(calculator.sum(10,10));
    }
}
