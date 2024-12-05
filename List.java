public interface List<T> extends Iterable<T>, Collection<T> {

    void add(int index, T e);
    T get(int index);
    T remove(int index);
    T set(int index, T e);

}
