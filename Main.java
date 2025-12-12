import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {

    static int calcularTamanhoPagina(int memoriaVirtual, int numPaginas) {
        return memoriaVirtual / numPaginas;
    }

    static int calcularFrames(int memoriaFisica, int tamanhoPagina) {
        return memoriaFisica / tamanhoPagina;
    }

    static int calcularSwap(int memoriaVirtual, int memoriaFisica) {
        return memoriaVirtual - memoriaFisica;
    }

    static Object[] simularFIFO(int[] requisicoes, int numFrames) {
        Set<Integer> memoria = new HashSet<>();
        Queue<Integer> fila = new LinkedList<>();
        Set<Integer> swap = new HashSet<>();
        int pageFaults = 0;

        for (int req : requisicoes) {   
            if (!memoria.contains(req)) {
                pageFaults++;
                if (memoria.size() == numFrames) {
                    int removido = fila.poll();
                    memoria.remove(removido);
                    swap.add(removido);
                }
                memoria.add(req);
                fila.add(req);
                swap.remove(req);
            }
        }
        return new Object[]{pageFaults, swap};
    }

    static  Object[] simularLRU(int[] requisicoes, int numFrames) {
        Set<Integer> memoria = new HashSet<>();
        Map<Integer, Integer> ultimoUso = new HashMap<>();
        Set<Integer> swap = new HashSet<>();
        int pageFaults = 0;
        

        for (int i = 0; i < requisicoes.length; i++) {
            int req = requisicoes[i];
            if (!memoria.contains(req)) {
                pageFaults++;
                if (memoria.size() == numFrames) {
                    int lru = -1, min = Integer.MAX_VALUE;
                    for (int p : memoria) {
                        if (ultimoUso.get(p) < min) {
                            min = ultimoUso.get(p);
                            lru = p;
                        }
                    }
                    memoria.remove(lru);
                    swap.add(lru);
                }
                memoria.add(req);
                swap.remove(req);
            }
            ultimoUso.put(req, i);
        }
        return new Object[]{pageFaults, swap};
    }

    static Object[] simularRAND(int[] requisicoes, int numFrames) {
        Set<Integer> memoria = new HashSet<>();
        List<Integer> lista = new ArrayList<>();
        Set<Integer> swap = new HashSet<>();
        Random rand = new Random();
        int pageFaults = 0;

        for (int req : requisicoes) {
            if (!memoria.contains(req)) {
                pageFaults++;
                if (memoria.size() == numFrames) {
                    int idx = rand.nextInt(lista.size());
                    int removido = lista.get(idx);
                    memoria.remove(removido);
                    lista.remove(idx);
                    swap.add(removido);
                }
                memoria.add(req);
                lista.add(req);
                swap.remove(req);
            }
        }
        return new Object[]{pageFaults, swap};
    }

    static Object[] simularMIN(int[] requisicoes, int numFrames) {
    Set<Integer> memoria = new HashSet<>();
    Set<Integer> swap = new HashSet<>();
    
    int pageFaults = 0;

    for (int i = 0; i < requisicoes.length; i++) {
        int req = requisicoes[i];
        if (!memoria.contains(req)) {
            pageFaults++;
            if (memoria.size() == numFrames) {
                int substituir = -1;
                int maisDistante = -1;

                for (int p : memoria) {
                    int j;
                    for (j = i + 1; j < requisicoes.length; j++) {
                        if (requisicoes[j] == p) break;
                    }
                    if (j == requisicoes.length) {
                        substituir = p;
                        break;
                    } else if (j > maisDistante) {
                        maisDistante = j;
                        substituir = p;
                    }
                }

                memoria.remove(substituir);
                swap.add(substituir);
            }

            memoria.add(req);
            swap.remove(req);
        }
    }

    return new Object[]{pageFaults, swap};
}


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int memoriaFisica = sc.nextInt();
        int memoriaVirtual = sc.nextInt();
        String arquitetura = sc.next();
        int numPaginas = sc.nextInt();
        sc.nextLine(); // quebra de linha

        sc.nextLine(); // linha em branco
        int numSequencias = sc.nextInt();
        sc.nextLine(); // quebra de linha

        //Leitura e armazenamento de sequencias digitadas
        int[][] todasAsSequencias = new int[numSequencias][];
        for (int indiceSequencia = 0; indiceSequencia < numSequencias; indiceSequencia++) {
            int tamanhoSequencia = sc.nextInt();
            int[] requisicoes = new int[tamanhoSequencia];
            for (int i = 0; i < tamanhoSequencia; i++) {
                requisicoes[i] = sc.nextInt();
            }
            todasAsSequencias[indiceSequencia] = requisicoes;
            sc.nextLine(); // quebra de linha após sequência
            }
            System.out.println();


            //Primeiras saidas
            int tamanhoPagina = calcularTamanhoPagina(memoriaVirtual, numPaginas);
            int numFrames = calcularFrames(memoriaFisica, tamanhoPagina);
            int tamanhoSwap = calcularSwap(memoriaVirtual, memoriaFisica);

            System.out.println(tamanhoPagina);
            System.out.println(numFrames);
            System.out.println(tamanhoSwap);
            System.out.println();
            System.out.println(numSequencias);
            System.out.println();
    }
}
