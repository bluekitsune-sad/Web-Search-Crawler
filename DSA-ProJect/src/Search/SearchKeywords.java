package Search;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

import Controller.WebEngine;
import ExternalClasses.In;
import ExternalClasses.TST;

public class SearchKeywords {

    //getting string and removing stop words from string
    public static String[] getKeywordsAndRemoveStopWords(String inputText)
    {
        //intializing variable
        int i = 0;
        String[] words;

        //reading file stopwords
        In in = new In(WebEngine.mainPath +"/src/Files/stopwords.txt");

        //converting string to lower case
        inputText = inputText.toLowerCase();

        //iterating in file
        while (!in.isEmpty())
        {
            //reading line
            String text = in.readLine();
            //converting text to lowercase
            text = text.toLowerCase();
            text = "\\b"+text+"\\b";

            //removing stopwords
            inputText = inputText.replaceAll(text,"");
        }
        //tokenizing string
        StringTokenizer st  = new StringTokenizer(inputText, " ");

        //creating a list of string with length of tokens
        words = new String[st.countTokens()];

        while (st.hasMoreTokens())
        {
            //adding value word array
            words[i]=st.nextToken();
            i++;
        }
        return words;
    }


    //read file words into tst by having filepath
    public static TST<Integer> readFileIntoTST(String filePath)
    {
        String fileData;
        String [] tokens;
        int occurenceCounter=0;

        In in = new In(filePath);

        TST<Integer> st = new TST<Integer>();

        fileData = in.readAll();

        //removing the stopwords from file to be able to have less keywords in tst
        //helps in reducing the number of words
        tokens = getKeywordsAndRemoveStopWords(fileData);

        //iterating in tokens
        for(String token : tokens)
        {
            if(st.contains(token))
            {
                //exist so first get the value and then add 1 to the value
                occurenceCounter = st.get(token)+1;

                //insert/update the value in the key
                st.put(token,occurenceCounter);
            }

            //not found in tree
            else
            {
                //make occurence 1
                occurenceCounter = 1;
                //add the token in tree with value of 1
                st.put(token,occurenceCounter);
            }
        }
        return st;
    }

    //storing hashmap into .dat file for future use
    public static void storeHashMapInFile(HashMap<String, Integer> listOfFrequencies, String[] words) {

        //initializing variables
        String fileName = "";
        String filePath = WebEngine.mainPath +"/src/Files/SearchedQuery/";
        String finalPath;

        //creating filname with searchquery
        for (String word : words)
        {
            fileName = fileName + word + "_";
        }

        //adding extension to the filename
        fileName = fileName + ".dat";

        finalPath = filePath + fileName;

        try {

            FileOutputStream fileOut = new FileOutputStream(finalPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            //writing hashmap into .dat file
            out.writeObject(listOfFrequencies);
            out.close();
            fileOut.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }
    public static HashMap<String,Integer> getHashMapFromFile(String[] words)
    {
        //initializing variables
        String fileName = "";
        String filePath = WebEngine.mainPath +"/src/Files/SearchedQuery/";
        String finalPath;

        for (String str : words) {

            fileName = fileName + str + "_";
        }

        fileName = fileName + ".dat";

        finalPath = filePath + fileName;

        HashMap<String,Integer> listOfFrequencies = new HashMap<String,Integer>();
        listOfFrequencies = null;

        try{

            FileInputStream fileIn = new FileInputStream(finalPath);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            //reading hashmap from file
            listOfFrequencies = (HashMap<String, Integer>)in.readObject();
            in.close();
            fileIn.close();

        } catch (Exception e){

            e.printStackTrace();
        }

        return listOfFrequencies;

    }



}