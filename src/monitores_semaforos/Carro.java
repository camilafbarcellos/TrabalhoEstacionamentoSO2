package monitores_semaforos;

/**Classe da thread do tipo Carro, responsável por ocupar vaga 
 * @author Bernardo Dirceu Tomasi
 * @author Camila Florão Barcellos
 * @version 1.0
 * @since Release 01 da aplicação
 */
public class Carro extends Thread {
    
    private int vaga_ocupada = -1;
    private Estacionamento estacionamento;

    /**Método construtor para receber a placa, a vaga e o estacionamento do carro
     * @author Camila Florão Barcellos
     * @param placa String - Placa do carro
     * @param estacionamento Estacionamento - Estacionamento que irá ocupar
     * @param vaga int - Vaga que irá ocupar
     */
    public Carro(String placa, Estacionamento estacionamento, int vaga) {
        super(placa);
        this.estacionamento = estacionamento;
        this.vaga_ocupada = vaga;
    }
    
    /**Método de retorno do estacionamento do carro
     * @return estacionamento - Estacionamento adentrado
     */
    public Estacionamento getEstacionamento() {
        return estacionamento;
    }

    /**Método de atribuição do estacionamento do carro
     * @param estacionamento Estacionamento - Estacionamento adentrado
     */
    public void setEstacionamento(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
    }
    
    /**Método de retorno da vaga do carro
     * @return int - Código da vaga ocupada
     */
    public int getVaga_ocupada() {
        return vaga_ocupada;
    }

    /**Método de atribuição da vaga do carro
     * @param vaga_ocupada int - Código da vaga disponível
     */
    public void setVaga_ocupada(int vaga_ocupada) {
        this.vaga_ocupada = vaga_ocupada;
    }
    
    /**Método de execução da thread para ocupar e desocupar uma vaga
     * @author Camila Florão Barcellos
     */
    public void run() {
        try {
            estacionamento.ocupar(estacionamento, this);
            sleep((long) (Math.random() * 5000));
            estacionamento.desocupar(estacionamento, this);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
