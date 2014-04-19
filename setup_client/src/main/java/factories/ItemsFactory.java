package factories;

import models.Answer;
import models.Question;
import models.Quiz;

/**
 * Factory for models to do with a quiz.
 */

public interface ItemsFactory {
    /**
     * Generated a quiz.
     *
     * @param title A title of a quiz.
     * @return Quiz object with the title given.
     */
    Quiz generateQuiz(String title);

    /**
     * Generates a question.
     *
     * @param question A question for the quiz.
     * @return Question object.
     */
    Question generateQuestion(String question);

    /**
     * Generates an answer.
     *
     * @param answer An answer to a question
     * @param answerType A boolean value to signify if the answer is correct or incorrect.
     * @return Answer object.
     */
    Answer generateAnswer(String answer, boolean answerType);
}
