import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import javax.swing.border.BevelBorder;

import javax.swing.*;
import javax.swing.text.*;
import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class TextEditor {
    
    //status and frame stuff.
    public JFrame textEditor;
    private JLabel statusLabel;
    private JMenu fileMenu, editMenu, toolMenu, helpMenu;
    
    //menu bars
    private JMenuBar editorMenuBar = new JMenuBar();
    private JToolBar guiButtonBar;
    private JFileChooser explorer;
    private JDialog optionsMenu;
    
    //text stuff
    private StyledDocument zoneDoc;
    private JTextPane textZone;
    
    //icons
    private ImageIcon editorIcon = new ImageIcon(getClass().getResource("/menuItems/journal.png")),
        newFileIcon  = new ImageIcon(getClass().getResource("/menuItems/newFile.png")),
        openFileIcon = new ImageIcon(getClass().getResource("/menuItems/openFile.png")),
        saveFileIcon = new ImageIcon(getClass().getResource("/menuItems/saveFile.png")),
        exitIcon     = new ImageIcon(getClass().getResource("/menuItems/exit.png")),
        undoIcon     = new ImageIcon(getClass().getResource("/menuItems/undo.png")),
        redoIcon     = new ImageIcon(getClass().getResource("/menuItems/redo.png")),
        cutIcon      = new ImageIcon(getClass().getResource("/menuItems/cut.png")),
        copyIcon     = new ImageIcon(getClass().getResource("/menuItems/copy.png")),
        optionsIcon  = new ImageIcon(getClass().getResource("/menuItems/options.png")),
        aboutIcon    = new ImageIcon(getClass().getResource("/menuItems/about.png"));
    
    //buttons, using the icons just above.
    private JButton newFileButton = new JButton(newFileIcon),
        openFileButton = new JButton(openFileIcon),
        saveFileButton = new JButton(saveFileIcon),
        exitButton     = new JButton(exitIcon),
        undoButton     = new JButton(undoIcon),
        redoButton     = new JButton(redoIcon),
        cutButton      = new JButton(cutIcon),
        copyButton     = new JButton(copyIcon),
        optionsButton  = new JButton(optionsIcon),
        aboutButton    = new JButton(aboutIcon);
    
    //set up the menu items
    private JMenuItem newFile = new JMenuItem("New", newFileIcon),
        openFile   = new JMenuItem("Open", openFileIcon),
        saveFile   = new JMenuItem("Save", saveFileIcon),
        saveFileAs = new JMenuItem("Save As"),
        exit       = new JMenuItem("Exit Journal", exitIcon),
        undo       = new JMenuItem("Undo", undoIcon),
        redo       = new JMenuItem("Redo", redoIcon),
        cut        = new JMenuItem("Cut", cutIcon),
        copy       = new JMenuItem("Copy", copyIcon),
        selectAll  = new JMenuItem("Select All"),
        options    = new JMenuItem("Options", optionsIcon),
        about      = new JMenuItem("About", aboutIcon);
    
    //most of this is Ctrl+C Ctrl+V from your boilerplate.
    public TextEditor() {
        //add stuff here
        
        //set look and feel has to be first
        setLookAndFeel();
        
        makeEditorBase();
        makeEditorStatusBar();
        makeEditorTextEntry();
        makeEditorMenus();
        makeEditorToolBar();
        
        //these have to be last, or the event handlers won't work. pushy java shit.
        setUpButtons();
        setUpMenuItems();
        
        textEditor.setVisible(true);
    }
    
    private void makeEditorBase() {
        textEditor = new JFrame("Journal");
        textEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editorIcon = new ImageIcon("menuItems/journal.png");
        textEditor.setIconImage(editorIcon.getImage());
        textEditor.setSize(600, 600);
    }
    
    private void makeEditorStatusBar() {
        JPanel statusBar = new JPanel();
        //BevelBorder barBorder = new BevelBorder(BevelBorder.LOWERED);
        Dimension barDimension = new Dimension(textEditor.getWidth(), 16);
        statusLabel = new JLabel("Status:");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        //statusBar.setBorder(barBorder);
        statusBar.setPreferredSize(barDimension);
        statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS)); //figure out later
        statusBar.add(statusLabel);
        textEditor.add(statusBar, BorderLayout.SOUTH);
    }
    
    private void makeEditorMenus() {
        editorMenuBar = new JMenuBar();
        //menus
        fileMenu = new JMenu("File");
        editorMenuBar.add(fileMenu);
        editMenu = new JMenu("Edit");
        editorMenuBar.add(editMenu);
        toolMenu = new JMenu("Tools");
        editorMenuBar.add(toolMenu);
        helpMenu = new JMenu("Help");
        editorMenuBar.add(helpMenu);
        //menu items
        //file menu
        newFile = new JMenuItem("New", newFileIcon);
        fileMenu.add(newFile);
        openFile = new JMenuItem("Open", openFileIcon);
        fileMenu.add(openFile);
        fileMenu.addSeparator();
        saveFile = new JMenuItem("Save", saveFileIcon);
        fileMenu.add(saveFile);
        saveFileAs = new JMenuItem("Save As");
        fileMenu.add(saveFileAs);
        fileMenu.addSeparator();
        exit = new JMenuItem("Exit Journal", exitIcon);
        fileMenu.add(exit);
        //edit menu
        undo = new JMenuItem("Undo", undoIcon);
        editMenu.add(undo);
        redo = new JMenuItem("Redo", redoIcon);
        editMenu.add(redo);
        editMenu.addSeparator();
        cut = new JMenuItem("Cut", cutIcon);
        editMenu.add(cut);
        copy = new JMenuItem("Copy", copyIcon);
        editMenu.add(copy);
        editMenu.addSeparator();
        selectAll = new JMenuItem("Select All");
        editMenu.add(selectAll);
        //tools menu TODO MORE ITEMS, Options to edit fonts
        options = new JMenuItem("Options", optionsIcon);
        toolMenu.add(options);
        //help menu
        about = new JMenuItem("About", aboutIcon);
        helpMenu.add(about);
        //adding all of it to Journal
        textEditor.setJMenuBar(editorMenuBar);
    }
    
    private void makeEditorToolBar() {
        guiButtonBar = new JToolBar("Buttons");
        //button tooltips
        newFileButton.setToolTipText("New File");
        openFileButton.setToolTipText("Open File");
        saveFileButton.setToolTipText("Save File");
        exitButton.setToolTipText("Exit Journal");
        undoButton.setToolTipText("Undo");
        redoButton.setToolTipText("Redo");
        cutButton.setToolTipText("Cut");
        copyButton.setToolTipText("Copy");
        optionsButton.setToolTipText("Options Menu");
        aboutButton.setToolTipText("About Journal");
        //add buttons to toolbar
        guiButtonBar.add(newFileButton);
        guiButtonBar.add(openFileButton);
        guiButtonBar.add(saveFileButton);
        guiButtonBar.addSeparator();
        guiButtonBar.add(undoButton);
        guiButtonBar.add(redoButton);
        guiButtonBar.add(copyButton);
        guiButtonBar.add(cutButton);
        guiButtonBar.addSeparator();
        guiButtonBar.add(optionsButton);
        guiButtonBar.add(aboutButton);
        guiButtonBar.add(exitButton);
        guiButtonBar.addSeparator();
        //button event listeners
        textEditor.add(guiButtonBar, BorderLayout.NORTH);
    }
    
    private void makeEditorTextEntry() {
        /**
         * Override for Caret to always make colored text visible, not only when selected by mouse highlight 
         */ 
        DefaultCaret caret = new DefaultCaret() {
            public boolean isSelectionVisible() {
                return true;
            }
        };
        textZone = new JTextPane();
        JScrollPane textScrolling = new JScrollPane(textZone);
        zoneDoc = (StyledDocument) textZone.getDocument();
        textZone.setCaret(caret);
        textZone.setFont(Options.font);
        textScrolling.setBounds(0,0,600,600);
        textEditor.add(textScrolling, BorderLayout.CENTER);
    }
    
    private void setUpButtons() {
        //this method is to set the action commands of each button, and then add the Event Handler
        newFileButton.setActionCommand("new");     newFileButton.addActionListener(Journal.eventHandler);
        openFileButton.setActionCommand("open");   openFileButton.addActionListener(Journal.eventHandler);
        saveFileButton.setActionCommand("save");   saveFileButton.addActionListener(Journal.eventHandler);
        exitButton.setActionCommand("exit");       exitButton.addActionListener(Journal.eventHandler);
        cutButton.setActionCommand("cut");         cutButton.addActionListener(Journal.eventHandler);
        copyButton.setActionCommand("copy");       copyButton.addActionListener(Journal.eventHandler);
        undoButton.setActionCommand("undo");       undoButton.addActionListener(Journal.eventHandler);
        redoButton.setActionCommand("redo");       redoButton.addActionListener(Journal.eventHandler);
        optionsButton.setActionCommand("options"); optionsButton.addActionListener(Journal.eventHandler);
        aboutButton.setActionCommand("about");     aboutButton.addActionListener(Journal.eventHandler);
    }
    
    private void setUpMenuItems() {
        //this method is to set the action commands, and then add the event handler.
        newFile.setActionCommand("new");          newFile.addActionListener(Journal.eventHandler);
        openFile.setActionCommand("open");        openFile.addActionListener(Journal.eventHandler);
        saveFile.setActionCommand("save");        saveFile.addActionListener(Journal.eventHandler);
        saveFileAs.setActionCommand("save as");   saveFileAs.addActionListener(Journal.eventHandler);
        exit.setActionCommand("exit");            exit.addActionListener(Journal.eventHandler);
        cut.setActionCommand("cut");              cut.addActionListener(Journal.eventHandler);
        copy.setActionCommand("copy");            copy.addActionListener(Journal.eventHandler);
        undo.setActionCommand("undo");            undo.addActionListener(Journal.eventHandler);
        redo.setActionCommand("redo");            redo.addActionListener(Journal.eventHandler);
        options.setActionCommand("options");      options.addActionListener(Journal.eventHandler);
        selectAll.setActionCommand("select all"); selectAll.addActionListener(Journal.eventHandler);
        about.setActionCommand("about");          about.addActionListener(Journal.eventHandler);
        //System.out.println("event handlers added for menu items.");
    }
    
    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel( new net.sourceforge.napkinlaf.NapkinLookAndFeel());
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception exc) {
            // ignore error
            System.out.println("an error occured.");
        }
    }
    
    public void setStatus(String text) {
        statusLabel.setText(text);
    }
    
    public int promptSave() throws Exception {
        return JOptionPane.showConfirmDialog(null,
            "Would you like to save your changes first?",
            "Save your changes first?",
            JOptionPane.YES_NO_CANCEL_OPTION
        );
    }
    
    public void clearTextBox() {
        textZone.setText("");
    }
    
    public void setText(String text) {
        textZone.setText(text);
    }
    
    public String getText() {
        return textZone.getText();
    }
    
    public void setTitle(String newTitle) {
        textEditor.setTitle(newTitle);
    }
}