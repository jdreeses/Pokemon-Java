package PokemonJava;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

  
enum Type {FIRE, WATER, GRASS, ROCK, GROUND, NORMAL, 
    DARK, POISON, STEEL, DRAGON, PSYCHIC, GHOST,
    ELECTRIC, FIGHTING, ICE, FAIRY, BUG, FLYING, NONE}

public class Pokemon {

    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";
    
    String name = "";
    Type Type1 = null;
    Type Type2 = null;
    double HP = 0;
    double realHP = 0;
    double currentHealth = 0;
    double attack = 0;
    double defense = 0;
    double spAttack = 0;
    double spDefense = 0;
    double speed = 0;
    double Dattack = 0;
    double Ddefense = 0;
    double DspAttack = 0;
    double DspDefense = 0;
    double Dspeed = 0;
    double DHP = 0;
    double attackBoost = 1.0;
    double defenseBoost = 1.0;
    double spAttackBoost = 1.0;
    double spDefenseBoost = 1.0;
    StatusEffect status = StatusEffect.NONE;
    ArrayList<move> moves = new ArrayList<move>();

    public static int getRand(int upperBound) {
        Random rand = new Random(); //instance of random class
        //generate random values from 0-24
      int int_random = rand.nextInt(upperBound);
      return int_random;
    }
    
    public double dStat(double x) {
        return ((0.095*x)+1.722);
    }

    public void makePokemon(String name, Type Type1, Type Type2, 
    double HP, double attack, double defense,
    double spAttack, double spDefense, double speed) {
        this.name = name;
        this.Type1 = Type1;
        this.Type2 = Type2;
        this.realHP = (0.01*(2*HP+31+(0))*100)+100+10;
        this.currentHealth = this.realHP;
        this.HP = HP;
        this.attack = attack;
        this.defense = defense;
        this.spAttack = spAttack;
        this.spDefense = spDefense;
        this.speed = speed;
        this.Dattack = dStat(attack)*4;
        this. Ddefense = dStat(defense);
        this.DspAttack = dStat(spAttack)*4;
        this.DspDefense = dStat(spDefense);
        this.Dspeed = dStat(speed);
        this.DHP = dStat(HP) + 5;
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

    public void printStats() {
        System.out.println(YELLOW+"--STATS--"+RESET);
        System.out.println("TYPE: "+Type1+"/"+Type2);
        System.out.println("HEALTH: "+this.HP);
        System.out.println("ATTACK: "+this.attack);
        System.out.println("DEFENSE: "+this.defense);
        System.out.println("SPEED: "+this.speed);

        System.out.println(YELLOW+"--MOVES--"+RESET);
        for(int i = 0; i < this.moves.size(); i++){
            System.out.println(this.moves.get(i).moveName);
        }
    }

    public void useMove(move Move, Pokemon enemy) throws InterruptedException {

        if(Move.damaging) {
            //MOVE HAS NO EFFECT
            
            for(int i = 0; i<Move.NEon.size();i++){
                if( (Move.NEon.get(i).equals(enemy.Type1)) || (Move.NEon.get(i).equals(enemy.Type2))){
                    System.out.println(Move.moveName+" has no effect on "+enemy.name+".");
                    pause();
                    return;
                }
            }

            double boosts = 1;
            if((this.Type1.equals(Move.type))||(this.Type2.equals(Move.type))){
                boosts*=1.5;
            }

            for(int i = 0; i<Move.SEon.size();i++){
                if( (Move.SEon.get(i).equals(enemy.Type1)) || (Move.SEon.get(i).equals(enemy.Type2))){
                    boosts*=2;
                    System.out.println(YELLOW+"It's super effective!"+RESET);
                }
            }
            for(int i = 0; i <Move.NVEon.size();i++){
                if( (Move.NVEon.get(i).equals(enemy.Type1)) || (Move.NVEon.get(i).equals(enemy.Type2))){
                    boosts*=0.5;
                    System.out.println(CYAN+"It's not very effective..."+RESET);
                }
            }
            for(int i = 0; i <Move.NEon.size();i++){
                if( (Move.NEon.get(i).equals(enemy.Type1)) || (Move.NEon.get(i).equals(enemy.Type2))){
                    boosts=0;
                    System.out.println("The move doesn't affect"+enemy.name+".");
                }
            }

            double percent = 0.0;
            if(Move.special){
                percent = ((this.spAttackBoost * this.DspAttack * boosts * Move.damage) / (enemy.DHP * enemy.DspDefense * enemy.spDefenseBoost));
            }
            if(!Move.special){
                percent = ((this.attackBoost * this.Dattack * boosts * Move.damage) / (enemy.defenseBoost * enemy.DHP * enemy.Ddefense));

            }
            double roll = ( double)(getRand(15)+85) * 0.01;
            double damageDone = enemy.realHP * (percent/100);
            double rolledDamageDone = roll*damageDone;

            int critChance = getRand(24);
            if(critChance == 0){
                System.out.println(RED+"It was a critical hit!"+RESET);
                rolledDamageDone *= 1.5;
            }

            enemy.currentHealth -= rolledDamageDone;
            if(Move.draining) {
                this.currentHealth += (rolledDamageDone*0.5);
                if(this.currentHealth >= this.realHP){
                    this.currentHealth = this.realHP;
                }
            }
        }

        if(Move.attackBoost) {
            this.attackBoost *= Move.modifier;
            pause();
            space();
            System.out.println(this.name+"'s Attack rose!");            
        }
        if(Move.spAttackBoost) {
            this.spAttackBoost *= Move.modifier;
            pause();
            space();
            System.out.println(this.name+"'s Special Attack rose!");            
        }
        if(Move.defenseBoost) {
            this.defenseBoost *= Move.modifier;
            pause();
            space();
            System.out.println(this.name+"'s Defense rose!");

        }
        if(Move.spDefenseBoost) {
            this.spDefenseBoost *= Move.modifier;
            pause();
            space();
            System.out.println(this.name+"'s Special Defense rose!");
    
        }
        if(Move.speedBoost) {
            this.speed *= Move.modifier;
            pause();
            space();
            System.out.println(this.name+"'s Speed rose!");
        }

        if(Move.restorative) {
            this.currentHealth += (this.realHP*0.5);
            if(this.currentHealth > this.realHP){
                this.currentHealth = this.realHP;
            }
            System.out.println(this.name+"'s Health was restored.");
        }
        if(Move.statusInflicting && !enemy.status.equals(StatusEffect.NONE)&& !Move.damaging){
            System.out.println(enemy.name + " already has a status condition!");
        }

        if(Move.statusInflicting && enemy.status.equals(StatusEffect.NONE)){
            for(int i = 0; i<Move.NEon.size();i++){
                if( (Move.NEon.get(i).equals(enemy.Type1)) || (Move.NEon.get(i).equals(enemy.Type2))){
                    System.out.println(Move.moveName+" has no effect on "+enemy.name+".");
                    pause();
                    return;
                }
            }
            double chance = getRand(101);
            if(chance <= (int) Move.statusChance){
                enemy.status = Move.status;
                if(Move.status.equals(StatusEffect.BRN)){
                    pause();
                    System.out.println(enemy.name+" was burned!");
                    double newAttack = enemy.attack/2;
                    enemy.attack = newAttack;
                }
                if(Move.status.equals(StatusEffect.PAR)){
                    pause();
                    System.out.println(enemy.name+" was paralyzed!");
                    double newSpeed = enemy.speed/2;
                    enemy.speed = newSpeed;
                }
                if(Move.status.equals(StatusEffect.FRZ)){
                    pause();
                    System.out.println(enemy.name+" was frozen!");
                }
                if(Move.status.equals(StatusEffect.PSN)){
                    pause();
                    System.out.println(enemy.name+" was poisoned!");
                }
                if(Move.status.equals(StatusEffect.SLP)){
                    pause();
                    System.out.println(enemy.name+" was put to sleep!");
                }
                if(Move.status.equals(StatusEffect.CNF)){
                    pause();
                    System.out.println(enemy.name+" was confused!");
                }
            }
        }
        
    }

    public void faint() {
        System.out.println(this.name + " fainted!");
    }

    public void usePotion() {
        this.currentHealth += 25;
    }
}