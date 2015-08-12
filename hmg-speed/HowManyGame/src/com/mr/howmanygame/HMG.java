package com.mr.howmanygame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class HMG implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture alien, star, swordsman, heart, lightning, five, four, three, two, one, winT, loseT;
	BitmapFont hmgfont;
	Music music;
	boolean qtime;
	boolean win;
	boolean lose;
	boolean doneqing;
	boolean answered;
	long time;
	int numofalien;
	int numofsword;
	int numofstar;
	int numofheart;
	int numoflight;
	String question = "";
	int guess;
	int answer;
	int chooser;
	
	@Override
	public void create() {		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640, 480);
		batch = new SpriteBatch();
		alien = new Texture(Gdx.files.internal("data/hmgalien.png"));
		swordsman = new Texture(Gdx.files.internal("data/hmgswordsman.png"));
		star = new Texture(Gdx.files.internal("data/hmgstar.png"));
		heart = new Texture(Gdx.files.internal("data/hmgheart.png"));
		lightning = new Texture(Gdx.files.internal("data/hmglightning.png"));
		five = new Texture(Gdx.files.internal("data/hmg5.png"));
		four = new Texture(Gdx.files.internal("data/hmg4.png"));
		three = new Texture(Gdx.files.internal("data/hmg3.png"));
		two= new Texture(Gdx.files.internal("data/hmg2.png"));
		one = new Texture(Gdx.files.internal("data/hmg1.png"));
		winT = new Texture(Gdx.files.internal("data/hmgwin.png"));
		loseT = new Texture(Gdx.files.internal("data/hmglose.png"));
		hmgfont = new BitmapFont(Gdx.files.internal("data/hmgfont.fnt"));
		music = Gdx.audio.newMusic(Gdx.files.internal("data/hmgsong.wav"));
		time = TimeUtils.millis();
		numofheart = MathUtils.random(0, 7);
		numofsword = MathUtils.random(0, 7);
		numofstar = MathUtils.random(0, 7);
		numoflight = MathUtils.random(0, 7);
		numofalien = MathUtils.random(0, 7);
		music.setLooping(true);
		music.play();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	public void question() {
		chooser = MathUtils.random(0, 4);
		if (chooser == 0)
		{
			answer = numofheart;
			question = "How many hearts were there?";
		}
		if (chooser == 1)
		{
			answer = numofstar;
			question = "How many stars were there?";
		}
		if (chooser == 2)
		{
			answer = numofsword;
			question = "How many swordsman were there?";
		}
		if (chooser == 3)
		{
			answer = numoflight;
			question = "How many lightning bolts were there?";
		}
		if (chooser == 4)
		{
			answer = numofalien;
			question = "How many aliens were there?";
		}
	}
	
	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		if (!doneqing)
		{
			if (TimeUtils.millis() - time > 5000)
			{
				qtime = true;
				question();
				doneqing = true;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_0))
		{
			guess = 0;
			answered = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1))
		{
			guess = 1;
			answered = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_2))
		{
			guess = 2;
			answered = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_3))
		{
			guess = 3;
			answered = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_4))
		{
			guess = 4;
			answered = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_5))
		{
			guess = 5;
			answered = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_6))
		{
			guess = 6;
			answered = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_7))
		{
			guess = 7;
			answered = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_8))
		{
			guess = 8;
			answered = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_9))
		{
			guess = 9;
			answered = true;
		}
		if (answered)
		{
			qtime = false;
			if (guess == answer)
			{
				win = true;
			}
			else
			{
				lose = true;
			}
		}
		batch.begin();
		if (!qtime && !answered)
		{
			for (int i = 0; i < numofheart;i++)
			{
				batch.draw(heart, 10, 0 + i * 70);
			}
			for (int j = 0; j < numofstar;j++)
			{
				batch.draw(star, 110, 0 + j * 70);
			}
			for (int p = 0; p < numofsword;p++)
			{
				batch.draw(swordsman, 210, 0 + p * 70);
			}
			for (int k = 0; k < numofalien;k++)
			{
				batch.draw(alien, 310, 0 + k * 70);
			}
			for (int h = 0; h < numoflight;h++)
			{
				batch.draw(lightning, 410, 0 + h * 70);
			}
			if (TimeUtils.millis() - time < 1000)
			{
				batch.draw(five, 510, 240);
			}
			if (TimeUtils.millis() - time > 1000 && TimeUtils.millis() - time < 2000)
			{
				batch.draw(four, 510, 240);
			}
			if (TimeUtils.millis() - time > 2000 && TimeUtils.millis() - time < 3000)
			{
				batch.draw(three, 510, 240);
			}
			if (TimeUtils.millis() - time > 3000 && TimeUtils.millis() - time < 4000)
			{
				batch.draw(two, 510, 240);
			}
			if (TimeUtils.millis() - time > 4000 && TimeUtils.millis() - time < 5000)
			{
				batch.draw(one, 510, 240);
			}
		}
		if (qtime)
		{
			hmgfont.draw(batch, question, 0, 400);
			hmgfont.draw(batch, "Answer with the number keys on your keyboard!", 0, 300);
		}
		if (win)
		{
			batch.draw(winT, 0 ,0);
		}
		if (lose)
		{
			batch.draw(loseT, 0 , 0);
		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
