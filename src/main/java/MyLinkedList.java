import java.util.Iterator;

/**
 * @author dong
 * @Title: MyLinkedList
 * @ProjectName esTest
 * @Description: 测试
 * @date 2019/4/30下午 1:44
 */
public class MyLinkedList<T> implements Iterable<T> {

    private Node<T> root = new Node<T>();
    private Node<T> last = root;

    public synchronized void add(T val){
        Node<T> addNode = new Node<T>();
        addNode.setValue(val);
        last.setNext(addNode);
        last = addNode;
    }

    public synchronized T get(){
        T result = null;
        Node<T> getNode = root.getNext();
        if(getNode!=null){
            result = getNode.getValue();
            root.setNext(getNode.getNext());
        }else{
            last = root;
        }
        return result;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            public void remove() {

            }
            public boolean hasNext() {
                return root.getNext()!=null;
            }
            public T next() {
                return get();
            }
        };
    }


    class Node<T>{
        private T value;
        private Node<T> next;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
