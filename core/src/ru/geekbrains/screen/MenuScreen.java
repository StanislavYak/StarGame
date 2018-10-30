package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    private Texture img;

    private Vector2 pos;
    private Vector2 v;
    private Vector2 touch;
    private Vector2 buf;


    @Override
    public void show(){
        super.show();
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        pos = new Vector2(0,0);
        v = new Vector2();
        touch = new Vector2();
        buf = new Vector2();

    }

    @Override
    public void render(float delta){
        super.render(delta);
        Gdx.gl.glClearColor(0.128f, 0.50f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        buf.set(touch);
        if (buf.sub(pos).len() > v.len()) {
            pos.add(v);
        }else{
            pos.set(touch);
        }
        batch.begin();
        batch.draw(img, pos.x, pos.y, 42f, 42f);
        batch.end();

    }


    @Override
    public  void dispose(){
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        this.touch = touch;
        v.set(touch.cpy().sub(pos). scl(0.01f));

        return false;
    }
}
