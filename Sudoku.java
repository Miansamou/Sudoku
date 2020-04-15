package Sudoku;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Sudoku {
	
	private static int size = 1;
	
	private static int[] storageX = new int[size], storageY = new int[size];	
	
	public static void main(String[]args)throws FileNotFoundException, IOException {
		game();
	}
	
	public static void game()  throws FileNotFoundException, IOException {
		
		char[][] field = initialize("C:\\Users\\mians\\eclipse-workspace\\Sudoku\\bin\\Sudoku\\grid.txt");
		
		print(field);
		
		boolean gameIsRunning = true;
		
		while(gameIsRunning) {
			Scanner write = new Scanner(System.in);
			
			System.out.println("Vai querer apagar a posição?(true ou false)");
			boolean clear = write.nextBoolean();
			
			System.out.println("Informe a posição X do tabuleiro:");
			int posX = write.nextInt();
			
			System.out.println("Informe a posição Y do tabuleiro:");
			int posY = write.nextInt();
			
			char number;
			
			if (clear == true) {
				number = '-';
			}
			else{
				System.out.println("Informe o número que quer colocar:");
				number = write.next().charAt(0);
			}
			
			int verifyInput = step(posX, posY, clear, field, number);
			
			if (verifyInput == 1) {
				field[posX][posY] = number;
			}
			else if(verifyInput == 0) {
				System.out.println("Não pode colocar esse numero ai");
			}
			
			else if(verifyInput == -1) {
				System.out.println("Posição inválida");
			}
			
			else if(verifyInput == -2) {
				System.out.println("Esse número é inviolável");
			}
			
			print(field);
			
			boolean victory = status(field);
			
			if (victory) {
				gameIsRunning = false;
				System.out.println("Congrats, you Won!   \n                                       .....'',;;::cccllllllllllllcccc:::;;,,,''...'',,'..\n"
                        + "                            ..';cldkO00KXNNNNXXXKK000OOkkkkkxxxxxddoooddddddxxxxkkkkOO0XXKx:.\n"
                        + "                      .':ok0KXXXNXK0kxolc:;;,,,,,,,,,,,;;,,,''''''',,''..              .'lOXKd'\n"
                        + "                 .,lx00Oxl:,'............''''''...................    ...,;;'.             .oKXd.\n"
                        + "              .ckKKkc'...'',:::;,'.........'',;;::::;,'..........'',;;;,'.. .';;'.           'kNKc.\n"
                        + "           .:kXXk:.    ..       ..................          .............,:c:'...;:'.         .dNNx.\n"
                        + "          :0NKd,          .....''',,,,''..               ',...........',,,'',,::,...,,.        .dNNx.\n"
                        + "         .xXd.         .:;'..         ..,'             .;,.               ...,,'';;'. ...       .oNNo\n"
                        + "         .0K.         .;.              ;'              ';                      .'...'.           .oXX:\n"
                        + "        .oNO.         .                 ,.              .     ..',::ccc:;,..     ..                lXX:\n"
                        + "       .dNX:               ......       ;.                'cxOKK0OXWWWWWWWNX0kc.                    :KXd.\n"
                        + "     .l0N0;             ;d0KKKKKXK0ko:...              .l0X0xc,...lXWWWWWWWWKO0Kx'                   ,ONKo.\n"
                        + "   .lKNKl...'......'. .dXWN0kkk0NWWWWWN0o.            :KN0;.  .,cokXWWNNNNWNKkxONK: .,:c:.      .';;;;:lk0XXx;\n"
                        + "  :KN0l';ll:'.         .,:lodxxkO00KXNWWWX000k.       oXNx;:okKX0kdl:::;'',;coxkkd, ...'. ...'''.......',:lxKO:.\n"
                        + " oNNk,;c,'',.                      ...;xNNOc,.         ,d0X0xc,.     .dOd,           ..;dOKXK00000Ox:.   ..''dKO,\n"
                        + "'KW0,:,.,:..,oxkkkdl;'.                'KK'              ..           .dXX0o:'....,:oOXNN0d;.'. ..,lOKd.   .. ;KXl.\n"
                        + ";XNd,;  ;. l00kxoooxKXKx:..ld:         ;KK'                             .:dkO000000Okxl;.   c0;      :KK;   .  ;XXc\n"
                        + "'XXdc.  :. ..    '' 'kNNNKKKk,      .,dKNO.                                   ....       .'c0NO'      :X0.  ,.  xN0.\n"
                        + ".kNOc'  ,.      .00. ..''...      .l0X0d;.             'dOkxo;...                    .;okKXK0KNXx;.   .0X:  ,.  lNX'\n"
                        + " ,KKdl  .c,    .dNK,            .;xXWKc.                .;:coOXO,,'.......       .,lx0XXOo;...oNWNXKk:.'KX;  '   dNX.\n"
                        + "  :XXkc'....  .dNWXl        .';l0NXNKl.          ,lxkkkxo' .cK0.          ..;lx0XNX0xc.     ,0Nx'.','.kXo  .,  ,KNx.\n"
                        + "   cXXd,,;:, .oXWNNKo'    .'..  .'.'dKk;        .cooollox;.xXXl     ..,cdOKXXX00NXc.      'oKWK'     ;k:  .l. ,0Nk.\n"
                        + "    cXNx.  . ,KWX0NNNXOl'.           .o0Ooldk;            .:c;.':lxOKKK0xo:,.. ;XX:   .,lOXWWXd.      . .':,.lKXd.\n"
                        + "     lXNo    cXWWWXooNWNXKko;'..       .lk0x;       ...,:ldk0KXNNOo:,..       ,OWNOxO0KXXNWNO,        ....'l0Xk,\n"
                        + "     .dNK.   oNWWNo.cXK;;oOXNNXK0kxdolllllooooddxk00KKKK0kdoc:c0No        .'ckXWWWNXkc,;kNKl.          .,kXXk,\n"
                        + "      'KXc  .dNWWX;.xNk.  .kNO::lodxkOXWN0OkxdlcxNKl,..        oN0'..,:ox0XNWWNNWXo.  ,ONO'           .o0Xk;\n"
                        + "      .ONo    oNWWN0xXWK, .oNKc       .ONx.      ;X0.          .:XNKKNNWWWWNKkl;kNk. .cKXo.           .ON0;\n"
                        + "      .xNd   cNWWWWWWWWKOkKNXxl:,'...;0Xo'.....'lXK;...',:lxk0KNWWWWNNKOd:..   lXKclON0:            .xNk.\n"
                        + "      .dXd   ;XWWWWWWWWWWWWWWWWWWNNNNNWWNNNNNNNNNWWNNNNNNWWWWWNXKNNk;..        .dNWWXd.             cXO.\n"
                        + "      .xXo   .ONWNWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWNNK0ko:'..OXo          'l0NXx,              :KK,\n"
                        + "      .OXc    :XNk0NWXKNWWWWWWWWWWWWWWWWWWWWWNNNX00NNx:'..       lXKc.     'lONN0l.              .oXK:\n"
                        + "      .KX;    .dNKoON0;lXNkcld0NXo::cd0NNO:;,,'.. .0Xc            lXXo..'l0NNKd,.              .c0Nk,\n"
                        + "      :XK.     .xNX0NKc.cXXl  ;KXl    .dN0.       .0No            .xNXOKNXOo,.               .l0Xk;.\n"
                        + "     .dXk.      .lKWN0d::OWK;  lXXc    .OX:       .ONx.     . .,cdk0XNXOd;.   .'''....;c:'..;xKXx,\n"
                        + "     .0No         .:dOKNNNWNKOxkXWXo:,,;ONk;,,,,,;c0NXOxxkO0XXNXKOdc,.  ..;::,...;lol;..:xKXOl.\n"
                        + "     ,XX:             ..';cldxkOO0KKKXXXXXXXXXXKKKKK00Okxdol:;'..   .';::,..':llc,..'lkKXkc.\n"
                        + "     :NX'    .     ''            ..................             .,;:;,',;ccc;'..'lkKX0d;.\n"
                        + "     lNK.   .;      ,lc,.         ................        ..,,;;;;;;:::,....,lkKX0d:.\n"
                        + "    .oN0.    .'.      .;ccc;,'....              ....'',;;;;;;;;;;'..   .;oOXX0d:.\n"
                        + "    .dN0.      .;;,..       ....                ..''''''''....     .:dOKKko;.\n"
                        + "     lNK'         ..,;::;;,'.........................           .;d0X0kc'.\n"
                        + "     .xXO'                                                 .;oOK0x:.\n"
                        + "      .cKKo.                                    .,:oxkkkxk0K0xc'.\n"
                        + "        .oKKkc,.                         .';cok0XNNNX0Oxoc,.\n"
                        + "          .;d0XX0kdlc:;,,,',,,;;:clodkO0KK0Okdl:,'..\n"
                        + "              .,coxO0KXXXXXXXKK0OOxdoc:,..\n"
                        + "                        ...\n SQN!");
			}
		}
	}
	
	public static char[][] initialize(String archive) throws FileNotFoundException, IOException{
		
		FileReader read = new FileReader(archive);
		BufferedReader readBuffered = new BufferedReader( read );
		String archiveLine = readBuffered.readLine();
		char[][] var = new char [10][10];
		
		for (int i = 1; i < var.length; i ++) {
			for (int h = 1, j = 0; h < var.length; h ++, j++) {
				var[i][h] = archiveLine.charAt(j * 2);
				if(archiveLine.charAt(j * 2) != '-') {
					storageX[size - 1] = i;
					storageY[size - 1] = h;
					
					size++;
					storageX = addCapacity(storageX);
					storageY = addCapacity(storageY);
				}
			}
			archiveLine = readBuffered.readLine();
		}
		
		readBuffered.close();

		return var;
	}
	
	public static void print(char var[][]) {
		System.out.println();
		for (int i = 0; i < var.length; i ++) {			
			for (int h = 0; h < var.length; h ++) {
				if(i == 0) {
					System.out.print("[00" + h + "]");
				}
				else if(h == 0) {
					System.out.print("[00" + i + "]");
				}
				else {
					System.out.print("[ " + var[i][h] + " ]");
				}
				if(h == 3 || h == 6) {
					System.out.print("  ");
				}
			}
			System.out.println();
			if(i == 3 || i == 6) {
				System.out.println();
			}
		}
		System.out.println();
	}
	
	public static int step(int x, int y, boolean clear, char[][] var, char number) {
		
		int subArrayInitX = 0, subArrayInitY = 0, subArrayEndX = 0, subArrayEndY = 0;
		
		if(x > 9 || x < 1 || y > 9 || y < 1) {
			return -1;
		}
		
		switch (x) {
		
		case 1: case 2: case 3:
			subArrayInitX = 1;
			subArrayEndX = 3;
			break;
		
		case 4: case 5: case 6:
			subArrayInitX = 4;
			subArrayEndX = 6;
			break;
			
		case 7: case 8: case 9:
			subArrayInitX = 7;
			subArrayEndX = 9;
			break;
		
		}
		
		switch (y) {
		
		case 1: case 2: case 3:
			subArrayInitY = 1;
			subArrayEndY = 3;
			break;
			
		
		case 4: case 5: case 6:
			subArrayInitY = 4;
			subArrayEndY = 6;
			break;
			
		case 7: case 8: case 9:
			subArrayInitY = 7;
			subArrayEndY = 9;
			break;
		
		}
		
		for(int i = 0; i < storageX.length; i++) {
			if(storageX[i] == x && storageY[i] == y) {
				return -2;
			}
		}
		
		if(clear == false) {
			for(int i = 1; i < var.length; i++) {
				if(var[x][i] == number) {
					return 0;
				}
			}
			
			for(int i = 1; i < var.length; i++) {
				if(var[i][y] == number) {
					return 0;
				}
			}
			
			for(int i = subArrayInitX; i <= subArrayEndX; i++) {
				for(int h = subArrayInitY; h <= subArrayEndY; h++) {
					if(var[i][h] == number) {
						return 0;
					}
				}
			}			
		}
		
		return 1;
	}
	
	public static boolean status(char[][] var) {
		int soma = 0;
		
		for(int i = 1; i < var.length; i++) {
			for(int h = 1; h < var.length; h++) {
				soma += Character.getNumericValue(var[i][h]);
			}
			if(soma != 45) {
				return false;
			}
			soma = 0;
		}
		
		for(int i = 1; i < var.length; i++) {
			for(int h = 1; h < var.length; h++) {
				soma += Character.getNumericValue(var[h][i]);
			}
			if(soma != 45) {
				return false;
			}
			soma = 0;
		}
		
		for(int subArrayInitX = 1, subArrayEndX = 3; subArrayEndX <= 9; subArrayInitX += 3, subArrayEndX += 3) {
			for(int subArrayInitY = 1, subArrayEndY = 3; subArrayEndY <= 9; subArrayInitY += 3, subArrayEndY += 3){
				for(int i = subArrayInitX; i <= subArrayEndX; i++) {
					for(int h = subArrayInitY; h <= subArrayEndY; h++) {
						soma += Character.getNumericValue(var[h][i]);
					}
				}
				if(soma != 45) {
					return false;
				}
				soma = 0;
			}
		}
		
		return true;
	}
	
	public static int[] addCapacity(int[] array) {
		int temp[] = new int[size];
		for(int i = 0; i < size - 1; i++) {
			temp[i] = array[i];
		}
		array = temp;
		return array;
	}
}
