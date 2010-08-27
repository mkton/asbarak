package org.ow2.podcasti.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.osoa.sca.annotations.Property;
import org.osoa.sca.annotations.Scope;

@Scope("COMPOSITE")
public class PodcastiDBImpl implements PodcastiDBService {

	@Property(name = "db-path")
	String dbPath;

	public PodcastiModel read() throws IOException, ClassNotFoundException {

		// read file, de-serialize object
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File(this.dbPath));
		} catch (FileNotFoundException e) {
			return new PodcastiModel();
		}

		ObjectInputStream ois = new ObjectInputStream(fis);

		return (PodcastiModel) ois.readObject();

	}

	public void write(PodcastiModel model) {

		// serialize object
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(new File(dbPath));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(model);
			oos.flush();
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
