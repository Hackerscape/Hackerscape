//Credits to MITB
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.*;

public class XMLLoader
    implements ActionListener, KeyListener
{

    private String itemName[];
    private String itemID[];
    private String fileName;
    private JTextArea namePanel;
    private JTextArea idPanel;
    private JTextArea searchPanel;

    public static void main(String args[]) {
        new XMLLoader("Objects.xml");
    }

    public XMLLoader(URL xmlURL)
    {
        itemName = new String[10000];
        itemID = new String[10000];
        try
        {
            URLConnection xmlConnect = xmlURL.openConnection();
            xmlConnect.setRequestProperty("User-Agent", "Mozilla");
            xmlConnect.connect();
            readStream(xmlConnect.getInputStream());
        }
        catch(IOException e)
        {
            System.out.println((new StringBuilder()).append("Couldn't find the file ").append(fileName).toString());
        }
        initUI();
    }

    public XMLLoader(String fileName)
    {
        itemName = new String[10000];
        itemID = new String[10000];
        File file = new File(fileName);
        this.fileName = file.getName();
        try
        {
            FileInputStream fistream = new FileInputStream(file);
            readStream(fistream);
        }
        catch(IOException e)
        {
            System.out.println((new StringBuilder()).append("Couldn't find the file ").append(fileName).toString());
        }
        initUI();
    }

    private void readStream(InputStream inputStream)
        throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        int linenumber = 0;
        String clientline[];
        for(clientline = new String[30000]; (clientline[linenumber] = reader.readLine()) != null; linenumber++) { }
        inputStream.close();
        int y = 0;
        for(int x = 0; x < clientline.length && clientline[x] != null; x++)
        {
            if(clientline[x].contains("name="))
            {
                String name = clientline[x].substring(clientline[x].indexOf("name=") + 6).replaceAll("\".*", "");
                String id = clientline[x].substring(clientline[x].indexOf("interfaceType=") + 6).replaceAll("\".*", "");
                itemName[y] = name;
                itemID[y] = id;
                y++;
            }
        }

    }

    private void initUI()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame(fileName);
        frame.setDefaultCloseOperation(2);
        frame.getContentPane().setLayout(new BorderLayout());
        namePanel = new JTextArea();
        namePanel.setEditable(false);
        idPanel = new JTextArea();
        idPanel.setEditable(false);
        JPanel infoPane = new JPanel(new FlowLayout());
        infoPane.add(namePanel);
        infoPane.add(idPanel);
        JScrollPane scroll = new JScrollPane(infoPane, 22, 31);
        scroll.setPreferredSize(new Dimension(240, 503));
        boolean foundOne = false;
        String newLine = "\n";
        for(int x = 0; x < itemName.length && itemName[x] != null; x++)
        {
            if(foundOne)
            {
                namePanel.append((new StringBuilder()).append(newLine).append(itemName[x]).toString());
                idPanel.append((new StringBuilder()).append(newLine).append(itemID[x]).toString());
            } else
            {
                foundOne = true;
                namePanel.append(itemName[x]);
                idPanel.append(itemID[x]);
            }
        }

        JButton search = new JButton("Search");
        search.addActionListener(this);
        searchPanel = new JTextArea();
        searchPanel.addKeyListener(this);
        searchPanel.setLineWrap(false);
        searchPanel.setRows(1);
        searchPanel.setColumns(9);
        JPanel searchPane = new JPanel(new FlowLayout());
        searchPane.add(searchPanel);
        searchPane.add(search);
        frame.getContentPane().add(scroll, "Center");
        frame.getContentPane().add(searchPane, "South");
        frame.pack();
        frame.setVisible(true);
        searchPanel.requestFocus();
    }

    private void search()
    {
        String substring = searchPanel.getText();
        namePanel.setText("");
        idPanel.setText("");
        boolean foundOne = false;
        String newLine = "\n";
        for(int x = 0; x < itemName.length && itemName[x] != null; x++)
        {
            if(!itemName[x].toLowerCase().contains(substring.toLowerCase()))
            {
                continue;
            }
            if(foundOne)
            {
                namePanel.append((new StringBuilder()).append(newLine).append(itemName[x]).toString());
                idPanel.append((new StringBuilder()).append(newLine).append(itemID[x]).toString());
            } else
            {
                foundOne = true;
                namePanel.append(itemName[x]);
                idPanel.append(itemID[x]);
            }
        }

        if(namePanel.getText().equals(""))
        {
            namePanel.setText("No Results Found");
        }
    }

    public void actionPerformed(ActionEvent evt)
    {
        search();
    }

    public void keyPressed(KeyEvent evt)
    {
        if(evt.getKeyCode() == 10)
        {
            search();
        }
    }

    public void keyReleased(KeyEvent evt)
    {
        if(evt.getKeyCode() == 10)
        {
            searchPanel.setText(searchPanel.getText().replace("\n", ""));
        }
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }
}