package vrBank.api.tests.Negative;

import vrBank.objects.Cards;
import vrBank.objects.Order;
import vrBank.objects.Terminal;
import vrBank.steps.StepsWrapper;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ApiDefaultPayWithEmptyCf extends StepsWrapper {

    @Test
    public void apiDefaultPayWithEmptyCf() {
        Terminal t = Terminal.getDefaultTerminal();
        Order order = Order.getOrder(5, Cards.Card.defaultCard);

        info.testDescription("Check Status KO for payment with empty cf");
        info.expectedResult("Status: KO; Error: A100 ");
        info.terminalData(t);
        info.orderData(order);

        order.setCf("");

        api.pay(t, order).expected().
                status(Order.Status.KO).
                err(Order.ApiErrCode.CF_NOT_PRESENT);
    }
}
