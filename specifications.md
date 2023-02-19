# Mini-Mendeley

**[Mendeley](https://www.mendeley.com/)** is a paper management software.
It supports users to organize the papers and attach the comments or labels to the papers.
In this project, you are required to implement your own paper management system, named Mini-Mendeley.
Similar to **Mendeley**, it should support the following functionalities:

- Support the creation of user accounts.
- Support the users to upload and download papers from/into bib files.
- Support the users to add labels to a specific paper according to paper ID.
- Support the users to add comments to a specific paper or another comment according to paper ID or comment ID, respectively.

In what follows, we provide more concrete specifications for the objects and their methods.

## Person

### Fields and methods

A `Person` object has two fields, namely `id: String` and `name: String`.
You should set both of them to be private and implement the methods to get or set their values.
Also, you should implement a proper constructor according to your need.

### Subclasses

The class `Person` has two subclasses, namely `Researcher` and `User`.

- The class `Researcher` has one more field than the class `Person`, i.e., `papers: ArrayList<Paper>`.
Here, `Paper` is one kind of the resources, and we will introduce it later.

- The class `User` has three more fields than the class `Person`, namely `registerDate: Date`, `userComments: ArrayList<Comment>`, and `userLabels: ArrayList<Label>`.

Similar to the class `Person`, all the fields of the classes `Researcher` and `User` should be private.

You need to implement the above two classes as the subclasses of `Person`. Specifically, you should implement their constructors if they are not implemented. Besides, you may need to define more methods according to your needs during the development.

## Resource

There are three kinds of resources in Mini-Mendeley, namely `Comment`, `Paper`, and `Label`.
- `Paper`: The main resources in our system.
- `Comment`: A comment can be attached to a paper or a comment.
- `Label`: The label of a paper.

To convienence your implementation,
we have provide the major implementation of the three classes.
For more detailed explanation of the fields and methods,
you can refer to the class files under the directory `src/main/java/hk.ust.comp3021/resource`.

## Action

An `Action` class contains four fields, namely "time: date" (indicating creation date), "id: String" (indicating the ID of the action), `action_type: ActionTyple,(indicating the type of the action). You can refer to the file Action for more detail.

Apart from the class `Action`, there are five another subclasses of `Action`, which indicate five typicl operations over the paper base. Similar to `Person` and it subclasses, the five classes also need to be implemented to support the original functionality of Mini-Mendeley.

## Utils

To support the paper uploading/downloading and the user registeration, you are also required to define the following three classes as follows:

- `BibParser`: Parse a given bib file and extract the paper objects from the bib file.
If the parsing succeeds, you should set `isErr` to `false`.
Otherwise, it should be set to `true`.
The example of the bib file is `resources/bibdata/PAData.bib`.
For some paper records, the fields in the `Paper` classes do not have the corresponding attributes, so you need to set them to be null.
When you parse the file, you can assume that all the bib files in our testcases have the same format as our example bib file.

- `BibExporter`: Dump given papers to a bib file. 
Similar to `BibParser`, you should set `isErr` to `false`/`true` if the exporting succeeds/fails.
The format of the exported bib file should be the same as our example bib file.

- `UserRegister`: Register a user. 
The method `register` should return a user with the specified user name, the assigned user ID, and a registeration time.

## MiniMendeley and MiniMendeleyEngine

The class `MiniMendeley` is the main class of our system,
which wraps a MiniMendeleyEngine inside.
After intializing all the fields, it loads all the papers in the default bib file to `paperBase`
by invoking `populatePaperBaseWithDefaultBibFile`,
which depends on the `BibParser` you implemented.
Then the method `userInterface` proccesses the commands in the console and invoke the corresponding handlers.

Initially, a user account should be created,
so you need to create a new user account and add it to `users`.
When a new user account is created in the middle of the execution,
the current user account will be overwritten, 
i.e., the newly created user account is the one who performs the subsequent operations.
All the resources (comments, labels, and papers) should be added the corresponding fields of `MiniMendeley`,
and meanwhile,
the fields of these resources should be updated, e.g., the fields `labels` and `comments` of a `Paper` object.
Notably, the major workflow of MiniMendely has been provided.

## What YOU need to do

- Fully define the class `Action` and its subclasses, including their constructors and other necessary methods.
- Fully define the class `Person` and its subclasses, including their constructors and other necessary methods.
- Fully implement `BibExporter` and `BibParser`
- Fully define the class `UserRegister`.
- Fully implement the methods in the class `MiniMendeleyEngine`
  - processUserRegister
  - processAddCommentAction
  - processAddTagAction
  - processDownloadPaperAction
  - processUploadPaperAction
  - processSearchPaperAction

## Tips

### Tip for Implementation

You can follow the comments of the methods to be implemented.
We have provided detailed description and even several hints for the methods.

To convenience the testing and debugging, you can just run the `main` method of `MiniMendely`
to interact with the system.
An example is as follows:

````
Begin to start MiniMendeley...
----------------------------------------------------------------------
MiniMendeley is running...
Initial paper base has been populated!
----------------------------------------------------------------------
Please select the following operations with the corresponding numbers:
0: Register an account
1: Search papers
2: Upload papers
3: Download papers
4: Add labels
5: Add comments
6: Exit
----------------------------------------------------------------------
> 1
You need to register an account first.
----------------------------------------------------------------------
Please select the following operations with the corresponding numbers:
0: Register an account
1: Search papers
2: Upload papers
3: Download papers
4: Add labels
5: Add comments
6: Exit
----------------------------------------------------------------------
> 0
Please enter your name.
> Chengpeng
Create the account with the name: Chengpeng
Account created!
----------------------------------------------------------------------
Please select the following operations with the corresponding numbers:
0: Register an account
1: Search papers
2: Upload papers
3: Download papers
4: Add labels
5: Add comments
6: Exit
----------------------------------------------------------------------
> 2
Please specify the absolute path of the bib file:
> /Users/xiangqian/Documents/TA/comp3021_charles_2023S/COMP3021-2023Spring-PA1/src/main/resources/bibdata/PAUploadData1.bib
Succeed! The uploaded papers are as follows:
Jones1979
Chase1990
Hutchison1973
----------------------------------------------------------------------
Please select the following operations with the corresponding numbers:
0: Register an account
1: Search papers
2: Upload papers
3: Download papers
4: Add labels
5: Add comments
6: Exit
----------------------------------------------------------------------
> 3
Please specify the absolute path of the bib file:
/Users/xiangqian/Documents/TA/comp3021_charles_2023S/COMP3021-2023Spring-PA1/src/main/resources/bibdata/PADownloadData1.bib
Please enter the paper ID line by line and end with END
> Jones1979
> Chase1990
> END
@article{Jones1979,
abstract = {In [12] the authors introduced the concept of binding time optimization and presented a series of data flow analytic methods for determining some of the binding time characteristics of programs. In this paper we extend that work by providing methods for determining the class of shapes which an unbounded data object may assume during execution of a LISP-like program, and describe a number of uses to which that information may be put to improve storage allocation in compilers and interpreters for advanced programming languages.We are concerned chiefly with finding, for each program point and variable a finite description of a set of graphs which includes all the shapes of values the variable could assume at that point during the execution of a program. If this set is small or regular in structure, this information can be used to optimize the program's execution, mainly by use of more efficient storage allocation schemes.In the first part we show how to construct from a program without selective updating a tree grammar whose nonterminals generate the desired sets of graphs; in this case they will all be trees. The tree grammars are of a more general form than is usually studied [8, 19], so we show that they may be converted to the usual form. The resulting tree grammar could naturally be viewed as a recursive type definition [11] of the values the variables may assume. Further, standard algorithms may be employed to test for infiniteness, emptiness or linearity of the tree structure.In the second part selective updating is allowed, so an alternate semantics is introduced which more closely resembles traditional LISP implementations, and which is equivalent to the tree model for programs without selective updating. In this model data objects are directed graphs. We devise a finite approximation method which provides enough information to detect cell sharing and cyclic structures whenever they can possibly occur. This information can be used to recognize when the use of garbage collection or of reference counts may be avoided.The work reported in the second part of this paper extends that of Schwartz [17] and Cousot and Cousot [7]. They have developed methods for determining whether the values of two or more variables share cells, while we provide information on the detailed structure of what is shared. The ability to detect cycles is also new. It also extends the work of Kaplan [13], who distinguishes only binary relations among the variables of a program, does not handle cycles, and does not distinguish selectors (so that his analysis applies to nodes representing sets rather than ordered tuples).},
author = {Neil D. Jones and Steven S. Muchnick},
doi = {10.1145/567752.567776},
journal = {Conference Record of the Annual ACM Symposium on Principles of Programming Languages},
title = {Flow analysis and optimization of LISP-like structures},
year = {1979},
}
@article{Chase1990,
abstract = {Note: \{OCR\},
author = {David R. Chase and Mark Wegman and F. Kenneth Zadeck},
doi = {10.1145/93542.93585},
journal = {Proceedings of the ACM SIGPLAN Conference on Programming Language Design and Implementation (PLDI)},
title = {Analysis of pointers and structures},
year = {1990},
}

Succeed! The downloaded paper is stored in your specified file.
----------------------------------------------------------------------
Please select the following operations with the corresponding numbers:
0: Register an account
1: Search papers
2: Upload papers
3: Download papers
4: Add labels
5: Add comments
6: Exit
----------------------------------------------------------------------
> 4
Please specify the paper ID:
> Chase1990
Please specify the label
> shape analysis
Succeed! The label is added.
----------------------------------------------------------------------
Please select the following operations with the corresponding numbers:
0: Register an account
1: Search papers
2: Upload papers
3: Download papers
4: Add labels
5: Add comments
6: Exit
----------------------------------------------------------------------
> 6
````


### Top for Submission

Lastly, it should be noting that your code will be tested by running our testcases rather than testing via the console manually.
Therefore, you should make sure that: 

- (1) your code can be complied successfully.
- (2) your implementation can pass the public testcases we provided in `src/test`.
However, passing all the public testcases does not mean that you can obtain the full mark for the PA.
  We will also provide many additional testcases as the hidden ones,
  which are different from the ones we provided in this skeleton.
- (3) your implementation should not yield too many errors when running `./gradlew checkstyleMain`.

If you have any question on the PA1, please email Chengpeng Wang via cwangch@cse.ust.hk


