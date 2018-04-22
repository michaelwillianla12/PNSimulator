import java.util.*;

public class Main {

    private static LinkedList<Lugares> listaLugares;
    private static LinkedList<Transicoes> listaTransicoes;
    private static LinkedList<Arcos> listaArcos;
    private static LinkedList<Transicoes> transicoesHabilitadas;
    private static ArrayList<String> statusRede = new ArrayList<>();
    private static ArrayList<Marca> marcasEmTransicao = new ArrayList<>();

    private static int ciclo = 0; //tempo de ciclo
    private static int zTime = 0; //tempo global

    // Configure aqui a quantidade de lugares, transiçoes e a quantidade de marcas na mesma ordem dos lugares

    private static int lugares = 8;
    private static int transicoes = 5;
    private static int marcas[] = {2, 0, 2, 0, 5, 0, 0, 0}; // aqui um vetor de mesmo tamanho da quantidade de lugares existentes

    public static void main(String args[]) {

        listaLugares = new LinkedList<>();
        listaTransicoes = new LinkedList<>();
        listaArcos = new LinkedList<>();
        transicoesHabilitadas = new LinkedList<>();
        boolean sair = false, proximo = false;
        String linha = "", status = "";
        Scanner entrada = new Scanner(System.in);
        int tempoLugares = 0;


        // adiciona instancias de lugares e suas marcas em uma lista encadeada
        for (int i = 0; i < lugares; i++) {

            //sorteia o tempo para cada lugar criado tempo vai de 0 a 21
            tempoLugares = (int) (Math.random() * 11);
            System.out.println(tempoLugares);

            //adiciona lugares com marcas em uma lista encadeada
            listaLugares.add(new Lugares(i, tempoLugares));
            for (int j = 0; j < marcas[i]; j++)
                listaLugares.get(i).setMarca(new Marca(0));
        }

        // adiciona instancias de transicoes em uma lista encadeada
        for (int i = 0; i < transicoes; i++) {
            listaTransicoes.add(new Transicoes(i));
            transicoesHabilitadas.add(listaTransicoes.get(i));
        }
        // aqui setar origem (lugar), destino (transicao) e o peso do arco
        listaArcos.add(new Arcos(listaLugares.get(0), listaTransicoes.get(0), 1, 't'));
        listaArcos.add(new Arcos(listaLugares.get(0), listaTransicoes.get(4), 2, 't'));
        listaArcos.add(new Arcos(listaLugares.get(1), listaTransicoes.get(1), 1, 't'));
        listaArcos.add(new Arcos(listaLugares.get(2), listaTransicoes.get(1), 2, 't'));
        listaArcos.add(new Arcos(listaLugares.get(3), listaTransicoes.get(2), 1, 't'));
        listaArcos.add(new Arcos(listaLugares.get(4), listaTransicoes.get(1), 3, 't'));
        listaArcos.add(new Arcos(listaLugares.get(5), listaTransicoes.get(3), 1, 't'));
        listaArcos.add(new Arcos(listaLugares.get(6), listaTransicoes.get(3), 1, 't'));

        //aqui setar origem (transicao), destino (lugar) e o peso do arco
        listaArcos.add(new Arcos(listaTransicoes.get(0), listaLugares.get(1), 1, 'l'));
        listaArcos.add(new Arcos(listaTransicoes.get(1), listaLugares.get(3), 1, 'l'));
        listaArcos.add(new Arcos(listaTransicoes.get(2), listaLugares.get(2), 2, 'l'));
        listaArcos.add(new Arcos(listaTransicoes.get(2), listaLugares.get(5), 1, 'l'));
        listaArcos.add(new Arcos(listaTransicoes.get(2), listaLugares.get(6), 1, 'l'));
        listaArcos.add(new Arcos(listaTransicoes.get(3), listaLugares.get(4), 3, 'l'));
        listaArcos.add(new Arcos(listaTransicoes.get(3), listaLugares.get(7), 1, 'l'));
        listaArcos.add(new Arcos(listaTransicoes.get(4), listaLugares.get(1), 1, 'l'));

        status = "Ciclo|";

        for (int i = 0; i < listaLugares.size(); i++)
            status += "L" + ((i < 10) ? "0" : "") + String.valueOf(i) + "|";

        for (int i = 0; i < listaTransicoes.size(); i++)
            status += "T" + ((i < 10) ? "0" : "") + String.valueOf(i) + "|";

        statusRede.add(status);

        //laco da rede/interação com o usuário
        while (!sair) {

            System.out.println("Tempo global: " + zTime);

            setHabilitadaTransicao();

            verificaStatusDaRede();
            atualizaEstados();

            ciclo++;

            //decrementa marcas se houver
            for (int i = 0; i < listaLugares.size(); i++) {
                if (listaLugares.get(i).isRecebeuMarca() == true)
                    listaLugares.get(i).decTempoMarcas();
            }

            System.out.println("");

            System.out.println("Pressione 'p' para próximo ciclo ou 's' para sair");
            linha = entrada.nextLine();

            if (linha.length() == 1 && (linha.charAt(0) == 's' || linha.charAt(0) == 'S'))
                sair = true;
            else if (linha.length() == 1 && (linha.charAt(0) == 'p' || linha.charAt(0) == 'P'))
                proximo = true;

            while (!sair && !proximo) {
                System.out.println("Pressione 'p' para próximo ciclo ou 's' para sair");
                linha = entrada.nextLine();

                if (linha.length() == 1 && (linha.charAt(0) == 's' || linha.charAt(0) == 'S'))
                    sair = true;
                else if (linha.length() == 1 && (linha.charAt(0) == 'p' || linha.charAt(0) == 'P'))
                    proximo = true;
            }
        }

        entrada.close();
    }

    //metodo que atualiza a rede, retira ou insere marcas em lugares
    private static void atualizaEstados() {

        //  int sorteado = sorteia();
        // int[] ramdom = new int[2];

        // ramdom = sorteiaTransicao();

        // System.out.println(ramdom[1]);

        // if(ramdom != -1)
        //   System.out.println(ramdom);

        for (int i = 0; i < transicoesHabilitadas.size(); i++) {

            if (listaTransicoes.get(i).isHabilitada() == true) {

                for (int j = 0; j < listaArcos.size(); j++) {

                    //verifica cada arco dirigido para uma transicao e consome marcas de quantidade dos pesos do arco
                    if (listaArcos.get(j).getTipoDestino() == 't') {

                        if (listaArcos.get(j).getDestino() == transicoesHabilitadas.get(i).getId()) {

                            //aqui exclui marcas dependendo do peso do arco
                            listaLugares.get(listaArcos.get(j).getOrigem()).retiraMarca(listaArcos.get(j).getPesoDoArco());
                            listaLugares.get(listaArcos.get(j).getOrigem()).setTransicoesHabilitadas(listaArcos.get(j).getDestino());

                        }
                    }

                    if (listaArcos.get(j).getTipoDestino() == 'l') {

                        if (listaArcos.get(j).getOrigem() == transicoesHabilitadas.get(i).getId()) {

                            for (int k = 0; k < listaArcos.get(j).getPesoDoArco(); k++) {
                                listaLugares.get(listaArcos.get(j).getDestino()).setMarca(new Marca(listaLugares.get(listaArcos.get(j).getDestino()).getTempo()));
                                listaLugares.get(listaArcos.get(j).getDestino()).setRecebeuMarca(true);

                            }
                            zTime += listaLugares.get(listaArcos.get(j).getDestino()).getTempo();
                        }
                    }
                }

                i = transicoesHabilitadas.size();
            }
        }
    }

    private static void verificaStatusDaRede() {

        String status = ((ciclo < 10) ? "00" : "0") + String.valueOf(ciclo) + "  |";

        for (int i = 0; i < listaLugares.size(); i++)
            status += ((listaLugares.get(i).getQntMarcas() < 10) ? "00" : "0") + String.valueOf(listaLugares.get(i).getQntMarcas()) + "|";

        for (int i = 0; i < listaTransicoes.size(); i++) {
            status += ((listaTransicoes.get(i).isHabilitada()) ? "sim" : "nao") + "|";
        }

        statusRede.add(status);

        for (int i = 0; i < statusRede.size(); i++)
            System.out.println(statusRede.get(i));

        setHabilitadaTransicao();

        System.out.println();
    }

    //metodo que habilita ou desabilita transições
    private static void setHabilitadaTransicao() {

        for (int i = 0; i < listaTransicoes.size(); i++) {


            for (int j = 0; j < listaArcos.size(); j++) {

                if (listaArcos.get(j).getTipoDestino() == 't') {

                    if (listaTransicoes.get(i).getId() == listaArcos.get(j).getDestino()) {

                        if ((listaArcos.get(j).getPesoDoArco() <= listaLugares.get(listaArcos.get(j).getOrigem()).getQntMarcas())) {

                            listaLugares.get(listaArcos.get(j).getOrigem()).setTransicoesHabilitadas(listaArcos.get(j).getDestino());
                            listaTransicoes.get(i).setHabilitada(true);

                        } else {

                            listaTransicoes.get(i).setHabilitada(false);

                            if (i != listaTransicoes.size() - 1) {
                                i++;
                                j = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    private static int[] sorteiaTransicao() {

        ArrayList<Integer> temp = new ArrayList();
        Random r = new Random();

        int[] sorteado = new int[2];

        for (int i = 0; i < listaLugares.size(); i++) {


            if (listaLugares.get(i).getTransicoesHabilitadas().size() > 1) {
                temp.addAll(listaLugares.get(i).getTransicoesHabilitadas());

                sorteado[1] = listaLugares.get(i).getId();

            }
            // depois de sortear reseta o array de transicoes habilitadas para nao duplicar informacao
            listaLugares.get(i).resetTH();

        }

        if (temp.size() > 0) {
            sorteado[0] = temp.get(r.nextInt(temp.size()));
        } else
            sorteado[0] = -1;

        return sorteado;
    }
}