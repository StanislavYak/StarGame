package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {
    private SpriteBatch batch;
    private Texture img;

    private Vector2 pos;
    private Vector2 v;
    private Vector2 posEnd;
    private  int y;



    @Override
    public void show(){
        super.show();
        Gdx.input.setInputProcessor(this);
        y = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        pos = new Vector2(0,0);
        v = new Vector2(0, 0);
        posEnd = new Vector2(0,0);

    }

    @Override
    public void render(float delta){
        super.render(delta);
        Gdx.gl.glClearColor(0.128f, 0.50f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
        newPos();
    }

    private void newPos() {
        if (pos.dst(posEnd) > 1) {
            pos.add(v);
        }
    }

    @Override
    public  void dispose(){
        batch.dispose();
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        posEnd.x = screenX;
        posEnd.y = y - screenY;
        v = posEnd.cpy().sub(pos).nor();

        return super.touchDown(screenX, screenY, pointer, button);
    }
}
