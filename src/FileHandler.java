import java.io.*;

//we'll be needing this...
import java.nio.file.*;

//this is to simply present a cleaner interface to the rest of the program.
//in the future, if we want to have tabbed editors, we simply assign a FileHandler to each editor tab.
public class FileHandler {
    //private BufferedReader reader;
    String path;
    
    public FileHandler() {
        this.path = "";
    }
    
    public FileHandler(String path) {
        this.path = path;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public void writeTo(String text) {
        //writes stuff to a file
        try {
            Files.write(Paths.get(path), text.getBytes());
        } catch (IOException e) {
            System.out.println("something went wrong with writing to " + path);
        }
    }
    
    public void writeTo(String path, String text) {
        this.path = path;
        //now write!
        writeTo(text);
    }
    
    public String readFile() {
        String text = "";
        //reads a file
        try {
            text = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            System.out.println("something went wrong with read the file at " + path);
        }
        
        return text;
    }
    
    //opening another file, which requires resetting the path
    public String readFile(String newPath) {
        this.path = newPath;
        return this.readFile();
    }
}