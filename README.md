**PNSimulator**

Executando o programa


****

`O programa simula uma rede de Petri do tipo lugar-transição temporizada.`

`Dentro da classe Main.java está como default a rede de Petri 
de exemplo da proposta do simulador, sendo que é possível
alterar lugares,transições,arcos (direção dos arcos),pesos,
e marcas dentro de Main.java
`

`A rede é capaz de executar todos os requisitos funcionais, como
habilitar e desabilitar transições dependendo dos pesos
dos arcos e marcas dos lugares;`

`Habilitar e desabilitar transições dependendo do tempo que
cada marca precisa ficar em um lugar (cada lugar possui um
tempo default que é sorteado ao iniciar a rede);`

`É capaz de sortear qual transição irá consumir uma marca
no caso de conflito/disputa por marca`

__

`Pressione a tecla 'p' ou 'P' para dar sequencia aos ciclos
da rede ou pressione a tecla 's' ou 'S' para encerrar;`

****

Observações e Limitações

`O programa não carrega dados de entrada de usuário através de
arquivo, o usuário precisa alterar código-fonte no caso de
querer configurar uma rede`

__

`Para informar qunantos lugares e transições em uma rede
basta alterar o tamanho dos atributos 'lugares' e 'transições';
`

`Para preencher cada lugar com marca(s) altere o atributo
'marcas[]' onde o primeiro elemento sera a quantidade de
marcas do primeiro lugar e assim sucessivamente;`

`Por fim, para dar direção aos arcos edite a inserção de
arcos com os respectivos lugares e transições como desejar,
bem como os pesos de cada arco (essa etapa pode ser alterada
a partir da linha 50)
`

By Michael and Édson