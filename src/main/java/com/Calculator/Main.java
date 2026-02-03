package main.java.com.Calculator;

import java.util.Scanner;

import main.java.com.Calculator.Evaluator.evaluator;

public class Main {

    public static void main(String[] args){

        System.out.println("*****Welcome to Expression Calculator******\n         Please Press Enter\n if you want to exit calculator wite exit");
        Scanner sc = new Scanner(System.in);
        String input=sc.nextLine();
        while(true){
            System.out.print("Enter expression: ");
            String exp = sc.nextLine();
            if(exp.equalsIgnoreCase("exit")){
                System.out.println("ThankYou!!!");
                break;
            }

            try{
                System.out.println("Result = " + evaluator.eval(exp));
            }
            catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        
        sc.close();
    }
}
