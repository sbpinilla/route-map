package com.sbpinilla.arcgismaproute;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.tasks.networkanalysis.Route;
import com.esri.arcgisruntime.tasks.networkanalysis.RouteParameters;
import com.esri.arcgisruntime.tasks.networkanalysis.RouteResult;
import com.esri.arcgisruntime.tasks.networkanalysis.RouteTask;
import com.esri.arcgisruntime.tasks.networkanalysis.Stop;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class MapActivity extends AppCompatActivity {

    private MapView mMapView;
    private RouteTask mRouteTask;
    private RouteParameters mRouteParams;
    private Route mRoute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
//,
        mMapView = (MapView) findViewById(R.id.map);
        ArcGISMap map = new ArcGISMap(Basemap.Type.TOPOGRAPHIC, 4.848364, -74.052444, 16);
        // set the map to be displayed in this view
        mMapView.setMap(map);




    }
}
