import java.util.*;

public class StackQueueProgram {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean again = true;

        while (again) {
            System.out.println("\nPlease choose the data structure you want to implement:");
            System.out.println("[A] STACK");
            System.out.println("[B] QUEUE");
            System.out.print("Enter choice: ");
            char input = Character.toUpperCase(sc.next().charAt(0));

            if (input == 'A') {
                stackMenu();
            } else if (input == 'B') {
                queueMenu();
            } else {
                System.out.println("Invalid choice.");
            }

            again = cont();
        }

        System.out.println("Program ended. Goodbye!");
    }

    // ---------------- STACK SECTION ----------------
    static void stackMenu() {
        System.out.print("Enter maximum size of stack: ");
        int size = sc.nextInt();
        StackX stack = new StackX(size);
        HashSet<Integer> duplicates = new HashSet<>();

        System.out.println("Enter elements (-1 to stop):");
        while (!stack.isFull()) {
            int val = sc.nextInt();
            if (val == -1) break; // FLAG to stop input

            if (duplicates.contains(val)) {
                System.out.println("Duplicate found, skipping...");
            } else {
                stack.push(val);
                duplicates.add(val);
            }
        }

        System.out.println("\nStack contents:");
        stack.display();

        System.out.println("\nPopping top element...");
        if (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop());
        }

        System.out.println("\nFinal Stack:");
        stack.display();
    }

    // ---------------- QUEUE SECTION ----------------
    static void queueMenu() {
        System.out.print("Enter maximum size of queue: ");
        int size = sc.nextInt();
        QueueX queue = new QueueX(size);
        HashSet<Integer> duplicates = new HashSet<>();

        System.out.println("Enter elements (-1 to stop):");
        while (!queue.isFull()) {
            int val = sc.nextInt();
            if (val == -1) break; // FLAG to stop input

            if (duplicates.contains(val)) {
                System.out.println("Duplicate found, skipping...");
            } else {
                queue.enqueue(val);
                duplicates.add(val);
            }
        }

        System.out.println("\nQueue contents:");
        queue.display();

        System.out.println("\nDequeuing front element...");
        if (!queue.isEmpty()) {
            System.out.println("Dequeued: " + queue.dequeue());
        }

        System.out.println("\nFinal Queue:");
        queue.display();
    }

    // ---------------- CONTINUE OPTION ----------------
    static boolean cont() {
        System.out.print("\nDo you want to continue? Y if Yes(Go back to Main Menu), N if No(Exit): ");
        char cont = Character.toUpperCase(sc.next().charAt(0));
        return cont == 'Y';
    }
}

// ---------------- STACK CLASS ----------------
class StackX {
    private int maxSize;
    private int[] stackArray;
    private int top;

    public StackX(int s) {
        maxSize = s;
        stackArray = new int[maxSize];
        top = -1;
    }

    public void push(int val) {
        if (!isFull()) {
            stackArray[++top] = val;
        } else {
            System.out.println("Stack is full.");
        }
    }

    public int pop() {
        if (!isEmpty()) {
            return stackArray[top--];
        } else {
            System.out.println("Stack is empty.");
            return -1;
        }
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("[Empty Stack]");
        } else {
            for (int i = top; i >= 0; i--) {
                System.out.println(stackArray[i]);
            }
        }
    }
}

// ---------------- QUEUE CLASS ----------------
class QueueX {
    private int maxSize;
    private int[] queueArray;
    private int front;
    private int rear;
    private int nItems;

    public QueueX(int s) {
        maxSize = s;
        queueArray = new int[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public void enqueue(int val) {
        if (!isFull()) {
            if (rear == maxSize - 1) rear = -1;
            queueArray[++rear] = val;
            nItems++;
        } else {
            System.out.println("Queue is full.");
        }
    }

    public int dequeue() {
        if (!isEmpty()) {
            int temp = queueArray[front++];
            if (front == maxSize) front = 0;
            nItems--;
            return temp;
        } else {
            System.out.println("Queue is empty.");
            return -1;
        }
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("[Empty Queue]");
        } else {
            int i = front;
            int count = 0;
            while (count < nItems) {
                System.out.println(queueArray[i]);
                i = (i + 1) % maxSize;
                count++;
            }
        }
    }
}
