# Trabalho LabProg 2 - Java

O código consiste das seguintes classes:

- Main: É a classe que resume a aplicação, é aqui que está o programa com o qual o usuário irá interagir.
- Handler: É a classe que lida com os dados que provém do State e do Dijkstra. A Main consome o Handler para executar suas funcionalidades.
- State: É a classe que lida com o estado dos objetos do programa, no nosso caso apenas os aeroportos. É quem inicializa os objetos na memória e disponibiliza eles para o Handler.
- Cache: É a classe que armazena os objetos na memória. Apenas o State tem acesso direto a ela.
- DatabaseAccess: Classe responsável por administrar o acesso ao banco de dados.
- Conexao: Classe responsável por abrir a conexão com o banco de dados MySql.
- Dijkstra/ShortestPath: Classes que realizam os tratamentos matemáticos para o algoritmo de Dijkstra
- Aeroporto: Classe que representa a modelagem de um Aeroporto.
- Triple: Classe auxiliar para lidar com alguns dados.

## Main

![Untitled](Trabalho%20LabProg%202%20-%20Java%20ca27ae6c3cc34314b74cfba1fad93cb7/Untitled.png)

Aqui concentramos o Menu de opções do usuário. 

Essas opções são inicializadas em um vetor de String que facilita a adição de futuras funcionalidades.

A interação do usuário acontece em um laço que só quebra caso ele escolha isso pelo console.

A depender da escolha as funções do Handler são chamadas.

## Handler

![Untitled](Trabalho%20LabProg%202%20-%20Java%20ca27ae6c3cc34314b74cfba1fad93cb7/Untitled%201.png)

O Menu exibe todas as Unidades Federativas dos aeroportos cadastrados no banco e permite ao usuário escolher uma. 

A partir da escolha do usuário o console irá imprimir todos os aeroportos existentes naquele estado com as suas respectivas cidades

![Untitled](Trabalho%20LabProg%202%20-%20Java%20ca27ae6c3cc34314b74cfba1fad93cb7/Untitled%202.png)

É a função que expõe ao usuário a possibilidade de determinar a menor rota existente entre um aeroporto de origem e um aeroporto de destino, que serão escolhidos pelo usuário via linha de terminal.

O sistema realiza a validação das entradas e, no caso de ambas corretas, segue efetivamente para o cálculo.

![Untitled](Trabalho%20LabProg%202%20-%20Java%20ca27ae6c3cc34314b74cfba1fad93cb7/Untitled%203.png)

Aqui iniciamos o cálculo de rotas.

Criamos todos os caminhos possíveis e armazenamos em uma lista de Triple. Usamos a coleção auxiliar para armazenar em um objeto só os dados de: Origem - Destino - Distância para todos os aeroportos que temos no banco.

Depois da criação dos caminhos precisamos eliminar a rota direta entre a Origem e o Destino por conta da restrição de ter ao menos uma parada, nesse caso atribuímos distância máxima para essa rota.

Depois disso inicializamos o objeto que será responsável por aplicar o algoritmo de Dijkstra nos caminhos fornecidos.

![Untitled](Trabalho%20LabProg%202%20-%20Java%20ca27ae6c3cc34314b74cfba1fad93cb7/Untitled%204.png)

Essa é a função utilizada para calcular a distância entre 2 aeroportos de acordo com os seus dados de Latitude, Longitude e Altitude. 

Seu retorno é a tripla que será armazenada na lista de caminhos.

## Dijkstra/ShortestPath

![Untitled](Trabalho%20LabProg%202%20-%20Java%20ca27ae6c3cc34314b74cfba1fad93cb7/Untitled%205.png)

No construtor da classe recebemos a tripla de caminhos que será convertida na forma de uma matriz de adjacência.

A matriz de adjacência é uma estrutura algébrica que indica o peso do caminho quando partimos de um ponto e vamos para o outro. Ela será consumida posteriormente para o cálculo da menor rota.

Como a matriz é orientada numericamente (de 0 a N), precisamos conhecer a associação entre o índice da linha/coluna com seu respectivo aeroporto, para isso temos um enumerador, que é inicializado junto com essa associação.

![Untitled](Trabalho%20LabProg%202%20-%20Java%20ca27ae6c3cc34314b74cfba1fad93cb7/Untitled%206.png)

Com essa função temos, dado um código IATA de um aeroporto, o seu índice na matriz.

No Handler, quando vamos efetivamente calcular a menor rota, chamamos o método menorRota da classe Dijkstra.

![Untitled](Trabalho%20LabProg%202%20-%20Java%20ca27ae6c3cc34314b74cfba1fad93cb7/Untitled%207.png)

Esse método apenas converte a String do código IATA dos aeroportos para os seus índices e chama o método interno ao objeto calculador, que foi inicializado no construtor e possui a aplicação do algoritmo

![Untitled](Trabalho%20LabProg%202%20-%20Java%20ca27ae6c3cc34314b74cfba1fad93cb7/Untitled%208.png)

Esse método é o arremate na lógica do Dijkstra.

Ele consome uma matriz de adjacência e calcula iterativamente todas as menores distâncias a partir de um ponto específico definido como origem.

Após o cálculo, a solução contendo Origem, Destino, a Escala e a Distância é impressa no console.

![Untitled](Trabalho%20LabProg%202%20-%20Java%20ca27ae6c3cc34314b74cfba1fad93cb7/Untitled%209.png)

## State/Cache

![Untitled](Trabalho%20LabProg%202%20-%20Java%20ca27ae6c3cc34314b74cfba1fad93cb7/Untitled%2010.png)

O State controla o acesso aos dados em memória.

Os dados em memória são representados pela classe Cache, que é inicializada no momento que um State é construído.

O Cache se comunica com o objeto que representa o acesso ao banco de dados.

## DatabaseAccess

![Untitled](Trabalho%20LabProg%202%20-%20Java%20ca27ae6c3cc34314b74cfba1fad93cb7/Untitled%2011.png)

Classe que possui conexão com o banco de dados e realiza a consulta para criar o objeto que vai alimentar o funcionamento geral do código.
Classe que possui conexão com o banco de dados e realiza a consulta para criar o objeto que vai alimentar o funcionamento geral do código.
