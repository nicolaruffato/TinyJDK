package Collection;

public class LinkedList<T> implements List<T> {

    protected static class Node<E> {
        protected E element;
        protected Node<E> next;
        protected Node<E> prev;

        protected Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        protected Node(E element) {
            this(element, null, null);
        }
    }

    protected Node<T> head;
    protected Node<T> tail;
    protected int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void add(int index, T e) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        else if(index == 0) {
            head = new Node<>(e, null, head);
            if(tail == null) {
                tail = head;
            }
        }
        else if(index == size - 1) {
            tail.prev = new Node<>(e, tail.prev, tail);
            tail.prev.prev.next = tail.prev;
        }
        else {
            Node<T> tmp = head;
            while(index - 1 > 0) {
                tmp = tmp.next;
                index--;
            }
            tmp.next = new Node<>(e, tmp, tmp.next);
            tmp.next.next.prev = tmp.next;
        }
        size++;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        else {
            Node<T> tmp = head;
            while(index > 0) {
                tmp = tmp.next;
                index--;
            }
            return tmp.element;
        }
    }

    @Override
    public T remove(int index) {
        T ret;
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        else if(index == 0){
            ret = head.element;
            if(size == 1) {
                head = tail = null;
            }
            else {
                head = head.next;
                head.prev = null;
            }
        }
        else if(index == size-1) {
            ret = tail.element;
            tail = tail.prev;
            tail.next = null;
        }
        else {
            Node<T> tmp = head;
            while(index-1 > 0) {
                tmp = tmp.next;
                index--;
            }
            ret = tmp.next.element;
            tmp.next = tmp.next.next;
            tmp.next.prev = tmp;
        }
        size--;
        return ret;
    }

    @Override
    public T set(int index, T e) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        else {
            Node<T> tmp = head;
            while(index > 0) {
                tmp = tmp.next;
                index--;
            }
            T ret = tmp.element;
            tmp.element = e;
            return ret;
        }
    }

    @Override
    public boolean add(T e) {
        if(head == null) {
            head = tail = new Node<>(e, null, null);
        }
        else {
            tail = new Node<>(e, tail, null);
            tail.prev.next = tail;
        }
        size++;
        return true;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean contains(T e) {
        Node<T> tmp = head;
        while(tmp != null) {
            if(tmp.element.equals(e)) {
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    @Override
    public boolean remove(T e) {
        int indx = 0;
        Node<T> tmp = head;
        while(indx < size) {
            if(tmp.element == e) {
                remove(indx);
                return true;
            }
            indx++;
            tmp = tmp.next;
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

            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T ret = current.element;
                current = current.next;
                return ret;
            }
        };
    }





}
