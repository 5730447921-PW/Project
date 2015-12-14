import javax.swing.JFrame;

import render.Screen;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame("Speial Fruit");
		Screen s = new Screen();
		f.add(s);
		f.setVisible(true);
		f.pack();
	}

}
