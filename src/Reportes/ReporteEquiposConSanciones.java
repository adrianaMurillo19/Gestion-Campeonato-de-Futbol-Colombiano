
package Reportes;

import Modelo.EquipoSanciones;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.List;

public class ReporteEquiposConSanciones {
    
    public void generarReporte(List<EquipoSanciones> equipos, double promedio, String rutaSalida) {

        Document documento = new Document();

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(rutaSalida));
            documento.open();

            // ---------- TÍTULO ----------
            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            Paragraph titulo = new Paragraph("Equipos con Mayor Índice de Sanciones", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            documento.add(titulo);

            // ---------- PROMEDIO ----------
            Font textoNormal = new Font(Font.FontFamily.HELVETICA, 12);
            documento.add(new Paragraph("Promedio general de sanciones por equipo: " + promedio, textoNormal));
            documento.add(Chunk.NEWLINE);

            // ---------- TABLA ----------
            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100);

            tabla.addCell("Equipo");
            tabla.addCell("Total Sanciones");
            tabla.addCell("Sobre el Promedio");

            for (EquipoSanciones eq : equipos) {

                tabla.addCell(eq.getNombreEquipo());
                tabla.addCell(String.valueOf(eq.getTotalSanciones()));

                String indicador = (eq.getTotalSanciones() > promedio)
                        ? "Sí"
                        : "No";

                tabla.addCell(indicador);
            }

            documento.add(tabla);

            documento.add(Chunk.NEWLINE);
            documento.add(new Paragraph("Reporte generado correctamente."));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            documento.close();
        }
    }
    
}
