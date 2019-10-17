package vrBank.responses.direct;

import io.restassured.response.Response;
import vrBank.objects.Order;

import static org.hamcrest.Matchers.*;

public class PayResponse {

    private final Response resp;
    private final Order order;

    public PayResponse(Response resp, Order order) {
        this.resp = resp;
        this.order = order;
    }

    public static PayResponse get(Response resp, Order order) {
        return new PayResponse(resp, order);
    }

    public PayResponse expected() {
        return this;
    }

    public PayResponse status(Order.Status status) {
        resp.then().body("response[0].status", equalTo(status.getStatus()));
        return this;
    }

    public PayResponse extendedStatus(Order.ExtendedStatus extendedStatus){
        resp.then().body("response[0].extended_status", equalTo(extendedStatus.getExtendedStatus()));
        return this;
    }

    public PayResponse duplicate(boolean flag){
        resp.then().body("response[0].duplicate", equalTo(String.valueOf(flag)));
        return this;
    }

    public PayResponse getPaymentId(){
        if(resp.then().extract().path("response[0].payment_id")!=null){
            order.setPaymentId(resp.then().body("response[0].payment_id", is(not(empty()))).extract().path("response[0].payment_id"));
        }
        return this;
    }

    public PayResponse err(Order.ApiErrCode errCode){
        resp.then().body("response[0].response_code", equalTo(errCode.getErrCode()));
        return this;
    }
}
