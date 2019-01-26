import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import javax.swing.border.BevelBorder;

import javax.swing.*;
import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class TextEditor {
    
    private JFrame textEditor;
    private JLabel statusLabel;
    private JTextPane textZone;
    private JMenu fileMenu, editMenu, toolMenu, helpMenu;
    private JMenuItem newFile, openFile, saveFile, saveFileAs, exit, undo, redo, cut, copy, selectAll, options, about;
    private JMenuBar editorMenuBar;
    private JToolBar guiButtonBar;
    private JButton newFileButton, openFileButton, saveFileButton, exitButton, undoButton, redoButton, cutButton, copyButton, optionsButton, aboutButton;
    private ImageIcon editorIcon, newFileIcon, openFileIcon, saveFileIcon, exitIcon, undoIcon, redoIcon, cutIcon, copyIcon, optionsIcon, aboutIcon;
    private JFileChooser explorer;
    private JDialog optionsMenu;
    
    //most of this is Ctrl+C Ctrl+V from your boilerplate.
    public TextEditor() {
        //add stuff here
        makeEditorBase();
        makeEditorStatusBar();
        makeEditorMenus();
        textEditor.setVisible(true);
    }
    
    public void makeEditorBase() {
        textEditor = new JFrame("Journal");
        textEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editorIcon = new ImageIcon("menuItems/journal.png");
        textEditor.setIconImage(editorIcon.getImage());
        textEditor.setSize(600, 600);
    }
    
    public void makeEditorStatusBar() {
        JPanel statusBar = new JPanel();
        BevelBorder barBorder = new BevelBorder(BevelBorder.LOWERED);
        Dimension barDimension = new Dimension(textEditor.getWidth(), 16);
        statusLabel = new JLabel("Status:");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusBar.setBorder(barBorder);
        statusBar.setPreferredSize(barDimension);
        statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS)); //figure out later
        statusBar.add(statusLabel);
        textEditor.add(statusBar, BorderLayout.SOUTH);
    }
    
    public void makeEditorMenus() {
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
        //menu items icons
        newFileIcon = new ImageIcon("menuItems\\newFile.png");
        openFileIcon = new ImageIcon("menuItems\\openFile.png");
        saveFileIcon = new ImageIcon("menuItems\\saveFile.png");
        exitIcon = new ImageIcon("menuItems\\exit.png");
        undoIcon = new ImageIcon("menuItems\\undo.png");
        redoIcon = new ImageIcon("menuItems\\redo.png");
        cutIcon = new ImageIcon("menuItems\\cut.png");
        copyIcon = new ImageIcon("menuItems\\copy.png");
        optionsIcon = new ImageIcon("menuItems\\options.png");
        aboutIcon = new ImageIcon("menuItems\\about.png");
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
        //event listeners
        newFile.addActionListener(new EventHandler());
        openFile.addActionListener(new EventHandler());
        saveFile.addActionListener(new EventHandler());
        saveFileAs.addActionListener(new EventHandler());
        exit.addActionListener(new EventHandler());
        undo.addActionListener(new EventHandler());
        redo.addActionListener(new EventHandler());
        cut.addActionListener(new EventHandler());
        copy.addActionListener(new EventHandler());
        selectAll.addActionListener(new EventHandler());
        options.addActionListener(new EventHandler());
        about.addActionListener(new EventHandler());
        //adding all of it to Journal
        textEditor.setJMenuBar(editorMenuBar);
    }
}