package vrBank.api.tests.Negative;

import vrBank.objects.Cards;
import vrBank.objects.Order;
import vrBank.objects.Terminal;
import vrBank.steps.StepsWrapper;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ApiDefaultPayWithInvalidPan extends StepsWrapper {

    @Test
    public void apiDefaultPayWithInvalidPan() {
        Terminal t = Terminal.getDefaultTerminal();
        Order order = Order.getOrder(5, Cards.Card.defaultCard);

        info.testDescription("Check Status KO after pay with invalid PAN");
        info.expectedResult("Status: KO; Error: 101");
        info.terminalData(t);
        info.orderData(order);

        order.setPan("400000000000002");

        api.pay(t, order).expected().
                status(Order.Status.KO).
                err(Order.ApiErrCode.INVALID_PAN);
    }
}
