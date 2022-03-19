package PokemonJava;

import java.util.ArrayList;
import PokemonJava.Type;

enum StatusEffect {PSN, SLP, BRN, PAR, FRZ, CNF, NONE}

public class move {
    String moveName;
    int damage;
    Type type;
    double accuracy;
    boolean special;
    double modifier;
    boolean damaging; 
    boolean attackBoost;
    boolean spAttackBoost;
    boolean defenseBoost;
    boolean spDefenseBoost;
    boolean speedBoost;
    boolean restorative;
    boolean draining;
    boolean statusInflicting;
    double statusChance;
    StatusEffect status;

    ArrayList<Type> SEon = new ArrayList<Type>();
    ArrayList<Type> NVEon = new ArrayList<Type>();
    ArrayList<Type> NEon = new ArrayList<Type>();

    String flavorText; 
    public void makeMove(String moveName, int damage, Type type,
                        double accuracy, 
                        boolean special, double modifier, 
                        boolean damaging,
                         boolean attackBoost, boolean spAttackBoost,
                         boolean defenseBoost, boolean spDefenseBoost,
                         boolean speedBoost, boolean restorative,
                         boolean draining, boolean statusInflicting, double statusChance, StatusEffect status,
                         String flavorText, ArrayList<Type> SEon,
                         ArrayList<Type> NVEon, ArrayList<Type> NEon) {

    this.moveName = moveName;
    this.damage = damage;
    this.type = type;
    this.accuracy = accuracy;
    this.special = special;
    this.modifier = modifier;
    this.damaging = damaging; 
    this.speedBoost = speedBoost;
    this.attackBoost = attackBoost;
    this.spAttackBoost = spAttackBoost;
    this.defenseBoost = defenseBoost; 
    this.spDefenseBoost = spDefenseBoost;
    this.flavorText = flavorText;
    this.SEon = SEon;
    this.NVEon = NVEon;
    this.restorative = restorative;
    this.draining = draining;
    this.status = status;
    this.statusChance = statusChance;
    this.NEon = NEon;
    this.statusInflicting = statusInflicting;
    }  
} 

