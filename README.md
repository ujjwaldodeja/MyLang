# PP Final Project: MyLang compiler
We have written a compiler using an ANTLR parser that compiles source code into SPRIL instructions for a Sprockell processor.
## Setup:
In IntelliJ, we had to mark the gen folder as Generated Sources Root, and mark src/main/java as Sources Root.

You can now run ut.pp.CompileMyLang to compile the program. The program should then immediately run in Haskell and display its output on the console.

In case this does not work, you can run prog.hs (run "stack runhaskell prog.hs" from console in working directory src/main/java/ut/pp)

In ut.pp.CompileMyLang, you can modify String file in the Main method to run another file from the testFiles directory.

## Note:
We changed Sprockell/BasicFunctions.hs to increase the size of shared memory from 8 to 12. Otherwise, our banking program does not work.