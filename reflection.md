Use this file to record your reflection on this assignment.

What are your initial impressions of how `Unit Testing` affects your programming?
- I thought it was really useful. From the concept to the implementation makes a lot of sense why it's used.

What did you do?

In my tests, I focused on verifying the functionality of the `Engine`, `Car`, `Passenger`, and `Train` classes. I wrote unit tests to ensure that each component behaved as expected. For example:
- I tested the initialization of the `Engine` and verified that its fuel type and fuel level were set correctly.
- I checked that the `Engine`'s `go()` method reduced the fuel level as intended.
- For the `Car` class, I tested adding and removing passengers, ensuring the seat count updated correctly.
- I verified that a `RuntimeException` was thrown when trying to board a full car.
- For the `Passenger` class, I tested the ability to board and leave cars.
- For the `Train` class, I tested the car count, passenger count, and the ability to retrieve specific cars. I also ensured that invalid car indices threw an `IndexOutOfBoundsException`.
- Finally, I tested the `printManifest()` method to confirm that it printed the correct output, capturing the console output and comparing it to the expected result.

These tests helped me identify and fix issues in my code, ensuring that each component worked as intended.

What worked, what didn't, what advice would you give someone taking this course in the future?
To answer this all in one, here is the advice with do's and dont's:
* Do's
    - Google search as much as you feel a need to. You might learn new methods that are useful or stumble across the exact same question someone has asked online 6 years ago. The Internet is truly magical.
    - Start the assignment ASAP
    - Ask for help
    
* Dont's
    - Sit there for hours waiting for your Eureka moment – It won't happen.
    - Procrastinate and wait until the day before to start. If anything, starting earlier gives you a lighter workload. Just read through the README and do one thing!
    - Ignore sleep and rest. 