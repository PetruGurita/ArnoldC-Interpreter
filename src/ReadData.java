
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
public class ReadData {

		
		public BufferedReader readFile(String fileName) throws IOException {
			
			try {
				File file = new File(fileName);
				FileInputStream filein = new FileInputStream(file);
				DataInputStream in = new DataInputStream(filein);
				BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
				return buffer;

			} catch (Exception e) {
				System.out.println("Error : " + e.getMessage());
				return null;
			}
		}

	}

