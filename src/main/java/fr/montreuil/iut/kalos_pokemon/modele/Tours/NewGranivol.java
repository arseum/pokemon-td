package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.Pokemon;
import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetPoison;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeTirEnContinue;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeTirUnique;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.Competence;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.NullActif;

public class NewGranivol extends Tour{
    public NewGranivol(int x, int y) {
        super(160, 1, Pokemon.granivol.getType(), Pokemon.granivol.getPrix(), x, y, Pokemon.granivol.name(), 6, new NullActif());
        //ModeAttaque modeAttaque = new ModeTirEnContinue(new EffetNull(this), this);
        //FIXME le temps de la prochaine attaque ca devrait etre le projectile et non l'effet
        // ok, ca vient de modeTirCon cette condition
        // ennemiCible.estEffecteParEffet(this.effetAttaque)
        // je l'avais implemente pour eviter bug visuel (genre ca attaque mais pas d'effet)
        ModeAttaque modeAttaque = new ModeTirEnContinue(new EffetPoison(10, new Seconde(4), new Seconde(3), this), this);
        setModeAttaque(modeAttaque);
    }

    @Override
    public void amelioreStats() {
        if (level.get() == Parametres.niveauEvolutionTour) {
            portee.set(180);
        } else {
            attaqueSpeed = 5;
        }
        degats += 1;
    }
}
