package PokemonJava;

import java.util.ArrayList;
import java.util.Arrays;

public class Trainer {
    Pokemon pokemon;
    ArrayList<Pokemon> team = new ArrayList<Pokemon>(Arrays.asList());
    boolean playAgain = false;
public void choosePokemon(Pokemon pokemon) {
    this.pokemon = pokemon;
    this.team.add(pokemon);
}
public void choose2ndPokemon(Pokemon pokemon){
    this.team.add(pokemon);
}
}

