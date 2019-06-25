package com.boogie666.photoeditor.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.boogie666.photoeditor.model.AppModel;

public class GrayScaleController implements ActionListener {
	
	private AppModel model;
	public GrayScaleController(AppModel model) {
		this.model = model;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		model.grayscale();
	}

}
