import java.util.ArrayList;

public class Lugares {

    private int id,marca;

    private ArrayList transicoesHabilitadas = new ArrayList();


    public Lugares(int id, int marca){

        this.id = id;
        this.marca = marca;


    }

    public ArrayList getTransicoesHabilitadas() {
        return transicoesHabilitadas;
    }

    public void setTransicoesHabilitadas(int transicaoHabilitada) {
        if(!transicoesHabilitadas.contains(transicaoHabilitada))
        this.transicoesHabilitadas.add(transicaoHabilitada);
    }

    public void resetTH(){

        transicoesHabilitadas.clear();

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getMarca() {
        return marca;
    }

    public void setMarca(int marca) {
        this.marca = marca;
    }

}
