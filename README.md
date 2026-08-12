WIP!
# MATRIX MANAGER
Matrix Manager helps perform various actions on matrixes like adding, multiplying, finding derivatives, etc.
The goal of this program is to reduce the time and workload required when manipulating matrixes.

## FUNCTIONALITY/COMMANDS
### "CREATE"
* Creates and fills a matrix which is then saved while the program is running
* The matrix first requires a name. A placeholder "untitled" name will be used if a name is not entered, a matrix with the same name already exists, or if the entered name contains illegal characters (" " or ",").
* Dimensions are entered in a "row,column" format.
* The newly created matrix can be filled in two ways: Either fill in all of the elements separated by commas in row-major format, or fill in row-by-row by entering elements in succeeding rows. Both ways fill in left to right.

### DELETE
* Permanently deletes a matrix from the list of saved matrices.
  
### SHOW
* Displays all saved matrices in order of creation, top to bottom, with their names followed by the contents, formatted to look like a standard matrix.

### ADD
* Adds two matrices together
* The names of the two matrices should be entered in the format of "Matrix1,Matrix2"
* The name of the new matrix is a combination of the names of its two parent matrices with a "+" in between.

### SUBTRACT
* Subtracts one matrix from another
* The names of the two matrices should be entered in the format of "Matrix1,Matrix2", with the second matrix being subtracted from the first.
* The name of the new matrix is a combination of the names of its two parent matrices with a "-" in between.

### TRANSPOSE


### MULTIPLY


### MULTIPLIER


### DETERMINANT


### INVERSE


### HELP


### QUIT



## LIMITATIONS
* The program _does not_ save after being closed.
* The program cannot quickly process big matrices and might run into a runtime error with a matrix with more than a few hundred elements.
