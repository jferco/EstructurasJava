
package Estructuras;// Se recomienda contener todas las estructuras dentro de un mismo paquete

/*
 *  Jose Pablo Fernandez Cordero.
 *  2014067396
 * Fecha de creacion:Noviembre 2015
 *  Ultima actualizacion: mayo 2017
 */

//Clase grafo. Esta implementación utiliza listas de adyacencia
//La lista de adyacencia utiliza nodos
public class Grafo<E> {

    public class Vertice<E>{
        /* Clase privada, anidada Par, utilizada únicamente dentro del vértice. Este guarda
        pesos y referencias
        */

        protected class Par{
            private int referencia;
            private double distancia;
            private double tiempo;

            public Par(int referencia,  double distancia, double tiempo){
                this.referencia=referencia;
                this.distancia=distancia;
                this.tiempo=tiempo;
            }

            public int getReferencia() {
                return referencia;
            }

            public void setReferencia(int referencia) {
                this.referencia = referencia;
            }

            public double getDistancia() {
                return distancia;
            }

            public void setDistancia(double distancia) {
                this.distancia = distancia;
            }

            public double getTiempo() {
                return tiempo;
            }

            public void setTiempo(double tiempo) {
                this.tiempo = tiempo;
            }






        }

        ListaEnlazada<Par> aristas= new ListaEnlazada<Par>();

        E elemento;

        public Vertice(E elemento){
            this.elemento=elemento;
        }

        public E getElemento() {
            return elemento;
        }


        public void nuevaRuta(int referencia, double peso, double tiempo){
            Par nuevoPar= new Par(referencia, peso, tiempo);
            aristas.append(nuevoPar);
        }


        public void eliminarRuta( int referencia, double peso){
            aristas.goToStart();
            for(int i=0;i<aristas.getSize();i++){
                Par parActual=aristas.getElement();
                if(parActual.getReferencia()==referencia&&parActual.getDistancia()== peso){
                    aristas.remove();
                    break;
                }
                aristas.next();
            }
        }

        public ListaEnlazada<Par> getAristas(){
            return this.aristas;
        }

    }

    private ListaDoble<Vertice> vertices;

    public Grafo(){
        vertices= new ListaDoble<>();

    }

    public void nuevoVertice(E element){
        Vertice nVertice= new Vertice(element);
        vertices.append(nVertice);
    }

    public void agregarRuta(int vertice, int referencia, double distancia, double tiempo){
        if(vertices.getSize()==0){
            System.out.println("grafo vacío");
            return;
        }
        if(vertice<0){
            System.out.println("Posición de vértice inválida");
            return;
        }
        if(vertice>=vertices.getSize()){
            System.out.println("Posición de vértice inválida");
            return;
        }

        else{
            vertices.goToPos(vertice);
            vertices.getElement().nuevaRuta(referencia, distancia,tiempo);
        }

    }

    public void eliminarVertice(int vertice){
        if(vertices.getSize()==0){
            System.out.println("grafo vacío");
            return;
        }
        if(vertice<0){
            System.out.println("Posición de vértice inválida");
            return;
        }
        if(vertice>=vertices.getSize()){
            System.out.println("Posición de vértice inválida");
            return;
        }
        else{
        vertices.goToPos(vertice);
        vertices.delete();
        }
    }

    public void eliminarRuta(int vertice, int referencia, double peso){
        if(vertices.getSize()==0){
            System.out.println("grafo vacío");
            return;
        }
        if(vertice<0){
            System.out.println("Posición de vértice inválida");
            return;
        }
        if(vertice>=vertices.getSize()){
            System.out.println("Posición de vértice inválida");
            return;
        }
        else{
            vertices.goToPos(vertice);
            vertices.getElement().eliminarRuta(referencia, peso);

        }


    }



    public void mostrarAdyacencias(){
        vertices.goToPos(0);
        for (int i=0; i<vertices.getSize();i++){
            System.out.print("Vértice: "+vertices.getPos()+ " Pesos: ");
            Vertice verticeActual= vertices.getElement();
            ListaEnlazada<Vertice.Par> listaActual= verticeActual.getAristas();
            listaActual.goToStart();
            for(int j=0;j<listaActual.getSize();j++){
                System.out.print(listaActual.getElement().getDistancia()+" ");
                listaActual.next();
            }
            vertices.next();
            System.out.print("\n");


        }


    }
    public ListaDoble<Vertice> getVertices(){
        return this.vertices;
    }




}
