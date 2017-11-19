package pis.hue1;

/**
 * Abstract class AbstractCodec contains keyword,
 * setter and getter for keyword
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
