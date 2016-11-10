package transportation;

public class GenericPair<E extends Object, F extends Object> {
	private E first;  
    private F second;  
      
    public GenericPair(E first, F second){  
    	  super();
          this.first = first;
          this.second = second;
    }  
      
    public E getFirst() {  
        return first;  
    }  
    public void setFirst(E first) {  
        this.first = first;  
    }  
    public F getSecond() {  
        return second;  
    }  
    public void setSecond(F second) {  
        this.second = second;  
    } 
}
