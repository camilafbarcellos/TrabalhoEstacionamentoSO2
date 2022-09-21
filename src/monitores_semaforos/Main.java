package monitores_semaforos;

import java.util.Random;

/**
 * Classe de execução do estacionamento
 *
 * @author Bernardo Dirceu Tomasi
 * @author Camila Florão Barcellos
 * @version 1.0
 * @since Release 01 da aplicação
 */
public class Main {

    public static void main(String[] args) throws Exception {

        //Cria e inicializa a thread atendente
        Atendente atendente = new Atendente();
        atendente.start();

        //Cria o estacionamento
        Estacionamento estacionamento = new Estacionamento(atendente);

        //Garante que a thread atendente será a primeira a ser inicializada
        Thread.sleep(3000);

        System.out.println("\nSISTEMA INICIALIZADO: ATENDENTE E ESTACIONAMENTO CRIADOS\n");

        //Incializa o random
        Random gerador = new Random();

        //Variáveis criadas para a thread carro
        int num = 0;
        String placa;
        int vaga = 0;

        //Gera 23 carros
        for (int i = 0; i < 50; i++) {

            //Número da placa
            num = gerador.nextInt(900) + 100;

            //Converte o número para String
            Integer.toString(num);
            //Adiciona o número a placa
            placa = "ABC" + num;

            //Gera um número aleatório para a vaga
            vaga = gerador.nextInt(12);

            //Cria e inciializa a thread carro
            Carro carro_i = new Carro(placa, estacionamento);
            carro_i.start();

            //Garante a não sobeposição entre as threads carros
            if (i == 20) {
                Thread.sleep(15000);
            } else {
                Thread.sleep(300);

            }
        }
    }
}
