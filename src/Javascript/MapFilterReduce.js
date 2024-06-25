// You are given a dictionary/hash/object containing some
// languages and your test results in the given languages.
// Return the list of languages where your test score is at
// least 60, in descending order of the scores.
//     Note: the scores will always be unique (so no duplicate values)
// {"Java" => 10, "Ruby" => 80, "Python" => 65}   -->  ["Ruby", "Python"]
// {"Hindi" => 60, "Dutch" => 93, "Greek" => 71}  -->  ["Dutch", "Greek", "Hindi"]
// {"C++" => 50, "ASM" => 10, "Haskell" => 20}    -->  []

// given an of different classes and scores, return a list
// of classes where the score is greater than or equal to 60
const obj1 = { Java: 10, Ruby: 80, Python: 65};
const obj2 = {Hindi: 60, Dutch: 93, GreeK: 71};
const obj3 = {'C++': 50, ASM:10, Haskell: 20};
/*
* question
* - will there be classes where the score is never greater than 60?
* - How many max classes can there be?
* - do you have any time or space constraints?
*
* examples
* - { Java: 10, Ruby: 80, Python: 65}; => {Ruby, Python}
* - {Hindi: 60, Dutch: 93, GreeK: 71}; => {Hindi, Dutch, Greek}
* - {'C++': 50, ASM:10, Haskell: 20}; => {}
*
* methods
* - can use map to return an array with only values of the classname that has score >= 60
* but map returns the same length of the original array which is not the intended outcome of this
* problem - its good for transforming each value in an array rather than filtering
* - filter creates a new array that adds only the elements of the original array
* that satisfy a condition in the callback function
* - iterate over with a for loop + create a new array to store results
*
* plan
* - filter over the original array and add it to a resulting array
*
* review
* - getting all the class names with Object.Keys() then filtering through
* each class name and returning only the class names that have scores >= 60
* */
const getPassedClasses = (obj) => {
    let allClasses = Object.keys(obj);
    let passClasses = allClasses.filter((classname) => obj[classname] >= 60);
    let sortedPassedClasses = passClasses.sort((a, b) => obj[b] - obj[a]);
    return sortedPassedClasses;
}
// console.log(getPassedClasses(obj1));
// console.log(getPassedClasses(obj2));
// console.log(getPassedClasses(obj3));

/*
* Return the number (count) of vowels in the given string.
We will consider a, e, i, o, u as vowels for this Kata (but not y).
The input string will only consist of lower case letters and/or spaces.
*
* questions
* - can the input string be empty?
* - what is the max length of the input string?
* - are there any space/time constraints?
*
* examples
* - s = "aislfe dial" => 5
* - s = "     " => 0
* - s = "abcdefg aaay" => 5
*  methods
* - iterate over each letter and check if it is any of the vowels
* - reduce function to iterate over each letter and if the value is a vowel than track with reducer
* - filter can be used to filter out non vowel letters from the string and return the length of the filtered arr
* - forEach to iterate over the array and keep a count of total vowels
*
* plan
* - split the string by spaces
* - use a reducer to with acc set to 0
* - initialize a vowel array
* - in reduce callback -> check if the current letter is in the vowel array
* - if it is then increment the acc otherwise continue
* return the reducer's result
*
* */

const countVowels = (str) => {
    let strArr = str.split('');
    let vowels = ['a', 'e', 'i', 'o', 'u'];
    return strArr.reduce((acc, letter) => {
        if(vowels.includes(letter)){
            return acc + 1;
        }
        return acc;
    }, 0)
}

// console.log(countVowels("aislfe dial"));
// console.log(countVowels("    "));
// console.log(countVowels("abcdefg aaay"));

/*
*John has invited some friends. His list is:

s = "Fred:Corwill;Wilfred:Corwill;Barney:Tornbull;Betty:Tornbull;Bjon:Tornbull;Raphael:Corwill;Alfred:Corwill";
Could you make a program that makes this string uppercase gives it sorted in alphabetical order by last name.
When the last names are the same, sort them by first name. Last name and first name of a guest come in the result between parentheses separated by a comma.
So the result of function meeting(s) will be:

"(CORWILL, ALFRED)(CORWILL, FRED)(CORWILL, RAPHAEL)(CORWILL, WILFRED)(TORNBULL, BARNEY)(TORNBULL, BETTY)(TORNBULL, BJON)"
It can happen that in two distinct families with the same family name two people have the same first name too.
*
* given a string of names, transform the string to all uppercase letters
* then sort the string by last names and tie-breakers with first names
* the return value should be a string with () surrounding each name with a comma between the names
*
* questions
* - how many names max can there be?
* - can names have punctuation or spaces
* - will there be empty strings for names?
*
* - the resulting string should wrap each name around () and separate the last and first name with a comma
* examples
* - "ela:huy;bao:din;min:din" => "(din, bao)(dinh, min)(huy, ela)"
* - "lam:mo;lim:ron;kim:ra" => "(lam, mo)(lim, rom)(kim, ra)"
* */

s = "Fred:Corwill;Wilfred:Corwill;Barney:Tornbull;Betty:Tornbull;Bjon:Tornbull;Raphael:Corwill;Alfred:Corwill";

const guessList = (s) => {
    s = s.toUpperCase();
    let names = s.split(";");
    let sortedNames = names.sort((a, b) => {
        let aName = a.split(":");
        let bName = b.split(":");
        if(aName[1] < bName[1]){
            return -1;
        } else if (aName[1] > bName[1]){
            return 1;
        } else if (aName[0] < bName[0]){
            return -1;
        } else if (aName[0] > bName[0]){
            return 1;
        }
        return 0;
    })
    let formattedList = sortedNames.map((name) => {
        let fullName = name.split(":");
        return `(${fullName[1]},${fullName[0]})`;
    })
    return formattedList.join('');
}
let result = guessList(s);
console.log(result)



