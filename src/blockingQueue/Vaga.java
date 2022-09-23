package blockingQueue;

/**Classe para objetos do tipo vaga, responsável por registrar a relação
 * vaga-carro
 * @author Bernardo Dirceu Tomasi
 * @author Camila Florão Barcellos
 */
public class Vaga {

    private Integer numero = -1;

    /**Método construtor para receber o número da vaga
     * @author Bernardo Dirceu Tomasi
     * @author Camila Florão Barcellos
     * @param numero Integer - Número da vaga
     */
    public Vaga(Integer numero) {
        this.numero=numero;
    }

    /**Método de retorno da vaga
     * @return numero Integer - Número da vaga
     */
    public Integer getNumero() {
        return numero;
    }

    /**Método de atribuição da vaga
     * @param numero Integer - Número da vaga
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

}
