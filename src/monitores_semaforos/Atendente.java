package monitores_semaforos;

/**A Classe Atendente realizá o papel de intermediario entre as Threand Carro e Vaga
 * @author Bernardo Dirceu Tomasi
 * @author Camila Florão Barcellos
 * @version 1.0
 * @since Release 01 da aplicação
 */
public class Atendente extends Thread {

    private Vaga vaga;
    private Carro carro;
    private Boolean trabalhando;

    /**Método construtor que define a variável trabalhando como 'false'
     * @author Bernardo Dirceu Tomasi
     * @param placa String - Placa do carro
     */
    public Atendente() {
        trabalhando = false;
    }

    /**Método que realiza a comunicação entre a Threand Carro e Estacionamento
     * para estabelecer a relação de ocupação de uma vaga
     * @param carro
     */
    public synchronized void comunicaOcupar(Carro carro) throws InterruptedException {
        try {
            this.carro = carro;

            /*
        Se for o primeiro carro a entrar ou se por ventura o estacionamento ficou vazio e o atendente dormiu, será
        necessário imprimir no terminal que ele voltou a trabalhar
             */
            if (trabalhando == false) {
                trabalhando = true;
                System.out.println("\nAtendente TRABALHANDO\n");
            }
            vaga.ocupar(carro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**Método que realiza a comunicação entre a Threand Carro e Estacionamento
     * para estabelecer a relação de desocupação de uma vaga
     * @param carro
     */
    public synchronized void comunicaDesocupar(Carro carro){
        try {
            this.carro = carro;

            vaga.devolver(carro);
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
     * @return the trabalhando
     */
    public Boolean getTrabalho() {
        return trabalhando;
    }

    /**
     * @param trabalhando the trabalhando to set
     */
    public void setTrabalho(Boolean trabalhando) {
        this.trabalhando = trabalhando;
    }

    /**
     * @return the vaga
     */
    public Vaga getVaga() {
        return vaga;
    }

    /**
     * @param vaga the vaga to set
     */
    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
}
