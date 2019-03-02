package CarDetection;

import org.opencv.core.Mat;

public interface Processor {
	public Mat process(Mat inputImage);

}
