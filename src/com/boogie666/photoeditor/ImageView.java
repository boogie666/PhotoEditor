package com.boogie666.photoeditor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageView extends Component{
	
	private BufferedImage image;
	
	public void setImage(BufferedImage image){
		this.image = image;
		this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
		this.setSize(new Dimension(image.getWidth(), image.getHeight()));
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		if(image != null) {
			g.drawImage(image, 0, 0, null);
		}
	}

}
