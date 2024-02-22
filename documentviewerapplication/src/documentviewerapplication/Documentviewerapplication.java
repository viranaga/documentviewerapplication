/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package documentviewerapplication;

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
        // TODO code application logic here
    }
    
}
