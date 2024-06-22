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
    return allClasses.filter((classname) => obj[classname] >= 60);
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

console.log(countVowels("aislfe dial"));
console.log(countVowels("    "));
console.log(countVowels("abcdefg aaay"));