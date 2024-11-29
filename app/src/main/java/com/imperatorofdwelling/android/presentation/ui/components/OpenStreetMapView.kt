package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView


@Composable
@Preview
fun OpenStreetMapViewPreview(){
    OpenStreetMapView()
}

@Composable
fun OpenStreetMapView() {
    val context = LocalContext.current
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            val view = MapView(context)
            view.setTileSource(TileSourceFactory.MAPNIK)
            view
        })
}