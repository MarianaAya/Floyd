
package entidades;

import java.util.List;


public class Retorno {
    public static Retorno instancia=null;
    private List<List<Integer>> matd;
    private List<List<Integer>> mats;
    private int custo;
    private List<Integer> caminho;

    public static Retorno getInstance(){
        if(instancia==null)
            instancia=new Retorno();
        return instancia;
    }

    public List<List<Integer>> getMatd() {
        return matd;
    }

    public void setMatd(List<List<Integer>> matd) {
        this.matd = matd;
    }

    public List<List<Integer>> getMats() {
        return mats;
    }

    public void setMats(List<List<Integer>> mats) {
        this.mats = mats;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public List<Integer> getCaminho() {
        return caminho;
    }

    public void setCaminho(List<Integer> caminho) {
        this.caminho = caminho;
    }

    @Override
    public String toString() {
        return "Retorno{" + "matd=" + matd + ", mats=" + mats + ", custo=" + custo + ", caminho=" + caminho + '}';
    }
    
    
    
    
}
