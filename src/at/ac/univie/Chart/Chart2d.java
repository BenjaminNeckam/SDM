package at.ac.univie.Chart;

import java.awt.BasicStroke;
import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

public class Chart2d extends ApplicationFrame{

	public Chart2d(String applicationTitle, String chartTitle) {
		super(applicationTitle);
		JFreeChart lineChart = ChartFactory.createXYLineChart(chartTitle, "x-Axis", "y-Axis", createDataset(),PlotOrientation.VERTICAL,true,true,false);
		
		ChartPanel chartPanel = new ChartPanel( lineChart );
	    chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
	    final XYPlot plot = lineChart.getXYPlot( );
	    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
	    renderer.setSeriesPaint( 0 , Color.RED );
	    renderer.setSeriesPaint( 1 , Color.GREEN );
	    renderer.setSeriesPaint( 2 , Color.YELLOW );
	    renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
	    renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
	    renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
	    plot.setRenderer( renderer ); 
	    setContentPane( chartPanel ); 
	}

	private XYDataset createDataset() {
		//TODO finish from https://www.tutorialspoint.com/jfreechart/jfreechart_xy_chart.htm
		return null;
	}

	
	
}
