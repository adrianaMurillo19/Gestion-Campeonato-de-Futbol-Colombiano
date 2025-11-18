package Reportes;

import DTO.EquipoDirectorCiudadDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;


public class ReporteIntermedioEquipoDTCiudad {
    public void generarReporteEquiposDT(List<EquipoDirectorCiudadDTO> lista) {

        Document documento = new Document();

        try {
            String nombreArchivo = "Reporte_Equipos_DirectorTecnico.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            // --- Título y Fecha ---
            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph titulo = new Paragraph("Listado de Equipos, Director Técnico y Ciudad", tituloFont);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documento.add(titulo);

            Paragraph fecha = new Paragraph(
                    "Fecha: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
            );
            fecha.setAlignment(Paragraph.ALIGN_RIGHT);
            documento.add(fecha);

            documento.add(new Paragraph("\n"));

            // --- Tabla de Datos ---
            if (lista.isEmpty()) {
                documento.add(new Paragraph("No existen equipos registrados para este reporte."));
            } else {
                PdfPTable tabla = new PdfPTable(3); // 3 columnas -> Equipo, DT, Ciudad
                tabla.setWidthPercentage(100);

                // Fuente negrilla para encabezados
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

                // Encabezados con estilo
                PdfPCell celdaEquipo = new PdfPCell(new Phrase("Equipo", headerFont));
                PdfPCell celdaDT = new PdfPCell(new Phrase("Director Técnico", headerFont));
                PdfPCell celdaCiudad = new PdfPCell(new Phrase("Ciudad del Equipo", headerFont));

                tabla.addCell(celdaEquipo);
                tabla.addCell(celdaDT);
                tabla.addCell(celdaCiudad);

                // Filas de datos
                for (EquipoDirectorCiudadDTO dto : lista) {
                    tabla.addCell(dto.getNombreEquipo());
                    tabla.addCell(dto.getNombreDT());
                    tabla.addCell(dto.getCiudad());
                }

                documento.add(tabla);
            }

            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte de Equipos generado con éxito.");
            Desktop.getDesktop().open(new File(nombreArchivo));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage());
        }
    }
}
