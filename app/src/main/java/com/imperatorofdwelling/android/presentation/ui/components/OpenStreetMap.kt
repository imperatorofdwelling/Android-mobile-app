package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.checkScrollableContainerConstraints
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
fun OpenStreetMap(
    geoPointCenter: IGeoPoint,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    AndroidView(
        modifier = modifier
            .fillMaxSize()
            .clipToBounds(),
        factory = {
            val view = MapView(context).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                controller.setCenter(geoPointCenter)
                setMultiTouchControls(false)
                isTilesScaledToDpi = true
                controller.setZoom(12.0)
                isFlingEnabled = false
                setBuiltInZoomControls(false)
                isScrollContainer = false
                setScrollableAreaLimitLatitude(geoPointCenter.latitude, geoPointCenter.latitude, 0)
                setScrollableAreaLimitLongitude(geoPointCenter.longitude, geoPointCenter.longitude, 0)

            }
            view
        }
    )
}
