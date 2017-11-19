package pis.hue1;

/**
 * Abstract class AbstractCodec contains key word,
 * setter and getter for key word
 */
public abstract class AbstractCodec implements Codec {

    //key word used for coding
    String schluessel;

    AbstractCodec() {
    }

    AbstractCodec(String schluessel) {
        this.schluessel = schluessel;
    }

    @Override
    public String gibLosung() {
        return schluessel;
    }
}
