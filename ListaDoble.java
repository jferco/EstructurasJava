/*
 *  Jose Pablo Fernandez Cordero.
 *  2014067396
 * Fecha de creacion:Noviembre 2015
 *  Ultima actualizacion: mayo 2017

 Lista doblemente enlazada. Utiliza Generics
 */
package Estructuras;// Este paquete se puede obviar 

/**
 *
 * @author Jose Pablo
 * @param <E>
 */
public class ListaDoble<E> {
    /*
    clase privada, anidada, Node, se utiliza unicamente dentro de la lista
    enlazada con el fin de guardar elementos y punteros al siguieente nodo
    */
    private class Node<E>{
        //Atributos

        private E element;
        private Node next;
        private int pos;
        private Node previous;

        //Constructores
        public Node(){
            this.element=null;
            this.next=null;
            this.previous=null;
        }
        public Node (E element){
            this.element=element;
            this.next = null;
        }
        public Node (E element, Node next){
            this.element=element;
            this.next=next;
        }
        public Node (E element, Node next, Node previous, int pos){
            this.element= element;
            this.next=next;
            this.previous=previous;
            this.pos=pos;

        }
        // Métodos

        public E getElement(){
            return this.element;
        }
        public void setElement(E element){
            this.element=element;
        }



        public Node getNext(){
            return this.next;
        }
        public void setNext(Node next){
            this.next=next;
        }

        public Node getPrevious(){
            return this.previous;
        }
        public void setPrevious(Node previous){
            this.previous=previous;
        }
        public int getPos(){
            return this.pos;

        }
        public void setPos(int pos){
            this.pos=pos;
        }
    }

    /*
    Atributos de la clase ListDoble
    */

    private Node head;
    private Node current;
    private Node tail;
    private int size;

    //Constructor ListaDoble
    public ListaDoble(){
        head= new Node<>();
        head.setPos(-1);

        this.current= head;
        this.tail= head;
        this.size=0;
    }

    //Métodos

     public void update(E element){
        current.setElement(element);
        }
    public void insert(E element){
        Node newNode = new Node<> (element);
        //Si el current está en la cabeza
        if(current==head){
            newNode.setPrevious(head);
            newNode.setNext(head.getNext());
            newNode.setPos(1);
            //next cambia el previous al nuevo nodo
            head.getNext().setPrevious(newNode);
            //head cambia el next a newNode
            head.setNext(newNode);
            //current igual al nuevo nodo
            current=newNode;

            //Arregla las posiciones a partir del current
            fixPositions1();
            //Aumenta el tamaño en 1
            this.size++;
        }


        //Si el current está en la cola
        if(current ==tail){
            //coloca el nuevo nodo al final
            tail.setNext(newNode);
            //Asigna el previous del nuevo nodo como la cola anterior
            newNode.setPrevious(tail);
            newNode.setNext(null);
            //Asigna la posición como el tamaño total actual de la lista
            newNode.setPos(size);
            //Tail cambia a newNode
            tail=newNode;
            //Aumenta el tamaño en 1
            size++;
        }
        else{
            //Conecta el nuevo nodo a la estructura
            newNode.setPrevious(current);
            newNode.setNext(current.getNext());
            //Asigna la posición
            newNode.setPos(current.getPos()+1);
            //Cambia los punteros viejos y los conecta al nuevo nodo
            current.getNext().setPrevious(newNode);
            current.setNext(newNode);
            current= newNode;
            //Arregla las posiciones a partir del current
            fixPositions1();
            size++;

        }
    }

    public void append(E element){
        Node newNode= new Node(element);
        tail.setNext(newNode);
        newNode.setPrevious(tail);
        newNode.setNext(null);
        newNode.setPos(size);
        tail= newNode;
        size++;
    }

    public void delete(){
        if (size==0){
            System.out.println("Lista está vacía");
            return;
        }
        if (current==head){
            head.setNext(null);
            tail=head;
            size=0;
            return;
        }
        if(current==tail){
            tail=tail.getPrevious();
            tail.setNext(null);
            size--;
        }
        else{
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
            current=current.getNext();
            current.setPos(current.getPos()-1);
            fixPositions2();
            size--;

        }

    }

    /*
    Método privado utiizado para aumentar las posiciones
    en cada nodo a partir del nodo actual
    */
    private void fixPositions1(){
        Node temp=current.getNext();
        while (temp!=tail){
            //Aumenta la posición
            temp.setPos(temp.getPos()+1);
            //pasa al siguiente nodo
            temp=temp.getNext();
        }
        // el tail no se toma en cuenta, por eso se suma al final
        tail.setPos(tail.getPos()+1);
    }
    private void fixPositions2(){
        Node temp=current.getNext();
        while (temp!=tail){
            //Reduce la posición
            temp.setPos(temp.getPos()-1);
            //pasa al siguiente nodo
            temp=temp.getNext();
        }
        // el tail no se toma en cuenta, por eso se resta al final
        tail.setPos(tail.getPos()-1);

    }
    public void goToPos(int pos){
        if(pos==current.getPos()){
            System.out.println("Ya se encuentra en esa posición");
            return;
        }

        if(pos<0){
            System.out.println("Posición inválida");
            return;
        }
        if(pos>= size){
            System.out.println("Posición inválida");
            return;
        }
        //Si la posición es menor a la actual
        if(pos<current.getPos()){
            Node temp= head;
            while(temp.getPos()!=pos){
                temp=temp.getNext();
            }
            current=temp;
        }
        if (pos>current.getPos()){
            Node temp= tail;
            while(temp.getPos()!=pos){
                temp=temp.getPrevious();
            }
            current=temp;
        }

    }

    public int getSize(){
        return this.size;
    }
    public E getElement(){
        return (E)current.getElement();
    }

    public int getPos(){
        return current.getPos();
    }
    public void next(){
        if(current!=tail)
        current=current.getNext();
    }

}
