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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;

public class ReporteIntermedioJugadores {
    public void generarReporteJugadores(List<Jugador> jugadores, int idEquipo, String nombreEquipo){
        
        Document documento = new Document();
        
        try {
            String nombreArchivo = "Jugadores_Equipo_" + idEquipo + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();
            
            // --- Título y Fecha ---
            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph titulo = new Paragraph("Listado de jugadores  - Equipo: " + nombreEquipo + " , ID: " + idEquipo, tituloFont);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documento.add(titulo);
            
            //fecha que se genera el reporte
            Paragraph fecha = new Paragraph ("Fecha: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            fecha.setAlignment(Paragraph.ALIGN_RIGHT);
            documento.add(fecha);
            
            documento.add(new Paragraph("\n"));
            
            // --- Tabla de Datos ---
            if(jugadores.isEmpty()){
                documento.add(new Paragraph("No se registraron jugadores para el equipo " + nombreEquipo + "."));
            } else {
                PdfPTable tabla = new PdfPTable(5); // cantidad de Campos -> nombreUno, nombreDos, apellidoUno,
                                                    // apellidoDos, numCamiseta
                tabla.setWidthPercentage(100);
                
                tabla.addCell("Primer nombre");
                tabla.addCell("Segundo nombre");
                tabla.addCell("Primer apellido");
                tabla.addCell("Segundo apellido");
                tabla.addCell("Numero de Camiseta");
                
                for(Jugador j : jugadores){
                    
                    tabla.addCell(j.getNombreUno());
                    tabla.addCell(j.getNombreDos());
                    tabla.addCell(j.getApellidoUno());
                    tabla.addCell(j.getApellidoDos());
                    tabla.addCell(String.valueOf(j.getNumCamiseta()));
                }
                
                documento.add(tabla);
            }
            
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte de Jugadores del equipo " + nombreEquipo + " generado con éxito.");
            Desktop.getDesktop().open(new File(nombreArchivo));
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage());
        }
    }
}
