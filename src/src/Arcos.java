public class Arcos {


    private int origem;
    private int destino;
    private int pesoDoArco;
    private char tipoDestino;


    public Arcos(Lugares origem, Transicoes destino, int pesoDoArco, char tipoDestino) {

        this.origem = origem.getId();
        this.destino = destino.getId();
        this.pesoDoArco = pesoDoArco;
        this.tipoDestino = tipoDestino;

    }

    public Arcos(Transicoes origem, Lugares destino, int pesoDoArco, char tipoDestino) {

        this.origem = origem.getId();
        this.destino = destino.getId();
        this.pesoDoArco = pesoDoArco;
        this.tipoDestino = tipoDestino;

    }

    public int getOrigem() {
        return origem;
    }

    public void setOrigem(int origem) {
        this.origem = origem;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }


    public int getPesoDoArco() {
        return pesoDoArco;
    }

    public void setPesoDoArco(int pesoDoArco) {
        this.pesoDoArco = pesoDoArco;
    }

    public char getTipoDestino() {
        return tipoDestino;
    }

    public void setTipoDestino(char tipoDestino) {
        this.tipoDestino = tipoDestino;
    }


}