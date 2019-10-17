package vrBank.objects;

import java.util.*;

public class Cards {
	public static Random random = new Random();


    public static enum CardType {
        RandomCard,
        RandomThreeDsCard,
        RandomWrongPanCard;
    }

    public enum Card {
        defaultCard("4000000000000002", "123", "12","2021", "Card Holder");

        String pan;
        String cvv;
        String expMonth;
        String expYear;
        String holder;

        Card(String pan) {
            this.pan = pan;
        }

        Card(String pan, String cvv) {
            this.pan = pan;
            this.cvv = cvv;
        }

        Card(String pan, String cvv, String holder) {
            this.pan = pan;
            this.cvv = cvv;
            this.holder = holder;
        }

        Card(String pan, String cvv, String expMonth, String expYear) {
            this.pan = pan;
            this.cvv = cvv;
            this.expMonth = expMonth;
            this.expYear = expYear;
        }

        Card(String pan, String cvv, String expMonth, String expYear, String holder) {
            this.pan = pan;
            this.cvv = cvv;
            this.expMonth = expMonth;
            this.expYear = expYear;
            this.holder = holder;
        }

        public String getPan() {
            return this.pan;
        }

        public String getCvv() {
            return this.cvv;
        }

        public String getExpMonth() {
            return this.expMonth;
        }

        public String getExpYear() {
            return this.expYear;
        }

        public String getHolder() {
            return this.holder;
        }
    }

    public static String hidePan(String pan) {
        return pan.substring(0, 6) + pan.substring(6, 12).replaceAll(".", "x") + pan.substring(12);
    }

    public static String getBin(String pan) {
        return pan.substring(0, 6);
    }

    public static String generateCard(CardType type) {

        String body = "";

        switch (type) {
            case RandomCard:
                body = "111110002";
                break;
            case RandomThreeDsCard:
                body = "111110001";
                break;
            case RandomWrongPanCard:
                body = "11111000";
                break;
        }

        
        String bin = String.valueOf(random.nextInt(99999 - 0) + 0);
        int j = 5 - bin.length();
        if (j != 5) {
            StringBuilder binBody = new StringBuilder();
            for (int i = 0; i < j; i++) {
                binBody.append("0");
            }
            bin += binBody.toString();
        }
        String number;
        if(type.equals(CardType.RandomWrongPanCard)) {
            number = "1" + bin + body;
        }
        else {
            number = "4" + bin + body;
        }
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {
            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }
        int mod = sum % 10;
        return ((mod == 0) ? number + 0 : number + (10 - mod));
    }


    public static String generateCirrusCard(CardType type) {
        String body = "";

        switch (type) {
            case RandomCard:
                body = "111110002";
                break;
            case RandomThreeDsCard:
                body = "111110001";
                break;
            case RandomWrongPanCard:
                body = "111110000";
                break;
        }

        String number;
        number = "465041" + body;
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {
            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }
        int mod = sum % 10;
        return ((mod == 0) ? number + 0 : number + (10 - mod));
    }

    public static String generateMasterCard(CardType type) {
        String body = "";

        switch (type) {
            case RandomCard:
                body = "111110002";
                break;
            case RandomThreeDsCard:
                body = "111110001";
                break;
            case RandomWrongPanCard:
                body = "111110000";
                break;
        }

        String number;
        number = "553691" + body;
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {
            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }
        int mod = sum % 10;
        return ((mod == 0) ? number + 0 : number + (10 - mod));
    }
}
