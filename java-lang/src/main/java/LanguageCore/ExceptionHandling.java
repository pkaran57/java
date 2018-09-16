package LanguageCore;

/*
A Java exception is an object that describes an exceptional (that is, error) condition
that has occurred in a piece of code. When an exceptional condition arises, an object
representing that exception is created and thrown in the method that caused the error.

Exceptions can be generated by the Java run-time
system, or they can be manually generated by your code. Exceptions thrown by Java relate
to fundamental errors that violate the rules of the Java language or the constraints of the
Java execution environment. Manually generated exceptions are typically used to report
some error condition to the caller of a method.

imp: Throwable and any subclass of Throwable that is not also a subclass of either RuntimeException or Error are regarded as checked exceptions.

Onenote: Exception Handling
 */
public class ExceptionHandling {

    /*
    Any exception that is not caught by your program will ultimately be processed by
    the default handler. The default handler displays a string describing the exception, prints a
    stack trace from the point at which the exception occurred, imp: and terminates the program.

    The stack trace will always show the sequence of method invocations that led up to the error.
    */
    public static void noHandler(){

        int i = 1 / 0;
    }

    /*
     Once an exception is thrown, program control transfers out of the try block into the catch block.
     Once the catch statement has executed, program control continues with the next line in the program following the entire try /
     catch mechanism.

     The goal of most well-constructed catch clauses should be to resolve the exceptional condition and then continue on as if the
     error had never happened
     */
    public static void tryCatch(){

        try{    // monitor a block of code for exceptions thrown
            int i = 121/0;

            //demonstrated below is a multi catch
            //The multi-catch feature allows two or more exceptions to be caught by the same catch clause.
            //imp: Each multi-catch parameter is implicitly final. (You can explicitly specify final,
            //if desired, but it is not necessary.) Because each multi-catch parameter is implicitly final, it
            //can’t be assigned a new value.
        }catch(ArithmeticException | ArrayIndexOutOfBoundsException e){  //catches exceptions raised in corresponding try block

            //Throwable overrides the toString( ) method (defined by Object) so that it returns a string
            //containing a description of the exception.
            System.err.println("From tryCatch(), caught ArithmeticException or ArrayIndexOutOfBoundsException : " + e);
        }catch(Exception e){

            System.err.println("From tryCatch(), caught Exception : " + e);
        }
    }

    /*
    When an exception is thrown, each catch statement is inspected
    in order, and the first one whose type matches that of the exception is executed. After one
    catch statement executes, the others are bypassed, and execution continues after the try / catch block
     */
    public static void multipleCatch(){

        try{    // monitor a block of code for exceptions thrown
            int i = 121/0;
        }catch(IndexOutOfBoundsException e){  //catches exceptions raised in corresponding try block

            System.err.println("From multipleCatch(), ArithmeticException : " + e);
        }catch(Exception e){

            System.err.println("From multipleCatch(), Exception : " + e);
        }
    }

    /*
    If an inner try statement does not have a catch handler for a particular exception,
    the stack is unwound and the next try statement’s catch handlers are inspected for a match.
    This continues until one of the catch statements succeeds, or until all of the nested try
    statements are exhausted. If no catch statement matches, then the Java run-time system
    will handle the exception

    Nesting of try statements can occur in less obvious ways when method calls are involved.
    For example, you can enclose a call to a method within a try block. Inside that method is
    another try statement. In this case, the try within the method is still nested inside the outer
    try block, which calls the method.
     */
    public static void nestedTry(String[] args){

        try {
            int a = args.length;

            /* If no command-line args are present, the following statement will generate a divide-by-zero exception. */
            int b = 42 / a;
            System.out.println("a = " + a);

            try { // nested try block

                /* If one command-line arg is used, then a divide-by-zero exception will be generated by the following code. */
                if(a==1) a = a/(a-a); // division by zero

                /* If two command-line args are used, then generate an out-of-bounds exception. */
                if(a==2) {
                    int c[] = { 1 };
                    c[42] = 99; // generate an out-of-bounds exception
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("From nestedTry(), Array index out-of-bounds: " + e);
            } catch(NullPointerException e){
                System.out.println("From nestedTry(), NullPointerException: " + e);
            }
        } catch(ArithmeticException e) {
            System.out.println("From nestedTry(), Divide by 0: " + e);
        }
    }

    /*
    throw ThrowableInstance;
    Here, ThrowableInstance must be an object of type Throwable or a subclass of Throwable

    Many of Java’s built-in run-time exceptions have at least two constructors: one with no parameter and one that
    takes a string parameter. When the second form is used, the argument specifies a string that
    describes the exception. This string is displayed when the object is used as an argument to
    print( ) or println( ). It can also be obtained by a call to getMessage( ), which is defined by Throwable
     */
    public static void throwDemo() {

        try {
            throw new NullPointerException("demo");     // throw a newly created exception
        } catch(NullPointerException e) {
            System.out.println("Caught inside throwDemo.");
            throw e; // rethrow the exception
        }
    }

    /*
    imp: If a method is capable of causing an exception that it does not handle, it must specify this
    behavior so that callers of the method can guard themselves against that exception.

    This is necessary for all exceptions, except those of type Error or RuntimeException, or any of their subclasses.
    All other exceptions that a method can throw must be declared in the throws clause. If they are not, a compile-time
    error will result.
     */
    public static void throwsDemo() throws IllegalAccessException {

        System.out.println("Inside throwOne.");
        throw new IllegalAccessException("demo");
    }

    public static void tryWithResource(String zipFileName, String outputFileName) throws java.io.IOException {

        java.nio.charset.Charset charset = java.nio.charset.StandardCharsets.US_ASCII;
        java.nio.file.Path outputFilePath = java.nio.file.Paths.get(outputFileName);

        // A try-with-resources statement can have catch and finally blocks just like an ordinary try statement. In a try-with-resources statement,
        // any catch or finally block is run after the resources declared have been closed.
        //imp: Any object that implements java.lang.AutoCloseable, which includes all objects which implement java.io.Closeable, can be used as a resource.
        // Note that the close methods of resources are called in the opposite order of their creation.
        try (
                java.util.zip.ZipFile zf =
                        new java.util.zip.ZipFile(zipFileName);
                java.io.BufferedWriter writer =
                        java.nio.file.Files.newBufferedWriter(outputFilePath, charset)     // no ; needed for last one
        ) {
            // do a few things
        }
    }

    /* *************************************************
    TOPIC : Finally

    Onenote: Finally under Exception Handling
     *************************************************/

    /* *************************************************
    TOPIC : Creating custom exceptions

    Onenote: Creating custom exceptions under Exception Handling
     *************************************************/

    /* *************************************************
    TOPIC : Chained Exceptions

    Onenote: Chained Exceptions under Exception Handling
     *************************************************/
}
