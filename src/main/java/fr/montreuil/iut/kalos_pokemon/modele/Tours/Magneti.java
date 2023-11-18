package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donnees.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Donnees.Seconde;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeRalentissement;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeEntiteAttaqueInstantane;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblageZone;

public class Magneti extends Tour {

    public Magneti(int x, int y) {
        super(90, 0, PokemonEnum.magneti.getType(),
                PokemonEnum.magneti.getPrix(), x, y, PokemonEnum.magneti.name(),
                22,null);

        setModeAttaque(new ModeCiblageZone(this));
        setMyForgeAttaque(new ForgeEntiteAttaqueInstantane());
        setMyForgeEffectImpact(new ForgeRalentissement(this,40,new Seconde(0.35)));
    }


    @Override
    public void amelioreStats() {
        portee.set(portee.get() + (6*level.get()));
        if(level.get() == Parametres.niveauEvolutionTour) {
            setMyForgeEffectImpact(new ForgeRalentissement(this,65,new Seconde(0.35)));
            degats = 3;
        }
    }

}
