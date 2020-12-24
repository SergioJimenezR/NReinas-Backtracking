import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Problema matemático clásico de las N Reinas resuelto por Backtracking.
 * 
 * Programa que enumera la solución de colocación de las N Reinas.
 * 
 * Verano 2020. Inspiración: http://youtu.be/WOZ4wDt-iYA
 * 
 * @author Sergio Jiménez Roncero
 */

public class NReinas {

	public static void main(String[] args) {
		// El problema resulta irresoluble para N < 4.
		backtracking(new int[getN(4)], 0);
	}

	public static int getN(int min) {
		Scanner sc = new Scanner(System.in);
		int N = -1;
		System.out.println("Introduzca un valor superior a " + (min - 1) + ".");
		do {
			try {
				N = sc.nextInt();
				if (N < min)
					System.out.println("Valor incorrecto. Debe ser superior a " + (min - 1) + ".");
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Cadena de caracteres inválida. Vuelva a introducir el valor.");
			}
		} while (N < min);
		sc.close();
		return N;
	}

	static int soluciones = 0;

	public static void backtracking(int[] estado, int etapa) {
		if (etapa == estado.length)
			System.out.println("Solución " + ++soluciones + ": " + toString(estado));
		else
			for (int k = 1; k <= estado.length; k++) {
				estado[etapa] = k;
				if (!esFracaso(estado, etapa))
					backtracking(estado, etapa + 1);
			}
	}

	public static boolean esFracaso(int[] estado, int pos) {
		boolean fracaso = false;
		for (int i = 0; i < pos; i++)
			if (estado[i] == estado[pos])
				fracaso = true;
			else if (Math.abs(estado[i] - estado[pos]) == (pos - i))
				fracaso = true;
		return fracaso;
	}

	public static String toString(int[] array) {
		String res = "";
		for (int i = 0; i < array.length - 2; i++)
			res += array[i] + ", ";
		return res + array[array.length - 2] + " y " + array[array.length - 1] + ".";
	}

}
