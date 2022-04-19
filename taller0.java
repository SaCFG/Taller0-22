package taller0;

import java.io.*;
import java.util.Scanner;


public class taller0 {
	
	
/**
 * leerJugadores reads the information of the txt that is given and write it down on matrizN and matrizS
 * @param archivo File to read
 * @param matriN  Name information matrix
 * @param matrizS  Stats information matrix
 * @return The amount of players in the game 
 * @throws IOException
 */
	
	public static int leerJugadores(File archivo, String matrizN[][], int matrizS[][], int cantidadJug) throws IOException{
		Scanner lector = new Scanner(archivo);
		
		//used to get what line is being read
		int numLine = 0;
		while(lector.hasNextLine()) {
			String linea = lector.nextLine();
			String partes[] = linea.split(",");
			
			//User name information
			matrizN[numLine][0] = partes[0];
			matrizN[numLine][1] = partes[1];			
			
			//User stats information 
			matrizS[numLine][0] = Integer.parseInt(partes[2]);
			matrizS[numLine][1] = Integer.parseInt(partes[3]);
			matrizS[numLine][2] = Integer.parseInt(partes[4]);
			matrizS[numLine][3] = Integer.parseInt(partes[5]);
			matrizS[numLine][4] = Integer.parseInt(partes[6]);
			matrizS[numLine][5] = Integer.parseInt(partes[7]);	
			numLine++;
		}		
		lector.close();
		cantidadJug = numLine;
		return cantidadJug;
	}

/** leerHechizos reads the information of the txt that is given and write it down on the vector H and P
 * 	
 * @param archivo File to read
 * @param H Vector of the name of the spells
 * @param P Vector of the power of the spells
 * @throws IOException
 */
	
	public static void leerHechizos(File archivo,String H[], int P[]) throws IOException{
		Scanner lector = new Scanner(archivo);
		
		//used to get what line is being read
		int numLine = 0;
		while(lector.hasNextLine()) {
			String linea = lector.nextLine();
			String partes[] = linea.split(",");
			
			//name of the spell
			H[numLine] = partes[0];
			
			//power of the spell
			P[numLine] = Integer.parseInt(partes[1]);
			
			numLine++;		
		}
		lector.close();
	}

/** leer reads the information of the txt that is given and write it down on the vector nom and clase and the matrix matrisSE
 * 	
 * @param archivo File to read
 * @param nom  Vector of the name of the enemies
 * @param clase  Vector of the class of the enemies
 * @param matrizSE  Matrix of the stats of the enemies
 * @throws IOException
 */
	
	public static void leerEnemigos(File archivo, String nom[], String clase[], int matrizSE[][]) throws IOException{
		Scanner lector = new Scanner(archivo);
		
		//used to get what line is being read
		int numLine = 0;
		while(lector.hasNextLine()) {
			String linea = lector.nextLine();
			String partes[] = linea.split(",");
			
			//name and class of the enemy (npc)
			nom[numLine] = partes[0];
			clase[numLine] = partes[3];
			
			//stats of the enemy (npc)
			matrizSE[numLine][0] = Integer.parseInt(partes[1]);
			matrizSE[numLine][1] = Integer.parseInt(partes[2]);
			matrizSE[numLine][3] = Integer.parseInt(partes[4]);
			
			
			numLine++;			
		}
		lector.close();
	}
	
/** asignarHechizos writes a 1 in a matrix that in the Y and X axes are in line with name of players and spell in the matrix
 * 	
 * @param archivo File to read
 * @param matrizEJ matrix to be written on
 * @param vectorEN for search purpose
 * @param cant for search purpose
 * @param matrizJN for search purpose
 * @throws FileNotFoundException
 */
	
	public static void asignarHechizos(File archivo, int matrizEJ[][], String vectorEN[],int cant, String matrizJN[][]) throws FileNotFoundException {
		Scanner lector = new Scanner(archivo);
		
		//used to get what line is being read
		while(lector.hasNextLine()) {
			String linea = lector.nextLine();
			String partes[] = linea.split(",");
			String pj = partes[0];
			String hechizo = partes[1];
			
			int x = (busquedaHe(vectorEN, hechizo));
			int y = (busquedaPj(matrizJN, pj, cant));
			
			if((!(x==-1))&&(!(y==-1))) {
				matrizEJ[y][x]=1;
			}		
			lector.close();
		}
	}
	
/** busquedaHe realize a search in the vector to find an spell and return the index if not found -1 will be returned
 * 	
 * @param vector used to be searched on
 * @param hechizo spell to be searched
 * @return
 */
	
	public static int busquedaHe(String vector[], String hechizo) {
		for (int i = 0; i < vector.length; i++) {
			if(vector[i].equals(hechizo)) {
				return i;
			}
		}
		return -1;
	}

/** busquedaHe realize a search in the matrix to find a name and return the index if not found -1 will be returned
 * 	
 * @param matrix used to be searched on
 * @param nom name to be searched
 * @param cant max amount of player
 * @return
 */
	
	public static int busquedaPj(String matrix[][], String nom, int cant) {
		for (int i = 0; i < cant; i++) {
			if(matrix[i][0].equals(nom)) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	
	//standard scanner used through the program 
	static Scanner leer = new Scanner(System.in);
	
/** main part of the program that starts everything, beginning with the read of the archives and login in, 
 * it will thorw an exception if the file names are not changed to the correct location btw
 * 
 * @param args
 * @throws IOException
 */
	
	public static void main(String[] args) throws IOException {
		
		//read archives
		File archivoJugador = new File("Jugadores.txt");
		int cantidadJug = 0;
		String matrizJugNames[][] = new String[999][2];
		int matrizJugStats[][] = new int[999][6];
		
		
		File archivoEchizos = new File("Hechizos.txt");
		String hechizosN[] = new String[999];
		int hechizosP[] = new int[999];
		
		
		File archivoEnemigos = new File("Enemigo.txt");
		String nombresEnemigos[] = new String[999];
		String claseEnemigos[] = new String[999];
		int matrizStatsEnemy[][] = new int[999][3];
		leerJugadores(archivoJugador, matrizJugNames, matrizJugStats,cantidadJug);
		leerHechizos(archivoEchizos, hechizosN, hechizosP);
		leerEnemigos(archivoEnemigos, nombresEnemigos, claseEnemigos, matrizStatsEnemy);
		//end read archives
		
		//this matrix needs to be the size of hechizosN length and the amount of players
		File archivoEchizosJugadores = new File("HechizosJugadores.txt");
		int matrizEchizosJugador[][] = new int [cantidadJug][hechizosN.length];		
		asignarHechizos(archivoEchizosJugadores, matrizEchizosJugador, hechizosN, cantidadJug, matrizJugNames);
		// end matrix spell with owners
		
		System.out.println("Bienvenido a MAO");
		
		//login in
		int login=0;
		while(login==0){						
			System.out.println("Ingrese su username: ");
			String name = leer.next();
	 		int resultado = (busquedaPj(matrizJugNames, name, cantidadJug));
	 		
	 		//Admin case
			if(name == "Admin") {
				int correctA = 1;
				while(correctA ==1) {
					System.out.println("Ingrese su contraseña: ");
					String pass = leer.next();
					if("Patata19"==pass) {
						System.out.println("Contraseña correcta");
						menuAdmin(matrizJugNames, matrizJugStats, cantidadJug, hechizosN, hechizosP, matrizEchizosJugador, nombresEnemigos, claseEnemigos, matrizStatsEnemy);
					}
					else if(pass=="1") {
						login++;
						break;
					}
					System.out.println("Contraseña incorrecta intente denuevo, escriba 1 para salir");
				}
			}
			
			//player found 
			else if(!(resultado==-1)) {
				int correct =1;
				while(correct ==1) {
					System.out.println("Ingrese su contraseña: ");
					String pass = leer.next();
					if(matrizJugNames[resultado][1].equals(pass)) {
						System.out.println("Contraseña correcta");
						menuPlayer(name,matrizJugNames,matrizJugStats,cantidadJug,hechizosN,hechizosP,matrizEchizosJugador,nombresEnemigos,claseEnemigos,matrizStatsEnemy);
					}
					else if(pass=="1") {
						login++;
						break;
					}
					System.out.println("Contraseña incorrecta intente denuevo, escriba 1 para salir");
				}
			
			}
			
			//player not found
			else if(resultado==-1) {
				System.out.println("Jugador no encontrado");
				System.out.println("Escriba 1 para reintentar");
				System.out.println("Escriba 2 para registrarse");
				System.out.println("Escriba 3 para salir");
				System.out.println("Ingrese opcion: ");
				int eleccion = leer.nextInt();
				switch (eleccion) {
				case 1: {					
				}
				case 2: {
					registrar(matrizJugNames, matrizJugStats, cantidadJug);		
				}
				case 3: {
					login++;
					break;
				}
				default:
					throw new IllegalArgumentException("Opcion no valida");
				}
			}
		}
		
		cerrarSistema(archivoJugador,archivoEchizos,archivoEchizosJugadores,archivoEnemigos,matrizJugNames,matrizJugStats,cantidadJug,hechizosN,hechizosP,matrizEchizosJugador,nombresEnemigos,claseEnemigos,matrizStatsEnemy);
	}

/** registrar registers a new user in to the system by asking the name and password the rest of information about the player is decide by the system it self
 * 
 * @param matrizN name information matrix
 * @param matrizS stats information matrix
 * @param cant used to get is the next line of the matrix, gets a +1 because of the new player
 * @return
 */

	private static int registrar(String matrizN[][], int matrizS[][],int cant) {
		//values of default stats had not been given so every new player starts with 5 on each, spell amount on 0 and 0 exp.
		System.out.println("Indique su username: ");
		String newName = leer.next();
		System.out.println("Indique su password: ");
		String newPass = leer.next();
		
		matrizN[cant][0] = newName;
		matrizN[cant][1] = newPass;
		
		for (int i = 0; i < 4; i++) {
			matrizS[cant][i] = 5;
		}
		
		cant++;		
		return cant;
	}
	
/** menuAdmin is the menu that the admin can use to manage the game, remove a player or add content
 * 	
 * @param matrisN matrix of player info
 * @param matrizS matrix of player stats
 * @param cant amount of player, gets and update in the end of the process
 * @param hN names of the spells
 * @param hP power of the spells
 * @param matrizDueño matrix owners of the spells
 * @param nombreE vector name of enemies
 * @param claseE vector class of enemies
 * @param matrisE matrix stats of  enemies
 */
	
	private static int menuAdmin(String matrisN[][], int matrizS[][], int cant,String hN[], int hP[],int matrizDueño[][], String nombreE[], String claseE[], int matrisE[][]) {
		int seguir =0;
		while(seguir ==0) {
		System.out.println("Bienvenido ");
		System.out.println("Estas son tus opciones en el menu de admin (cabe destacar que despues de "
				+ "\ncada nueva adicion de enemigo o hechizo se aconseja reiniciar al programa, para evitar errores)"
				+ "\n1 Eliminar un jugador"
				+ "\n2 Agregar enemigos"
				+ "\n3 Agregar hechizos"
				+ "\n4 Ver las estadísticas de los jugadores"
				+ "\n5 Salir");
		int opcion = leer.nextInt();
		switch (opcion) {
		case 1: {
			System.out.println("Ingrese el nombre del jugador a eleminiar: ");
			String objetivoEliminar = leer.next();
			int indexDelete = (busquedaPj(matrisN, objetivoEliminar, cant));
			if(!(indexDelete == -1)) {
				//deleted from matrix info player
				for (int i = indexDelete; i < cant-1; i++) {
					for (int j = 0; j < 2; j++) {
						matrisN[i][j] = matrisN[i+1][j];
					}					
					for (int j = 0; j < 6; j++) {
						matrizS[i][j] = matrizS[i+1][j];
					}							
				}
				//deleted from matrix owner of spell
				for (int i = indexDelete; i < cant-1; i++) {
					for (int j = 0; j < hN.length; j++) {
						matrizDueño[i][j] = matrizDueño[i+1][j];
					}
				}
				cant--;
			}
			else if(indexDelete == -1) {
				System.out.println("player not found");
			}
		}
		case 2: {
			System.out.println("Ingrese el nombre del enemigo que desea añadir: ");
			String nom = leer.next();
			System.out.println("Ingrese el hp del enemigo: ");
			int hp = leer.nextInt();
			System.out.println("Ingrese el ataque del enemigo: ");
			int ataque = leer.nextInt();
			System.out.println("Ingrese la clase del enemigo: ");
			String clase = leer.next();
			System.out.println("Ingrese la velocidad del enemigo: ");
			int velocidad = leer.nextInt();
			int actualEnemy = nombreE.length;
			nombreE[actualEnemy] = nom;
			claseE[actualEnemy] = clase;
			
			matrisE[actualEnemy][0] = hp;
			matrisE[actualEnemy][1] = ataque;
			matrisE[actualEnemy][2] = velocidad;
			
			System.out.println("");
			System.out.println("Enemigo añadido");
		}
		
		
		case 3: {
			System.out.println("Ingrese el nombre del enemigo que desea añadir: ");
			String nom = leer.next();
			System.out.println("Ingrese el hp del enemigo: ");
			int hp = leer.nextInt();
			System.out.println("Ingrese el ataque del enemigo: ");
			int ataque = leer.nextInt();
			System.out.println("Ingrese la clase del enemigo: ");
			String clase = leer.next();
			System.out.println("Ingrese la velocidad del enemigo: ");
			int velocidad = leer.nextInt();
			int actualEnemy = nombreE.length;
			nombreE[actualEnemy] = nom;
			claseE[actualEnemy] = clase;
			
			matrisE[actualEnemy][0] = hp;
			matrisE[actualEnemy][1] = ataque;
			matrisE[actualEnemy][2] = velocidad;
			
			System.out.println("");
			System.out.println("Enemigo añadido, se aconseja reinicio (solo si no se modificara mas los datos del programa)");
		}
		
		
		case 4: {
			System.out.println("Ingrese el nombre del hechizo que desea añadir: ");
			String nom = leer.next();
			System.out.println("Ingrese el poder del hechizo: ");
			int pow = leer.nextInt();
			int actualSpell = hN.length;
			hN[actualSpell]=nom;
			hP[actualSpell]=pow;
			System.out.println("");
			System.out.println("Hechizo añadido, se aconseja reinicio (solo si no se modificara mas los datos del programa)");
			
		}
		
		
		case 5: {
			System.out.println("Volviendo al inicio");
			seguir++;
		}
		
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + opcion);
		}
		}
		return cant;
	}

/** menuPlayer the user can make use of his options as a player
 * 	
 * @param name of the player
 * @param matrisN matrix of player info
 * @param matrizS matrix of player stats
 * @param cant amount of player
 * @param hN vector with spells names
 * @param hP vector with spells power
 * @param matrizDueño matrix with the spell and its owner
 * @param nombreE vector with name of enemies
 * @param claseE vector with the class of the enemies
 * @param matrisE matrix with stats of the enemies
 */
	
	private static void menuPlayer(String name, String matrisN[][], int matrizS[][], int cant,String hN[], int hP[],int matrizDueño[][], String nombreE[], String claseE[], int matrisE[][]) {
		int seguir =0;
			while(seguir ==0) {
			System.out.println("Bienvenido "+name);
			System.out.println("Estas son tus opciones en el menu de jugador"
					+ "\n1 Pelear contra un enemigo, pvp o pve"
					+ "\n2 Aprender un hechizo, costara 1000xp y es al azar"
					+ "\n3 Ver estadísticas de un jugador"
					+ "\n4 Ver estadísticas de hechizos"
					+ "\n5 Ver ranking jugadores más experiencia"
					+ "\n6 Salir");
			int opcion = leer.nextInt();
			int indexPlayer = (busquedaPj(matrisN, name, cant));
			switch (opcion) {
			
			
			case 1: {
				System.out.println("¿Desea pelear en PvP o PvE?"
						+ "\n1 Player vs Player"
						+ "\n2 Player vs Environment");
				int electionFight = leer.nextInt();
				switch (electionFight) {
				case 1: {
					batallaPVP(name, indexPlayer, matrisN, matrizS, cant, matrizDueño, hN, hP);
					
				}
				case 2: {
					batallaPVE(name, indexPlayer, matrizS, matrizDueño, hN, hP, nombreE, claseE, matrisE );
					
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + electionFight);
				}
			}
			
			
			case 2: {
				
				int caso2 = 0;				
				while(caso2 == 0){
				 	int azar = (int) (Math.random()*hN.length);
				 	int tieneDueño = 0;
				 	for (int i = 0; i < cant; i++) {
						if (matrizDueño[i][azar] ==1) {
							tieneDueño++;						}
					}
				 	if (tieneDueño==0 && matrizS[indexPlayer][5] >= 1000) {
						matrizDueño[indexPlayer][azar] = 1;
						System.out.println("Se gano el hechizo: "+hN[azar]);
						matrizS[indexPlayer][5] = matrizS[indexPlayer][5] - 1000;
						caso2++;
					}
				 	else if(matrizS[indexPlayer][5] < 1000) {
				 		System.out.println("No se tiene la exp necesaria para aprender un nuevo hechizo o no quedan hechizos");
				 	}
				}
			}
			
			
			case 3: {
				System.out.println("Ingrese el nombre del jugador: ");
				String objetivo = leer.next();
				int busquedaR = (busquedaPj(matrisN, objetivo, cant));
				if(!(busquedaR == -1)) {
					System.out.println("Nombre: "+matrisN[busquedaR][0]
							+ "\nPuntos de Vida: "+matrizS[busquedaR][0]
							+ "\nAtaque: "+matrizS[busquedaR][1]
							+ "\nDefensa: "+matrizS[busquedaR][2]
							+ "\nVelocidad: "+matrizS[busquedaR][3]
							+ "\nNumero de Hechizos: "+matrizS[busquedaR][4]);
				}
				else if(busquedaR == -1) {
					System.out.println("Jugador no encontrado");
				}
			}
			
			
			case 4: {
				System.out.println("Ingrese el nombre del hechizo: ");	
				String objetivoE = leer.next();
				int busquedaE = (busquedaHe(hN, objetivoE));
				if(!(busquedaE == -1)) {
					System.out.println("Nombre: "+hN[busquedaE]
							+ "\nPoder: "+hP[busquedaE]);
				//because its says to only display the stats of spells i just display the name and power to display the owner it would use the matrix owners and the busquedaE to find the owner
				}
				else if(busquedaE == -1) {
					System.out.println("Hechizo no encontrado");
				}
			}
			
			
			case 5: {
				ranking(matrisN,matrizS, cant);				
			}
			
			
			case 6: {
				System.out.println("Volviendo al inicio");
				seguir++;
			}
			
			
			default:
				throw new IllegalArgumentException("Opcion no valida");
			}
			
			
		}
	}
	
/** batallaPVE is a battle against an IA controlled enemy which i don't know how it works
 * 	
 * @param name
 * @param indexP
 * @param mS
 * @param spell
 * @param spellN
 * @param spellP
 * @param nombreE
 * @param claseE
 * @param statsE
 */
	
	private static void batallaPVE(String name, int indexP, int mS[][], int spell[][], String spellN[], int spellP[], String nombreE[], String claseE[], int statsE[][]) {
		int indexEnemy = (int) (Math.random()*nombreE.length);
		int p1[] = new int [4];
		int E[] = new int [3];
		for (int i = 0; i < 4; i++) {
			p1[i] = mS[indexP][i];
		}
		for (int i = 0; i < 3; i++) {
			E[i] = statsE[indexEnemy][i];
		}
		int ganador = 0;
		while(ganador == 0) {
			if(p1[3] > E[2]) {
				System.out.println("Jugador que accion tomaras: "
						+ "\n1 Atacar"
						+ "\n2 Usar hechizo");
				int opcion1 = leer.nextInt();
				if(opcion1 == 1) {
					E[0] = E[0] - p1[1];
				}
				else if(opcion1 == 2) {
					System.out.println("Elija un echizo");
					for(int i = 0; i < spellN.length; i++) {
						if(spell[indexP][i]==1) {
							System.out.println(spell[indexP][i]);
						}					
					}
					String hechizo = leer.next();
					int dmg = spellP[busquedaHe(spellN, hechizo)];
					
					E[0] = E[0] - dmg;
				}
				
				if(E[0] <= 0) {
					ganador = 1;
					break;
				}
				
				p1[0] = p1[0] - E[1];
				
				if(p1[0] <= 0) {
					ganador = 2;
					break;
				}
			}
			
			if(p1[3] < E[2]) {
				
				p1[0] = p1[0] - E[1];
				
				if(p1[0] <= 0) {
					ganador = 2;
					break;
				}
				
				System.out.println("Jugador que accion tomaras: "
						+ "\n1 Atacar"
						+ "\n2 Usar hechizo");
				int opcion1 = leer.nextInt();
				if(opcion1 == 1) {
					E[0] = E[0] - p1[1];
				}
				else if(opcion1 == 2) {
					System.out.println("Elija un echizo");
					for(int i = 0; i < spellN.length; i++) {
						if(spell[indexP][i]==1) {
							System.out.println(spell[indexP][i]);
						}					
					}
					String hechizo = leer.next();
					int dmg = spellP[busquedaHe(spellN, hechizo)];
					
					E[0] = E[0] - dmg;
				}
				
				if(E[0] <= 0) {
					ganador = 1;
					break;
				}				
			}
		}
		
		switch (ganador) {
		//gives the exp and lets the user uses the stats + point to lvl up
		case 1: {
			System.out.println("ganaste");
			switch (claseE[indexEnemy]) {
			case "Clase S": {
				System.out.println("+ 1000 experiencia y +20 stats");
				mS[indexP][5] = mS[indexP][5] +1000;
				int point =20;
				System.out.println("cuantos puntos a Hp");
				int electHP = leer.nextInt();
				if(point > 0 && !(electHP>point)) {
					mS[indexP][0]=+ electHP;
					point =- electHP;
				}
							
				System.out.println("cuantos puntos a ataque");
				int electDmg = leer.nextInt();				
				if(point > 0 && !(electDmg>point)) {
					mS[indexP][1]=+ electDmg;
					point =- electDmg;
				}
				
				System.out.println("cuantos puntos a def");
				int electDef = leer.nextInt();			
				if(point > 0 && !(electDef>point)) {
					mS[indexP][2]=+ electDef;
					point =- electDef;
				}
				
				System.out.println("cuantos puntos a velocidad");
				int electVel = leer.nextInt();
				if(point > 0 && !(electVel>point)) {
					mS[indexP][3]=+ electVel;
					point =- electVel;
				}
				
				
			}
			case "Clase A": {
				System.out.println("+ 750 experiencia y +10 stats");
				mS[indexP][5] = mS[indexP][5] +750;
				int point =10;
				System.out.println("cuantos puntos a Hp");
				int electHP = leer.nextInt();
				if(point > 0 && !(electHP>point)) {
					mS[indexP][0]=+ electHP;
					point =- electHP;
				}
							
				System.out.println("cuantos puntos a ataque");
				int electDmg = leer.nextInt();				
				if(point > 0 && !(electDmg>point)) {
					mS[indexP][1]=+ electDmg;
					point =- electDmg;
				}
				
				System.out.println("cuantos puntos a def");
				int electDef = leer.nextInt();			
				if(point > 0 && !(electDef>point)) {
					mS[indexP][2]=+ electDef;
					point =- electDef;
				}
				
				System.out.println("cuantos puntos a velocidad");
				int electVel = leer.nextInt();
				if(point > 0 && !(electVel>point)) {
					mS[indexP][3]=+ electVel;
					point =- electVel;
				}
			}
			case "Clase B": {
				System.out.println("+ 500 experiencia y +5 stats");
				mS[indexP][5] = mS[indexP][5] +500;
				int point =5;
				System.out.println("cuantos puntos a Hp");
				int electHP = leer.nextInt();
				if(point > 0 && !(electHP>point)) {
					mS[indexP][0]=+ electHP;
					point =- electHP;
				}
							
				System.out.println("cuantos puntos a ataque");
				int electDmg = leer.nextInt();				
				if(point > 0 && !(electDmg>point)) {
					mS[indexP][1]=+ electDmg;
					point =- electDmg;
				}
				
				System.out.println("cuantos puntos a def");
				int electDef = leer.nextInt();			
				if(point > 0 && !(electDef>point)) {
					mS[indexP][2]=+ electDef;
					point =- electDef;
				}
				
				System.out.println("cuantos puntos a velocidad");
				int electVel = leer.nextInt();
				if(point > 0 && !(electVel>point)) {
					mS[indexP][3]=+ electVel;
					point =- electVel;
				}
			}
			case "Clase C": {
				System.out.println("+ 250 experiencia y +2 stats");
				mS[indexP][5] = mS[indexP][5] +250;
				int point =2;
				System.out.println("cuantos puntos a Hp");
				int electHP = leer.nextInt();
				if(point > 0 && !(electHP>point)) {
					mS[indexP][0]=+ electHP;
					point =- electHP;
				}
							
				System.out.println("cuantos puntos a ataque");
				int electDmg = leer.nextInt();				
				if(point > 0 && !(electDmg>point)) {
					mS[indexP][1]=+ electDmg;
					point =- electDmg;
				}
				
				System.out.println("cuantos puntos a def");
				int electDef = leer.nextInt();			
				if(point > 0 && !(electDef>point)) {
					mS[indexP][2]=+ electDef;
					point =- electDef;
				}
				
				System.out.println("cuantos puntos a velocidad");
				int electVel = leer.nextInt();
				if(point > 0 && !(electVel>point)) {
					mS[indexP][3]=+ electVel;
					point =- electVel;
				}
			}
			case "Clase F": {
				System.out.println("+ 100 experiencia y +1 stats");
				mS[indexP][5] = mS[indexP][5] +100;
				System.out.println("Ingrese en que stat iran los puntos"
						+ "\n1 hp "
						+ "\n2 ataque "
						+ "\n3 def "
						+ "\n4 sped");
				int elect = leer.nextInt();
				switch (elect){
				case 1: {
					mS[indexP][0]++;				
				}
				case 2: {
					mS[indexP][1]++;				
				}
				case 3: {
					mS[indexP][2]++;				
				}
				case 4: {
					mS[indexP][3]++;				
				}
				default:
					throw new IllegalArgumentException("Unexpected value");
				}
			}
			default:
				throw new IllegalArgumentException("Unexpected value: ");
			}
		}
		case 2: {
			System.out.println("Perdiste");
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + ganador);
		}
	}

/** batallaPVP is a battle against another player which i don't know how it works, if its IA controlled the player or another person on the terminal/console doing inputs, and i forgot to ask this and there is no more time left
 * so i will do the things that it say but with place holders the same will apply for the PVE
 * 
 * @param name
 * @param indexP
 * @param mN
 * @param mS
 * @param spell
 * @param spellN
 * @param spellP
 */

	private static void batallaPVP(String name, int indexP, String mN[][],  int mS[][], int cant, int spell[][], String spellN[], int spellP[]) {
		int	seleccionar = 0;
		int indexPlayer2 = -1 ;
		while(seleccionar == 0) {
			indexPlayer2 = (int) (Math.random()*cant);//random selected enemy for the pvp
			if (!(indexPlayer2 == indexP)) {
				seleccionar++;
			}
		}
		
		int p1[] = new int [4];
		int p2[] = new int [4];
		for (int i = 0; i < 4; i++) {
			p1[i] = mS[indexP][i];
			p2[i] = mS[indexPlayer2][i];
		}
		int ganador =0;
		while(ganador == 0) {
			if(p1[3] > p2[3]) {
				System.out.println("Jugador numero 1 que accion tomaras: "
						+ "\n1 Atacar"
						+ "\n2 Usar hechizo");
				int opcion1 = leer.nextInt();
				if(opcion1 == 1) {
					p2[0] = p2[0] - (p1[1] - p2[2]);
				}
				else if(opcion1 == 2) {
					System.out.println("Elija un echizo");
					for(int i = 0; i < spellN.length; i++) {
						if(spell[indexP][i]==1) {
							System.out.println(spell[indexP][i]);
						}					
					}
					String hechizo = leer.next();
					int dmg = spellP[busquedaHe(spellN, hechizo)];
					
					p2[0] = p2[0] - dmg;
				}
			
				if(p2[0] <= 0) {
					ganador = 1;
					break;
				}
				
				System.out.println("Jugador numero 2 que accion tomaras: "
						+ "\n1 Atacar"
						+ "\n2 Usar hechizo");
				int opcion2 = leer.nextInt();
				if(opcion2 == 1) {
					p1[0] = p1[0] - (p2[1] - p1[2]);
				}
				else if(opcion2 == 2) {
					System.out.println("Elija un echizo");
					for(int i = 0; i < spellN.length; i++) {
						if(spell[indexPlayer2][i]==1) {
							System.out.println(spell[indexPlayer2][i]);
						}					
					}
					String hechizo = leer.next();
					int dmg = spellP[busquedaHe(spellN, hechizo)];
					
					p1[0] = p1[0] - dmg;
				}
				if(p1[0] <= 0) {
					ganador = 2;
					break;
				}
				
			}
			else if(p1[3] < p2[3]) {
				System.out.println("Jugador numero 2 que accion tomaras: "
						+ "\n1 Atacar"
						+ "\n2 Usar hechizo");
				int opcion2 = leer.nextInt();
				if(opcion2 == 1) {
					p1[0] = p1[0] - (p2[1] - p1[2]);
				}
				else if(opcion2 == 2) {
					System.out.println("Elija un echizo");
					for(int i = 0; i < spellN.length; i++) {
						if(spell[indexPlayer2][i]==1) {
							System.out.println(spell[indexPlayer2][i]);
						}					
					}
					String hechizo = leer.next();
					int dmg = spellP[busquedaHe(spellN, hechizo)];
					
					p1[0] = p1[0] - dmg;
				}
				if(p1[0] <= 0) {
					ganador = 2;
					break;
				}
				
				System.out.println("Jugador numero 1 que accion tomaras: "
						+ "\n1 Atacar"
						+ "\n2 Usar hechizo");
				int opcion1 = leer.nextInt();
				if(opcion1 == 1) {
					p2[0] = p2[0] - (p1[1] - p2[2]);
				}
				else if(opcion1 == 2) {
					System.out.println("Elija un echizo");
					for(int i = 0; i < spellN.length; i++) {
						if(spell[indexP][i]==1) {
							System.out.println(spell[indexP][i]);
						}					
					}
					String hechizo = leer.next();
					int dmg = spellP[busquedaHe(spellN, hechizo)];
					
					p2[0] = p2[0] - dmg;
				}
			
				if(p2[0] <= 0) {
					ganador = 1;
					break;
				}
			}
			
		}
		switch (ganador) {
		case 1: {
			System.out.println("Gano el jugador 1, reciviras +250exp");
			mS[indexP][5] = mS[indexP][5]+250;
		}
		case 2: {
			System.out.println("Gano el jugador 2, reciviras +250exp");
			mS[indexPlayer2][5] = mS[indexPlayer2][5]+250;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + ganador);
		}	
	}

/** ranking used to print the top 10 players on amount of experience points
 * 
 * @param matrisN matrix to pass on the name of [i][0] to a auxiliary vector
 * @param matrisS matrix to pass on the exp of [i][0] to a auxiliary vector
 * @param cant amount of player used in cycle for 
 */

	private static void ranking(String matrisN[][], int matrisS[][], int cant) {
		//auxiliary vector to rank the players  
		String auxMN[] = new String[cant];
		int auxMS[] = new int[cant];
		for (int i = 0; i < cant; i++) {
			auxMN[i] = matrisN[i][0];
			auxMS[i] = matrisS[i][5];
		}
		
		for (int i = 0; i <= cant-2; i++) {
			for (int j = i+1; j < cant-1; j++) {
				
				if(auxMS[i] > auxMS[j]) {
					int auxI = auxMS[i];
					auxMS[i] = auxMS[j];
					auxMS[j] = auxI;
					
					String auxS = auxMN[i];
					auxMN[i] = auxMN[j];
					auxMN[j] = auxS;
				}				
			}
		}
		System.out.println("Top 10 jugadores");
		for (int i = 0; i < 10; i++) {
			System.out.println("Top "+(i+1)+"Nombre: "+auxMN[i]+" Cantidad de exp: "+auxMS[i]);
		}
	}
	
/** cerrarSistema uses the file and the matrix provided to overwrite the information in the place that it should be
 * 	
 * @param Jugadores 
 * @param Spells
 * @param Owners
 * @param Enemies
 * @param matrisN
 * @param matrizS
 * @param cant
 * @param hN
 * @param hP
 * @param matrizDueño
 * @param nombreE
 * @param claseE
 * @param matrisE
 * @throws IOException
 */

	private static void cerrarSistema(File Jugadores, File Spells, File Owners, File Enemies, String matrisN[][], int matrizS[][], int cant,String hN[], int hP[],int matrizDueño[][], String nombreE[], String claseE[], int matrisE[][]) throws IOException {
		//player overwrite 
		FileWriter escritorJ = new FileWriter(Jugadores);
		for (int i = 0; i < cant; i++) {
			escritorJ.write(matrisN[i][0]+", "+matrisN[i][1]+", "+matrizS[i][0]+", "+matrizS[i][1]+", "+matrizS[i][2]+", "+matrizS[i][3]+", "+matrizS[i][4]+", "+matrizS[i][5]+"\n");
		}
		
		//Spell overwrite
		FileWriter escritorS = new FileWriter(Spells);
		for(int i = 0; i < hN.length; i++) {
			escritorS.write(hN[i]+","+hP[i]+"\n");
		}
		
		//Owners overwrite
		FileWriter escritorO = new FileWriter(Owners);
		for(int i = 0; i < hN.length; i++) {
			for (int j = 0; j < cant; j++) {
				if(matrizDueño[j][i]==1) {
					escritorO.write(matrisN[j][0]+","+hN[i]+"\n");
				}
			}
		}
		
		//Enemies overwrite
		FileWriter escritorE = new FileWriter(Enemies);
		for(int i = 0; i < nombreE.length; i++) {
			escritorE.write(nombreE[i]+", "+matrisE[i][0]+", "+matrisE[i][1]+", "+claseE[i]+", "+matrisE[i][2]+"\n");
		}
		
		//close every writer
		escritorE.close();
		escritorJ.close();
		escritorO.close();
		escritorS.close();
		
		//system close
		System.exit(0);
	}		
}