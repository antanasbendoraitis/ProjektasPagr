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
public class DoublyLinkedList<E> implements AbstractLinkedList<E>{
    private Node<E> begin;
    private Node<E> end;
    private Node<E> l;
    private Node<E> store;
    private Node<E> pre;
    private Node<E> pab;
    private Node<E> temp;
    private int Size;
    private boolean side; 
    
    public DoublyLinkedList(){
       begin = new Node<E>(null, null, null);
       end = new Node<E>(null, begin, null);//Su fiktyviais elmentais
       pre = begin;
       begin.right = end;
       pab = end;
       l = null;
       store = null;
    }
    
     public E nextRigth()
    {
        if (l == null) {
            l = begin.right.right;
            return l.data;
        }
        else
        l = l.right;
        return l.data;
    }
    
    /**
     * Nustatomas kryptis pagal kurią bus imami elementas
     * @param side Kryptis
     */
    @Override
    public void Begin(boolean side){
        this.side = side;
        if (side)
            l = begin.right;
        else
            l = end.left;
    }
    
    /**
     * Ar elementas nėra null
     * @return Ar elementas ne null
     */
    @Override
    public boolean isEmpty(){
        if(side)
            return l.right != null;
        else
            return l.left != null;
    }
    
    /**
     * Sekantis elementas
     */
    @Override
    public void Next(){
        if (side)
            l = l.right;
        else
            l = l.left;
    }
       
    /**
     * Dabartinis elementas
     * @return Elementas
     */
    public E GetData(){
        return l.data;
    }
    
    /**
     * Guy sąsajai sekančio elemento paėmimas pagal kryptį
     * @param side0 Kryptis
     * @return Elementas
     */
    public E get(boolean side0){
        if (temp == null) {
            temp = begin.right;
        }
        if (side0 && temp.right.right != null) {
            temp = temp.right;
        }
        else if(!side0 && temp.left.left != null){
            temp = temp.left;
        }
        return temp.data;
    }
    
    /**
     * Gui sąsajai nurodoma kryptis elementų paėmimui
     * @param side0 Kryptis
     * @return Elementas
     */
    public E toBeginOrEnd(boolean side0)
    {
        if (side0) {
            temp = begin.right;
        }
        else
            temp = end.left;
        return temp.data;
    }
    
    /**
     * Gui sąsajai kairės pusės tikrinimas
     * @return Patikrinimas ar ne null kairė pusė
     */
    public boolean checkLeft()
    {
        if(temp.left.left == null){
            return true;
        }
        return false;
    }

    /**
     * Elemento pridėjimas į sąrašo galą
     * @param element Elementas
     */
    @Override
    public void add(E element) {
      pre.right = new Node<E>(element, pre, end);
      pre = pre.right;
      end.left = pre;
      Size++;
    }

    /**
     * Pridedamas elementas į nurodytą vietą
     * @param place   Vieta
     * @param element Elementas
     */
    @Override
    public void add(int place, E element) {
        if (place >= 0 && place < Size) {
            int k = 0;
            for (Node<E> d1 = begin.right; d1 != null; d1 = d1.right) {
                if (place == k) {
                    Node<E> temp = new Node<E>(element, null, null);
                    temp.right = d1.right;
                    temp.left = d1;
                    d1.right.left = temp;
                    d1.right = temp;
                }
                k++;
            }
        }
        Size++;
    }

    /**
     * Pridemas elementą į sąrašo priekį
     * @param element Elementas
     */
    @Override
    public void addFirst(E element) {

                Node<E> temp = new Node<E>(element, null, null);
                temp.right = begin.right;
                temp.left = begin;
                begin.right.left = temp;
                begin.right = temp;
                Size++;
    }

    /**
     * Pridedamas elementas į sąrašo galą
     * @param element Elementas
     */
    @Override
    public void addLast(E element) {
            Node<E> tem = end.left;
            Node<E> temp = new Node<E>(element, null, null);
            temp.right = tem.right;
            temp.left = tem;
            tem.right.left = temp;
            tem.right = temp;
            Size++;
    } 
    
    /**
     * Pridedamas elementas prieš nurodytą elementą
     * @param elementAfter  elementas prieš kurį pridedama
     * @param element       pridedamas elementas
     */
    @Override
    public void addBefore(E elementAfter, E element) {
            if (contains(elementAfter) == true)
            for (Node<E> d1 = begin.right; d1 != null; d1 = d1.right) {
                if (d1.right.data != null && d1.right.data.equals(elementAfter)) {
                    Node<E> temp = new Node<E>(element, null, null);
                    temp.right = d1.right;
                    temp.left = d1;
                    d1.right.left = temp;
                    d1.right = temp;
                    break;
                }
            }
            Size++;
    }

    /**
     * Pridedamas elementas po nurodyto elemento
     * @param elementBefore Elementas po kurio pridedama
     * @param element       Pridedamas elementas
     */
    @Override
    public void addAfter(E elementBefore, E element) {
           if (contains(elementBefore) == true)
            for (Node<E> d1 = begin.right; d1 != null; d1 = d1.right) {
                if (d1.data.equals(elementBefore)) {
                    Node<E> temp = new Node<E>(element, null, null);
                    temp.right = d1.right;
                    temp.left = d1;
                    d1.right.left = temp;
                    d1.right = temp;
                    break;
                }
            }
           Size++;
    }

    /**
     * Pridedamas elementas prieš nurodytą vietą
     * @param place   Vieta
     * @param element Elementas
     */
    @Override
    public void addBefore(int place, E element) {
        add(place, element);
    }

    /**
     * Pridedamas elementas po nurodytos vietos
     * @param place   Vieta
     * @param element Elementas
     */
    @Override
    public void addAfter(int place, E element) {
        add(place+1, element);
    }

    /**
     * Pašalinamas nurodytas elementas
     * @param element elementas
     * @return        Grąžinamas pašalintas elementas
     */
    @Override
    public E remove(E element) {
        Node<E> current = begin;
        for (Node<E> d1 = current.right; d1 != null; d1 = d1.right) {
            if (element.equals(d1.data)) {
                if (d1.right != null) {
                    d1.left.right = d1.right;
                    d1.right.left = d1.left;
                    Size--;
                }
                else{ //Pabaigos sutvarkymas
                    d1 = current;
                    d1.right = null;
                    end.left = end.left.left;
                    Size--;
                }
                break;
            }
            if (!d1.equals(element)) {
                current = d1;
            }
        }
        return element;
    }

    /**
     * Pašalinamas pirmas elementas
     * @return Grąžinamas pašalintas elementas
     */
    @Override
    public E removeFirst() {
        l = begin.right;
        store = new Node<E>(l.data, null, null);
        l.right.left = l.left;
        l.left.right = l.right;
        Size--;
        return store.data;
    }

    /**
     * Pašalinamas paskutinis elementas
     * @return Grąžinamas pašalintas elementas
     */
    @Override
    public E removeLast() {
        l = end.left;
        store = new Node<E>(l.data, null, null);
        l.right.left = l.left;
        l.left.right = l.right;
        Size--;
        return store.data;
    }

    /**
     * Senas elementas pakeičiamas nauju elementu
     * @param oldElement Senas elementas
     * @param newElement Naujas elementas
     */
    @Override
    public void set(E oldElement, E newElement) {
        for (Node<E> d1 = begin.right; d1 != null; d1 = d1.right) {
            if (oldElement.equals(d1.data)) {
               d1.data = newElement;
               break;
            }
        }
    }

    /**
     * Elemento keitimas pagal nurodytą vietą
     * @param place   Vieta
     * @param element Elementas
     */
    @Override
    public void set(int place, E element) {
        if (place >= 0 && place < Size) {
            System.out.println("fs");
            int counter = -1;
            for (Node<E> d1 = begin.right; d1 != null; d1 = d1.right) {
                if (place == counter) {
                   d1.data = element;
                   break;
                }
                counter++;
            }
        }
    }

    /**
     * Sąrašo dydis
     * @return Sąrašo dydis
     */
    @Override
    public int Size() {
        return Size;
    }

    /**
     * Sąrašo išvalymas
     */
    @Override
    public void clear() {
       begin = new Node<E>(null, null, null);
       end = new Node<E>(null, begin, null);
       pre = begin;
       begin.right = end;
       pab = end;
       l = null;
       store = null;
    }

    /**
     * Patikrinama ar elementas yra sąraše
     * @param element Elementas
     * @return        Jei yra elementas sąraše true, neradus false
     */
    @Override
    public boolean contains(E element) {
        Node<E> current = begin;
        for (Node<E> d1 = current.right; d1 != null; d1 = d1.right) {
            if (element.equals(d1.data)) {
               return true;
            }
        }
        return false;
    }
    
    /**
     * Pašalinamas nurodytoje vietoje esantis elementas
     * @param place Vieta
     * @return      Grąžinamas pašalintas elementas
     */
    @Override
    public E remove(int place) {
        if (place >= Size && place < 0) {
            return null;
        }
        int counter = -1;
        Node<E> current = begin;
        E temp = null;
        for (Node<E> d1 = current.right; d1 != null; d1 = d1.right) {
            if (place == counter) {
                if (d1.right != null) {
                    temp = d1.data;
                    d1.left.right = d1.right;
                    d1.right.left = d1.left;
                    Size--;
                }
                else{ //Pabaigos sutvarkymas
                    temp = d1.data;
                    d1 = current;
                    d1.right = null;
                    end.left = end.left.left;
                    Size--;
                }
            }
            if (place != counter) {
                current = d1;
            }
            counter++;
        }
        return temp;
    }
    
    /**
     * Pašalinami nuo iki(įskaitant) elementai pagal vietas
     * @param beginPlace Pradžios elemento vieta
     * @param endPlace   Pabaigos elemento vieta
     */
    @Override
    public void removeRange(int beginPlace, int endPlace) {
        if (beginPlace >= 0 && beginPlace < Size && endPlace < Size && endPlace > 0 && beginPlace != endPlace) {
                       Node<E> current = begin;
                    int time = 0;
            for (Node<E> d1 = current.right; d1 != null; d1 = d1.right) {
                    if (endPlace == time) {
                       current.left.right = d1.right;
                       d1.right.left = current.left;
                       break;
                    }
                    if (beginPlace == time)
                    {
                        current = d1;
                    }
                    time++;
            }
                    Size += beginPlace -1 - endPlace;
        }
    }

    /**
     * Pašalinami nuo iki(įskaitant) elementai pagal nurodytus elementus
     * @param beginElement Pradžios elementas
     * @param endElement   Pabaigos elementas
     */
    @Override
    public void removeRange(E beginElement, E endElement) {
        if (contains(beginElement) && contains(endElement) && !beginElement.equals(endElement)) {

                    Node<E> current = begin;
                    int temp = 0;
                    int time = 1;
            for (Node<E> d1 = current.right; d1 != null; d1 = d1.right) {
                if(temp == 1)
                time++;
                if (endElement.equals(d1.data)) {
                       current.left.right = d1.right;
                       d1.right.left = current.left;
                       break;
                    }
                    if (d1.data.equals(beginElement) && temp == 0)
                    {
                        temp++;
                        current = d1;
                    }
                }
            Size -= time;
        }
    }

    /**
     * Gaunamas elementas pagal vietą
     * @param place Vieta
     * @return      Elementas
     */
    @Override
    public E get(int place) {
        int time = 0;
        for (Node<E> d1 = begin.right; d1 != null; d1 = d1.right) {
            if (place == time) {
                return d1.data;
            }
            time++;
        }
        return null;
    }
    /**
     * Formuojamas Object masyvas (E tipo masyvo negalima sukurti) ir surašomi
     * sąrašo elementai
     *
     * @return Sąrašo elementų masyvas
     */
    @Override
    public Object[] toArray() {
        Object[] a = new Object[Size];
        int i = 0;
        for (Node<E> d1 = begin.right; d1 != null; d1 = d1.right) {
            a[i++] = d1.data;
        }
        return a;
    }

    /**
     * Visų sąrašo elementų klonavimas
     * @return Sąrašo kopija
     */
    @Override
    public DoublyLinkedList<E> clone() {
        if(begin.right == null) return null;
        DoublyLinkedList<E> twin = new DoublyLinkedList<E>();
        for (Node<E> d1 = begin.right; d1.right != null; d1 = d1.right) {
            twin.add(d1.data);
             //twin.begin.data = begin.right.data;
        }
        return twin;
    }
    /**
     * Mazgo klasė
     * @param <E> 
     */
    private class Node<E>{
        public E data;
        public Node<E> left;
        public Node<E> right;

        public Node(E data, Node<E> left, Node<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
