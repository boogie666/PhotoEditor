package com.boogie666.photoeditor.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import com.boogie666.photoeditor.model.AppModel;

public class GrayScaleController extends AbstractAction implements ActionListener{
	
	private AppModel model;
	public GrayScaleController(AppModel model) {
		super("Grayscale");
		this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
		
		this.model = model;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		model.grayscale();
	}

}
