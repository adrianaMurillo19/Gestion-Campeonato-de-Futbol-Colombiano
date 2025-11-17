
package Reportes;


import Modelo.Goleador;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JOptionPane;

public class ReporteGoleadoresTop {

    public void generarPdfTop(List<Goleador> lista, String rutaSalida) {
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(rutaSalida));
            doc.open();

            Font title = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph p = new Paragraph("Top 10 Máximos Goleadores", title);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(12);
            doc.add(p);

            doc.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(new float[]{1, 5, 4, 2}); // columnas proporcionales
            table.setWidthPercentage(100);
            table.addCell(new Phrase("ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            table.addCell(new Phrase("Jugador", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            table.addCell(new Phrase("Equipo", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            table.addCell(new Phrase("Goles", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));

            if (lista.isEmpty()) {
                table.addCell("-");
                table.addCell("No hay registros");
                table.addCell("-");
                table.addCell("0");
            } else {
                for (Goleador g : lista) {
                    table.addCell(String.valueOf(g.getIdJugador()));
                    table.addCell(g.getNombreCompleto());
                    table.addCell(g.getEquipo());
                    table.addCell(String.valueOf(g.getTotalGoles()));
                }
            }

            doc.add(table);
            doc.close();

            JOptionPane.showMessageDialog(null, "PDF generado: " + rutaSalida);
            Desktop.getDesktop().open(new File(rutaSalida));

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar PDF: " + ex.getMessage());
        } finally {
            if (doc.isOpen()) doc.close();
        }
    }
}
