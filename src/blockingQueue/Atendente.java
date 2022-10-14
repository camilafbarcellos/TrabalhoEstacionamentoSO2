package blockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * Classe responsável por administrar o BlockingQueue de Vagas
 *
 * @author Bernardo Dirceu Tomasi
 * @author Camila Florão Barcellos
 */
public class Atendente extends Thread {

    private Boolean trabalhando;
    private Boolean ocupado;
    private String nome;
    private Boolean recebendo;
    private Boolean vagaBool[] = new Boolean[13];
    private BlockingQueue<Vaga> vagaBQ = null;
    private int quant_vagas;
    int j = 0;

    /**Método construtor que inicializa os status de trabalho, recebimento e
     * ocupação em falso, o nome do atendente e a estrutura BlockingQueue
     * @author Bernardo Dirceu Tomasi
     * @author Camila Florão Barcellos
     * @param vagaBQ BlockingQueue - Indica a estrutura bloqueante de vagas
     */
    public Atendente(BlockingQueue<Vaga> vagaBQ) {
        try {
            trabalhando = false;
            nome = "Bartolomeu";
            recebendo = false;
            ocupado = false;

            quant_vagas = 12;

            this.vagaBQ = vagaBQ;

            for (int i = 1; i <= 12; i++) {
                vagaBool[i] = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**Método de fabricação das vagas para utilização no BlockingQueue
     * @author Bernardo Dirceu Tomasi
     * @author Camila Florão Barcellos
     * @return Vaga(j) - Nova vaga com índice j
     */
    public Vaga fabricarVaga() {
        j++;
        return new Vaga(j);
    }

    /**Método de execução da thread para ocupar o BlockingQueue com
     * as 12 vagas do estacionamento
     * @author Bernardo Dirceu Tomasi
     * @author Camila Florão Barcellos
     */
    public void run() {
        for (int i = 1; i <= 12; i++) {
            try {
                vagaBQ.put(this.fabricarVaga());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        System.out.println(". . . . . . . . . . . . . . . ."
                        + "\nSTATUS DO ATENDENTE: "
                        + "\nNome: " + nome
                        + "\nTrabalhando: " + trabalhando
                        + "\nRecebendo: " + recebendo
                        + "\n. . . . . . . . . . . . . . . .\n");
    }

    /**Método que realiza a comunicação com a Thread Carro
     * para estabelecer a relação de ocupação de uma vaga
     * @author Bernardo Dirceu Tomasi
     * @author Camila Florão Barcellos
     * @param carro Carro - Indica qual carro está fazendo o pedido
     */
    public synchronized void chamarAtendente(Carro carro) {
        try {
            if (getTrabalhando() == false) {
                setTrabalhando((Boolean) true);
                setRecebendo((Boolean) true);
                System.out.println(". . . . . . . . . . . . . . . ."
                        + "\nSTATUS DO ATENDENTE: ACORDADO"
                        + "\nTrabalhando: " + trabalhando
                        + "\nRecebendo: " + recebendo
                        + "\n. . . . . . . . . . . . . . . .");
            }

            ocupado = true;

            for (int i = 1; i <= 12; i++) {
                if (getVagaBool()[i] == false) {
                    quant_vagas = quant_vagas - 1;

                    carro.vaga = this.vagaBQ.take();

                    System.out.println("-> Carro " + carro.getName()
                            + " ocupou a vaga " + carro.vaga.getNumero()
                            + "\n-- QUANTIDADE DE VAGAS DISPONÍVEL: " + getQuant_vagas() + " --");

                    this.getVagaBool()[i] = true;

                    return;
                } else if (i == 12) {
                    System.out.println("-- ESTACIONAMENTO CHEIO! --\n"
                            + "-> Carro " + carro.getName() + " indo EMBORA...");

                    carro.vaga = null;
                }
            }

            ocupado = false;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**Método que realiza a comunicação com a Thread Carro
     * para estabelecer a relação de desocupação de uma vaga
     * @author Bernardo Dirceu Tomasi
     * @author Camila Florão Barcellos
     * @param carro Carro - Indica qual carro está fazendo o pedido
     */
    public synchronized void chamarAtendenteDesocupar(Carro carro) {
        try {

            ocupado = true;

            for (int i = 1; i <= 12; i++) {
                if (carro.vaga.getNumero() == i) {
                    quant_vagas = quant_vagas + 1;

                    System.out.println("\n-> Carro " + carro.getName()
                            + " desocupou a vaga " + carro.vaga.getNumero()
                            + "\n-- QUANTIDADE DE VAGAS DISPONÍVEL: " + getQuant_vagas() + " --");

                    this.vagaBQ.put(carro.vaga);

                    this.getVagaBool()[i] = false;
                }
            }

            if (quant_vagas == 12) {
                System.out.println("\n-- ESTACIONAMENTO VAZIO! --\n");
                setTrabalhando(false);
                setRecebendo(false);
                System.out.println(". . . . . . . . . . . . . . . ."
                        + "\nSTATUS DO ATENDENTE: DORMINDO"
                        + "\nTrabalhando: " + trabalhando
                        + "\nRecebendo: " + recebendo
                        + "\n. . . . . . . . . . . . . . . .");
            }

            ocupado = false;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**Método de retorno do status de trabalho
     * @return trabalhando - Indica se está trabalhando ou não
     */
    public Boolean getTrabalhando() {
        return trabalhando;
    }

    /**Método de atribuição do status de trabalho
     * @param trabalhando Boolean - Indica se está trabalhando ou não
     */
    public void setTrabalhando(Boolean trabalhando) {
        this.trabalhando = trabalhando;
    }

    /**Método de retorno do status de ocupação
     * @return ocupado - Indica se está ocupado ou não
     */
    public Boolean getOcupado() {
        return ocupado;
    }

    /**Método de atribuição do status de ocupação
     * @param ocupado Boolean - Indica se está ocupado ou não
     */
    public void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
    }

    /**Método de retorno do nome
     * @return nome - Nome do atendente
     */
    public String getNome() {
        return nome;
    }

    /**Método de atribuição do nome
     * @param nome String - Nome do atendente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**Método de retorno do status de recebimento de salário
     * @return recebendo - Indica se está recebendo ou não
     */
    public Boolean getRecebendo() {
        return recebendo;
    }

    /**Método de atribuição do status de recebimento de salário
     * @param recebendo Boolean - Indica se está recebendo ou não
     */
    public void setRecebendo(Boolean recebendo) {
        this.recebendo = recebendo;
    }

    /**Método de retorno do BlockingQueue
     * @return vagaBQ - Estrutura bloqueante de vagas
     */
    public BlockingQueue<Vaga> getVagaBQ() {
        return vagaBQ;
    }

    /**Método de atribuição do BlockingQueue
     * @param vagaBQ BlockingQueue - Estrutura bloqueante de vagas
     */
    public void setVagaBQ(BlockingQueue<Vaga> vagaBQ) {
        this.vagaBQ = vagaBQ;
    }

    /**Método de retorno da quantidade de vagas
     * @return quant_vagas - Quantidade de vagas disponíveis
     */
    public int getQuant_vagas() {
        return quant_vagas;
    }

    /**Método de atribuição da quantidade de vagas
     * @param quant_vagas int - Quantidade de vagas disponíveis
     */
    public void setQuant_vagas(int quant_vagas) {
        this.quant_vagas = quant_vagas;
    }

    /**Método de retorno do controle de ocupação da vaga
     * @return vagaBool - Indica ocupação da vaga
     */
    public Boolean[] getVagaBool() {
        return vagaBool;
    }

    /**Método de atribuição do controle de ocupação da vaga
     * @param vagaBool Boolean - Indica ocupação da vaga
     */
    public void setVagaBool(Boolean[] vagaBool) {
        this.vagaBool = vagaBool;
    }
}
