
public class Book {
	protected String bookName;
	protected String bookDesc;
	protected String bookAuthor;
	protected int bookLikes;
	/**
	 * @param bookName
	 * @param bookDesc
	 * @param bookAuthor
	 * @param bookLikes
	 */
	public Book(String bookName, String bookDesc, String bookAuthor, int bookLikes) {
		super();
		this.bookName = bookName;
		this.bookDesc = bookDesc;
		this.bookAuthor = bookAuthor;
		this.bookLikes = bookLikes;
	}
	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}
	/**
	 * @param bookName the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	/**
	 * @return the bookDesc
	 */
	public String getBookDesc() {
		return bookDesc;
	}
	/**
	 * @param bookDesc the bookDesc to set
	 */
	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}
	/**
	 * @return the bookAuthor
	 */
	public String getBookAuthor() {
		return bookAuthor;
	}
	/**
	 * @param bookAuthor the bookAuthor to set
	 */
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	/**
	 * @return the bookLikes
	 */
	public int getBookLikes() {
		return bookLikes;
	}
	/**
	 * @param bookLikes the bookLikes to set
	 */
	public void setBookLikes(int bookLikes) {
		this.bookLikes = bookLikes;
	}
	
	
}
