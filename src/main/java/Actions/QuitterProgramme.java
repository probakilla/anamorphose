package Actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitterProgramme implements ActionListener
{    
    public void actionPerformed(ActionEvent e) 
    {
        System.exit(0); // Ferme le programme.
    }
}
