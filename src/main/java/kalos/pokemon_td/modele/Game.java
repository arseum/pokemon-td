package kalos.pokemon_td.modele;

public class Game {

    private char[][] map;

    public Game(){
    }

    public char[][] getMap() {
        return map;
    }

    public void initMap(int ligne, int col){
        map = new char[ligne][col];
        for (int i = 0 ; i < ligne ; i++)
            for (int j = 0 ; j < col ; j++)
                map[i][j] = ' ';
    }

    public void insert(int ligne, int col, char i){
        map[ligne][col] = i;
    }

    public void afficheMapConsole(){
        for (char[] tab : map) {
            for (char a : tab)
                System.out.print(a);
            System.out.println();
        }
    }


}
