import javax.swing.SwingUtilities;

public class Main implements Runnable{
	public static void main(String[] args){
		SwingUtilities.invokeLater( new TabInterface());
	}

	@Override
	public void run() {
		TabInterface gui = new TabInterface();
		gui.setVisible(true);
	}
}