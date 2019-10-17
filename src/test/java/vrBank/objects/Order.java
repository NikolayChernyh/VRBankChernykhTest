package vrBank.objects;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import vrBank.objects.Cards.Card;

public class Order {

    public enum Status {
        OK("OK"),
        KO("KO"),
        REBILL_OK("REBILL_OK"),
        REBILL_KO("REBILL_KO");

        String status;
        boolean booleanStatus;

        Status() {
        }

        Status(String status, boolean booleanStatus) {
            this.status = status;
            this.booleanStatus = booleanStatus;
        }

        Status(String status) {
            this.status = status;
        }

        public String getStatus() {
            return this.status;
        }
    }

    public enum ExtendedStatus{
        PURCHASE("PURCHASE");

        String extendedStatus;

        ExtendedStatus(){
        }

        ExtendedStatus(String extendedStatus){
            this.extendedStatus = extendedStatus;
        }

        public String getExtendedStatus() { return this.extendedStatus;}
    }

    public enum ApiErrCode {
        CF_NOT_PRESENT("A100"),
        INVALID_PAN("A101"),
        INVALID_CARD_HOLDER("A102"),
        INVALID_IP("A103"),
        INVALI_CVV("A104"),
        INVALID_EXP_MONTH("A105"),
        INVALID_EXP_YEAR("A106"),
        EXPIRED_CARD("A107"),
        REQUIRED_FIELD_MISSING("A108"),
        INCORRECT_PRODUCT_ID("A109"),
        INCORRECT_OP_CODE("A111"),
        INCORRECT_PAYMENT_ID("A112"),
        REBILL_IS_PROHIBITTED("A113"),
        INCORRECT_TOKEN("A114"),
        INCORRECT_AMOUNT("A115"),
        INCORRECT_PAYMENT_CONDITION("A116"),
        SUSPECTED_FRAUD("A200"),
        INTERNAL_ERROR("A500"),
        CONFIGURATION_ERROR("A501");

        String errCode;

        ApiErrCode(String errCode) { this.errCode = errCode; }

        public String getErrCode() { return this.errCode; }
    }

    private Integer opCode = 0;
    private String amount = String.valueOf(getRandomAmount(200, 300));
    private String cf = UUID.randomUUID().toString();
    private String ip = "127.0.0.1";
    private String pan = Cards.generateCard(Cards.CardType.RandomCard);
    private String cardHolder = Card.defaultCard.getHolder();
    private String[] expData = currentMonthYear();
    private String expMonth = expData[0];
    private String expYear = expData[1];
    private String cvv = Card.defaultCard.getCvv();
    private String hidePan = Cards.hidePan(this.pan);
    private boolean userRebillApproved = false;
    private Integer userRebillFreq = 1;
    private String userRebillExp = expYear + expMonth;
    private String paymentId = "";

    public String getOrderInfo() {
        return "\nopCode: " + opCode + ";\n" +
                "amount: " + amount + ";\n" +
                "cf: " + cf + ";\n" +
                "pan: " + pan + ";\n" +
                "cardHolder: " + cardHolder + ";\n" +
                "expMonth: " + expMonth + ";\n" +
                "expYear: " + expYear + ";\n" +
                "cvv: " + cvv + ";\n" +
                "userRebillApproved: " + userRebillApproved + ";\n" +
                "userRebillExp: " + userRebillExp + ";\n" +
                "userRebillFreq: " + userRebillFreq +";" ;
    }

    public String getIp() {
        return ip;
    }

    public String getBin() {
        return Cards.getBin(this.pan);
    }

    public String getPan() {
        return pan;
    }

    public String getHidePan() {
        return hidePan;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getCvv() {
        return cvv;
    }

    public Boolean getUserRebillApproved() {
        return userRebillApproved;
    }

    public Integer getUserRebillFreq(){
        return userRebillFreq;
    }

    public String getUserRebillExp() { return userRebillExp; }

    public Integer getOpCode() {
        return opCode;
    }

    public String getCf() {
        return cf;
    }

    public String getAmount() {
        return this.amount;
    }

    public boolean isUserRebillApproved() {
        return userRebillApproved;
    }

    public String getPaymentId () { return paymentId; }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public void setHidePan(String hidePan) {
        this.hidePan = hidePan;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public void setCardHolder(String holder) {
        this.cardHolder = holder;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setOpCode(Integer opCode) {
        this.opCode = opCode;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setUserRebillApproved(boolean userRebillApproved) {
        this.userRebillApproved = userRebillApproved;
    }

    public void setUserRebillFreq(Integer userRebillFreq) {
        this.userRebillFreq = userRebillFreq;
    }

    public void setUserRebillExp(String userRebillExp) {
        this.userRebillExp = userRebillExp;
    }

    public void setAmount(int amount) {
        this.amount = String.valueOf(amount);
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public void setCard(String card) {
        this.pan = card;
    }

    public void setEmptyCf() {
        this.cf = "";
    }

    public void setEmptyAmount() {
        this.amount = "";
    }

    public void setEmptyPan() {
        this.pan = "";
    }

    public void setEmptyExpMonth() {
        this.expMonth = "";
    }

    public void setEmptyExpYear() {
        this.expYear = "";
    }

    public void setEmptyCvv() {
        this.cvv = "";
    }

    public void setEmptyHolder() {
        this.cardHolder = "";
    }

    public void setPaymentId(String paymentId) {this.paymentId = paymentId;}

    public void setCard(Cards.Card card) {
        this.pan = card.getPan();
        this.cvv = card.getCvv();
        this.expMonth = currentMonthYear()[0];
        this.expYear = currentMonthYear()[1].substring(2, 4);
    }

    public Order() {
    }

    public Order(int amount) {
        this.amount = String.valueOf(amount);
    }

    public Order(int amount, String pan) {
        this.amount = String.valueOf(amount);
        this.pan = pan;
        this.hidePan = Cards.hidePan(pan);
    }

    public Order(int amount, Cards.Card card) {
        this.pan = card.getPan();
        this.expMonth = card.getExpMonth();
        this.expYear = card.getExpYear();
        this.cardHolder = card.getHolder();
        this.hidePan = Cards.hidePan(card.getPan());
        this.amount = String.valueOf(amount);
    }

    public Order(int amount, String pan, boolean userRebillApproved){
        this.amount = String.valueOf(amount);
        this.pan = pan;
        this.hidePan = Cards.hidePan(pan);
        this.userRebillApproved = userRebillApproved;
    }

    public static Order getOrder() {
        return new Order();
    }

    public static Order getOrder(int amount, String pan) {
        return new Order(amount, pan);
    }

    public static Order getOrder(int amount, Cards.Card card) {
        return new Order(amount, card);
    }

    public static Order getOrder(int amount) {
        return new Order(amount);
    }

    public String[] currentMonthYear() {
        Calendar cal = Calendar.getInstance();
        String[] date = {new SimpleDateFormat("MM").format(cal.getTime()), new SimpleDateFormat("yyyy").format(cal.getTime())};
        Integer a = Integer.parseInt(date[1])+1;
        date[1]=String.valueOf(a);
        return date;
    }

    public int getRandomAmount(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static String serializeToJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static String stringToBase64(String string) {
        try {
            return Base64.getEncoder().encodeToString((string).getBytes("utf-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
