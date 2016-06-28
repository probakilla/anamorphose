
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Fenetre {
	
	public static void main (String args[]) {
		
		
		JFrame fenetre = new JFrame ("Anamorphose");
		JMenuBar menuBar = new JMenuBar ();
                JMenu menu = new JMenu ("test");
                JMenuItem menuItem = new JMenuItem ("Test menu item", KeyEvent.VK_T);
                
                
                // Affiche le menu dans le menuBar
                menu.setMnemonic(KeyEvent.VK_A);
                menu.getAccessibleContext().setAccessibleDescription("Test de menu");
                menuBar.add(menu);
                
                // Affiche le menuItem
                menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
                menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
                menu.add(menuItem);
                
		// Creer la fenetre
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setPreferredSize(new Dimension(1024, 768));
		
		
		
		// Afficher
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setJMenuBar(menuBar);
	}
}
