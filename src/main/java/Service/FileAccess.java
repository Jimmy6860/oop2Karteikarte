package Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import model.FileInfo;


public class FileAccess {

	private final static String LASTFILEINFOPATH = "FileInfo.ser";

	private static Path defaultPath = Paths.get("C:/temp");
	private static String actualFile = "";
	private static Date lastSaveDate;

	public static String getActualDirectoryString() {
		return defaultPath.toString();
	}

	public static String getActualFileString() {
		return actualFile;
	}

	public static Date getLastSaveDate() {
		return lastSaveDate;
	}

	public static void initializeActualPath() throws ClassNotFoundException, IOException {
		FileInfo lastFileInfo = deSerializeFileInfo();
		if (lastFileInfo != null) {
			defaultPath = Paths.get(lastFileInfo.getDirPath());
			actualFile = lastFileInfo.getFileName();
			lastSaveDate = lastFileInfo.getDate();
		}
	}

	public static void saveFile(String pathString, String content) throws IOException {
		Path path = preparePath(pathString);

		if (!Files.exists(path.getParent())) {
			Files.createDirectories(path.getParent());
		}

		BufferedWriter bw = Files.newBufferedWriter(path, Charset.forName("UTF-8"));
		bw.write(content);
		bw.close();

		serializeFileInfo(new FileInfo(new Date(), path.getFileName().toString(), path.getParent().toString()));
	}

	public static String readFile(String pathString) throws IOException {
		Path path = preparePath(pathString);

		StringBuilder content = new StringBuilder();
		BufferedReader reader = Files.newBufferedReader(path);
		String line = reader.readLine();
		while (line != null) {
			content.append(line + "\n");
			line = reader.readLine();
		}
		reader.close();

		return content.toString();
	}

	private static Path preparePath(String pathString) {
		Path path = defaultPath.resolve(pathString);
		path = path.normalize();
		defaultPath = path.getParent();
		actualFile = path.getFileName().toString();
		return path;
	}

	private static void serializeFileInfo(FileInfo c) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(LASTFILEINFOPATH);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(c);
		out.close();
		fileOut.close();
	}

	private static FileInfo deSerializeFileInfo() throws IOException, ClassNotFoundException {
		if (Files.exists(Paths.get(LASTFILEINFOPATH))) {
			FileInputStream fileIn = new FileInputStream(LASTFILEINFOPATH);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			FileInfo f = (FileInfo) in.readObject();
			in.close();
			return f;
		}
		return null;
	}

}