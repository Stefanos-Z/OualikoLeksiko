package models;

/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */
public class WelshWord {
    
    /* Variables Declaration */
    private int wordID;
    private String welshWord;
    private String englishMeaning;
    private String gender;

    /**
     * Constructor
     * @param wordID references the word ID
     * @param welshWord references the Welsh Meaning
     * @param englishMeaning references the English Meaning
     * @param gender references the word gender
     */
    public WelshWord(int wordID, String welshWord, String englishMeaning, String gender) {
        this.wordID = wordID;
        this.welshWord = welshWord;
        this.englishMeaning = englishMeaning;
        this.gender = gender;
    }

    /**
     * Gets word ID
     * @return an integer holding the word id
     */
    public int getWordID() {
        return wordID;
    }

    /**
     * Gets the Welsh Word Meaning
     * @return a string representation of the word in Welsh
     */
    public String getWelshWord() {
        return welshWord;
    }
    
    /**
     * Gets the English Word Meaning
     * @return a string representation of the word in English
     */
    public String getEnglishMeaning() {
        return englishMeaning;
    }

    /**
     * Gets the word's Gender
     * @return the current Gender of the selected word
     */
    public String getGender() {
        return gender;
    }
    
    /**
     * Sets the word's ID
     * @param wordID is the new given ID
     */
    public void setWordID(int wordID) {
        this.wordID = wordID;
    }

    /**
     * Sets the Welsh Meaning of the Word
     * @param welshWord is the new given word for the Welsh Meaning
     */
    public void setWelshWord(String welshWord) {
        this.welshWord = welshWord;
    }
    
    /**
     * Sets the English Meaning of the Word
     * @param englishMeaning is the new given word for the English Meaning
     */
    public void setEnglishMeaning(String englishMeaning) {
        this.englishMeaning = englishMeaning;
    }

    /**
     * Sets the Gender of the Word
     * @param gender is the new given gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
}
