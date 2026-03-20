import java.util.*;

class Stack {
    private int MaxSize;
    private int [] StackArray;
    private int top;

    public Stack (int size) {
        MaxSize = size;
        StackArray = new int[size];
        top = -1;
    }

    // peek
    public int peek () {
        return StackArray[top];
    }

    // push
    public void push (int e) {
        StackArray[++top] = e;
    }

    // pop
    public void pop () {
        top--;
    }

    // display
    public void display () {
        for (int i = 0; i <= top; i++) {
            System.out.print(StackArray[i] + " ");
        }
        System.out.println();
    }

    public boolean duplicate() {
        for (int i = 0; i <= top; i++) {
            for (int j = i + 1; j <= top; j++) {
                if (StackArray[i] == StackArray[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public void RemoveDuplicate() {
        for (int i = 0; i <= top; i++) {
            for (int j = i + 1; j <= top; j++) {
                if (StackArray[i] == StackArray[j]) {
                    for (int k = j; k < top; k++) {
                        StackArray[k] = StackArray[k + 1];
                    }
                    top--;
                    j--;
                }
            }
        }
    }

    public boolean odd() {
        return (top + 1) % 2 == 1;
    }

    // sort
    public void sort() {
        for (int i = 0; i <= top; i++) {
            for (int j = i + 1; j <= top; j++) {
                if (StackArray[i] > StackArray[j]) {
                    int temp = StackArray[i];
                    StackArray[i] = StackArray[j];
                    StackArray[j] = temp;
                }
            }
        }
        for (int i = 0; i <= top; i++) {
            System.out.print(StackArray[i] + " ");
        }
        System.out.println();
    }

    public boolean full () {
        return (top == MaxSize - 1);
    }

    public boolean empty () {
        return (top == -1);
    }
}

class LinkedList {
    public int element;
    public LinkedList prev;
    public LinkedList next;

    public LinkedList(int element) {
        this.element = element;
    }
}

class doubly {
    private LinkedList first;
    private LinkedList last;
    private int MaxSize;
    private int count;

    public doubly(int size) {
        MaxSize = size;
        first = null;
        last = null;
        count = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public boolean isFull() {
        return count >= MaxSize;
    }

    public void add(int element) {
        if (isFull()) {
            System.out.println("Linked List is full! Cannot add more elements.");
            return;
        }

        LinkedList newLink = new LinkedList(element);

        if (isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
            newLink.prev = last;
        }

        last = newLink;
        count++;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Linked List is empty!");
            return;
        }

        LinkedList current = last;
        while (current != null) {
            System.out.print(current.element + " ");
            current = current.prev;
        }
        System.out.println();
    }

    public void search(int key) {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }

        LinkedList current = first;
        int countPos = 1;
        boolean found = false;

        while (current != null) {
            if (current.element == key) {
                found = true;
                break;
            }
            current = current.next;
            countPos++;
        }

        if (found) {
            System.out.println("Element " + key + " is in position " + countPos + ".");
        } else {
            System.out.println("Element not found.");
        }
    }

    public void delete(int key) {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }

        LinkedList current = first;
        boolean found = false;

        while (current != null) {
            if (current.element == key) {
                current.element = key + 1;
                found = true;
                break;
            }
            current = current.next;
        }

        if (found) {
            System.out.println("Element " + key + " has been deleted and replaced with " + (key + 1) + ".");
        } else {
            System.out.println("Element " + key + " not found.");
        }
    }

    public void removeDuplicate() {
        LinkedList current = first;

        while (current != null) {
            LinkedList temp = current.next;
            while (temp != null) {
                if (temp.element == current.element) {
                    if (temp == last) {
                        last = temp.prev;
                    }
                    if (temp.prev != null) {
                        temp.prev.next = temp.next;
                    }
                    if (temp.next != null) {
                        temp.next.prev = temp.prev;
                    }
                }
                temp = temp.next;
            }
            current = current.next;
        }
    }

    public boolean odd() {
        return count % 2 == 1;
    }

    public void sort() {
        if (first == null) return;

        for (int i = 0; i < count - 1; i++) {
            LinkedList current = first;
            while (current.next != null) {
                if (current.element > current.next.element) {
                    int temp = current.element;
                    current.element = current.next.element;
                    current.next.element = temp;
                }
                current = current.next;
            }
        }

        LinkedList current = first;
        while (current != null) {
            System.out.print(current.element + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void deleteIFodd () {
        if (first.next == null) {
            first = null;
        }
        else {
            last.prev.next = null;
            last = last.prev;
        }
    }
}


public class StackXLinkedList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nMAIN MENU");
            System.out.println("[A] STACK");
            System.out.println("[B] LINKED LIST");
            System.out.println("[E] EXIT");
            System.out.print("> ");
            String choice = sc.nextLine();

            if (choice.equalsIgnoreCase("A")) {

                System.out.print("Set the maximum size of your Stack: ");
                int size = sc.nextInt();
                sc.nextLine();
                Stack stack = new Stack(size);

                System.out.println("ENTER ELEMENTS (-1 TO STOP): ");
                while (true) {
                    if (stack.full()) {
                        break;
                    }

                    int e = sc.nextInt();
                    sc.nextLine();
                    if (e == -1) {
                        break;
                    }

                    stack.push(e);
                }

                while (true) {
                    System.out.println("\nMENU:");
                    System.out.println("[1] PEEK");
                    System.out.println("[2] PUSH");
                    System.out.println("[3] POP");
                    System.out.println("[4] DISPLAY");
                    System.out.println("[5] SORT");
                    System.out.println("[6] EXIT");
                    System.out.print("> ");
                    String ch = sc.nextLine();

                    if (ch.equals("1")) {
                        if (stack.empty()){
                            System.out.println("Stack is empty! Nothing to peek.");
                        }
                        //
                        System.out.println("Element at top: " + stack.peek());
                        while (true) {
                            System.out.println("Do you want to continue? Y if yes (Go back to Menu) and N if no (Go back to Main Menu)");
                            System.out.print("> ");
                            char input = Character.toUpperCase(sc.next().charAt(0));
                            sc.nextLine();
                            if (input == 'Y') {
                                System.out.println("Returning to STACK MENU...");
                                break;
                            } else if (input == 'N') {
                                System.out.println("Returning to MAIN MENU....");
                                main(args);
                            } else {
                                System.out.println("Invalid input. Try again.");
                            }
                        }
                    } else if (ch.equals("2")) {
                        if (stack.full()) {
                            System.out.println("Stack is full! Cannot insert element.");
                            stack.display();
                        } else {
                            System.out.print("Insert Element to push: ");
                            int push = sc.nextInt();
                            sc.nextLine();
                            stack.push(push);
                            stack.display();
                        }
                        //
                        while (true) {
                            System.out.println("Do you want to continue? Y if yes (Go back to Menu) and N if no (Go back to Main Menu)");
                            System.out.print("> ");
                            char input = Character.toUpperCase(sc.next().charAt(0));
                            sc.nextLine();
                            if (input == 'Y') {
                                System.out.println("Returning to STACK MENU...");
                                break;
                            } else if (input == 'N') {
                                System.out.println("Returning to MAIN MENU....");
                                main(args);
                            } else {
                                System.out.println("Invalid input. Try again.");
                            }
                        }
                    } else if (ch.equals("3")) {
                        System.out.println("Deleting an element...");
                        stack.pop();
                        stack.display();
                        if (stack.empty()) {
                            System.out.println("Stack is empty! Nothing to delete.");
                        }
                        //
                        while (true) {
                            System.out.println("Do you want to continue? Y if yes (Go back to Menu) and N if no (Go back to Main Menu)");
                            System.out.print("> ");
                            char input = Character.toUpperCase(sc.next().charAt(0));
                            sc.nextLine();
                            if (input == 'Y') {
                                System.out.println("Returning to STACK MENU...");
                                break;
                            } else if (input == 'N') {
                                System.out.println("Returning to MAIN MENU....");
                                main(args);
                            } else {
                                System.out.println("Invalid input. Try again.");
                            }
                        }
                    } else if (ch.equals("4")) {
                        System.out.println("Displaying Elements: ");
                        if (stack.empty()) {
                            System.out.println("Stack is Empty! Nothing to display.");
                        }
                        stack.display();
                        //
                        while (true) {
                            System.out.println("Do you want to continue? Y if yes (Go back to Menu) and N if no (Go back to Main Menu)");
                            System.out.print("> ");
                            char input = Character.toUpperCase(sc.next().charAt(0));
                            sc.nextLine();
                            if (input == 'Y') {
                                System.out.println("Returning to STACK MENU...");
                                break;
                            } else if (input == 'N') {
                                System.out.println("Returning to MAIN MENU...");
                                main(args);
                            } else {
                                System.out.println("Invalid input. Try again.");
                            }
                        }
                    } else if (ch.equals("5")) {
                        if (stack.empty()) {
                            System.out.println("Stack is empty! Nothing to sort.");
                        }
                        System.out.println("Checking and removing duplicate elements...");
                        if (stack.duplicate()) {
                            System.out.println("Duplicates found.");
                            System.out.println("Removing Duplicates...");
                            stack.RemoveDuplicate();
                        }
                        else {
                            System.out.println("No duplicates found.");
                        }
                        stack.display();
                        System.out.println("Checking if size is odd...");
                        if (stack.odd()) {
                            System.out.println("Size is odd, Deleting top element...");
                            stack.pop();
                            System.out.println("Displaying elements...");
                            stack.display();
                            System.out.println("Sorting the elements (using Selection Sort): ");
                            stack.sort();
                        }
                        else {
                            System.out.println("Displaying elements...");
                            stack.display();
                            System.out.println("Sorting the elements (using Selection Sort): ");
                            stack.sort();
                        }
                        //
                        while (true) {
                            System.out.println("Do you want to continue? Y if yes (Go back to Menu) and N if no (Go back to Main Menu)");
                            System.out.print("> ");
                            char input = Character.toUpperCase(sc.next().charAt(0));
                            sc.nextLine();
                            if (input == 'Y') {
                                System.out.println("Returning to Stack menu...");
                                break;
                            } else if (input == 'N') {
                                System.out.println("Returning to MAIN MENU....");
                                main(args);
                            } else {
                                System.out.println("Invalid input. Try again.");
                            }
                        }
                    } else if (ch.equals("6")) {
                        System.out.println("Returning to MAIN MENU....");
                        break;
                    }
                    else {
                        System.out.println("Invalid choice. Try again.");
                    }
                }
            } else if (choice.equalsIgnoreCase("B")) {
                System.out.print("Set the maximum size of your LinkedList: ");
                int s = sc.nextInt();
                sc.nextLine();
                doubly d = new doubly(s);

                System.out.println("ENTER ELEMENTS (-1 TO STOP):");
                while (true) {
                    if (d.isFull()) {
                        break;
                    }

                    int e = sc.nextInt();
                    sc.nextLine();
                    if (e == -1) {
                        break;
                    }

                    d.add(e);
                }

                System.out.print("Enter an element: ");
                int key = sc.nextInt();
                sc.nextLine();
                d.search(key);

                System.out.print("Enter an element to delete: ");
                int delete = sc.nextInt();
                sc.nextLine();
                d.delete(delete);

                System.out.println("Displaying the elements in reverse: ");
                d.display();

                System.out.println("Removing duplicate elements...");
                d.removeDuplicate();

                System.out.println("Checking if size is odd...");
                if (d.odd()) {
                    System.out.println("Size is odd. Removing the last element...");
                    d.deleteIFodd();
                    System.out.println("Displaying elements:");
                    d.display();
                }
                else {
                    System.out.println("Displaying elements:");
                    d.display();
                    System.out.println("Sorting the Elements (using bubble sort): ");
                    d.sort();
                }
                System.out.println("Returning to MAIN MENU....");
            } else if (choice.equalsIgnoreCase("E")) {
                System.out.println("Exiting program...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
