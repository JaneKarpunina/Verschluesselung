package pis.hue1;

public class Caesar implements Codec {

    private String schluessel;

    @Override
    public String kodiere(String klartext) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < klartext.length(); i++) {
            char newChar = (char) (klartext.charAt(i) + schluessel.length());
            char j = (char) (newChar <= 'z' ? newChar : 'a' + newChar - 'z' - 1);
            result.append(j);
        }
        return result.toString();
    }

    @Override
    public String dekodiere(String geheimtext) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < geheimtext.length(); i++) {
            char newChar = (char) (geheimtext.charAt(i) - schluessel.length());
            char j = (char) (newChar >= 'a' ? newChar : newChar - 'z'- 'a' + 1 );
            result.append(j);
        }
        return result.toString();
    }

    @Override
    public String gibLosung() {
        return schluessel;
    }

    @Override
    public void setzeLosung(String schluessel) throws IllegalArgumentException {
        if (schluessel.contains("[^a-zA-Z]+"))
            throw new IllegalArgumentException("Schluessel kann nur Buchstaben enthalten");
        this.schluessel = schluessel;
    }
}
