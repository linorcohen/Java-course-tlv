package Exercises.ex8.wordsRank;

import java.io.File;
import java.util.*;

import Exercises.ex8.histogram.HashMapHistogram;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class FileIndex {

    public static final int UNRANKED_CONST = 30;
    private final HashMap<String, HashMapHistogram<String>> fileIndex;

    public FileIndex() {
        this.fileIndex = new HashMap<>();
    }

    /*
     * @pre: the directory is no empty, and contains only readable text files
     */
    public void indexDirectory(String folderPath) {
        //This code iterates over all the files in the folder. add your code wherever is needed
        File folder = new File(folderPath);
        File[] listFiles = folder.listFiles();
        for (File file : listFiles) {
            // for every file in the folder
            if (file.isFile()) {
                HashMapHistogram<String> fileHistogram = new HashMapHistogram<>();
                List<String> listOfWords = null;
                try {
                    listOfWords = FileUtils.readAllTokens(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (listOfWords != null) {
                    for (String word : listOfWords) {
                        fileHistogram.addItem(word.toLowerCase());
                    }
                }
                fileIndex.put(file.getName(), fileHistogram);
            }
        }
    }


    /*
     * @pre: the index is initialized
     * @pre filename is a name of a valid file
     * @pre word is not null
     */
    public int getCountInFile(String filename, String word) throws FileIndexException {
        if (!fileIndex.containsKey(filename)) {
            throw new FileIndexException("File does not exists");
        }
        return (fileIndex.get(filename)).getCountForItem(word.toLowerCase());

    }

    /*
     * @pre: the index is initialized
     * @pre filename is a name of a valid file
     * @pre word is not null
     */
    public int getRankForWordInFile(String filename, String word) throws FileIndexException {
        if (!fileIndex.containsKey(filename)) {
            throw new FileIndexException("File does not exists");
        }
        if (!setFileList(filename).contains(word.toLowerCase())) {
            return UNRANKED_CONST + setFileList(filename).size();
        }
        return setFileList(filename).indexOf(word.toLowerCase()) + 1;
    }


    private List<String> setFileList(String filename) {
        List<String> list = new ArrayList<>();
        for (String s : fileIndex.get(filename)) {
            list.add(s);
        }
        return list;
    }


    /*
     * @pre: the index is initialized
     * @pre word is not null
     */
    public int getAverageRankForWord(String word) {
        Map<String, Integer> ranksForFile = getRankForFileMap(word);
        RankedWord rWord = new RankedWord(word, ranksForFile);
        return rWord.getAverage();
    }

    private Map<String, Integer> getRankForFileMap(String word) {
        Map<String, Integer> rankForFile = new HashMap<>();
        for (String file : fileIndex.keySet()) {
            try {
                rankForFile.put(file, getRankForWordInFile(file, word.toLowerCase()));
            } catch (FileIndexException e) {
                e.printStackTrace();
            }
        }
        return rankForFile;
    }


    private List<RankedWord> getRankedWords() {
        Set<String> setOfAllWords = new HashSet<>();
        for (String file : fileIndex.keySet()) {
            setOfAllWords.addAll(fileIndex.get(file).getItemsSet());
        }
        List<RankedWord> listOfWordsRanks = new ArrayList<>();
        for (String word : setOfAllWords) {
            listOfWordsRanks.add(new RankedWord(word, getRankForFileMap(word)));
        }
        return listOfWordsRanks;
    }

    public List<String> getWordsWithAverageRankSmallerThanK(int k) {
        return getWords(k, RankedWord.rankType.average);

    }

    public List<String> getWordsWithMinRankSmallerThanK(int k) {
        return getWords(k, RankedWord.rankType.min);
    }

    private List<String> getWords(int k, RankedWord.rankType rankType) {
        List<RankedWord> listOfWordsRanks = getRankedWords();
        listOfWordsRanks.sort(new RankedWordComparator(rankType));
        List<String> sublist = new ArrayList<>();
        for (RankedWord e : listOfWordsRanks) {
            if (rankType.equals(RankedWord.rankType.min) && e.getMin() < k
                    || rankType.equals(RankedWord.rankType.max) && e.getMax() < k
                    || rankType.equals(RankedWord.rankType.average) && e.getAverage() < k ) {
                sublist.add(e.getWord());
            }
        }
        return sublist;
    }

    public List<String> getWordsWithMaxRankSmallerThanK(int k) {
        return getWords(k, RankedWord.rankType.max);
    }

}
