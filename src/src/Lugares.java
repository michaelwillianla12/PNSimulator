import java.util.ArrayList;

public class Lugares {

    private int id;
    private int tempo;

    private ArrayList transicoesHabilitadas = new ArrayList();
    private ArrayList<Marca> marcas = new ArrayList<>();
    private boolean recebeuMarca;

    public Lugares(int id, int tempo){

        super();
        this.id = id;
        this.tempo = tempo;
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


    public void retiraMarca(int qnt) {
        for (int i = 0; i < qnt; i++)
            marcas.remove(marcas.get(0));

        if(marcas.size() == 0)
            setRecebeuMarca(false);
    }

    public void setMarca(Marca marca) {

        marcas.add(marca);
    }

    public int getQntMarcas() {

        int qntMarcas = 0;

        for (int i = 0; i <marcas.size() ; i++) {
            if(marcas.get(i).getTempo() == 0)
            qntMarcas ++;

        }
        return qntMarcas;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public boolean isRecebeuMarca() {
        return recebeuMarca;
    }

    public void setRecebeuMarca(boolean recebeuMarca) {
        this.recebeuMarca = recebeuMarca;
    }

    //decrementa tempo de todas as marcas de um lugar
    public void decTempoMarcas(){

        for (int i = 0; i <marcas.size() ; i++) {
            System.out.println("Lugar "+this.getId());
            System.out.println("Marca numero "+marcas.get(i).getId());
            System.out.println("Antes de decrem"+marcas.get(i).getTempo());
            if(marcas.get(i).getTempo() > 0)
            marcas.get(i).decTempo();
            System.out.println("Depois de decrem"+marcas.get(i).getTempo());

        }

    }

}

