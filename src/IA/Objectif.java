package IA;

import java.awt.Point;

public class Objectif {
	private int type;
	private Point position;
	
	public Objectif(int type, Point position) {
		this.type = type;
		this.position = position;
	}
	
	public int type() {
		return this.type;
	}
	
	public Point position() {
		return this.position;
	}
}
