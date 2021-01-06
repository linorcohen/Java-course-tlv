package Exercises.ex4;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class WordPuzzle {
    public static final char HIDDEN_CHAR = '_';
    public static final int MAX_VOCABULARY_SIZE = 3000;


    public static String[] scanVocabulary(Scanner scanner) { // Q - 1
        String[] vocabularyArray = new String[MAX_VOCABULARY_SIZE];
//        initialize all elements:
        for (int i = 0; i < Array.getLength(vocabularyArray); i++) {
            vocabularyArray[i] = "";
        }
//        get vocabulary length:
        int vocabularyLength = creatingVocabularyArray(scanner, vocabularyArray);
//        create the vocabulary array:
        vocabularyArray = Arrays.copyOf(vocabularyArray, vocabularyLength);
        Arrays.sort(vocabularyArray, Collections.reverseOrder());
//        create new vocabulary array without duplicate elements:
        return removeDuplicateWordsFromArray(vocabularyArray);

    }

    private static String[] removeDuplicateWordsFromArray(String[] vocabularyArray) {
        int indexToPlace = 0;
        int newArrayLength = 1;
//        creating a new vocabulary array that grows when you want to add another element:
        String[] vocabularyStack = {vocabularyArray[indexToPlace]};
//        checking for duplicate words:
        for (int index = 1; index < Array.getLength(vocabularyArray); index++) {
//            if not duplicate:
            if (!vocabularyArray[index].equals(vocabularyArray[index - 1])) {
                newArrayLength++;
                indexToPlace++;
//                adding the word to the new vocabulary array and making it bigger:
                vocabularyStack = Arrays.copyOf(vocabularyStack, newArrayLength);
                vocabularyStack[indexToPlace] = vocabularyArray[index];
            }
        }
        Arrays.sort(vocabularyStack);
        return vocabularyStack;
    }

    private static int creatingVocabularyArray(Scanner scanner, String[] vocabularyArray) {
        String word;
        int indexArray = 0;
        while (scanner.hasNext() && indexArray <= MAX_VOCABULARY_SIZE) {

            word = scanner.next();
            for (int index = 0; index < word.length(); index++) {
//                check if char in word is digit:
                if (Character.isDigit(word.charAt(index)) || word.length() < 2) {
                    break;
                }
//                check if char in word is letter and we checked the entire word successfully:
                if (Character.isLetter(word.charAt(index)) && index == word.length() - 1) {
//                    adding the word to the vocabulary array:
                    vocabularyArray[indexArray] = word.toLowerCase();
                    indexArray++;
                }
            }
        }
        return indexArray;
    }


    public static int countHiddenInPuzzle(char[] puzzle) { // Q - 2
        int countHiddenChar = 0;
        if (Array.getLength(puzzle) != 0) {
            for (char i : puzzle) {
                if (i == HIDDEN_CHAR) {
                    countHiddenChar++;
                }
            }
        }
        return countHiddenChar;
    }

    public static String getRandomWord(String[] vocabulary, Random generator) { // Q - 3
        int indexWord = generator.nextInt(Array.getLength(vocabulary));
        return vocabulary[indexWord];
    }

    public static boolean checkLegal(String word, char[] puzzle) {  // Q - 4
        String legalWord = word;
        int countHiddenChar = 0;
        int countLetterShows = 0;
        for (int index = 0; index < word.length(); index++) {
//            check if puzzle contain hidden letter:
            if (puzzle[index] == HIDDEN_CHAR) {
                countHiddenChar++;
//                creating a legal word for the puzzle to check after:
                legalWord = legalWord.replace(word.charAt(index), HIDDEN_CHAR);
//            check if puzzle contain letter that shows:
            } else {
                countLetterShows++;
            }
        }
//        check if the legal puzzle for the word is equals to the puzzle, and contain the legal chars for puzzle:
        char[] legalWordArray = legalWord.toCharArray();
        if (Arrays.equals(legalWordArray, puzzle) && (countLetterShows >= 1 && countHiddenChar >= 1)) {
            return true;
        }
        return false;
    }

    public static char[] getRandomPuzzleCandidate(String word, double prob, Random generator) { // Q - 5
        String hiddenWord = word;
        for (int index = 0; index < word.length(); index++) {
//            defined a prob for each letter:
            float charProb = generator.nextFloat();
            if (charProb <= prob) {
//            adding the letter to the puzzle:
                hiddenWord = hiddenWord.replace(word.charAt(index), HIDDEN_CHAR);
            }
        }
        return hiddenWord.toCharArray();
    }

    public static char[] getRandomPuzzle(String word, double prob, Random generator) {  // Q - 6
        char[] candidate = getRandomPuzzleCandidate(word, prob, generator);
        int countTry = 0;
//        check if candidate is legal to use
        while (!checkLegal(word, candidate)) {
//            throws exception if its more then 1000 tries to find candidate:
            if (countTry > 1000) {
                throwPuzzleGenerationException();
            }
            candidate = getRandomPuzzleCandidate(word, prob, generator);
            countTry++;
        }
        return candidate;
    }

    public static int applyGuess(char guess, String solution, char[] puzzle) { // Q - 7
        int countChanges = 0;
        if (solution.indexOf(guess) != -1 && puzzle[solution.indexOf(guess)] != guess) {
            for (int index = 0; index < solution.length(); index++) {
//                check if the guess is right and correcting the puzzle:
                if (solution.charAt(index) == guess) {
//                   updating the puzzle and counting the changes:
                    puzzle[index] = guess;
                    countChanges++;
                }
            }
        }
        return countChanges;
    }

    public static char[] getHelp(String solution, char[] puzzle) { // Q - 8

        for (int index = 0; index < solution.length(); index++) {
            if (puzzle[index] == HIDDEN_CHAR) {
                applyGuess(solution.charAt(index), solution, puzzle);
                break;
            }
        }
        return puzzle;
    }

    private static char getGuess() {
        Scanner enterGuess = new Scanner(System.in);
        return enterGuess.nextLine().charAt(0);
    }

    private static String getFilePathFromUser() {
        Scanner inputFilePath = new Scanner(System.in);
        return inputFilePath.nextLine();
    }

    private static String getAnswerFromUser() {
        Scanner inputAnswer = new Scanner(System.in);
        return inputAnswer.nextLine();
    }

    private static float getProb() {
        Scanner hidingProb = new Scanner(System.in);
        return hidingProb.nextFloat();
    }

    private static boolean CheckIfWon(char[] randomPuzzle) {
        if (countHiddenInPuzzle(randomPuzzle) == 0) {
            printPuzzle(randomPuzzle);
            printWinMessage();
            return true;
        }
        return false;
    }

    private static void checkIfTheUserNeedToGetHelp(String puzzleWord, char[] randomPuzzle, char guess) {
        if (guess == 'H') {
            getHelp(puzzleWord, randomPuzzle);
            printPuzzle(randomPuzzle);
        }
    }

    public static void throwPuzzleVocabularyCreatingException() {
        throw new RuntimeException("Failed creating a vocabulary . missing an argument");
    }

    // missed exception notice
    public static void main(String[] args) throws Exception { //Q - 9
//        my random generator:
        Random generator = new MyRandom(new int[]{0, 1, 2, 3, 4, 5}, new float[]{0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f});
//        getting a file for the vocabulary:
        String fileName = getFilePathFromUser();
        File file = new File(fileName);
        Scanner puzzleFile = new Scanner(file);
//        creating the vocabulary:
        String[] vocabularyFile = scanVocabulary(puzzleFile);
        int numOfWords = Array.getLength(vocabularyFile);
//        throws exception:
        if (fileName.equals("") || numOfWords == 0){
            throwPuzzleVocabularyCreatingException();
        }

        printReadVocabulary(fileName, numOfWords);


        printSettingsMessage();
//       enter a prob:
        printEnterHidingProbability();
        float prob = getProb();
//       get random word:
        String puzzleWord = getRandomWord(vocabularyFile, generator);
        char[] randomPuzzle = getRandomPuzzle(puzzleWord, prob, generator);
        printPuzzle(randomPuzzle);

//        asking to replace the puzzle:
        while (true) {
            printReplacePuzzleMessage();
            String userAnswer = getAnswerFromUser();

            if (userAnswer.equalsIgnoreCase("yes")) {
                puzzleWord = getRandomWord(vocabularyFile, generator);
                randomPuzzle = getRandomPuzzle(puzzleWord, prob, generator);
                printPuzzle(randomPuzzle);
            }
            if (userAnswer.equalsIgnoreCase("no")) {
                break;
            }
        }
//        start the game:
        printGameStageMessage();
        int numOfTry = countHiddenInPuzzle(randomPuzzle) + 3;
        printPuzzle(randomPuzzle);

//       game loop:
        while (true) {
            printEnterYourGuessMessage();
            char guess = getGuess();
//           if guess was correct:
            if (applyGuess(guess, puzzleWord, randomPuzzle) > 0) {
                numOfTry--;
                if (CheckIfWon(randomPuzzle)) break;

                printCorrectGuess(numOfTry);
                printPuzzle(randomPuzzle);
            }
//           if guess was wrong:
            if (applyGuess(guess, puzzleWord, randomPuzzle) == 0) {
                numOfTry--;
//                if needed help:
                checkIfTheUserNeedToGetHelp(puzzleWord, randomPuzzle, guess);
                if (CheckIfWon(randomPuzzle)) break;

                printWrongGuess(numOfTry);
                printPuzzle(randomPuzzle);
            }
//           check num of tries for if game over:
            if (numOfTry == 0) {
                printGameOver();
                break;
            }

        }

    }


    /*************************************************************/
    /*********************  Don't change this ********************/
    /*************************************************************/



    public static void printReadVocabulary(String vocabularyFileName, int numOfWords) {
        System.out.println("Read " + numOfWords + " words from " + vocabularyFileName);
    }

    public static void throwPuzzleGenerationException() {
        throw new RuntimeException("Failed creating a legal puzzle after 1000 attempts!");
    }

    public static void printSettingsMessage() {
        System.out.println("--- Settings stage ---");
    }

    public static void printEnterHidingProbability() {
        System.out.println("Enter your hiding probability:");
    }

    public static void printPuzzle(char[] puzzle) {
        System.out.println(puzzle);
    }

    public static void printReplacePuzzleMessage() {
        System.out.println("Replace puzzle?");
    }

    public static void printGameStageMessage() {
        System.out.println("--- Game stage ---");
    }

    public static void printEnterYourGuessMessage() {
        System.out.println("Enter your guess:");
    }

    public static void printCorrectGuess(int attemptsNum) {
        System.out.println("Correct Guess, " + attemptsNum + " guesses left");
    }

    public static void printWrongGuess(int attemptsNum) {
        System.out.println("Wrong Guess, " + attemptsNum + " guesses left");
    }

    public static void printWinMessage() {
        System.out.println("Congratulations! You solved the puzzle");
    }

    public static void printGameOver() {
        System.out.println("Game over!");
    }

}
