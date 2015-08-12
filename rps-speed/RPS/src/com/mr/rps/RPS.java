package com.mr.rps;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class RPS implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture backTexture, loseTexture, winTexture, drawTexture, crock, cpaper, cscissors;
	Stage stage;
	Table table;
	TextButton rock, paper, scissors;
	Skin skin;
	BitmapFont rps;
	TextureAtlas atlas;
	boolean win;
	boolean lose;
	boolean r;
	boolean p;
	boolean s;
	boolean cr;
	boolean cp;
	boolean cs;
	boolean draw;
	Sound click;
	Music music;
	int random;
	
	@Override
	public void create() {		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640, 480);
		batch = new SpriteBatch();
		backTexture = new Texture(Gdx.files.internal("data/RPS.png"));
		winTexture = new Texture(Gdx.files.internal("data/winrps.png"));
		loseTexture = new Texture(Gdx.files.internal("data/loserps.png"));
		drawTexture = new Texture(Gdx.files.internal("data/rpsdraw.png"));
		crock = new Texture(Gdx.files.internal("data/crock.png"));
		cpaper = new Texture(Gdx.files.internal("data/cpaper.png"));
		cscissors = new Texture(Gdx.files.internal("data/cscissors.png"));
		music = Gdx.audio.newMusic(Gdx.files.internal("data/rpssong.wav"));
		click = Gdx.audio.newSound(Gdx.files.internal("data/rpsclick.wav"));
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		rps = new BitmapFont(Gdx.files.internal("data/rpsfont.fnt"));
		atlas = new TextureAtlas("data/button.pack");
		skin = new Skin(atlas);
		table = new Table(skin);
		table.setBounds(0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("buttonUp");
		textButtonStyle.down = skin.getDrawable("buttonDown");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = - 1;
		textButtonStyle.font = rps;
		textButtonStyle.fontColor = Color.BLACK;
		rock = new TextButton("ROCK", textButtonStyle); 
		rock.pad(15);
		rock.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				click.play();
				r = false;
				p = false;
				s = false;
				win = false;
				lose = false;
				draw = false;
				cr = false;
				cp = false;
				cs = false;
				r = true;
				computer();
			}
		});
		scissors = new TextButton("SCISSORS", textButtonStyle); 
		scissors.pad(15);
		scissors.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				click.play();
				r = false;
				p = false;
				s = false;
				win = false;
				lose = false;
				draw = false;
				cr = false;
				cp = false;
				cs = false;
				s = true;
				computer();
			}
		});
		paper = new TextButton("PAPER", textButtonStyle); 
		paper.pad(15);
		paper.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				click.play();
				r = false;
				p = false;
				s = false;
				win = false;
				lose = false;
				draw = false;
				cr = false;
				cp = false;
				cs = false;
				p = true;
				computer();
			}
		});
		table.bottom();
		table.add(rock);
		table.getCell(rock).spaceRight(15);
		table.add(paper);
		table.getCell(paper).spaceRight(15);
		table.add(scissors);
		stage.addActor(table);
		music.setLooping(true);
		music.play();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	public void computer() {
		random = MathUtils.random(0 , 2);
		if (random == 0)
		{
			cr = true;
		}
		if (random == 1)
		{
			cp = true;
		}
		if (random == 2)
		{
			cs = true;
		}
	}
	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		stage.act(); 
		if (cr && s || cp && r || cs && p)
		{
			lose = true;
		}
		if (cs && r || cr && p || cp && s)
		{
			win = true;
		}
		if (cs && s || cr && r || cp && p)
		{
			draw = true;
		}
		batch.begin();
		batch.draw(backTexture, 0 , 0);
		if (win)
		{
			batch.draw(winTexture, 320, 100);
		}
		if (lose)
		{
			batch.draw(loseTexture, 320, 100);
		}
		if (draw)
		{
			batch.draw(drawTexture, 320, 100);
		}
		if (cr)
		{
			batch.draw(crock, 320, 380);
		}
		if (cp)
		{
			batch.draw(cpaper, 320, 380);
		}
		if (cs)
		{
			batch.draw(cscissors, 320, 380);
		}
		batch.end();
		stage.draw();
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
