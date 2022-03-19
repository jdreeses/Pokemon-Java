package PokemonJava;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
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
        }
        if(pokemon.status.equals(StatusEffect.PSN)){
            System.out.println(pokemon.name+" was hurt by its poison!");
            pokemon.currentHealth -= (pokemon.realHP / 16);
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
            youWin();
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
        displayHealth();
        pause();
        space();
        if(target.currentHealth <= 0){
            target.currentHealth = 0;
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
            DamageStatus(this.trainerPokemon);
            DamageStatus(this.rivalPokemon);
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
    
    public void Engage() throws InterruptedException{
        Scanner scanner = new Scanner(System.in);
        displayHealth();
        space();
        System.out.print("[Fight] or [Use Item]");
        space();
        String response = scanner.nextLine();
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
