# LoadedCalculator
The goal of this project is to create a calculator that has all the functionality of your average 
phone calculator, but also has some more complicated operations stored within a catalog.

# Supported Operations
Here is the list of operations this calculator currently supports:
- Addition
- Subtraction
- Multiplication
- Division
- Complex Number Addition
- Complex Number Subtraction
- Complex Number Multiplication
- Complex Number Division
- Complex Number Remainder
- Complex Number Norm
- GCD
- LCM
- Prime Checking (Up to 2^31 - 1)
- Exponential Equations (2^3)
- Base 10 Logarithm
- Natural Logarithm
- Base N Logarithm
- Modulo
- Absolute Value
- Sine
- Cosine
- Tangent
- Factoring Quadratic Expressions
- Linear Combination of two numbers

# Classes and Interfaces
## Model
The Model has 10 classes and interfaces that describe the inner workings of the calculator. Here is a list of the classes
and what they contain:
- SimpleIntCalculator: This interface describes a calculator that only functions with integers. It has four
operations: add, subtract, multiply, and divide. 
- SimpleIntCalculatorImpl: This class is an implementation of the SimpleIntCalculator interface.
It implements all four operations from the interface. 
- ISimpledDoubleCalculator: This interface is the backbone of every other model, except the SimpleIntCalculator. All the other interfaces extend and build on this one. This interface is nearly
identical to the SimpleIntCalculator, except each of the four operations are all on doubles.
- SimpleDoubleCalculator: This class is an implementation of the ISimpleDoubleCalculator interface.
  It implements all four operations from the interface. 
- IStoringCalculator: This interface represents a calculator that has all the functionality of the 
ISimpleDoubleCalculator, with the added functionality of being able to store the answers of previous
calculations. The only method this interface has is getAns() which will return the answer of the 
previous calculation.
- StoringCalculator: This class is an implementation of the IStoringCalculator interface. It implements getAns and
the four operations from the ISimpleDoubleCalculator interface. This implementation uses a stack
to store and retrieve the answers. It also has a ISimpleDoubleCalculator to use as a delegate.
- ISetPrecisionCalculator: This interface represents a calculator that has all the functionality of the 
                           ISimpleDoubleCalculator, with the added functionality of being able to set the precision of the results of the 
                           calculations, get the precision, and round any number to the given precision. The only method this interface has is setPrecision() which will change the precision value for the implementation.
- SetPrecisionCalculator:  This class is an implementation of the ISetPrecisionCalculator Interface.
It implements the four operations the ISimpleDoubleCalculator interface and the setPrecision(), getPrecision() and round() methods.
The Precision library by Apache is used here to achieve the desired rounding effect. It also has a ISimpleDoubleCalculator to use as a delegate.
- ILoadedCalculatorModel: This interface represents the full fledged calculator that this project is all about.
This interface contains all the various operations that this calculator will be able use. This interface
extends both the ISetPrecisionCalculator and IStoringCalculator interface.
- LoadedCalculatorModel:  This class is an implementation of the ILoadedCalculatorModel interface. 
This class uses a stack to implement the answer retrieval system, and a ISetPrecisionCalculator delegate
to implement the rounding component. It implements all the methods from each interface that it implements.

## View
The view contains four files (one interface, 2 classes, and a GUI Form). 
Here is an explanation of each one:
- ILoadedCalculatorView: This interface represents a view that displays, in some way, a calculator. The interface
has 4 methods: run, ShowErrorMessage, setCommandCallback, and acceptResult. 
- TextualView: This class implements the ILoadedCalculatorView interface. This class implements all 4
methods from the interface. This class is a textual view. It takes in commands from a Readable object, and
outputs the results with an Appendable object. The commandCallback method is not used as the Readable
is what does the communicating.
- LoadedCalculatorView: This class is a visual view and implements ILoadedCalculatorView. This class 
extends JFrame and provides the details for the GUI. This view has an input area for equations and simple math,
as well as a catalog for the more complex operations. The run method sets the GUI to visible, while the 
acceptResult method is what takes in the results from the Controller which is set by the setCommandCallback
method.
- LoadedCalculator.form: This file is the GUI designer. This file visually depicts the GUI through Intellij's
GUI designer. 

## Controller
The controller consists of an interface and one implementation. Here is an explanation of each one:
- ILoadedCalculatorController: This interface represents a controller and communicates with both the model 
and the view. This interface only has two methods: run and processCommand. Run will begin the communication between
the model and the view, and processCommand will take in input from the view and send that to the model.
- LoadedCalculatorController: The class is an implementation of the ILoadedCalculatorController interface. It
also implements the Consumer<String> interface and, the accept-method that goes along with it. The Consumer 
interface is there to help facilitate the communication between the three parts of the Loaded Calculator.
This class also contains a private class that holds all the commands that the controller can interpret.
