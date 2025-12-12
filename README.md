📘 Simulador de Memória Virtual com Paginação por Demanda

Este projeto implementa um simulador de memória virtual utilizando paginação por demanda e quatro políticas clássicas de substituição.
O foco deste README é documentar o código, método por método, explicando para que serve cada parte.

📑 Sumário

📘 Descrição Geral

🧮 Cálculos Iniciais

calcularTamanhoPagina

calcularFrames

calcularSwap

🔁 Algoritmos de Substituição

FIFO — simularFIFO

LRU — simularLRU

RAND — simularRAND

MIN — simularMIN

🖨️ Métodos de Impressão

▶️ Método main

📌 Observações Técnicas

👤 Autor

📘 Descrição Geral

O programa simula o funcionamento da paginação por demanda em memória virtual, analisando o comportamento das políticas de substituição FIFO, LRU, RAND e MIN.

O usuário fornece:

Memória física

Memória virtual

Arquitetura 

Número de páginas

Sequências de acessos à memória

A partir disso, o simulador calcula estatísticas e executa todas as políticas em cada sequência.

🧮 Cálculos Iniciais
📏 calcularTamanhoPagina(int memoriaVirtual, int numPaginas)

Calcula e retorna o tamanho de uma página de memória.

Lógica:
Divide a memória virtual pelo número total de páginas.
Isso determina quantos bytes (ou unidades) cada página possui.

🧱 calcularFrames(int memoriaFisica, int tamanhoPagina)

Calcula quantos frames cabem na memória física.

Lógica:
Divide a memória física pelo tamanho da página.
Isso indica quantas páginas podem estar carregadas simultaneamente.

🔄 calcularSwap(int memoriaVirtual, int memoriaFisica)

Retorna a quantidade de memória que ficará no swap.

Lógica:
É a diferença entre a memória virtual total e a física disponível.

🔁 Algoritmos de Substituição

Cada método simula uma política e retorna:

Object[] { pageFaults, conjuntoDePaginasRemovidas }

🟦 FIFO — simularFIFO(int[] requisicoes, int numFrames)

Responsabilidade:
Simular a substituição First In, First Out.

Como funciona internamente:

Mantém uma fila (Queue) representando a ordem de chegada das páginas.

Se ocorre page fault e a memória está cheia, remove a página mais antiga.

Armazena páginas removidas no conjunto swap.

Estruturas utilizadas:
✔️ HashSet (memória)
✔️ LinkedList (fila FIFO)
✔️ HashSet (swap)

🟩 LRU — simularLRU(int[] requisicoes, int numFrames)

Responsabilidade:
Simular o algoritmo Least Recently Used.

Como funciona:

Armazena a última posição de uso de cada página em um HashMap.

Para substituir, procura a página com último uso mais antigo.

Remove essa página e atualiza swap.

Estruturas utilizadas:
✔️ HashSet (memória)
✔️ HashMap<Integer, Integer> (último uso)
✔️ HashSet (swap)

🟨 RAND — simularRAND(int[] requisicoes, int numFrames)

Responsabilidade:
Aplicar substituição aleatória.

Como funciona:

Ao ocorrer page fault com a memória cheia, escolhe uma página aleatória e remove.

Usa Random para selecionar um índice da lista.

Estruturas utilizadas:
✔️ HashSet
✔️ ArrayList
✔️ Random
✔️ HashSet (swap)

🟪 MIN — simularMIN(int[] requisicoes, int numFrames)

Responsabilidade:
Simular o algoritmo Ótimo.

Como funciona:

Para cada page fault com memória cheia:

Analisa todas as páginas presentes.

Procura aquela que será usada mais distante no futuro.

Se alguma não for usada novamente, ela é escolhida imediatamente.

Remove apenas a ideal.

Estruturas utilizadas:
✔️ HashSet (memória)
✔️ HashSet (swap)

🖨️ Métodos de Impressão

Cada método (imprimirFifo, imprimirRand, imprimirLru, imprimirMin) faz:

Mede o tempo com nanoTime()

Executa a simulação da política correspondente

Imprime:

Nome da política

Tempo de execução

Número de page faults

Páginas enviadas ao swap

Os métodos são praticamente idênticos, mudando apenas qual algoritmo chamam.

▶️ Método main

O método principal é responsável por:

📥 Entrada:

Lê a memória física e virtual

Lê arquitetura (não usada)

Número de páginas

Número de sequências

Cada sequência de requisições

⚙️ Processamento:

Calcula:

Tamanho da página

Número de frames físicos

Tamanho do swap

Armazena todas as sequências em um vetor de arrays

📤 Saída:

Para cada sequência:

Imprime a sequência

Executa FIFO, RAND, LRU e MIN

Imprime os resultados

📌 Observações Técnicas

O swap é representado apenas como conjunto lógico (não há armazenamento físico).

O algoritmo MIN pode ser custoso, pois olha para o futuro a cada page fault.

A arquitetura lida na entrada não interfere nas simulações.

Os tempos de execução impressos estão em segundos.

👤 Autor

Vitor Hugo Dutra Marinho