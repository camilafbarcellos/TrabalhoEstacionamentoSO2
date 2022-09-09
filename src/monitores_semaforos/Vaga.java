package monitores_semaforos;

/**Classe para objetos do tipo vaga, responsável por registrar a relação vaga-carro
 * @author Bernardo Dirceu Tomasi
 * @author Camila Florão Barcellos
 * @version 1.0
 * @since Release 01 da aplicação
 */
public class Vaga {

    private Integer numero;
    private Carro carro = null;

    public Vaga(Integer numero) {
        this.numero = numero;
    }

    public synchronized void ocupar(Carro carro) {

        try {

            //verifica se há um carro com a vaga
            while (this.getCarro() != null) {
                wait(); //manda esperar
            }
            
            //recebe o carro atual 
            this.setCarro(carro);
            notify(); //realiza a comunicação

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public synchronized void devolver(Vaga vag) {

        try {

            //verifica se a vaga é igual a nulo = vazia
            while (this.getCarro() == null) {
                wait(); //manda esperar se for nula
            }

            //se não cair no loop -> realiza a desocupação
            this.setCarro(null);
            notify(); //notifica

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return the carro
     */
    public Carro getCarro() {
        return carro;
    }

    /**
     * @param carro the carro to set
     */
    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    /**
     * @return the numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

}
