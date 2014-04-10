package constants;

/**
 * Exception messages for setup client to avoid 'magic words'
 */

public class ExceptionMessages {
   public static final String EMPTY_TITLE = "Title is empty. Please enter a title with at least one character.";
   public static final String DUPLICATE_QUIZ = "A quiz with the same name already exists. Please try again with another name.";
   public static final String NO_QUIZ_EXISTS = "Quiz does not exist. Please try again.";
   public static final String EMPTY_QUESTION = "Question entered is empty. Please try again.";
   public static final String DUPLICATE_QUESTION = "You have already entered that question. Please enter a different one.";
   public static final String NO_QUESTION_EXISTS = "Question doesn't exist. There must be a question to have an answer!";
   public static final String DUPLICATE_ANSWER = "You have already entered that answer. Please enter a different one.";
   public static final String EMPTY_ANSWER = "Answer entered is empty. Please enter an answer with at least one character.";
   public static final String NO_QUIZ_WITH_ID_EXISTS = "A quiz with that ID does not exist. Please enter a contains ID.";
   public static final String NO_QUIZ_TO_SAVE = "There is no quiz to save to server.";
   public static final String NO_QUESTIONS_CANNOT_SAVE =  "Cannot save a quiz without any questions. Please enter at lease one question.";
   public static final String INVALID_USER_INPUT = "That's not a valid input please enter again!";
}
