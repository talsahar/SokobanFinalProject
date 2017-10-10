package controller.command;

import java.util.List;

import controller.Controller;
import model.Model;
import server.MyClient;
import view.View;

public abstract class SokobanCommand implements Command {
	protected List<String> params;
	protected Controller controller;
	protected Model model;
	protected View view;
	protected MyClient client;
	private String key;

	public SokobanCommand(View v, Model m, Controller c, MyClient cli, String key) {
		this.view = v;
		this.model = m;
		this.controller = c;
		this.client = cli;
		this.key = key;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	public String getKey() {
		return key;
	}

}
