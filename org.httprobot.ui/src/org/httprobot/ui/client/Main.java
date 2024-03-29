package org.httprobot.ui.client;

import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Event;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.KeyStroke;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.ScrollPaneLayout;

import java.awt.Dimension;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.httprobot.common.definitions.Enums.UiEventType;
import org.httprobot.common.events.UiEventArgs;
import org.httprobot.core.interfaces.IUiListener;
import org.httprobot.ui.common.JScrollPaneXml;
import org.httprobot.ui.common.JTreeXml;
import java.awt.GridLayout;
import java.util.Vector;

public class Main {

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private JPanel jContentPane = null;
	private JMenuBar jJMenuBar = null;
	private JMenu fileMenu = null;
	private JMenu editMenu = null;
	private JMenu helpMenu = null;
	private JMenuItem exitMenuItem = null;
	private JMenuItem aboutMenuItem = null;
	private JMenuItem cutMenuItem = null;
	private JMenuItem copyMenuItem = null;
	private JMenuItem pasteMenuItem = null;
	private JMenuItem saveMenuItem = null;
	private JDialog aboutDialog = null;  //  @jve:decl-index=0:visual-constraint="1119,49"
	private JPanel aboutContentPane = null;
	private JLabel aboutVersionLabel = null;
	private JTabbedPane jTabbedPane_Pantalla = null;
	private JPanel jPanel_Servers = null;
	private JPanel jPanel = null;
	private JPanel jPanel_Config = null;
	private JLabel jLabel_RutaTitle = null;
	private JTextField jTextField_RutaValue = null;
	private JButton jButton_Connect = null;
	private JTabbedPane jTabbedPane_Captures = null;
	private JTabbedPane jTabbedPane_DataViews = null;

	
	private String appConfigScrollPaneXml = null;
	private String serversListScrollPaneXml = null;

	public Main(String app_config, String servers_list)
	{
		this.appConfigScrollPaneXml = app_config;
		this.serversListScrollPaneXml = servers_list;
	}
	
	/**
	 * ui_changed_listeners Listeners
	 */
	private Vector<IUiListener> ui_changed_listeners = new Vector<IUiListener>();  //  @jve:decl-index=0:
	/**
	 * Adds ui_changed_listeners event listener
	 * @param rmlControl
	 */
	public final synchronized void addUiChangedListeners(IUiListener rmlControl)
	{
		ui_changed_listeners.add(rmlControl);
	}	
	/**
	 * Removes ui_changed_listeners event listener
	 * @param rmlControl
	 */
	public final synchronized void removeUiChangedListeners(IUiListener listener)
	{
		ui_changed_listeners.remove(listener);
	}
	/**
	 * Fires event method to parent.
	 * @param UiEventArgs
	 */
	protected final void UiChangedEvent(UiEventArgs e) 
	{
		for (IUiListener listener : ui_changed_listeners) 
		{
			if(listener != null)
			{
				listener.OnUiChanged(this, e);
			}
		}
	}
	
	/**
	 * This method initializes jFrame
	 * 
	 * @return javax.swing.JFrame
	 */
	public JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setJMenuBar(getJJMenuBar());
			jFrame.setSize(1024, 768);
			jFrame.setResizable(true);
			jFrame.setContentPane(getJContentPane());
			jFrame.setTitle("Application");
//			jFrame.setContentPane(jTabbedPane_Pantalla);  
//			jFrame.pack();
		}
		return jFrame;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJTabbedPane_Pantalla(), BorderLayout.CENTER);
			jContentPane.add(getJPanel(), BorderLayout.NORTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFileMenu());
			jJMenuBar.add(getEditMenu());
			jJMenuBar.add(getHelpMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.add(getSaveMenuItem());
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getEditMenu() {
		if (editMenu == null) {
			editMenu = new JMenu();
			editMenu.setText("Edit");
			editMenu.add(getCutMenuItem());
			editMenu.add(getCopyMenuItem());
			editMenu.add(getPasteMenuItem());
		}
		return editMenu;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UiChangedEvent(new UiEventArgs(this, UiEventType.EXIT));
				}
			});
		}
		return exitMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText("About");
			aboutMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog aboutDialog = getAboutDialog();
					aboutDialog.pack();
					Point loc = getJFrame().getLocation();
					loc.translate(20, 20);
					aboutDialog.setLocation(loc);
					aboutDialog.setVisible(true);
				}
			});
		}
		return aboutMenuItem;
	}

	/**
	 * This method initializes aboutDialog	
	 * 	
	 * @return javax.swing.JDialog
	 */
	private JDialog getAboutDialog() {
		if (aboutDialog == null) {
			aboutDialog = new JDialog(getJFrame(), true);
			aboutDialog.setTitle("About");
			aboutDialog.setContentPane(getAboutContentPane());
		}
		return aboutDialog;
	}

	/**
	 * This method initializes aboutContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getAboutContentPane() {
		if (aboutContentPane == null) {
			aboutContentPane = new JPanel();
			aboutContentPane.setLayout(new BorderLayout());
			aboutContentPane.add(getAboutVersionLabel(), BorderLayout.CENTER);
		}
		return aboutContentPane;
	}

	/**
	 * This method initializes aboutVersionLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getAboutVersionLabel() {
		if (aboutVersionLabel == null) {
			aboutVersionLabel = new JLabel();
			aboutVersionLabel.setText("Version 1.0");
			aboutVersionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return aboutVersionLabel;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCutMenuItem() {
		if (cutMenuItem == null) {
			cutMenuItem = new JMenuItem();
			cutMenuItem.setText("Cut");
			cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
					Event.CTRL_MASK, true));
		}
		return cutMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCopyMenuItem() {
		if (copyMenuItem == null) {
			copyMenuItem = new JMenuItem();
			copyMenuItem.setText("Copy");
			copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
					Event.CTRL_MASK, true));
		}
		return copyMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getPasteMenuItem() {
		if (pasteMenuItem == null) {
			pasteMenuItem = new JMenuItem();
			pasteMenuItem.setText("Paste");
			pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
					Event.CTRL_MASK, true));
		}
		return pasteMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSaveMenuItem() {
		if (saveMenuItem == null) {
			saveMenuItem = new JMenuItem();
			saveMenuItem.setText("Save");
			saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					Event.CTRL_MASK, true));
		}
		return saveMenuItem;
	}

	/**
	 * This method initializes jTabbedPane_Pantalla	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane_Pantalla() {
		if (jTabbedPane_Pantalla == null) {
			jTabbedPane_Pantalla = new JTabbedPane();
			//jTabbedPane_Pantalla.setLayout(null);
			//jTabbedPane_Pantalla.setSize(811, 668);
			jTabbedPane_Pantalla.setBounds(0,20, 811, 668);
			jTabbedPane_Pantalla.setName("Config");
			jTabbedPane_Pantalla.setToolTipText("");
			jTabbedPane_Pantalla.addTab("Servers", null, getJPanel_Servers(), "Servers");
			jTabbedPane_Pantalla.addTab("Captures", null, getJTabbedPane_Captures(), "Captures");
			jTabbedPane_Pantalla.addTab("DataViews", null, getJTabbedPane_DataViews(), "DataViews");
			jTabbedPane_Pantalla.addTab("Config", null, getJPanel_Config(), "Config");
			;
		}
		return jTabbedPane_Pantalla;
	}

	/**
	 * This method initializes jPanel_LocalConfig	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_Servers() {
		if (jPanel_Servers == null) 
		{
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(1);
			JTreeXml jTreeXml = new JTreeXml();
			jTreeXml.setRootVisible(false);
			jPanel_Servers = new JPanel();
			//GridBagLayout gbl = new GridBagLayout();
			jPanel_Servers.setLayout(gridLayout);
			//jPanel_Servers.setSize(new Dimension(1011, 663));
			JScrollPaneXml jpxv = new JScrollPaneXml(jPanel_Servers, this.serversListScrollPaneXml);
			jpxv.setLayout(new ScrollPaneLayout());
			jpxv.setTreeXml(jTreeXml);
			jPanel_Servers.add(jpxv, null);
			//gbl.setConstraints(jpxv, gridBagConstraints);
			jPanel_Servers.revalidate();
		}
		return jPanel_Servers;
	}
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			jLabel_RutaTitle = new JLabel();
			jLabel_RutaTitle.setText("Ruta WSDL:");
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(jLabel_RutaTitle, gridBagConstraints);
			jPanel.add(getJTextField_RutaValue(), gridBagConstraints1);
			jPanel.add(getJButton_Connect(), gridBagConstraints2);
		}
		return jPanel;
	}
	/**
	 * This method initializes jTextField_RutaValue	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_RutaValue() {
		if (jTextField_RutaValue == null) {
			jTextField_RutaValue = new JTextField();
			jTextField_RutaValue.setPreferredSize(new Dimension(400, 20));
		}
		return jTextField_RutaValue;
	}
	/**
	 * This method initializes jButton_Connect	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_Connect() {
		if (jButton_Connect == null) {
			jButton_Connect = new JButton();
			jButton_Connect.setText("Connectar");
			jButton_Connect.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					UiChangedEvent(new UiEventArgs(this, UiEventType.CONNECT));
				}
			});
		}
		return jButton_Connect;
	}
	/**
	 * This method initializes jTabbedPane_Captures	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane_Captures() {
		if (jTabbedPane_Captures == null) {
			jTabbedPane_Captures = new JTabbedPane();
			jTabbedPane_Captures.setBackground(Color.GRAY);
		}
		return jTabbedPane_Captures;
	}	
	/**
	 * This method initializes jTabbedPane_DataViews	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane_DataViews() {
		if (jTabbedPane_DataViews == null) {
			jTabbedPane_DataViews = new JTabbedPane();
		}
		return jTabbedPane_DataViews;
	}	
	private JPanel getJPanel_Config()
	{
		if (jPanel_Config == null) {
			jPanel_Config = new JPanel();
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(1);
			JTreeXml jTreeXml = new JTreeXml();
			jTreeXml.setRootVisible(false);
			//GridBagLayout gbl = new GridBagLayout();
			jPanel_Config.setLayout(gridLayout);
			//jPanel_Servers.setSize(new Dimension(1011, 663));
			JScrollPaneXml jpxv = new JScrollPaneXml(jPanel_Config, this.appConfigScrollPaneXml);
			jpxv.setLayout(new ScrollPaneLayout());
			jpxv.setTreeXml(jTreeXml);
			jPanel_Config.add(jpxv, null);
			//gbl.setConstraints(jpxv, gridBagConstraints);
			jPanel_Config.revalidate();
		}
		return jPanel_Config;
	}



//	/**
//	 * Launches this application
//	 */
//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				Main application = new Main();
//				application.getJFrame().setVisible(true);
//			}
//		});
//	}


}
