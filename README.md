[![Build Status](https://travis-ci.org/kokamo01/Quiz.svg?branch=master)](https://travis-ci.org/kokamo01/Quiz)


Quiz
=========

A quiz system that allows a user to set a quiz up stating an arbitrary number of questions and as many possible answers with that question. No duplicate questions and answers assoisiated with that question is allowed. 

The setup client may choose to close the quiz using option 2.
They must type the quizâs ID in order to do so.


Server
=========
The server hold all current quizzes and closed quizzes. 
It is also responsible of holding the highest score and player to a particular quiz.

    -StartServer to launch the server.
    -Press enter to close the server

Setup
=========

The Setup client can set up as many quizzes as possible and close quizzes 
quoting the ID of the quiz. (Given when the quiz is made)

    - Run SetupStart.java to start the Player Client.
    -’1â To create a quiz.
    -‘â2â’ To close a quiz.
    -âEXITâ Can be typed at any point to close the application.


Player
=========

A player can play as many quizzes and will be notified of their score.
They may also view past (closed) quizzes together with the highest score and player.

    - Run PlayerStartImpl.java to start the Player Client.
    -â1â To play a quiz and view lis of available quizzes. 
    -â2â To view past quizzes.
    -âEXITâ Can be typed at any point to close the application.

