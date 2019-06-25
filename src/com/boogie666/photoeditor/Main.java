package com.boogie666.photoeditor;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.boogie666.photoeditor.controllers.GrayScaleController;
import com.boogie666.photoeditor.controllers.LigthenController;
import com.boogie666.photoeditor.controllers.UndoController;
import com.boogie666.photoeditor.model.AppModel;
import com.boogie666.photoeditor.model.AppModelListener;
import com.boogie666.photoeditor.views.CropView;
import com.boogie666.photoeditor.views.CropViewListener;
import com.boogie666.photoeditor.views.ImageView;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedImage image = ImageIO.read(new File("/home/boogie666/Pictures/captain_obvious.jpg"));
		
		AppModel model = new AppModel(image);
		
		JFrame f = new JFrame();
		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageView iv = new ImageView();
		iv.setImage(image);
		
		CropView cv = new CropView();
		cv.setListener(new CropViewListener() {
			
			@Override
			public void onCrop(Rectangle rect) {
				System.out.println(rect);
				model.crop(rect);
			}
		});
		cv.setImage(image);
		
		f.add(cv);
		
		JMenuItem undo = new JMenuItem();
		undo.setAction(new UndoController(model));
		undo.setEnabled(model.canUndo());
		model.addUpdateListener(new AppModelListener() {
			@Override
			public void onUpdate() {
				undo.setEnabled(model.canUndo());
				iv.setImage(model.getImage());
				cv.setImage(model.getImage());
			}
		});
		
		JButton b = new JButton("GrayScale");
		b.addActionListener(new GrayScaleController(model));
		f.add(b);
		
		JButton crop = new JButton("Crop");
		crop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.crop(new Rectangle(100, 100, 70, 70));
			}
		});
		f.add(crop);
		
		
		JButton b2 = new JButton("Ligthen");
		b2.addActionListener(new LigthenController(model));
		f.add(b2);
		
//		f.add(iv);
		
		JMenuBar mb = new JMenuBar();
		JMenu edit = new JMenu();
		edit.setText("Edit");
		
		JMenuItem grayscale = new JMenuItem();
		grayscale.setAction(new GrayScaleController(model));
		
		JMenuItem lighten = new JMenuItem();
		lighten.setAction(new LigthenController(model));
		
		edit.add(undo);
		edit.add(grayscale);
		edit.add(lighten);
		mb.add(edit);
		
		f.setJMenuBar(mb);
		
		f.setVisible(true);
	}

}
