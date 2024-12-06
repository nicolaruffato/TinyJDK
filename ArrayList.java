public class ArrayList<T> implements Iterable<T>, Collection<T>, List<T> {

    private T[] data;
    private int size;


    public ArrayList() {
        data = (T[]) new Object[10];
        size = 0;
    }

    public ArrayList(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    @Override
    public void add(int index, T e) {
        resize();
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        else {
            for(int i = size+1; i > index; i--) {
                data[i] = data[i-1];
            }
            data[index] = e;
            size++;
        }
    }

    @Override
    public T get(int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        else {
            return data[index];
        }
    }

    @Override
    public T remove(int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        else {
            T removed = data[index];
            for(int i = index; i < size - 1 ; i++) {
                data[i] = data[i+1];
            }
            data[size - 1] = null;
            size--;
            resize();
            return removed;
        }
    }

    @Override
    public T set(int index, T e) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        else {
            T old = data[index];
            data[index] = e;
            return old;
        }
    }

    @Override
    public boolean add(T e) {
        resize();
        data[size++] = e;
        return true;
    }

    @Override
    public void clear() {
        size = 0;
        data = (T[]) new Object[10];
    }

    @Override
    public boolean contains(T e) {
        for(int i = 0; i < size; i++) {
            if(data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(T e) {
        for(int i = 0; i < size; i++) {
            if(data[i].equals(e)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return data[currentIndex++];
            }
        };
    }

    private void resize() {
        if(size == data.length) {
            T[] newData = (T[]) new Object[2 * data.length];
            for(int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
        else if(size < data.length / 4) {
            T[] newData = (T[]) new Object[data.length / 2];
            for(int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }

}
