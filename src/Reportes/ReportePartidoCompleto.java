
package Reportes;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Modelo.PartidoCompletoDTO;
import Modelo.*;


import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

public class ReportePartidoCompleto {

    public void generarPDF(PartidoCompletoDTO partido, String rutaSalida) {
        Document documento = new Document();

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(rutaSalida));
            documento.open();

            // Título
            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            Paragraph titulo = new Paragraph("REPORTE COMPLETO DEL PARTIDO", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            documento.add(titulo);

            Font tituloSec = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // ======================= INFORMACIÓN GENERAL ======================
            Paragraph infoGeneral = new Paragraph("Información General\n", tituloSec);
            infoGeneral.setSpacingAfter(10);
            documento.add(infoGeneral);

            PdfPTable tablaGeneral = new PdfPTable(2);
            tablaGeneral.setWidthPercentage(100);

            tablaGeneral.addCell("ID Partido:");
            tablaGeneral.addCell(String.valueOf(partido.getIdPartido()));

            tablaGeneral.addCell("Fecha:");
            tablaGeneral.addCell(sdf.format(partido.getFechaJuego()));

            tablaGeneral.addCell("Hora de Inicio:");
            tablaGeneral.addCell(partido.getHoraInicio().toString());

            tablaGeneral.addCell("Resultado Final:");
            tablaGeneral.addCell(partido.getResultadoFinal());

            tablaGeneral.addCell("Estadio:");
            tablaGeneral.addCell(partido.getEstadio().getNombre());

            tablaGeneral.addCell("Ciudad:");
            tablaGeneral.addCell(partido.getCiudad().getNombreCiudad());

            tablaGeneral.addCell("Árbitro:");
            tablaGeneral.addCell(partido.getArbitro().getNombreUno());

            documento.add(tablaGeneral);
            documento.add(Chunk.NEWLINE);

            // ======================= EQUIPOS ======================
            Paragraph infoEquipos = new Paragraph("Equipos participantes\n", tituloSec);
            infoEquipos.setSpacingAfter(10);
            documento.add(infoEquipos);

            PdfPTable tablaEquipos = new PdfPTable(1);
            tablaEquipos.setWidthPercentage(100);

            partido.getEquipos().forEach(e ->
                tablaEquipos.addCell(e.getNombre())
            );

            documento.add(tablaEquipos);
            documento.add(Chunk.NEWLINE);

            // ======================= GOLES ======================
            Paragraph infoGoles = new Paragraph("Goles\n", tituloSec);
            infoGoles.setSpacingAfter(10);
            documento.add(infoGoles);

            if (partido.getGoles().isEmpty()) {
                documento.add(new Paragraph("No hubo goles."));
            } else {
                PdfPTable tablaGoles = new PdfPTable(1);
                tablaGoles.setWidthPercentage(100);
                tablaGoles.addCell("Minuto del gol");

                for (Gol g : partido.getGoles()) {
                    tablaGoles.addCell("Minuto: " + g.getMinuto());
                }
                documento.add(tablaGoles);
            }

            documento.add(Chunk.NEWLINE);

            // ======================= TARJETAS ======================
            Paragraph infoTarjetas = new Paragraph("Tarjetas\n", tituloSec);
            infoTarjetas.setSpacingAfter(10);
            documento.add(infoTarjetas);

            if (partido.getTarjetas().isEmpty()) {
                documento.add(new Paragraph("No hubo tarjetas."));
            } else {
                PdfPTable tablaTarjetas = new PdfPTable(1);
                tablaTarjetas.setWidthPercentage(100);
                tablaTarjetas.addCell("Color de tarjeta");

                for (Tarjeta t : partido.getTarjetas()) {
                    tablaTarjetas.addCell("Tarjeta: " + t.getColor());
                }
                documento.add(tablaTarjetas);
            }

            documento.add(Chunk.NEWLINE);

            // ======================= SUSTITUCIONES ======================
            Paragraph infoSust = new Paragraph("Sustituciones\n", tituloSec);
            infoSust.setSpacingAfter(10);
            documento.add(infoSust);

            if (partido.getSustituciones().isEmpty()) {
                documento.add(new Paragraph("No hubo sustituciones."));
            } else {
                PdfPTable tablaSust = new PdfPTable(1);
                tablaSust.setWidthPercentage(100);

                for (Sustitucion s : partido.getSustituciones()) {
                    tablaSust.addCell(s.getDescripcion());
                }
                documento.add(tablaSust);
            }

            documento.add(Chunk.NEWLINE);
            documento.add(new Paragraph("Reporte generado correctamente."));

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            documento.close();
        }
    }
}
