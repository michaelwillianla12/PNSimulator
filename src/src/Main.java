import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static LinkedList<Lugares> listaLugares;
    private static LinkedList<Transicoes> listaTransicoes;
    private static LinkedList<Arcos> listaArcos;
    private static LinkedList<Transicoes> transicoesHabilitadas;
    private static ArrayList<String> statusRede = new ArrayList<String>();
    private static ArrayList somenteHabilitadas;

    //private static Scanner entrada = new Scanner(System.in);
    //private static int keyPressed;
    //private static boolean exit;

    // Configure aqui a quantidade de lugares, transiçoes, arcos, bem como as marcas em cada lugar e os pesos e direções dos arcos

    private static int lugares = 8;
    private static int transicoes = 5;
    private static int marcas[] = {2, 0, 2, 0, 5, 0, 0, 0}; // aqui mesmo tamanho da quantidade de lugares, mudar se for o caso
    private static int z = 0;

    public static void main(String args[]) {

        listaLugares = new LinkedList<>();
        listaTransicoes = new LinkedList<>();
        listaArcos = new LinkedList<>();
        transicoesHabilitadas = new LinkedList<>();
        somenteHabilitadas = new ArrayList();
        boolean sair = false, proximo = false;
        String linha = "", status = "":
        Scanner entrada = new Scanner(System.in);

        // adiciona instancias de lugares em uma lista encadeada

        for (int i = 0; i < lugares; i++)
            listaLugares.add(new Lugares(i, marcas[i]));

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
        
        for(int i = 0; i < lugares.size(); i++)
            status += "L" + ((i < 10) ? "0" : "") + String.valueOf(i) + "|";
        
        for(int i = 0; i < transicoes.size(); i++)
            status += "T" + ((i < 10) ? "0" : "") + String.valueOf(i) + "|";
        
        statusRede.add(status);

        verificaStatusDaRede();

        white(!sair) {
            z++;

            if(z % listaLugares.get(0).getTempo() == 0)
                listaLugares.get(0).setMarca(listaLugares.get(0).getMarca() + 1);

            atualizaEstados();

            System.out.println("");
            
            System.out.println("Pressione 'p' para próximo ciclo ou 's' para sair");
            linha = entrada.nextLine();
            
            if(linha.length() == 1 && (linha.charAt(0) == 's' || linha.charAt(0) == 'S'))
                sair = true;
            else if(linha.length() == 1 && (linha.charAt(0) == 'p' || linha.charAt(0) == 'P'))
                proximo = true;
            
            while(!sair && !proximo){
                System.out.println("Pressione 'p' para próximo ciclo ou 's' para sair");
                linha = entrada.nextLine();
                
                if(linha.length() == 1 && (linha.charAt(0) == 's' || linha.charAt(0) == 'S'))
                    sair = true;
                else if(linha.length() == 1 && (linha.charAt(0) == 'p' || linha.charAt(0) == 'P'))
                    proximo = true;
            }
        }

        entrada.close();

        //entrada.nextLine();
        //keyPressed = KeyEvent.KEY_PRESSED;

    }

    private static void atualizaEstados() {

      //  int sorteado = sorteia();
        int[] ramdom = new int[2];

            ramdom = sorteiaTransicao();

        System.out.println(ramdom[1]);

       // if(ramdom != -1)
         //   System.out.println(ramdom);


        // System.out.println(transicoesHabilitadas.get(i).isHabilitada());
        for (int i = 0; i < transicoesHabilitadas.size(); i++) {

            //   System.out.println("aidi"+transicoesHabilitadas.get(sorteado).getId());

            if(listaTransicoes.get(i).isHabilitada() == true) {

                for (int j = 0; j < listaArcos.size(); j++) {

                    //verifica cada arco dirigido para uma transicao e consome marcas de quantidade dos pesos do arco
                    if (listaArcos.get(j).getTipoDestino() == 't') {

                       // System.out.println(listaLugares.get(0));
                       // System.out.println(listaLugares.get(listaArcos.get(j).getOrigem()).getId());


                        if (listaArcos.get(j).getDestino() == transicoesHabilitadas.get(i).getId()) {


                            listaLugares.get(listaArcos.get(j).getOrigem()).setMarca(listaLugares.get(listaArcos.get(j).getOrigem()).getMarca() - listaArcos.get(j).getPesoDoArco());
                            listaLugares.get(listaArcos.get(j).getOrigem()).setTransicoesHabilitadas(listaArcos.get(j).getDestino());

                            if(listaLugares.get(listaArcos.get(j).getOrigem()).getId() != listaLugares.get(0).getId())
                                if (listaLugares.get(listaArcos.get(j).getOrigem()).getMarca() == 0)
                                    listaLugares.get(listaArcos.get(j).getOrigem()).setFimProcesso(-1);
                          //  System.out.println(listaLugares.get(listaArcos.get(j).getOrigem()).getId());
                        }
                    }

                    if (listaArcos.get(j).getTipoDestino() == 'l') {

                        if (listaArcos.get(j).getOrigem() == transicoesHabilitadas.get(i).getId()){
                            listaLugares.get(listaArcos.get(j).getDestino()).setMarca(listaLugares.get(listaArcos.get(j).getDestino()).getMarca() + listaArcos.get(j).getPesoDoArco());
                            if(listaLugares.get(listaArcos.get(j).getDestino()).getId() != listaLugares.get(0).getId())
                                if(listaLugares.get(listaArcos.get(j).getDestino()).getFimProcesso() == -1)
                                    listaLugares.get(listaArcos.get(j).getDestino()).setFimProcesso(z + listaLugares.get(listaArcos.get(j).getDestino()).getTempo());
                        }

                    }

                }


                verificaStatusDaRede();

                i = transicoesHabilitadas.size();

            }
        }
    }

    private static void verificaStatusDaRede() {

        String status = ((z < 10) ? "00" : "0") + String.valueOf(z) + "  |";
        
        for (int i = 0; i < lugares.size(); i++)
            status += ((lugares.get(i).getMarca() < 10) ? "00" : "0") + String.valueOf(lugares.get(i).getMarca()) + "|";

        for (int i = 0; i < transicoes.size(); i++){
            status += ((transicoes.get(i).isHabilitada()) ? "sim" : "nao") + "|";
        }
        
        statusRede.add(status);
        
        for(int i = 0; i < statusRede.size(); i++)
            System.out.println(statusRede.get(i)); 

        setHabilitada();

        transicoesHabilitadas();
    }

    private static void setHabilitada() {
        int cont;

        for (int i = 0; i < listaTransicoes.size(); i++) {
            listaTransicoes.get(i).setHabilitada(false);
            cont = 0;

            for (int j = 0; j < listaArcos.size(); j++) {
                if (listaArcos.get(j).getTipoDestino() == 't') {
                    if(listaTransicoes.get(i).getId() == listaArcos.get(j).getDestino()) {
                        if (listaArcos.get(j).getPesoDoArco() <= listaLugares.get(listaArcos.get(j).getOrigem()).getMarca()) {
                            if(listaLugares.get(listaArcos.get(j).getOrigem()).getFimProcesso() >= z){
                                listaLugares.get(listaArcos.get(j).getOrigem()).setTransicoesHabilitadas(listaArcos.get(j).getDestino());
                                listaTransicoes.get(i).setHabilitada(true);
                                
                                if(listaTransicoes.get(i).getLugares() > 1)
                                    cont ++;
                            }
                        }   
                    }
                }
            }

            if(listaTransicoes.get(i).getLugares() > 1 && cont != listaTransicoes.get(i).getLugares()){
                listaTransicoes.get(i).setHabilitada(false);
                for (int j = 0; j < listaArcos.size(); j++) {
                    if (listaArcos.get(j).getTipoDestino() == 't') {
                        if(listaTransicoes.get(i).getId() == listaArcos.get(j).getDestino()) {
                            if (listaArcos.get(j).getPesoDoArco() <= listaLugares.get(listaArcos.get(j).getOrigem()).getMarca()) {
                                if(listaLugares.get(listaArcos.get(j).getOrigem()).getFimProcesso() >= z){
                                    listaLugares.get(listaArcos.get(j).getOrigem()).removeHabilitada(listaArcos.get(j).getDestino());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    /*
    private static void transicoesHabilitadas() {

        // mostra na saída as transicoes habilitadas e nao habilitadas
        for (int i = 0; i < listaTransicoes.size(); i++) {
            if (listaTransicoes.get(i).isHabilitada()) {
               // transicoesHabilitadas.add(listaTransicoes.get(i));

                System.out.println("Transição " + listaTransicoes.get(i).getId() + " está habilitada");
            } else {

                System.out.println("Transição " + listaTransicoes.get(i).getId() + " não está habilitada");
            }
        }

        System.out.println();
    }
    */
    private static int[] sorteiaTransicao(){

        ArrayList<Integer> temp = new ArrayList();
        Random r = new Random();

        int[] sorteado = new int[2];

        for (int i = 0; i < listaLugares.size(); i++) {


            if(listaLugares.get(i).getTransicoesHabilitadas().size() > 1) {
                temp.addAll(listaLugares.get(i).getTransicoesHabilitadas());

                sorteado [1] = listaLugares.get(i).getId();

            }
            // depois de sortear reseta o array de transicoes habilitadas para nao duplicar informacao
            listaLugares.get(i).resetTH();

        }

        if(temp.size() > 0) {
             sorteado[0] = temp.get(r.nextInt(temp.size()));
        }else
            sorteado[0] = -1;

        return sorteado;
    }

}



