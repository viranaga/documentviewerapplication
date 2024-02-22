/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package documentviewerapplication;
import java.util.Scanner;
/**
 *
 * @author Viranga
 */
class Node {
    String content;
    Node prev;
    Node next;

    public Node(String content) {
        this.content = content;
        this.prev = null;
        this.next = null;
    }
}


class DoublyLinkedList {
    Node head;
    Node tail;
    Node current;

    public void addPage(String content) {
        Node newNode = new Node(content);

        if (head == null) {
            head = newNode;
            tail = newNode;
            current = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }
    public int getTotalPages() {
    Node currentNode = head;
    int totalPages = 0;

    while (currentNode != null) {
        totalPages++;
        currentNode = currentNode.next;
    }

    return totalPages;
}


    public void addPageAtIndex(String content, int index) {
        Node newNode = new Node(content);

        if (index < 1) {
            System.out.println("Invalid index. Please enter a positive index starting from 1.");
            return;
        }

        if (index == 1) {
            // Insert at the beginning
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;

            if (tail == null) {
                // If the list was empty, update tail as well
                tail = newNode;
            }
        } else {
            Node current = head;
            int currentIndex = 1;

            while (current != null && currentIndex < index - 1) {
                current = current.next;
                currentIndex++;
            }

            if (current == null) {
                System.out.println("Index out of bounds. Cannot add page at the specified index.");
                return;
            }

            newNode.next = current.next;
            newNode.prev = current;
            current.next = newNode;

            if (newNode.next != null) {
                // If newNode is not the last node, update the next node's prev pointer
                newNode.next.prev = newNode;
            } else {
                // If newNode is the last node, update tail
                tail = newNode;
            }
        }
 
   }
        public void removePage(int pageNumber) {
        Node nodeToRemove = findNodeByPageNumber(pageNumber);

        if (nodeToRemove != null) {
            Node prevNode = nodeToRemove.prev;
            Node nextNode = nodeToRemove.next;

            if (prevNode != null) {
                prevNode.next = nextNode;
            } else {
                head = nextNode;
            }

            if (nextNode != null) {
                nextNode.prev = prevNode;
            } else {
                tail = prevNode;
            }

            if (current == nodeToRemove) {
                current = nextNode; // Move to the next page after removal
            }
        }
    }
    public void editPageContent(int pageNumber, String newContent) {
    Node targetPage = findNodeByPageNumber(pageNumber);

    if (targetPage != null) {
        targetPage.content = newContent;
    } else {
        System.out.println("Invalid page number. Page not found.");
    }
}
    private Node findNodeByPageNumber(int pageNumber) {
        Node currentNode = head;
        int currentPageNumber = 1;

        while (currentNode != null && currentPageNumber < pageNumber) {
            currentNode = currentNode.next;
            currentPageNumber++;
        }

        return currentNode;
    }

    public void goToNextPage() {
        if (current != null && current.next != null) {
            current = current.next;
        } else {
            System.out.println("No next page available.");
        }
    }

    public void goToPreviousPage() {
        if (current != null && current.prev != null) {
            current = current.prev;
        } else {
            System.out.println("No previous page available.");
        }
    }




    public void goToFirstPage() {
        current = head;
    }

    public void goToLastPage() {
        current = tail;
    }

    public String getCurrentPageContent() {
        return (current != null) ? current.content : "No page available.";
    }

    public void displayAllPages() {
        Node currentNode = head;

        while (currentNode != null) {
            System.out.println("Page Content: " + currentNode.content);
            currentNode = currentNode.next;
        }


        // Reset the current node to the head
        current = head;
    }
    
  
    
}


public class Documentviewerapplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       DoublyLinkedList document = new DoublyLinkedList();
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("\nDocument Viewer Menu:");
        System.out.println("1. Add Page");
        System.out.println("2. Add Page at Index");
        System.out.println("3. Remove Page");
        System.out.println("4. Go to Next Page");
        System.out.println("5. Go to Previous Page");
        System.out.println("6. Go to First Page");
        System.out.println("7. Go to Last Page");
        System.out.println("8. Display All Pages");
        System.out.println("9. Edit Current Page Content");
        System.out.println("10. Exit");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                scanner.nextLine(); // Consume the newline character
                System.out.print("Enter page content: ");
                String content = scanner.nextLine();
                document.addPage(content);
                break;

            case 2:
                scanner.nextLine(); // Consume the newline character
                System.out.print("Enter page content: ");
                String contentAtIndex = scanner.nextLine();
                System.out.print("Enter index to add page: ");
                int indexToAdd = scanner.nextInt();
                document.addPageAtIndex(contentAtIndex, indexToAdd);
                break;

            case 3:
                System.out.print("Enter page number to remove: ");
                int pageNumberToRemove = scanner.nextInt();
                document.removePage(pageNumberToRemove);
                break;

            case 4:
                document.goToNextPage();
                break;

            case 5:
                document.goToPreviousPage();
                break;

            case 6:
                document.goToFirstPage();
                break;

            case 7:
                document.goToLastPage();
                break;

            case 8:
                document.displayAllPages();
                break;

             case 9:
    System.out.print("Enter page number to edit: ");
    int pageToEdit = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character
    System.out.print("Enter new content for page " + pageToEdit + ": ");
    String newContent = scanner.nextLine();
    document.editPageContent(pageToEdit, newContent);
    break;


            case 10:
                System.out.println("Exiting Document Viewer. Goodbye!");
                System.exit(0);

            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }

        System.out.println("Current Page: " + document.getCurrentPageContent());
    }
    }
    
}
