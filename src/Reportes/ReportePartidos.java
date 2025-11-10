
package Reportes;

import Modelo.Partido;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;

public class ReportePartidos {
    
    public void generarReportePartidos(List<Partido> partidos){
        
        Document documento = new Document(PageSize.A4.rotate()); // Se usa horizontal (Landscape) por la cantidad de columnas
        
        try {
            String nombreArchivo = "Calendario_Partidos_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm")) + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();
            
            // --- Título y Fecha ---
            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph titulo = new Paragraph("CALENDARIO COMPLETO DE PARTIDOS", tituloFont);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documento.add(titulo);
            
            Paragraph fecha = new Paragraph ("Fecha Generación: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            fecha.setAlignment(Paragraph.ALIGN_RIGHT);
            documento.add(fecha);
            
            documento.add(new Paragraph("\n"));
            
            // --- Tabla de Datos ---
            if(partidos.isEmpty()){
                documento.add(new Paragraph("No hay partidos programados en la base de datos."));
            } else {
                // Creamos la tabla con 6 columnas (idPartido, fechaJuego, horaInicio, resultadoFinal, Estadio_idEstadio, Arbitro_idUsuario)
                PdfPTable tabla = new PdfPTable(6); 
                tabla.setWidthPercentage(100);
                
                // Establecer encabezados
                tabla.addCell(new Phrase("ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
                tabla.addCell(new Phrase("Fecha", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
                tabla.addCell(new Phrase("Hora", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
                tabla.addCell(new Phrase("Resultado", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
                tabla.addCell(new Phrase("ID Estadio", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
                tabla.addCell(new Phrase("ID Árbitro", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));

                // Llenar filas
                for(Partido p : partidos){
                    tabla.addCell(String.valueOf(p.getIdPartido()));
                    // Formatear la fecha para mejor visualización
                    tabla.addCell(p.getFechaJuego() != null ? p.getFechaJuego().toString() : "");
                    tabla.addCell(p.getHoraInicio() != null ? p.getHoraInicio().toString() : "");
                    tabla.addCell(p.getResultadoFinal());
                    tabla.addCell(String.valueOf(p.getEstadio_idEstadio())); 
                    tabla.addCell(String.valueOf(p.getArbitro_idUsuario())); 
                }
                
                documento.add(tabla);
            }
            
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte de Calendario de Partidos generado con éxito.");
            Desktop.getDesktop().open(new File(nombreArchivo));
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage());
        }
    }
}
