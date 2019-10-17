package vrBank.requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.codec.digest.DigestUtils;
import vrBank.objects.Order;
import vrBank.objects.Terminal;

import static net.serenitybdd.rest.SerenityRest.rest;

public class DirectRequests implements Requests {

    public static Response pay(Terminal key, Order order) {

        return rest().
                contentType(ContentType.URLENC).
                param("opcode", order.getOpCode()).
                param("product_id", key.getProductId()).
                param("amount", order.getAmount()).
                param("cf", order.getCf()).
                param("ip_address", order.getIp()).
                param("pan", order.getPan()).
                param("cardholder", order.getCardHolder()).
                param("exp_month", order.getExpMonth()).
                param("exp_year", order.getExpYear()).
                param("cvv", order.getCvv()).
                param("token", DigestUtils.md5Hex(String.valueOf(key.getMerchantId()) + String.valueOf(key.getProductId()) + order.getAmount() + order.getCf() + key.getSecretWord())).
                param("product_name", "TestProduct").
                param("user_rebill_approved", 1).
                when().log().all().
                post(BASE_URL);
    }

    public static Response payForRecurrentPay(Terminal key, Order order, Integer userRebillApproved) {

        return rest().
                contentType(ContentType.URLENC).
                param("opcode", order.getOpCode()).
                param("product_id", key.getProductId()).
                param("amount", order.getAmount()).
                param("cf", order.getCf()).
                param("ip_address", order.getIp()).
                param("pan", order.getPan()).
                param("cardholder", order.getCardHolder()).
                param("exp_month", order.getExpMonth()).
                param("exp_year", order.getExpYear()).
                param("cvv", order.getCvv()).
                param("token", DigestUtils.md5Hex(String.valueOf(key.getMerchantId()) + String.valueOf(key.getProductId()) + order.getAmount() + order.getCf() + key.getSecretWord())).
                param("product_name", "TestProduct").
                param("user_rebill_approved", userRebillApproved).
                when().log().all().
                post(BASE_URL);
    }

    public static Response recurrentPay(Terminal key, Order order) {

        return rest().
                contentType(ContentType.URLENC).
                param("opcode", order.getOpCode()).
                param("payment_id", order.getPaymentId()).
                param("amount", order.getAmount()).
                param("token", DigestUtils.md5Hex(String.valueOf(key.getMerchantId()) + String.valueOf(order.getPaymentId()) + order.getAmount() + key.getSecretWord())).
                when().log().all().
                post(BASE_URL);
    }
}
