package gui;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import data.Map;

public class GuiMap extends JPanel {
	
	private Map map;
	private PaintStrategy paintStrategy = new PaintStrategy();

	public GuiMap(Map map) {
		this.map = map;
		setLayout(new BorderLayout());

	}
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		paintStrategy.miniMapPaint(map, g2);
		paintStrategy.paint(map, g);
		paintStrategy.drawInformation(map,g2);
	}
	
	
	
}