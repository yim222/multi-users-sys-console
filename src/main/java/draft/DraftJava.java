package main.java.draft;

import java.util.Scanner;

//import java.io.Console;
public class DraftJava {
	
	
	public static void main(String[] args) {
		System.out.println("Hello");
		Scanner sc = new Scanner(System.in);
		outerScanner();
		String x = sc.nextLine();
		System.out.println(x);
		sc.close();
	}
	
	public static void  outerScanner(){
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Trying to run another scanner");
		String str3 = sc2.nextLine();
		System.out.println(str3);
		
	}
}
