import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyJPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	WorkPM wPMemory;
	
	public MyJPanel(WorkPM wfizMemory) {
		this.wPMemory = wfizMemory;
	}
	
	@Override
	public void paint(Graphics g) {
		int sizeX = this.getWidth()/wPMemory.getSizePaintSectors();
		int sizeY = this.getHeight()/wPMemory.getSizePaintSectors()-4;
		int nextSelectSector=wPMemory.getStartSelectedFile();
		super.paint(g);
		int x = 0;
		int y = 0;
		for (int i = 0; i < wPMemory.getPlace().length; i++) {
			if (x+sizeX>=getWidth()) {
				x=0;
				y+=sizeY;
			}
			if(i==nextSelectSector) {
				nextSelectSector = wPMemory.getCell(i);
				g.setColor(Color.red);
			} else if(wPMemory.getCell(i)==0) {
				g.setColor(Color.gray);
			} else {
				g.setColor(Color.blue);
			}
			g.fillRect(x, y, sizeX, sizeY);
			g.setColor(Color.black);
			g.drawRect(x, y, sizeX, sizeY);
			x+=sizeX;
		}		
	}

}
