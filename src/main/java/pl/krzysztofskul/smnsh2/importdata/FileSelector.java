package pl.krzysztofskul.smnsh2.importdata;

import java.io.File;

import javax.swing.JFileChooser;

import org.springframework.stereotype.Service;

@Service
public class FileSelector {
	
	/**
	 * This method shows a window which allows to choose a file or a folder from the system file system on the host local drive;
	 * @param String mode ("file" accepts files; "folder" accepts folders; by default accepts files;)
	 * @return String: path to chosen file or folder
	 */
	public static String select(String mode) {
//	    final JFileChooser chooser = new JFileChooser() {
//	        public void approveSelection() {
//	            if (getSelectedFile().isFile()) {
//	                return;
//	            } else
//	                super.approveSelection();
//	        }
//	    };

		JFileChooser chooser = new JFileChooser();
		
	    //chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setCurrentDirectory(new java.io.File(ImportData.getImportDataSingleton().getPathProjectsToImport()));
	    
	    switch (mode) {
	    case ("file"): {
		    	chooser.setDialogTitle("Select file");
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);	    
			    break;
	    	}
	    case ("folder"): {
	    	chooser.setDialogTitle("Select folder");
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    break;
    	}
	    default: {
	    	chooser.setDialogTitle("Select file");
		    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);	    
		    break;	    	
	    }
		    
	    }

	    chooser.setAcceptAllFileFilterUsed(true);

	    //chooser.setSelectedFile(new java.io.File("."));
	    
	    
	    chooser.showOpenDialog(null);
	    File x = chooser.getSelectedFile();

	    if (x != null) {
	        //System.out.println(x.toPath());
	    	return x.toString();
	    }
	    
	
	    return null;
	}
	
}
