# Easy_Animator

## Overview:

This project combines 2D shapes and animation to create something visually appealing. It allows for
users to add shapes and edit the animation with respect to shape position, shape color and shape
size. The project was completed in a group of three for the CS 5004 class at Northeastern
University.

## Model Design Choices and Decisions:

The model consists of:

* **Interfaces**

    * IShape

    * AnimationChanges

    * EasyAnimatorModel

* **Classes**

    * AbstractShape (abstract class implements IShape)

    * Rectangle (extends Abstract Shape)

    * Oval (extends Abstract Shape)

    * MoveShape (implements AnimationChanges)

    * ChangeColor (implements AnimationChanges)

    * ScaleShape (implements AnimationChanges)

    * EasyAnimatorModel (implements EasyAnimatorModel)

* **ENUM's**
    * Transformations
      
    * ShapeType
    
#### IShape:

This interface defines the methods that are going to be implemented on different shapes. It consists
mainly of a constructor, getter and setter methods.

#### AbstractShape (implements IShape):

The AbstractShape class was created to reduce duplication of variables and functions that would be
used by both the Rectangle and Oval classes. It implements most of the methods outlined in IShape.
However, methods such as `getPerimeter()` and `getArea()` amongst others, are implemented in the
classes that extended AbstractShape.

#### Rectangle (extends AbstractShape):

This class represents the Rectangle shape. It extends the class AbstractShape because a lot of its
methods and fields are common to all shapes. Rectangle also contains a `toString()` method that
displays its original state.

#### Oval (extends AbstractShape):

This class represents the Oval shape. It extends the class AbstractShape because a lot of its
methods and fields are common to all shapes. Oval also contains a `toString()` method that displays
its original state.

#### AnimationChanges:

This interface defines the methods that change the state of an IShape object. This interface will be
implemented by class that represent the changes. We felt that this interface was required since
classes that mutate the fields of IShape Objects share a lot of commonalities.  
Any class that implements this interface will also have an added "tweening" method, that allows us
to make changes per "tick" in the animation (if the user selects a visual view).

#### MoveShape (implements AnimationChanges):

This class represents the changes to the IShape Objects (x,y) position. It allows the user to move
the IShape object from its current position to the new position on the 2D plane.

#### ChangeColor (implements AnimationChanges):

This class represents the changes to the IShape Object's color. It allows the user to change a
shape’s color to any within the RGB spectrum.

#### ScaleShape (implements AnimationChanges):

This class represents the changes to the IShape Object's size. It allows the user to resize the
shape to either a custom length and width or scale the Object using a scale factor. This class
involves overloading of constructors.

#### EasyAnimatorModel:

This interface was created in order to allow easy testing of our controller. It also hides the
implementation of the Model from the clients.

#### EasyAnimatorModelImpl (implements EasyAnimatorModel):

This class serves as the Model class of our animation. The class is responsible for implementing the
logic that updates the state of objects to eventually pass that information to the controller. The
inputs from the controller will call methods in this class to update/mutate our shape objects. The
class also allows for the addition and removal of shapes from the animation.

The class utilizes two types of data structures namely, an `ArrayList<IShape>` and
a `HashMap<String,AnimationChanges>`. The ArrayList is responsible for storing original states of
all shapes present in the animation, while the HashMap stores the mutations that have taken place to
each shape. The keys of the hashmap are the String names of the IShape objects, and the values are
an `ArrayList<AnimationChanges`. The ArrayList stores a clone of the original shape Objects.

We used an implementation of Java's List interface because they are dynamic in nature. This is
because an animation can have multiple shapes within a certain time period. Therefore, we can add
and remove shapes as we please to the ArrayList. This would have been a much harder process had we
used a traditional 2D array.

The reason we went with a HashMap as our primary data structure is because each name associated with
each shape is unique. The name of the shape can also be used to identify all the modifications made
to that particular shape. Therefore we used a HashMap, due to the "unique" nature of Key/Value
pairs.

The class contains a `toString()` method that when called returns a textual representation of the
animation.

#### Further Design Choices:

We chose to represent mutations such as moving, changing scale, changing color as their own objects
to make our design more modular. This decision allows each `MoveShape`, `ScaleShape`, `ChangeColor`
to represent their changes in their own `toString()` method. Furthermore, if we needed to add more
changes to the obects, we can just create more classes that implement the `AnimationChanges`
interface. This eliminates the need to edit the existing code.

## View Design Choices and Decisions:

The view consists of:

* **Interfaces**

    * IView

* **Classes**

    * SvgView (implements IView)
    * TextView (implements IView)
    * VisualView (implements IView, has a JFrame and JPanel)
        * SwingFrame (extends JFrame)
        * SwingPanel (extends JPanel)
    * EasyAnimator (parses the file name passed in from the command line and determines what type of
      view the user wants, the name of the output file, populates the data structures in the model
      and determines the speed of the animation. Also handles exceptional situations based on the
      command-line input. Passes on the parsed information to the ViewFactory)
    * ViewFactory (determines what kind of view to produce based on user input that was passed to it
      from the EasyAnimator class)


N.B. The model's data structures are populated through the use of the AnimationBuilder, whose
implementation exists as a static class in the EasyAnimatorModelImpl class.

### Text View: ### 

The text view allows the user to:

* View a full description of the original states of the shapes that are present in the animation.

* View all the changes made to every shape present in the animation.

* View changes made to a specific shape present in the animation.

### SVG View: ###

The SVG view allows us to render the model in SVG format such that the animation can be viewed in
other applications, such as a browser. It allows the same abilities as the text view, except that
the animation is represented in svg/xml format. The SVG view is built using a string builder, which
is then returned to the ViewFactory that outputs that string into a file.

### Visual View: ###

The Visual view allows a user to visualize in real time the animation that they passed into the
AnimationReader.

The visual view uses the Java JFrame and JPanel classes respectively to show the animation to the
user. The JFrame also has the ability to scroll if the size of the canvas is not optimal. The JFrame
uses composition to have a JPanel, and the JPanel is responsible for drawing a set of mutated shapes
at a particular time stamp (i.e. the current tick). The list of mutated shapes are given

## Overview of Run Design:

1. The user passes in a text file representing an animation.
2. The text file, along with any other command line arguments are passed into the EasyAnimator
   class.
    - Data structures in the model are populated based on the text file going into the
      AnimationReader.
      (They are populated through the use of the builder in the EasyAnimatorModelImpl class).
    - The type of view, output file name, and the speed of the animation are also determined.
3. We pass along the new "filled" model, the speed, the type of view, and the output file name to
   the ViewFactory class.
4. Based on the view type passed into it from EasyAnimator, an instance of the appropriate IView
   subtype is created, and the view is generated.

### Changes made for week 2: ###

What was added:

- implementation of the `getShapesAtTimeStamp(double time)` method (it was previously null):
  This method creates a list of all the shapes present at a particular time stamp during the
  animation. This method also calls upon the `executeChange(double time)` method at a particular
  time stamp by delegating to the specific AnimationChanges object. That object then returns the new
  modified shape to this function call, which then adds it to a list in this method.

- added a new method called "isConflictingChange" that takes in a key for the hashmap, a start and
  end time, and a Transformation object. This is to ensure that a conflicting change cannot be made.

- added a method called `setCanvasDimensions(int x, int y, int height, int width)` that allows us to
  mutate the dimensions of the canvas.

- added getter methods to EasyAnimatorModelImpl: `getCanvasHeight()`, `getCanvasWidth()`, `getX()`, `getY()`
  , `getEndTime()`.
  
- added getter methods to AnimationChanges: `getOld()`, `getNew()` - get the old and new time

- added an instance of `IShape` to AnimationChanges object, in order to keep track of the mutations
occurring.

- added an ENUM Transformations and ShapeType to identify each AnimationChanges object
and IShape object by their type. 

- added a setter method: `setEndTime(double time)` that sets the end time of the animation.

- added a method `tweening(double time)` in the AnimationChanges interface that calculates the
  transformation of the object at a particular time

- Added a new constructor to AbstractShape that takes in only the name of the shape - so that it could
work with the `declareShape(String name)` method in AnimationBuilder.

- Added test classes to test `SvgView`, `VisualView` and the builder. Added tests to TestEasyAnimatorModel
associated with the changes made to that respective class.
  
## Controller Design Choices and Decisions:

The controller consists of only 2 files: IController (an interface outlining the behavior of the
java class that implements the interface) and the actual implementing class.

* Interfaces
    - IController
* Classes
    - Controller
    
#### Controller

The controller takes in the view, and the model that has been generated from the EasyAnimator class.
If the view is a CompositeView, then a new GUI is generated from the constructor of the Controller
itself. If it is any of the other types of views (text, SVG or visual), the controller's `playAnimation()` (from
the `main()` in EasyAnimator) method is called upon to generate the output necessary. The controller has
several private classes that implement the `ActionListener` interface such that when a user clicks any type
of button in the GUI, the controller can execute the required steps. Within those private classes are the 
`actionPerformed(ActionEvent e)` methods that take in an action event, and based on that, the controller
tells the model/view what to do when. Each class represents each button/user interaction with the GUI.

The controller has a `Timer` field that is connected to the start button, so when the user clicks 
that start button, the timer starts; the `StartListener` class's `actionPerformed(ActionEvent e)
`method is therefore continuously called. Within that method, a field called `tick` is incremented 
and is passed into the `getShapesAtTimeStamp` method in the model, which allows for the animation to run
continuously without having the user "click" the start button every time. This same instance of 
the timer is also used in other button listener classes (i.e. the PauseListener, RestartListener 
etc.) in order to manipulate/modify the animation.

The controller also has a private class for when the user wants to save their currently running 
animation as a text or svg file. Within those classes, the file is automatically saved with a 
pre-defined file name ("Custom_Saved_SVG_#number" or "Custom_Saved_Text_#number"), where "#number"
is a static field that is incremented every time the save SVG or save txt buttons are clicked.
These files are saved in the current working directory, so if the user wants to save the files as
their own custom names, they can simply navigate to that directory and rename the file.

### Changes made for week 3: ###

What was added:

- A new `CompositeView` class that represents the view type if the user specifies "playback" in 
their command line arguments. This view type is GUI that allows users to start, pause, resume,
  loop, speed up and slow down their desired animation. It also includes a menu in which users
  can save the currently running animation as a text file and/or an SVG file and load in a new/second
  animation in a separate window, that runs alongside the original one. It also allows them to exit the animation
  from the "File" option in the menu. 
  - In addition, a user can also add a new shape to their animation. When the user selects this 
    option, they have to manually input the required fields such as the name, type, x/y coordinates,
    the RBG values and dimensions of the new shape.
  - A user can also remove a shape from the animation by simply entering the name of the shape
    they wish to remove.
    
- A new `CompositeFrame` class that extends the JFrame class. This is the class that is reponsible
  for adding the buttons and all the interactive aspects of GUI to the `CompositeView` that has 
  the frame. 
    The frame has the following components:
    - An `animationPanel` that houses the main animation. This is an instance of `SwingPanel` that
      was implemented last week.
    - A `buttonsPanel` that houses the buttons.
    - A `menuBar` that has JMenu (File, Add Shape and Remove Shape) objects and JMenuItem objects that
    implement action listeners in order to perform their desired tasks upon clicks.
    - Horizontal and Vertical JScrollBar objects.
    - An `addShapePanel` panel that holds the dialog box that has JLabels and JTextField's that take
    in all the necessary arguments to create a new `IShape` object to add on to the animation.
    - Several getter methods to retrieve the string input to the JTextField when a user is adding
    a new shape to the animation, as well as retrieving the button fields (i.e. start, pause, restart etc.).

- A controller (outlined above).

What was modified:

- A modified ViewFactory in which its only static method returns an instance of the IView interface.
- The CompositeView also now implements the IView interface.

#### Design Choices for Week 3:

- We made all the view types (`CompositeView`, `TextView`, `SvgView` and `VisualView`) implement
IView because we wanted to have only 1 view factory and make that ViewFactory return only 1 type of view. 
  As a result, some methods in the IView interface throw `UnsupportedOperationException` based on 
  particular sub-type, to let the user know that they cannot utilize those particular operations.
  For example, a `TextView` does not have a "frame", therefore we throw an `UnsupportedOperationException`.
  
  This could have been avoided 1 in 2 ways: 1. We could have had separate controllers for each view. While
  this would have achieved high cohesion, it would have also tightly coupled the different controllers
  to their respective views. We want our controller to be independent of the type of view it recieves.
  2. We could have several methods in the view factory that return different types of views accordingly, 
    and moved all the "if-else" conditions that exist in the ViewFactory into the EasyAnimator class.
     However, this would also require us to have several controllers. 
 
- We chose NOT to have the controller implement the `ActionListener` interface because it would tightly couple
  it to the `CompositeView` GUI. Therefore, in order to lower coupling while maintaining high cohesion, we implement
  private classes that reside within the controller that implement the `ActionListener` interface. This gives
  each private class a single responsibility in their associated `actionPerformed(ActionEvent e)` methods.
  Therefore, when a user uses the GUI in a particular way, ONLY that particular private class's
  `actionPerformed(ActionEvent e)` method will be called upon (i.e. each class has only 1 reason for existing).
  Therefore, this also helps with protection from variations in the future. For example, if we want
  to add a new button, we simply create a new class that implements the `ActionListener` interface, and
  write the associated commands for that button, and "hook-up" that class to that particular button.
