# MATRIX MANAGER
Matrix Manager helps perform various actions on matrixes like adding, multiplying, finding derivatives, etc.

The goal of this program is to reduce the time and workload required when manipulating matrixes.

## FUNCTIONALITY/COMMANDS
#### CREATE
* Creates and fills a matrix which is then saved while the program is running
* The matrix first requires a name. A placeholder "untitled" name will be used if a name is not entered, a matrix with the same name already exists, or if the entered name contains illegal characters (" " or ",").
* Dimensions are entered in a "row,column" format.
* The newly created matrix can be filled in two ways: Either fill in all of the elements separated by commas in row-major format, or fill in row-by-row by entering elements in succeeding rows. Both ways fill in left to right.
* The elements must be _numbers_. Decimals and negative numbers are accepted.

#### DELETE
* Permanently deletes a matrix from the list of saved matrices.
  
#### SHOW
* Displays all saved matrices in order of creation, top to bottom, with their names followed by the contents, formatted to look like a standard matrix.

#### ADD
* Adds two matrices together
* The names of the two matrices should be entered in the format of "Matrix1,Matrix2"
* The name of the new matrix is a combination of the names of its two parent matrices with a "+" in between.

#### SUBTRACT
* Subtracts one matrix from another
* The names of the two matrices should be entered in the format of "Matrix1,Matrix2", with the second matrix being subtracted from the first.
* The name of the new matrix is a combination of the names of its two parent matrices with a "-" in between.

#### TRANSPOSE
* Creates a new matrix with the rows and columns of the original matrix switched.
* The matrix _must be_ square.
* The original matrix is not modified and the name of the new matrix is the original matrix's name with a "(t)" at the end.

#### MULTIPLY
* Multiplies the two matrices together
* The names of the two matrices should be entered in the format of "Matrix1,Matrix2", with the second matrix being multiplied by the first (order matters).
* Matrices _must_ have opposite dimensions (Ex: 2x3 and 3x2).
* The original matrix is not modified and the name of the new matrix is a combination of the names of its two parent matrices with a "*" in between.

#### MULTIPLIER
* Multiplies all elements in the matrix by a number.
* The original matrix is not modified and the name of the new matrix is the original matrix's name with the multiplier at the beginning.

#### DETERMINANT
* Finds the determinant of the matrix.
* The matrix _must be_ square.
* The original matrix is not modified and no new matrices are saved.

#### INVERSE
* Creates a new matrix that is the inverse of the original matrix.
* The matrix _must be_ square.
* The original matrix is not modified and the name of the new matrix is the original matrix's name with a "(-1)" at the end.

#### HELP
* Shows all commands with a short description

#### QUIT
* Closes the program
* The program can also be stopped manually without any damage, as nothing is saved either way.



## LIMITATIONS
* The program _does not_ save after being closed.
* The program cannot quickly process big matrices and might run into a runtime error with a matrix with more than a few hundred elements.
* The program cannot process fractions in non-decimal form
* The program cannot process non-numerical characters as elements for the matrix.
