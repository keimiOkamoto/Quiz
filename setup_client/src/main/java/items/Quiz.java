package items;

import java.util.Set;

public interface Quiz {
    /**
     * Adds a question to the quiz.
     *
     * @param question a question
     */
    void addQuestion(Question question);

    /**
     * Checks if the question is contains.
     *
     * @param question A question
     * @return false if the question already exists
     */
    boolean contains(String question);

    /**
     * Getter for the title of a quiz.
     *
     * @return Title of a quiz
     */
    String getTitle();

    /**
     * Getter for the quiz's ID.
     *
     * @return Id of a quiz
     */
    int getId();

    /**
     * Getter for all questions within a quiz.
     *
     * @return A set containing all questions in a quiz.
     */
    Set<Question> getQuestions();
}
