package nl.project34.henk.client;

import nl.project34.henk.api.WithdrawRequest;

import java.awt.*;
import java.awt.print.*;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Printer implements Printable
{
	//********FIELDS********//

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	Date date = new Date();

	static ClientDatabase db;
	static WithdrawRequest request;

	private String transactieNummer	= "xxxxxx";
	private String bankNaam 		= "Bankalicious";
	private static String 			afschrijving;
	private String datumTijd 		=  dateFormat.format(date);
	private String locatie 			= "Wijnhaven 107,";
	private String rekeningNummer 	= "**********1230";

	public Printer(ClientDatabase db, String s)
	{
		this.db = db;
		afschrijving = s;

		transactieNummer = db.getTransactieNummer();
		rekeningNummer = db.getRekeningNummer();
		System.out.println("de afschrijving op de bon is: " + afschrijving);







	}



	//********METHODS********//
	public int print(Graphics text, PageFormat pageFormat, int pageNumber) throws PrinterException
	{
		System.out.println("PRINTING");

		if(pageNumber > 0)
		{
			return NO_SUCH_PAGE;
		}

		Graphics2D bon =  (Graphics2D) text;
		bon.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
		text.drawString(bankNaam, 50, 10);
		text.drawString("Transaction Number:", 20, 30);
		text.drawString(transactieNummer, 20, 43);
		text.drawString("Afschrijving:", 20, 63);
		text.drawString(afschrijving, 20, 76);
		text.drawString("Datum en tijd: ", 20, 96);
		text.drawString(datumTijd, 20, 109);
		text.drawString("Locatie: ", 20, 129);
		text.drawString(locatie, 20, 142);
		text.drawString("rekening nummer: ", 20, 162);
		text.drawString(rekeningNummer, 20, 175);

		
		return PAGE_EXISTS;
	}

	public static void printSettings()
	{

		double 		paperHeight = (3.97637795275591 * 72);
		double 		paperWidth 	= (2.13 * 72);
		PrinterJob 	printerJob	= PrinterJob.getPrinterJob();
		PageFormat 	pageFormat 	= printerJob.defaultPage();
		Paper 		paper 		= new Paper();

		paper.setSize(paperWidth, paperHeight);
		paper.setImageableArea(0, 0, paperWidth, paperHeight);
		pageFormat.setPaper(paper);
		pageFormat.setOrientation(PageFormat.PORTRAIT);


		printerJob.setPrintable(new Printer(db, afschrijving), pageFormat);

		try
		{
			printerJob.print();
		}
		catch(PrinterException px)
		{
			System.out.println("Could not manage to print label");
		}
	}
}
