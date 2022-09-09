// Java Program to Convert English
// Text to Morse Code and Vice Versa

import java.util.*;

interface Encryption {
    void MorseToEnglish(String[] code, String morseCode) throws InterruptedException;

    void EnglishToMorse(String englishLang) throws InterruptedException;

    char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', ' '};
    // Morse code by indexing
    String[] morseLetters = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
            "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", " / "};

    String base64Decoder(char encoded[], int len_str);

    void base64Encoder(String encode);

    void StringToHexa(String str);
}

class defaultEncrypt implements Encryption {
    @Override
    public void MorseToEnglish(String[] code, String morseCode) throws InterruptedException {
        // morse code to English Hashmap
        Map<String, Character> morseToEnglish = new HashMap<>();
        // Map value allocation
        for (int i = 0; i < morseLetters.length; i++) {
            morseToEnglish.put(morseLetters[i], (char) ('a' + i));
        }
        // Split morse code in array of string
        String[] array = morseCode.split(" ");
        String t = "Morse code " + morseCode + " to English is ";

        for (int i = 0; i < t.length(); i++) {
            Thread.sleep(100);
            System.out.print("\u001B[33m" + t.charAt(i));
        }
        // System.out.print(t);
        // Morse code to English
        for (String s : array) {
            System.out.print("\u001B[35m" + morseToEnglish.get(s) + "" + "\u001b[0m");
        }

    }

    // @Override
    public void EnglishToMorse(String englishLang) throws InterruptedException {
        englishLang = englishLang.toLowerCase();
        String newText = " ";
        for (int i = 0; i < englishLang.length(); i++) {
            for (short j = 0; j < morseLetters.length; j++) {
                if (englishLang.charAt(i) == letters[j]) {
                    newText += morseLetters[j];
                    newText += " ";
                    break;
                }
            }
        }

        // System.out.println(englishLang);
        for (int i = 0; i < newText.length(); i++) {
            Thread.sleep(100);
            System.out.print("\u001b[33m" + newText.charAt(i) + "\u001b[0m");
        }

    }

    public String base64Decoder(char[] encoded, int len_str) {
        char[] decoded_String;

        decoded_String = new char[100];

        int i, j, k = 0;

        // stores the bitstream.
        int num = 0;

        // count_bits stores current
        // number of bits in num.
        int count_bits = 0;

        // selects 4 characters from
        // encoded String at a time.
        // find the position of each encoded
        // character in char_set and stores in num.
        for (i = 0; i < len_str; i += 4) {
            num = 0;
            count_bits = 0;
            for (j = 0; j < 4; j++) {

                // make space for 6 bits.
                if (encoded[i + j] != '=') {
                    num = num << 6;
                    count_bits += 6;
                }

                /*
                 * Finding the position of each encoded
                 * character in char_set
                 * and storing in "num", use OR
                 * '|' operator to store bits.
                 */

                // encoded[i + j] = 'E', 'E' - 'A' = 5
                // 'E' has 5th position in char_set.
                if (encoded[i + j] >= 'A' && encoded[i + j] <= 'Z')
                    num = num | (encoded[i + j] - 'A');

                    // encoded[i + j] = 'e', 'e' - 'a' = 5,
                    // 5 + 26 = 31, 'e' has 31st position in char_set.
                else if (encoded[i + j] >= 'a' && encoded[i + j] <= 'z')
                    num = num | (encoded[i + j] - 'a' + 26);

                    // encoded[i + j] = '8', '8' - '0' = 8
                    // 8 + 52 = 60, '8' has 60th position in char_set.
                else if (encoded[i + j] >= '0' && encoded[i + j] <= '9')
                    num = num | (encoded[i + j] - '0' + 52);

                    // '+' occurs in 62nd position in char_set.
                else if (encoded[i + j] == '+')
                    num = num | 62;

                    // '/' occurs in 63rd position in char_set.
                else if (encoded[i + j] == '/')
                    num = num | 63;

                    // ( str[i + j] == '=' ) remove 2 bits
                    // to delete appended bits during encoding.
                else {
                    num = num >> 2;
                    count_bits -= 2;
                }
            }

            while (count_bits != 0) {
                count_bits -= 8;

                // 255 in binary is 11111111
                decoded_String[k++] = (char) ((num >> count_bits) & 255);
            }
        }
        return String.valueOf(decoded_String);
    }

    public void base64Encoder(String sample) {
        // print actual String

        System.out.println("\u001b[32mSample String:\n"
                + sample + "\u001b[0m");

        // Encode into Base64 format
        String BasicBase64format = Base64.getEncoder()
                .encodeToString(sample.getBytes());

        // print encoded String
        System.out.println("\u001b[33mEncoded String:\n"
                + BasicBase64format + "\u001b[0m");
    }

    public void StringToHexa(String str) {
        StringBuffer sb = new StringBuffer();
        //Converting string to character array
        char ch[] = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            String hexString = Integer.toHexString(ch[i]);
            sb.append(hexString);
        }
        String result = sb.toString();
        System.out.println("\u001b[33m" + result + "\u001b[0m");
    }

}

class Encryptionfactory {
    private static Encryption instance;

    public static Encryption CreateInstance() {
        if (instance == null) {
            return new defaultEncrypt();
        }
        return instance;
    }

}

public class EncryptionMessage {
    public static void main(String... args) throws InterruptedException {
        Encryption obj = Encryptionfactory.CreateInstance();
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("\u001b[36mDo you want to encrypt a text or decrypt\u001b[0m");
            Thread.sleep(1000);
            System.out.println("\n" + "\u001b[33m1-Press 1 to encode text to Morse code from English"
                    + "\n" + "2-Press 2 to dencode text to English from Morse Code\u001b[0m" + "\n" + "\u001b[35m3-Base64 decoder" + "\n"
                    + "4-Base64 encoder\u001b[0m");
            String choose = input.next();
            if (choose.equals("1")) {
                System.out.println("\u001B[34mWrite a text to encrypt it: \u001b[0m");
                String text = "";
                text = input.next();
                text += input.nextLine();
                text.toLowerCase();
                obj.EnglishToMorse(text);
                System.out.println();
            } else if (choose.equals("2")) {
                System.out.println("\u001B[31mWrite the text to be decrypted: \u001b[0m");
                String morse = "";
                morse = input.next();
                morse += input.nextLine();
                // System.out.println(morse);
                morse.toLowerCase();
                obj.MorseToEnglish(obj.morseLetters, morse);
                System.out.println();
            } else if (choose.equals("3")) {
                System.out.println("\u001B[31mWrite the text to be decrypted: \u001b[0m");
                String base64Decoder = "";
                base64Decoder = input.next();
                base64Decoder += input.nextLine();
                base64Decoder.toLowerCase();
                System.out.println("Input: " + base64Decoder);
                char encoded_String[] = base64Decoder.toCharArray();
                int len_str = encoded_String.length;
                // Do not count last nu1ll character.
                len_str -= 1;
                System.out.printf("\u001b[33mEncoded String : %s\n",
                        String.valueOf(encoded_String) + "\u001b[0m");
                System.out.printf("\u001b[34mDecoded_String : %s\n",
                        obj.base64Decoder(encoded_String, len_str) + "\u001b[0m");
            } else if (choose.equals("4")) {
                System.out.println("\u001B[31mWrite the text to be decrypted: \u001b[0m");
                String encoder = input.next();
                encoder += input.nextLine();
                System.out.println("Here is the input: " + encoder);
                // encoder += input.nextLine();
                encoder.toLowerCase();
                obj.base64Encoder(encoder);
            }else if (choose.equals("5")){
               System.out.println("Write the text");
                String hexa = input.nextLine();
                hexa += input.nextLine();
                obj.StringToHexa(hexa);
            }
            
        } while (1 == 1);
    }
}