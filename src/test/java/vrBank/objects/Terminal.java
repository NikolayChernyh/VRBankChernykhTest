package vrBank.objects;

public class Terminal {

    public static enum ReturnObject {
        True,
        False
    }

    private int MerchantId;
    private int ProductId;
    private String SecretWord;

    public int getMerchantId() { return MerchantId; }

    public int getProductId() { return ProductId; }

    public String getSecretWord() { return SecretWord; }

    public String getTerminalData(){
        return "\nMerchantId: " + MerchantId + ";\n" +
                "ProductId: " + ProductId + ";\n" +
                "SecretWord: " + SecretWord + ";";
    }

    public static class Builder {

        private int MerchantId = -1;
        private int ProductId = -1;
        private String SecretWord = "";

        public Builder() {
        }

        public Builder merchantId(int val) {
            MerchantId = val;
            return this;
        }

        public Builder productId(int val){
            ProductId = val;
            return this;
        }

        public Builder secretWord(String val){
            SecretWord = val;
            return this;
        }

        public Builder with() {
            return this;
        }

        public Builder add() {
            return this;
        }

        public Terminal build() {
            return new Terminal(this, ReturnObject.True);
        }

        public Terminal build(ReturnObject object) {
            return new Terminal(this, object);
        }
    }

    public static Terminal getDefaultTerminal() {
        return new Builder().merchantId(702).
                with().
                productId(4870).
                secretWord("sw").build();
    }

    private Terminal(Builder builder, ReturnObject object) {
        MerchantId = builder.MerchantId;
        ProductId = builder.ProductId;
        SecretWord = builder.SecretWord;
    }
}
