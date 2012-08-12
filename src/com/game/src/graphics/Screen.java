package com.game.src.graphics;

import java.util.Random;

public class Screen extends Render {

	private Render test;

	public Screen(int width, int height) {
		super(width, height);
		
		test = new Render(256, 256);
		Random random = new Random();
		
		for (int i = 0; i < 256 * 256; i++) {
			test.pixels[i] = random.nextInt();
		}
	}

	public void render() {
		Draw(test, 0, 0);
		//repaint(WIDTH, WIDTH, WIDTH, HEIGHT);
	}

}
