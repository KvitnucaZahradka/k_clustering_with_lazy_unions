import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Pipjak
 */
public class KClusteringWithLazyUnions {
    /* fields */
    static int numVert;
    
    LazyUnionsImpl g;
    
    /* constructors */
    KClusteringWithLazyUnions(){
    }
    
    
    KClusteringWithLazyUnions(int nVert){
    
        g = new LazyUnionsImpl(nVert);
    }
    
    // * methods *
    private int maxDist(){
    
        // looking for four clusters:
        this.g.findKclusters(4);
        return g.maxDistance();
    
    }
    
    
    public static void main(String[] args) throws IOException {
       
       // reading number of vertices:
       KClusteringWithLazyUnions.nVert();
       
       // calculating the unions
       KClusteringWithLazyUnions k = new KClusteringWithLazyUnions(KClusteringWithLazyUnions.numVert);
       k.run();
       
       System.out.println("Spacing of 4-clustering is " + k.maxDist());
       
    }
    
    
    
    /* reading classes */
    
    // read in number of vertices
    public static void nVert() throws IOException {
    
    try{
        File file = new File("clustering1.txt");
        FileReader fileReader = new FileReader(file);
            
        BufferedReader bufferedReader = new BufferedReader(fileReader);    
        StringBuffer stringBuffer = new StringBuffer();
            
        String line;
            
        line = bufferedReader.readLine();
        
        // fillint the number of vertices;
        KClusteringWithLazyUnions.numVert = Integer.parseInt(line);
        
        // closing the fileReader:
        fileReader.close();
    
    
    }catch (IOException e) {
             e.printStackTrace();
        }
    
    }
    
    // read all the other lines;
    public void run() throws IOException {
        
    try {
        
        File file = new File("clustering1.txt");
        FileReader fileReader = new FileReader(file);
            
        BufferedReader bufferedReader = new BufferedReader(fileReader);    
        StringBuffer stringBuffer = new StringBuffer();
            
        String line;
            
        // skip first line
        
        line = bufferedReader.readLine();
        
       
        // read a next line:
        while ((line = bufferedReader.readLine()) != null ) {
                
            int idA,idB,weight;
                
            String[] arr = line.split("\\s+");
                
            // Node A, Node B and weight are
            idA = Integer.parseInt(arr[0])-1;
            idB = Integer.parseInt(arr[1])-1;
            weight = Integer.parseInt(arr[2]);
            //System.out.println("idA " + idA + " idB " + idB + " weight " + weight);
                    
            // adding the edge
            this.g.setEdge(idA, idB, weight);
            
        }
            
        // closing the fileReader:
        fileReader.close();
            
        }catch (IOException e) {
             e.printStackTrace();
        }
    }
    
    
}
