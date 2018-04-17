package models;

/**
 *
 * @author Loizos Vasileiou (eeu905)
 */
public class WelshWord {
    
    private int wordID;
    private String welshWord;
    private String englishMeaning;
    private String gender;

    public WelshWord(int wordID, String welshWord, String englishMeaning, String gender) {
        this.wordID = wordID;
        this.welshWord = welshWord;
        this.englishMeaning = englishMeaning;
        this.gender = gender;
    }

    public int getWordID() {
        return wordID;
    }

    public void setWordID(int wordID) {
        this.wordID = wordID;
    }

    public String getWelshWord() {
        return welshWord;
    }

    public void setWelshWord(String welshWord) {
        this.welshWord = welshWord;
    }

    public String getEnglishMeaning() {
        return englishMeaning;
    }

    public void setEnglishMeaning(String englishMeaning) {
        this.englishMeaning = englishMeaning;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
