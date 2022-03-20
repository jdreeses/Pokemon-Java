package PokemonJava;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;


public class BattleProcedure {
    Trainer trainer;
    Trainer rival;
    Pokemon trainerPokemon;
    Pokemon rivalPokemon;

    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String Cyan = "\u001B[36m";
    

    public void createBattle(Trainer trainer, Trainer rival, Pokemon trainerPokemon, Pokemon rivalPokemon) {
        this.trainer = trainer;
        this.rival = rival;
        this.trainerPokemon = trainerPokemon;
        this.rivalPokemon = rivalPokemon;
    }

    public static int getRand(int upperBound) {
        Random rand = new Random(); //instance of random class
        //generate random values from 0-24
      int int_random = rand.nextInt(upperBound);
      return int_random;
    }

    public void pause() throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(1500);   
    }
    public static void longPause() throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(3000);   
    }
    public static void space() {
        System.out.print("\n");
    }

    public void displayHealth() {
       double HP = this.trainer.pokemon.currentHealth;
       double maxHP = this.trainer.pokemon.realHP;
       double enemyHP = this.rival.pokemon.currentHealth;
       double enemyMaxHP = this.rival.pokemon.realHP;

       space();

       System.out.print(this.trainer.pokemon.name + ": ");
       System.out.print( (int)Math.rint((HP/maxHP)*100)+"% ");
    
       for(int i = 0; i < ((HP/maxHP)*10); i++) {
        System.out.print(GREEN+"||"+RESET);
       }
       for(int x = 0; x < ( (10-((HP/maxHP)*10) ) ); x++){
        System.out.print("__");
       }
       System.out.println("");

       System.out.print(this.rival.pokemon.name + ": ");
       System.out.print((int) Math.rint((enemyHP/enemyMaxHP)*100)+"% ");
    
       for(int i = 0; i < ((enemyHP/enemyMaxHP)*10); i++) {
        System.out.print(GREEN+"||"+RESET);
       }
       for(int x = 0; x < ( (10-((enemyHP/enemyMaxHP)*10) ) ); x++){
        System.out.print("__");
       }
       space();
    }

    public boolean SleepParFreeze(Pokemon pokemon) throws InterruptedException{
        if(pokemon.status.equals(StatusEffect.FRZ)){
            int stillfrozen = getRand(3);
            if(stillfrozen > 0){
                space();
                pause();
                System.out.println(pokemon.name+" is frozen solid!");
                return true;
            }
            if(stillfrozen == 0){
                space();
                pause();
                System.out.println(pokemon.name + " thawed out!");
                return false;
            }
        }

        else if(pokemon.status.equals(StatusEffect.PAR)){
            int stillPar = getRand(4);
            if(stillPar == 0){
                space();
                pause();
                System.out.println(pokemon.name+" is paralyzed and can't move!");
                return true;

            }
        }
        else if(pokemon.status.equals(StatusEffect.SLP)){
            int sleeping = getRand(100);
            if(sleeping > 40){
                space();
                pause();
                System.out.println(pokemon.name + "is fast asleep...");
                return true;
            }
            if(sleeping <= 40){
                space();
                pause();
                System.out.println(pokemon.name+" woke up!");
                return false;
            }
        }
        return false;
    }

    public void DamageStatus(Pokemon pokemon) throws InterruptedException{
        if(pokemon.status.equals(StatusEffect.BRN)){
            pause();
            space();
            System.out.println(pokemon.name+" was hurt by its burn!");
            pokemon.currentHealth -= (pokemon.realHP / 16);
            if(this.trainer.pokemon.currentHealth <= 0){
                userFaint();
                return;
            }
            if(this.rival.pokemon.currentHealth <= 0){
                rivalFaint();
                return;
            }
        }
        if(pokemon.status.equals(StatusEffect.PSN)){
            System.out.println(pokemon.name+" was hurt by its poison!");
            pokemon.currentHealth -= (pokemon.realHP / 16);
            if(this.trainer.pokemon.currentHealth <= 0){
                userFaint();
                return;
            }
            if(this.rival.pokemon.currentHealth <= 0){
                rivalFaint();
                return;
            }
        }
    }

    public void useMove(Pokemon pokemon, Pokemon target, move move) throws InterruptedException{
        if(SleepParFreeze(pokemon)){
            space();
            pause();
            return;
        }

        System.out.println(pokemon.name + " used " + move.moveName + "!");
        int miss = getRand(101);
        if(miss > move.accuracy){
            pause();
            System.out.println("The attack missed!");
            pause();
            return;
        }
        pokemon.useMove(move, target);
        pause();
        if(target.currentHealth <= 0){
            target.currentHealth = 0;
        }
        displayHealth();
        pause();
        space();
        if(target.currentHealth <=0){
            target.currentHealth = 0;
            rivalFaint();
            Engage();
            return;
        }
    }

    public void rivalMove(Pokemon pokemon, Pokemon target, move move) throws InterruptedException{
        if(SleepParFreeze(pokemon)){
            space();
            pause();
            return;
        }
        System.out.println(pokemon.name + " used " + move.moveName + "!");
        int miss = getRand(101);
        if(miss > move.accuracy){
            pause();
            System.out.println("The attack missed!");
            space();
            return;
        }
        pokemon.useMove(move, target);
        if(target.currentHealth <= 0){
            target.currentHealth = 0;
        }
        pause();
        displayHealth();
        pause();
        space();
        if(target.currentHealth <= 0){
            target.currentHealth = 0;
            System.out.println(target.name+" fainted!");
            userFaint();
            Engage();
            return;
        }
    }


    public void rivalFaint() throws InterruptedException {
        boolean pokemonLeft = false;
        System.out.println(this.rival.pokemon.name + " fainted!");
        pause();
        for(int i = 0; i < this.rival.team.size(); i++){
            if(this.rival.team.get(i).currentHealth > 0){
                pokemonLeft = true;
            }
        }
        if(pokemonLeft){
            ArrayList<Pokemon> usable = new ArrayList<>(Arrays.asList());
            for(int i = 0; i < this.rival.team.size(); i++){
                if(this.rival.team.get(i).currentHealth > 0){
                    usable.add(this.rival.team.get(i));
                }
            }
            int rand = getRand(usable.size());
            this.rival.pokemon = usable.get(rand);
            this.rivalPokemon = usable.get(rand);
            space();
            pause();
            System.out.println("Rival sent out "+this.rival.pokemon.name+"!");
            pause();
            displayHealth();
            return;
        }
        else{
            pause();
            space();
            System.out.println("Rival is out of usable pokemon!");
            pause();
            space();
            youWin();
        }
    }

    public void userFaint() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean pokemonLeft = false;
        for(int i = 0; i < this.trainer.team.size(); i++){
            if(this.trainer.team.get(i).currentHealth > 0){
                pokemonLeft = true;
            }
        }
        if(pokemonLeft){
            ArrayList<Pokemon> usable = new ArrayList<>(Arrays.asList());
            for(int i = 0; i < this.trainer.team.size(); i++){
                if(this.trainer.team.get(i).currentHealth > 0){
                    usable.add(this.trainer.team.get(i));
                }
            }
            System.out.println("Select next Pokemon:");
            for(int i = 0; i < usable.size();i++){
                System.out.println(usable.get(i).name);
            }
            String selection = scanner.nextLine();
            boolean goodSelect = false;
            for(int i = 0; i < usable.size(); i++){
                if(selection.equals(usable.get(i).name)){
                    this.trainer.pokemon = usable.get(i);
                    this.trainerPokemon = usable.get(i);
                    goodSelect = true;
                    pause();
                    space();
                    System.out.println("Go, "+usable.get(i).name);
                    pause();
                    displayHealth();
                    return;
                }
            }
            if(!goodSelect){
                System.out.println("Invalid selection.");
                userFaint();
            }
        }
        else{
            pause();
            space();
            System.out.println("You are out of usable pokemon!");
            pause();
            space();
            rivalWins();
        }
    }

    public void Fight() throws InterruptedException {
        space();
        System.out.println("Select a move:");
        space();
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < this.trainer.pokemon.moves.size();i++){
            move move1 = this.trainer.pokemon.moves.get(i);
            System.out.println("||"+move1.moveName + "|| Type: "+move1.type+" / Power: "+move1.damage+" / Acc. "+move1.accuracy);
            System.out.println("--"+move1.flavorText);
            space();
        }
        String chosenMove = scanner.nextLine();
        move selectedMove = null;
        boolean validMove = false;
        for(int i = 0; i < this.trainer.pokemon.moves.size();i++){
            move move1 = this.trainer.pokemon.moves.get(i);
            if(move1.moveName.equals(chosenMove)){
                selectedMove = move1;
                validMove = true;
            }
        }
        if (!validMove){
            System.out.println("Invalid move selected.");
            Engage();
        }
        if(this.trainerPokemon.speed >= this.rivalPokemon.speed){
            pause();
            space();
            useMove(this.trainerPokemon, this.rivalPokemon, selectedMove);
            int rivalMoveIndex = getRand(this.rivalPokemon.moves.size());
            move rivalsMove = this.rivalPokemon.moves.get(rivalMoveIndex);

            rivalMove(this.rivalPokemon, this.trainerPokemon, rivalsMove);
            DamageStatus(this.trainer.pokemon);
            DamageStatus(this.rival.pokemon);
            pause();
            Engage();
        }
        if(this.trainer.pokemon.speed < this.rivalPokemon.speed){
            pause();
            int rivalMoveIndex = getRand(this.rivalPokemon.moves.size());
            move rivalsMove = this.rivalPokemon.moves.get(rivalMoveIndex);
            rivalMove(this.rivalPokemon, this.trainerPokemon, rivalsMove);
            pause();
            useMove(this.trainerPokemon, this.rivalPokemon, selectedMove);
            DamageStatus(this.rivalPokemon);
            DamageStatus(this.trainerPokemon);
            pause();
            Engage();
        }
    }

    public void Switch() throws InterruptedException {
        System.out.println("Come back "+this.trainerPokemon.name);
        space();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Pokemon> usable = new ArrayList<>(Arrays.asList());
         System.out.println("Switch to which Pokemon?");
         for(int i = 0; i < this.trainer.team.size(); i++){
            if(this.trainer.team.get(i).currentHealth > 0){
                usable.add(this.trainer.team.get(i));
            }
        }
         for(int i = 0; i < usable.size(); i++){
                System.out.println(usable.get(i).name);
         }
         String response = scanner.nextLine();
         for(int i = 0; i < usable.size(); i++){
            if(usable.get(i).name.equals(response)){
                this.trainerPokemon = this.trainer.team.get(i);
                this.trainer.pokemon = this.trainer.team.get(i);
            }
        }
        pause();
        space();
        System.out.println("Go, "+this.trainerPokemon.name+"!");
        displayHealth();
        pause();
        space();
        int rivalMoveIndex = getRand(this.rivalPokemon.moves.size());
        move rivalsMove = this.rivalPokemon.moves.get(rivalMoveIndex);
        rivalMove(this.rivalPokemon, this.trainerPokemon, rivalsMove);
        DamageStatus(this.trainerPokemon);
        DamageStatus(this.rivalPokemon);
        pause();
        Engage();
    }
    
    public void Engage() throws InterruptedException{
        Scanner scanner = new Scanner(System.in);
        displayHealth();
        space();
        System.out.print("Fight, Use Item, or Switch");
        space();
        String response = scanner.nextLine();
        if(response.equals("Switch")){
            space();
            Switch();
            space();
        }

        if(response.equals("Fight")){
            Fight();
            space();
         }

        if(response.equals("Use Item")){
            System.out.println("Available items: Potion ");
            pause();
            System.out.println("Select name of item you would like to use.");
            String itemName = scanner.nextLine();
            if(itemName.equals("Potion")){
                trainerPokemon.currentHealth += 25;
                System.out.println(trainerPokemon.name+"'s health was restored by 25 points.");
                pause();
                int rivalMoveIndex = getRand(this.rivalPokemon.moves.size());
                move rivalsMove = this.rivalPokemon.moves.get(rivalMoveIndex);
    
                rivalMove(this.rivalPokemon, this.trainerPokemon, rivalsMove);
                DamageStatus(this.trainerPokemon);
                DamageStatus(this.rivalPokemon);
                pause();
                Engage();
            }
            else{
                System.out.println("Invalid item name.");
                pause();
                Engage();
                return;
            }
        }
        else{
            System.out.println("Invalid response.");
            pause();
            Engage();
            return;
        }
    }

public void youWin() throws InterruptedException{
    Scanner scanner2 = new Scanner(System.in);
    System.out.println("You win! Congratulations. (Please kill(trash icon) terminal and re-run 'Battle' to play again! )");
    String playAgain = scanner2.nextLine();
    if(playAgain.equals("Yes")){
        trainer.playAgain = true;
    }
    if(playAgain.equals("No")){
        trainer.playAgain = false;
    }
    else{
        System.out.println("Invalid response.");
        pause();
        youWin();
    }
    scanner2.close();
    return;
}

public void rivalWins() throws InterruptedException{
    Scanner scanner2 = new Scanner(System.in);
    System.out.println("You lost! (Please kill(trash icon) terminal and re-run 'Battle' to play again! )");
    String playAgain = scanner2.nextLine();
    if(playAgain.equals("Yes")){
        trainer.playAgain = true;
    }
    if(playAgain.equals("No")){
        trainer.playAgain = false;
    }
    else{
        System.out.println("Invalid response.");
        pause();
        rivalWins();
    }
    scanner2.close();
    return;
}
}
