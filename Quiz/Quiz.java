// This Java program implements a simple quiz game 
// with multiple-choice questions. The program features 
// storing quiz questions, a timer for each question, 
// displaying questions one at a time, answer submission, 
// score calculation, and displaying the final results.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

public class Quiz {
    private List<Question> questions;
    private int score;
    private List<Boolean> results;

    public Quiz() {
        this.questions = new ArrayList<>();
        this.score = 0;
        this.results = new ArrayList<>();
        initializeQuestions();
    }

    private void initializeQuestions() {
        questions.add(new Question("What is the capital of France?", new String[]{"1. Paris", "2. London", "3. Berlin", "4. Madrid"}, 0));
        questions.add(new Question("What is 2 + 2?", new String[]{"1. 3", "2. 4", "3. 5", "4. 6"}, 1));
        questions.add(new Question("What is the largest planet?", new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"}, 2));
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println("\n" + question.getQuestionText());
            String[] options = question.getOptions();
            for (String option : options) {
                System.out.println(option);
            }

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up!");
                    results.add(false);
                    timer.cancel();
                }
            };

            timer.schedule(task, 10000); // 10 seconds for each question

            System.out.print("Enter your answer (1-4): ");
            int userAnswer = scanner.nextInt() - 1;
            timer.cancel();

            if (userAnswer == question.getCorrectAnswerIndex()) {
                score++;
                results.add(true);
            } else {
                results.add(false);
            }
        }

        scanner.close();
        displayResults();
    }

    private void displayResults() {
        System.out.println("\nQuiz Completed!");
        System.out.println("Your final score is: " + score + " out of " + questions.size());

        for (int i = 0; i < questions.size(); i++) {
            System.out.println("\nQuestion: " + questions.get(i).getQuestionText());
            System.out.println("Your answer was: " + (results.get(i) ? "Correct" : "Incorrect"));
            System.out.println("Correct answer: " + questions.get(i).getOptions()[questions.get(i).getCorrectAnswerIndex()]);
        }
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.startQuiz();
    }
}
