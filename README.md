# 📘 Simulador de Memória Virtual com Paginação por Demanda

Este projeto implementa um simulador de **memória virtual** utilizando paginação por demanda e quatro políticas clássicas de substituição.  
O foco deste README é **documentar o código**, método por método, explicando para que serve cada parte.

---

# 📑 Sumário
- [📘 Descrição Geral](#-descrição-geral)
- [🧮 Cálculos Iniciais](#-cálculos-iniciais)
  - [📏 calcularTamanhoPagina](#-calculartamanhopaginaint-memoriavirtual-int-numpaginas)
  - [🧱 calcularFrames](#-calcularframesint-memoriafisica-int-tamanhopagina)
  - [🔄 calcularSwap](#-calcularswapint-memoriavirtual-int-memoriafisica)
- [🔁 Algoritmos de Substituição](#-algoritmos-de-substituição)
  - [🟦 FIFO](#-fifo--simularfifoint-requisicoes-int-numframes)
  - [🟩 LRU](#-lru--simularlruint-requisicoes-int-numframes)
  - [🟨 RAND](#-rand--simularrandint-requisicoes-int-numframes)
  - [🟪 MIN](#-min--simularminint-requisicoes-int-numframes)
- [🖨️ Métodos de Impressão](#️-métodos-de-impressão)
- [▶️ Método main](#️-método-main)
- [📌 Observações Técnicas](#-observações-técnicas)
- [👤 Autor](#-autor)

---

# 📘 Descrição Geral

O programa simula o funcionamento da **paginação por demanda** em memória virtual, analisando o comportamento dos algoritmos:

- FIFO  
- LRU  
- RAND  
- MIN (Ótimo)

O usuário fornece:

- Memória física  
- Memória virtual  
- Arquitetura  
- Número de páginas  
- Sequências de acessos à memória  

O simulador então processa e executa todas as políticas para cada sequência fornecida.

---

# 🧮 Cálculos Iniciais

## 📏 `calcularTamanhoPagina(int memoriaVirtual, int numPaginas)`

Calcula e retorna o tamanho de uma página.

**Lógica:**  
Divide a memória virtual pelo número de páginas para determinar o tamanho de cada página.

---

## 🧱 `calcularFrames(int memoriaFisica, int tamanhoPagina)`

Calcula quantos frames cabem na memória física.

**Lógica:**  
Divide a memória física pelo tamanho da página.  
Determina quantas páginas podem ficar carregadas simultaneamente.

---

## 🔄 `calcularSwap(int memoriaVirtual, int memoriaFisica)`

Retorna o tamanho da área de swap necessária.

**Lógica:**  
swap = memória virtual – memória física

---

# 🔁 Algoritmos de Substituição

Cada método retorna:


---

## 🟦 FIFO — `simularFIFO(int[] requisicoes, int numFrames)`

### ✔️ Responsabilidade  
Simular o algoritmo **First In, First Out**.

### ✔️ Funcionamento  
- Usa uma **fila (Queue)** para registrar a ordem de chegada das páginas.  
- Ocorre page fault?  
  - Se a memória está cheia → remove a **página mais antiga**.  
- Registra páginas removidas em `swap`.

### ✔️ Estruturas usadas  
- `HashSet` (memória)  
- `LinkedList` (fila FIFO)  
- `HashSet` (swap)

---

## 🟩 LRU — `simularLRU(int[] requisicoes, int numFrames)`

### ✔️ Responsabilidade  
Simular o algoritmo **Least Recently Used**.

### ✔️ Funcionamento  
- Um `HashMap` armazena a última vez que cada página foi acessada.  
- Quando ocorre substituição, remove a página **menos recentemente usada**.  
- Atualiza swap conforme necessário.

### ✔️ Estruturas usadas  
- `HashSet`  
- `HashMap<Integer, Integer>`  
- `HashSet` (swap)

---

## 🟨 RAND — `simularRAND(int[] requisicoes, int numFrames)`

### ✔️ Responsabilidade  
Simular substituição **aleatória**.

### ✔️ Funcionamento  
- Quando ocorre page fault com memória cheia:  
  - Escolhe uma página aleatória utilizando `Random`.  
  - Remove e registra no swap.

### ✔️ Estruturas usadas  
- `HashSet`  
- `ArrayList`  
- `Random`  
- `HashSet` (swap)

---

## 🟪 MIN — `simularMIN(int[] requisicoes, int numFrames)`

### ✔️ Responsabilidade  
Simular o algoritmo **Ótimo (Belady)**.

### ✔️ Funcionamento  
- Ao ocorrer page fault:  
  - Analisa todas as páginas presentes.  
  - Detecta qual página será usada **mais distante no futuro**.  
  - Se alguma não será usada novamente → ela é removida imediatamente.

### ✔️ Estruturas usadas  
- `HashSet` (memória)  
- `HashSet` (swap)

---

# 🖨️ Métodos de Impressão

Os métodos:

- `imprimirFifo`
- `imprimirRand`
- `imprimirLru`
- `imprimirMin`

Cada um:

1. Mede o tempo de execução com `System.nanoTime()`.  
2. Executa o algoritmo correspondente.  
3. Imprime:  
   - Nome do algoritmo  
   - Tempo de execução em segundos  
   - Quantidade de page faults  
   - Páginas enviadas ao swap  

---

# ▶️ Método main

O método principal é responsável por toda a execução do simulador.

---

## 📥 Entrada

- Memória física  
- Memória virtual  
- Arquitetura (não utilizada)  
- Número de páginas  
- Número de sequências  
- Cada sequência de requisições de memória  

---

## ⚙️ Processamento

- Calcula:  
  - Tamanho da página  
  - Número de frames  
  - Tamanho do swap  
- Lê todas as sequências  
- Para cada sequência:  
  - Imprime os valores  
  - Executa FIFO, RAND, LRU e MIN  

---

## 📤 Saída

Para cada sequência, o programa imprime:

- A lista de acessos  
- Resultados de cada política:  
  - Tempo  
  - Page Faults  
  - Páginas removidas  

---

# 📌 Observações Técnicas

- O conjunto `swap` é apenas lógico — não simula armazenamento real.  
- O algoritmo **MIN** é o mais custoso, pois analisa o futuro da sequência.  
- A arquitetura lida na entrada não interfere na execução.  
- Os tempos são impressos em **segundos**.

---

# 👤 Autor
**Vitor Hugo Dutra Marinho**

