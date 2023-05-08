/**
 * PriorityQueue.java
 * @author Sakeena Younus
 * @description Priority Queue that prioriizes elements with insert sort
 * @version 1.0 2023-04-20
 */

// REF: PriorityQueue() uses code either written in class or posted by Prof. Jamshidi from sortedLinkedList(), singularlyLinkedList.java, singularlyLinkedListWithTail.java, and Queue.java

// REF: Decided to code my own priority queue after a discussion with Ricardo Salazar about priority queues in Java vs. Python

public class PriorityQueue<T>{

  //---------------------- Attributes ----------------------
  protected Node<T> startNode;
  protected Node<T> endNode;
  protected int size = 0;


  //---------------------- Methods ----------------------

  /* Constructor */
  public PriorityQueue(){
    startNode = null;
    endNode = null;
  }

    /** Add method for insert sort with nodes
     * REF: Utilized sortedLinkedList() code given by Prof. Jamshidi and edited it to work with a queue of 2 entries instead of 1
    */
    public void addSorted(T entry, String article, int priority){
      if (size == 0 || priority > startNode.getPriority()){
        enqueue(article, priority);
      }
      else {

      Node<T> nodeAt = startNode;
      while(nodeAt.getNextNode() != null && nodeAt.getNextNode().getPriority() > priority){
          nodeAt = nodeAt.getNextNode();
      }
      Node<T> newNode = new Node<T>(article, priority);
      newNode.setNextNode(nodeAt.getNextNode());
      nodeAt.setNextNode(newNode);

      size++;
      }
  }

    /* Adds the Node to the front of the Priority Queue */
    public void enqueue(String article, int priority) {
      if (size == 0) {
          startNode = new Node<T>(article, priority); 
          size++;
      }
      else {
          Node<T> newNode = new Node<T>(article, priority);
          newNode.setNextNode(startNode);
          startNode = newNode;
          size++;
      }
  }

  /* Removes the Node at the end of the Priority Queue */
  public String dequeue(){
    if (size >=1){
      Node<T> nodeAt = startNode;

      while(nodeAt.getNextNode() != null){
        nodeAt = nodeAt.getNextNode();
      }
        endNode = nodeAt;
        Node<T>deletedEntry = nodeAt;
        endNode.setNextNode(null);
        endNode = null;
        size--;
        return deletedEntry.getEntry();
    }
    return null;  
}

  /* Returns the number of elements in the Priority Queue */
  public int getSize(){
    return size;
  }

  /* Returns whether the Priority Queue is empty or not as a boolean */
  public boolean isEmpty(){
    return size == 0;
}

  /* Returns the first element in the Priority Queue without deleting it */
  public String peek(){
    return startNode.getEntry();
  }


  /** Prints queue for checking */
  public void printQueue(){
    Node<T> nodeAt = startNode;
    if (nodeAt.getPriority() != 0)
      System.out.println(nodeAt.getEntry() + ": " + nodeAt.getPriority());
    for (int i=1; i<size; i++){
      nodeAt=nodeAt.getNextNode();

      if (nodeAt.getPriority() != 0)
        System.out.println(nodeAt.getEntry() + ": " + nodeAt.getPriority());
    }
  }

  //------------------------ Nested Node Class ------------------------
  public static class Node<T>{
    //------------------Attributes------------------
    protected String value;
    protected int priority;
    protected Node<T> nextNode;

    //------------------Methods------------------
    /* Constructor */
    public Node(String entry){
      value = entry;
      nextNode = null;
      priority = 0;
    }

    /* Constructor */
    public Node(String entry, int priority){
      value = entry;
      nextNode = null;
      this.priority = priority;
    }

    //Constructor overloading!
    //In this case, constructor overloading is used to allow priority to be set to a default value for node, 
    //but so that priority is also able to be given a specific value when performing insert sort
    //REF: https://www.geeksforgeeks.org/constructor-overloading-java/


    /** set the next node */
    public void setNextNode(Node<T> nextNode){
      this.nextNode = nextNode;
    }

    /** get the next node */
    public Node<T> getNextNode(){
      return nextNode;
    }

    /** get the priority */
    public int getPriority(){
      return priority;
    }

    /** get the article entry*/
    public String getEntry(){
      return value;
    }
  } // end of nested class


/* Main Method for Testing */
  public static void main(String[] args) {
    PriorityQueue<Article> queue = new PriorityQueue<Article>();
    String topic = "The ethics of artificial intelligence and machine learning";

    String[] articles = new String[] {
        "How Artificial Intelligence is Revolutionizing Healthcare: A Look at the Latest Developments",
        "Artificial Intelligence vs. Human Intelligence: Which is More Ethical?",
        "Artificial Intelligence in Finance: Applications and Implications",
        "10 Reasons to Visit Hawaii for Your Next Vacation",
        "The Ethics of Artificial Intelligence: Balancing Innovation and Responsibility",
        "How to Start Your Own Small Business: Tips and Strategies",
        "Artificial Intelligence and Education: How Technology is Changing the Classroom",
        "Understanding the Fundamentals of Machine Learning",
        "Why You Should Be Watching More Foreign Films",
        "The Future of Artificial Intelligence: Predictions for the Next Decade",
        "The History of Baseball: From its Origins to the Modern Era" };
  
    //REF: Prof. Jamshidi helped me structure my code to access methods & attributes from Article.java by creating an Article object & reconfiguring some methods
    Article testArticle = new Article();

    for (int i = 0; i < articles.length; i++){
      queue.addSorted(testArticle, articles[i], testArticle.articlePriority(topic, articles[i]));
      System.out.println(" '" + articles[i] + "' has been added to Priority Queue");
  }

  System.out.println();

  System.out.println("Here is your full list! \n");
  System.out.println("--------------------------------------");
  queue.printQueue();
  System.out.println("--------------------------------------");

  System.out.println();

  System.out.println("Hope this is helpful!");
  String top = queue.peek();
  System.out.println("'" + top + "' is the article closest to your topic, you should definitely read it first!");
  System.out.println("Save these results, I will now empty the Priority Queue \n");

  System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");


  System.out.println("Watch as the queue empties!");
  int i = 0;
  System.out.println("--------------------------------------");

  while (!queue.isEmpty()){
    i++;
    System.out.println("Round " + i + "!");
    queue.dequeue();
    System.out.println("--------------------------------------");

    queue.printQueue();
 
    System.out.println("--------------------------------------");

}
System.out.println("The queue is now empty");  
System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

  }

}
