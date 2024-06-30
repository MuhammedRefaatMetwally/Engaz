package com.example.engaz.features.home.view.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.R
import com.ramcosta.composedestinations.annotation.Destination
import java.io.File
import java.io.FileOutputStream

@Destination()
@Composable
fun LicenseCardApp() {
    var selectedBackground by remember { mutableStateOf(1) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { selectedBackground = 1 }) {
                Text("Background 1")
            }
            Button(onClick = { selectedBackground = 2 }) {
                Text("Background 2")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.Black),

        ) {
            Box {
                if (selectedBackground == 1) {
                    LicenseCardTW()
                } else {
                    LicenseCardContenttw()
                }

                LicenseCardContent()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            createPdfFromComposable(context) {
                LicenseCardTW()
            }
        }) {
            Text("Download as PDF")
        }
    }
}

@Composable
fun LicenseCardContent() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "جمهورية مصر العربية",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "وزارة الداخلية",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        // Add more Text and layout elements here as needed
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "عميد / هشام دياب",
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.End)
        )
    }
}
@Composable
fun LicenseCardTW() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Black),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "جمهورية مصر العربية",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "وزارة الداخلية",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "وحدة مرور المنصورة",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "إدارة مرور الدقهلية",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "جمال عبد الناصر السيد إبراهيم", fontSize = 14.sp)
                Text(text = "أ", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "رخصة تسيير ملاكي", fontSize = 14.sp)
                Text(text = "د", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "مصري", fontSize = 14.sp)
                Text(text = "ح", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "كفر صميره القديم مركز طلخا", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "نهاية الترخيص", fontSize = 14.sp)
                Text(text = "15-10-2027", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "تاريخ التحرير", fontSize = 14.sp)
                Text(text = "16-06-2024", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "عميد / هشام دياب",
                fontSize = 14.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun LicenseCardContenttw() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "تويوتا كورولا",
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = "شاسية: RKLBB0BE6L0033541",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = "موتور: 629250",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = "3 \u0644\u064A\u062A\u0631 \u0643\u0648\u062A\u0631 \u0643\u0628\u064A\u0631",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = "تاريخ الفحص: 2027",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = "H 2333146",
            fontSize = 14.sp,
            color = Color.Red,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

fun createPdfFromComposable(context: Context, composableContent: @Composable () -> Unit) {
    val pageWidth = 595  // A4 size in points (1 point = 1/72 inch)
    val pageHeight = 842

    // Create a bitmap and a canvas to draw on it
    val bitmap = Bitmap.createBitmap(pageWidth, pageHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    // Create a ComposeView and set the Composable content
    val composeView = ComposeView(context).apply {
        setContent {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                composableContent()
            }
        }
    }

    // Measure and layout the ComposeView
    composeView.measure(
        View.MeasureSpec.makeMeasureSpec(pageWidth, View.MeasureSpec.EXACTLY),
        View.MeasureSpec.makeMeasureSpec(pageHeight, View.MeasureSpec.EXACTLY)
    )
    composeView.layout(0, 0, pageWidth, pageHeight)

    // Draw the ComposeView onto the bitmap
    composeView.draw(canvas)

    // Create a PDF document
    val document = PdfDocument()
    val pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()
    val page = document.startPage(pageInfo)
    page.canvas.drawBitmap(bitmap, 0f, 0f, null)
    document.finishPage(page)

    // Write the PDF document to a file
    val filePath = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "license_card.pdf")
    document.writeTo(FileOutputStream(filePath))
    document.close()

    Toast.makeText(context, "PDF saved to: ${filePath.absolutePath}", Toast.LENGTH_LONG).show()
}