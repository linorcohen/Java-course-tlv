package Exercises.ex5;


import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class BigramModel {
    public static final int MAX_VOCABULARY_SIZE = 14000;
    public static final String VOC_FILE_SUFFIX = ".voc";
    public static final String COUNTS_FILE_SUFFIX = ".counts";
    public static final String SOME_NUM = "some_num";
    public static final int ELEMENT_NOT_FOUND = -1;


    String[] mVocabulary;
    int[][] mBigramCounts;


    // DO NOT CHANGE THIS !!!
    public void initModel(String fileName) throws IOException {
        mVocabulary = buildVocabularyIndex(fileName);
        mBigramCounts = buildCountsArray(fileName, mVocabulary);
    }


    /*
     * @post: mVocabulary = prev(mVocabulary)
     * @post: mBigramCounts = prev(mBigramCounts)
     */
    public String[] buildVocabularyIndex(String fileName) throws IOException { // Q 1
        File fileOpen = new File(fileName);
        BufferedReader fileRead = new BufferedReader(new FileReader(fileOpen));
        String[] vocabulary = new String[MAX_VOCABULARY_SIZE];
        String line;
        int indexForVocabulary = 0;
        while ((line = fileRead.readLine()) != null) {
            String[] splitLine = line.split(" ");
                indexForVocabulary = checkForLegalWordsToAddVocabulary(splitLine, indexForVocabulary, vocabulary);
            if (indexForVocabulary == MAX_VOCABULARY_SIZE) {
                break;
            }
        }
        fileRead.close();
        vocabulary = Arrays.copyOf(vocabulary, indexForVocabulary);
        vocabulary = removeDuplicateWords(vocabulary);
        return vocabulary;
    }

    /*
     *
     * @pre: splitLine length > 0
     * @pre: indexForVocabulary >= 0
     * @pre: vocabulary length > 0
     * @post: change all the legal words in splitline to lowercase and change numbers to 'sum num'
     * and add them to the vocabulary. ignore all the illegal words.
     */
    private int checkForLegalWordsToAddVocabulary(String[] splitLine, int indexForVocabulary, String[] vocabulary) {
        for (int index = 0; index < splitLine.length; index++) {
                String[] wordToCheck = splitLine[index].split("");
                int countNumOfDigits = 0;
                for (int indexChar = 0; indexChar < wordToCheck.length; indexChar++) {
                    if (indexForVocabulary >= MAX_VOCABULARY_SIZE) {
                        break;
                    }
                    if (wordToCheck[indexChar].equals("")) {
                        break;
                    }
                    char checkChar = wordToCheck[indexChar].charAt(0);
                    if (indexChar == 0 && !Character.isLetterOrDigit(checkChar)) {
                        break;
                    }
                    if (Character.isDigit(checkChar)) {
                        countNumOfDigits++;
                    }
                    if (Character.isLetter(checkChar)) {
                        wordToCheck[indexChar] = wordToCheck[indexChar].toLowerCase();
                    }
                    if (indexChar == wordToCheck.length - 1) {
                        vocabulary[indexForVocabulary] = String.join("", wordToCheck);
                        indexForVocabulary++;
                    }
                }
                if (countNumOfDigits == wordToCheck.length) {
                    vocabulary[indexForVocabulary] = SOME_NUM;
                    indexForVocabulary++;
                }

        }
        return indexForVocabulary;
    }


    /*
     *
     * @pre: vocabulary length >= 2
     * @post: vocabulary without duplicate words.
     */
    private String[] removeDuplicateWords(String[] vocabulary) {
        int indexToPlace = 1;
        for (int index = 1; index < Array.getLength(vocabulary); index++) {
            String word = vocabulary[index];
            for (int i = 0; i < index; i++) {
                if (word.equals(vocabulary[i])) {
                    vocabulary[i] = word;
                    break;
                }
                if (i == index - 1) {
                    vocabulary[indexToPlace] = word;
                    indexToPlace++;
                }
            }
        }
        vocabulary = Arrays.copyOf(vocabulary, indexToPlace);
        return vocabulary;
    }


    /*
     * @post: mVocabulary = prev(mVocabulary)
     * @post: mBigramCounts = prev(mBigramCounts)
     */
    public int[][] buildCountsArray(String fileName, String[] vocabulary) throws IOException { // Q - 2
        File fileOpen = new File(fileName);
        BufferedReader fileRead = new BufferedReader(new FileReader(fileOpen));
        String line;
        int[][] counts = new int[vocabulary.length][vocabulary.length];
        while ((line = fileRead.readLine()) != null) {
            String[] splitLine = line.split(" ");
            for (int index = 0; index < splitLine.length - 1; index++) {
                int firstWordIndex = transferWords(splitLine[index], vocabulary);
                int secWordIndex = transferWords(splitLine[index + 1], vocabulary);
                if (firstWordIndex != ELEMENT_NOT_FOUND && secWordIndex != ELEMENT_NOT_FOUND) {
                    counts[firstWordIndex][secWordIndex]++;
                }
            }
        }
        fileRead.close();
        return counts;

    }

    /*
     *
     * @pre: word length > 0
     * @pre: vocabulary need to be created and fill.
     * @post: change the word to legal word, and $ret = index of the word in the vocabulary.
     */
    private static int transferWords(String word, String[] vocabulary) {
        String[] wordArray = word.split("");
        int countNumOfDigits = 0;
        for (int index = 0; index < wordArray.length; index++) {
            String i = wordArray[index];
            if (i.equals("")) {
                break;
            }
            if (Character.isDigit(i.charAt(0))) {
                countNumOfDigits++;
            }
            if (Character.isLetter(i.charAt(0))) {
                wordArray[index] = wordArray[index].toLowerCase();
            }
        }
        if (countNumOfDigits == wordArray.length) {
            word = SOME_NUM;
        } else {
            word = String.join("", wordArray);
        }
        return returnWordIndex(word, vocabulary);
    }

    /*
     *
     * @pre: word need to be legal word.
     * @pre: vocabulary need to be created and full.
     * @post: $ret = index of the word in the vocabulary , else $ret = -1 for element not found.
     */
    private static int returnWordIndex(String word, String[] vocabulary) {
        for (int i = 0; i < vocabulary.length; i++) {
            if (vocabulary[i].equals(word)) {
                return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /*
     * @pre: the method initModel was called (the language model is initialized)
     * @pre: fileName is a legal file path
     */
    public void saveModel(String fileName) throws IOException { // Q-3
//        write to file voc :
        File fileVocWrite = new File(fileName + VOC_FILE_SUFFIX);
        BufferedWriter fileVoc = new BufferedWriter(new FileWriter(fileVocWrite));

        fileVoc.write(mVocabulary.length + " words\n");

        for (int i = 0; i < mVocabulary.length; i++) {
            fileVoc.write(i + "," + mVocabulary[i] + "\n");
        }
        fileVoc.close();

//       write to file counts :
        File fileCountWrite = new File(fileName + COUNTS_FILE_SUFFIX);
        BufferedWriter fileCount = new BufferedWriter(new FileWriter(fileCountWrite));

        for (int indexWord1 = 0; indexWord1 < mBigramCounts.length; indexWord1++) {
            for (int indexWord2 = 0; indexWord2 < mBigramCounts.length; indexWord2++) {
                if (mBigramCounts[indexWord1][indexWord2] > 0) {
                    fileCount.write(indexWord1 + "," + indexWord2 + ":" + mBigramCounts[indexWord1][indexWord2] + "\n");
                }
            }
        }
        fileCount.close();
    }


    /*
     * @pre: fileName is a legal file path
     */
    public void loadModel(String fileName) throws IOException { // Q - 4

        File fileVocRead = new File(fileName + VOC_FILE_SUFFIX);
        BufferedReader fileVoc = new BufferedReader(new FileReader(fileVocRead));

        String line;
        line = fileVoc.readLine();
        String[] splitLine = line.split(" ");
        mVocabulary = new String[Integer.parseInt(splitLine[0])];
        while ((line = fileVoc.readLine()) != null) {
            splitLine = line.split(",");
            mVocabulary[Integer.parseInt(splitLine[0])] = splitLine[1];
        }
        fileVoc.close();

        File fileCountRead = new File(fileName + COUNTS_FILE_SUFFIX);
        BufferedReader fileCount = new BufferedReader(new FileReader(fileCountRead));
        int vLength = Array.getLength(mVocabulary);
        mBigramCounts = new int[vLength][vLength];
        while ((line = fileCount.readLine()) != null) {
            splitLine = line.split("[,:]");
            int firstIndex = Integer.parseInt(splitLine[0]);
            int secIndex = Integer.parseInt(splitLine[1]);
            int value = Integer.parseInt(splitLine[2]);
            mBigramCounts[firstIndex][secIndex] = value;
        }
        fileCount.close();
    }


    /*
     * @pre: word is in lowercase
     * @pre: the method initModel was called (the language model is initialized)
     * @pre: word is in lowercase
     * @post: $ret = -1 if word is not in vocabulary, otherwise $ret = the index of word in vocabulary
     */
    public int getWordIndex(String word) {  // Q - 5
        int wordIndex = ELEMENT_NOT_FOUND;
        for (int index = 0; index < mVocabulary.length; index++) {
            if (mVocabulary[index].equals(word)) {
                wordIndex = index;
                break;
            }
        }
        return wordIndex;
    }


    /*
     * @pre: word1, word2 are in lowercase
     * @pre: the method initModel was called (the language model is initialized)
     * @post: $ret = the count for the bigram <word1, word2>. if one of the words does not
     * exist in the vocabulary, $ret = 0
     */
    public int getBigramCount(String word1, String word2) { //  Q - 6
        int indexWord1 = getWordIndex(word1);
        int indexWord2 = getWordIndex(word2);
        if (indexWord1 != -1 && indexWord2 != -1) {
            return mBigramCounts[indexWord1][indexWord2];
        }
        return 0;
    }


    /*
     * @pre word in lowercase, and is in mVocabulary
     * @pre: the method initModel was called (the language model is initialized)
     * @post $ret = the word with the lowest vocabulary index that appears most fequently after word (if a bigram starting with
     * word was never seen, $ret will be null
     */
    public String getMostFrequentProceeding(String word) { //  Q - 7
        int countFreq = 0;
        String wordFreq = "";
        for (int index1 = 0; index1 < mVocabulary.length; index1++) {
            if (mVocabulary[index1].equals(word)) {
                int countIndex = 0;
                countFreq = mBigramCounts[index1][countIndex];
                wordFreq = mVocabulary[countIndex];
                for (int index2 = 1; index2 < mBigramCounts.length; index2++) {
                    if (mBigramCounts[index1][index2] > mBigramCounts[index1][countIndex]) {
                        countIndex = index2;
                        countFreq = mBigramCounts[index1][countIndex];
                        wordFreq = mVocabulary[countIndex];
                    }
                }
                break;
            }
        }
        if (countFreq != 0) {
            return wordFreq;
        }
        return null;
    }


    /* @pre: sentence is in lowercase
     * @pre: the method initModel was called (the language model is initialized)
     * @pre: each two words in the sentence are are separated with a single space
     * @post: if sentence is is probable, according to the model, $ret = true, else, $ret = false
     */
    public boolean isLegalSentence(String sentence) {  //  Q - 8
        String[] splitSentence = sentence.split(" ");
        for (String i : splitSentence) {
            if (getWordIndex(i) == -1) {
                return false;
            }
        }
        for (int index = 0; index < splitSentence.length - 1; index++) {
            int indexWord = getWordIndex(splitSentence[index]);
            int indexNextWord = getWordIndex(splitSentence[index + 1]);
            if (mBigramCounts[indexWord][indexNextWord] == 0) {
                return false;
            }
        }
        return true;
    }


    /*
     * @pre: arr1.length = arr2.legnth
     * post if arr1 or arr2 are only filled with zeros, $ret = 0, otherwise
     */
    public static double calcCosineSim(int[] arr1, int[] arr2) { //  Q - 9
        double count = 0;
        double denominator1 = 0;
        double denominator2 = 0;
        for (int index = 0; index < arr1.length; index++) {
            count = count + (arr1[index] * arr2[index]);
            denominator1 = denominator1 + Math.pow(arr1[index], 2);
            denominator2 = denominator2 + Math.pow(arr2[index], 2);
        }
        return count / (Math.sqrt(denominator1) * Math.sqrt(denominator2));
    }


    /*
     * @pre: word is in vocabulary
     * @pre: the method initModel was called (the language model is initialized),
     * @post: $ret = w implies that w is the word with the largest cosineSimilarity(vector for word, vector for w) among all the
     * other words in vocabulary
     */
    public String getClosestWord(String word) { //  Q - 10
        if (mVocabulary.length == 1) {
            return mVocabulary[0];
        }
        int wordIndex = getWordIndex(word);
        int[] wordVector = mBigramCounts[wordIndex];
        double max = 0.0;
        int wordVectorClosestIndex = 0;
        for (int index = 0; index < mVocabulary.length; index++) {
            if (index == wordIndex) {
                continue;
            }
            double calc = calcCosineSim(wordVector, mBigramCounts[index]);
            if (calc > max) {
                max = calc;
                wordVectorClosestIndex = index;
            }
        }
        return mVocabulary[wordVectorClosestIndex];
    }

}