package Reportes;

import Modelo.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JOptionPane;

public class ReporteGolesSimple {

    
    public void generarReporteGoles(List<Gol> goles, int idPartido){
        
        Document documento = new Document();
        
        try {
            String nombreArchivo = "Goles_Partido_" + idPartido + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();
            
            // --- Título y Fecha ---
            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph titulo = new Paragraph("Listado de Goles - Partido ID: " + idPartido, tituloFont);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documento.add(titulo);
            
            // ... (Agregar la fecha de generación como lo hiciste antes)
            
            documento.add(new Paragraph("\n"));
            
            // --- Tabla de Datos ---
            if(goles.isEmpty()){
                documento.add(new Paragraph("No se registraron goles para el Partido ID " + idPartido + "."));
            } else {
                PdfPTable tabla = new PdfPTable(2); // idGol, minuto
                tabla.setWidthPercentage(50); // Tabla más pequeña
                
                // Encabezados
                tabla.addCell(new Phrase("ID Gol", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
                tabla.addCell(new Phrase("Minuto", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));

                // Llenar filas
                for(Gol g : goles){
                    tabla.addCell(String.valueOf(g.getIdGol()));
                    tabla.addCell(String.valueOf(g.getMinuto()));
                }
                
                documento.add(tabla);
            }
            
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte de Goles generado con éxito.");
            Desktop.getDesktop().open(new File(nombreArchivo));
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage());
        }
    }
} 
    

