import java.util.Scanner;
class MatrixManager{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String input;
    String[] names;//crutches idk
    Matrix m1;
    Matrix m2;
    Matrix c;
    System.out.println("***Matrix Manager***");
    System.out.println("(Note: this program sometimes rounds values to 4 decimals so minor errors can come up and pile up with multiple opperations)\n(Also, please don't input dimensions bigger than 10000)");
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
        case "quit":
          loop=false;
          break;
        default:
          System.out.println("Non-existent choice.");
          break;
      }
    }
    sc.close();
  }
}

