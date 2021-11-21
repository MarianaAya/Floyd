
function inicio(){
    var div=document.getElementById("div-dados");
    var ret="";
    var qtde=document.getElementById("qtde-vertice").value;
    var nome="";
    for(let i=0;i<qtde;i++){
        ret+="<div class='div-input-linha'>";
        for(let j=0;j<qtde;j++){
            nome="input"+i+j;
            ret+="<input type='number' id='"+nome+"' name='"+nome+"'/>";
        }
        ret+="</div>";
    }
    div.innerHTML=ret;
}
function floyd(){
   
        const URL_TO_FETCH='ReceberDados?acao=floyd';
        var formData = new FormData();
        var qtdeVertice=document.getElementById("qtde-vertice").value;
        var valores=[];
        var valor,nome;
        for(let i=0;i<qtdeVertice;i++){
            for(let j=0;j<qtdeVertice;j++){
                nome="input"+j+i;
         
                valor=document.getElementById(nome).value;
                valores.push(valor);
            }
        }
        
        formData.append('valores',valores);
        formData.append('qtde',qtdeVertice);
        fetch(URL_TO_FETCH,{method:'post', body: formData}).then(function(response)
        {
            response.text().then(function(result)  //response é um promisse
            {
                obj=JSON.parse(result);
                console.log(obj);
               
                var resposta=document.getElementById("div-resposta");
                var respd="",resps="";
                respd+="<h2>Matriz de Distância</h2>"
                respd+="<table class='table-coloracao' id='table-distancia'>";
                respd+="<tbody>";
                for(let i=0;i<qtdeVertice;i++){
                    respd+="<tr>";
                    for(let j=0;j<qtdeVertice;j++){
                        respd+="<td>"+obj.matd[i][j]+"</td>";
                    }
                    respd+="</tr>";
                }
                respd+="</tbody>";
                respd+="</table>";
                
                resps+="<h2>Matriz de Solução</h2>"
                resps+="<table class='table-coloracao' id='table-solucao'>";
                resps+="<tbody>";
                for(let i=0;i<qtdeVertice;i++){
                    resps+="<tr>";
                    for(let j=0;j<qtdeVertice;j++){
                        resps+="<td>"+obj.mats[i][j]+"</td>";
                    }
                    resps+="</tr>";
                }
                resps+="</tbody>";
                resps+="</table>";
                
              
                resposta.innerHTML=respd+resps;
               
                
            });
        }).catch (function(err) {console.error(err);});
    
}

function obterCaminho(){
    const URL_TO_FETCH='ReceberDados?acao=caminho';
        var formData = new FormData();
        var origem=document.getElementById("origem").value;
        var destino=document.getElementById("destino").value;
        
        formData.append('origem',origem);
        formData.append('destino',destino);
        fetch(URL_TO_FETCH,{method:'post', body: formData}).then(function(response)
        {
            response.text().then(function(result)  //response é um promisse
            {
                obj=JSON.parse(result);
                var div=document.getElementById("div-caminhodados");
                
                var resp="";
                resp+="<p><strong>Custo: </strong>"+obj.custo+"</p>";
                resp+="<p><strong>Caminho: </strong>"
                for(let i=0;i<obj.caminho.length;i++){
                    resp+=obj.caminho[i];
                    if(i!=obj.caminho.length-1){
                        resp+="->";
                    }
                }
                resp+="</p>";
                
                div.innerHTML=resp;
                
            });
        }).catch (function(err) {console.error(err);});
}

