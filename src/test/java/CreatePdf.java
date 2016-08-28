/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joao
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


public class CreatePdf {
    
    private StreamedContent content;  
    
    public static void main(String[] args) throws FileNotFoundException, DocumentException
     {

//               Document document = new Document();
//                @SuppressWarnings("unused")
//                PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
//                document.open();
//                 
//                Phrase phrase = new Phrase();
//                for(int count = 0;count<10;count++)
//                {
//
//                        Chunk chunk = new Chunk("Hello World!!");
//                        phrase.add(chunk);
//                        
//                }
//                document.add(phrase);
//                document.close();
                
                try {
              ByteArrayOutputStream out = new ByteArrayOutputStream();  
  
            Document documentPRe = new Document();  
            PdfWriter.getInstance(documentPRe, out);  
            documentPRe.open();  
  
            for (int i = 0; i < 50; i++) {  
                documentPRe.add(new Paragraph("All work and no play makes Jack a dull boy"));  
            }  
              
            documentPRe.close();  
          //  content = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/pdf");  
         } catch (Exception e) {
             e.printStackTrace();
         }
               

        }
         
    public StreamedContent getContent() {  
        return content;  
    }  
  
    public void setContent(StreamedContent content) {  
        this.content = content;  
    }  
    
}
