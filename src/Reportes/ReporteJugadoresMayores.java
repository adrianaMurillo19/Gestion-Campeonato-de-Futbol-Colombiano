
package Reportes;

import Modelo.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;

public class ReporteJugadoresMayores {
    
    public void generarReporteJugadoresMayores(List<Jugador> jugadores, int edadMinima){
        
        Document documento = new Document();
        
        try {
            String nombreArchivo = "Jugador_Mayores_A_" + edadMinima + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();
            
            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph titulo = new Paragraph("Listado de Jugadores mayores a " + edadMinima + " años", tituloFont);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documento.add(titulo);
            
            //fecha que se genera el reporte
            Paragraph fecha = new Paragraph ("Fecha: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            fecha.setAlignment(Paragraph.ALIGN_RIGHT);
            documento.add(fecha);
            
            documento.add(new Paragraph("\n"));
            
            if(jugadores.isEmpty()){
                documento.add(new Paragraph("No hay Jugadores que cumplan la condición."));
            }else{
                PdfPTable tabla = new PdfPTable(4); // cantidad de Campos -> Fecha Nac, Género, Edad
                tabla.setWidthPercentage(100);
                
                tabla.addCell("Primer nombre");
                tabla.addCell("Primer apellido");
                tabla.addCell("Fecha de nacimiento");
                tabla.addCell("Edad");
                
                for(Jugador j : jugadores){
                    
                    tabla.addCell(j.getNombreUno());
                    tabla.addCell(j.getApellidoUno());
                    tabla.addCell(j.getFechaNacimiento().toString());
                    tabla.addCell(String.valueOf(j.getEdad()));
                }
                
                documento.add(tabla);
            }
            
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte generado con éxito");
            Desktop.getDesktop().open(new File(nombreArchivo));
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void abrirPDF(String archivo){
        
        try {
            Desktop.getDesktop().open(new File(archivo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
