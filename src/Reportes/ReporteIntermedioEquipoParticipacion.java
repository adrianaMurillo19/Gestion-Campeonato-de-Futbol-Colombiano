package Reportes;

import DTO.EquipoParticipacionDTO;
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

public class ReporteIntermedioEquipoParticipacion {
    public void generarReporteEquipoConPartidos(List<EquipoParticipacionDTO> lista) {

        Document documento = new Document();

        try {
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay información registrada para este equipo.");
                return;
            }

            // Extrae una sola vez los datos del equipo (la primera fila)
            EquipoParticipacionDTO base = lista.get(0);

            String nombreArchivo = "Reporte_Equipo_" + base.getNombreEquipo().replace(" ", "_") + "_Partidos.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            // Titulo
            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph titulo = new Paragraph("Reporte del Equipo: " + base.getNombreEquipo(), tituloFont);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documento.add(titulo);

            // Fecha
            Paragraph fecha = new Paragraph(
                    "Fecha: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
            );
            fecha.setAlignment(Paragraph.ALIGN_RIGHT);
            documento.add(fecha);

            documento.add(new Paragraph("\n"));

            // Datos del equipo (Solo una fila)

            Font negrilla = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPTable tablaEquipo = new PdfPTable(4);
            tablaEquipo.setWidthPercentage(100);

            tablaEquipo.addCell(new PdfPCell(new Phrase("ID Equipo", negrilla)));
            tablaEquipo.addCell(new PdfPCell(new Phrase("Nombre", negrilla)));
            tablaEquipo.addCell(new PdfPCell(new Phrase("Puntos", negrilla)));
            tablaEquipo.addCell(new PdfPCell(new Phrase("Dif. Goles", negrilla)));

            tablaEquipo.addCell(String.valueOf(base.getIdEquipo()));
            tablaEquipo.addCell(base.getNombreEquipo());
            tablaEquipo.addCell(String.valueOf(base.getPuntos()));
            tablaEquipo.addCell(String.valueOf(base.getDiferenciaGoles()));

            documento.add(tablaEquipo);

            documento.add(new Paragraph("\n\n")); // Espacio entre tablas

            // Subtitulo para tabla de partidos
            
            Font subtituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Paragraph subtitulo = new Paragraph("Partidos:");
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documento.add(subtitulo);
            documento.add(new Paragraph("\n"));
            
            // Tabla: Partidos

            PdfPTable tablaPartidos = new PdfPTable(1); // Solo una columna: fecha
            tablaPartidos.setWidthPercentage(50);

            tablaPartidos.addCell(new PdfPCell(new Phrase("Fecha Partido", negrilla)));

            for (EquipoParticipacionDTO dto : lista) {
                tablaPartidos.addCell(dto.getFechaJuego() != null
                        ? dto.getFechaJuego().toString()
                        : "Sin fecha");
            }

            documento.add(tablaPartidos);


            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte generado con éxito.");
            Desktop.getDesktop().open(new File(nombreArchivo));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage());
        }
    }

}
