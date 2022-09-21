package monitores_semaforos;

/**A Classe Atendente realizá o papel de intermediario entre as Threand Carro e Estacionamento
 * @author Bernardo Dirceu Tomasi
 * @author Camila Florão Barcellos
 * @version 1.0
 * @since Release 01 da aplicação
 */
public class Atendente extends Thread {

    //private Estacionamento vaga;
    //private Carro carro;
    private Boolean trabalhando;
    private Boolean ocupado;
    private String nome;
    private Boolean recebendo;

    /**Método construtor que define a variável trabalhando como 'false'
     * @author Bernardo Dirceu Tomasi
     * @param trabalhando Boolean - Indica estado do atendente
     */
    public Atendente() {
        trabalhando = false;
        nome = "Bartolomeu";
        recebendo = false;
        ocupado = false;
        
        System.out.println("\n-- STATUS DO ATENDENTE --"
                        + "\nNome: " + nome
                        + "\nTrabalhando: " + trabalhando
                        + "\nRecebendo: " + recebendo
                        + "\n");
    }

    /**Método que realiza a comunicação entre a Threand Carro e Estacionamento
     * para estabelecer a relação de ocupação de uma vaga
     * @param carro Carro - Indica qual carro está fazendo o pedido
     */
    public synchronized void chamarAtendente(Estacionamento estacionamento, Carro carro) {
        try {
            //this.carro = carro;

            /*
        Se for o primeiro carro a entrar ou se por ventura o estacionamento ficou vazio e o atendente dormiu, será
        necessário imprimir no terminal que ele voltou a trabalhar
             */
            if (trabalhando == false) {
                trabalhando = true;
                recebendo = true;
                System.out.println("\n-- STATUS DO ATENDENTE --"
                        + "\nNome: " + nome
                        + "\nTrabalhando: " + trabalhando
                        + "\nRecebendo: " + recebendo
                        + "\n");
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

    /**
     * @return the trabalhando
     */
    public Boolean getTrabalhando() {
        return trabalhando;
    }

    /**
     * @param trabalhando the trabalhando to set
     */
    public void setTrabalhando(Boolean trabalhando) {
        this.trabalhando = trabalhando;
    }

    /**
     * @return the ocupado
     */
    public Boolean getOcupado() {
        return ocupado;
    }

    /**
     * @param ocupado the ocupado to set
     */
    public void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the recebendo
     */
    public Boolean getRecebendo() {
        return recebendo;
    }

    /**
     * @param recebendo the recebendo to set
     */
    public void setRecebendo(Boolean recebendo) {
        this.recebendo = recebendo;
    }

}
