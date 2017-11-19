package pis.hue1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.function.Consumer;

/**
 * CodecGUI class is simple javafx gui class, which is
 * representing components for coding and decoding of texts
 */
public class CodecGUI {

    private static final String WUERFEL = "Wuerfel";
    private static final String CAESAR = "Caesar";

    private Codec codec1;

    public Codec getCodec1() {
        return codec1;
    }

    public Codec getCodec2() {
        return codec2;
    }

    private Codec codec2;

    @FXML
    GridPane gridPane;

    @FXML
    Button kodieren;

    @FXML
    Button decodieren;

    @FXML
    TextField loesung1;

    @FXML
    TextField loesung2;

    @FXML
    TextArea klarText;

    @FXML
    TextArea geheimText;

    @FXML
    Button saveLoesung1;

    @FXML
    Button saveLoesung2;

    private ToggleGroup group;


    void setCodec1(Codec codec1) {
        this.codec1 = codec1;
    }

    void setCodec2(Codec codec2) {
        this.codec2 = codec2;
    }

    @FXML
    protected void initialize() {

        group = new ToggleGroup();

        RadioButton rb1 = new RadioButton(WUERFEL);
        rb1.setUserData(WUERFEL);
        rb1.setToggleGroup(group);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton(CAESAR);
        rb2.setUserData(CAESAR);
        rb2.setToggleGroup(group);
        HBox rbContainer = new HBox(rb1, rb2);

        gridPane.add(rbContainer, 2, 3);
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) ->
        {
            if (group.getSelectedToggle() != null) {
                Codec codec1;
                Codec codec2;
                if (WUERFEL.equals(newValue.getUserData().toString())) {
                    codec1 = new Wuerfel();
                    codec2 = new Wuerfel();
                } else {
                    codec1 = new Caesar();
                    codec2 = new Caesar();
                }
                copiereLoesungsWoerter(codec1, codec2);
                setCodec1(codec1);
                setCodec2(codec2);

            }
        });
    }

    private void copiereLoesungsWoerter(Codec codec1, Codec codec2) {
        if (this.codec1.gibLosung() == null) setzeLoesung(codec1::setzeLosung, "");
        else setzeLoesung(codec1::setzeLosung, this.codec1.gibLosung());
        if (this.codec2.gibLosung() == null) setzeLoesung(codec2::setzeLosung, "");
        else setzeLoesung(codec2::setzeLosung, this.codec2.gibLosung());
    }


    @FXML
    private void kodieren(ActionEvent event) {
        setEmptyLoesungen();
        geheimText.setText(codec2.kodiere(codec1.kodiere(klarText.getText())));
    }

    @FXML
    private void dekodieren(ActionEvent event) {
        setEmptyLoesungen();
        klarText.setText(codec1.dekodiere(codec2.dekodiere(geheimText.getText())));
    }

    private void setEmptyLoesungen() {
        if (codec1.gibLosung() == null) codec1.setzeLosung("");
        if (codec2.gibLosung() == null) codec2.setzeLosung("");
    }


    @FXML
    private void setzeLeosung1(ActionEvent event) {
        setzeLoesung(codec1::setzeLosung, loesung1.getText());
    }

    @FXML
    private void setzeLeosung2(ActionEvent event) {
        setzeLoesung(codec2::setzeLosung, loesung2.getText());
    }

    private void setzeLoesung(Consumer<String> consumer, String text) {
        try {
            consumer.accept(text);
        } catch (IllegalArgumentException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler Dialoge");
            alert.setHeaderText("Inkorrekte Loesungwort Eingabe fuer "
                    + group.getSelectedToggle().getUserData().toString() + " Kodierung Verfahren");
            alert.setContentText(ex.getLocalizedMessage());
            alert.getDialogPane().getStylesheets().add(
                    "stylesheet.css");
            alert.showAndWait();
        }
    }

}
