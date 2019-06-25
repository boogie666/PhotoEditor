package com.boogie666.photoeditor.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class CropView extends Component {
	private BufferedImage image;
	
	private Point p1;
	private Point p2;

	private CropViewListener cropViewListener;
	
	public CropView() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Click!!!");
				if(p1 == null) {
					p1 = new Point(e.getX(), e.getY());
					repaint();
					return;
				}
				if(p2 == null) {
					p2 = new Point(e.getX(), e.getY());
					cropViewListener.onCrop(new Rectangle(p1, new Dimension(p2.x - p1.x, p2.y - p1.y)));
					repaint();
					p2 = null;
					p1 = null;
					return;
				}
			}
		});
	}
	
	public void setImage(BufferedImage image) {
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
		
		if(p1 !=null) {
			g.setColor(Color.RED);
			g.fillOval(p1.x, p1.y, 10, 10);
		}
		
		if(p2 !=null) {
			g.setColor(Color.RED);
			g.fillOval(p2.x, p2.y, 10, 10);
		}
		
	}

	public void setListener(CropViewListener cropViewListener) {
		this.cropViewListener = cropViewListener;
	}
}
