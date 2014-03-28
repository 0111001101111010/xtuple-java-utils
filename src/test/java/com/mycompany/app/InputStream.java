
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputStream {

	public static void main(String[] args) {

		try{
			BufferedReader br = new BufferedReader(new FileReader("input.json"));
			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}