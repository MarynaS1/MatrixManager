import java.util.Scanner;
import java.util.ArrayList;
public class Matrix{
    private static Scanner sc = new Scanner(System.in);
    private String input;
    private int rows;
    private int cols;
    private String name;
    private static int unnamedCount = 1;
    public static ArrayList<Matrix> matrixList = new ArrayList<Matrix>();
    private double[][] matrix;

    /*Constructor*/
    
    public Matrix(int rows,int cols,String name){
        this.rows = rows;
        this.cols = cols;
        this.name = name;
        matrix = new double[rows][cols];
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
  

    /*Helpers*/

    private static int[] inputToDimensions(String input){
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
    
    private static double[] inputToDoubles(String[] strings){
        double[] elements = new double[strings.length];
        for(int i = 0;i<strings.length;i++){
            try{
                elements[i]=Double.parseDouble(strings[i]);
            }catch(Exception e){return null;}
        }
        return elements;
    }
  

    /*Matrix Operations*/

    //PRECONDITION: THE MATRICES HAVE THE SAME DIMENSIONS
    public static Matrix addMatrices(Matrix A, Matrix B){
        Matrix C = new Matrix(A.getRows(),A.getCols(),(A.getName()+"+"+B.getName()));
        for(int i = 0;i<A.getRows();i++){
            for(int j = 0;j<A.getCols();j++){
                double result = A.getElement(i,j)+B.getElement(i,j);
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
    
    //PRECONDITION: THE MATRICES HAVE THE SAME DIMENSIONS
    public static Matrix subtractMatrices(Matrix A, Matrix B){
        Matrix C = new Matrix(A.getRows(),A.getCols(),(A.getName()+"-"+B.getName()));
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
        Matrix C = new Matrix(A.getRows(),B.getCols(),(A.getName()+"*"+B.getName()));
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
    
    /*Getters*/
    
    public String getName(){return name;}
    
    public int getRows(){return rows;}
    
    public int getCols(){return cols;}

    public double getElement(int i,int j){return matrix[i][j];}
    
    /*Setters*/
    
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
    
    public void setName(String name){this.name=name;}
    
    public void setElement(int i,int j,double element){matrix[i][j]=element;}


    /*Other*/
    /* */
    public void randomSet(){
    for(int i = 0;i<rows;i++){
      for(int j = 0;j<cols;j++){
        matrix[i][j]=(int)(Math.random()*10);
      }
    }
  }
}
