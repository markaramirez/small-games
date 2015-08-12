package com.mr.birffday;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Results implements Screen{

	final BDAY game;
	OrthographicCamera camera;
	Texture screenImage;
	BitmapFont rps;
	String outcome = "";
	public static int cor;
	
	public Results(final BDAY gam) {
		game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640, 480);
		screenImage = new Texture(Gdx.files.internal("resbg.png"));
		rps = new BitmapFont(Gdx.files.internal("white.fnt"));
		if(cor == 10)
		{
			outcome = "WIN!";
		}
		else
		{
			outcome = "LOSE!";
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.batch.draw(screenImage, 0, 0);
		rps.draw(game.batch, "Correct:" + cor + "0%", 160, 240);
		rps.draw(game.batch, "YOU " + outcome, 160, 210);
		rps.draw(game.batch, "CLICK TO PLAY AGAIN!", 160, 180);
		game.batch.end();
		if (Gdx.input.isTouched())
		{
				game.setScreen(new MainScreen(game));
				dispose();
		}
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		
		
	}

}
