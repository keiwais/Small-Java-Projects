/* hello self, i recommend this study method
if not busy you can do this and try coding with copy
 why with copy? to nake notes and understand why every
 block is important. coding is legit boring, but with
 music it is not. please sunda ako i recommend stupid self!*/

import java.util.Scanner;

class Link { // node for me is murag diri i gather unsay need na data
    public int id;
    public String name;
    public float gpa;
    public Link next; // pointer to next node
    public Link previous; // pointer to previous node

    Link (int StudentId, String StudentName, float StudentGpa) { /* diri ipasa ang data na galing sa main i think */
        this.id = StudentId;
        this.name = StudentName;
        this.gpa = StudentGpa;
    }

    // diri idisplay murag tanan ang sulod, format rather kay i loop manes methods
    public void DisplayLink () {
        System.out.println("[ " + id + " , " + name + " , " + gpa + " ]" + " ");
    }

}

class DoublyLinkedList { // purpose ani kay mag create og linkedlist
    private Link first; // Link ang name sa LinkedList mao man ang class na naa ang data types na i store
    // ^ also naa na daan ang first, next, last, prev ha? ana ni bro code remember?
    private Link last; // mao pud ni ang pointer sa last node

    public DoublyLinkedList () { // i think purpose niya mag hold unya initialize sa taas
        first = null;
        last = null;
    }

    public boolean Empty () {
        return first == null; // if walay sulod mao mugawas, maoy purpose ani
    }

    // insert last
    public void InsertLast(int id, String name, float gpa) { // param from main
        Link newLink = new Link(id, name, gpa); // create newlink and add the param
        if (Empty()) { // if list is empty
            first = newLink; // matic mao ni if wa sulod
        } else { // diri if naa
            last.next = newLink; // the new link is now last
            newLink.previous = last; // previous sa newLink as is (9) and (10) new link, so now prev na ang last
        }
        last = newLink; // then ang last kay new link
    }

    // display forward
    public void DisplayForward () {
        System.out.println("List (first --> last): ");
        Link current = first; // sugod sa una, dapat current nimo kay first jud
        while (current != null) { // != null kay mo undang if null na siya
            current.DisplayLink(); // method sa taas, displays the current of the list
            current = current.next; // pangpa next
        }
        System.out.println(" ");
    }

    // display backward
    public void DisplayBackward () {
        System.out.println("List (last --> first): ");
        Link current = last; // karon magsugod na siya sa last
        while (current!= null) { // stops if null na siya
            current.DisplayLink(); // remember current nato run equals last so last idisplay una
            current = current.previous; // previous kay pabalik man
        }
        System.out.println(" ");
    }

    // search record by id
    public void SearchRecordId (int StudentId) throws Exception { // put parameter kay ipasa manas scanner gud
        Link current = first; //ofc sa una jud mag sugod
        int index = 1; //tracker deay ni siya

        while (current != null) { //only stops if null na
            if (current.id == StudentId) { //id gi gamit kay mao juy name sa initialization, StudentId for param only.
                System.out.println(StudentId + " found at index " + index + "."); //if nakita
                current.DisplayLink(); // para ma display
                return; // para mo out sa loop
            }
            current = current.next; // para ma next if wala nakita pa
            index++; //increment para makabalo ika pila siya na index sa list
        }
        throw new Exception("Student ID not found.");
    }

    // delete by key
    public Link DeleteByUSingKey (int StudentId) { // we use id as key
        Link current = first; // sugod jud sa una
        while (current != null && current.id != StudentId) { // stop if same og id
            current = current.next; // pangpa next
        }
        if (current == null) {// if wala jud
            return null;
        }
        if (current == first) { // if ang gi delete kay first
            first = current.next; // sunod na ang first
        } else {
            current.previous.next = current.next; // skip current
        }
        if (current == last) { // if ang gi delete kay last
            last = current.previous; // balik ta sa prev
        } else {
            current.next.previous = current.previous; // connect prev to next
        }
        return current; // return deleted
    }

    // update record - ra rag search naa lang sc og inner na kuan condition
    public void UpdateRecordId (int StudentId, Scanner sc) throws Exception {
        Link current = first; // start sa una
        while (current != null) { // loop hangtod null
            if (current.id == StudentId) { // if nakit-an
                System.out.println("Current record:");
                current.DisplayLink();

                System.out.println("What do you want to update? ");
                System.out.println("[1] ID");
                System.out.println("[2] Name");
                System.out.println("[3] GPA");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter new ID: ");
                        current.id = sc.nextInt();
                    }
                    case 2 -> {
                        System.out.print("Enter new Name: ");
                        current.name = sc.nextLine();
                    }
                    case 3 -> {
                        System.out.print("Enter new GPA: ");
                        current.gpa = sc.nextFloat();
                    }
                    default -> System.out.println("Back to menu...");
                }
                return; // importante kay mo exit dayon if updated
            }
            current = current.next; // move forward if not match
        }
        throw new Exception("Student ID not found.");
    }

    // sort by GPA ascending
    public void SortByGPAAscending() {
        if (first == null) {
            return; // return to null
        }
        // bubble sort logic
        for (Link i = first; i != null; i = i.next) { // first = 1 (1.25)
            for (Link j = i.next; j != null; j = j.next) { // j = 2 (1.00) swap sila
                if (i.gpa > j.gpa) { // condition greater than
                    float tempGpa = i.gpa; // 1.25
                    i.gpa = j.gpa; // 1.00
                    j.gpa = tempGpa; // 1.25
                    int tempId = i.id; // swap id
                    i.id = j.id;
                    j.id = tempId;
                    String tempName = i.name; // swap name
                    i.name = j.name;
                    j.name = tempName;
                }
            }
        }
        System.out.println("Sorted by GPA (Ascending).");
    }

    // count records
    public int CountRecords() {
        int count = 0; // kay wa pa gi count
        Link current = first; // una jud sa first
        while (current != null) { // di mo stop hantod ma null
            count++; // increment every loop na di null
            current = current.next; // pangpa sunod then loop ule
        }
        return count; // if null na mo return ang count
    }
}


public class SchoolRecordManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DoublyLinkedList list = new DoublyLinkedList(); // himo list

        // insrt diri data
        list.InsertLast(1, "Alice", 2.5f);
        list.InsertLast(2, "Bob", 3.1f);
        list.InsertLast(3, "Charlie", 1.8f);
        list.InsertLast(4, "Diana", 3.7f);

        while (true) {
            System.out.println("\n--- School Record Menu ---");
            System.out.println("[1] Add Student");
            System.out.println("[2] Display Forward");
            System.out.println("[3] Display Backward");
            System.out.println("[4] Search by ID");
            System.out.println("[5] Delete by ID");
            System.out.println("[7] Update by ID");
            System.out.println("[8] Sort by GPA Ascending");
            System.out.println("[9] Count Records");
            System.out.println("[0] Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter GPA: ");
                        float gpa = sc.nextFloat();
                        list.InsertLast(id, name, gpa);
                    }
                    case 2 -> list.DisplayForward();
                    case 3 -> list.DisplayBackward();
                    case 4 -> {
                        System.out.print("Enter ID to search: ");
                        int id = sc.nextInt();
                        list.SearchRecordId(id);
                    }
                    case 5 -> {
                        System.out.print("Enter ID to delete: ");
                        int id = sc.nextInt();
                        Link deleted = list.DeleteByUSingKey(id);
                        if (deleted != null) {
                            System.out.println("Deleted:");
                            deleted.DisplayLink();
                        } else {
                            System.out.println("ID not found or already deleted.");
                        }
                    }
                    case 7 -> {
                        System.out.print("Enter ID to update: ");
                        int id = sc.nextInt();
                        list.UpdateRecordId(id, sc);
                    }
                    case 8 -> list.SortByGPAAscending();
                    case 9 -> System.out.println("Records left: " + list.CountRecords());
                    case 0 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage()); // get message ra sa mga throws
            }
        }
    }
}
