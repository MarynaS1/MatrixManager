import java.util.Scanner;
import java.util.ArrayList;

class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String input;
    String[] names;//crutches idk
    Matrix m1;
    Matrix m2;
    Matrix c;
    System.out.println("***Matrix Manager***");
    System.out.println("(Note: this program sometimes rounds values to 4 decimals so minor errors can come up and pile up with multiple opperations)\n(Also, please don't input numbers bigger than 10000)");
    System.out.println("Type \"help\" to get a list of commands.");
    boolean loop=true;
    boolean found;
    //Switch loop
    while(loop){
      m1 = null;
      m2 = null;
      c = null;
      System.out.print(">");
      switch(sc.nextLine().toLowerCase()){
        case "create":
          Matrix.matrixList.add(Matrix.createMatrix());
          break;
        case "delete":
          System.out.println("Name of the matrix you want to delete:");
          System.out.print(">");
          input = sc.nextLine().toLowerCase();
          found=false;
          for(Matrix m:Matrix.matrixList){
            if(m.getName().toLowerCase().equals(input)){
              Matrix.matrixList.remove(Matrix.matrixList.indexOf(m));
              System.out.println("Matrix "+m.getName()+" removed.");
              found = true;
              break;
            }
          }
          if(!found)System.out.println("Matrix not found, action canceled");
          break;
        case "show":
          for(Matrix m:Matrix.matrixList)System.out.println(m);
          break;
        case "add":
          System.out.println("Name the two matrices to add: (matrixA,matrixB)");
          System.out.print(">");
          names = sc.nextLine().toLowerCase().split(",");
          if(names.length!=2){
            System.out.println("Wrong number of names, action ended");
            break;
          }
          for(Matrix m:Matrix.matrixList){
            if(m.getName().toLowerCase().equals(names[0]))m1=m;
            else if(m.getName().toLowerCase().equals(names[1]))m2=m;
          }
          if(m1==null||m1==null){
            System.out.println("One or more matrices not found, action ended");
            break;
          } else if(m1.getRows()!=m2.getRows()||m1.getCols()!=m2.getCols()){
            System.out.println("Matrix dimensions are not the same, action ended");
            break;
          } else{
            c = Matrix.addMatrices(m1,m2);
            Matrix.matrixList.add(c);
            System.out.println("Matrix created:"+c);
          }
          break;
        case "subtract":
          System.out.println("Name the two matrices to subtract: (matrixA,matrixB) \n(hypothetical result: matrixA-matrixB)");
          System.out.print(">");
          names = sc.nextLine().toLowerCase().split(",");
          if(names.length!=2){
            System.out.println("Wrong number of names, action ended");
            break;
          }
          for(Matrix m:Matrix.matrixList){
            if(m.getName().toLowerCase().equals(names[0]))m1=m;
            else if(m.getName().toLowerCase().equals(names[1]))m2=m;
          }
          if(m1==null||m1==null){
            System.out.println("One or more matrices not found, action ended");
            break;
          } else if(m1.getRows()!=m2.getRows()||m1.getCols()!=m2.getCols()){
            System.out.println("Matrix dimensions are not the same, action ended");
            break;
          } else{
            c = Matrix.subtractMatrices(m1,m2);
            Matrix.matrixList.add(c);
            System.out.println("Matrix created:"+c);
          }
          break;
        case "transpose":
          System.out.println("Name of the matrix you want to transpose:");
          System.out.print(">");
          found = false;
          input = sc.nextLine().toLowerCase();
          for(Matrix m:Matrix.matrixList){
            if(m.getName().toLowerCase().equals(input)){
                if(m.getRows()!=m.getCols()){
                    System.out.println("Matrix must be square, action canceled");
                }else{
                    c = Matrix.transposeMatrix(m);
                    Matrix.matrixList.add(c);
                    System.out.println("Matrix created:\n"+c);
                }
                found = true;
                break;
            }
          }
          if(!found)System.out.println("Matrix not found, action canceled");
          break;
        case "multiply":
          System.out.println("Name the two matrices to multiply: (matrixA,matrixB) \\n(hypothetical result: matrixA*matrixB)");
          System.out.print(">");
          names = sc.nextLine().toLowerCase().split(",");
          if(names.length!=2){
            System.out.println("Wrong number of names, action ended");
            break;
          }
          for(Matrix m:Matrix.matrixList){
            if(m.getName().toLowerCase().equals(names[0]))m1=m;
            else if(m.getName().toLowerCase().equals(names[1]))m2=m;
          }
          if(m1==null||m1==null){
            System.out.println("One or more matrices not found, action ended");
            break;
          } else if(m1.getRows()!=m2.getCols()||m1.getCols()!=m2.getRows()){
            System.out.println("Matrix dimensions are not opposite, action ended");
            break;
          } else{
            c = Matrix.multiplyMatrices(m1,m2);
            Matrix.matrixList.add(c);
            System.out.println("Matrix created:\n"+c);
          }
          break;
        case "multiplier":
          System.out.println("Name of the matrix you want multiply and a number.\nexample format: matrixA,2");
          System.out.print(">");
          found = false;
          names = sc.nextLine().toLowerCase().split(",");
          for(Matrix m:Matrix.matrixList){
            if(m.getName().toLowerCase().equals(names[0])){
                try{
                    c = Matrix.multiplyByNum(m,Double.parseDouble(names[1]));
                }catch(Exception e){
                    System.out.println("Incorrect format, action canceled");
                }
                Matrix.matrixList.add(c);
                System.out.println("Matrix created:\n"+c);
                found = true;
                break;
            }
          }
          if(!found)System.out.println("Matrix not found, action canceled");
          break;
        case "determinant":
          System.out.println("Name of the (square) matrix you want find the determinant of:");
          System.out.print(">");
          found = false;
          input = sc.nextLine().toLowerCase();
          for(Matrix m:Matrix.matrixList){
            if(m.getName().toLowerCase().equals(input)){
                if(m.getRows()!=m.getCols()){
                    System.out.println("Matrix must be square, action canceled");
                }else{
                    System.out.println("Determinant found:\n"+Matrix.findDeterminant(m));
                }
                found = true;
                break;
            }
          }
          if(!found)System.out.println("Matrix not found, action canceled");
          break;
        case "inverse":
          System.out.println("Name of the matrix you want find the inverse of:");
          System.out.print(">");
          found = false;
          input = sc.nextLine().toLowerCase();
          for(Matrix m:Matrix.matrixList){
            if(m.getName().toLowerCase().equals(input)){
                c = Matrix.findInverse(m);
                if(c==null)System.out.println("Inverse doesn't exist");
                else{
                    Matrix.matrixList.add(c);
                    System.out.println("Matrix created:\n"+c);
                }
                found = true;
                break;
            }
          }
          if(!found)System.out.println("Matrix not found, action canceled");
          break;

        case "help":
          System.out.println("'create' - create a matrix (automatically saved)"+
                            "\n'delete' - delete a saved matrix"+
                            "\n'show' - show all saved matrices"+
                            "\n'add' - add two matrices together"+
                            "\n'subtract' - subtract one matrix from another"+
                            "\n'transpose' - transpose a matrix"+
                            "\n'multiply' - multiply one matrix by another"+
                            "\n'multiplier' - multiply every element in a matrix by a number"+
                            "\n'determinant' - get the determinant of a matrix"+
                            "\n'inverse' - get the inverse of a matrix"+
                            "\n'help' - print the list of commands");
          break;
        case "q":
          loop=false;
        default:
          System.out.println("Non-existent choice.");
          break;
      }
    }
    sc.close();
  }
}

class Matrix{
  private static Scanner sc = new Scanner(System.in);
  private String input;
  private int rows;
  private int cols;
  private String name;
  private static int unnamedCount = 1;
  public static ArrayList<Matrix> matrixList = new ArrayList<Matrix>();
  private double[][] matrix;

  //Matrix creation methods
  public Matrix(int rows,int cols,String name){
    this.rows = rows;
    this.cols = cols;
    this.name = name;
    matrix = new double[rows][cols];
  }
  
  public void setMatrix(){
    System.out.print(">");
    input = sc.nextLine();
    double[] elements = inputToDoubles(input.split(","));
    
    while(elements==null){
      System.out.println("Matrix can only contain numbers and doubles, try again:");
      System.out.print(">");
      input = sc.nextLine();
      elements = inputToDoubles(input.split(","));
    }
    
    if(elements.length!=rows*cols){//checking if input is in parts
    
      if(elements.length==cols){//checks if first input fits column
        
        matrix[0]=elements;
        
        for(int i = 1;i<rows;i++){//repeats until matrix is filled or loop is broken
          System.out.println("Next row:");
          System.out.print(">");
          input = sc.nextLine();
          elements = inputToDoubles(input.split(","));
          if(elements!=null&&elements.length==cols)matrix[i] = elements;//aditional numbers check
          else{
            System.out.println("Incorrect format, try again:");
            i--;
          }
        }
      }
      else{//if first input doesn't fit columns
        System.out.println("Incorrect format, try again:");
        setMatrix();
      }
    }else{//if input isn't in parts
      for(int i = 0;i<rows;i++){
        for(int j = 0;j<cols;j++){
          matrix[i][j]=elements[j+i*cols];
        }  
      }
    }
  }
  
  public static Matrix createMatrix(){
    int[] dimensions;
    System.out.println("Name the matrix: (no commas, spaces, or parentheses)");
    System.out.print(">");
    String inputName = sc.nextLine();
    
    //name check
    for(Matrix m:matrixList){
      if(m.getName().equals(inputName)){
        System.out.println("Name already exists");
        inputName="untitled"+unnamedCount;
        System.out.println("Name Changed to "+inputName);
        unnamedCount++;
      }
    }
    if(inputName.equals("")||inputName.contains(",")||inputName.contains(" ")||inputName.contains("(")||inputName.contains(")")){
      System.out.println("Name is empty or contains illegal characters");
      inputName="untitled"+unnamedCount;
      System.out.println("Name Changed to "+inputName);
      unnamedCount++;
    }
    
    System.out.println("Dimensions of matrix: \n(Format: rows,columns)");
    System.out.print(">");
    dimensions=inputToDimensions(sc.nextLine());
    Matrix matrix = new Matrix(dimensions[0],dimensions[1],inputName);
    System.out.println("Contents of matrix (only integers or doubles):\n(Either one row at a time or all elements in row-major order)");
    matrix.setMatrix();
    System.out.println("Matrix created successfuly!");
    return matrix;
  }
  
  //Helper methods for matrix creation
  public static int[] inputToDimensions(String input){
    String str=input;//crutch idk
    String[] strResult;
    int[] intResult = new int[2];
    while(true){
      try{
        strResult = str.split(",",2);
        intResult[0]=Integer.parseInt(strResult[0]);
        intResult[1]=Integer.parseInt(strResult[1]);
        if(intResult[0]!=0&&intResult[1]!=0) return intResult;//only returns when data is correct
      }catch(Exception e){
        System.out.println("Incorrect format, try again.");
        System.out.print(">");
        str=sc.nextLine();
      }
    }
  }
  //this is a mess...
  public static double[] inputToDoubles(String[] strings){
    double[] elements = new double[strings.length];
    for(int i = 0;i<strings.length;i++){
      try{
        elements[i]=Double.parseDouble(strings[i]);
      }catch(Exception e){return null;}
    }
    return elements;
  }
  
  //Matrix operation methods
  //PRECONDITION: THE MATRICES HAVE THE SAME DIMENSIONS
  public static Matrix addMatrices(Matrix A, Matrix B){
    Matrix C = new Matrix(A.getRows(),A.getCols(),(A.getName()+"(+)"+B.getName()));
    for(int i = 0;i<A.getRows();i++){
      for(int j = 0;j<A.getCols();j++){
        double result = A.getElement(i,j)+B.getElement(i,j);
        try{
          C.setElement(i,j,((double)Math.round(result*10000)/10000));
        }catch(Exception e){
          System.out.println("A matrix contained too big pf a number");
          C=null;
          return C;
        }
      }
    }
    return C;
  }
  //PRECONDITION: THE MATRICES HAVE THE SAME DIMENSIONS
  public static Matrix subtractMatrices(Matrix A, Matrix B){
    Matrix C = new Matrix(A.getRows(),A.getCols(),(A.getName()+"(-)"+B.getName()));
    for(int i = 0;i<A.getRows();i++){
      for(int j = 0;j<A.getCols();j++){
        double result = A.getElement(i,j)-B.getElement(i,j);
        try{
          C.setElement(i,j,((double)Math.round(result*10000)/10000));
        }catch(Exception e){
          System.out.println("A matrix contained too big of a number");
          C=null;
          return C;
        }
      }
    }
    return C;
  }
  public static Matrix multiplyByNum(Matrix A,double num){
    Matrix c = new Matrix(A.getRows(), A.getCols(),((num==(int)num) ? (int)num:num+"") + A.getName());
    for(int i = 0;i<A.getRows();i++){
        for(int j = 0;j<A.getCols();j++){
            c.setElement(i, j, ((double)Math.round(A.getElement(i, j)*num*10000)/10000));
        }
    }
    return c;
  }
//PRECONDITION: THE MATRICES HAVE OPPOSITE DIMENSIONS
  public static Matrix multiplyMatrices(Matrix A, Matrix B){
    Matrix C = new Matrix(A.getRows(),B.getCols(),(A.getName()+"(*)"+B.getName()));
    double temp;
    //had to look up the code to do this because I got confused :(
    for(int i = 0;i<A.getRows();i++){
        for(int j = 0;j<B.getCols();j++){
            temp=0;
            for(int k = 0; k<A.getCols();k++){
                temp+=A.getElement(i, k)*B.getElement(k, j);
            }
            C.setElement(i, j, ((double)Math.round(temp*10000)/10000));
        }
    }
    return C;
  }
  //PRECONDITION: THE MATRIX IS SQUARE
  public static Matrix transposeMatrix(Matrix A){
    Matrix C = new Matrix(A.getRows(),A.getCols(),(A.getName()+"(t)"));
    for(int i = 0;i<A.getRows();i++){
        for(int j = 0;j<A.getCols();j++){
            C.setElement(j, i, A.getElement(i, j));
        }
    }
    return C;
  }
  //PRECONDITION: THE MATRIX IS SQUARE
  public static double findDeterminant(Matrix A){
    double result = 0;
    if(A.getRows()==2){
        result = A.getElement(0, 0)*A.getElement(1, 1)-A.getElement(1, 0)*A.getElement(0, 1);
    }
    else{
        for(int i = 0;i<A.getCols();i++){
            result += Math.pow(-1,2+i)*A.getElement(0, i)*findDeterminant(findMinor(0, i, A));
        }
    }
    return result;
  }
  //Soft precondition: the matrix is square
  private static Matrix findMinor(int row,int col,Matrix A){
    Matrix c = new Matrix(A.getRows()-1,A.getCols()-1,"temp");
    boolean roffset = false;
    boolean coffset = false;
    for(int i = 0;i<A.getRows();i++){
        if(i!=row){
            for(int j=0;j<A.getCols();j++){
                if(j!=col)c.setElement(i-((roffset==true) ? 1:0), j-((coffset==true) ? 1:0),A.getElement(i, j));
                else coffset=true;
            }
        } else roffset=true;
        coffset=false;
    }
    return c;
  }
  //Soft precondition: the matrix is square
  private static Matrix findAij(Matrix A){
    Matrix Aij = new Matrix(A.getRows(), A.getCols(), "temp");
    for(int i = 0;i<Aij.getRows();i++){
        for(int j = 0;j<Aij.getCols();j++){
            Aij.setElement(i, j, (Math.pow(-1,i+j+2)*findDeterminant(findMinor(i, j, A))));
        }
    }
    return Aij;
  }
  //PRECONDITION: THE MATRIX IS SQUARE
  public static Matrix findInverse(Matrix A){//not done
    Matrix c = new Matrix(A.getRows(), A.getCols(), "temp");
    double determinant = findDeterminant(A);
    if(determinant==0)return null;
    c = multiplyByNum(transposeMatrix(findAij(A)),1/determinant);
    c.setName(A.getName()+"(-1)");
    return c;
  }
  //toString overwrite
  public String toString(){
    String result = name+":";
    for(double[] r:matrix){
      result+="\n|  ";
      for(double c:r){
        if(c==(int)c)result+=((int)c+"  ");
        else result+=(c+"  ");
      }
      result+="|";
    }
    return result;
  }
  //getters and setters
  public String getName(){return name;}
  public void setName(String name){this.name=name;}
  public int getRows(){return rows;}
  public int getCols(){return cols;}
  public double getElement(int i,int j){return matrix[i][j];}
  public void setElement(int i,int j,double element){matrix[i][j]=element;}
  public void randomSet(){
    for(int i = 0;i<rows;i++){
      for(int j = 0;j<cols;j++){
        matrix[i][j]=(int)(Math.random()*10);
      }
    }
  }
}
