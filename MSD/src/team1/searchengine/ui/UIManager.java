package team1.searchengine.ui;

import java.util.ArrayList;

public class UIManager {
	private static final ArrayList<ContentTypeInfo> contentTypeInfos = new ArrayList<ContentTypeInfo>();

	private UIManager(){
		// singleton through static methods
	}
	
	public static ArrayList<ContentTypeInfo> getAllContentTypes() {

		// Content Type : AUTHOR
		ContentTypeInfo authorCT = new ContentTypeInfo();
		authorCT.setName(ContentType.AUTHOR);
		// attributes of Author
		// name:
		ContentTypeAttributeInfo authorName = new ContentTypeAttributeInfo();
		authorName.setName("name");
		authorName.setDisplayName("Name");
		authorName.addOperator(Operator.EQ);
		authorName.addOperator(Operator.NEQ);
		authorName.addOperator(Operator.CONTAINS);
		authorName.addOperator(Operator.NOTCONTAINS);
		authorName.setFormat(ContentTypeFormat.TEXT);
		authorCT.addAttribute(authorName);

		// is program chair:
		ContentTypeAttributeInfo authorIsPC = new ContentTypeAttributeInfo();
		authorIsPC.setName("P");
		authorIsPC.setDisplayName("Program Chair Member");
		authorIsPC.addOperator(Operator.EQ);
		authorIsPC.addOperator(Operator.NEQ);
		authorIsPC.setFormat(ContentTypeFormat.BOOLEAN);
		authorIsPC.setQueryable(false);
		authorCT.addAttribute(authorIsPC);

		// is committee member:
		ContentTypeAttributeInfo authorIsCM = new ContentTypeAttributeInfo();
		authorIsCM.setName("M");
		authorIsCM.setDisplayName("Committee Member");
		authorIsCM.addOperator(Operator.EQ);
		authorIsCM.addOperator(Operator.NEQ);
		authorIsCM.setFormat(ContentTypeFormat.BOOLEAN);
		authorIsCM.setQueryable(false);
		authorCT.addAttribute(authorIsCM);

		// is general chair:
		ContentTypeAttributeInfo authorIsGC = new ContentTypeAttributeInfo();
		authorIsGC.setName("G");
		authorIsGC.setDisplayName("General Chair");
		authorIsGC.addOperator(Operator.EQ);
		authorIsGC.addOperator(Operator.NEQ);
		authorIsGC.setFormat(ContentTypeFormat.BOOLEAN);
		authorIsGC.setQueryable(false);
		authorCT.addAttribute(authorIsGC);

		// is Conference chair:
		ContentTypeAttributeInfo authorIsCC = new ContentTypeAttributeInfo();
		authorIsCC.setName("C");
		authorIsCC.setDisplayName("Conference Chair");
		authorIsCC.addOperator(Operator.EQ);
		authorIsCC.addOperator(Operator.NEQ);
		authorIsCC.setFormat(ContentTypeFormat.BOOLEAN);
		authorIsCC.setQueryable(false);
		authorCT.addAttribute(authorIsCC);

		// is Conference chair:
		ContentTypeAttributeInfo authorIsEE = new ContentTypeAttributeInfo();
		authorIsEE.setName("E");
		authorIsEE.setDisplayName("External Review");
		authorIsEE.addOperator(Operator.EQ);
		authorIsEE.addOperator(Operator.NEQ);
		authorIsEE.setFormat(ContentTypeFormat.BOOLEAN);
		authorIsEE.setQueryable(false);
		authorCT.addAttribute(authorIsEE);

		// Content Type : PUBLICATION
		ContentTypeInfo publicationCT = new ContentTypeInfo();
		publicationCT.setName(ContentType.PUBLICATION);
		// attributes of publication
		// title:
		ContentTypeAttributeInfo title = new ContentTypeAttributeInfo();
		title.setName("title");
		title.setDisplayName("Title");
		title.addOperator(Operator.EQ);
		title.addOperator(Operator.NEQ);
		title.addOperator(Operator.CONTAINS);
		title.addOperator(Operator.NOTCONTAINS);
		title.setFormat(ContentTypeFormat.TEXT);
		publicationCT.addAttribute(title);

		// attributes of publication
		// author:
		ContentTypeAttributeInfo publicationAuthor = new ContentTypeAttributeInfo();
		publicationAuthor.setName("Author");
		publicationAuthor.setDisplayName("Author");
		publicationAuthor.addOperator(Operator.EQ);
		publicationAuthor.addOperator(Operator.NEQ);
		publicationAuthor.addOperator(Operator.CONTAINS);
		publicationAuthor.addOperator(Operator.NOTCONTAINS);
		publicationAuthor.setFormat(ContentTypeFormat.TEXT);
		publicationCT.addAttribute(publicationAuthor);

		// attributes of publication
		// year:
		ContentTypeAttributeInfo year = new ContentTypeAttributeInfo();
		year.setName("year");
		year.setDisplayName("Year");
		year.addOperator(Operator.EQ);
		year.addOperator(Operator.NEQ);
		year.addOperator(Operator.GT);
		year.addOperator(Operator.LT);
		year.setFormat(ContentTypeFormat.NUMBER);
		publicationCT.addAttribute(year);

		// attributes of publication
		// volume:
		ContentTypeAttributeInfo volume = new ContentTypeAttributeInfo();
		volume.setName("volume");
		volume.setDisplayName("Volume");
		volume.addOperator(Operator.EQ);
		volume.addOperator(Operator.NEQ);
		volume.addOperator(Operator.GT);
		volume.addOperator(Operator.LT);
		volume.setFormat(ContentTypeFormat.NUMBER);
		publicationCT.addAttribute(volume);

		// attributes of publication
		// book title:
		ContentTypeAttributeInfo bookTitle = new ContentTypeAttributeInfo();
		bookTitle.setName("bookTitle");
		bookTitle.setDisplayName("Book Title");
		bookTitle.addOperator(Operator.EQ);
		bookTitle.addOperator(Operator.NEQ);
		bookTitle.addOperator(Operator.CONTAINS);
		bookTitle.addOperator(Operator.NOTCONTAINS);
		bookTitle.setFormat(ContentTypeFormat.TEXT);
		publicationCT.addAttribute(bookTitle);

		// Content Type : JOURNAL
		ContentTypeInfo journalCT = new ContentTypeInfo();
		journalCT.setName(ContentType.JOURNAL);

		// attributes of journal
		// title:
		journalCT.addAttribute(title);

		// attributes of journal
		// editor:
		ContentTypeAttributeInfo editor = new ContentTypeAttributeInfo();
		editor.setName("editors");
		editor.setDisplayName("Editors");
		editor.addOperator(Operator.EQ);
		editor.addOperator(Operator.NEQ);
		editor.addOperator(Operator.CONTAINS);
		editor.addOperator(Operator.NOTCONTAINS);
		editor.setFormat(ContentTypeFormat.TEXT);
		journalCT.addAttribute(editor);

		// attributes of publication
		// volume:
		journalCT.addAttribute(volume);

		// attributes of publication
		// year:
		journalCT.addAttribute(year);

		// attributes of journal
		// isbn:
		ContentTypeAttributeInfo isbn = new ContentTypeAttributeInfo();
		isbn.setName("isbn");
		isbn.setDisplayName("ISBN");
		isbn.addOperator(Operator.EQ);
		isbn.addOperator(Operator.NEQ);
		isbn.addOperator(Operator.CONTAINS);
		isbn.addOperator(Operator.NOTCONTAINS);
		isbn.setFormat(ContentTypeFormat.TEXT);
		journalCT.addAttribute(isbn);

		// attributes of journal
		// book title:
		journalCT.addAttribute(bookTitle);

		// attributes of journal
		// Publisher:
		ContentTypeAttributeInfo publisher = new ContentTypeAttributeInfo();
		publisher.setName("publisher");
		publisher.setDisplayName("Publisher");
		publisher.addOperator(Operator.EQ);
		publisher.addOperator(Operator.NEQ);
		publisher.addOperator(Operator.CONTAINS);
		publisher.addOperator(Operator.NOTCONTAINS);
		publisher.setFormat(ContentTypeFormat.TEXT);
		journalCT.addAttribute(publisher);

		// attributes of journal
		// Publisher:
		ContentTypeAttributeInfo seriesTitle = new ContentTypeAttributeInfo();
		seriesTitle.setName("seriesTitle");
		seriesTitle.setDisplayName("Series Title");
		seriesTitle.addOperator(Operator.EQ);
		seriesTitle.addOperator(Operator.NEQ);
		seriesTitle.addOperator(Operator.CONTAINS);
		seriesTitle.addOperator(Operator.NOTCONTAINS);
		seriesTitle.setFormat(ContentTypeFormat.TEXT);
		journalCT.addAttribute(seriesTitle);

		// -------------------------------------------------------------

		// Content Type : CONFERENCE
		ContentTypeInfo conferenceCT = new ContentTypeInfo();
		conferenceCT.setName(ContentType.CONFERENCE);

		// attributes of conference
		// title:
		conferenceCT.addAttribute(title);

		// attributes of conference
		// editor:
		conferenceCT.addAttribute(editor);

		// attributes of publication
		// volume:
		conferenceCT.addAttribute(volume);

		// attributes of publication
		// year:
		conferenceCT.addAttribute(year);

		// attributes of conference
		// isbn:
		conferenceCT.addAttribute(isbn);

		// attributes of conference
		// book title:
		conferenceCT.addAttribute(bookTitle);

		// attributes of conference
		// Publisher:
		conferenceCT.addAttribute(publisher);

		// attributes of conference
		// Publisher:
		conferenceCT.addAttribute(seriesTitle);

		contentTypeInfos.add(authorCT);
		contentTypeInfos.add(publicationCT);
		contentTypeInfos.add(journalCT);
		contentTypeInfos.add(conferenceCT);

		return contentTypeInfos;
	}

}
