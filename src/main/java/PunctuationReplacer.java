public class PunctuationReplacer {

    public static String deletePunctuation(String word) {
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (!Character.isAlphabetic(letter)) {
                word = word.replace(Character.toString(letter), "");
            }
        }
        return word;
    }
}
