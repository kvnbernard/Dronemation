package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import config.GuiData;
import config.SimulationParameters;
import data.Element;
import data.Forest;
import data.Map;
import data.Position;
import process.VisionManager;

public class PaintStrategy {
	private int sizeOfRect = 0;
	private Forest[] forest;
	private Position key;
    private Position cornerPosition;
    private int nbForet=0;

	
	public void miniMapPaint(Map map, Graphics2D g) {
		// recupreation of the vision by drone
		g.setColor(Color.BLACK);
		g.fillRect(GuiData.WINDOW_WIDTH+6,0, 20, 500);
				int y = map.getDrone().getPosition().getColumn();
				int x = map.getDrone().getPosition().getLine();
				VisionManager visionManager =  new VisionManager(map, map.getDrone());
				Element[][] droneVision = map.getElementsInMap();
				for (int line = 0; line < droneVision.length; line++) {
					for (int columb = 0; columb < droneVision.length; columb++) {
						if(droneVision[line][columb]!=null) {
							g.setColor(droneVision[line][columb].getColor());
							Rectangle2D.Double rect = new Rectangle2D.Double(line+442, columb, 0.1, 0.1);
							g.fillRect(line+442, columb, 1, 1);
//							g.draw(rect);

							
						}
							g.setColor(Color.BLACK);
							g.drawRect(x+ 442, y , 22, 21);
			                g.setColor(Color.white);

//							g.fillRect(x * 5, y * 5, 5, 5);
//							g.setColor(Color.DARK_GRAY);
//							g.drawLine(0, columb*2, GuiData.WINDOW_HEIGHT, columb*2);
//							g.drawLine(line*2,0, line*2, GuiData.WINDOW_WIDTH);
		                
					}
				}
	}
	
	public void paint(Map map, Graphics g) {
        nbForet=0;
        // recupreation of the vision by drone
        VisionManager visionManager =  new VisionManager(map, map.getDrone());

        visionManager.checkIfDetected(map);
        Element[][] droneVision = map.getVisionDrone();
        cornerPosition= map.getDrone().getPosition();
        for (int line = 0; line < SimulationParameters.NUMBER_OF_HEIGHT_SQUARES; line++) {
            for (int column = 0; column < SimulationParameters.NUMBER_OF_WIDTH_SQUARES; column++) {
                if(droneVision[line][column]!=null) {
                    g.setColor(droneVision[line][column].getColor());
                    g.fillRect(line * GuiData.RECT_SIZE, column * GuiData.RECT_SIZE, GuiData.RECT_SIZE, GuiData.RECT_SIZE);
                    
                }
                g.setColor(Color.DARK_GRAY);
                g.drawLine(0, column*GuiData.RECT_SIZE, GuiData.WINDOW_HEIGHT, column*GuiData.RECT_SIZE);
                g.drawLine(line*GuiData.RECT_SIZE,0, line*GuiData.RECT_SIZE, GuiData.WINDOW_WIDTH+42);
                g.setColor(Color.white);
            }
            
        }
        //forest= map.getDrone().getDetectForest().get(cornerPosition);
      //  int i=0;
//        while(forest[i]!=null) {
//            key=forest[0].getForest().get(1).getPosition();
//            i++;
//            createPopUp();
//        }
        }
	
	 public JFrame createPopUp() {
         JFrame jFrame = new JFrame();
         jFrame.setSize(300,300);
         jFrame.setLocation((key.getLine()-cornerPosition.getLine())*20,(key.getColumn()-cornerPosition.getColumn())*20);
         jFrame.setTitle(GuiData.FOREST_TEXT_TITLE+nbForet);
         JTextArea jTextArea = new JTextArea(GuiData.FOREST_TEXT);
         jTextArea.setEnabled(false);
         jTextArea.setForeground(Color.WHITE);
         jTextArea.setLineWrap(true);
         jTextArea.setBackground(Color.BLACK);
         jFrame.add(jTextArea);
         jFrame.setVisible(true);
         nbForet++;
         return jFrame;
     }

	public void drawInformation(Map map, Graphics2D g2) {
		g2.setColor(Color.white);
		g2.fillRect(0, 439 , 925, 200);
			g2.setColor(Color.black);
			int numberFireDetect = map.getDrone().getNumberFireDetect();
			String number = "Nombres de feux detecte: " +numberFireDetect;
			g2.drawString(number, 5, 455);
			drawString(g2,GuiData.FOREST_TEXT, 15, 465);
			
			
			}
	
	public void drawString(Graphics2D g2, String text, int x, int y) {
	    for (String line : text.split("\n"))
	        g2.drawString(line, x, y += g2.getFontMetrics().getHeight());
	}

}