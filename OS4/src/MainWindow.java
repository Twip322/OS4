import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow{

	private JFrame frame;
	private JTextField txtName;
	private JTree tree;
	private JTextField textConsol;
	private JTextField textFieldSizeDisc;
	private JTextField textSizeSector;
	private JButton btnMove;
	private JButton buttonCreateFolder;
	private JButton buttonCopy;
	private JButton buttonPaste;
	private JButton btnCreateFile;
	private JButton btnDelete;
	MyJPanel panel;
	WorkPM PMemory;
	BlocksForFile blf;
	private fileManager fileManager;
	private JTextField textFieldSizeFile;
	private JLabel lblSizeFile;
	private DefaultMutableTreeNode treeFile;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 929, 579);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.getContentPane().setLayout(null);
		txtName = new JTextField();
		txtName.setEnabled(false);
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setText("new");
		txtName.setBounds(263, 11, 86, 20);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setEnabled(false);
		lblName.setBounds(187, 14, 46, 14);
		frame.getContentPane().add(lblName);
		
		buttonCreateFolder = new JButton("Create folder");
		buttonCreateFolder.setEnabled(false);
		buttonCreateFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileManager.CreateNewFile(true,txtName.getText(),Integer.parseInt(textFieldSizeFile.getText()));
				startUpdateTree(fileManager.getRootFile().getChilds());
				textConsol.setText("Create");
				panel.repaint();
			}
		});
		buttonCreateFolder.setBounds(184, 68, 165, 23);
		frame.getContentPane().add(buttonCreateFolder);
		
		textConsol = new JTextField();
		textConsol.setEnabled(false);
		textConsol.setBounds(179, 247, 169, 20);
		frame.getContentPane().add(textConsol);
		textConsol.setColumns(10);
		
		buttonCopy = new JButton("Copy");
		buttonCopy.setEnabled(false);
		buttonCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textConsol.setText("Copy: "+fileManager.Copy());
			}
		});
		buttonCopy.setBounds(184, 110, 165, 23);
		frame.getContentPane().add(buttonCopy);
		
		buttonPaste = new JButton("Paste");
		buttonPaste.setEnabled(false);
		buttonPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					fileManager.paste();
					startUpdateTree(fileManager.getRootFile().getChilds());
					textConsol.setText("Complete paste");
					panel.repaint();
			}
		});
		buttonPaste.setBounds(184, 144, 165, 23);
		frame.getContentPane().add(buttonPaste);
		
		btnCreateFile = new JButton("Create file");
		btnCreateFile.setEnabled(false);
		btnCreateFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fileManager.CreateNewFile(false,txtName.getText(),Integer.parseInt(textFieldSizeFile.getText()));
				startUpdateTree(fileManager.getRootFile().getChilds());
				textConsol.setText("Create");
				panel.repaint();
			}
		});
		btnCreateFile.setBounds(184, 42, 165, 23);
		frame.getContentPane().add(btnCreateFile);
		
		
		btnMove = new JButton("Move");
		btnMove.setEnabled(false);
		btnMove.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					fileManager.setForMove(null);
					startUpdateTree(fileManager.getRootFile().getChilds());
				}
			});
		
		btnMove.setBounds(179, 179, 170, 23);
		frame.getContentPane().add(btnMove);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					fileManager.delete();
					startUpdateTree(fileManager.getRootFile().getChilds());
					panel.repaint();
					
			}
		});
		btnDelete.setBounds(189, 213, 160, 23);
		frame.getContentPane().add(btnDelete);
		
		JButton btnSearchForBlocks = new JButton("Blocks");
		btnSearchForBlocks.setEnabled(false);
		btnSearchForBlocks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BlocksForFile bff=new BlocksForFile(PMemory);
				int[] bl=bff.getBlocks();
				for(int i=0;i<bl.length;i++)
				{
					System.out.println(bl[i]);
				}
			}
		});
		btnSearchForBlocks.setBounds(187, 402, 162, 23);
		frame.getContentPane().add(btnSearchForBlocks);
		
		textFieldSizeDisc = new JTextField();
		textFieldSizeDisc.setText("1024");
		textFieldSizeDisc.setBounds(263, 278, 86, 20);
		frame.getContentPane().add(textFieldSizeDisc);
		textFieldSizeDisc.setColumns(10);
		
		textSizeSector = new JTextField();
		textSizeSector.setText("4");
		textSizeSector.setBounds(263, 305, 86, 20);
		frame.getContentPane().add(textSizeSector);
		textSizeSector.setColumns(10);
		
		JLabel lblSizedisk = new JLabel("size disk");
		lblSizedisk.setEnabled(false);
		lblSizedisk.setBounds(187, 281, 46, 14);
		frame.getContentPane().add(lblSizedisk);
		
		JLabel lblSizeSector = new JLabel("size sector");
		lblSizeSector.setEnabled(false);
		lblSizeSector.setBounds(187, 308, 66, 14);
		frame.getContentPane().add(lblSizeSector);
		
		JButton btnGenerateDisc = new JButton("Set memory");
		btnGenerateDisc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldSizeDisc.setEditable(false);;
				textSizeSector.setEditable(false);
				btnGenerateDisc.setEnabled(false);
				btnCreateFile.setEnabled(true);
				btnDelete.setEnabled(true);
				btnMove.setEnabled(true);
				buttonCopy.setEnabled(true);
				buttonCreateFolder.setEnabled(true);
				buttonPaste.setEnabled(true);
				txtName.setEnabled(true);
				btnSearchForBlocks.setEnabled(true);
				PMemory = new WorkPM(Integer.parseInt(textFieldSizeDisc.getText()),Integer.parseInt(textSizeSector.getText()));
				PMemory.setStartSelectedFile(0);
				panel = new MyJPanel(PMemory);
				panel.setBounds(359, 11, 544, 518);
				frame.getContentPane().add(panel);
				fileManager= new fileManager(PMemory);
				panel.repaint();
				startUpdateTree(fileManager.getRootFile().getChilds());
			}
		});
		btnGenerateDisc.setBounds(179, 336, 170, 23);
		frame.getContentPane().add(btnGenerateDisc);
		
		textFieldSizeFile = new JTextField();
		textFieldSizeFile.setText("10");
		textFieldSizeFile.setBounds(263, 371, 86, 20);
		frame.getContentPane().add(textFieldSizeFile);
		textFieldSizeFile.setColumns(10);
		
		lblSizeFile = new JLabel("Size File:");
		lblSizeFile.setBounds(199, 366, 54, 31);
		frame.getContentPane().add(lblSizeFile);
		
	
	}

	

	protected void startUpdateTree(ArrayList<File> childs) {
		treeFile = new DefaultMutableTreeNode(fileManager.getRootFile());
		updateTree(treeFile,childs);
		if(!Objects.isNull(tree)) {
			frame.getContentPane().remove(tree);
		}
		tree = new JTree(treeFile);
		tree.setEnabled(true);
		tree.setShowsRootHandles(true);
		tree.setBounds(0, 0, 169, 529);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				fileManager.setSelectedNodeTree((DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent());
				PMemory.setStartSelectedFile(fileManager.getSelected().getStartInMem());
				panel.repaint();
				System.out.println(fileManager.getSelected());
			}
		});
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(tree);
		tree.updateUI();
		tree.setScrollsOnExpand(true);
	}
	
	private void updateTree(DefaultMutableTreeNode treeFile, ArrayList<File> childs) {
		
		for (File file : childs) {
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(file);
			treeFile.add(newNode);
			if(file.getFolder()) {
				updateTree(newNode, file.getChilds());
			}
		}	
	}
}