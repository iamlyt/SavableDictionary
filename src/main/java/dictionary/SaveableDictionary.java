package dictionary;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SaveableDictionary {

    private HashMap<String, String> dictionary;
    private File textFile;

    public SaveableDictionary() {
        this.dictionary = new HashMap<>();
    }

    // tells the dictionary the name of the file to load dictionary from
    public SaveableDictionary(String file) {
        this.dictionary = new HashMap<>();


//        this.fileReader = new Scanner(Paths.get(file));
        // load file
        this.textFile = new File(file);
    }


    public boolean load() {
        boolean wasSuccessful = false;


        try (Scanner scan = new Scanner(textFile)){
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split(":");
//                System.out.println(parts[0]);     // part of line before :
//                System.out.println(parts[1]);     // part of line after :
                this.add(parts[0], parts[1]);
                wasSuccessful = true;
            }

        } catch (Exception e) {
            wasSuccessful = false;
            System.out.println(e.getMessage());
        }
        return wasSuccessful;
    }

    // saves dictionary to file given to the dictionary as a parameter to the constructor
//    public boolean save() throws IOException {
//
//        boolean wasSuccessful = false;
//        try {
//            PrintWriter writer = new PrintWriter(textFile);
//
//            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
//                writer.printf("%s:%s\n", entry.getKey(), entry.getValue());
//            }
//            wasSuccessful = true;
//            writer.close();
//
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//            wasSuccessful = false;
//        }
//        return wasSuccessful;
//
//    }


    // adds a word to the dictionary
    // every word = 1 translation
    // if same word added for 2nd time, NOTHING HAPPENS
    public void add(String words, String translation) {
//        if (!(this.dictionary.containsKey(words))) {
//            this.dictionary.put(words, translation);
//        }
        // SAME AS:
        this.dictionary.putIfAbsent(words, translation);
    }

    // returns translation for the given word
    // if word NOT in dictionary, RETURN NULL
    public String translate(String word) {
        // get key OR value of the WORD
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            // if word matches value, return the key of that value
            if (word.equals(entry.getValue())) {
                return entry.getKey();
            }
            // if word matches key, return the value of that key
            if (word.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

//    public HashMap<String, String> getDictionary() {
//        return dictionary;
//    }

    // deletes given word and its translation from dictionary
    // cannot remove simply by using for loop -- use Iterator or removeIf operation
    public void delete(String word) {
        // using Iterator
//        Iterator<Map.Entry<String, String>> it = dictionary.entrySet().iterator();
//
//        while (it.hasNext()) {
//            if (it.next().getKey().equals(word)) {
//                it.remove();
//            }
//        }

        // remove if key matched
        dictionary.entrySet()
                .removeIf(entry -> entry.getKey().equals(word));
        // remove if value matched
        dictionary.entrySet()
                .removeIf(entry -> entry.getValue().equals(word));

    }

    public void printDictionary() {
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            System.out.println("Word: " + entry.getKey() + " -- Translation: " + entry.getValue());
        }
    }
}
