package vrBank.api.tests.Positive;

import vrBank.objects.Cards;
import vrBank.objects.Order;
import vrBank.objects.Terminal;
import vrBank.steps.StepsWrapper;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

@RunWith(SerenityParameterizedRunner.class)
public class ApiDefaultPayment_CheckAmountLimit extends StepsWrapper {
    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {-50, Order.Status.KO},
                {4, Order.Status.KO},
                {5, Order.Status.OK},
                {6, Order.Status.OK},
                {250, Order.Status.OK},
                {499, Order.Status.OK},
                {500, Order.Status.OK},
                {501, Order.Status.KO},
                {550, Order.Status.KO}
        });
    }

    private final Integer amount;
    private final Order.Status status;

    public ApiDefaultPayment_CheckAmountLimit(Integer amount, Order.Status status){
        this.amount = amount;
        this.status = status;
    }

    @Qualifier
    public String description() {
        return "For Amount : " + amount;
    }

    @Test
    public void apiDefaultPayment_CheckAmountLimit() {
        Terminal t = Terminal.getDefaultTerminal();
        Order order = Order.getOrder(amount, Cards.Card.defaultCard);

        info.testDescription("Check amount field values");
        info.expectedResult("Status: " + status);
        info.terminalData(t);
        info.orderData(order);

        api.pay(t, order).expected().
                status(status);
    }
}
