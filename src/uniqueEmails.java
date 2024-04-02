import java.util.HashSet;
import java.util.Set;

public class uniqueEmails {
    public static void main(String[] args){
        String[] emailList = new String[]{"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com",
                "testemail+david@lee.tcode.com"};
        int result = numUnqiueEmails(emailList);
        System.out.println(result);

        emailList = new String[]{"a@leetcode.com","b@leetcode.com","c@leetcode.com"};
        result = numUnqiueEmails(emailList);
        System.out.println(result);

        emailList = new String[]{"test.email+alex@leetcode.com", "test.email@leetcode.com"};
        result = numUnqiueEmails(emailList);
        System.out.println(result);
    }

    /**
     *
     * Every valid email consists of a local name and a domain name, separated by the '@' sign.
     * Besides lowercase letters, the email may contain one or more '.' or '+'.
     *
     * For example, in "alice@leetcode.com", "alice" is the local name, and "leetcode.com" is the domain name.
     * If you add periods '.' between some characters in the local name part of an email address, mail sent
     * there will be forwarded to the same address without dots in the local name. Note that this rule
     * does not apply to domain names.
     *
     * For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.
     * If you add a plus '+' in the local name, everything after the first plus sign will be ignored.
     * This allows certain emails to be filtered. Note that this rule does not apply to domain names.
     *
     * For example, "m.y+name@email.com" will be forwarded to "my@email.com".
     * It is possible to use both of these rules at the same time.
     *
     * Given an array of strings emails where we send one email to each emails[i],
     * return the number of different addresses that actually receive mails.
     *
     * RULES
     * 1. in local name, '.' wil be ignored
     * 2. in local name, '+' will cause any char after to be disregarding (not including domain name)
     *
     * PLAN
     * - set will track the unique local names
     * Use a helper function to filter the local names by the given rules
     * split the string by the @ symbol and save the domain name
     * - check if
     * - 1. any '.' is present then replace with "" (empty space)
     * - 2. any '+' is present then substring from the 0 to the first + occurence
     * - return the clean string + domain name
     * add the parsed email to the set
     * now all unqiue emails are added to the set
     * return the set size
     */
    public static int numUnqiueEmails(String[] emails){
        Set<String> set = new HashSet<>();
        for(String s : emails){
            String parsedEmail = parseEmail(s);
            System.out.println(parsedEmail);
            set.add(parsedEmail);
        }

        return set.size();

    }

    private static String parseEmail(String email){
        String[] emailArr = email.split("@");
        emailArr[0] = emailArr[0].replace(".", "");
        int plusIndex = emailArr[0].indexOf('+');

        if(plusIndex != -1){
            emailArr[0] = emailArr[0].substring(0, plusIndex);
        }
        return emailArr[0] + "@" + emailArr[1];
    }
}
