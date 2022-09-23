package blockingQueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**Classe principal de execução do estacionamento
 * @author Bernardo Dirceu Tomasi
 * @author Camila Florão Barcellos
 */
public class Main {

    /**Método principal de execução do sistema de estacionamento utilizando
     * estrutura bloqueante
     * @param args - Bloco de comandos
     * @throws Exception - Tratamento de exceção
     */
    public static void main(String[] args) throws Exception {

        BlockingQueue<Vaga> vagaBQ = new ArrayBlockingQueue<Vaga>(13);
        
        //Cria e inicializa a thread atendente
        Atendente atendente = new Atendente(vagaBQ);
        atendente.start();

        //Garante que a thread atendente será a primeira a ser inicializada
        Thread.sleep(3000);

        System.out.println("\nSISTEMA INICIALIZADO: ATENDENTE E ESTACIONAMENTO CRIADOS\n");

        //Incializa o random
        Random gerador = new Random();

        //Gera 50 carros
        for (int i = 0; i < 50; i++) {

            //Número da placa
            int num = gerador.nextInt(900) + 100;

            //Converte o número para String
            Integer.toString(num);
            //Adiciona o número a placa
            String placa = "ABC" + num;

            //Gera um número aleatório para a vaga
            int vaga = gerador.nextInt(12);

            //Cria e inciializa a thread carro
            Carro carro_i = new Carro(placa, atendente);
            carro_i.start();

            //Garante a não sobeposição entre as threads carros
            if (i == 20) {
                /*
                    Pausa a geração por um tempo maior para demonstrar o comportamento
                    do atendente em um contexto de estacionamento vazio com o sistema
                    ainda em execução
                */
                Thread.sleep(15000);
            } else {
                Thread.sleep(300);

            }
        }
    }
}
