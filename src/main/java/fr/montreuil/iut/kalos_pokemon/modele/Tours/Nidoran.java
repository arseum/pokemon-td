package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donnees.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Donnees.Seconde;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetPoison;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeProjectileSimple;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.CompetenceRalentissementEnnemiPoison;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.NullActif;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblesUniques;


public class Nidoran extends Tour {

    public Nidoran(int x, int y) {
        super(115, 3,
                PokemonEnum.nidoran.getType(),
                PokemonEnum.nidoran.getPrix(), x, y,
                PokemonEnum.nidoran.name(), 60, new NullActif());

        setModeAttaque(new ModeCiblesUniques(this));
        setMyForgeEffectImpact(new ForgeEffetPoison(this,4,new Seconde(10),new Seconde(0.5)));
        setMyCompetence(new CompetenceRalentissementEnnemiPoison(this,new Seconde(22)));
        setMyForgeAttaque(new ForgeProjectileSimple());

    }

    @Override
    public void amelioreStats() {
        this.degats += 4;
        if (level.get() == Parametres.niveauEvolutionTour)
            portee.set(portee.get()+10);
    }


}
