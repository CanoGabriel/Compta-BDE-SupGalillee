package IHMComponent;
import java.io.File;

public class DirReader {
	public DirReader() {
	}

	public File[] listFiles(String directoryPath){
		File[] files = null;
		File directoryToScan = new File(directoryPath);
		files = directoryToScan.listFiles();
		return files;
	}
	public int count(String directoryPath){
		File[] f= this.listFiles(directoryPath);
		return f.length;
	}
	
	public void affiche(File[] l){
		for(File i : l){
			System.out.println(i.getName());
		}
	}
	
	public void affichePath(File[] l){
		for(File i : l){
			System.out.println(i.getPath());
		}
	}
} 

