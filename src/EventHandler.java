import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EventHandler implements ActionListener {
    public EventHandler() {
        //nothing to see here. just to set up a reference.
    }
    
    //this is static, and so it's accessible from each and every EventHandler instance.
    public void actionPerformed(ActionEvent event) {
        
        //yes, we have this code abomination below. it's to get the string
        //representing the action. to prevent any shenangans with CaSe, the string
        //is then converted to lowercase.
        //use setActionCommand() to set the action command and have this function properly.
        switch (event.getActionCommand().toLowerCase()) {
            case "exit":
                //exit Journal. finish this.
                break;
            
            case "new":
                //a new file. finish this.
                break;
            
            case "open":
                //open a file. finish this.
                break;
                
            case "save":
                //save file. finish this.
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
    
    //we'll implement keyborad shortcuts in another update. let's get a working version first!
}