package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.Pokemon;
import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetPoison;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.SpecialClassePourTour.ForgePoisson;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeTirUnique;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeZone;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.NullActif;

public class NewNidoran extends Tour{
    private EffetPoison effetPoison;
    public NewNidoran(int x, int y) {
        super(115, 0, Pokemon.nidoran.getType(), Pokemon.nidoran.getPrix(), x, y, Pokemon.nidoran.name(), 100, new NullActif());
        this.effetPoison = new EffetPoison(2, new Seconde(3), new Seconde(0.2), this);
        ModeAttaque tirUniqueParEnnemi = new ModeTirUnique(this);
        setMyForgeEffectImpact(new ForgePoisson(2, new Seconde(3),new Seconde(0.2),this));
        setModeAttaque(tirUniqueParEnnemi);
    }

    @Override
    public void amelioreStats() {
        this.effetPoison.amelioreEffet(10,0);
        if (level.get() == Parametres.niveauEvolutionTour){
            portee.set(portee.get()+400);
//            ModeAttaque zone = new ModeZone(this.effetPoison, this);
//            setModeAttaque(zone);
        }
    }
}
