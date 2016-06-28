package Boutons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;

public class ChargerImage implements ActionListener {
    JFrame nomFenetre;
    
    public ChargerImage (JFrame f) {
        nomFenetre = f;
    }
    
    public void actionPerformed(ActionEvent e) {
        MBFImage source = null;
        JFileChooser fichier = new JFileChooser();
        fichier.showOpenDialog(null);
        
        try {
            source = ImageUtilities.readMBF(fichier.getSelectedFile());
        } catch (IOException ex) {
            System.err.printf("Veuillez sélectionner une image.");
        }
        DisplayUtilities.display(source, nomFenetre);
    }
}
