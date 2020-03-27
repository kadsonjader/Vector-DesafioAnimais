/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiovectoranimal;

import desafiovectoranimal.model.No;
import javax.swing.JOptionPane;


/**
 *
 * @author kadsonjader
 */
public class DesafioVectorAnimal {
    
    private No no;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DesafioVectorAnimal desafio = new DesafioVectorAnimal();
        //inicia o programa
        desafio.novoJogo().primeraPergunta();
    }
    
    //Inicia a brincadeira
    public void primeraPergunta() {
    int input = JOptionPane.showOptionDialog(
            null, 
            "Pense em um Animal...", 
            "Jogo dos Animais", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            null, null, null
        );

        if(input == JOptionPane.OK_OPTION){
            this.jogar();
        }
    }
    
    //inicia a pergunta
    public void jogar() {
        this.jogar(this.no);
    }
     /* Construtores para inicialização de variaveis */ 
    public static DesafioVectorAnimal novoJogo() {
        return new DesafioVectorAnimal(buildPrimeiroNo());
    }
 
    private DesafioVectorAnimal() {}
    
    private DesafioVectorAnimal(No no) {
        this.no = no;
    } 
    
    /*
    Verifica qual opção selecionada
    */    
    private void jogar(No no) {
        if (no.proximoNo())
            this.jogar(this.pergunta(no) ? no.getNo_direita() : no.getNo_esquerda());
        else
            this.pergunta(no);
    }
    
    /*
    Seleção de opções 
    */
    private boolean pergunta(No no) {
        int awswer = JOptionPane.showConfirmDialog(null, no.getPergunta(), "Jogo dos Animais", JOptionPane.YES_NO_OPTION);

        if (awswer == JOptionPane.YES_OPTION) {
            if (!no.proximoNo())
                acerto();
            
            return true;
        } else if (!no.proximoNo())
            this.repositionNode(no);
        
        return false;
    }
    
    /*
    Quando o sistema acerta a pergunta
    */
    private void acerto() {
        JOptionPane.showMessageDialog(null, "Acertei!\nViu como sou bom nisso? ;-)");
    }
    
    /*
     quando nao ha opção de advinhação
    */
    
    private No perguntar(No no_atual) {
        String animal = "", action = "", previous_animal = no_atual.getAnimal();
                
        animal = JOptionPane.showInputDialog(null, "Qual foi o animal que você pensou? ");
        
        animal = animal != null ? animal.trim() : null;
        if (animal != null && animal.equals("")) {
            JOptionPane.showOptionDialog(
                null, 
                "Você deve informar um animal para continuar a brincadeira.", 
                "Erro!", 
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null, null, null
            );
            
            return this.perguntar(no_atual);
        } else if (animal != null) {
            action = JOptionPane.showInputDialog(null, String.format("Um(a) %s ___________ mas um(a) %s não.", animal, previous_animal));
        
            action = action != null ? action.trim() : null;
            if (action != null && action.equals("")) {
                JOptionPane.showOptionDialog(
                    null, 
                    "Você deve informar o que o animal faz para continuar a brincadeira.", 
                    "Erro!", 
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null, null, null
                );

                return this.perguntar(no_atual);
            }
        }
            
        return animal != null && action != null ? this.buildNovoNo(no_atual, animal, action) : new No("");
    }
    
    private void repositionNode(No no_atual) {
        No no = this.perguntar(no_atual);
        if (!no.getPergunta().equals("")) { 
            No pai = no_atual.getNo_pai();

            no_atual.setNo_pai(pai);

            if (pai.getNo_direita() == no_atual)
                pai.setNo_esquerda(no);
            else
                pai.setNo_direita(no);
            this.primeraPergunta();
        }
    }
    
    //mostra a opção do usuario
    private No buildNovoNo(No no_atual, String animal, String action) {
        No no = new No(String.format("O animal que você pensou %s?", action));
        No no_direita = new No(String.format("O animal que você pensou é um(a) %s?", animal), animal);
        
        no.setNo_pai(no_atual.getNo_pai())
                .setNo_direita(no_direita)
                .setNo_direita(no_atual);
        no_direita.setNo_pai(no);
        
        return no;
    }
    
    //Primeiras perguntas
    private static No buildPrimeiroNo() {
        No no_principal = new No("O animal que você pensou vive na Água?");
        No No_direita = new No("O animal que você pensou é Tubarão?", "Tubarão");
        No no_esquerda = new No("O animal que você pensou é Macaco?", "Macaco");
        
        no_principal.setNo_direita(No_direita).setNo_esquerda(no_esquerda);
        No_direita.setNo_pai(no_principal);
        no_esquerda.setNo_pai(no_principal);
        
        return no_principal;
    }
    
}
