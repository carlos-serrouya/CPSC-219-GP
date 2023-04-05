# CPSC-219-GP

CPSC Group Project submission 4

We Ran in to some issues as previous iterations of our project were not javafx projects and instead java projects. 
This caused some issues when getting code ont ecclipse and a Ta suggested it would be easiest to start a new 
repository. You can find our old repository in previos submission or by this link
: https://github.com/labrat57/CPSC-project.git
We also noticed that since were using ecclipse from the virtual desktop to commit changes all commits come from CPSC-219 and Romeos commits come from "Jessica Minshull" and we couldnt Firgure out why. but we are all regularly commiting and pushing. To see what everyone is working on see the author for each class.

Efficient Point Mapping System

The goal of this project is to be able to calculate the most efficient path between 4 points while keeping the combined paths under a length restriction. A project like this could be used to design a more efficient transit system for a city or walking paths in a community.

To use our algorithm, open FinalCode and begin by inputting the 4 X coordinates followed by the 4 Y coordinates into the program. The program will then prompt you for your maximum path length.

The program then calculates the distances between all the input values. These are then turned into vectors. And are returned to us as vector lengths for the 6 possible vectors. These vectors are printed

The code makes a powerset of all possible vector combinations

the powerset is tested for path connectivity. If the set of paths it is connective and under the max path length it is added to a 2d array and its corrosponding distance set is added to another 2d array in the corrosponding position

Every set in the 2d array is tested for efficiency and the most efficient is printed

15 lines of code are cited 

source for some of the code https://stackoverflow.com/questions/1670862/obtaining-a-powerset-of-a-set-in-java https://stackoverflow.com/questions/26726366/convert-nested-list-to-2d-array
