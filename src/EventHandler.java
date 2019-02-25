import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;

public class EventHandler implements ActionListener {
    public EventHandler() {
        //nothing to see here. just to set up a reference.
    }
    
    //this can't be static, but it will be accessible from each and every EventHandler instance.
    public void actionPerformed(ActionEvent event) {
        
        System.out.println("an action occurred: " +  event.getActionCommand());
        
        //yes, we have this code abomination below. it's to get the string
        //representing the action. to prevent any shenangans with CaSe, the string
        //is then converted to lowercase.
        //use setActionCommand() to set the action command and have this function properly.
        switch (event.getActionCommand().toLowerCase()) {
            case "exit":
                //exit Journal. finish this.
                if (Journal.saved/* || Journal.promptSave() != 2*/) { //2 is the 'cancel' option
                    System.exit(0);
                }
                
                break;
            
            case "new":
                //a new file. finish this.
                if (Journal.saved/* || Journal.promptSave() != 2*/) {
                    Journal.editor.clearTextBox();
                    Journal.fileHandler = new FileHandler();
                    Journal.editor.setStatus("Ready.");
                    Journal.editor.setTitle("New file -- Journal");
                }
                
                break;
            
            case "open":
                //open a file. finish this.
                if (Journal.saved/* || Journal.promptSave() != 2*/) {
                    Journal.editor.setStatus("Opening a file...");
                    JFileChooser explorer = new JFileChooser();
                    explorer.setDialogTitle("Open a file...");
                    int selection = explorer.showOpenDialog(Journal.editor.textEditor);
                    
                    if (selection == JFileChooser.APPROVE_OPTION) {
                        Journal.fileHandler = new FileHandler(explorer.getSelectedFile().toPath().toString());
                        Journal.editor.setText(Journal.fileHandler.readFile());
                        Journal.editor.setStatus("File opened.");
                        Journal.editor.setTitle(explorer.getSelectedFile().getName() + " -- Journal");
                    } else {
                        Journal.editor.setStatus("Ready.");
                    }
                }
                
                Journal.saved = true;
                break;
                
            case "save":
                //save file. finish this.
                if (Journal.fileHandler.getPath() == "") {
                    Journal.editor.setStatus("Saving file...");
                    JFileChooser explorer = new JFileChooser();
                    explorer.setDialogTitle("Save the file...");
                    int selection = explorer.showSaveDialog(Journal.editor.textEditor);
                    
                    if (selection == JFileChooser.APPROVE_OPTION) {
                        Journal.fileHandler = new FileHandler(explorer.getSelectedFile().toPath().toString());
                        Journal.fileHandler.writeTo(Journal.editor.getText());
                        Journal.editor.setStatus("File saved at: " + explorer.getSelectedFile().toPath().toString());
                        Journal.editor.setTitle(explorer.getSelectedFile().getName() + " -- Journal");
                    }
                } else {
                    //there is already a file path, so we simply save
                    Journal.fileHandler.writeTo(Journal.editor.getText());
                    Journal.editor.setStatus("File saved.");
                }
                break;
                
            case "save as":
                //save as command. finish this.
                break;
            
            case "close":
                //close the current file. finish this.
                break;
                
            case "cut":
                //cut action. finish this.
                break;
            
            case "copy":
                //copy action. finish this.
                break;
            
            case "paste":
                //paste action. finish this.
                break;
                
            case "undo":
                // undo action. finish this.
                break;
                
            case "redo":
                //redo action. finish this.
                break;
                
            //feel free to add anything i've missed. remember to use setActionCommand()
        }
    }
    
    //we'll implement keyboard shortcuts in another update. let's get a working version first!
}