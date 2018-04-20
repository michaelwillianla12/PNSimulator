import java.util.ArrayList;

public class Lugares {

    private int id,marca, tempo, fimProcesso;

    private ArrayList transicoesHabilitadas = new ArrayList();


    public Lugares(int id, int marca, int tempo){

        this.id = id;
        this.marca = marca;
        this.tempo = tempo;
        this.fimProcesso = -1;

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

    public void removeHabilitada(int remover){
        for (int i = 0; i < transicoesHabilitadas.size(); i++){
            if(transicoesHabilitadas.get(i) == remover)
                transicoesHabilitadas.remove(remover);
        }
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

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getFimProcesso() {
        return fimProcesso;
    }

    public void setFimProcesso(int fimProcesso) {
        this.fimProcesso = fimProcesso;
    }

}
