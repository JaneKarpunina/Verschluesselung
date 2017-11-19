package pis.hue1;


/**
 * Codec Interface is designed to provide a common protocol for objects
 * that are used for coding and decoding Strings.
 * It is written for coding and decoding String only and is using string as a key.
 * If a key is not valid then IllegalArgumentException will be thrown
 */
public interface Codec {

    /**
     * The method for coding a string using a specified key
     *
     * @param klartext text to be coded
     * @return String - result of the coding
     */
    String kodiere(String klartext);

    /**
     * The method for decoding a string using a specified key
     *
     * @param geheimtext text to be decoded
     * @return String - result of the decoding
     */
    String dekodiere(String geheimtext);

    /**
     * @return String - key word which is used for coding
     */
    String gibLosung();

    /**
     * sets key word to be used for coding
     *
     * @param schluessel is a key word
     * @throws IllegalArgumentException if the key word is not valid
     */
    void setzeLosung(String schluessel) throws IllegalArgumentException; // bei ungeeignetem Schlüssel!
}
