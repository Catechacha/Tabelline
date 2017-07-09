public class TabHandler {

	public TabHandler(){ }
	
	public int getNumber(){
		return (int) (Math.random() * (10 - 0) + 0);
	}
	
	public boolean verify(int n1, int n2, int n){
		return (n1*n2==n);
	}		
}