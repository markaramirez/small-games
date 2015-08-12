package com.mr.birffday;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

public class MainScreen implements Screen {
	
	final BDAY game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture backTexture;
	Stage stage;
	Table table;
	TextButton Abutton, Bbutton, Cbutton;
	Skin skin;
	BitmapFont rps;
	TextureAtlas atlas;
	Sound click;
	Music music;
	int level = 1;
	int numcorrect = 0;
	String question = "";
	String A = "";
	String B = "";
	String C = "";
	
	public MainScreen(final BDAY gam) {
		game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640, 480);
		batch = new SpriteBatch();
		backTexture = new Texture(Gdx.files.internal("bgbd.png"));
		music = Gdx.audio.newMusic(Gdx.files.internal("badbday.wav"));
		click = Gdx.audio.newSound(Gdx.files.internal("bdayselect.wav"));
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		rps = new BitmapFont(Gdx.files.internal("white.fnt"));
		atlas = new TextureAtlas("button.pack");
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
		Abutton = new TextButton("A", textButtonStyle); 
		Abutton.pad(15);
		Abutton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				click.play();
				if(level==5){numcorrect++;}
				if(level==7){numcorrect++;}
				if(level==10){numcorrect++;}
				level++;
			}
		});
		Cbutton = new TextButton("C", textButtonStyle); 
		Cbutton.pad(15);
		Cbutton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				click.play();
				if(level==1){numcorrect++;}
				if(level==4){numcorrect++;}
				if(level==8){numcorrect++;}
				level++;
			}
		});
		Bbutton = new TextButton("B", textButtonStyle); 
		Bbutton.pad(15);
		Bbutton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				click.play();
				if(level==2){numcorrect++;}
				if(level==3){numcorrect++;}
				if(level==6){numcorrect++;}
				if(level==9){numcorrect++;}
				level++;
			}
		});
		table.bottom();
		table.add(Abutton);
		table.getCell(Abutton).spaceRight(15);
		table.add(Bbutton);
		table.getCell(Bbutton).spaceRight(15);
		table.add(Cbutton);
		stage.addActor(table);
		music.setLooping(true);
		music.play();
	}

	@Override
	public void dispose() {
		music.dispose();
		click.dispose();
		backTexture.dispose();
	}	
	
	public void drawWhat() {
		if(level == 1)
		{
			question = "Pick the best catch-phrase.";
			A = "A) I love Briana.";
			B = "B) I love Briana!";
			C = "C) I really love Briana with all my heart!";
		}
		if(level == 2)
		{
			question = "Briana is ";
			A = "A) silly.";
			B = "B) perfect.";
			C = "C) dumb.";
		}
		if(level == 3)
		{
			question = "What is the most pleasant thing to look at?";
			A = "A) kittens";
			B = "B) Briana's face";
			C = "C) sunsets behind waterfalls";
		}
		if(level == 4)
		{
			question = "Briana's IQ is around ";
			A = "A) 50";
			B = "B) 100";
			C = "C) 5000";
		}
		if(level == 5)
		{
			question = "Name the greatest thing ever.";
			A = "A) Briana";
			B = "B) Videogames";
			C = "C) Ice Cream";
		}
		if(level == 6)
		{
			question = "What sound will instantly cause infatuation?";
			A = "A) BattleBlock Theater's Soundtrack";
			B = "B) Briana's Voice";
			C = "C) A concert F played on a french horn";
		}
		if(level == 7)
		{
			question = "Choose Wisely.";
			A = "A) Briana";
			B = "B) Mark";
			C = "C) Chupa";
		}
		if(level == 8)
		{
			question = "_________::Beautiful, Pizza::Delicious";
			A = "A) Clover";
			B = "B) Lotus";
			C = "C) Briana";
		}
		if(level == 9)
		{
			question = "Best amount of time to spend with Briana White?";
			A = "A) A couple of hours";
			B = "B) Forever";
			C = "C) About a week";
		}
		if(level == 10)
		{
			question = "Rate Briana on a scale of 1-10.";
			A = "A) Infinity";
			B = "B) 9";
			C = "C) 8";
		}
	}
	
	@Override
	public void render(float delta) {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		stage.act(); 
		if(level > 10)
		{
			Results.cor = numcorrect;
			game.setScreen(new Results(game));
			dispose();
		}
		drawWhat();
		batch.begin();
		batch.draw(backTexture, 0 , 0);
		rps.draw(batch, "Question " + level + "/10", 450, 470);
		rps.draw(batch, "Correct: " + numcorrect, 450, 440);
		rps.draw(batch, question, 20, 400);
		rps.draw(batch, A, 20, 300);
		rps.draw(batch, B, 20, 250);
		rps.draw(batch, C, 20, 200);
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

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
