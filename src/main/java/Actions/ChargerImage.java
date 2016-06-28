package Actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;

public class ChargerImage implements ActionListener {
 
    public void actionPerformed(ActionEvent e) {
        JFileChooser fichier = new JFileChooser();
        fichier.showOpenDialog(null);
    }
}
