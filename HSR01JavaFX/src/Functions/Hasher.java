package Functions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    public static String hash(String algorithm, String message) {
        try {
            //MessageDigest wordt aangemaakt met meegegeven algoritme
            MessageDigest md = MessageDigest.getInstance(algorithm);
            //Het meegegeven bericht wordt omgezet tot een array van het datatype byte en toegevoegd aan de MessageDigest
            md.update(message.getBytes());
            //Hasht het (in bytes omgezette) bericht en stopt die in de array bytes
            byte[] bytes = md.digest();
            //Bytes worden omgezet van decimalen naar hexadecimalen
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //De hexadecimale hash wordt gereturnd
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "Hash error";
        }
    }
}
