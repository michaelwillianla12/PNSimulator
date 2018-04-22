public class Marca {

    private int id;
    private int tempo;


    public Marca(int tempo) {

        this.tempo = tempo;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public void decTempo() {
        tempo--;
    }

}
