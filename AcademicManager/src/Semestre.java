import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by ahinri on 11-10-2015.
 */
public class Semestre {

    List<Double> notas;
    List<Ramo> ramos= new List<Ramo>() {
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
        public Iterator<Ramo> iterator() {
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
        public boolean add(Ramo ramo) {
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
        public boolean addAll(Collection<? extends Ramo> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Ramo> c) {
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
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public Ramo get(int index) {
            return null;
        }

        @Override
        public Ramo set(int index, Ramo element) {
            return null;
        }

        @Override
        public void add(int index, Ramo element) {

        }

        @Override
        public Ramo remove(int index) {
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
        public ListIterator<Ramo> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Ramo> listIterator(int index) {
            return null;
        }

        @Override
        public List<Ramo> subList(int fromIndex, int toIndex) {
            return null;
        }
    } ;

    public Semestre(){
        notas=new List<Double>() {
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
            public Iterator<Double> iterator() {
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
            public boolean add(Double aDouble) {
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
            public boolean addAll(Collection<? extends Double> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Double> c) {
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
            public Double get(int index) {
                return null;
            }

            @Override
            public Double set(int index, Double element) {
                return null;
            }

            @Override
            public void add(int index, Double element) {

            }

            @Override
            public Double remove(int index) {
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
            public ListIterator<Double> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Double> listIterator(int index) {
                return null;
            }

            @Override
            public List<Double> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

    }

    public boolean agregarRamo(Ramo ramo){
        try{
            ramos.add(ramo);
            //el profesor debe calificar al alumno
            notas.add(1.0);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
