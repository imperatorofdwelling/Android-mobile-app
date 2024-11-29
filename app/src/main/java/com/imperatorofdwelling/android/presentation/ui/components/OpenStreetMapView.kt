package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.api.IGeoPoint
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView

@Composable
fun OpenStreetMapView(
    geoPointCenter: IGeoPoint,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    AndroidView(
        modifier = modifier
            .fillMaxSize() // или задайте фиксированные размеры, например, `.size(200.dp)`
            .clipToBounds(), // Обрезает содержимое внутри границ родительского контейнера
        factory = {
            val view = MapView(context).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                controller.setCenter(geoPointCenter)
                setMultiTouchControls(true)
                isTilesScaledToDpi = true
                controller.setZoom(12.0)
                isFlingEnabled = false
            }
            view
        }
    )
}
