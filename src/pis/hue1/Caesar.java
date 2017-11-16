package pis.hue1;

public class Caesar extends AbstractCodec implements Codec {

    public Caesar() {
    }

    public Caesar(String schluessel) {
        super(schluessel);
    }

    @Override
    public String kodiere(String klartext) {
        if (schluessel.length() == 0) return klartext;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < klartext.length(); i++) {
            if (!Character.isLetter(klartext.charAt(i))) result.append(klartext.charAt(i));
            else if (!Character.isUpperCase(klartext.charAt(i))) {
                kodiereChar(klartext, result, i, 'a', 'z');
            } else {
                kodiereChar(klartext, result, i, 'A', 'Z');
            }
        }
        return result.toString();
    }

    private void kodiereChar(String klartext, StringBuilder result, int i, char start, char end) {
        char newChar = (char) (klartext.charAt(i) + schluessel.length());
        char j = (char) (newChar <= end ? newChar : start + newChar - end - 1);
        result.append(j);
    }

    @Override
    public String dekodiere(String geheimtext) {
        if (schluessel.length() == 0) return geheimtext;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < geheimtext.length(); i++) {
            if (!Character.isLetter(geheimtext.charAt(i))) result.append(geheimtext.charAt(i));
            else if (!Character.isUpperCase(geheimtext.charAt(i))) {
                dekodiereChar(geheimtext, result, i, 'a', 'z');
            } else {
                dekodiereChar(geheimtext, result, i, 'A', 'Z');
            }
        }
        return result.toString();
    }

    private void dekodiereChar(String geheimtext, StringBuilder result, int i, char start, char end) {
        char newChar = (char) (geheimtext.charAt(i) - schluessel.length());
        char j = (char) (newChar >= start ? newChar : newChar + end - start + 1);
        result.append(j);
    }

    @Override
    public void setzeLosung(String schluessel) throws IllegalArgumentException {
        if (schluessel.contains("\\s+"))
            throw new IllegalArgumentException("Schluessel kann nicht speciale Character enthalten");
        this.schluessel = schluessel;
    }
}
