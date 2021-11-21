
package entidades;

import java.util.ArrayList;
import java.util.List;


public class Floyd {
    private List<List<Integer>> mats;
    private List<List<Integer>> matd;
    private List<Integer> caminho;
    private int n;
    public Floyd(){
        
    }
    public void iniciar(List<List<Integer>> dados){
      
        mats=new ArrayList<>(n);
        matd=new ArrayList<>(n);
       
        for(int i=0;i<n;i++){
            mats.add(new ArrayList<>(n));
            matd.add(new ArrayList<>(n));
        }
      
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                mats.get(i).add(0);
                matd.get(i).add(0);
            }
        }
      
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                
                matd.get(i).set(j, dados.get(i).get(j));
            }
        }
     
    }
    public Retorno executar(int qtde, List<List<Integer>> dados){
        int iteracao=0;
        this.n=qtde;
        
        iniciar(dados);
     
        for(iteracao=0;iteracao<n;iteracao++){
         
            analisar(iteracao);
            
    
    
        }
        Retorno r=Retorno.getInstance();
 
        
        exibirMatrizes();
        
        r=Retorno.getInstance();
        r.setMatd(matd);
        r.setMats(mats);

        return r;
    }
    public Retorno procurarCaminho(int origem,int destino){
        System.out.println("procurarcaminho "+origem+" "+destino);
        Retorno r=Retorno.getInstance();
        caminho=new ArrayList<>(n);
        matd=r.getMatd();
    
        mats=r.getMats();
        n=r.getMatd().size();
        
        caminho.add(origem);
        
     
        obterCaminho(origem,destino);
        caminho.add(destino);
        r.setCaminho(caminho);
        r.setCusto(matd.get(origem-1).get(destino-1));
        
        return r;
    }
    public void obterCaminho(int origem, int destino){
        int valor,i;
        valor=mats.get(origem-1).get(destino-1);
        if(valor!=0){
            obterCaminho(origem,valor);
            caminho.add(valor);
            obterCaminho(valor,destino);
            
            
        }
        
    }
    
    public void analisar(int num){
        int soma;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if((i!=num && j!=num) && i!=j){
                    
                    if(!(matd.get(i).get(num)==0 || matd.get(num).get(j)==0)){
    //                    System.out.println("i="+i+" j="+j+" matd.get(i).get(num)="+matd.get(i).get(num)+" matd.get(num).get(j)="+matd.get(num).get(j));
                        soma=matd.get(i).get(num)+matd.get(num).get(j);
                        if(soma<matd.get(i).get(j) || matd.get(i).get(j)==0){
                            
                            matd.get(i).set(j, soma);
                            mats.get(i).set(j, num+1);
                        }
                    }
                }
            }
        }
    }
    
    public void exibirMatrizes(){
        System.out.println("Matriz de distância");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(matd.get(i).get(j)+" ");
            }
            System.out.println("");
        }
        
        System.out.println("\nMatriz de solução");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(mats.get(i).get(j)+" ");
            }
            System.out.println("");
        }
    }
}
