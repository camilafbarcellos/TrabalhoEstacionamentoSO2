package monitores_semaforos;

/**Classe para objetos do tipo vaga, responsável por registrar a relação vaga-carro
 * @author Bernardo Dirceu Tomasi
 * @author Camila Florão Barcellos
 * @version 1.0
 * @since Release 01 da aplicação
 */
public class Estacionamento {

    private int quant_vagas;
    private Atendente atendente;
    
    /**Método construtor que define a quantidade total de vagas do estacionamento
     * @author Camila Florão Barcellos
     * @param atendente Atendente - Atendente que irá comandar a ocupação de vagas
     */
    public Estacionamento(Atendente atendente) {
        this.quant_vagas = 12; // vagas totais
        this.atendente = atendente;
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
    
    /**Método de retorno do atendente
     * @return atendente - Atendente responsável pelo estacionamento
     */
    public Atendente getAtendente() {
        return atendente;
    }

    /**Método de atribuição do atendente
     * @param atendente Atendente - - Atendente responsável pelo estacionamento
     */
    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }
    
    /**Método de diminuição da quantidade de vagas para indicar ocupação
     * @author Camila Florão Barcellos
     * @return quant_vagas - Quantidade atualizada de vagas
     */
    public int diminuirVagas() {
        return this.quant_vagas = quant_vagas - 1;
    }
    
    /**Método de aumento da quantidade de vagas para indicar desocupação
     * @author Camila Florão Barcellos
     * @return quant_vagas - Quantidade atualizada de vagas
     */
    public int aumentarVagas() {
        return this.quant_vagas = quant_vagas + 1;
    }
    
    /**Método sicronizado para o carro ocupar uma vaga no estacionamento
     * @author Camila Florão Barcellos
     * @param vaga Estacionamento - Estacionamento com vagas a ocupar
     * @param carro Carro - Carro que irá ocupar a vaga
     */
    public synchronized void ocupar(Estacionamento vaga, Carro carro) {
        try {
            if(vaga.getQuant_vagas() == 0) {
                System.out.println("\n-- ESTACIONAMENTO CHEIO! --\n"+
                        "-> Carro "+carro.getName()+" indo embora...");
                Thread.currentThread().interrupt();
            }
            carro.setVaga_ocupada(quant_vagas);
            System.out.println("-> Carro"+carro.getName()+" ocupou a vaga "+carro.getVaga_ocupada());
            vaga.diminuirVagas(); // vaga--
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**Método sicronizado para o carro desocupar uma vaga no estacionamento
     * @author Camila Florão Barcellos
     * @param vaga Estacionamento - Estacionamento com a vaga a desocupar
     * @param carro Carro - Carro que irá desocupar a vaga
     */
    public synchronized void desocupar(Estacionamento vaga, Carro carro) {
        try {
            System.out.println("-> Carro"+carro.getName()+" desocupou a vaga "+carro.getVaga_ocupada());
            carro.setVaga_ocupada(-1); // indica que não ocupa nenhuma vaga pois saiu
            Thread.currentThread().interrupt();
            vaga.aumentarVagas(); // vaga++
            notifyAll(); // notifica as threads
            if(vaga.getQuant_vagas() == 12) {
                System.out.println("\n-- ESTACIONAMENTO VAZIO! --\n");
                atendente.setTrabalhando(false);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
