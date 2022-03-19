package PokemonJava;

import java.util.ArrayList;
import java.util.Arrays;

public class PokemonRoster {
    ArrayList<Pokemon> roster = new ArrayList<Pokemon>();

    public void addPokemon(){
        ArrayList<Type> FIREseon= new ArrayList<>(Arrays.asList(Type.GRASS,Type.ICE,Type.BUG,Type.STEEL));
        ArrayList<Type> FAIRYseon= new ArrayList<>(Arrays.asList(Type.FIGHTING,Type.DRAGON,Type.DARK));
        ArrayList<Type> STEELseon= new ArrayList<>(Arrays.asList(Type.ICE,Type.ROCK,Type.FAIRY));
        ArrayList<Type> DARKseon= new ArrayList<>(Arrays.asList(Type.PSYCHIC,Type.GHOST));
        ArrayList<Type> DRAGONseon= new ArrayList<>(Arrays.asList(Type.DRAGON));
        ArrayList<Type> GHOSTseon= new ArrayList<>(Arrays.asList(Type.PSYCHIC,Type.GHOST));
        ArrayList<Type> ROCKseon= new ArrayList<>(Arrays.asList(Type.FIRE,Type.ICE,Type.FLYING,Type.BUG));
        ArrayList<Type> BUGseon= new ArrayList<>(Arrays.asList(Type.GRASS,Type.PSYCHIC,Type.DARK));
        ArrayList<Type> PYSCHICseon= new ArrayList<>(Arrays.asList(Type.FIGHTING,Type.POISON));
        ArrayList<Type> FLYINGseon= new ArrayList<>(Arrays.asList(Type.GRASS,Type.FIGHTING,Type.BUG));
        ArrayList<Type> GROUNDseon= new ArrayList<>(Arrays.asList(Type.FIRE,Type.ELECTRIC,Type.POISON,Type.ROCK,Type.STEEL));
        ArrayList<Type> POISONseon= new ArrayList<>(Arrays.asList(Type.GRASS,Type.FAIRY));
        ArrayList<Type> FIGHTINGseon= new ArrayList<>(Arrays.asList(Type.NORMAL,Type.ICE,Type.ROCK,Type.DARK,Type.STEEL));
        ArrayList<Type> ICEseon= new ArrayList<>(Arrays.asList(Type.GRASS,Type.GROUND,Type.FLYING,Type.DRAGON));
        ArrayList<Type> GRASSseon= new ArrayList<>(Arrays.asList(Type.WATER,Type.GROUND,Type.ROCK));
        ArrayList<Type> ELECTRICseon= new ArrayList<>(Arrays.asList(Type.WATER,Type.FLYING));
        ArrayList<Type> WATERseon= new ArrayList<>(Arrays.asList(Type.FIRE,Type.GROUND,Type.ROCK));
        ArrayList<Type> NORMALseon= new ArrayList<>(Arrays.asList());

        ArrayList<Type> NORMALnveon= new ArrayList<>(Arrays.asList(Type.ROCK,Type.STEEL));
        ArrayList<Type> FIREnveon= new ArrayList<>(Arrays.asList(Type.FIRE,Type.WATER,Type.ROCK,Type.DRAGON));
        ArrayList<Type> WATERnveon= new ArrayList<>(Arrays.asList(Type.WATER,Type.GRASS,Type.DRAGON));
        ArrayList<Type> GRASSnveon= new ArrayList<>(Arrays.asList(Type.FIRE,Type.GRASS,Type.POISON,Type.FLYING,Type.BUG,Type.DRAGON,Type.STEEL));
        ArrayList<Type> ELECTRICnveon= new ArrayList<>(Arrays.asList(Type.GRASS,Type.ELECTRIC,Type.DRAGON));
        ArrayList<Type> ICEnveon= new ArrayList<>(Arrays.asList(Type.FIRE,Type.WATER,Type.ICE,Type.STEEL));
        ArrayList<Type> FIGHTINGnveon= new ArrayList<>(Arrays.asList(Type.POISON,Type.FLYING,Type.PSYCHIC,Type.BUG,Type.FAIRY));
        ArrayList<Type> POISONnveon= new ArrayList<>(Arrays.asList(Type.POISON,Type.GROUND,Type.ROCK,Type.GHOST));
        ArrayList<Type> GROUNDnveon= new ArrayList<>(Arrays.asList(Type.GRASS,Type.BUG));
        ArrayList<Type> FLYINGnveon= new ArrayList<>(Arrays.asList(Type.ELECTRIC,Type.ROCK,Type.STEEL));
        ArrayList<Type> PSYCHICnveon= new ArrayList<>(Arrays.asList(Type.PSYCHIC,Type.STEEL));
        ArrayList<Type> BUGnveon= new ArrayList<>(Arrays.asList(Type.FIRE,Type.FIGHTING,Type.POISON,Type.FLYING,Type.GHOST,Type.STEEL,Type.FAIRY));
        ArrayList<Type> ROCKnveon= new ArrayList<>(Arrays.asList(Type.FIGHTING,Type.GROUND,Type.STEEL));
        ArrayList<Type> GHOSTnveon= new ArrayList<>(Arrays.asList(Type.DARK));
        ArrayList<Type> DRAGONnveon= new ArrayList<>(Arrays.asList(Type.STEEL));
        ArrayList<Type> DARKnveon= new ArrayList<>(Arrays.asList(Type.FIGHTING,Type.DARK,Type.FAIRY));
        ArrayList<Type> STEELnveon= new ArrayList<>(Arrays.asList(Type.FIRE,Type.WATER,Type.ELECTRIC,Type.STEEL));
        ArrayList<Type> FAIRYnveon= new ArrayList<>(Arrays.asList(Type.FIRE,Type.POISON,Type.STEEL));


        ArrayList<Type> NORMALneon= new ArrayList<>(Arrays.asList(Type.GHOST));
        ArrayList<Type> FIREneon= new ArrayList<>(Arrays.asList());
        ArrayList<Type> WATERneon= new ArrayList<>(Arrays.asList());
        ArrayList<Type> GRASSneon= new ArrayList<>(Arrays.asList());
        ArrayList<Type> ELECTRICneon= new ArrayList<>(Arrays.asList(Type.GROUND));
        ArrayList<Type> ICEneon= new ArrayList<>(Arrays.asList());
        ArrayList<Type> FIGHTINGneon= new ArrayList<>(Arrays.asList(Type.GHOST));
        ArrayList<Type> POISONneon= new ArrayList<>(Arrays.asList(Type.STEEL));
        ArrayList<Type> GROUNDneon= new ArrayList<>(Arrays.asList(Type.FLYING));
        ArrayList<Type> FLYINGneon= new ArrayList<>(Arrays.asList(Type.ELECTRIC,Type.ROCK,Type.STEEL));
        ArrayList<Type> PSYCHICneon= new ArrayList<>(Arrays.asList(Type.DARK));
        ArrayList<Type> BUGneon= new ArrayList<>(Arrays.asList());
        ArrayList<Type> ROCKneon = new ArrayList<>(Arrays.asList());
        ArrayList<Type> GHOSTneon= new ArrayList<>(Arrays.asList(Type.NORMAL));
        ArrayList<Type> DRAGONneon= new ArrayList<>(Arrays.asList(Type.FAIRY));
        ArrayList<Type> DARKneon= new ArrayList<>(Arrays.asList());
        ArrayList<Type> STEELneon= new ArrayList<>(Arrays.asList());
        ArrayList<Type> FAIRYneon= new ArrayList<>(Arrays.asList());

        //("Move name", damage, type, accuracy, special, modifier, damaging, attackBoost, SpA Boost, Defense Boost,
        //Sp Defense Boost, speed Boost, restorative, draining, statuschance, status effect, flavor text, SEon, NVEon, NEon)


        //CHARIZARD
        move FireBlast = new move();
        FireBlast.makeMove("Flamethrower", 90, Type.FIRE, 100, true, 0.0, true, false, false, false,
                                           false, false, false, false, true, 10, StatusEffect.BRN, 
                                           "A powerful fire attack that may inflict a burn.",
                                           FIREseon,FIREnveon,FIREneon); 
        move DragonDance = new move();
        DragonDance.makeMove("Dragon Dance", 0, Type.DRAGON, 100, false, 1.5, false, true, false, false, false, true, false, false, false, 0, StatusEffect.NONE,
        "A mystical dance that boosts ATTACK and SPEED.", null, null, null);
        move Thunderpunch = new move();
        Thunderpunch.makeMove("Thunder Punch", 75, Type.ELECTRIC, 100, false, 0.0, true, false, false, false, false, false, false, false, true, 20, StatusEffect.PAR, 
        "An electric attack that may paralyze the opponent.", ELECTRICseon, ELECTRICnveon, ELECTRICneon);
        move Roost = new move();
        Roost.makeMove("Roost", 0, Type.FLYING, 100, false, 0.0, false, false, false, false, false, false, true, false, false, 0, StatusEffect.NONE, 
        "The user lands on the ground and rests, restoring 50% HP.", null, null, null);
        Pokemon Charizard = new Pokemon();
        Charizard.makePokemon("Charizard", Type.FIRE, Type.FLYING, 78, 84, 78, 109, 85, 100);
        Charizard.moves.add(FireBlast);
        Charizard.moves.add(DragonDance);
        Charizard.moves.add(Thunderpunch);
        Charizard.moves.add(Roost);


       ///VENASAUR
        move SeedBomb = new move();
        SeedBomb.makeMove("Seed Bomb", 80, Type.GRASS, 100, false, 0.0, true, false, false, false, false, false, false, false, false, 0, StatusEffect.NONE,
         "Explosive seeds are launched at the target.", GRASSseon, GRASSnveon, GRASSneon);
        move PoisonPowder = new move();
        PoisonPowder.makeMove("Poison Powder", 0, Type.POISON, 75, false, 0.0, false, false, false, false, 
        false, false, false, false, true, 100.0, StatusEffect.PSN, "Poison powder is sprinkled onto the foe. Inflicts Posion.", null, null, POISONneon);
        Pokemon Venasaur = new Pokemon();
        move GigaDrain = new move();
        GigaDrain.makeMove("Giga Drain", 60, Type.GRASS, 100, true, 0.0, true, false, false, false, false, false, false, true, false, 0, StatusEffect.NONE,
         "The user saps life energy from the target, restoring HP.", GRASSseon, GRASSnveon, GRASSneon);
        Venasaur.makePokemon("Venasaur", Type.GRASS, Type.POISON, 80, 82, 83, 100, 100, 80);
        move SludgeBomb = new move();
        SludgeBomb.makeMove("Sludge Bomb", 90, Type.POISON, 100, true, 0.0, true, false, false, false, false, false, false, false, true, 30, StatusEffect.PSN, 
        "The user hurls a ball of toxic sludge at the target.", POISONseon, POISONnveon, POISONneon);
        Venasaur.moves.add(SeedBomb);
        Venasaur.moves.add(PoisonPowder);
        Venasaur.moves.add(GigaDrain);
        Venasaur.moves.add(SludgeBomb);

        //BLASTOISE
        move DefenseCurl = new move();
        DefenseCurl.makeMove("Defense Curl", 0, Type.NORMAL, 100, false, 1.5, false, false, false, true, false, false, false, false, false, 0, StatusEffect.NONE, 
        "The user tightens its body to boost DEFENSE.", null, null, null);
        move Surf = new move();
        Surf.makeMove("Surf", 95, Type.WATER, 100, true, 0.0, true, false, false, false, false, false, false, false, false, 0, StatusEffect.NONE, 
        "A vicious wave is sent crashing onto the opponent.", WATERseon, WATERnveon, WATERneon);
        move IceBeam = new move();
        IceBeam.makeMove("Ice Beam", 95, Type.ICE, 100, true, 0.0, true, false, false, false, false, false, false, false, true, 10, StatusEffect.FRZ, 
        "The user fires a damaging ray of ice. May freeze the target.", ICEseon, ICEnveon, ICEneon);
        move Earthquake = new move();
        Earthquake.makeMove("Earthquake", 100, Type.GROUND, 100, false, 0.0, true, false, false, false, false, false, false, false, false, 0.0, StatusEffect.NONE, 
        "A damaging and destructive attack.", GROUNDseon, GROUNDnveon, GROUNDneon);
        Pokemon Blastoise = new Pokemon();
        Blastoise.makePokemon("Blastoise", Type.WATER, Type.NONE, 79, 83, 100, 85, 105, 78);
        Blastoise.moves.add(DefenseCurl);
        Blastoise.moves.add(Surf);
        Blastoise.moves.add(IceBeam);
        Blastoise.moves.add(Earthquake);

        //ADD SELECTED POKEMON 
        roster.add(Charizard);
        roster.add(Blastoise);
        roster.add(Venasaur);
    }
}


