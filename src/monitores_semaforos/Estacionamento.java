package monitores_semaforos;

/**Classe para objetos do tipo vaga, responsável por registrar a relação
 * entre a vaga e o carro no estacionamento
 * @author Bernardo Dirceu Tomasi
 * @author Camila Florão Barcellos
 */
public class Estacionamento {

    private Boolean vagaBool[] = new Boolean[13];

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
        
        for (i = 1; i <= 12; i++) {
            vagaBool[i] = false;
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
     * @param atendente Atendente - Atendente responsável pelo estacionamento
     */
    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }
    
    /**Método de retorno do controle de ocupação da vaga
     * @return vagaBool - Indica ocupação da vaga
     */
    public Boolean[] getVagaBool() {
        return vagaBool;
    }

    /**Método de atribuição do controle de ocupação da vaga
     * @param vagaBool Boolean - Indica ocupação da vaga
     */
    public void setVagaBool(Boolean[] vagaBool) {
        this.vagaBool = vagaBool;
    }
    
    /**Método sicronizado para o carro ocupar uma vaga no estacionamento
     * @author Bernardo Dirceu Tomasi
     * @author Camila Florão Barcellos
     * @param vaga Estacionamento - Estacionamento com vagas a ocupar
     * @param carro Carro - Carro que irá ocupar a vaga
     */
    public synchronized void ocupar(Estacionamento vaga, Carro carro) {
        try {
            for (i = 1; i <= 12; i++) {
                if (vaga.vagaBool[i] == false) {
                    vaga.quant_vagas = vaga.quant_vagas - 1;
                    
                    carro.setVaga_ocupada(i);
                    
                    System.out.println("-> Carro " + carro.getName() 
                            + " ocupou a vaga " + carro.getVaga_ocupada()
                            + "\n-- QUANTIDADE DE VAGAS DISPONÍVEL: " + vaga.getQuant_vagas() + " --");

                    this.vagaBool[i] = true;

                    return;
                } else if (i == 12 && vaga.vagaBool[12] == true) {
                    System.out.println("\n-- ESTACIONAMENTO CHEIO! --\n"
                            + "-> Carro " + carro.getName() + " indo EMBORA...\n");

                    carro.setVaga_ocupada(0);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**Método sicronizado para o carro desocupar uma vaga no estacionamento
     * @author Bernardo Dirceu Tomasi
     * @author Camila Florão Barcellos
     * @param vaga Estacionamento - Estacionamento com a vaga a desocupar
     * @param carro Carro - Carro que irá desocupar a vaga
     */
    public synchronized void desocupar(Estacionamento vaga, Carro carro) {
        try {
            for (i = 1; i <= 12; i++) {
                if (carro.getVaga_ocupada() == i) {
                    vaga.quant_vagas = vaga.quant_vagas + 1;

                    System.out.println("\n-> Carro " + carro.getName()
                            + " desocupou a vaga " + carro.getVaga_ocupada()
                            + "\n-- QUANTIDADE DE VAGAS DISPONÍVEL: " + vaga.getQuant_vagas() + " --\n");

                    carro.setVaga_ocupada(0);
                    
                    this.vagaBool[i] = false;
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
