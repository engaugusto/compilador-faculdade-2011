/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import javax.naming.spi.DirectoryManager;

/**
 *
 * @author Carlos Augusto
 */
public class Tokens {

    private String fileName;
    private String fileName2;
    private Hashtable tokens;
    private Hashtable keyWords;
    private Hashtable CkeyWords;

    public Hashtable getTokens() {
        return tokens;
    }

    public Tokens() {
        fileName = "tmp//tokens.txt";
        fileName2 = "tmp\\CTokens.txt";
        tokens = new Hashtable<String, String>();
        keyWords = new Hashtable<String, String>();
        CkeyWords = new Hashtable<String, String>();
        readFile();
    }
    
    public String getValue(String Token){
        return (String) tokens.get(Token);
    }  
    
    public String getKeyWord(String Token){
        return (String) keyWords.get(Token);
    }
    
    public String getCKeyWord(String Token){
        return (String) CkeyWords.get(Token);
    }
  
    public void readFile() {
        try {
            FileReader fIn = new FileReader("C:\\Users\\luizbag\\Documents\\facens\\Quarto Ano\\Tópicos de Computação\\CompiladorDude++\\trunk\\Controller\\"+fileName);
            BufferedReader brIn = new BufferedReader(fIn);
            String line = null;
            while ((line = brIn.readLine()) != null) {
                tokens.put(line.substring(0, line.indexOf(";")),line.substring(line.indexOf(";")+1, line.length()));
                keyWords.put(line.substring(line.indexOf(";")+1, line.length()),line.substring(0, line.indexOf(";")));
            }
            fIn.close();
            
            keyWords.put("tkId", "Variavel");
            keyWords.put("tkI", "Inteiro");
            keyWords.put("tkR", "Real");                    
            
            //Carregando tokens de C
            FileReader fInC = new FileReader("C:\\Users\\luizbag\\Documents\\facens\\Quarto Ano\\Tópicos de Computação\\CompiladorDude++\\trunk\\Controller\\"+fileName2);
            BufferedReader brInC = new BufferedReader(fInC);
            String lineC = null;
            while ((lineC = brInC.readLine()) != null) {                
                CkeyWords.put(lineC.substring(0, lineC.indexOf("|")),lineC.substring(lineC.indexOf("|")+1, lineC.length()));                              
            }
            fInC.close();
            
        } catch (IOException e) {
            System.out.println("IOException error!");
            e.printStackTrace();
        }
    }
}
