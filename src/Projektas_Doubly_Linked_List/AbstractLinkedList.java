/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projektas_Doubly_Linked_List;

/**
 *
 * @author Antanas
 */
public interface AbstractLinkedList<E> {
    
        
    //Pridedamas elementas į sąrašo pabaigą
    public void add(E element);
    
    //Pridedamas elementas į nurodytą vietą
    public void add(int place, E element);
    
    //Pridedamas elementas prieš nurodytą vietą
    public void addBefore(int place, E element);
    
     //Pridedamas elementas po nurodytos vietos
    public void addAfter(int place, E element);
    
    //Pridemas elementą į sąrašo priekį
    public void addFirst(E element);
    
    //Pridedamas elementas į sąrašo galą
    public void addLast(E element);
    
    //Pridedamas elementas prieš nurodytą elementą
    public void addBefore(E elmentAfter, E element);
    
    //Pridedamas elementas po nurodyto elemento
    public void addAfter(E elmentBefore, E element);
    
    //Pašalinamas nurodytas elementas
    public E remove(E element);
    
    //Pašalinamas nurodytoje vietoje esantis elementas
    public E remove(int place);
    
    //Pašalinamas pirmas elementas
    public E removeFirst();
    
    //Pašalinamas paskutinis elementas
    public E removeLast();
    
    //Pašalinami nuo iki(įskaitant) elementai pagal vietas
    public void removeRange(int beginPlace, int endPlace);
    
   //Pašalinami nuo iki(įskaitant) elementai pagal nurodytus elementus
    public void removeRange(E beginElement, E endElement);
    
    //Senas elementas pakeičiamas nauju elementu
    public void set(E oldElement, E newElement);
    
    //Elemento keitimas pagal nurodytą vietą
    public void set(int place, E element);
    
    //Gaunamas elementas pagal vietą
    public E get(int place);
    
    //Gaunamas elementų masyvas
    public Object[] toArray();
    
    //Sąrašo dydis
    public int Size();
    
    //Sąrašo išvalymas
    public void clear();
    
    //Patikrinama ar elementas yra sąraše
    public boolean contains(E element);
    
    //Visų sąrašo elementų klonavimas
    public DoublyLinkedList<E> clone();
    
    //Iš kurios pusės bus einamas
    public void Begin(boolean side);
    
    //Ar elementas ne null
    public boolean isEmpty();
    
    //Sekantis elementas
    public void Next();
}
