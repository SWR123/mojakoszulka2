package com.example.mojakoszulka2.util

import android.content.Context
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import com.example.mojakoszulka2.data.OrderData
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class PdfGenerator {
    @Throws(IOException::class)
    fun generatePdf(context: Context, orderData: OrderData): File {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        val canvas = page.canvas
        val paint = Paint().apply {
            textSize = 14f
        }

        try {
            var yPos = 50
            canvas.drawText("Zamówienie - mojakoszulka.com", 50f, yPos.toFloat(), paint)
            yPos += 30
            canvas.drawText("Kolor koszulki: ${orderData.shirtColor}", 50f, yPos.toFloat(), paint)
            yPos += 30
            canvas.drawText("Rozmiar: ${orderData.shirtSize}", 50f, yPos.toFloat(), paint)
            yPos += 30

            pdfDocument.finishPage(page)

            val directory = context.getExternalFilesDir(null) ?: context.filesDir
            val file = File(directory, "zamowienie.pdf")

            FileOutputStream(file).use { fos ->
                pdfDocument.writeTo(fos)
            }

            return file
        } finally {
            pdfDocument.close()
        }
    }
} 