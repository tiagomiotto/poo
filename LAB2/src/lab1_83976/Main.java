package lab1_83976;

import java.util.Random;

public class Main {
 public static void main(String[] args) {
	 int a= Integer.parseInt(args[0]);
	 if(a<1) {
		System.out.println("Input number must be hreater than one\n");
		 System.exit(1);
	 }
	 
	 LAB2[] lab2 = new LAB2[a];
	 Random rand = new Random();
	 int counter=0;
	 boolean b=true;
	 boolean verify=true, v_aux=false;
	 while(b) {
		 int i,j;
		 for(i=0;i<a;i++) {
			 lab2[i]=new LAB2();
		 }
		 for(i=0;i<a;i++) {
			 for(j=0;j<10;j++) {
				 int alfa=rand.nextInt(10);
				 System.out.println(alfa);
				 
			 LAB1 aux= new LAB1(alfa,0);
			 v_aux=lab2[i].associateLab1(aux);
			 if(!v_aux) verify=false;
			 }
			 counter+=i;
			 if(verify) {
				 System.out.println("Found " + lab2[i].toString() + " after " + counter + " iterations");
				 System.exit(3);
			 }
		 }

	 }
 }
}
