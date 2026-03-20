import java.util.Scanner;

public class Amon {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your password: ");
        int pass = sc.nextInt();
        // entering password
        if (pass == 12345) {
            System.out.println("Welcome to MyBank!");
            // deposit amount part
            System.out.print("\nEnter the amount you want to deposit: ");
            double dep = sc.nextDouble();
            System.out.println("Checking your balance……");
            int inbal = 500; // Initial balance
            double fbal = dep + inbal; // para lang naay mawatag variable
            System.out.println("Balance is: " + fbal);


            // withdraw
            System.out.print("\nEnter the amount you want to withdraw: ");
            double with = sc.nextDouble();
            if (with < fbal) {
                System.out.println("Checking your balance……");
                double ffbal = fbal - with;
                System.out.println("Balance is: " + ffbal);

                // continue or nah?
                System.out.println("\nDo you want to continue? [1] yes [2] no");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                // conditional part
                if (choice == 1) {
                    System.out.print("\nEnter the amount you want to deposit: ");
                    int con = sc.nextInt();
                    System.out.println("Checking your balance……");
                    System.out.println("Balance is: " + (ffbal + con) );
                    System.out.print("The program will now exit....Bye!");
                    System.exit(0);
                } else if (choice == 2) {
                    System.out.println("\nBye!");
                    System.exit(0);
                } else {
                    System.out.println("\nInvalid option... Please choose between option 1 and option 2");
                    main(args);
                }
            }

            // If amount is greater than the balance
            else if (with > fbal) {
                System.out.print("\nCannot withdraw! Insufficient balance. Please enter another amount");
                System.out.print("\nEnter the amount you want to withdraw: ");
                double w = sc.nextDouble();
                System.out.println("Checking your balance……");
                double fnbal = fbal - w;
                System.out.println("Balance is: " + fnbal);

                // continue or nah?
                System.out.println("\nDo you want to continue? [1] yes [2] no");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                // conditional part
                if (choice == 1) {
                    System.out.print("\nEnter the amount you want to deposit: ");
                    int con = sc.nextInt();
                    System.out.println("Checking your balance……");
                    System.out.println("Balance is: " + (fnbal + con) );
                    System.out.print("The program will now exit....Bye!");
                    System.exit(0);
                } else if (choice == 2) {
                    System.out.println("Bye!");
                    System.exit(0);
                } else {
                    System.out.println("\nInvalid option... Please choose between option 1 and option 2");
                    main(args);
                }
            }
        }
         else {
            System.out.println("Incorrect password! Bye!");
            System.exit(0);
        }


        }
    }