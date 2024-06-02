// Customer object
let customers = [
    {
        'id': 001,
        'f_name': 'Abby',
        'l_name': 'Thomas',
        'gender': 'M',
        'married': true,
        'age': 32,
        'expense': 500,
        'purchased': ['Shampoo', 'Toys', 'Book']
    },
    {
        'id': 002,
        'f_name': 'Jerry',
        'l_name': 'Tom',
        'gender': 'M',
        'married': true,
        'age': 64,
        'expense': 100,
        'purchased': ['Stick', 'Blade']
    },
    {
        'id': 003,
        'f_name': 'Dianna',
        'l_name': 'Cherry',
        'gender': 'F',
        'married': true,
        'age': 22,
        'expense': 1500,
        'purchased': ['Lipstik', 'Nail Polish', 'Bag', 'Book']
    },
    {
        'id': 004,
        'f_name': 'Dev',
        'l_name': 'Currian',
        'gender': 'M',
        'married': true,
        'age': 82,
        'expense': 90,
        'purchased': ['Book']
    },
    {
        'id': 005,
        'f_name': 'Maria',
        'l_name': 'Gomes',
        'gender': 'F',
        'married': false,
        'age': 7,
        'expense': 300,
        'purchased': ['Toys']
    }
];

// return only the senior citizens from the list
// senior age >= 60
let seniorCitizens = customers.filter((customer) => customer.age >= 60)
                                    .map(senior => (`${senior.f_name} ${senior.l_name}`));
// console.log(seniorCitizens);

let customerNames = customers.map(customer => {
    let title = customer.gender === 'M' ? 'Mr.' : 'Miss.';
    if(customer.gender === 'F' && customer.married) {
        title = 'Mrs.'
    }
    return {
        ...customer, 'fullname': `${title} ${customer.f_name} ${customer.l_name}`
    }
})
// console.log(customerNames)

/**
 * Get average age of customer who purchased books
 * */
let totalBookReader = 0;
let averageBookAge = customers.reduce((acc, curr) => {
    if(curr.purchased.includes('Book')){
        acc += curr.age
        totalBookReader++
    }
    return acc;
}, 0);
// console.log(Math.floor(averageBookAge / totalBookReader));

let ageLessThanTen = customers.some(customer => customer.age < 10);
// console.log(`Age less than 10 : ${ageLessThanTen}`);

/**
 * Find youngest customer
 * */
let youngestAge = 10
customers.forEach(customer => {
    if(customer.age < youngestAge){
        youngestAge = customer.age
    }
})
youngestCustomer = customers.find(customer => customer.age === youngestAge);
// console.log(youngestCustomer);

// find customer without any purchases
let windowShopper = customers.every(customer => customer.purchased.length === 0)
// console.log(windowShopper)

// total amount spent by married customers
let totalCost = customers.reduce((acc, curr) => {
    if(curr.married === true){
        acc += curr.expense
    }
    return acc;
}, 0)
console.log(totalCost)