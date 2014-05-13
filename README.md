[![Build Status](https://travis-ci.org/kokamo01/Quiz.svg?branch=master)](https://travis-ci.org/kokamo01/Quiz)


Quiz
=========

A quiz system that allows a user to set a quiz up, stating an arbitrary number of questions and as many possible answers with that question. No duplicate questions and answers associated with that quiz is allowed. 

The setup client may choose to close the quiz using option 2.
They must type the quiz’s ID in order to do so.


Server
=========
The server hold all current quizzes and closed quizzes. 
It is also responsible of holding the highest score and player to a particular quiz.

    -Run StartServer.java to launch the server.
    -Press enter to close the server

Setup
=========

The Setup client can set up as many quizzes as possible and close quizzes 
quoting the ID of the quiz. (Given when the quiz is made)

    - Run SetupStart.java to start the Player Client.
    -’1’ To create a quiz.
    -‘2’ To close a quiz.
    -‘EXIT’ Can be typed at any point to close the application.


Player
=========

A player can play as many quizzes and will be notified of their score.
They may also view past (closed) quizzes together with the highest score and player.

    - Run PlayerStartImpl.java to start the Player Client.
    -‘1’ To play a quiz and view lis of available quizzes. 
    -‘2’ To view past quizzes.
    -‘EXIT’ Can be typed at any point to close the application.
    
    
References
=========
http://docs.oracle.com/javase/7/docs/technotes/guides/lang/hook-design.html
http://www.tutorialspoint.com/java/lang/runtime_addshutdownhook.htm
http://javaevangelist.blogspot.co.uk/2011/05/application-shutdown-hook.html
http://docs.oracle.com/javase/7/docs/api/java/lang/Runtime.html
http://www.vogella.com/tutorials/JavaSerialization/article.html

