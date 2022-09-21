package monitores_semaforos;

/**Classe para objetos do tipo vaga, responsável por registrar a relação vaga-carro
 * @author Bernardo Dirceu Tomasi
 * @author Camila Florão Barcellos
 * @version 1.0
 * @since Release 01 da aplicação
 */
public class Estacionamento {

    private Boolean vag[] = new Boolean[12];

    int i = 0;
    
    private int quant_vagas;
    private Atendente atendente;
    
    /**Método construtor que define a quantidade total de vagas do estacionamento
     * @author Camila Florão Barcellos
     * @param atendente Atendente - Atendente que irá comandar a ocupação de vagas
     */
    public Estacionamento(Atendente atendente) {
        this.quant_vagas = 12; // vagas totais
        this.atendente = atendente;
        
        for (i = 0; i < 12; i++) {
            vag[i] = false;
        }
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
    
    /**
     * @return the vag
     */
    public Boolean[] getVag() {
        return vag;
    }

    /**
     * @param vag the vag to set
     */
    public void setVag(Boolean[] vag) {
        this.vag = vag;
    }
    
    /**Método sicronizado para o carro ocupar uma vaga no estacionamento
     * @author Camila Florão Barcellos
     * @param vaga Estacionamento - Estacionamento com vagas a ocupar
     * @param carro Carro - Carro que irá ocupar a vaga
     */
    public synchronized void ocupar(Estacionamento vaga, Carro carro) {
        try {
            for (i = 0; i < 12; i++) {
                if (vaga.vag[i] == false) {

                    vaga.quant_vagas = vaga.quant_vagas - 1;
                    
                    carro.setVaga_ocupada(i);
                    
                    System.out.println("-> Carro " + carro.getName() 
                            + " ocupou a vaga " + carro.getVaga_ocupada()
                            + "\n-- QUANTIDADE DE VAGAS DISPONÍVEL: " + vaga.getQuant_vagas() + " --");

                    //vaga.diminuirVagas();

                    this.vag[i] = true;

                    return;
                } else if (i == 11 && vaga.vag[11] == true) {
                    System.out.println("\n-- ESTACIONAMENTO CHEIO! --\n"
                            + "-> Carro " + carro.getName() + " indo EMBORA...\n\n");

                    carro.setVaga_ocupada(-100);
                    //Thread.currentThread().interrupt();
                }
            }
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
            for (i = 0; i < 12; i++) {
                if (carro.getVaga_ocupada() == i) {

                    //vaga.aumentarVagas();
                    vaga.quant_vagas = vaga.quant_vagas + 1;

                    System.out.println("\n-> Carro " + carro.getName()
                            + " desocupou a vaga " + carro.getVaga_ocupada()
                            + "\n-- QUANTIDADE DE VAGAS DISPONÍVEL: " + vaga.getQuant_vagas() + " --");

                    carro.setVaga_ocupada(-10);
                    //Thread.currentThread().interrupt();
                    
                    System.out.println("\n");
                    
                    this.vag[i] = false;
                }
            }

            if (vaga.getQuant_vagas() == 12) {
                System.out.println("\n-- ESTACIONAMENTO VAZIO! --\n");
                atendente.setTrabalhando(false);
                atendente.setRecebendo(false);
                System.out.println("\n-- STATUS DO ATENDENTE --"
                        + "\nTrabalhando: " + atendente.getTrabalhando()
                        + "\nRecebendo: " + atendente.getRecebendo()
                        + "\n");
            }

            notifyAll(); // notifica as threads
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
