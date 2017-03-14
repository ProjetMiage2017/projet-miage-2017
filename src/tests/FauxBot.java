package tests;

import java.awt.Point;

import clientdesarenes.Bot;

public class FauxBot extends Bot {
	int nombreLivres = 0;
	Point position = new Point(4,1);
	int esprit = 100;
	
	public FauxBot(String id, String cle) {
		super(id, cle);
	}
	
	public void setLivres(int l) {
		this.nombreLivres = l;
	}
	
	@Override
	public int nombreLivres() {
		return this.nombreLivres;
	}

	public void setPosition(Point p) {
		this.position = p;
	}
	
	@Override
	public Point donnePosition() {
		return this.position;
	}
	
	public void setEsprit(int e) {
		this.esprit = e;
	}
	
	@Override
	public int donneEsprit() {
		return this.esprit;
	}
}
