package com.binarfud.proplayer.challange5.services;

import com.binarfud.proplayer.challange5.dto.merchant.response.OrderDTO;
import com.binarfud.proplayer.challange5.dto.merchant.response.ReportDTO;
import com.binarfud.proplayer.challange5.dto.merchant.response.ReportItemDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class InvoiceService {


    public byte[] generateReportingMerchant(ReportDTO reportDTO) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            addMerchantReportContentToPdf(document, reportDTO);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document != null && document.isOpen()) {
                document.close();
            }
        }

        return byteArrayOutputStream.toByteArray();
    }

    private void addMerchantReportContentToPdf(Document document, ReportDTO reportDTO) throws DocumentException {
        // Add title
        document.add(new Paragraph("Merchant Report\n\n"));

        // Iterate through reported items
        for (ReportItemDTO reportItemDTO : reportDTO.getReported()) {
            String header = String.format("Minggu Ke: %d\nBulan Ke: %d\nTotal Pendapatan: %.2f\n\n",
                    reportItemDTO.getWeekNumber(), reportItemDTO.getMonthNumber(), reportItemDTO.getTotalIncome());
            document.add(new Paragraph(header));
            // Add table
            PdfPTable table = new PdfPTable(6);
            table.addCell(new PdfPCell(new Paragraph("Order ID")));
            table.addCell(new PdfPCell(new Paragraph("Order Time")));
            table.addCell(new PdfPCell(new Paragraph("Destination Address")));
            table.addCell(new PdfPCell(new Paragraph("Completed")));
            table.addCell(new PdfPCell(new Paragraph("Quantity")));
            table.addCell(new PdfPCell(new Paragraph("Product Name")));

            // Iterate
            for (OrderDTO orderDTO : reportItemDTO.getOrders()) {
                table.addCell(new PdfPCell(new Paragraph(String.valueOf(orderDTO.getOrder().getId()))));
                table.addCell(new PdfPCell(new Paragraph(String.valueOf(orderDTO.getOrder().getOrder_time()))));
                table.addCell(new PdfPCell(new Paragraph(orderDTO.getOrder().getDestinationAddress())));
                table.addCell(new PdfPCell(new Paragraph(String.valueOf(orderDTO.getOrder().getCompleted()))));
                table.addCell(new PdfPCell(new Paragraph(String.valueOf(orderDTO.getOrder().getOrderDetails().get(0).getQuantity()))));
                table.addCell(new PdfPCell(new Paragraph(orderDTO.getOrder().getOrderDetails().get(0).getProduct().getProductName())));
            }

            document.add(table);
            document.add(new Paragraph("\n"));
        }
    }
}