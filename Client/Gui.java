import javax.swing.*;
import java.awt.*;
import java.lang.String;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import sign.signlink;
import java.awt.*;
import java.awt.event.*;


@SuppressWarnings({ "unused", "serial" })
public class Gui extends client implements ActionListener, FocusListener {

	public int frameTheme = 1;
	public static void main(String args[]) {
		new Gui(args);
	}
	public Gui(String args[]) {
		super();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			sign.signlink.startpriv(InetAddress.getByName("127.0.0.1"));
			initUI();
		} catch(Exception ex)	{
			ex.printStackTrace();
		}
	}

 public void launchURL(String s)
    {
        String s1 = System.getProperty("os.name");
        try
        {
            
            if(s1.startsWith("Windows"))
            {
                Runtime.getRuntime().exec((new StringBuilder()).append("rundll32 url.dll,FileProtocolHandler ").append(s).toString());
            } else
            {
                String as[] = {
                    "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape"
                };
                String s2 = null;
                for(int i = 0; i < as.length && s2 == null; i++)
                    if(Runtime.getRuntime().exec(new String[] {
    "which", as[i]
}).waitFor() == 0)
                        s2 = as[i];

                if(s2 == null)
                    throw new Exception("Could not find web browser");
                Runtime.getRuntime().exec(new String[] {
                    s2, s
                });
            }
        }
        catch(Exception exception)
        {
            System.out.println("An error occured while trying to open the web browser!\n");
        }
    }
	
	private void initUI()  {
		try  {
			JFrame.setDefaultLookAndFeelDecorated(true);
			JPopupMenu.setDefaultLightWeightPopupEnabled(false);
			frame = new JFrame(frameTitle);
			
			
			frame.setLayout(new BorderLayout());
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel gamePanel = new JPanel();
			gamePanel.setLayout(new BorderLayout());
			gamePanel.add(this);
			gamePanel.setPreferredSize(new Dimension(765, 503));
			JCheckBox CheckButton;
			JMenu fileMenu = new JMenu("Menu");
			
			String[] mainButtons = new String[]
				{
					"Vote", "Forums", "-", "Exit"
				};
				
			for (String name : mainButtons) 
			{
				JMenuItem menuItem = new JMenuItem(name);
				if (name.equalsIgnoreCase("-"))
					fileMenu.addSeparator();
				else 
				{
					menuItem.addActionListener(this);
					fileMenu.add(menuItem);
				}
			}
			
			JMenuBar menuBar = new JMenuBar();
			JMenuBar jmenubar = new JMenuBar();
			frame.add(jmenubar);
			frame.getContentPane().add(jmenubar, "South");
			menuBar.add(fileMenu);
			
			frame.getContentPane().add(menuBar, BorderLayout.NORTH);
			frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
			frame.pack();

			frame.setVisible(true);
            frame.setResizable(false);
			init();
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
    public void WorldSelect() {
		try
		{
		   String s1 = JOptionPane.showInputDialog(this, (new StringBuilder()).append("Current server:"), "Enter Server", 3);
		   if(s1 == null){
				System.out.println("Canceled");
		   } else if(s1.equalsIgnoreCase("")) {
			   System.out.println("Sorry, have to have some input");
		   } else {
			   System.out.println((new StringBuilder()).append("Set world to: ").append(s1).toString());
		   }
		} catch(Exception e) {
			   System.out.println((new StringBuilder()).append("You must enter a numeric value!: ").append(e).toString());
			}
		}
		
	public static void Launch(String launch) {
		String operatingSystem = System.getProperty("os.name");
		try {
			if(operatingSystem.startsWith("Windows")) {
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+launch);
			} else {
		}
		} catch(Exception exception) {
			System.err.println("Error launching url.");
			exception.printStackTrace();
		}
	}

	public URL getCodeBase() {
		try {
			return new URL("http://insentium.no-ip.org/cache");
		} catch (Exception e) {
			return super.getCodeBase();
		}
	}

	public URL getDocumentBase() {
		return getCodeBase();
	}
	
	public void loadError(String s) {
		System.out.println("loadError: " + s);
	}
	
	public String getParameter(String key) {
		if (key.equals("nodeid"))
			return "10";
		else if (key.equals("portoff"))
			return "0";
		else if (key.equals("lowmem"))
			return "1";
		else if (key.equals("free"))
			return "0";
		else
			return "";
	}

//ACTIONS DONE
	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		if (cmd != null) {
			if (cmd.equalsIgnoreCase("Forums")) {
launchURL("neriops.createaforum.com");
			}
			if (cmd.equalsIgnoreCase("Vote")) {
launchURL("http://topg.org/server-nerio-id456360");
				 }
			if (cmd.equalsIgnoreCase("Exit")) {
				int i1;
				if((i1 = JOptionPane.showConfirmDialog(this, "Do you really want to exit the client?")) == 0)
					System.exit(0);
				return;
			}

		}



	}
    private static boolean isApplet = false;
    public int theme;
    protected static JTextArea textArea;
    protected JScrollPane scrollPane;
    private static final String newline = "\n";
    public static String browserPath = "C:/Program Files/Internet Explorer/iexplore.exe";
    public static String url = "";
	private JTabbedPane jTabbedPane1;
	private String frameTitle = "Nerio";
	public JFrame frame;
}