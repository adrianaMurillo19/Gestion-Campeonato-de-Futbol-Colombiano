package Reportes;

import DTO.PartidoRangoFechaDTO;
import java.util.Date;
import java.util.List;
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

public class ReporteIntermedioPartidosFecha {
    public void generarReportePartidosPorFechas(List<PartidoRangoFechaDTO> partidos, Date fechaInicio, Date fechaFin) {

        Document documento = new Document();

        try {
            // Nombre del archivo PDF
            String nombreArchivo = "Reporte_Partidos_" 
                    + new java.sql.Date(fechaInicio.getTime()) + "_a_" 
                    + new java.sql.Date(fechaFin.getTime()) + ".pdf";

            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            // ----- Título -----
            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            String tituloTexto = "REPORTE DE PARTIDOS ENTRE FECHAS\n"
                    + "Desde: " + fechaInicio + "   Hasta: " + fechaFin;

            Paragraph titulo = new Paragraph(tituloTexto, tituloFont);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documento.add(titulo);

            // Fecha de creación del documento
            Paragraph fecha = new Paragraph(
                    "Fecha generación: " 
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
            );
            fecha.setAlignment(Paragraph.ALIGN_RIGHT);
            documento.add(fecha);

            documento.add(new Paragraph("\n"));

            // ----- Contenido -----
            if (partidos.isEmpty()) {
                documento.add(new Paragraph("No se encontraron partidos en el rango de fechas indicado."));
            } else {

                PdfPTable tabla = new PdfPTable(5); 
                tabla.setWidthPercentage(100);

                // Encabezados
                tabla.addCell("ID Partido");
                tabla.addCell("Fecha Juego");
                tabla.addCell("Hora Inicio");
                tabla.addCell("Resultado Final");
                tabla.addCell("Nombre del Estadio");

                // Filas
                for (PartidoRangoFechaDTO p : partidos) {
                    tabla.addCell(String.valueOf(p.getIdPartido()));
                    tabla.addCell(p.getFechaJuego().toString());
                    tabla.addCell(p.getHoraInicio());
                    tabla.addCell(p.getResultadoFinal() != null ? p.getResultadoFinal() : "—");
                    tabla.addCell(p.getNombreEstadio());
                }

                documento.add(tabla);
            }

            documento.close();

            JOptionPane.showMessageDialog(null, "Reporte generado con éxito.");
            Desktop.getDesktop().open(new File(nombreArchivo));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage());
        }
    }

}
