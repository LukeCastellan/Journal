import java.io.*;

//we'll be needing this...
import java.nio.file.*;

//this is to simply present a cleaner interface to the rest of the program.
public class FileHandler {
    private BufferedReader reader;
    
    public FileHandler() {
    
    }
    
    public void writeTo(String path, String text) {
        //writes stuff to a file
        try {
            Files.write(Paths.get(path), text.getBytes());
        } catch (IOException e) {
            System.out.println("something went wrong with writing to " + path);
        }
    }
    
    public String readFile(String path) {
        String text = "";
        //reads a file
        try {
            text = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            System.out.println("something went wrong with read the file at " + path);
        }
        
        return text;
    }
}