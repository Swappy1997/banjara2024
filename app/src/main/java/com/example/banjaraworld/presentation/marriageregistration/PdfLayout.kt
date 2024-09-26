import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.Utils.showToast
import com.example.banjaraworld.ui.theme.onSecondary
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.colors.ColorConstants
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfPage
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.action.PdfAction
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine
import com.itextpdf.layout.Document
import com.itextpdf.layout.borders.Border
import com.itextpdf.layout.element.*
import com.itextpdf.layout.property.Leading
import com.itextpdf.layout.property.Property
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.UnitValue
import com.itextpdf.layout.property.VerticalAlignment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.text.Typography.amp

fun generateProfilePdf(context: Context) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            // Step 1: Create the PDF file
            val outputFile = File(context.filesDir, "profile_biodata.pdf")
            if (outputFile.exists()) outputFile.delete()  // Remove old PDF if it exists

            val pdfWriter = PdfWriter(outputFile)
            val pdfDocument = PdfDocument(pdfWriter)
            val document = Document(pdfDocument, PageSize.A4).apply {
                setMargins(0f, 13f, 13f, 13f)
                setProperty(Property.LEADING, Leading(Leading.MULTIPLIED, 1f))
            }.setWordSpacing(0f)
            val page = pdfDocument.addNewPage()

            // Step 2: Add the header with link, title, and logo
            addHeader(page, document, context)

            // Step 3: Add the profile section
            addProfileSection(document, context) // Implement this function
            addFamilySection(document, context)

            // Step 4: Add the family, religious, and career details
            // addDetailsSection(document) // Implement this function

            // Step 5: Close the document
            document.close()

            // Step 6: Switch back to the main thread to update UI (e.g., launch PDF viewer)
            withContext(Dispatchers.Main) {
                val uri: Uri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.fileprovider",
                    outputFile
                )

                val viewPdfIntent = Intent(Intent.ACTION_VIEW).apply {
                    setDataAndType(uri, "application/pdf")
                    flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                }

                // Check if there's an app to handle the PDF intent
                if (viewPdfIntent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(viewPdfIntent)
                } else {
                    showToast(context = context, message =  "No PDF viewer found!")
                }

                showToast(context = context, message =  "PDF generated successfully!")
            }

        } catch (e: Exception) {
            e.printStackTrace()
            withContext(Dispatchers.Main) {
                showToast(context = context, message = "Error generating PDF: ${e.message}")
                Log.d("TAG", "generateProfilePdf: ${e.message}")
            }
        }
    }
}

// Function to add the header (link, title, logo)
fun addHeader(page: PdfPage, document: Document, context: Context) {
    // Step 1: Create the title
    val titleParagraph = Paragraph("Bio Data")
        .setFontSize(32f)
        .setBold()
        .setTextAlignment(TextAlignment.CENTER)

    // Step 2: Convert the logo image to an iText Image object
    val drawable =
        ContextCompat.getDrawable(context, R.drawable.logo) ?: return // Handle drawable not found
    val bitmap = (drawable as BitmapDrawable).bitmap

    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 20, stream)
    val byteArray = stream.toByteArray()

    val logoImage = Image(ImageDataFactory.create(byteArray))
    logoImage.scaleToFit(100f, 100f)  // Scale down the logo size to fit properly in the header

    // Step 3: Create the link (right side of the header)
    val link = Link("view profile", PdfAction.createURI("https://www.google.com"))
        .setBorder(Border.NO_BORDER)

        .setFontColor(ColorConstants.BLUE) // Set link color
        .setUnderline() // Ensure the link is underlined, but no border

    val linkParagraph = Paragraph(link)
        .setFontSize(12f)
        .setTextAlignment(TextAlignment.RIGHT)
        .setPaddingRight(10f)
        .setPaddingTop(0f) // Optional: Ensure there's no padding that might affect the layout
        .setPaddingBottom(0f)

    // Step 4: Create a 3-column table (Logo, Title, Link)
    val headerTable = Table(floatArrayOf(1f, 3f, 1f))
        .setWidth(page.pageSize.width - 30)  // Set width based on page size
        .setFixedLayout()

    // Add cells with alignment and border settings
    // Left: Logo (right-aligned)
    headerTable.addCell(
        Cell().add(logoImage)
            .setBorder(Border.NO_BORDER)
            .setTextAlignment(TextAlignment.LEFT)
            .setVerticalAlignment(VerticalAlignment.MIDDLE)
    )

    // Center: Title (centered)
    headerTable.addCell(
        Cell().add(titleParagraph)
            .setBorder(Border.NO_BORDER)
            .setTextAlignment(TextAlignment.CENTER)
            .setVerticalAlignment(VerticalAlignment.MIDDLE)
    )

    // Right: Link (left-aligned)
    headerTable.addCell(
        Cell().add(linkParagraph)
            .setBorder(Border.NO_BORDER)
            .setTextAlignment(TextAlignment.RIGHT)
            .setVerticalAlignment(VerticalAlignment.MIDDLE)
    )

    // Step 5: Add the header to the document
    document.add(headerTable)

    // Optional: Add a line separator below the header
    val line = LineSeparator(SolidLine())
        .setWidth(page.pageSize.width - 30)
        .setMarginTop(10f)
        .setMarginBottom(10f)
    document.add(line)

}

// Function to add the profile section (image + basic details)
fun addFamilySection(document: Document, context: Context) {
    // Create the main profile table with 2 columns (1f for "About Myself" and "Family Details", 2f for "Education & Career Details")
    val profileTable =
        Table(UnitValue.createPercentArray(floatArrayOf(1f, 2f))) // Two columns with a 1:2 ratio
            .setWidth(UnitValue.createPercentValue(100f))
            .setBorder(Border.NO_BORDER) // No border for the main table

    // About Myself Section
    val aboutMyselfTitle = Paragraph("About Myself")
        .setBold()
        .setFontSize(18f)
        .setTextAlignment(TextAlignment.CENTER) // Centered title

    val aboutMyselfDetails = Paragraph(
        "I am a dedicated individual with a passion for technology and software development.\n" +
                "I enjoy solving complex problems and continuously improving my skills in Android development."
    ).setTextAlignment(TextAlignment.JUSTIFIED)
        .setMarginTop(8f)
        .setFontSize(12f)

    // Family Details Section
    val familyDetailsTitle = Paragraph("Family Details")
        .setBold()
        .setFontSize(18f)
        .setUnderline()
        .setTextAlignment(TextAlignment.LEFT) // Left-aligned title
        .setMarginTop(10f)

    // Family Details as a nested table using createDetailRow for alignment
    val familyDetailsTable = Table(floatArrayOf(1f)) // Nested table for details
        .setWidth(UnitValue.createPercentValue(100f))
        .setBorder(Border.NO_BORDER)

    // Add detail rows for family information
    familyDetailsTable.addCell(
        Cell().add(createDetailRow("Father Status", "Farmer")).setBorder(Border.NO_BORDER)
    )
    familyDetailsTable.addCell(
        Cell().add(createDetailRow("Mother Status", "HouseMaker")).setBorder(Border.NO_BORDER)
    )
    familyDetailsTable.addCell(
        Cell().add(createDetailRow("Native Place", "Washim Maharashtra"))
            .setBorder(Border.NO_BORDER)
    )
    familyDetailsTable.addCell(
        Cell().add(createDetailRow("Father Affluence", "Middle Class")).setBorder(Border.NO_BORDER)
    )
    familyDetailsTable.addCell(
        Cell().add(createDetailRow("No of Sister", "2 sister")).setBorder(Border.NO_BORDER)
    )
    familyDetailsTable.addCell(
        Cell().add(createDetailRow("No of Brother", "0 brother")).setBorder(Border.NO_BORDER)
    )

    // Create a cell for "About Myself" and "Family Details" combined
    val aboutMyselfAndFamilyCell = Cell()
        .add(aboutMyselfTitle)
        .add(aboutMyselfDetails)
        .add(familyDetailsTitle)
        .add(familyDetailsTable)
        .setBorder(Border.NO_BORDER) // No border for this cell
        .setBackgroundColor(DeviceRgb(255, 219, 187)) // Set background color

    // Add this cell to the first column of the profile table (1f)
    profileTable.addCell(aboutMyselfAndFamilyCell)

    // Education & Career Details Section
    val educationDetailsTitle = Paragraph("Education & Career Details")
        .setBold()
        .setFontSize(18f)
        .setUnderline()
        .setTextAlignment(TextAlignment.LEFT) // Left-aligned title

    // Education & Career Details as a nested table using createDetailRow for alignment
    val educationDetailsTable = Table(floatArrayOf(1f)) // Nested table for details
        .setWidth(UnitValue.createPercentValue(100f))
        .setBorder(Border.NO_BORDER)

    // Add detail rows for education and career information
    educationDetailsTable.addCell(
        Cell().add(createDetailRow("Highest Qualification", "B.E")).setBorder(Border.NO_BORDER)
    )
    educationDetailsTable.addCell(
        Cell().add(
            createDetailRow(
                "College",
                "Sri Sant Gajanan Maharaj College of Engineering"
            )
        ).setBorder(Border.NO_BORDER)
    )
    educationDetailsTable.addCell(
        Cell().add(createDetailRow("Profession", "Software Engineer")).setBorder(Border.NO_BORDER)
    )
    educationDetailsTable.addCell(
        Cell().add(createDetailRow("Income", "INR 20 Lakh to 25 Lakh")).setBorder(Border.NO_BORDER)
    )

    // Create a cell for "Education & Career Details"
    val educationDetailsCell = Cell()
        .add(educationDetailsTitle)
        .add(educationDetailsTable)
        .setBorder(Border.NO_BORDER) // No border for this cell

    // Add the "Education & Career Details" cell to the second column of the profile table (2f)
    profileTable.addCell(educationDetailsCell)

    // Contact Details Section
    val contactDetailsTitle = Paragraph("Contact Details")
        .setBold()
        .setFontSize(18f)
        .setUnderline()
        .setTextAlignment(TextAlignment.LEFT) // Left-aligned title
        .setMarginTop(10f)

    // Contact Details as a nested table using createDetailRow for alignment
    val contactDetailsTable = Table(floatArrayOf(1f)) // Nested table for details
        .setWidth(UnitValue.createPercentValue(100f))
        .setBorder(Border.NO_BORDER)

    // Add detail rows for contact information
    contactDetailsTable.addCell(
        Cell().add(createDetailRow("Phone Number", "123-456-7890")).setBorder(Border.NO_BORDER)
    )
    contactDetailsTable.addCell(
        Cell().add(createDetailRow("Email", "example@example.com")).setBorder(Border.NO_BORDER)
    )
    contactDetailsTable.addCell(
        Cell().add(createDetailRow("Address", "123 Main St, City, State, Zip")).setBorder(Border.NO_BORDER)
    )

    // Create a cell for "Contact Details"
    val contactDetailsCell = Cell()
        .add(contactDetailsTitle)
        .add(contactDetailsTable)
        .setBorder(Border.NO_BORDER) // No border for this cell

    // Add the "Contact Details" cell to the second column of the profile table (2f)
    educationDetailsTable.addCell(contactDetailsCell)

    // Add the main profile table to the document
    document.add(profileTable)
}

fun addProfileSection(document: Document, context: Context) {
    val profileTable = Table(UnitValue.createPercentArray(floatArrayOf(1f, 2f)))
        .setWidth(UnitValue.createPercentValue(100f))
        .setBorder(Border.NO_BORDER) // No border for the main table
    val drawable = context.resources.getDrawable(R.drawable.one, null)
    val bitmap = (drawable as BitmapDrawable).bitmap

    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 20, stream) // Use 100 for best quality
    val byteArray = stream.toByteArray()

    val profileImage = Image(ImageDataFactory.create(byteArray))
        .scaleToFit(150f, 150f)
    // Left cell: Profile image
    profileTable.addCell(
        Cell().add(profileImage)
            .setBorder(Border.NO_BORDER) // No border for this cell
            .setTextAlignment(TextAlignment.CENTER)
    )

    val detailsTable = Table(floatArrayOf(1f)) // Nested table for details
        .setWidth(UnitValue.createPercentValue(100f))
        .setBorder(Border.NO_BORDER) // No border for nested table

    val titleParagraph = Paragraph("Basic Details")
        .setBold()
        .setFontSize(18f)
        .setUnderline()
        .setTextAlignment(TextAlignment.LEFT)

    detailsTable.addCell(
        Cell().add(titleParagraph).setBorder(Border.NO_BORDER)
    ) // No border for title

    // Add each detail using the helper function
    detailsTable.addCell(
        Cell().add(createDetailRow("Date of Birth", ":10-Sep-1997")).setBorder(Border.NO_BORDER)
    ) // No border for each detail
    detailsTable.addCell(
        Cell().add(createDetailRow("Height", ":5' 8\"")).setBorder(Border.NO_BORDER)
    )
    detailsTable.addCell(
        Cell().add(createDetailRow("Marital Status", ":Never Married")).setBorder(Border.NO_BORDER)
    )
    detailsTable.addCell(
        Cell().add(createDetailRow("Email ID", ":rathodswapnil9@gmail.com"))
            .setBorder(Border.NO_BORDER)
    )
    detailsTable.addCell(
        Cell().add(createDetailRow("Contact No.", ":+91-7720840636")).setBorder(Border.NO_BORDER)
    )

    // Add the nested details table to the right cell of the profile table
    profileTable.addCell(
        Cell().add(detailsTable).setBorder(Border.NO_BORDER)
    ) // No border for this cell

    // Add the main profile table to the document
    document.add(profileTable)
}


fun createDetailRow(label: String, value: String): Table {
    // Create a table with two columns: one for label and one for value
    val detailTable = Table(UnitValue.createPercentArray(floatArrayOf(1f, 2f)))
        .setWidth(UnitValue.createPercentValue(100f))

    // Add the label in the first cell (left-aligned, gray color)
    detailTable.addCell(
        Cell().add(
            Paragraph(label)
                .setFontColor(DeviceRgb(128, 128, 128))
                .setFontSize(14f)
                .setTextAlignment(TextAlignment.LEFT)
        )
            .setBorder(Border.NO_BORDER)
    )

    // Add the value in the second cell (left-aligned, black color)
    detailTable.addCell(
        Cell().add(
            Paragraph(value)
                .setFontSize(14f)
                .setTextAlignment(TextAlignment.LEFT)
        )
            .setBorder(Border.NO_BORDER)
    )

    return detailTable
}

