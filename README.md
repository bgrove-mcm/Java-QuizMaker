# Java-QuizMaker

## Overview

Java Quiz Maker is a Java-based quiz management system designed for students and instructors. The application provides role-based functionality for taking quizzes, managing questions, registering students, and analyzing quiz performance.

The system stores user, question, answer, and quiz performance information using text files and Java data structures.

## Features

### Student Functionality

* Student authentication
* Random selection of 10 quiz questions
* True/False quiz questions
* Quiz completion and scoring
* Individual quiz performance reports

### Instructor Functionality

* Register new students
* Add new quiz questions
* View quiz performance statistics
* Manage the test question bank

### Performance Statistics

The system analyzes quiz results and calculates:

* Lowest score
* Highest score
* Average score
* Total quizzes taken
* Number of unique students
* Pass rate
* Individual quiz completion reports

## Technologies

* Java
* Object-Oriented Programming
* HashMap
* HashSet
* Linked Lists
* File I/O
* Data Processing
* Statistical Calculations

## Project Structure

```text
src/
    Java source files

data/
    User information
    Question bank
    Answer data

screenshots/
    Application screenshots
```

## Data Structures

The project uses several Java data structures to organize and process information.

**HashMap**
Used to organize information associated with students and quiz results.

**HashSet**
Used to identify unique students and prevent duplicate student records when calculating statistics.

**Linked List**
Used to manage quiz-related information within the application.

## Data Analysis

Quiz results are processed to generate performance statistics. The application calculates average, minimum, and maximum scores and uses quiz results to determine the overall pass rate.

These features demonstrate how programming can be used to collect, organize, process, and interpret data.

## What I Learned

This project strengthened my understanding of Java programming, object-oriented design, file processing, data structures, and statistical analysis. It also provided experience designing a system that collects data and converts it into meaningful performance information.
