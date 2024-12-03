package test.unit;

import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.junit.Test;

import config.GuiData;

public class TestGuiElements {

	@Test
	public void creationOfWindow() {
		JFrame jFrame = new JFrame();
		jFrame.setSize(300,300);
		jFrame.setTitle("test");
		JTextArea jTextArea = new JTextArea(GuiData.FOREST_TEXT);
		jTextArea.setEnabled(false);
		jTextArea.setForeground(Color.WHITE);
		jTextArea.setLineWrap(true);
		jTextArea.setBackground(Color.BLACK);
		jFrame.add(jTextArea);
		assertTrue(jFrame instanceof JFrame);
	}

}
