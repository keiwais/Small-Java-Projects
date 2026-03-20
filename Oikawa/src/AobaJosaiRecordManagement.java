import java.util.Scanner;

class Node {
    public int id;
    public String name;
    public float gpa;
    public Node next;
    public Node previous;

    Node(int StudentId, String student, float gpa) {
        this.id = StudentId;
        this.name = student;
        this.gpa = gpa;
    }

    public void DisplayStudents() {
        System.out.printf("[ %d , %s , %.2f ] ", id, name, gpa);
    }
}

class DoublyLinkedListSeijou {
    private Node first;
    private Node last;

    public DoublyLinkedListSeijou() {
        first = null;
        last = null;
    }

    public boolean empty() {
        return first == null;
    }

    // insert last
    public void InsertLast(int id, String name, float gpa) throws Exception {
        Node current = first;
        while (current != null) {
            if (current.id == id) {
                throw new Exception("Error: Student ID " + id + " already exists.");
            }
            current = current.next;
        }

        Node newNode = new Node(id, name, gpa);
        if (empty()) {
            first = newNode;
        } else {
            last.next = newNode;
            newNode.previous = last;
        }
        last = newNode;
    }

    // insert first
    public void InsertFirst(int id, String name, float gpa) throws Exception {
        Node current = first;
        while (current != null) {
            if (current.id == id) {
                throw new Exception("Error: Student ID " + id + " already exists.");
            }
            current = current.next;
        }

        Node newNode = new Node(id, name, gpa);
        if (empty()) {
            last = newNode;
        } else {
            newNode.next = first;
            first.previous = newNode;
        }
        first = newNode;
    }

    // NEW insert after
    public boolean insertAfter(int key, int id, String name, float gpa) throws Exception {
        Node current = first;

        // find the node with key
        while (current != null && current.id != key) {
            current = current.next;
        }

        if (current == null) {
            return false; // key not found
        }

        // check duplicate ID
        Node checker = first;
        while (checker != null) {
            if (checker.id == id) {
                throw new Exception("Error: Student ID " + id + " already exists.");
            }
            checker = checker.next;
        }

        Node newNode = new Node(id, name, gpa);

        if (current == last) { // if inserting at the end
            newNode.next = null;
            last = newNode;
        } else { // inserting in the middle
            newNode.next = current.next;
            current.next.previous = newNode;
        }

        newNode.previous = current;
        current.next = newNode;

        return true;
    }

    // display first to last
    public void FirstLast() {
        System.out.println("First to Last: ");
        Node current = first;
        while (current != null) {
            current.DisplayStudents();
            current = current.next;
        }
        System.out.println();
    }

    // display last to first
    public void LastToFirst() {
        System.out.println("Last to First: ");
        Node current = last;
        while (current != null) {
            current.DisplayStudents();
            current = current.previous;
        }
        System.out.println();
    }

    // search id
    public void Search(int id) throws Exception {
        Node current = first;
        int index = 1;

        while (current != null) {
            if (current.id == id) {
                System.out.println(id + " found at index " + index);
                current.DisplayStudents();
                System.out.println();
                return;
            }
            current = current.next;
            index++;
        }
        throw new Exception("ID not found");
    }

    public Node DeleteUglyStudent(int id) {
        Node current = first;

        while (current != null && current.id != id) {
            current = current.next;
        }
        if (current == null) {
            return null;
        }
        if (current == first) {
            first = current.next;
        } else {
            current.previous.next = current.next;
        }
        if (current == last) {
            last = current.previous;
        } else {
            current.next.previous = current.previous;
        }
        return current;
    }

    // update
    public void update(int id, Scanner sc) throws Exception {
        Node current = first;

        while (current != null) {
            if (current.id == id) {
                while (true) {
                    System.out.println("What do you want to update? ");
                    System.out.println("[1] Id");
                    System.out.println("[2] Name");
                    System.out.println("[3] GPA");
                    System.out.println("[4] Back");

                    System.out.print("> ");
                    int choice = sc.nextInt();
                    sc.nextLine();

                    if (choice == 1) {
                        System.out.print("New ID: ");
                        int newId = sc.nextInt();
                        Node checker = first;
                        while (checker != null) {
                            if (checker.id == newId) {
                                System.out.println("Error: Student ID already exists.");
                                return;
                            }
                            checker = checker.next;
                        }
                        current.id = newId;
                    } else if (choice == 2) {
                        System.out.print("New Name: ");
                        current.name = sc.nextLine();
                    } else if (choice == 3) {
                        System.out.print("New GPA: ");
                        current.gpa = sc.nextFloat();
                    } else if (choice == 4) {
                        return;
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
            }
            current = current.next;
        }
        throw new Exception("ID not found");
    }

    public void SortByAsc() {
        if (first == null) {
            return;
        }
        for (Node i = first; i != null; i = i.next) {
            for (Node j = i.next; j != null; j = j.next) {
                if (i.gpa > j.gpa) {
                    float tempGpa = i.gpa;
                    i.gpa = j.gpa;
                    j.gpa = tempGpa;
                    int tempId = i.id;
                    i.id = j.id;
                    j.id = tempId;
                    String tempName = i.name;
                    i.name = j.name;
                    j.name = tempName;
                }
            }
        }
        System.out.println("Sorted Ascending.");
    }

    public int countAll() {
        Node current = first;
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}

public class AobaJosaiRecordManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DoublyLinkedListSeijou seijou = new DoublyLinkedListSeijou();

        try {
            seijou.InsertLast(1, "Oikawa", 1.25f);
            seijou.InsertLast(2, "Iwa-chan", 1.00f);
            seijou.InsertLast(3, "Osamu", 2.00f);
            seijou.InsertLast(4, "Tsumu", 1.75f);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            System.out.println("\nWELCOME TO AOBA JOHSAI!");
            System.out.println("[1] Add Last");
            System.out.println("[2] Add First");
            System.out.println("[3] First to Last");
            System.out.println("[4] Last to First");
            System.out.println("[5] Search");
            System.out.println("[6] Delete");
            System.out.println("[7] Update");
            System.out.println("[8] Sort Ascending");
            System.out.println("[9] Count");
            System.out.println("[10] Insert After");
            System.out.println("[0] Back");
            System.out.print("> ");
            int choice = sc.nextInt();

            try {
                if (choice == 1) {
                    System.out.print("Id: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Gpa: ");
                    float gpa = sc.nextFloat();
                    seijou.InsertLast(id, name, gpa);
                } else if (choice == 2) {
                    System.out.print("Id: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Gpa: ");
                    float gpa = sc.nextFloat();
                    seijou.InsertFirst(id, name, gpa);
                } else if (choice == 3) {
                    seijou.FirstLast();
                } else if (choice == 4) {
                    seijou.LastToFirst();
                } else if (choice == 5) {
                    System.out.print("Search ID: ");
                    int id = sc.nextInt();
                    seijou.Search(id);
                } else if (choice == 6) {
                    System.out.print("Delete ID: ");
                    int id = sc.nextInt();
                    Node deleted = seijou.DeleteUglyStudent(id);
                    if (deleted != null) {
                        System.out.print("Deleted: ");
                        deleted.DisplayStudents();
                        System.out.println();
                    } else {
                        System.out.println("ID not found or already deleted.");
                    }
                } else if (choice == 7) {
                    System.out.print("Update ID: ");
                    int id = sc.nextInt();
                    seijou.update(id, sc);
                } else if (choice == 8) {
                    seijou.SortByAsc();
                } else if (choice == 9) {
                    System.out.println("Total Records: " + seijou.countAll());
                } else if (choice == 10) {
                    System.out.print("Insert after which ID? ");
                    int key = sc.nextInt();
                    System.out.print("New ID: ");
                    int newId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("New GPA: ");
                    float newGpa = sc.nextFloat();

                    boolean success = seijou.insertAfter(key, newId, newName, newGpa);
                    if (success) {
                        System.out.println("Inserted successfully after " + key);
                    } else {
                        System.out.println("Key not found.");
                    }
                } else if (choice == 0) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
