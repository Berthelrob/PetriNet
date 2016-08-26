package principal;


import java.util.ArrayList;
import java.util.Scanner;


public class Petri {

	private Scanner scanner;
	private Scanner scan;
	
	public ArrayList<ArrayList<Integer>> cria_matriz(int n_vert, int n_trans){
		scanner = new Scanner(System.in);
	    int  weight;
		Transition dado = new Transition();
		
		ArrayList<ArrayList<Integer>> matriz = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<n_vert;i++){
			ArrayList<Integer> lista = new ArrayList<Integer>();
		    //index = 'A'; 
			System.out.println("Digite o peso das arestas na linha "+(i+1)+"\t");
			for(int j=0;j<n_trans;j++){
				weight = scanner.nextInt();
				dado.setWeight(weight);
				lista.add(dado.getWeight());
			}
			matriz.add(lista);
		}
		return matriz;
	}
	

	public ArrayList<ArrayList<Integer>> insere_tokens(int n_vert, int n_trans){
		scanner = new Scanner(System.in);
	    int  weight;
		Transition dado = new Transition();
		
		ArrayList<ArrayList<Integer>> matriz = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<n_vert;i++){
			ArrayList<Integer> lista = new ArrayList<Integer>();
	        System.out.println("Digite o número de tokens para "+(i+1)+ "\t");
			for(int j=0;j<1;j++){
				weight = scanner.nextInt();
				dado.setWeight(weight);
				//index = index + 1;
				lista.add(dado.getWeight());
			}
			matriz.add(lista);
		}
		return matriz;
	}
	
	public ArrayList<ArrayList<Integer>> resolve_matriz_inc(ArrayList<ArrayList<Integer>> matrizIn, ArrayList<ArrayList<Integer>> matrizOut, int n_vert, int n_trans){
		ArrayList<ArrayList<Integer>> matrizInc = new ArrayList<ArrayList<Integer>>();
		int result;
	    
		for(int i=0;i<n_vert;i++){
			ArrayList<Integer> lista = new ArrayList<Integer>();
			for(int j=0;j<n_trans;j++){
				result=(matrizOut.get(i).get(j)) - (matrizIn.get(i).get(j));   
				lista.add(result);
			}
			matrizInc.add(lista);
		}
		return matrizInc;
	}
	
	public boolean[] verifica_se_esta_habilitada(ArrayList<ArrayList<Integer>> matrizIn, ArrayList<ArrayList<Integer>> matrizOut, ArrayList<ArrayList<Integer>> m0, int n_vert, int n_trans){
		boolean[] vetHabs = new boolean[n_trans];
		int i, j, k;
		
		k=0;
		for(i=0;i<n_trans;i++){
			vetHabs[k] = true;
			for(j=0;j<n_vert;j++){
				if (m0.get(j).get(0) >= matrizIn.get(j).get(i)){
					vetHabs[k] = vetHabs[k] && true;
				}else{
					vetHabs[k] = vetHabs[k] && false;
				}				
			}
			k=k+1;
		}
		return vetHabs;
	}
	
	public void imprime_Habilitadas(boolean[] vetHabs, int n_trans){
		System.out.println("Transições habilitadas: \n");
		for(int i=0; i<n_trans; i++){
			if(vetHabs[i])
				System.out.println("Transição "+i+" habilitada.");
			else
				System.out.println("Transição "+i+" desabilitada.");
		}
	}
	
	public ArrayList<ArrayList<Integer>> resolve_matriz_m1(ArrayList<ArrayList<Integer>> matrizIn, ArrayList<ArrayList<Integer>> matrizOut, ArrayList<ArrayList<Integer>> m0, int n_vert, int n_trans){
		ArrayList<ArrayList<Integer>> m1 = new ArrayList<ArrayList<Integer>>();
		int result;
		boolean vetHabs[] = new boolean[n_trans];
		
		scan = new Scanner(System.in);
		int tran_disp;
		
		vetHabs=verifica_se_esta_habilitada(matrizIn, matrizOut, m0, n_vert, n_trans);
		imprime_Habilitadas(vetHabs, n_trans);
		
		System.out.println("\nQual transição você quer disparar?");
		tran_disp = scan.nextInt();
		
		if(vetHabs[tran_disp]){
			for(int j=0;j<n_vert;j++){
				ArrayList<Integer> lista = new ArrayList<Integer>();
				for(int i=0;i<1;i++){
					result=(m0.get(j).get(0) - matrizIn.get(j).get(tran_disp)) + matrizOut.get(j).get(tran_disp);   
					lista.add(result);
				}
				m1.add(lista);
			}
		}else{
			System.out.println("Transição não habilitada. Sinto muito!");
		}
		return m1;	
	}
	
	public boolean verifica_conexidade(ArrayList<ArrayList<Integer>> matrizInc, int n_vert, int n_trans){
		int count=0;
		for(int i = 0;i<n_trans;i++){  
	    	for(int j = 0;j<n_vert;j++){
	    		if(matrizInc.get(j).get(i)==0)
	    			count++;
	    	} 
	    } 
		if(n_vert - count < 2){
			return false;
		}else{
			return true;
		}
	}
	
	public void imprime_conexividade(ArrayList<ArrayList<Integer>> matrizInc, int n_vert, int n_trans){
		if(verifica_conexidade(matrizInc, n_vert, n_trans))
			System.out.println("Rede conexa.");
		else
			System.out.println("Rede desconexa.");
	}
	
	public boolean verifica_pura(ArrayList<ArrayList<Integer>> matrizIn, ArrayList<ArrayList<Integer>> matrizOut, int n_vert, int n_trans){
		int result;

		for(int i=0;i<n_vert;i++){
			for(int j=0;j<n_trans;j++){
				result=(matrizOut.get(i).get(j)) * (matrizIn.get(i).get(j));    
				if(result!=0)
					return false;
			}
		}
		return true;
	}	
	
	public void mostra_pura_ou_impura(ArrayList<ArrayList<Integer>> matrizIn, ArrayList<ArrayList<Integer>> matrizOut, int n_vert, int n_trans){
		if(verifica_pura(matrizIn, matrizOut, n_vert, n_trans))
			System.out.println("\nA rede de Petri é pura.\n");
		else
			System.out.println("\nA rede de Petri é impura.\n");
	}
	
	public void mostra_matriz(ArrayList<ArrayList<Integer>> matriz, int n_vert, int n_trans){
	    int i;
		for(i=0;i<n_trans;i++){
	    	System.out.print("\tT"+i+"\t");
	    }
		System.out.println();
		for(i = 0;i<n_vert;i++){  
	        System.out.print("P"+i+"[ "); 
	    	for(int j = 0;j<n_trans;j++){
		         System.out.print("\t"+matriz.get(i).get(j)+"\t"); 
	    	} 
	         System.out.print("]\n"); 
	    }  
	}
	
	public void mostra_vetorM(ArrayList<ArrayList<Integer>> matriz){
	    for(int i = 0;i<matriz.size();i++){  
	        System.out.println("P"+i+matriz.get(i)); 
	       
	    }  
	}
}
