import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class notepad extends JFrame implements ActionListener {

    ImageIcon icon=new ImageIcon("C:\\Users\\DELL\\Desktop\\nnnnn.png");
    JMenuBar menubar;
    JMenu file,edit,help;
    JMenuItem newDoc,open,save,print,copy,paste,cut,selectall,helpp;
    JTextArea area=new JTextArea();

   JScrollPane pane;

    notepad(){

        this.setTitle("Notepad");

        menubar=new JMenuBar();

        file=new JMenu("File");
        edit=new JMenu("Edit");
        help=new JMenu("Help");

/*                 MENU ITEMS                   */

        newDoc=new JMenuItem("New");
        newDoc.addActionListener(this);
        newDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        file.add(newDoc);

        open=new JMenuItem("Open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        file.add(open);

        save=new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        file.add(save);

        print=new JMenuItem("Print");
        print.addActionListener(this);
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        file.add(print);

        copy=new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        edit.add(copy);

        paste=new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        edit.add(paste);

        cut=new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        edit.add(cut);

        selectall=new JMenuItem("Select All");
        selectall.addActionListener(this);
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        edit.add(selectall);

        helpp=new JMenuItem("Help");
        help.addActionListener(this);
        helpp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        help.add(helpp);

        /*      -----------------------------------------          */

        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        /*                       text area                        */

        area.setFont(new Font("Ariel",Font.LAYOUT_LEFT_TO_RIGHT,18));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);                       //to bring a bigger word in next line

        this.setJMenuBar(menubar);
        this.add(area);

        pane=new JScrollPane(area);                        //scroll pane
        pane.setBorder(BorderFactory.createEmptyBorder());
        this.add(pane);

        this.setIconImage(icon.getImage());                 //set favicon
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);       //to get full screen frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("New")) {
            area.setText("");
        }

        else if(e.getActionCommand().equals("Open")){

            JFileChooser choose=new JFileChooser();
            choose.setCurrentDirectory(new File("C:\\Users\\DELL\\Desktop"));      //current directory set to Desktop

            choose.setAcceptAllFileFilterUsed(false);                 // restrict to open only txt files
            FileNameExtensionFilter restrict=new FileNameExtensionFilter("Only .txt files","txt");
            choose.addChoosableFileFilter(restrict);                 //chooses file

           int action = choose.showOpenDialog(this);
            if(action!= JFileChooser.APPROVE_OPTION){            //if we click cancel button
                return;
            }

            File file=choose.getSelectedFile();

            try{
                BufferedReader read=new BufferedReader(new FileReader(file));          //reads the file
                area.read(read,null);       //opens file in area
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }

        else if(e.getActionCommand().equals("Save")){

            JFileChooser saveas=new JFileChooser();
            saveas.setCurrentDirectory(new File("C:\\Users\\DELL\\Desktop"));
            saveas.setApproveButtonText("Save");

            int action = saveas.showSaveDialog(this);
            if(action!= JFileChooser.APPROVE_OPTION){            //if we click cancel button
                return;
            }

            File filename=new File(saveas.getSelectedFile()+"");

            try{
                BufferedWriter  write=new BufferedWriter(new FileWriter(filename));
                area.write(write);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        }

        else if(e.getActionCommand().equals("Print")){

            try{
                area.print();              //opens printer commands
            }
            catch(Exception ex){
            ex.printStackTrace();
            }
        }

        /*
        to exit just do
        System.Exit(0);
         */
    }

    public static void main(String[] args) {

     new notepad();

    }


}