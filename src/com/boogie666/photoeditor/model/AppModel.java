package com.boogie666.photoeditor.model;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AppModel {
	private Stack<BufferedImage> images;
	private List<AppModelListener> updateListeners;
	
	public AppModel(BufferedImage image) {
		this.images = new Stack<BufferedImage>();
		this.images.push(image);
		this.updateListeners = new ArrayList<AppModelListener>();
	}
	
	public void crop(Rectangle rect) {
		BufferedImage image = this.images.peek();
		BufferedImage nextImage = new BufferedImage((int) rect.getWidth(), (int)rect.getHeight(), image.getType());
		
		int x = 0;
		
		for(int i = rect.x; i < rect.getWidth() + rect.x; i++, x++) {

			int y = 0;
			for(int j = rect.y; j < rect.getHeight() + rect.y; j++, y++) {
				nextImage.setRGB(x,y, image.getRGB(i, j));
			}
		}
		this.images.push(nextImage);
		fireUpdate();
	}
	
	public void ligthen() {		
		BufferedImage image = this.images.peek();
		BufferedImage nextImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		
		for(int i = 0; i < image.getWidth(); i++) {
			for(int j = 0; j < image.getHeight(); j++) {
				Color c = new Color(image.getRGB(i, j));
				
				Color newC = c.brighter();
				
				nextImage.setRGB(i,j, newC.getRGB());
			}
		}
		this.images.push(nextImage);
		fireUpdate();
	}
	
	public void grayscale() {
		BufferedImage image = this.images.peek();
		BufferedImage nextImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		
		for(int i = 0; i < image.getWidth(); i++) {
			for(int j = 0; j < image.getHeight(); j++) {
				Color c = new Color(image.getRGB(i, j));
				int g = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
				Color newColor = new Color(g,g,g);
				nextImage.setRGB(i,j, newColor.getRGB());
			}
		}
		this.images.push(nextImage);
		fireUpdate();
	}
	
	public BufferedImage getImage() {
		return images.peek();
	}
	
	private void fireUpdate() {
		for(AppModelListener up: this.updateListeners) {
			up.onUpdate();
		}
	}

	public void addUpdateListener(AppModelListener updateListener) {
		this.updateListeners.add(updateListener);
	}

	public void undo() {
		if(!canUndo()) {
			return;
		}
		this.images.pop();
		fireUpdate();
	}

	public boolean canUndo() {
		return this.images.size() > 1;
	}
	
	
}
