package com.boogie666.photoeditor.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.boogie666.photoeditor.model.AppModel;

public class LigthenController implements ActionListener {
	
	private AppModel model;
	public LigthenController(AppModel model) {
		this.model = model;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		model.ligthen();
	}

}
