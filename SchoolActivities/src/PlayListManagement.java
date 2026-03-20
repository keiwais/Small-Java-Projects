import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Link {
    public int id;
    public String artist;
    public String title;
    public float duration;
    public Link next;
    public Link previous;

    Link(int id, String title, String artist, float duration) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.duration = duration;
    }

    public void displaySongs() {
        System.out.println("[ " + id + " ] " + title + " by " + artist + ": " + duration);
    }
}

class doublylinkedlist {
    private Link first;
    private Link last;

    public doublylinkedlist() {
        first = null;
        last = null;
    }

    public boolean empty() {
        return first == null;
    }

    public void add(int id, String title, String artist, float duration) throws Exception {
        Link current = first;
        while (current != null) {
            if (current.id == id) {
                throw new Exception("Song number already exists.");
            }
            current = current.next;
        }
        Link newlink = new Link(id, title, artist, duration);
        if (empty()) {
            first = newlink;
            last = newlink;
        } else {
            last.next = newlink;
            newlink.previous = last;
            last = newlink;
        }
        System.out.println("Song successfully added to the playlist!");
    }

    public Link deleteSong(int id) throws Exception {
        Link current = first;
        while (current != null && current.id != id) {
            current = current.next;
        }
        if (current == null) {
            throw new Exception("Invalid! No id found!");
        }
        if (current == first) {
            first = current.next;
            if (first != null) first.previous = null;
        } else {
            current.previous.next = current.next;
        }
        if (current == last) {
            last = current.previous;
        } else if (current.next != null) {
            current.next.previous = current.previous;
        }
        return current;
    }

    public void update(int id, Scanner sc) throws Exception {
        Link current = first;
        while (current != null && current.id != id) {
            current = current.next;
        }
        if (current == null) {
            throw new Exception("Invalid! No id found!");
        }
        sc.nextLine();
        System.out.println("Id found!");
        System.out.print("Song: ");
        current.title = sc.nextLine();
        System.out.print("Artist: ");
        current.artist = sc.nextLine();
        System.out.print("Duration: ");
        current.duration = sc.nextFloat();
    }

    public void search(String choice) throws Exception {
        Link current = first;
        boolean found = false;
        while (current != null) {
            if (current.title.equalsIgnoreCase(choice) || current.artist.equalsIgnoreCase(choice)) {
                current.displaySongs();
                found = true;
            }
            current = current.next;
        }
        if (!found) throw new Exception("No results found.");
    }

    public void reverse() {
        Link current = last;
        System.out.println("\n-- Playlist in Reverse Order --");
        if (current == null) {
            System.out.println("[playlist is empty]");
            return;
        }
        while (current != null) {
            current.displaySongs();
            current = current.previous;
        }
    }

    public void asc() {
        ArrayList<Link> temp = new ArrayList<>();
        for (Link cur = first; cur != null; cur = cur.next) temp.add(cur);
        Collections.sort(temp, Comparator.comparingDouble(l -> l.duration));
        System.out.println("\n-- Sorted Ascending by Duration --");
        if (temp.isEmpty()) {
            System.out.println("[playlist is empty]");
            return;
        }
        for (Link l : temp) l.displaySongs();
    }

    public void desc() {
        ArrayList<Link> temp = new ArrayList<>();
        for (Link cur = first; cur != null; cur = cur.next) temp.add(cur);
        Collections.sort(temp, (a, b) -> Float.compare(b.duration, a.duration));
        System.out.println("\n-- Sorted Descending by Duration --");
        if (temp.isEmpty()) {
            System.out.println("[playlist is empty]");
            return;
        }
        for (Link l : temp) l.displaySongs();
    }

    public void display() {
        Link current = first;
        System.out.println("\n-- Playlist (Original Order) --");
        if (current == null) {
            System.out.println("[playlist is empty]");
            return;
        }
        while (current != null) {
            current.displaySongs();
            current = current.next;
        }
    }
}

public class PlayListManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        doublylinkedlist doubly = new doublylinkedlist();

        try {
            doubly.add(1, "Thunder", "Boys like Girls", 3.45f);
            doubly.add(2, "Candy Pop", "TWICE", 3.20f);
            doubly.add(3, "Cheer up", "TWICE", 2.95f);
            doubly.add(4, "Sienna", "The Marias", 3.60f);
            doubly.add(5, "Sienna", "Adrian Florida", 4.10f);
        } catch (Exception e) {
            System.out.println("Error: " + (e.getMessage() != null ? e.getMessage() : e));
        }

        while (true) {
            try {
                System.out.println("\n======MUSIC PLAYLIST======");
                System.out.println("1. ADD SONG");
                System.out.println("2. DELETE SONG");
                System.out.println("3. UPDATE SONG");
                System.out.println("4. SEARCH SONG");
                System.out.println("5. REVERSE PLAYLIST");
                System.out.println("6. SORT PLAYLIST BY DURATION");
                System.out.println("7. DISPLAY");
                System.out.println("0. EXIT");
                System.out.print("> ");
                int choice = sc.nextInt();

                if (choice == 1) {
                    System.out.print("Enter Id: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Artist: ");
                    String artist = sc.nextLine();
                    System.out.print("Enter Duration: ");
                    float duration = sc.nextFloat();
                    doubly.add(id, title, artist, duration);

                } else if (choice == 2) {
                    System.out.print("Song id to delete: ");
                    int id = sc.nextInt();
                    doubly.deleteSong(id);
                    System.out.println("Deleted.");

                } else if (choice == 3) {
                    System.out.print("Update id: ");
                    int id = sc.nextInt();
                    doubly.update(id, sc);
                    System.out.println("Updated.");

                } else if (choice == 4) {
                    sc.nextLine();
                    System.out.print("Search artist or song: ");
                    String query = sc.nextLine();
                    doubly.search(query);

                } else if (choice == 5) {
                    doubly.reverse();

                } else if (choice == 6) {
                    System.out.println("1. Ascending");
                    System.out.println("2. Descending");
                    System.out.print("> ");
                    int ok = sc.nextInt();
                    if (ok == 1) doubly.asc();
                    else if (ok == 2) doubly.desc();

                } else if (choice == 7) {
                    doubly.display();

                } else if (choice == 0) {
                    System.out.println("Exiting program...");
                    break;

                } else {
                    System.out.println("Invalid input.");
                }

            } catch (Exception e) {
                // 🔥 Always prints either the message or the full exception
                System.out.println("Error: " + (e.getMessage() != null ? e.getMessage() : e));
                sc.nextLine(); // clear input buffer
            }
        }
    }
}
