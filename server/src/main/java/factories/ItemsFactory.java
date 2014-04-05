package factories;

import models.Answer;
import models.Question;
import models.Quiz;

/**
 * Factory for models associated with a quiz.
 */

public interface ItemsFactory {
    /**
     * Generated a quiz.
     *
     * @param title A title of a quiz
     * @return Quiz object
     */
    Quiz generateQuiz(String title);

    /**
     * Generates a question.
     *
     * @param question A question
     * @return Question object
     */
    Question generateQuestion(String question);

    /**
     * Generates an answer.
     *
     * @param answer An answer to a question
     * @return Answer object
     */
    Answer generateAnswer(String answer, boolean answerType);
}
