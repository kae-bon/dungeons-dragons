package org.example;

public class Main {

    public static void main(String[] args) {
        isPalindrome("A man, a plan, a canal: Panama");
    }

    public static boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }

        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length / 2; i++) {
            for (int j = chars.length - 1; j >= chars.length / 2; j-- ) {
                if (Character.toLowerCase(chars[i]) != Character.toLowerCase(chars[j])
                        && (Character.isLetterOrDigit(chars[i]) && Character.isLetterOrDigit(chars[j]))) {
                    return false;
                } else if (!Character.isLetterOrDigit(chars[i])) {
                    i++;
                    j++;
                } else if (!Character.isLetterOrDigit(chars[j])) {
                    j--;
                } else {
                    i++;
                }
            }

        }

        return true;
    }
}

