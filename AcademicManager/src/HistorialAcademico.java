import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by ahinri on 11-10-2015.
 */
public class HistorialAcademico {

    Malla malla;
    int sem_actual=0;
    List<Semestre> semestres=new List<Semestre>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Semestre> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Semestre semestre) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Semestre> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Semestre> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Semestre get(int index) {
            return null;
        }

        @Override
        public Semestre set(int index, Semestre element) {
            return null;
        }

        @Override
        public void add(int index, Semestre element) {

        }

        @Override
        public Semestre remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Semestre> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Semestre> listIterator(int index) {
            return null;
        }

        @Override
        public List<Semestre> subList(int fromIndex, int toIndex) {
            return null;
        }
    };

    public HistorialAcademico(Malla malla){
        this.malla=malla;
    }

    public boolean agregarSemestre(){
        try{
            semestres.add(new Semestre());
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    
    
    public void avance(){
    	List aux= new List();
    	List hechos= new List();
    	List faltan=new List();
    	for (Semestre s : semestres){
    		for( Ramo r :s){
    			aux.add(r);
    		}	
    	}
    	for(Ramo x:this.malla){
    		if (aux.contains(x){
    			hechos.add(x);
    			}
    		else{
    			faltan.add(x);
    			}
    		
    	}
    	System.out.println("Ramos avanzados: "+ hechos);
    	System.out.println("Ramos que faltan: "+faltan);
    	
    	
    	
    	
    }
}
