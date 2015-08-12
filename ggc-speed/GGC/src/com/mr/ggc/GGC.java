package com.mr.ggc;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class GGC implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture backTexture, bombTexture, cheeseTexture, loseTexture, winTexture;
	private Sprite sprite;
	int cxmove = 0;
	int cymove = 0;
	int bxmove = 0;
	int bymove = 0;
	int bombx = 200;
	int bomby = 200;
	int cheesex = 300;
	int cheesey = 300;
	long timetochange = 200;
	long lastCHchange;
	long lastBOchange;
	boolean done = false;
	Music music;
	Sound bounceS, bombS, cheeseS;
	Rectangle bombR = new Rectangle();
	Rectangle cheeseR = new Rectangle();
	Rectangle clickR = new Rectangle();
	Vector3  touchPos = new Vector3();
	
	@Override
	public void create() {		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		bombTexture = new Texture(Gdx.files.internal("data/bomb.png"));
		cheeseTexture = new Texture(Gdx.files.internal("data/ch.png"));
		backTexture = new Texture(Gdx.files.internal("data/ggcback.png"));
		winTexture = new Texture(Gdx.files.internal("data/win.png"));
		loseTexture = new Texture(Gdx.files.internal("data/lose.png"));
		music = Gdx.audio.newMusic(Gdx.files.internal("data/ggcsong.wav"));
		bombS = Gdx.audio.newSound(Gdx.files.internal("data/ggcbomb.wav"));
		cheeseS = Gdx.audio.newSound(Gdx.files.internal("data/ggccheese.wav"));
		bounceS = Gdx.audio.newSound(Gdx.files.internal("data/ggcbounce.wav"));
		music.setLooping(true);
		clickR.width = 1;
		clickR.height = 1;
		clickR.x = 1000;
		clickR.y = 1000;
		bombR.width = 64;
		bombR.height = 64;
		bombR.x = bombx;
		bombR.y = bomby;
		cheeseR.width = 64;
		cheeseR.height = 64;
		cheeseR.x = cheesex;
		cheeseR.y = cheesey;
		music.play();
	}

	public void moveCheese() {
		if (TimeUtils.millis() - lastCHchange >= timetochange)
		{
			cxmove = MathUtils.random(-20, 20);
			cymove = MathUtils.random(-20, 20);
			lastCHchange = TimeUtils.millis();
		}
	}
	
	public void moveBomb() {
		if (TimeUtils.millis() - lastBOchange >= timetochange)
		{
			bxmove = MathUtils.random(-20, 20);
			bymove = MathUtils.random(-20, 20);
			lastBOchange = TimeUtils.millis();
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		moveCheese();
		moveBomb();
		bombx += bxmove;
		bomby += bymove;
		if (!done)
		{
		if (bombx > 640)
		{
			bxmove = -10;
			bounceS.play();
		}
		if (bombx < 0)
		{
			bxmove = 10;
			bounceS.play();
		}
		if (bomby < 0)
		{
			bymove = 10;
			bounceS.play();
		}
		if (bomby > 480)
		{
			bymove = -10;
			bounceS.play();
		}
		if (cheesex > 640)
		{
			cxmove = -10;
			bounceS.play();
		}
		if (cheesex < 0)
		{
			cxmove = 10;
			bounceS.play();
		}
		if (cheesey < 0)
		{
			cymove = 10;
			bounceS.play();
		}
		if (cheesey > 480)
		{
			cymove = -10;
			bounceS.play();
		}
		cheesex += cxmove;
		cheesey += cymove;
		cheeseR.x = cheesex;
		cheeseR.y = cheesey;
		bombR.x = bombx;
		bombR.y = bomby;
		}
		if (Gdx.input.isTouched())
		{
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			clickR.x = touchPos.x;
			clickR.y = touchPos.y;
			if (clickR.overlaps(cheeseR))
			{
				cheeseS.play();
				backTexture = winTexture;
				done = true;
			}
			if (clickR.overlaps(bombR))
			{
				bombS.play();
				backTexture = loseTexture;
				done = true;
			}
		}
		batch.begin();
		batch.draw(backTexture, 0 ,0);
		if (!done)
		{
			batch.draw(bombTexture, bombx, bomby);
			batch.draw(cheeseTexture, cheesex, cheesey);
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
