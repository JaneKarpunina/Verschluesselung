package pis.hue1;

public abstract class AbstractCodec implements Codec {

    String schluessel;

    AbstractCodec() {}

    AbstractCodec(String schluessel) {
      this.schluessel = schluessel;
    }

    @Override
    public String gibLosung() {
        return schluessel;
    }
}
