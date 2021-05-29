/*
 * @ Gabriel 2021
 */

public class ModelFile {
    private String fileName;

    public boolean append(int pos, String s){
    	char a[] = fileName.toCharArray();
    	char b[] = s.toCharArray();
    	char c[] = new char[a.length + b.length];
    	
    	int i,j,k;
    	
    	pos--;
    	
    	if (pos >= 0 && pos < c.length){
	    	
	    	for (i = 0; i< pos; i++ )
	    	{
	    		c[i] = a[i];
	    	}
	    	
	    	for (j = 0; j< b.length; j++ )
	    	{
	    		c[i++] = b[j];
	    	}
	    	for (k = pos; k < a.length ; k++)
	    	{
	    		c[i++] = a[k];
	    	}
	    	
	    fileName = new String(c);	
    	}
	    else
	    	{
	    	System.out.println("Position is outside of the file name.");
	    	return false;
	    	}
    	
    return true;	
    }
    
    public boolean subtract(int pos, int noPos){
    	char a[] = fileName.toCharArray();
    	char b[] = new char [a.length-noPos];
    	
    	int i,j;
    	
    	pos--;
    	
    	if (pos >= 0 && pos < b.length ){
	    	
	    	for (i = 0; i<= pos; i++ )
	    	{
	    		b[i] = a[i];
	    	}
	    	
	    	for (j = i + noPos; j< a.length; j++ )
	    	{
	    		b[i++] = a[j];
	    	}
	    	
	    fileName = new String(b);	
    	}
	    else
	    	{
	    	System.out.println("Position is outside of the file name.");
	    	return false;
	    	}
    	
    return true;
    }
    
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}
        
}
