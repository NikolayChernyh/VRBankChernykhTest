package vrBank.api.tests.Negative;

import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import vrBank.objects.Cards;
import vrBank.objects.Order;
import vrBank.objects.Terminal;
import vrBank.steps.StepsWrapper;

@RunWith(SerenityRunner.class)
public class ApiDefaultNegativeRecurrentPayment extends StepsWrapper {

    @Test
    public void apiDefaultPositivePayment() {
        Terminal t = Terminal.getDefaultTerminal();
        Order order = Order.getOrder(5, Cards.Card.defaultCard);

        info.testDescription("Check REBILL_KO for payment after userRebillApproved = 0");
        info.expectedResult("REBILL_KO for recurrentPayment (opcode=6)");
        info.terminalData(t);
        info.orderData(order);

        api.payForRecurrentPay(t, order, 0).expected().
                status(Order.Status.OK).
                extendedStatus(Order.ExtendedStatus.PURCHASE).
                duplicate(false).
                getPaymentId();

        order.setOpCode(6);

        api.recurrentPay(t, order).expected().
                status(Order.Status.REBILL_KO);
    }
}
