
public class CellTable {
	private File file;
	private int startFile;
	
	public CellTable(File file, int startInMem) {
		this.file = file;
		this.startFile = startInMem;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public int getStartFile() {
		return startFile;
	}

	public void setStartFile(int startFile) {
		this.startFile = startFile;
	}
}
