package vrBank.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import vrBank.objects.Order;
import vrBank.objects.Terminal;
import vrBank.requests.DirectRequests;
import vrBank.responses.direct.PayResponse;

public class DirectSteps extends ScenarioSteps {

    @Step("Pay")
    public PayResponse pay(Terminal t, Order order) {
        return PayResponse.get(DirectRequests.pay(t, order), order);
    }

    @Step("Pay")
    public PayResponse payForRecurrentPay(Terminal t, Order order, Integer userRebillApproved) {
        return PayResponse.get(DirectRequests.payForRecurrentPay(t, order, userRebillApproved), order);
    }

    @Step("RecurrentPay")
    public PayResponse recurrentPay(Terminal t, Order order) {
        return PayResponse.get(DirectRequests.recurrentPay(t, order), order);
    }
}
