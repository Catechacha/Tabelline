import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TabInterface extends JFrame implements Runnable{
	
	private static final long serialVersionUID = 1L;
	TabHandler tabHandler = null;
	int n1, n2;
		
	public TabInterface(){
		this.setSize(300,300);
		this.setTitle("Tabelline");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(beginPanel());
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	System.exit(0);
            }
        });
	}
	
	private JPanel beginPanel() {
		JPanel beginPanel=new JPanel();
		beginPanel.setBorder(new LineBorder(new Color(28, 57, 157),3));
		beginPanel.setBackground(new Color(171,205,239));
		beginPanel.setLayout(new GridLayout(2, 1));
		beginPanel.add(new JLabel ("Tabelline",SwingConstants.CENTER));
		Button beginButton=new Button("Inizia");
		beginButton.setBackground(new Color(204,204,255));
		beginButton.addActionListener(e -> { 
			this.tabHandler= new TabHandler();
			this.setContentPane(playPanel());
			this.revalidate();
		});
		beginPanel.add(beginButton);
		return beginPanel;
	}
	
	private JPanel playPanel(){
		JPanel playPanel=new JPanel();
		playPanel.setBorder(new LineBorder(new Color(28, 57, 157),3));
		playPanel.setBackground(new Color(171,205,239));
		playPanel.setLayout(new GridLayout(5, 1));
		
		playPanel.add(new JLabel ("Risolvi le moltiplicazioni:",SwingConstants.CENTER));
		
		n1=this.tabHandler.getNumber();
		n2=this.tabHandler.getNumber();
		JLabel lab= new JLabel(n1+" x "+n2,SwingConstants.CENTER);
		playPanel.add(lab);
			
		JTextField respText= new JTextField();
		playPanel.add(respText);
		
		Button playButton=new Button("Verifica");
		playButton.setBackground(new Color(204,204,255));
		playButton.addActionListener(e -> { 
			try{
				if(this.tabHandler.verify(n1,n2, Integer.parseInt(respText.getText()))){
					n1=this.tabHandler.getNumber();
					n2=this.tabHandler.getNumber();
					lab.setText(n1+" x "+n2);
					respText.setText("");
					respText.requestFocusInWindow();
					this.revalidate();
				}else{
					JOptionPane.showMessageDialog(playPanel,"Sbagliato!","Errore", JOptionPane.ERROR_MESSAGE);
					respText.setText("");
					respText.requestFocusInWindow();
					this.revalidate();
				}
			}catch(NumberFormatException ee){
				JOptionPane.showMessageDialog(playPanel,"Sbagliato!","Errore", JOptionPane.ERROR_MESSAGE);
				respText.setText("");
				respText.requestFocusInWindow();
				this.revalidate();
			}
		});
		
		playPanel.add(playButton);
		Button menuButton=new Button("Menu");
		menuButton.setBackground(new Color(204,204,255));
		menuButton.addActionListener(e -> { 
			this.changeContentPanel(beginPanel());
		});
		playPanel.add(menuButton);
		return playPanel;
	}

	private void changeContentPanel(JPanel panel) {
		this.setContentPane(panel);
		this.revalidate();
	}

	@Override
	public void run() {	}
}
