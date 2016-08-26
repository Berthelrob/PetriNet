package principal;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static Scanner scanner;

	static ArrayList<ArrayList<Integer>> matrizIn = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> matrizOut = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> matrizInc = new ArrayList<ArrayList<Integer>>();
	
	static ArrayList<ArrayList<Integer>> m0 = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> m1 = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String[] args) {
		  int n_vert = 0, n_trans = 0;
		  int novo_n_vert, novo_n_trans;
		  int opcao;
		  int status = 0;
		 		  		  
		  scanner = new Scanner(System.in); 
		  Petri rede_petri = new Petri();
		  
		    while(true){
		        System.out.println("\n\n####################################################################");		        
		        System.out.println("\t\t\tPetri Simulator \n \t\t\t\tversion: 0.1");
		        System.out.println("####################################################################\n\n");
		        System.out.println("Menu:\n"                                    +
					   "1) Inserir matriz de pré e pós-1condição\n"             +
					   "2) Inserir tokens\n"                                    +
					   "3) Inserir novo lugar ou transição\n"                   +
					   "4) Remover lugar ou transição\n"                                    +
					   "5) Verificar conexidade\n"                              +
					   "6) Verificar se a rede é pura ou impura\n"              +
					   "7) Mostrar vetor de marcação M0\n"                      +
					   "8) Mostrar a matriz de pré-condição\n"                  +
					   "9) Mostrar a matriz de pós-condição\n"                  +
					   "10) Resolver e mostrar a matriz de incidência\n"        +
					   "11) Resolver e mostrar o vetor de marcação M1\n\n"      +
			
					   "0) Sair\n");

		        opcao = scanner.nextInt();
		        if(opcao == 0){
		        	System.out.println("Saindo...\n");
		            break;
		        }
		        
		        switch(opcao){
					case 1:
						System.out.println("Insira a quantidade de lugares:\t");
						n_vert = scanner.nextInt();
						System.out.println("Insira a quantidade de transições:\t");
						n_trans = scanner.nextInt();		
						System.out.println("Insira a matriz de entrada:\n");
						matrizIn = rede_petri.cria_matriz(n_vert, n_trans);
						System.out.println("\n\nInsira a matriz de saída:\n");
						matrizOut = rede_petri.cria_matriz(n_vert, n_trans); 
						status = 1;
						break;
		            case 2:
		            	if(status==1)
		            		m0=rede_petri.insere_tokens(n_vert, n_trans);
		            	status = 2;
						break;
		            case 3:
		            	if(status==1||status==2||status==3){
		            		System.out.println("Insira quantas quantidades a mais de lugares:\t");
		            		novo_n_vert = scanner.nextInt();
		            		n_vert = n_vert + novo_n_vert;
		            		System.out.println("Insira quantas quantidades a mais de transições:\t");
		            		novo_n_trans = scanner.nextInt();		
		            		n_trans = n_trans + novo_n_trans;
		            	
		            		matrizIn.clear();
		            		matrizOut.clear();
		            		matrizInc.clear();
		            		
		            		m0.clear();
		            		m1.clear();
		            				            		
		            		System.out.println("Insira a matriz de entrada:\n");
							matrizIn = rede_petri.cria_matriz(n_vert, n_trans);
							System.out.println("\n\nInsira a matriz de saída:\n");
							matrizOut = rede_petri.cria_matriz(n_vert, n_trans);
		            	}
						status = 1;
		            		
		            		break;
					case 4:
						if(status==1||status==2||status==3){
								System.out.println("Insira a quantidade de lugares:\t");
								n_vert = scanner.nextInt();
								System.out.println("Insira a quantidade de transições:\t");
								n_trans = scanner.nextInt();	

			            		matrizIn.clear();
			            		matrizOut.clear();
			            		matrizInc.clear();
			            		
			            		m0.clear();
			            		m1.clear();
			            		
								System.out.println("Insira a matriz de entrada:\n");
								matrizIn = rede_petri.cria_matriz(n_vert, n_trans);
								System.out.println("\n\nInsira a matriz de saída:\n");
								matrizOut = rede_petri.cria_matriz(n_vert, n_trans); 
			            	}
							status = 1;
						break;
					case 5:
						if(status==3)
					    	rede_petri.imprime_conexividade(matrizInc, n_vert, n_trans);
						break;
					case 6:
						if(status==1||status==2||status==3)
							rede_petri.mostra_pura_ou_impura(matrizIn, matrizOut, n_vert, n_trans);
						break;
					case 7:
						if(status==2||status==3)
							rede_petri.mostra_vetorM(m0);
						break;
					case 8:
						if(status==1||status==2||status==3)
							rede_petri.mostra_matriz(matrizIn, n_vert, n_trans);
						break;
					case 9:
						if(status==1||status==2||status==3)
							rede_petri.mostra_matriz(matrizOut, n_vert, n_trans);
						break;
					case 10:
						if(status==1||status==2||status==3)
							matrizInc= rede_petri.resolve_matriz_inc(matrizIn, matrizOut, n_vert, n_trans);
							rede_petri.mostra_matriz(matrizInc, n_vert, n_trans);
							status=3;
						break;
					case 11:
						if(status==2||status==3)
							m1 = rede_petri.resolve_matriz_m1(matrizIn, matrizOut, m0, n_vert, n_trans);
							rede_petri.mostra_vetorM(m1);
						break;
					default:
						System.out.println("Opção inválida!\n");
						break;
		        }

		    }

	}

}

