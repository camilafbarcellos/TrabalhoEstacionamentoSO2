package blockingQueue;

/**Classe da thread do tipo Carro, responsável por ocupar vagas
 * do estacionamento em estrutura bloqueante
 * @author Bernardo Dirceu Tomasi
 * @author Camila Florão Barcellos
 */
public class Carro extends Thread {

    public Vaga vaga;
    private Atendente atendente;

    /**Método construtor para receber a placa e o atendente
     * responsável pela administração do estacionamento
     * @author Bernardo Dirceu Tomasi
     * @author Camila Florão Barcellos
     * @param placa String - Placa do carro
     * @param atendente Atendente - Atendente responsável
     */
    public Carro(String placa, Atendente atendente) {
        super(placa);
        this.atendente = atendente;
    }

    /**Método de execução da thread para realizar a ocupação e a desocupação 
     * do carro em uma vaga ao interagir com o Atendente
     * @author Bernardo Dirceu Tomasi
     * @author Camila Florão Barcellos
     */
    public void run() {
        try {

            System.out.println("\n-> Carro " + this.getName() + " chegou ao estacionamento");

            atendente.chamarAtendente(this);

            if (vaga != null) {

                sleep((long) (Math.random() * 10000));

                atendente.chamarAtendenteDesocupar(this);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**Método de retorno do atendente
     * @return atendente - Atendente responsável pelo estacionamento
     */
    public Atendente getAtendente() {
        return atendente;
    }

    /**Método de atribuição do atendente
     * @param atendente Atendente - Atendente responsável pelo estacionamento
     */
    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

}
