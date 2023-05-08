/**
 * Article.java
 * @author Sakeena Younus
 * @description Assigns priority number to articles based on given topic and performs insert sort on 2D array of articles & priority numbers 
 * @version 1.0, 2023-04-11
 */

import java.util.Scanner;

public class Article {

  // ------------Attributes-----------
  private String topic;
  private int priority;
  private String article;

  // -------------Methods-------------

  /* Constuctor */
  public Article() {
  }

  /*
   * Takes in topic & current article name managed by main method
   * Returns priority number based on number of similar words between topic &
   * article
   * Acts as a heuristic method for PriorityQueue.java
   */
  public static int articlePriority(String topic, String article) {
    String[] topicWords = topic.split(" ");
    String[] articleWords = article.split(" ");
    int priority = 0;
    int i = 0;
    while (i < topicWords.length) {
      String word = topicWords[i];
      int j = 0;
      while (j < articleWords.length) {
        String articleWord = articleWords[j];
        if (word.equalsIgnoreCase(articleWord)) { // Ref: Didn't know method to ignore case in Java -
                                                  // https://www.w3schools.com/java/ref_string_equalsignorecase.asp
          priority++;
          break;
        }
        j++;
      }
      i++;
    }
    return priority;
  }

  /*
   * Performs insert sort on 'result' (2D array containing all articles and corresponding priority number)
   * Takes in 'result,' 'articles' (String array containing all article names, and 'sortedArray' (passed as empty 2D array to contain the sorted articles and priority numbers)
   * Calls add() which find the appropriate spot for each entry in result before it is added into sortedArray
   * Returns sortedArray with full sorted articles and corresponding priority numbers
   */

    // REF: insertSort() and add() are from insertSort code given by Prof. Jamshidi and altered to sort a 2D String array instead of an int array
  public static String[][] insertSort(String[][] result, String[] articles, String[][] sortedArray) {
    for (int i = 0; i < result.length; i++) {
      sortedArray = add(result[i][0], result[i][1], sortedArray, i);
    }
    return sortedArray;
  }

  /*
   * Finds the appropriate spot for each entry in result before it is added into sortedArray
   * Called by insertSort() to perform the sorting aspect of insertSort when adding each entry
   * Takes in 'article' (current article entry), 'entry' (priority number for current article), 'sortedArray' (the current sortedArray), 'i' (the current index of for loop in insertSort managing calling add() for as many articles there are)
   */

  // REF: insertSort() and add() are from insertSort code given by Prof. Jamshidi and altered to sort a 2D String array instead of an int array
  public static String[][] add(String article, String entry, String[][] sortedArray, int i) {
    int j = i;

    while (j > 0 && Integer.parseInt(sortedArray[j - 1][1]) < Integer.parseInt(entry)) {
      sortedArray[j][0] = sortedArray[j - 1][0];
      sortedArray[j][1] = sortedArray[j - 1][1];
      j--;
    }
    sortedArray[j][0] = article;
    sortedArray[j][1] = entry;

    return sortedArray;
  }

  /* Main method for testing */
  public static void main(String[] args) {

    //Gets optional inputs from user to set topic & articles:
    // ----------------------------------------------------------------------------------------------------------------------
    String topic = "";
    String[] articles = null;
    Scanner input = new Scanner(System.in);
    System.out.println("Welcome! If you provide me with a topic and some article titles, I can return a list in order of most relevant to your topic.");
    System.out.println("Enter Y if you want to provide me with the information, or N if you want to use test out the program with our sample topic and articles");

    String userAnswer = input.nextLine();
    while (userAnswer.toUpperCase() != "Y" || userAnswer.toUpperCase() != "N") {

      if (userAnswer.toUpperCase().equals("Y")) { //REF: Was trying to do !=, resource reminded me of .equals() for strings - https://www.w3schools.com/java/ref_string_equals.asp
        System.out.println("Please enter your topic:");
        topic = input.nextLine();

        System.out.println("Enter the number of articles you have (Make sure you enter correctly, if you want to adjust, you'll have to start over)");
        int numArticles = Integer.parseInt(input.nextLine());

        articles = new String[numArticles];
        for (int i = 0; i < numArticles; i++) {

          System.out.println("Enter article " + (i + 1) + ": ");
          String currArticle = input.nextLine();
          articles[i] = currArticle;
        }
        break;
      } else if (userAnswer.toUpperCase().equals("N")) {
        topic = "The ethics of artificial intelligence and machine learning";

        articles = new String[] {
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
            "The History of Baseball: From its Origins to the Modern Era"
        };

        break;
      } else {
        System.out.println("I'm sorry, I don't understand that. Please Enter Y or N");
        userAnswer = input.nextLine();

      }
    }
    input.close(); // REF: Forgot I had to close scanner, used reference to fix warning: "Resource leak: 'input' is never closed" 
                   //https://stackoverflow.com/questions/12519335/resource-leak-in-is-never-closed

    // ----------------------------------------------------------------------------------------------------------------------

    String[][] result = new String[articles.length][2];

    for (int k = 0; k < articles.length; k++) {

      int score = articlePriority(topic, articles[k]);
      result[k][0] = articles[k];
      result[k][1] = Integer.toString(score);

      System.out.println();
    }

    String[][] sortedArray = new String[result.length][result.length];

    insertSort(result, articles, sortedArray);

    System.out.println("Based on your topic, '" + topic + "', here is the order you should read your articles in!");
    System.out.println("For efficiency, I took out any articles that had no similarities with your topic");
    System.out.println("Hope this helps & good luck with your research! \n");

    System.out.println("----------------------------------------");

    for (int i = 0; i < sortedArray.length; i++) {
      if (Integer.parseInt(sortedArray[i][1]) != 0)

        System.out.print(sortedArray[i][0] + " - " + sortedArray[i][1] + "\n");

    }

    System.out.println("----------------------------------------");

  }

}
