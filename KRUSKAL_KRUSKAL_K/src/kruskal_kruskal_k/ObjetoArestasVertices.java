/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kruskal_kruskal_k;

import java.util.Collections;

/**
 *
 * @author viviane
 */
public class ObjetoArestasVertices implements Comparable<ObjetoArestasVertices>{
    
    private int pesoAresta;
    private int verticeOrigem;
    private int verticeDestino;
    public ObjetoArestasVertices(int pesoAresta, int verticeOrigem, int verticeDestino)
    {
       this.pesoAresta = pesoAresta;
        this.verticeOrigem = verticeOrigem;
        this.verticeDestino = verticeDestino;
    };

    public ObjetoArestasVertices() {
       //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * @return the pesoAresta
     */
    public int getPesoAresta() {
        return pesoAresta;
    }

    /**
     * @param pesoAresta the pesoAresta to set
     */
    public void setPesoAresta(int pesoAresta) {
        this.pesoAresta = pesoAresta;
    }

    /**
     * @return the verticeOrigem
     */
    public int getVerticeOrigem() {
        return verticeOrigem;
    }

    /**
     * @param verticeOrigem the verticeOrigem to set
     */
    public void setVerticeOrigem(int verticeOrigem) {
        this.verticeOrigem = verticeOrigem;
    }

    /**
     * @return the verticeDestino
     */
    public int getVerticeDestino() {
        return verticeDestino;
    }

    /**
     * @param verticeDestino the verticeDestino to set
     */
    public void setVerticeDestino(int verticeDestino) {
        this.verticeDestino = verticeDestino;
    }

    @Override
    public int compareTo(ObjetoArestasVertices t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       if(this.getPesoAresta() > t.getPesoAresta())
       {
           return 1;
       }else
       {
           return -1;
       }
        
    }
    
   
}
