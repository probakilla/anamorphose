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
    
    // Récupère le nom de la fenetre où l'on veut afficher l'image.
    public ChargerImage (JFrame f) {
        nomFenetre = f;
    }
    
    public void actionPerformed(ActionEvent e) {
        
        // Récupère l'image
        MBFImage source = null;
        JFileChooser fichier = new JFileChooser();
        fichier.showOpenDialog(null);
        
        // Verrifie si le fichier sélectionné est une image.
        try {
            source = ImageUtilities.readMBF(fichier.getSelectedFile());
        } catch (IOException ex) {
            System.err.printf("Veuillez sÃ©lectionner une image.");
        }
        
        if (source.getHeight() > nomFenetre.getHeight() || source.getWidth() > nomFenetre.getWidth()) {
            nomFenetre.setSize(source.getWidth(), source.getHeight());
            
        }
                
        // Affiche l'image choisie.
        DisplayUtilities.display(source, nomFenetre);
    }
}
