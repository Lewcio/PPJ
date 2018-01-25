public class LinkedList {
    private Node first, last;
    private int size;
    public LinkedList() {
        Node node = new Node(null, null);
        first = node;
        last = node;
        first.setNext(last);
        size = 0;
    }
    public void insert(char value) {
        Node node = new Node(value, null);

        if (size > 1) {
            last.setNext(node);
            last = node;
        }
        else if (size == 0) {
            first = node;
        } else {
            first.setNext(node);
            last = node;
        }
        size++;
    }
    public boolean hasText(String text) {
        Node node = first;
        int r = 0;
        while (node.next != null) {
            if (text.charAt(r) == node.value) {
                r++;
                if (r > text.length() - 1) return true;
            } else {
                r = 0;
            }
            node = node.next;
        }
        return false;
    }
}
