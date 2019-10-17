package vrBank.api.tests.Positive;

import vrBank.objects.Cards;
import vrBank.objects.Order;
import vrBank.objects.Terminal;
import vrBank.steps.StepsWrapper;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ApiDefaultPositiveRecurrentPayment extends StepsWrapper {

    @Test
    public void apiDefaultPositiveRecurrentPayment() {
        Terminal t = Terminal.getDefaultTerminal();
        Order order = Order.getOrder(5, Cards.Card.defaultCard);

        info.testDescription("Default positive recurrent payment");
        info.expectedResult("Status: REBILL_OK");
        info.terminalData(t);
        info.orderData(order);

        api.payForRecurrentPay(t, order, 1).expected().
                status(Order.Status.OK).
                extendedStatus(Order.ExtendedStatus.PURCHASE).
                duplicate(false).
                getPaymentId();

        order.setOpCode(6);

        api.recurrentPay(t, order).expected().
                status(Order.Status.REBILL_OK).
                extendedStatus(Order.ExtendedStatus.PURCHASE).
                duplicate(false);
    }
}
