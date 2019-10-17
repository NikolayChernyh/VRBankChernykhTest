package vrBank.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import vrBank.objects.Order;
import vrBank.objects.Terminal;

public class InfoSteps extends ScenarioSteps {
    //Здесь храним всё что нужно для вывода дополнительных данных в отчёт
    @Step
    public void testInfo(String testInfo){
        //do nothing
    }

    @Step
    public void testDescription(String testDescription){
        //do nothing
    }

    @Step
    public void expectedResult(String expectedResult){
        //do nothing
    }

    @Step
    public void orderData(String s){
        //do nothing
    }

    @Step
    public void terminalData(String s){
        //do nothing
    }

    public void orderData (Order order){
        orderData(order.getOrderInfo());
    }

    public void terminalData(Terminal t){
        terminalData(t.getTerminalData());
    }
}
