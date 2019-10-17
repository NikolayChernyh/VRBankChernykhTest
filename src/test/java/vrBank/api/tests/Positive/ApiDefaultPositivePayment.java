package vrBank.api.tests.Positive;

import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import vrBank.objects.Cards;
import vrBank.objects.Order;
import vrBank.objects.Terminal;
import vrBank.steps.StepsWrapper;

@RunWith(SerenityRunner.class)
public class ApiDefaultPositivePayment extends StepsWrapper {

    @Test
    public void apiDefaultPositivePayment() {
        Terminal t = Terminal.getDefaultTerminal();
        Order order = Order.getOrder(5, Cards.Card.defaultCard);

        info.testDescription("Default positive payment test");
        info.expectedResult("Status: OK");
        info.terminalData(t);
        info.orderData(order);

        api.pay(t, order).expected().
                status(Order.Status.OK).
                extendedStatus(Order.ExtendedStatus.PURCHASE).
                duplicate(false);
    }
}
