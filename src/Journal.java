 //main class of the editor. stores and handles global stuff.
public class Journal {
    //everything here is static, since it will probably need to be accessed anywhere, if you decide to expand this in the future.
    
    //the language the editor is currently working with
    public static String language;
    
    public static java.awt.Font font;
    
    //the filepath of the current open document. makes saving easier.
    //to do "save as", set this to "".
    public static String filePath;
    public static boolean saved = true;
    
    //the big bois
    public static TextEditor editor;
    public static EventHandler eventHandler = new EventHandler();
    public static FileHandler fileHandler = new FileHandler();
    
    //this starts the app
    public static void main(String[] args) {
        editor = new TextEditor();
    }
    
    //prompts the user to save, if they haven't already.
    public static int promptSave() throws Exception {
        return editor.promptSave(); //really, this belongs in the editor. sorry.
    }
}