import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.AbstractListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * @ Gabriel 2021
 */

public class ControllerFile {
     
	 protected ViewFile vf;
     
 	 protected File files [], filesChanged [], f;
 	 protected List<String> filenameChanged;
 	 protected ArrayList<File> arrayFiles ;
 	 protected JFileChooser jfc;
 	 
     ControllerFile (ViewFile vf){
    	 this.vf = vf;
    	 arrayFiles = new ArrayList<File>();
         
    	 jfc = new JFileChooser();
     	
     }
     
     public void alertMsg(String text){
    	    
	 	JOptionPane optionPane = new JOptionPane(text,JOptionPane.WARNING_MESSAGE);
	    JDialog dialog = optionPane.createDialog("Warning!");
	    dialog.setAlwaysOnTop(true);
	    dialog.setVisible(true);
	}
 
     protected void setlistFilesModel(File f)
     {
    	 arrayFiles.clear();
		 	
	     files = f.listFiles();
	    	
	     for (File i : files) {
	    		if (i.isFile()) {
	    			
	    			arrayFiles.add(i);
	    			
	    		}
	    	}
	    	
    	 vf.listFiles.setModel(new AbstractListModel<String>() {
				
				private static final long serialVersionUID = 1L;
				
				public int getSize() {
					
 				return arrayFiles.size();
				}
				public String getElementAt(int index) {
					return arrayFiles.get(index).getName();
				}
			});
     }
     
     public void addListeners() {
    	 
    	vf.btnOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				jfc=new JFileChooser();
				
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				int retriveValue=jfc.showOpenDialog(null);
			    
				
			    if(retriveValue==JFileChooser.APPROVE_OPTION)
			    	{
			    	f = jfc.getSelectedFile();
			        setlistFilesModel(f);
			    	}
			  
			 }
    	 });
    	
    	vf.tglbtnSwitch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent arg0) {
            	vf.tglbtnSwitch.setActivated ( ! vf.tglbtnSwitch.isActivated());
                vf.tglbtnSwitch.repaint();
                
                if (vf.tglbtnSwitch.isActivated()){
                	vf.labelSwitch.setText("Append");
                	vf.hb[2].setVisible(true);
                	vf.hb[3].setVisible(false);
                	vf.legendSubtract.setVisible(false);
                	vf.legendAppend.setVisible(true);
                	}
                else {
                	vf.labelSwitch.setText("Subtract");
                	vf.hb[2].setVisible(false);
                	vf.hb[3].setVisible(true);
                	vf.legendSubtract.setVisible(true);
                	vf.legendAppend.setVisible(false);
                }
            }
        });
    	
    	
    	vf.btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filenameChanged = vf.listFiles.getSelectedValuesList();
				
				for (int i = 0; i< filenameChanged.size(); i++)
					for(int j=0 ; j< arrayFiles.size(); j++)
						
						if (arrayFiles.get(j).getName().equals( filenameChanged.get(i)) )
							{
							vf.mf.setFileName(filenameChanged.get(i));
							
							if (vf.tglbtnSwitch.isActivated() )
								{if (! vf.indexStartField.getText().isEmpty() && ! vf.inputCharacter.getText().isEmpty() )
									if( ! vf.mf.append(new Integer(vf.indexStartField.getText()), vf.inputCharacter.getText()))
										alertMsg("Position is outside of the file name. Please check the input values.");
								}	
							else
								{if (! vf.indexStartField.getText().isEmpty() && ! vf.charLength.getText().isEmpty() )
									if( ! vf.mf.subtract(new Integer(vf.indexStartField.getText()), new Integer(vf.charLength.getText())))
										alertMsg("Position is outside of the file name. Please check the input values.");
								}
							
							
							try {
								
								Path source = arrayFiles.get(j).toPath() ;
								
								Files.move(source, source.resolveSibling( vf.mf.getFileName()) );
								
								System.out.println(	arrayFiles.get(j).getCanonicalPath().toString() );
								
							    /*File newFile = new File( vf.mf.getFileName() );
								
								
								if ( ! arrayFiles.get(j).renameTo( newFile ) ) {
									System.out.println(" File was not successfully renamed");
								} 
								*/
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							  
							}
				
				if (f != null )
					setlistFilesModel(f);
			}
    	});
    	
    	
    	
     }
}
