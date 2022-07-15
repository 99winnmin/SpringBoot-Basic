package JUnitTest;

import JUnit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

// Mockito 처리를 한다 : 특정한 객체에서 원하는 메소드가 호출이 되었을 때 원하는 결과값을 리턴한다??
@ExtendWith(MockitoExtension.class)
public class DollarCalculatorTest {

    @Mock // MarketApi 객체를 mock 처리한 것임
    public MarketApi marketApi;

    @BeforeEach
    public void init(){ // marketApi.connect()가 실행될 때 메서드의 리턴값이 아니라 내가 원하는 값을 리턴함
        Mockito.lenient().when(marketApi.connect()).thenReturn(3000);
    }

    @Test
    public void testHello(){
        System.out.println("hello");
    }

    @Test
    public void dollarTest(){
        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);

        System.out.println(calculator.sum(10,10));

        Assertions.assertEquals(22000, calculator.sum(10,10));
        Assertions.assertEquals(0,calculator.minus(10,10));
    }

    @Test
    public void mockTest(){
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);

        System.out.println(calculator.sum(10,10));

        Assertions.assertEquals(60000, calculator.sum(10,10));
        Assertions.assertEquals(0,calculator.minus(10,10));
    }
}
