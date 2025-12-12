import java.util.Scanner;

public class Main {
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
    }
}
