/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kruskal_kruskal_k;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author viviane
 */
public class KRUSKAL_KRUSKAL_K {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         /*Chamada do método que ler o arquivo que contém o peso, verticeOrigem, verticeDestino
         e retorna uma lista de ObjetoArestasVertices. É passado o caminho do arquivo como parâmetro*/
         List<ObjetoArestasVertices> result = readFile("C:\\Users\\viviane\\Documents\\NetBeansProjects\\peso_kruskal2.txt");
         int tamanho = result.size();
         System.out.println("Linhas retornadas: " + tamanho);
         System.out.println();
         int i;
        
        for (i =0; i<tamanho; i++)
        {
          ObjetoArestasVertices av = (ObjetoArestasVertices) result.get(i);
          System.out.println(" Peso: " + av.getPesoAresta() + " ( " + av.getVerticeOrigem() + ", " + av.getVerticeDestino() + " )");
            
        }
        
        //Ordenar a lista
        Collections.sort(result);
        System.out.println("-----------------------Grafo ordenado ------------------------");
        System.out.println();
        for (i =0; i<tamanho; i++){
            //System.out.println(result);
          ObjetoArestasVertices av = (ObjetoArestasVertices) result.get(i);
          System.out.println(" Peso: " + av.getPesoAresta() + " ( " + av.getVerticeOrigem() + ", " + av.getVerticeDestino() + " )");
        }
        
        //chamada da função para criar a lista de árvore geradora mínima(MTS)
        List<ObjetoArestasVertices> ArvoreGeradoraMinima = treeMin(result);
        //chamada de função que calcula o custo mínimo total
        int custoMinTotal = custoMinimo(ArvoreGeradoraMinima);
        //Imprimir a árvore geradora mínima
        System.out.println("\n------------------ Árvore geradora mínima -------------------\n");
        imprimeMTS(ArvoreGeradoraMinima);
        //Imprimir custo mínimo total
        System.out.println("\nCusto mínimo: " + custoMinTotal);
        
        
    }     
    
    /*O método a seguir ler o arquivo que contém as informações do grafo e recupera, de cada linha,
    o peso, vértice de origem e vértice de destino, respectivamente, e retorna um List */
    private static List<ObjetoArestasVertices> readFile(String filePath){
        File arq = new File(filePath);

        List<ObjetoArestasVertices> dataList = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(arq);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linha = "";
            while ((linha = bufferedReader.readLine()) != null) {
                String[] split = linha.split(" ");
                dataList.add(new ObjetoArestasVertices(Integer.parseInt(getPesoAresta(split)), getVerticeOrigem(split), getVerticeDestino(split)));
            }

            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }
    //O método a seguir recupera o peso da aresta no arquivo
    private static String getPesoAresta(String[] split){
        if (split == null || split.length == 0){
            return null;
        }

        return split[0];
    }
    //O método a seguir recupera o vértice de origem no arquivo
    private static int getVerticeOrigem(String[] split){
        if (split == null || split.length < 2 || split[1] == null){
            return -1;
        }

        return Integer.valueOf(split[1].trim());
    }
    //O método a seguir recupera o vértice de destino no arquivo
    private static Integer getVerticeDestino(String[] split){
        if (split == null || split.length < 3 || split[2] == null){
            return null;
        }

        return Integer.valueOf(split[2]);
    }

    //Método que verifica se o par de vértices já está na lista de visitados
   private static int verificaListaVisitados(List <ObjetoArestasVertices> verticesVisitados, int origem, int destino)
    {
        int resultado = 0;
        int i;
        int contaElementosLista = verticesVisitados.size();
        for(i = 0; i < contaElementosLista; i++)
        {
            ObjetoArestasVertices verticesV = (ObjetoArestasVertices) verticesVisitados.get(i);
            if(verticesV.getVerticeOrigem() == destino && verticesV.getVerticeDestino() == origem)
            {
                
                      resultado = 1;
                        break;
                
            }
            if(verticesV.getVerticeOrigem() == origem && verticesV.getVerticeDestino() == destino)
            {
                
                      resultado = 1;
                        break;
                
            }
        }
        
        return resultado;
        
    }
    //Método que verifica se forma ciclo
   private static int verificaCiclo(List <ObjetoArestasVertices> verticesVisitados, int origem, int destino)
    {
        int resultado = 0;
        int i, j = 0;
        int contaElementosLista = verticesVisitados.size();
        List <ObjetoArestasVertices> verificacaoInternaListaVisitados = verticesVisitados;
        int achou = 0 ;
        
        for(i = 0; i < contaElementosLista; i++)
        {            
            ObjetoArestasVertices verticesV = (ObjetoArestasVertices) verticesVisitados.get(i);
           // System.out.println("Percorrendo: vértice " + verticesV.getVerticeOrigem() + ", " + verticesV.getVerticeDestino());
           if(verticesV.getVerticeDestino()== origem)
                {
                    //System.out.println("destino é igual a destino");
                    achou = verticesV.getVerticeOrigem();
                    //System.out.println("verificando... " + achou + " ," + destino);
                    resultado = verificaListaVisitados(verificacaoInternaListaVisitados, achou, destino);
                    if(resultado == 1)
                    {
                        resultado = 1;
                        break;//se já encontrou pode sair do loop
                    }

                }
             if(verticesV.getVerticeDestino()== destino)
                {
                    //System.out.println("destino é igual a destino");
                    achou = verticesV.getVerticeOrigem();
                    //System.out.println("verificando... " + achou + " ," + destino);
                    resultado = verificaListaVisitados(verificacaoInternaListaVisitados, achou, destino);
                    if(resultado == 1)
                    {
                        resultado = 1;
                        break;//se já encontrou pode sair do loop
                    }

                }
           
        }
        
        
        return resultado;
    }
    
    //Função que cria a árvore geradora mínima
    private static List <ObjetoArestasVertices> treeMin(List <ObjetoArestasVertices> grafoG)
    {
        int tamanhoGrafo = grafoG.size();
        List <ObjetoArestasVertices> mts = new ArrayList<>();
        int verificaListaVisitados = 0;
        int verificaCicloLista = 0;
        int i;
        
        System.out.println(" ");
        for(i = 0; i < tamanhoGrafo; i++)
        {
          ObjetoArestasVertices arestasVertices = (ObjetoArestasVertices) grafoG.get(i);
         //Sabendo o 1º é uma aresta de menor peso já posso adicioná-la na mts
          if(i == 0)
          {
              mts.add(arestasVertices);
          }else
          {
             //System.out.println(" aresta agora Peso: " + arestasVertices.getPesoAresta() + " ( " + arestasVertices.getVerticeOrigem() + ", " + arestasVertices.getVerticeDestino() + " )");
              verificaListaVisitados = verificaListaVisitados(mts, arestasVertices.getVerticeOrigem(), arestasVertices.getVerticeDestino());
              if(verificaListaVisitados != 1)
              {
                 verificaCicloLista = verificaCiclo(mts, arestasVertices.getVerticeOrigem(), arestasVertices.getVerticeDestino());
                  //System.out.println(" a que foi verificada se forma ciclo Peso: " + arestasVertices.getPesoAresta() + " ( " + arestasVertices.getVerticeOrigem() + ", " + arestasVertices.getVerticeDestino() + " )");
                  if(verificaCicloLista != 1)
                  {
                      mts.add(arestasVertices);
                  }
              }
                  
          }
          
        }     
        return mts;
    }
    
    //Método que calcula custo mínimo total a partir da mts
   private static int custoMinimo(List <ObjetoArestasVertices> mts)
    {
        int i;
        int tamanhoMts = mts.size();
        int custoM = 0;
        
        for(i = 0; i < tamanhoMts; i++)
        {
            ObjetoArestasVertices arestaVertices = (ObjetoArestasVertices) mts.get(i);
            custoM = custoM + arestaVertices.getPesoAresta();
        }
        
        
        return custoM;
    }
    //Método que imprime mts
    public static void imprimeMTS(List <ObjetoArestasVertices> mts)
    {
        int i;
        int tamanho = mts.size();
        for (i =0; i < tamanho; i++){
            //System.out.println(result);
          ObjetoArestasVertices arestaVertices = (ObjetoArestasVertices) mts.get(i);
          System.out.println(" Peso: " + arestaVertices.getPesoAresta() + " ( " + arestaVertices.getVerticeOrigem() + ", " + arestaVertices.getVerticeDestino() + " )");
        }
    }
}
