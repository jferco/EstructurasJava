package Estructuras;// Se recomienda contener todas las estructuras dentro de un mismo paquete
/*
 *  Jose Pablo Fernandez Cordero.
 *  2014067396
 * Fecha de creacion:Noviembre 2015
 *  Ultima actualizacion: mayo 2017

 Lista enlazada. Utiliza Generics
 */
/**
 * @param <E>
 */
public class ListaEnlazada<E> {

    private class Node<E>{
        //Atributos

        private E element;
        private Node next;

        //Constructores
        public Node(){
            this.element=null;
            this.next=null;
        }
        public Node (E element){
            this.element=element;
            this.next = null;
        }
        public Node (E element, Node next){
            this.element=element;
            this.next=next;
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
    }

    //Atributos de la lista enlazada

    private Node head;
    Node current;
    private Node tail;
    private int position;
    private int size;

    //Constructor ListEnlazada


    public ListaEnlazada(){
        this.head=new Node<E>();
        this.current =this.head;
        this.tail=this.head;
        this.size=0;
        this.position=-1;
        //insert(null);
    }

    //Métodos{

    public void update(E element){
        current.setElement(element);
    }



    public void insert(E element){
        Node newNode=new Node<>(element,this.current.getNext());
        this.current.setNext(newNode);
        if(this.current==this.tail){
            this.tail=tail.getNext();
        }
        this.size++;
    }

    //Agregar al final
    public void append(E element){
        Node newNode =new Node<>(element);
        tail.setNext(newNode);
        tail=newNode;
        if(current==head){
            current=head.getNext();
        }
        this.size++;
    }

    public void remove(){
        //si la lista está vacía retorna un mensaje de error
        if((this.head==this.current)&&(this.head==this.tail)){
            return;
        }
        //busca el nodo anterior
        Node temp=head;
        while(temp.getNext()!= this.current){
            temp=temp.getNext();
        }

        //Fija el next del temp = al next del current
        temp.setNext(this.current.getNext());
        //Si el current es la cola, el temporal se vuelve la cola
        if(this.current==this.tail){
            this.current=this.tail=temp;
            this.position--;
        }

        //Separa el current para que el garbage collector lo elimine
        else
            this.current=this.current.getNext();
        //reduce el tamaño de la lista
        this.size--;
    }

    //Elimina todos los nodos de la lista
    public void clear(){
        //Iguala todos los punteros a un nodo nuevo y vacío
        this.head=this.tail=this.current=new Node();
        //Posición en -1
        this.position=-1;
        //Tamaño en 0
        this.size=0;
    }

    //Retorna el elemento del current
    public E getElement(){
        return (E) this.current.getElement();
    }

    //Retorna el tamaño de la lista
    public int getSize(){
        return this.size;
    }

    //Avanza el current al siguiente
    public void next(){
        if(this.current==this.tail){
            return;
        }
        this.current=this.current.getNext();
        this.position++;
    }

    //Retrocede el current al nodo anterior
    public void previous(){

        //Si el current está en la cabeza, no se puede retroceder
        if(this.current==this.head){
            return;
        }
        //Crea un nodo temporal al comienzod de la lista
        Node temp=head;

        //Posición al comienzo de la lista
        this.position=-1;

        //Busca el nodo anterior al current
        while(temp.getNext()!=this.current){
            temp=temp.getNext();
            this.position++;
        }
        //Iguala el current al nodo anterior
        this.current=temp;
    }

    //Retorna la posición del current
    public int getPosition(){
        return this.position;
    }

    //Coloca el puntero en el inicio de la lista
    public void goToStart(){
        this.current= this.head.getNext();
        this.position=0;
    }

    //Coloca el puntero al final de la lista
    public void goToEnd(){
        this.current=this.tail;
        this.position=this.size-1;
    }

    //Coloca el puntero en la posición ingresada
    public void goToPos(int pos){
        if(pos<0||pos>=this.size){
            return;
        }

        if(pos>this.position){
            while(pos>this.position){
                this.next();
            }
        } else if(pos<this.position){
            while(pos<this.position){
                this.previous();
            }
        }
    }

    public void tostring(){
        Node temp=this.head.getNext();
        int indice=0;

        while(indice<this.size){
            Object elemento=temp.getElement();
            System.out.print(elemento+", ");
            temp=temp.getNext();
            indice++;
        }
        System.out.println("\n");

    }

    public boolean isEmpty(){
        if(this.size==0)
            return true;
        else
            return false;
    }
// este main es solo para probar
    public static void main(String [] args){
        ListaEnlazada lista= new ListaEnlazada();
        lista.append(3);
        lista.append(4);
        lista.append(5);
        lista.append(1);
        lista.append(9);
     //   lista.tostring();

    /*    System.out.println("\nTamaño= "+lista.getSize());
        lista.remove();

        lista.tostring();

        lista.next();
        lista.remove();
        lista.tostring();
        System.out.println(lista.getSize());*/
        lista.goToStart();
        System.out.println(lista.getSize());
        for(int i =0;i<lista.getSize();i++){
            lista.goToPos(i);
            System.out.println(lista.getElement());
           // lista.tostring();
        }

    }

}
