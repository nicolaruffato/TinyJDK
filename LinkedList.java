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
            tail.next = new Node<>(e, tail, null);
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
        return null;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T set(int index, T e) {
        return null;
    }

    @Override
    public boolean add(T e) {
        return false;
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
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }





}
