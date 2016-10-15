import java.util.Comparator;
import java.util.PriorityQueue;


public class LazyUnionsImpl extends LazyUnions {
    
    /* I need to implement the full graph with edges */
    static Comparator cust = (Comparator<Edge>) (Edge o1, Edge o2) -> {
        Integer a = o1.weight;
        Integer b = o2.weight;
        
        return a.compareTo(b);
    };
    
    PriorityQueue<Edge> edges;
    
    
    public LazyUnionsImpl(int nVert) {
        super(nVert);
        
        this.edges = new PriorityQueue(1000,LazyUnionsImpl.cust);
    }
    
    /* new methods */
    
    // extend by find number of clusters
    private int getNumClusters(){
    
        int numCl = 0;
        
        for(int i = 0; i<this.leaders.length; i++ ){
        
            if(this.leaders[i]==i)
                numCl++;
        }
        
        return numCl;
    }
    
    // set edge
    
    public void setEdge(int a, int b, int weight){
    
        this.edges.add(new Edge( a, b, weight));
    }
    
    private Edge getTopEdge(){
    
        return this.edges.poll();
    }
    
    private boolean checkCycle(Edge edo){
    
        return this.find(edo.getA())==this.find(edo.getB());
    }
    
    public void findKclusters(int k){
    
        Edge temp;
        
        while(this.getNumClusters()>k){
        
            temp = this.getTopEdge();
            
            if(!this.checkCycle(temp)){
                this.uniteEdge(temp);
                //System.out.println("num of cluster is " + this.getNumClusters() + " counter " + counter++);
            }
        }
    }
    
    private void uniteEdge(Edge edo){
    
        this.union(edo.getA(), edo.getB());
    }
     
    
    public int maxDistance(){
    
        Edge temp;
        int distance = -1;
        
        while(true){
        
            temp = this.edges.poll();
            
            if(temp!=null && !this.checkCycle(temp)){
                distance = temp.getW();
                break;
            }
            else if(temp==null){
                break;
            }
        }
         
        return distance;
    }
    
    /* internal edge class */
    private class Edge{
    
        /* fields */
        // from = a, to = b
        int a;
        int b;
        int weight;
        
        private Edge(int a, int b, int weight){
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
        
        /* methods */
        
        private int getA(){
        
            return this.a;
        }
        
        private int getB(){
            
            return this.b;
        }
        
        private int getW(){
            return this.weight;
        }
    }
    
    
}
