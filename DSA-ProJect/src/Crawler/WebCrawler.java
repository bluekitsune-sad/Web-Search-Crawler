package Crawler;

import Controller.WebEngine;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class WebCrawler {
    //HashSet is used as it prevents the duplicate value;
    //HashSet class is used to create a collection that uses a hash table for storage;
    private static Set<String> crawledURLS = new HashSet<String>();
    private static int maxLimit = 2; //depth is 2 as our system took more time above that to crawl;
    //For below line see this link http://regexr.com?37i6s  ;
    //Regex(Regular expression) if you want to ensure URL starts with HTTP/HTTPS;
    private static String urlRegex = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";    //validator;

    public static void crawlerStart(String URL, int limit) throws IOException { //IO Exception occurs in disk,network or using external,IO file. this is not a runtime Exception;
        Pattern urlPattern =Pattern.compile(urlRegex);  // pattern (or filter) that describes a set of strings that matches the pattern;
        //We are not using this expression because we ara checking it in Web Engine class;      // pattern.compile compares the regex expression and the given url;
        PrintWriter urlsFile = new PrintWriter( new FileWriter(WebEngine.mainPath + "/src/Files/URLSExtracted/Urls.txt"));  //Url file opening;
        //this is just like system.out.print but this can be customized;
        if(limit<= maxLimit) {
            try {
                Document getDoc = Jsoup.connect(URL).get(); //Document is a Jsoup method;
                limit++;
                savingDoc(getDoc,URL);  //Send the url received connected(html) and user inputted url(html);
                if (limit < maxLimit) {
                    Elements elementsLinks = getDoc.select("a[href]");  // This is a css code <a herf = url> this is an example;
                    for(Element element : elementsLinks){ //Element is a Jsoup method;

                        crawlerStart(element.absUrl("href"), limit); //absurl is a Jsoup.nodes method which takes String arrtibute key;
                        //this extracts the <a href="/text">example</a> from html file;
                        if (!crawledURLS.contains(element.absUrl("href"))) {    //If the url does not contain href attribute key;
                            WebEngine.setOutputln(element.absUrl("href"));
                            crawledURLS.add(element.absUrl("href"));
                            urlsFile.println(element.absUrl("href"));
                        }
                    }
                }
            }catch (Exception e){
                System.out.println("Exception "+e); //printf the exception occured in "try" ;
            }
        }
        urlsFile.close();   //Closing the url file opened abowe;
    }
    private static void savingDoc(Document getDoc, String URL) throws FileNotFoundException { //this Eception need no explanation;
        try {
            PrintWriter html = new PrintWriter( //this is a java.io method. this is used for writhing html code (div, span);
                    new FileWriter(System.getProperty("user.dir") + "/src/Files/HtmlFiles/"+getDoc.title().replaceAll("\\W", "") + ".html")); // Open of the htlm file;
            html.write(getDoc.toString());
            html.close();//close of html file;

            processHtmlToText(System.getProperty("user.dir") + "/src/Files/HtmlFiles/" + getDoc.title().replaceAll("\\W", "")+ ".html", URL,getDoc.title().replaceAll("\\W", "") + ".txt");
        }
        catch (Exception e){
            System.out.println("Exception" + e); //print exception occured;
        }

    }
    private static void processHtmlToText(String file, String URL, String fileName) throws IOException {
        File textfile= new File(file);  //Creating a new file called textfile ;
        Document textdoc= Jsoup.parse(textfile, "UTF-8");   //Creating Document(Jsoup method) textdoc;
        String words = textdoc.text().toLowerCase(); //.text is a jsoup.element method ;
        words = URL + "\n" + words; //Editing in words
        PrintWriter printwriter= null;  //seting to null. this is the html code helper from savingDoc;

        try {
            File saveFile= new File(System.getProperty("user.dir") + "/src/Files/TextFiles/" + fileName);   //creation
            printwriter = new PrintWriter(saveFile);    //
            printwriter.println(words);
        }finally {  //The 'finally' keyword is used to execute code(used with exceptions - try.. catch statements) no matter if there is an exception or not;
            if(printwriter!=null) { //if for reason it is not set as null close the html editor from savingDoc;
                printwriter.close();
            }
        }
    }
}
