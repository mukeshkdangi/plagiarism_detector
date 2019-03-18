# plagiarismdetector

> Plagiarism Detection
Your assignment is to write a command-line program that performs plagiarism detection using a N- tuple comparison algorithm allowing for synonyms in the text. Your program should take in 3 required arguments, and one optional.  In other cases such as no arguments, the program should print out usage instructions.

1. file name for a list of synonyms
2. input file 1
3. input file 2
4. (optional) the number N, the tuple size.  If not supplied, the default should be N=3.

The synonym file has lines each containing one group of synonyms.  For example a line saying &quot;run sprint jog&quot; means these words should be treated as equal.The input files should be declared plagiarized based on the number of N-tuples in file1 that appear in
file2, where the tuples are compared by accounting for synonyms as described above.  For example, the text &quot;go for a run&quot; has two 3-tuples, [&quot;go for a&quot;, &quot;for a run&quot;] both of which appear in the text &quot;go for a jog

The output of the program should be the percent of tuples in file1 which appear in file2.  So for the above example, the output would be one line saying &quot;100%&quot;.  In another example, for texts &quot;go for a run&quot; and &quot;went for a jog&quot; and N=3 we would output &quot;50%&quot; because only one 3-tuple in the first text appears in the second one.

``Example inputs from above:
syns.txt: run sprint jog
file1.txt: go for a run
file2.txt: go for a jog `
Sample output for above inputs:
  100%

## PlagiarismDetector Project readME

Language Used Java 1.8

## Naming : 
file1 : Base File
file2 : Comparison file
file3 : Synonym file
N     : tuple size

## Design Decisions:
> 1. PlagiarismDetector is the controller of the whole task ans responsible for following subtasks:
	i)  Pre-Processing (user input info object and validation)
	ii) Processing 
		- Reading all three files and generating N-tuples <tuples_of_size_N> from first two files.
		- Reading synonym file and generating synonym map<word:sysnonym_set> for each word
		-  
	iii) Post Processing: Here, we are checking each N-Tuple from base file to comparison file and a matching is considered iff each word/synonyms from N-Tuple-1(file1) is equal to N-Tuple-2(file2) word/synonyms. If words are not matching then we are taking intersection of two Synonym sets of word from N-Tuple-1 and word from N-Tuple-2. if intersection is greater than one that mean those two words are synonym. 
> 2. Clear segregation/grouping of files: Services, POJOs, Exception, Interfaces, Constants etc.
> 3. Exception Handling: We are throwing custom errors from each (pre-processing, processing and post-processing) and catching them in PlagiarismDetector controller to print the error code and error description. 

## Running the code:

> Compile : ```Java 
javac *java 
``` or Ctrl + B(Win) or Command + B (Mac OS)  
Run     : ```Java
java <main_function()_file_with_package_structure> <Synonym_file> <file_1_path> <file_2_path> [tupe_size]
```
 

> For example: ```Java
java com.tripadvisor.utils.plagiarismdetector.PlagiarismDetector /Users/mukesh/Desktop/syns.txt /Users/mukesh/Desktop/file1.txt  /Users/mukesh/Desktop/file2.txt 3
```

## Assumptions:
> 1. File content is valid i.e. special characters(',', '.', ';' etc) should be removed prior to building N-Tuple. For example "run for hours." and "run for hours" wouldn't match unless we remove "comma(,) from tuple 1"

> 2. File size fits into RAM for processing.
> 3. Two Tuple(from file1 and file2 respectively) are considered plagiarised only iff words or their corresponding synonyms are equal. 
> 4. If requested tuple size is more than length is line a file, then ignoring that line for plagiarism check.


## Future Improvements: 
> This code can be easily extended to create RESTful API where we take input(in Spring Boot @RequestBody(POST Request) or @PathVariable(@Get Request) and return the similarity percentage and tuples where content is matched. 

> 1. We can use logger(log4j, slf4j) to better logging like WARN, ERROR etc.
> 2. We can use lombok project annotation(@Getter, @Setter, @Data etc) for better code readability in POJOs.
> 3. Take into consideration of stop words and remove them before generating NTuple or synonym map
> 4. Use of Spring boot framework for dependency injection(@Autowired) and more code decoupling.
> 5. Takes care of files which are very big(GBs) by some concepts like pagination apart from using streams than a buffer.  
> 6. Take more than one file as base file (real word scenarios like web has millions of URL/files) and then compare a new against our web crawled files. 
> 7. Other language support where we have  different style(right-to-left).
