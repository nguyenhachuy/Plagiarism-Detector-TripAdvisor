# Plagiarism-Detector-TripAdvisor
Take home challenge

# Usage instructions

1.  Compile the program: ```javac *.java``` . 
2.  Run the program (with the provided example files): ```java PlagiarismDetector test-dict.txt test-file1.txt test-file2.txt``` . 
3.  Run the example tests: ```java TestRunner ``` . 

# Important assumptions
1.  Words in files are considered as is, and no preprocessing to remove unwanted characters. So ".jog" is a valid word . 
2.  Each word is delimited by a space character . 
3.  Dependencies:
    ```openjdk 11.0.1 2018-10-16
    OpenJDK Runtime Environment 18.9 (build 11.0.1+13)
    OpenJDK 64-Bit Server VM 18.9 (build 11.0.1+13, mixed mode)```

# Note
This project was done in limited time due to important exams. So it is not perfect and I would do the following if I have time:

-  Improve naming convention . 
-  Write more tests/Handle edge cases . 
-  Optimize space and time of scoring strategy . 
