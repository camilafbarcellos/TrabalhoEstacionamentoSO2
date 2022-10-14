package monitores_semaforos;

import java.util.Random;

/**Classe principal de execução do estacionamento
 * @author Bernardo Dirceu Tomasi
 * @author Camila Florão Barcellos
 */
public class Main {

    /**Método principal de execução do sistema de estacionamento utilizando
     * monitores
     * @param args - Bloco de comandos
     * @throws Exception - Tratamento de exceção
     */
    public static void main(String[] args) throws Exception {
        
        System.out.println(". . . . . . . . . . . . . . . .\n" +
                           ". Bem-vindo ao estacionamento .\n" +
                           ".        Estacione Bem        .\n" +
                           ". . . . . . . . . . . . . . . .\n");

        //Cria e inicializa a thread atendente
        Atendente atendente = new Atendente();
        atendente.start();

        //Cria o estacionamento
        Estacionamento estacionamento = new Estacionamento(atendente);

        //Garante que a thread atendente será a primeira a ser inicializada
        Thread.sleep(3000);

        System.out.println("SISTEMA INICIALIZADO: ATENDENTE E ESTACIONAMENTO CRIADOS"
                + "\nIMPLEMENTAÇÃO: MONITORES");

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

            //Cria e inciializa a thread carro
            Carro carro_i = new Carro(placa, estacionamento);
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
