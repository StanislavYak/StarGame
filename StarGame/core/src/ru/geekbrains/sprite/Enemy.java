package ru.geekbrains.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Ship;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.ExplosionPool;


public class Enemy extends Ship {

    private enum State { DESCENT, FIGHT }


    private Vector2 speed = new Vector2(0, -0.10f);
    private Vector2 v0 = new Vector2();

    private State state;



    public Enemy(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Sound shootSound) {
        super(shootSound);
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
        this.v.set(v0);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);

        switch (state) {
            case DESCENT:
                if (getTop() <= worldBounds.getTop()) {
                    v.set(v0);
                    state = State.FIGHT;
                }
                break;
            case FIGHT:
                reloadTimer += delta;
                if (reloadTimer >= reloadInterval) {
                    shoot();
                    reloadTimer = 0f;
                }
                if (getBottom() < worldBounds.getBottom()) {
                    boom();
                    destroy();
                }
                break;
        }
    }
    public void set(
        TextureRegion[] regions,
        Vector2 v0,
        TextureRegion bulletRegion,
        float bulletHeight,
        float bulletVY,
        int bulletDamage,
        float reloadInterval,
        float height,
        int hp

    ) {
        this.regions = regions;
        this.v0.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0f, bulletVY);
        this.bulletDamage = bulletDamage;
        this.reloadInterval = reloadInterval;
        this.hp = hp;
        this.reloadTimer = reloadInterval;
        setHeightProportion(height);
        v.set(speed);
        state = State.DESCENT;
    }

    public boolean isBulletCollision(Rect bullet){
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < pos.y
        );
    }

    @Override
    public void destroy(){
        boom();
        hp = 0;
        super.destroy();
    }

}
