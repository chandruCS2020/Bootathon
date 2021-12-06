/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billing;
/**
 *
 * @author chandru
 */
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import com.itextpdf.kernel.pdf.PdfDocument; 

import com.itextpdf.layout.element.Cell; 
import com.itextpdf.layout.element.Table; 
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class Pdf {
     static Font blue ;

    
   public  void writeUsingIText(String name,String email,int bill_no,String cargo_type,String from,String to,double amount) {
        
        String FILE_NAME = "D:\\New folder\\"+name+".pdf";

        Document document = new Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
            document.setPageSize(PageSize.A6);
            //open
            document.open();
            
//            border
            Rectangle rect= new Rectangle(280,405,18,15); // you can resize rectangle 
            rect.enableBorderSide(1);
            rect.enableBorderSide(2);
            rect.enableBorderSide(4);
            rect.enableBorderSide(8);
            rect.setBorderColor(BaseColor.BLACK);
            rect.setBorderWidth(1);
            document.add(rect);
            
//            title
            Paragraph Title= new Paragraph();
            blue= new Font(FontFamily.HELVETICA, 15, Font.BOLD | Font.UNDERLINE, BaseColor.RED);
            Chunk Titlep = new Chunk("Cargo Logistics Managements", blue);
            Title.add(Titlep);
            Title.setAlignment(Element.ALIGN_CENTER);
            document.add(Title);
            Paragraph p2 = new Paragraph();
            blue  = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
            Chunk username = new Chunk("NAME       :  "+name, blue);
            p2.add(username);
            p2.setAlignment(Element.ALIGN_LEFT);
            p2.setLeading(50);
            document.add(p2);
            
            Paragraph p3 = new Paragraph();
            blue  = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
            Chunk useremail = new Chunk("EMAIL      :  "+email, blue);
            p3.add(useremail);
            p3.setAlignment(Element.ALIGN_LEFT);
            p3.setLeading(25);
            document.add(p3);
            
            Paragraph p4 = new Paragraph();
            blue  = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
            Chunk billno = new Chunk("BILL NO   :  "+bill_no, blue);
            p4.add(billno);
            p4.setAlignment(Element.ALIGN_LEFT);
            p4.setLeading(25);
            document.add(p4);
            
            
            Paragraph p5 = new Paragraph();
            blue  = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now(); 
            Chunk date = new Chunk("Date          :  "+dtf.format(now), blue);
            p5.add(date);
            p5.setAlignment(Element.ALIGN_LEFT);
            p5.setLeading(25);
            p5.setExtraParagraphSpace(2);
            document.add(p5);
            
            
            BaseFont bf = BaseFont.createFont(
                        BaseFont.TIMES_ROMAN,
                        BaseFont.CP1252,
                        BaseFont.EMBEDDED);
                Font font = new Font(bf, 8);
                Font font1 = new Font(bf, 6);
            PdfPTable table = new PdfPTable(6);
            PdfPCell cell = new PdfPCell(new Phrase("S.NO",font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("PRODUCT",font));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
            cell= new PdfPCell(new Phrase("FROM/TO",font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("PRICE",font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("1",font1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(cargo_type,font1));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(from+"-"+to,font1));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell= new PdfPCell(new Phrase(Double.toString(amount),font1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            table.setSpacingAfter(10);
            table.setSpacingBefore(25);
            document.add(table);              
         
         

//            Font f = new Font();
//            f.setStyle(Font.BOLD);
//            f.setSize(8);
//
//            document.add(new Paragraph(str, f));

            //close
            document.close();

            System.out.println("Done");
            sendAttachment s=new sendAttachment(email,name);
         
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
       new Pdf();
    }

}