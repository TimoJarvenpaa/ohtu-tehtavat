
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa implements Komento {
    
    private TextField tuloskentta;
    private TextField syotekentta;
    private Button undo;
    private Button nollaa;
    private Sovelluslogiikka sovellus;
    private int edellinen;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }

    @Override
    public void suorita() {
        int arvo = 0;

        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        
        this.edellinen = Integer.parseInt(tuloskentta.getText());
        sovellus.nollaa();
        int laskunTulos = sovellus.tulos();
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        
        if ( laskunTulos==0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        undo.disableProperty().set(false);
    }

    @Override
    public void peru() {
        tuloskentta.setText("" + edellinen);
        sovellus.nollaa();
        nollaa.disableProperty().set(false);
        undo.disableProperty().set(true);
    }
    
}
