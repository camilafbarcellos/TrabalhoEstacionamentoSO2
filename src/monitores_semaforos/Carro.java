package monitores_semaforos;

/**Classe da thread do tipo Carro responsável por ocupar o objeto vaga
 * @author 20202PF.CC0003
 * @version 1
 * @since Release 01 da aplicação
 */
public class Carro extends Thread {
    
    private Vaga vaga_ocupada; // COLOCAR CARRO NA VAGA TBM

    /**Método construtor para receber a placa e definir a vaga nula
     * @author 20202PF.CC0003
     * @param placa String - Placa do carro
     */
    public Carro(String placa) {
        super(placa);
        this.vaga_ocupada = null;
        
    }
    
    /**Método para retorno da vaga do carro
     * @return Vaga - Código da vaga ocupada
     */
    public Vaga getVaga_ocupada() {
        return vaga_ocupada;
    }

    /**Método para atribuir a vaga do carro
     * @param vaga_ocupada Vaga - Código da vaga disponível
     */
    public void setVaga_ocupada(Vaga vaga_ocupada) {
        this.vaga_ocupada = vaga_ocupada;
    }
    
    public void run() {
        
    }
    
}
