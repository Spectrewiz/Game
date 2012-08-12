package com.game.src;

import static java.lang.System.out;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.game.src.graphics.Render;
import com.game.src.graphics.Screen;

public class Display extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "Horror";

	private Thread thread;
	private boolean running = false;

	@SuppressWarnings("unused")
	private Render render;
	private Screen screen;

	private BufferedImage img;

	private int[] pixels;

	public Display() {
		// render = new Render(WIDTH, HEIGHT);

		screen = new Screen(WIDTH, HEIGHT);
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
	}

	private void Start() {
		if (running)
			return;
		running = true;

		thread = new Thread(this);
		thread.start();
	}

	@SuppressWarnings("unused")
	private void End() {
		if (!running)
			return;
		running = false;

		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	@Override
	public void run() {
		while (running) {
			tick();
			paint();
		}
	}

	private void tick() {

	}

	private void paint() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.render();

		for (int i = 0; i < WIDTH * HEIGHT; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, HEIGHT * 20, WIDTH * 20, null);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Display game = new Display();
		JFrame frame = new JFrame();
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(TITLE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		game.Start();

		out.println("ello");
	}
}
