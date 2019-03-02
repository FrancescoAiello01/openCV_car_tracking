package CarDetection;


import org.opencv.core.Mat;
import org.opencv.video.BackgroundSubtractorMOG2;




public class MixtureOfGaussianBackground implements Processor {
	
	private BackgroundSubtractorMOG2 mog =  org.opencv.video.Video.createBackgroundSubtractorMOG2();
	private Mat foreground = new Mat();
	private double learningRate = 0.06;

	public Mat process(Mat inputImage) {
		
		mog.apply(inputImage, foreground, learningRate);
		
		return foreground;
	}

}
