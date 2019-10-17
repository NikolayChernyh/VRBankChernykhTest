package vrBank.api.tests.Negative;

import vrBank.objects.Cards;
import vrBank.objects.Order;
import vrBank.objects.Terminal;
import vrBank.steps.StepsWrapper;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ApiDefaultPayWithInvalidCardHolder extends StepsWrapper {

    @Test
    public void apiDefaultPayWithInvalidCardHolder() {
        Terminal t = Terminal.getDefaultTerminal();
        Order order = Order.getOrder(5, Cards.Card.defaultCard);

        info.testDescription("Check Status KO for payment with invalid data in CardHolder");
        info.expectedResult("Status: KO; Error: A102");
        info.terminalData(t);
        info.orderData(order);

        order.setCardHolder("123!#@");

        api.pay(t, order).expected().
                status(Order.Status.KO).
                err(Order.ApiErrCode.INVALID_CARD_HOLDER);
    }
}
