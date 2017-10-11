package team1.searchengine.ui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import team1.searchengine.model.Author;

public class ShortlistManager implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static class ShortlistAuthor implements java.io.Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Author author;
		private AuthorPosition position;
		private String notes;

		public ShortlistAuthor(Author author, AuthorPosition position, String notes) {
			this.author = author;
			this.position = position;
			this.notes = notes;
		}

		public void setAuthor(Author a) {
			this.author = a;
		}

		public Author getAuthor() {
			return this.author;
		}

		public void setPosition(AuthorPosition position) {
			this.position = position;
		}

		public AuthorPosition getPosition() {
			return this.position;
		}

		public void setNotes(String notes) {
			this.notes = notes;
		}

		public String getNotes() {
			return this.notes;
		}
	}

	private static ArrayList<ShortlistAuthor> shortlist = new ArrayList<>();

	private ShortlistManager() {
		// static class
	}

	/**
	 * Load short list on initialization
	 */
	static {
		loadShortlist();
	}

	/**
	 * get short list
	 * 
	 * @return
	 */
	public static List<ShortlistAuthor> getShortlist() {
		return shortlist;
	}

	/**
	 * Add a new author to short-list
	 * 
	 * @param author
	 * @param position
	 * @param notes
	 */
	public static List<ShortlistAuthor> addToShortlist(Author author, AuthorPosition position, String notes) {
		if (author == null)
			throw new IllegalArgumentException("Author parameter cannot be null");
		if (existsInShortlist(author))
			return shortlist;

		shortlist.add(new ShortlistAuthor(author, position, notes));
		save();
		return shortlist;
	}

	/**
	 * Remove the author from short-list
	 * 
	 * @param author
	 */
	public static List<ShortlistAuthor> removeFromShortlist(Author author) {
		if (author == null)
			return shortlist;
		ShortlistAuthor toRemove = shortlist.stream().filter(x -> x.author.name.equals(author.name)).findFirst()
				.orElse(null);
		if (toRemove != null) {
			shortlist.remove(toRemove);
			save();
		}
		return shortlist;
	}

	/**
	 * Check if an author already exists in the short-list
	 * 
	 * @param author
	 * @return true, iff the author of the same name exists in the short-list
	 */
	public static boolean existsInShortlist(Author author) {
		if (author == null)
			return false;
		return shortlist.stream().anyMatch(x -> x.author.name.equals(author.name));
	}

	/**
	 * Load the short-list file
	 */
	private static void loadShortlist() {
		try {
			FileInputStream fileIn = new FileInputStream("shortlist.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Object inObj = in.readObject();
			if (inObj != null)
				shortlist = (ArrayList<ShortlistAuthor>) inObj;
			else
				shortlist = new ArrayList<>();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Save the short-list file
	 */
	public static void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("shortlist.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(shortlist);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}
