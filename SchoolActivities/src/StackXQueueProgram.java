import java.util.*;

class Stack {
    private int maxSize;
    private long[] stackArray;
    private int top;

    public Stack(int size) {
        maxSize = size;
        stackArray = new long[maxSize];
        top = -1;
    }

    public void push(long e) {
        stackArray[++top] = e;
    }

    public long pop() {
        return stackArray[top--];
    }

    public long peek() {
        return stackArray[top];
    }

    public void display() {
        for (int i = 0; i <= top; i++) {
            System.out.print(stackArray[i] + " ");
        }
        System.out.println();
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isDuplicate(long e) {
        for (int i = 0; i <= top; i++) {
            if (stackArray[i] == e) {
                return true;
            }
        }
        return false;
    }
}

class Queue {
    private int maxSize;
    private long[] queArray;
    private int front;
    private int rear;
    private int nItems;

    public Queue(int size) {
        maxSize = size;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public void insert(long e) {
        if (rear == maxSize - 1) {
            rear = -1;
        }
        queArray[++rear] = e;
        nItems++;
    }

    public void remove() {
        front++;
        if (front == maxSize) {
            front = 0;
        }
        nItems--;
    }

    public long peekFront() {
        return queArray[front];
    }

    public void display() {
        int i = front;
        int count = 0;
        while (count < nItems) {
            System.out.print(queArray[i] + " ");
            i = (i + 1) % maxSize;
            count++;
        }
        System.out.println();
    }

    public boolean isDuplicate(long e) {
        int count = 0;
        int index = front;
        while (count < nItems) {
            if (queArray[index] == e)
                return true;
            index = (index + 1) % maxSize;
            count++;
        }
        return false;
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }
}

public class StackXQueueProgram {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {

            System.out.println("\n========================================");
            System.out.println("                  MENU");
            System.out.println("========================================");
            System.out.println("Please choose the data structure you want to implement:");
            System.out.println("[A] STACK");
            System.out.println("[B] QUEUE");
            System.out.println("[X] EXIT");
            System.out.print("> ");
            char input = Character.toUpperCase(sc.next().charAt(0));

            if (input == 'A') {
                System.out.print("Set the maximum size of the stack: ");
                int size = sc.nextInt();
                Stack stack = new Stack(size);

                System.out.println("Enter Elements (-1 to stop): ");
                while (!stack.isFull()) {
                    long e = sc.nextLong();
                    if (e == -1) {
                        break;
                    }
                    if (stack.isDuplicate(e)) {
                        System.out.println("!! Duplicate element found !!");
                        System.out.println("[Failed to insert]");
                    } else {
                        stack.push(e);
                    }
                }

                boolean again = true;
                while (again) {
                    System.out.println("\n========================================");
                    System.out.println("              STACK MENU");
                    System.out.println("========================================");
                    System.out.println("[1] PEEK");
                    System.out.println("[2] PUSH");
                    System.out.println("[3] POP");
                    System.out.println("[4] DISPLAY");
                    System.out.println("[5] EXIT");
                    System.out.print("> ");
                    int choice = sc.nextInt();

                    if (choice == 1) {
                        if (stack.isEmpty()) {
                            System.out.println("Stack is empty. Nothing to peek.");
                        } else {
                            System.out.println("Element at top: " + stack.peek());
                        }
                        again = cont();
                    } else if (choice == 2) {
                        System.out.print("Enter element to push: ");
                        int e = sc.nextInt();
                        if (stack.isFull()) {
                            System.out.println("Stack is full. Cannot add more elements.");
                        } else if (stack.isDuplicate(e)) {
                            System.out.println("!! Duplicate element found !!");
                            System.out.println("[Failed to insert]");
                        } else {
                            stack.push(e);
                            System.out.println("Element added successfully.");
                            System.out.println("Element added: " + e);
                        }
                        again = cont();
                    } else if (choice == 3) {
                        System.out.println("Deleting element...");
                        if (stack.isEmpty()) {
                            System.out.println("Stack empty. There is nothing to delete.");
                        } else {
                            long delete = stack.pop();
                            System.out.println("Popped element: " + delete);
                        }
                        again = cont();
                    } else if (choice == 4) {
                        System.out.println("Displaying the elements: ");
                        stack.display();
                        again = cont();
                    } else if (choice == 5) {
                        System.out.println("Returning to MAIN MENU...");
                        break;
                    } else {
                        System.out.println("\n========================================");
                        System.out.println("Invalid input. Please try again.");
                        System.out.println("========================================\n");
                    }
                }

            } else if (input == 'B') {
                System.out.print("Set the maximum size of the queue: ");
                int size = sc.nextInt();
                Queue queue = new Queue(size);

                System.out.println("Enter Elements (-1 to stop): ");
                while (!queue.isFull()) {
                    long e = sc.nextLong();
                    if (e == -1) {
                        break;
                    }
                    if (queue.isDuplicate(e)) {
                        System.out.println("!! Duplicate element found !!");
                        System.out.println("[Failed to insert]");
                    } else {
                        queue.insert(e);
                    }
                }

                boolean again = true;
                while (again) {
                    System.out.println("\n========================================");
                    System.out.println("               QUEUE MENU");
                    System.out.println("========================================");
                    System.out.println("[1] PEEK");
                    System.out.println("[2] ENQUEUE");
                    System.out.println("[3] DEQUEUE");
                    System.out.println("[4] DISPLAY");
                    System.out.println("[5] EXIT");
                    System.out.print("> ");
                    int choice = sc.nextInt();

                    if (choice == 1) {
                        if (queue.isEmpty()) {
                            System.out.println("Queue empty. Nothing to peek.");
                        } else {
                            System.out.println("Element at the front: " + queue.peekFront());
                        }
                        again = cont();
                    } else if (choice == 2) {
                        System.out.print("Enter element to insert: ");
                        int e = sc.nextInt();
                        if (queue.isFull()) {
                            System.out.println("Queue is full. Must delete front element before inserting.");
                        } else if (queue.isDuplicate(e)) {
                            System.out.println("!! Duplicate element found !!");
                            System.out.println("[Failed to insert]");
                        } else {
                            queue.insert(e);
                            System.out.println("Element added successfully.");
                            System.out.println("Element added: " + e);
                        }
                        again = cont();
                    } else if (choice == 3) {
                        System.out.println("Deleting an element...");
                        if (queue.isEmpty()) {
                            System.out.println("Queue empty. There is nothing to delete.");
                        } else {
                            long delete = queue.peekFront();
                            queue.remove();
                            System.out.println("Dequeued element: " + delete);
                        }
                        again = cont();
                    } else if (choice == 4) {
                        System.out.println("Displaying the elements: ");
                        queue.display();
                        again = cont();
                    } else if (choice == 5) {
                        System.out.println("Returning to MAIN MENU...");
                        break;
                    } else {
                        System.out.println("\n========================================");
                        System.out.println("Invalid input. Please try again.");
                        System.out.println("========================================\n");
                    }
                }

            } else if (input == 'X') {
                System.out.println("The program will now exit.....");
                sc.close();
                System.exit(0);
            } else {
                System.out.println("\n========================================");
                System.out.println("Invalid input. Please try again.");
                System.out.println("========================================\n");
            }
        }
    }

    static boolean cont() {
        char cont;
        while (true) {

            System.out.println("\nDo you want to continue? Y if Yes(Back to MENU), N if No(Back to MAIN MENU): ");
            System.out.print("> ");
            cont = Character.toUpperCase(sc.next().charAt(0));

            if (cont == 'Y') {
                return true;
            } else if (cont == 'N') {
                return false;
            } else {
                System.out.println("\n========================================");
                System.out.println("Invalid input. Please try again.");
                System.out.println("========================================\n");
            }
        }
    }
}
