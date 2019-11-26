/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projektas_Doubly_Linked_List;

import java.util.Scanner;
//import lab3_pasirinktinis_adt.StackLinkedBlocks;
//import static lab3_pasirinktinis_adt.StackTrial.demoOperacijosSuSteku;
/**
 *
 * @author Antanas
 */
public class DoublyLinkedListTrial {//Budnikas Aurelijus
    
    public static void main(String[] args) {
        demoOperacijosSudDoublyLinkedList();
        
    }
    public static void demoOperacijosSudDoublyLinkedList(){
        DoublyLinkedList<String> temp = new DoublyLinkedList<String>();
        temp.add("Knyga1");
        temp.add("Knyga2");
        temp.add("Knyga3");
        temp.add("Knyga4");
        temp.add("Knyga5");
        temp.addFirst("Knyga6");
        temp.addLast("Knyga7");
        temp.add(0, "Knyga22");
        Thread1 obj = new Thread1(temp);
        System.out.println("Knygų apžvalga");
        System.out.println("Ankstesnė knyga - back; Sekanti knyga - next; Peržiūros pabaiga -exit");
        //Thread thread = new Thread(obj);
       // thread.start();
    }
    static class Thread1 implements Runnable{

        DoublyLinkedList<String> temp = new DoublyLinkedList<String>();
            public Thread1(DoublyLinkedList<String> temp) {
                this.temp = temp;
            }
            @Override
            public void run() {
             Scanner answer = new Scanner(System.in);
             String respond = null;
            while(respond != "exit"){
                  respond = answer.nextLine();
                if (respond.equals((Object)"next")) {
                    String elem = temp.get(true);
                    if(elem != null)
                    System.out.println(elem);
                }
                if (respond.equals((Object)"back")) {
                    String elem = temp.get(false);
                    if(elem != null)
                    System.out.println(elem);
                }
                 if (respond.equals((Object)"exit")) {
                     Thread.currentThread().stop();
                 }
            }
        }   
    }
}
