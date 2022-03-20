package PokemonJava;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.*;


public class Battle {

    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String Cyan = "\u001B[36m";

    public static int getRand(int upperBound) {
        Random rand = new Random(); //instance of random class
        //generate random values from 0-24
      int int_random = rand.nextInt(upperBound);
      return int_random;
    }
    
    public static void pause() throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(1500);   
    }
    public static void longPause() throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(3000);   
    }
    public static void space() {
        System.out.print("\n");
    }
    public static void main(String[] args) throws InterruptedException, IOException {
        
        //BATTLE START
        Scanner myScanner = new Scanner(System.in);
        System.out.print("\n");
        System.out.println(YELLOW+"Welcome to Pokemon Java!"+RESET);
        pause();
        space();
        System.out.println("Choose your Team:");
        pause();
        space();
        PokemonRoster trainerRoster = new PokemonRoster();
        PokemonRoster rivalRoster = new PokemonRoster();
        trainerRoster.addPokemon();
        rivalRoster.addPokemon();
        for(int i = 0; i<trainerRoster.roster.size();i++){
            System.out.println(trainerRoster.roster.get(i).name);
            trainerRoster.roster.get(i).printStats();
            pause();
            pause();
            space();
        }

        System.out.println("Type name of lead Pokemon ("+RED+"Charizard"+RESET+","+BLUE+" Blastoise"+RESET+", or "+GREEN+"Venasaur"+RESET+")");
        Trainer trainer = new Trainer();
        Trainer rival = new Trainer();

        String chosenPokemon = myScanner.nextLine();
        for(int i = 0; i < trainerRoster.roster.size(); i++){
            if(chosenPokemon.equals(trainerRoster.roster.get(i).name)){
                trainer.choosePokemon(trainerRoster.roster.get(i));
            }
        }

        System.out.println("Type name of second Pokemon ("+RED+"Charizard"+RESET+","+BLUE+" Blastoise"+RESET+", or "+GREEN+"Venasaur"+RESET+")");
        String secondPokemon = myScanner.nextLine();
        for(int i = 0; i < trainerRoster.roster.size(); i++){
            if(secondPokemon.equals(trainerRoster.roster.get(i).name)){
                trainer.choose2ndPokemon(trainerRoster.roster.get(i));
            }
        }

        System.out.println("Type name of third Pokemon ("+RED+"Charizard"+RESET+","+BLUE+" Blastoise"+RESET+", or "+GREEN+"Venasaur"+RESET+")");
        String third = myScanner.nextLine();
        for(int i = 0; i < trainerRoster.roster.size(); i++){
            if(third.equals(trainerRoster.roster.get(i).name)){
                trainer.choose2ndPokemon(trainerRoster.roster.get(i));
            }
        }

        
        //WAY TO LOOP BACK FOR INCORRECT POKEMON CHOICE

        space();
        pause();

        if(trainer.team.size() != 3){
            System.out.println("1 or more of the Pokemon you selected were Invalid. Please Try again.");
            pause();
            main(args);
        }
        System.out.println("You have chosen... "+trainer.pokemon.name+", "+trainer.team.get(1).name+", and "+trainer.team.get(2).name);
        pause();

        int order = getRand(3);
        if(order == 0){
            rival.choosePokemon(rivalRoster.roster.get(0));
            rival.choose2ndPokemon(rivalRoster.roster.get(1));
            rival.choose2ndPokemon(rivalRoster.roster.get(2));

        }
        if(order == 1){
            rival.choosePokemon(rivalRoster.roster.get(1));
            rival.choose2ndPokemon(rivalRoster.roster.get(2));
            rival.choose2ndPokemon(rivalRoster.roster.get(0));

        }
        if(order == 2){
            rival.choosePokemon(rivalRoster.roster.get(2));
            rival.choose2ndPokemon(rivalRoster.roster.get(0));
            rival.choose2ndPokemon(rivalRoster.roster.get(1));

        }


        System.out.println("Your opponent has chosen... "+rival.pokemon.name+", "+rival.team.get(1).name+", and "+rival.team.get(2).name);
        pause();

        System.out.println("Battle Start!");
        BattleProcedure battleProcedure = new BattleProcedure();
        battleProcedure.createBattle(trainer, rival, trainer.pokemon, rival.pokemon);
        pause();
        battleProcedure.Engage();

        return;
    }
}
