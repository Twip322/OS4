import java.util.ArrayList;
import java.util.Objects;

import javax.swing.tree.DefaultMutableTreeNode;

public class fileManager {
	private File rootFile = new File("root",null,true);
	private File selected = rootFile;
	private File forCopy;
	private File forMove;
	private DefaultMutableTreeNode treeFile = new DefaultMutableTreeNode(rootFile);
	private DefaultMutableTreeNode selectedNodeTree = treeFile;
	private WorkPM wPMemory;
	
	public fileManager(WorkPM wPMemory) {
		this.wPMemory = wPMemory;
		rootFile.setSize(1);
		wPMemory.allocateMemoryForFile(rootFile);
	}

	public File getRootFile() {
		return rootFile;
	}

	public void setRootFile(File rootFile) {
		this.rootFile = rootFile;
	}

	public File getSelected() {
		return selected;
	}

	public void setSelected(File selected) {
		this.selected = selected;
	}

	public File getForCopy() {
		return forCopy;
	}

	public void setForCopy(File forCopy) {
		this.forCopy = forCopy;
	}

	public File getForMove() {
		return forMove;
	}

	public void setForMove(File forMove) {
		this.forMove = forMove;
	}

	public DefaultMutableTreeNode getTreeFile() {
		return treeFile;
	}

	public void setTreeFile(DefaultMutableTreeNode treeFile) {
		this.treeFile = treeFile;
	}

	public DefaultMutableTreeNode getSelectedNodeTree() {
		return selectedNodeTree;
	}

	public void setSelectedNodeTree(DefaultMutableTreeNode selectedNodeTree) {
		this.selectedNodeTree = selectedNodeTree;
		UpdateSelect();
	}

	private void UpdateSelect() {
		this.selected = (File) selectedNodeTree.getUserObject();	
	}

	public File Copy() {
		return forCopy = selected;		
	}
	
	public boolean paste() {
		if(selected.getFolder()) {
			try {
				File newFile = forCopy.clone();
				newFile.setParrent(selected);
				selected.getChilds().add(newFile);
				wPMemory.allocateMemoryForFile(newFile);
				if (newFile.getFolder()) {
					copyFiles(newFile);
				}				
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
		
	}
	
	public void copyFiles(File newFile) {
		for (File file : newFile.getChilds()) {
			wPMemory.allocateMemoryForFile(file);
			if(file.getFolder()) {
				copyFiles(file);
			}
		}
	}
	
	public void startDelForlder() {
		wPMemory.clearMemory(selected);
		delForder(selected.getChilds());
	}

	public void delForder(ArrayList<File> files) {
		for (File file : files) {
			if(file.getFolder()) {
				delForder(file.getChilds());
			}
			wPMemory.clearMemory(file);
		}
	}
	
	public Boolean CreateNewFile(boolean b,String stringNameFile,int fileSize) {
		if(selected.getFolder()) {
			File newFile = new File(stringNameFile+"",selected,b);	
			if(b) {
				newFile.setSize(1);
			} else {
				newFile.setSize(fileSize);			
			}
			wPMemory.allocateMemoryForFile(newFile);
			selected.getChilds().add(newFile);
			selectedNodeTree.add(new DefaultMutableTreeNode(newFile));
			return true;
		} else {
			return false;
		}
	}
	
	public boolean move() {
		if(Objects.isNull(forMove)) {
			forMove = selected;			
		} else {
			if(selected.getFolder()) {
				forMove.getParrent().getChilds().remove(forMove);
				forMove.setParrent(selected);
				forMove.getParrent().getChilds().add(forMove);
				forMove = null;
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
	
	public boolean delete() {
		if(selected == rootFile) {
			return false;
		} else {
			selected.getParrent().getChilds().remove(selected);
			if (selected.getFolder()) {
				startDelForlder();;
			} else {
				wPMemory.clearMemory(selected);
			}
		}
		return false;
	}
}
