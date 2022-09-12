# TrabalhoEstacionamentoSO2
Trabalho avaliativo da primeira etapa da disciplina de Sistemas Operacionais II no semestre 2022/2.

Desenvolvido por Bernardo D. Tomasi e Camila F. Barcellos.

Implementação de um problema de programação concorrente com sincronização de threads por monitores/semáforos e BlockingQueue.

Algoritmo em Java para realizar o controle de um estacionamento rotativo possuindo:
- Somente um atendente e o seu salário é pago pela quantidade de tempo de 
trabalho, ou seja, o objetivo é sempre manter o estacionamento com uma 
rotatividade de automóveis, caso isso não ocorra o atendente dorme e não recebe o seu
salário;
- 12 vagas de garagem, quando um novo cliente chega o atendente deve encontrar
uma vaga de estacionamento e caso este estiver cheio o cliente deve ir embora. Caso 
um novo cliente chegue enquanto o atendente estiver em atendimento esse novo cliente
deve aguardar.
