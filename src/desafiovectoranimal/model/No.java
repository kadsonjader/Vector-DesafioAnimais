/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiovectoranimal.model;






/**
 *
 * @author kadsonjader
 */
public class No {
    private No no_pai, no_direita, no_esquerda;
    
    private String pergunta, animal;
    
    //Metodo Construtor
    public No(String pergunta) {
        this.pergunta = pergunta;
    }
    
    public No(String pergunta, String animal) {
        this.pergunta = pergunta;
        this.animal = animal;
    }
    
    public String toString() {
        return this.pergunta;
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        else if (object instanceof No) {
            No no = (No) object; 
            
            
            return (this.pergunta == null ? pergunta == null : this.pergunta.equals(pergunta));
        }
        
        return false;
    }
    
    public boolean proximoNo() {
        return this.no_direita != null || this.no_esquerda != null;
    }
    
    //Getters and Setters
    
    public String getPergunta() {
        return this.pergunta;
    }
    
    public String getAnimal() {
        return this.animal;
    }
    
    public No getNo_pai() {
        return no_pai;
    }

    public No getNo_direita() {
        return no_direita;
    }
    
    public No getNo_esquerda() {
        return no_esquerda;
    }
    
    public No setQuestion(String pergunta) {
        this.pergunta = pergunta;
        
        return this;
    }
    
    public No setAnimal(String animal) {
        this.animal = animal;
        
        return this;
    }
    
    public No setNo_pai(No parent_node) {
        this.no_pai = parent_node;
        
        return this;
    }

    public No setNo_direita(No no_direita) {
        this.no_direita = no_direita;
        
        return this;
    }
    
    public No setNo_esquerda(No no_esquerda) {
        this.no_esquerda = no_esquerda;
        
        return this;
    }
    
    
    
}
