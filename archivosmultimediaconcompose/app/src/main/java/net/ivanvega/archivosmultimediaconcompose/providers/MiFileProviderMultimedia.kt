package net.ivanvega.archivosmultimediaconcompose.providers

import android.content.Context
import android.net.Uri
import androidx.compose.ui.geometry.RoundRect
import androidx.core.content.FileProvider
import net.ivanvega.archivosmultimediaconcompose.R
import java.io.File

class MiFileProviderMultimedia: FileProvider(
    R.xml.file_paths
)  {
    companion object{
        fun getImageUri (ctx : Context): Uri {

            val dirIma = File(ctx.cacheDir, "images")
            dirIma.mkdirs()

            val fileImage = File.createTempFile("img_",
                ".jpg", dirIma)

            val auth = ctx.packageName + ".fileprovidermultimedia"

            return getUriForFile(ctx, auth, fileImage)

        }
    }

}