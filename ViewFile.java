import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
//import javax.swing.JToggleButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Box;
/*
 * @ Gabriel 2021
 */

public class ViewFile {

	protected JFrame frmRenamefiles;
	protected JTextField indexStartField, inputCharacter, charLength;
	protected JPanel panel, panel_1;
	protected JToolBar toolBar;
	protected JButton btnOpen, btnSave;
	protected JList<String> listFiles;
	//protected JToggleButton tglbtnSwitch;
	protected ToggleSwitch tglbtnSwitch;
	protected JScrollPane sp;
    protected JLabel labelSwitch;
    protected JTextArea legendSubtract, legendAppend;
    protected Box hb[];
    
	protected ModelFile mf;
	protected ControllerFile cf;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewFile window = new ViewFile();
					window.frmRenamefiles.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewFile() {
		mf = new ModelFile();
		
		initialize();
		
		cf = new ControllerFile(this);
		cf.addListeners();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRenamefiles = new JFrame();
		frmRenamefiles.setTitle("RenameFiles");
		frmRenamefiles.setBounds(100, 100, 450, 300);
		frmRenamefiles.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frmRenamefiles.getContentPane().add(panel, BorderLayout.WEST);
		
		panel_1 = new JPanel();
		frmRenamefiles.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		Box verticalBox = Box.createVerticalBox();
		
		panel_1.add(verticalBox);
		
		//swBtn = new SwitchButton();
		//verticalBox.add(swBtn);
		
		
		hb = new Box[4];
		
		for (int i=0; i< hb.length ; i++)
			hb[i] = Box.createHorizontalBox();
		
		labelSwitch = new JLabel();
		
		tglbtnSwitch = new ToggleSwitch(30,30);
		tglbtnSwitch.setActiveSwitch(Color.GREEN);
		//frmRenamefiles.getContentPane().add(tglbtnSwitch, BorderLayout.CENTER);
		
		//tglbtnSwitch = new JToggleButton("Append",true);

		hb[0].add(labelSwitch);
		hb[0].add(tglbtnSwitch);
		
		JLabel jl[]= new JLabel[3];
		
		for (int i=0; i< jl.length ; i++)
			jl[i] = new JLabel();

		indexStartField = new JTextField();
		indexStartField.setToolTipText("Start Index");
		indexStartField.setColumns(3);
		jl[0].setText(indexStartField.getToolTipText());
		
		hb[1].add(jl[0]);
		hb[1].add(indexStartField);
		
		inputCharacter = new JTextField();
		inputCharacter.setToolTipText("Input Character");
		inputCharacter.setColumns(3);
		jl[1].setText(inputCharacter.getToolTipText());

		hb[2].add(jl[1]);
		hb[2].add(inputCharacter);

		charLength = new JTextField();
		charLength.setToolTipText("Character Length");
		charLength.setColumns(3);
		jl[2].setText(charLength.getToolTipText());
		
		hb[3].add(jl[2]);
		hb[3].add(charLength);

		for (int i= 0; i<hb.length; i++)
			verticalBox.add(hb[i]);
		
		legendSubtract = new JTextArea();
		legendSubtract.setText("Ex. filename.txt "
				+ "\n\n Subtract 8 , 1 "
				+ "\n results in filenametxt"
				+ "\n"
				+ "\n Subtract 1 , 1 "
				+ "\n results in flename.txt"
				+ "\n\n since you'll need at least"
				+ "\n one character for a file name");
		legendSubtract.setBackground(frmRenamefiles.getBackground());
		legendSubtract.setDisabledTextColor(Color.RED);
		legendSubtract.setEnabled(false);
		
		legendAppend = new JTextArea();
		legendAppend.setText("Ex. filename.txt "
				+ "\n\n Append 10 , x "
				+ "\n results in filename.xtxt"
				+ "\n"
				+ "\n Append 1 , x "
				+ "\n results in xfilename.txt");
		legendAppend.setBackground(frmRenamefiles.getBackground());
		legendAppend.setDisabledTextColor(Color.RED);
		legendAppend.setEnabled(false);
		
		verticalBox.add(legendSubtract);
		verticalBox.add(legendAppend);
		
		toolBar = new JToolBar();
		frmRenamefiles.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		btnOpen = new JButton("Open Folder");
		btnSave = new JButton("Save");
		
		toolBar.add(btnOpen);
		toolBar.add(btnSave);
		
		listFiles = new JList<String>();
		
		sp = new JScrollPane();
		sp.setViewportView(listFiles);
		
		panel.add(sp);
		
		
		labelSwitch.setText("Subtract");
    	hb[2].setVisible(false);
    	hb[3].setVisible(true);
    	legendAppend.setVisible(false);
	}
}
