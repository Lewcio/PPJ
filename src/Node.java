public class Node {
    Character value;
    Node next;
    public Node(Character value, Node next) {
        this.value = value;
        this.next = next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}