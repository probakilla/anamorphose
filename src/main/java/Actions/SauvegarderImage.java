package Actions;

import static cern.clhep.Units.s;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class SauvegarderImage implements ActionListener {
    JFrame fenetre;
    
    public SauvegarderImage (JFrame f) {
        fenetre = f;
    }
    
    private void showSaveFileDialog() {    
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        
        int userSelection = fileChooser.showSaveDialog(fenetre);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
        }
    }
    
    public void actionPerformed (ActionEvent e) {
        showSaveFileDialog();
    }
}
