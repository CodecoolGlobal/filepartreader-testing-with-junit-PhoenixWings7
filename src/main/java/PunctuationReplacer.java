public class PunctuationReplacer {
    private static final char[] punctuationSigns = {'.', ',', '?', ':', ';'};

    public static String deletePunctuation(String word) {
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            for (char sign : punctuationSigns) {
                if (letter == sign) {
                    word = word.replace(Character.toString(sign), "");
                    break;
                }
            }
        }
        return word;
    }
}
