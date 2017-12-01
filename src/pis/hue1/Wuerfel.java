package pis.hue1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Class Wuerfel implements Codec interface and is used for
 * coding with wuerfel method. Valid keywords consist only from
 * letters in both upper and lower case and it is irrelevant witch case the
 * letter in a keyword has.
 * https://de.wikipedia.org/wiki/%C3%9CBCHI - description of the coding method
 */
public class Wuerfel extends AbstractCodec implements Codec {

    /**
     * array may have only positive and consecutive integer members
     * the minimal element is always 1
     * the length of the array is equal to the length of the key word
     * keyword could contain only letters in both cases
     */
    private int[] array;

    Wuerfel() {
    }

    public Wuerfel(String schluessel) {
        super(schluessel);
        array = new int[schluessel.length()];
        initializeArray();
    }

    private void initializeArray() {
        StringBuilder result = new StringBuilder(schluessel);
        schluesselSortieren(result);
        massivAusfuellen(result);
    }

    private void massivAusfuellen(StringBuilder result) {
        for (int i = 0; i < result.length(); i++) {
            int j = 0;
            while (array[schluessel.indexOf(result.charAt(i), j)] != 0)
                j = schluessel.indexOf(result.charAt(i), j) + 1;
            array[schluessel.indexOf(result.charAt(i), j)] = i + 1; //fill an array with the number from 1
        }
    }

    private void schluesselSortieren(StringBuilder result) {
        for (int i = 0; i < result.length(); i++) {
            for (int j = result.length() - 1; j > 0; j--) {
                if (Character.toUpperCase(result.charAt(j)) < Character.toUpperCase(result.charAt(j - 1))) {
                    char s = result.charAt(j);
                    result.setCharAt(j, result.charAt(j - 1));
                    result.setCharAt(j - 1, s);
                }
            }
        }
    }

    /**
     * Coding using wuerfel coding method
     */
    @Override
    public String kodiere(String klartext) {
        if (schluessel.length() == 0) return klartext;
        List<String> zeilen = new ArrayList<>();
        zeilenEingeben(klartext, zeilen);
        return spaltenLesen(zeilen).toString();
    }

    private StringBuilder spaltenLesen(List<String> zeilen) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= schluessel.length(); i++) {
            int index = findIndex(array, i);
            for (String zeile : zeilen)
                if (index < zeile.length()) result.append(zeile.charAt(index));
        }
        return result;
    }

    private void zeilenEingeben(String klartext, List<String> zeilen) {
        for (int i = 0; i <= klartext.length() / schluessel.length(); i++) {
            if (i == klartext.length() / schluessel.length())
                zeilen.add(klartext.substring(i * schluessel.length()));
            else
                zeilen.add(klartext.substring(i * schluessel.length(), (i + 1) * schluessel.length()));
        }
    }

    private int findIndex(int[] array, int elementToFind) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == elementToFind) return i;
        }
        return -1;
    }

    /**
     * Decoding using wuerfel coding method
     */
    @Override
    public String dekodiere(String geheimtext) {
        if (schluessel.length() == 0) return geheimtext;
        List<String> spalten = new ArrayList<>();
        int count = geheimtext.length() / schluessel.length();
        int rest = geheimtext.length() % schluessel.length();
        spaltenEingeben(geheimtext, spalten, count, rest);
        return zeilenLesen(spalten, count);
    }

    private void spaltenEingeben(String geheimtext, List<String> spalten, int count, int rest) {
        int index = 0;
        int part[] = Arrays.copyOfRange(array, 0, rest);
        for (int i = 0; i < schluessel.length(); i++) {
            if (isInArray(part, i + 1)) {
                spalten.add(geheimtext.substring(index, index + count + 1));
                index = index + count + 1;
            } else {
                spalten.add(geheimtext.substring(index, index + count));
                index = index + count;
            }
        }
    }

    private String zeilenLesen(List<String> spalten, int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= count; i++) {
            for (int a : array) {
                if (i < count || spalten.get(a - 1).length() == count + 1)
                    result.append(spalten.get(a - 1).charAt(i));
            }
        }
        return result.toString();
    }

    private boolean isInArray(int[] a, int x) {
        for (int element : a)
            if (element == x) return true;
        return false;
    }

    /**
     *
     * @throws IllegalArgumentException when the key contains symbols other then letters
     */
    @Override
    public void setzeLosung(String schluessel) throws IllegalArgumentException {
        if (schluessel == null || !Pattern.compile("[a-zA-Z]*").matcher(schluessel).matches())
            throw new IllegalArgumentException("Schluessel kann nur Buchstaben enthalten");
        this.schluessel = schluessel;
        array = new int[schluessel.length()];
        initializeArray();
    }

}
