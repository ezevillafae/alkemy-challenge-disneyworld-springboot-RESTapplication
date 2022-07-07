package com.alkemy.disneyworldspringbootapplication.shared;

import com.alkemy.disneyworldspringbootapplication.entity.CharacterEntity;

public class CharacterEntityMother {
    public static CharacterEntity random(){
        CharacterEntity character = new CharacterEntity();
        character.setName("Will Smith");
        character.setAge(IntegerMother.random());
        character.setHistory("Random history");
        character.setWeight(DoubleMother.random());
        character.setImage("img/image-random.jpg");
        return character;
    }
}
