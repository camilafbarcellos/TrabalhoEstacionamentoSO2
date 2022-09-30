package monitores_semaforos;

/**Classe intermediária entre a Thread Carro e a Classe Estacionamento
 * @author Bernardo Dirceu Tomasi
 * @author Camila Florão Barcellos
 */
public class Atendente extends Thread {

    private Boolean trabalhando;
    private Boolean ocupado;
    private String nome;
    private Boolean recebendo;

    /**Método construtor que inicializa os status de trabalho, recebimento e
     * ocupação em falso e também o nome do atendente
     * @author Bernardo Dirceu Tomasi
     * @author Camila Florão Barcellos
     */
    public Atendente() {
        trabalhando = false;
        recebendo = false;
        ocupado = false;
        nome = "Bartolomeu";
    }
    
    /**Método de execução da thread que exibe as informações
     * do atendente no momento da inicialização
     * @author Bernardo Dirceu Tomasi
     * @author Camila Florão Barcellos
     */
    public void run() {
        System.out.println(". . . . . . . . . . . . . . . ."
                        + "\nSTATUS DO ATENDENTE: "
                        + "\nNome: " + nome
                        + "\nTrabalhando: " + trabalhando
                        + "\nRecebendo: " + recebendo
                        + "\n. . . . . . . . . . . . . . . .\n");
    }

    /**Método que realiza a comunicação entre a Thread Carro e Estacionamento
     * para estabelecer a relação de ocupação de uma vaga
     * @author Bernardo Dirceu Tomasi
     * @author Camila Florão Barcellos
     * @param estacionamento Estacionamento - Indica o estacionamento ocupado
     * @param carro Carro - Indica qual carro está fazendo o pedido
     */
    public synchronized void chamarAtendente(Estacionamento estacionamento, Carro carro) {
        try {
            /*
                Se for o primeiro carro a entrar ou se por ventura o estacionamento ficou vazio e o atendente dormiu, será
                necessário imprimir no terminal que ele voltou a trabalhar
             */
            if (trabalhando == false) {
                trabalhando = true;
                recebendo = true;
                System.out.println(". . . . . . . . . . . . . . . ."
                        + "\nSTATUS DO ATENDENTE: "
                        + "\nTrabalhando: " + trabalhando
                        + "\nRecebendo: " + recebendo
                        + "\n. . . . . . . . . . . . . . . .");
            }
            
            setOcupado((Boolean) true);
            notifyAll();
            
            estacionamento.ocupar(estacionamento, carro);
            
            setOcupado((Boolean) false);
            notifyAll();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**Método que realiza a comunicação entre a Thread Carro e Estacionamento
     * para estabelecer a relação de desocupação de uma vaga
     * @author Bernardo Dirceu Tomasi
     * @author Camila Florão Barcellos
     * @param estacionamento Estacionamento - Indica o estacionamento ocupado
     * @param carro Carro - Indica qual carro está fazendo o pedido
     */
    public synchronized void chamarAtendenteDesocupar(Estacionamento estacionamento, Carro carro) {
        try {

            setOcupado((Boolean) true);
            notifyAll();

            estacionamento.desocupar(estacionamento, carro);

            setOcupado((Boolean) false);
            notifyAll();

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

}
