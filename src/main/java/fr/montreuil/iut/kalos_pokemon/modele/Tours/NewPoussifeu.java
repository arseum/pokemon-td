package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.Pokemon;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.SpecialClassePourTour.ForgeEffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeTirAleatoire;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.Competence;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.NullActif;

public class NewPoussifeu extends Tour{
    public NewPoussifeu(int x, int y) {
        super(100, 30, Pokemon.poussifeu.getType(), Pokemon.poussifeu.getPrix(), x, y, Pokemon.poussifeu.name(), 50, new NullActif());
        ModeAttaque tirAleatoire = new ModeTirAleatoire(new EffetNull(this),this);
        setModeAttaque(tirAleatoire);
        setMyForgeEffectImpact(new ForgeEffetNull(this));
    }

    @Override
    public void amelioreStats() {
        this.degats *= 1.5;
        this.attaqueSpeed -= 6* (getLevel()-1);
        portee.set(portee.get()+(3* getLevel()));
    }
}
