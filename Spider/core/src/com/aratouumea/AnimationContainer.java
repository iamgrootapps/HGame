/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aratouumea;

import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;

/**
 *
 * @author Aratouumea
 */
public class AnimationContainer {

    private ArrayList<Texture> textures = new ArrayList<Texture>(8);

    public AnimationContainer(ArrayList<Texture> textures) {
        this.textures = textures;
    }
    public AnimationContainer() {
        for(int i=0;i<9;i++){
            textures.add(null);
        }
    }

    public void setTextureAtDirection(Direction direction, Texture texture) {
        textures.set(direction.ordinal(), texture);
    }

    public Texture getTextureAtDirection(Direction direction) {
        return textures.get(direction.ordinal());
    }

    public Texture getTextureAtPosition(int position) {
        return textures.get(position);
    }

    public void dispose() {
        for (Texture texture : textures) {
            texture.dispose();
        }
    }
}
