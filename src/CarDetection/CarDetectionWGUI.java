package CarDetection;
import java.awt.AWTException;
import java.awt.Graphics;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;  
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;  
import javax.swing.JPanel;  
import org.opencv.core.Core;  
import org.opencv.core.Mat;   
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;  
import org.opencv.core.Size;  
import org.opencv.imgproc.*;
import org.opencv.core.Rect;
import org.opencv.videoio.VideoCapture;





class Panel extends JPanel{  
private static final long serialVersionUID = 1L;  
private BufferedImage image;    

public Panel(){  
	super();  
	}  

private BufferedImage getimage(){  
	return image;  
	}  

public void setimage(BufferedImage newimage){  
	image=newimage;  
	}  
	
public void setimagewithMat(Mat newimage){  
		image=this.matToBufferedImage(newimage);  
	}  

	public BufferedImage matToBufferedImage(Mat matrix) {  
		int cols = matrix.cols();  
		int rows = matrix.rows();  
		int elemSize = (int)matrix.elemSize();  
		byte[] data = new byte[cols * rows * elemSize];  
		int type;  
		matrix.get(0, 0, data);  
		switch (matrix.channels()) {  
		case 1:  
			type = BufferedImage.TYPE_BYTE_GRAY;  
			break;  
		case 3:  
			type = BufferedImage.TYPE_3BYTE_BGR;  
			// bgr to rgb  
			byte b;  
			for(int i=0; i<data.length; i=i+3) {  
				b = data[i];  
				data[i] = data[i+2];  
				data[i+2] = b;  
			}  
			break;  
		default:  
			return null;  
		}  
		BufferedImage image2 = new BufferedImage(cols, rows, type);  
		image2.getRaster().setDataElements(0, 0, cols, rows, data);  
		return image2;  
	}  
	@Override  
	protected void paintComponent(Graphics g){  
		super.paintComponent(g);  
		//BufferedImage temp=new BufferedImage(640, 480, BufferedImage.TYPE_3BYTE_BGR);  
		BufferedImage temp=getimage();  
		//Graphics2D g2 = (Graphics2D)g;
		if( temp != null)
			g.drawImage(temp,10,10,temp.getWidth(),temp.getHeight(), this);  
	}  
}  
public class CarDetectionWGUI{  
    
    static boolean DETECTIONSTARTED = false;
    static int CARCOUNT;
    
    static boolean ROI1PRESSED = true;      
    static boolean LOCK4ROI1;      
    static boolean ROI1RECTPREV = false;
    static boolean ROI1ISSETTABLE = false;
    static int ROI1X = 0;
    static int ROI1Y = 0;
    static int ROI1WIDTH = 0;
    static int ROI1HEIGHT = 0;
    static int ROI1X_FINAL;
    static int ROI1Y_FINAL;
    static int ROI1WIDTH_FINAL;
    static int ROI1HEIGHT_HEIGHT;
    static Scalar ROI1BOXCOLOR ;
    static int ROI1SIZE = 30;
    static Rect ROI1RECT;

    static boolean ROI2PRESSED;      
    static boolean LOCK4ROI2;      
    static boolean ROI2RECTPREV = false;
    static boolean ROI2ISSETTABLE = false;
    static int ROI2X = 0;
    static int ROI2Y = 0;
    static int ROI2WIDTH = 0;
    static int ROI2HEIGHT = 0;
    static int ROI2X_FINAL;
    static int ROI2Y_FINAL;
    static int ROI2WIDTH_FINAL;
    static int ROI2HEIGHT_HEIGHT;
    static Scalar ROI2BOXCOLOR ;
    static int ROI2SIZE = 30;
    static Rect ROI2RECT;

    static boolean ROI3PRESSED;      
    static boolean LOCK4ROI3;      
    static boolean ROI3RECTPREV = false;
    static boolean ROI3ISSETTABLE = false;
    static int ROI3X = 0;
    static int ROI3Y = 0;
    static int ROI3WIDTH = 0;
    static int ROI3HEIGHT = 0;
    static int ROI3X_FINAL;
    static int ROI3Y_FINAL;
    static int ROI3WIDTH_FINAL;
    static int ROI3HEIGHT_HEIGHT;
    static Scalar ROI3BOXCOLOR ;
    static int ROI3SIZE = 30;
    static Rect ROI3RECT;
    
    static boolean ROI4PRESSED;      
    static boolean LOCK4ROI4;      
    static boolean ROI4RECTPREV = false;
    static boolean ROI4ISSETTABLE = false;
    static int ROI4X = 0;
    static int ROI4Y = 0;
    static int ROI4WIDTH = 0;
    static int ROI4HEIGHT = 0;
    static int ROI4X_FINAL;
    static int ROI4Y_FINAL;
    static int ROI4WIDTH_FINAL;
    static int ROI4HEIGHT_HEIGHT;
    static Scalar ROI4BOXCOLOR ;
    static int ROI4SIZE = 30;
    static Rect ROI4RECT;
    
    static boolean ROI5PRESSED;      
    static boolean LOCK4ROI5;      
    static boolean ROI5RECTPREV = false;
    static boolean ROI5ISSETTABLE = false;
    static int ROI5X = 0;
    static int ROI5Y = 0;
    static int ROI5WIDTH = 0;
    static int ROI5HEIGHT = 0;
    static int ROI5X_FINAL;
    static int ROI5Y_FINAL;
    static int ROI5WIDTH_FINAL;
    static int ROI5HEIGHT_HEIGHT;
    static Scalar ROI5BOXCOLOR ;
    static int ROI5SIZE = 30;
    static Rect ROI5RECT;

    static boolean ROI6PRESSED;      
    static boolean LOCK4ROI6;      
    static boolean ROI6RECTPREV = false;
    static boolean ROI6ISSETTABLE = false;
    static int ROI6X = 0;
    static int ROI6Y = 0;
    static int ROI6WIDTH = 0;
    static int ROI6HEIGHT = 0;
    static int ROI6X_FINAL;
    static int ROI6Y_FINAL;
    static int ROI6WIDTH_FINAL;
    static int ROI6HEIGHT_HEIGHT;
    static Scalar ROI6BOXCOLOR ;
    static int ROI6SIZE = 30;
    static Rect ROI6RECT;
    
    static double ROISRATIO = 2;

    static int MOUSEX =0;
    static int MOUSEY = 0;
    @SuppressWarnings({"UnusedAssignment", "SuspiciousIndentAfterControlStatement"})
    private static void Calculations() throws AWTException, InterruptedException{
    ROI1BOXCOLOR = new Scalar(0,0,255);
    ROI2BOXCOLOR = new Scalar(0,0,255);
    ROI3BOXCOLOR = new Scalar(0,0,255);
    ROI4BOXCOLOR = new Scalar(0,0,255);
    ROI5BOXCOLOR = new Scalar(0,0,255);
    ROI6BOXCOLOR = new Scalar(0,0,255);

    
    Processor processor;

//CAMERA FRAME PARAMS
JFrame CameraFrame = new JFrame("CAMERA");  
CameraFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
CameraFrame.setSize(500,500);  
Panel panel1 = new Panel();  
CameraFrame.setContentPane(panel1);  
CameraFrame.setVisible(true);  
    
CameraFrame.addMouseMotionListener(new MouseMotionListener() {
        @Override public void mouseDragged(MouseEvent me) {}

        @Override
        public void mouseMoved(MouseEvent me) {
MOUSEX = me.getX();
MOUSEY = me.getY();
        } });

CameraFrame.addMouseListener(new MouseListener() {
    @Override
    public void mouseClicked(MouseEvent me) {   
        if(ROI1PRESSED){

            ROI1X_FINAL = ROI1X;
            ROI1Y_FINAL = ROI1Y;
            ROI1WIDTH_FINAL = ROI1WIDTH;
            ROI1HEIGHT_HEIGHT = ROI1HEIGHT;
      
            ROI1RECT = new Rect(ROI1X_FINAL,ROI1Y_FINAL, (int) (ROI1SIZE*ROISRATIO), (int) (ROI1SIZE));
                      
            LOCK4ROI1 = true;
            ROI1ISSETTABLE = false;      
            
            ROI2PRESSED = false;
            ROI3PRESSED = false;
            ROI4PRESSED = false;
            ROI5PRESSED = false;
            ROI6PRESSED = false;
        }

        if(ROI2PRESSED){

            ROI2X_FINAL = ROI2X;
            ROI2Y_FINAL = ROI2Y;
            ROI2WIDTH_FINAL = ROI2WIDTH;
            ROI2HEIGHT_HEIGHT = ROI2HEIGHT;
      
            ROI2RECT = new Rect(ROI2X_FINAL,ROI2Y_FINAL, (int) (ROI2SIZE*ROISRATIO), (int) (ROI2SIZE));
                      
            LOCK4ROI2 = true;
            ROI2ISSETTABLE = false;          
       
            ROI1PRESSED = false;
            ROI3PRESSED = false;
            ROI4PRESSED = false;
            ROI5PRESSED = false;
            ROI6PRESSED = false;
        }
        
         if(ROI3PRESSED){

            ROI3X_FINAL = ROI3X;
            ROI3Y_FINAL = ROI3Y;
            ROI3WIDTH_FINAL = ROI3WIDTH;
            ROI3HEIGHT_HEIGHT = ROI3HEIGHT;
      
            ROI3RECT = new Rect(ROI3X_FINAL,ROI3Y_FINAL, (int) (ROI3SIZE*ROISRATIO), (int) (ROI3SIZE));
                      
            LOCK4ROI3 = true;
            ROI3ISSETTABLE = false;      
            
            ROI2PRESSED = false;
            ROI1PRESSED = false;
            ROI4PRESSED = false;
            ROI5PRESSED = false;
            ROI6PRESSED = false;
        }       
        if(ROI4PRESSED){

            ROI4X_FINAL = ROI4X;
            ROI4Y_FINAL = ROI4Y;
            ROI4WIDTH_FINAL = ROI4WIDTH;
            ROI4HEIGHT_HEIGHT = ROI4HEIGHT;
      
            ROI4RECT = new Rect(ROI4X_FINAL,ROI4Y_FINAL, (int) (ROI4SIZE*ROISRATIO), (int) (ROI4SIZE));
                      
            LOCK4ROI4 = true;
            ROI4ISSETTABLE = false;      
            
            ROI2PRESSED = false;
            ROI3PRESSED = false;
            ROI1PRESSED = false;
            ROI5PRESSED = false;
            ROI6PRESSED = false;
        }       
        if(ROI5PRESSED){

            ROI5X_FINAL = ROI5X;
            ROI5Y_FINAL = ROI5Y;
            ROI5WIDTH_FINAL = ROI5WIDTH;
            ROI5HEIGHT_HEIGHT = ROI5HEIGHT;
      
            ROI5RECT = new Rect(ROI5X_FINAL,ROI5Y_FINAL, (int) (ROI5SIZE*ROISRATIO), (int) (ROI5SIZE));
                      
            LOCK4ROI5 = true;
            ROI5ISSETTABLE = false;      
            
            ROI2PRESSED = false;
            ROI3PRESSED = false;
            ROI4PRESSED = false;
            ROI1PRESSED = false;
            ROI6PRESSED = false;
        }        
        
        if(ROI6PRESSED){

            ROI6X_FINAL = ROI6X;
            ROI6Y_FINAL = ROI6Y;
            ROI6WIDTH_FINAL = ROI6WIDTH;
            ROI6HEIGHT_HEIGHT = ROI6HEIGHT;
      
            ROI6RECT = new Rect(ROI6X_FINAL,ROI6Y_FINAL, (int) (ROI6SIZE*ROISRATIO), (int) (ROI6SIZE));
                      
            LOCK4ROI6 = true;
            ROI6ISSETTABLE = false;      
            
            ROI2PRESSED = false;
            ROI3PRESSED = false;
            ROI4PRESSED = false;
            ROI5PRESSED = false;
            ROI1PRESSED = false;
        }        
        
        }
    
    
    @Override
    public void mousePressed(MouseEvent me){}@Override public void mouseReleased(MouseEvent me){}@Override public void mouseEntered(MouseEvent me){}@Override   public void mouseExited(MouseEvent me) {}});
    

CameraFrame.addMouseWheelListener((MouseWheelEvent mwe) -> {
    if(mwe.getWheelRotation()<0){
        if(ROI1PRESSED){
            ROI1SIZE = ROI1SIZE - 1;
        }
        if(ROI2PRESSED){
            ROI2SIZE = ROI2SIZE - 1;
        }
        if(ROI3PRESSED){
            ROI3SIZE = ROI3SIZE - 1;
        }
        if(ROI4PRESSED){
            ROI4SIZE = ROI4SIZE - 1;
        }
        if(ROI5PRESSED){
            ROI5SIZE = ROI5SIZE - 1;
        }
        if(ROI6PRESSED){
            ROI6SIZE = ROI6SIZE - 1;
        }
    }
    if(mwe.getWheelRotation()>0){
        if(ROI1PRESSED){
                ROI1SIZE = ROI1SIZE + 1;

        }
        if(ROI2PRESSED){
                ROI2SIZE = ROI2SIZE + 1;

        }
        if(ROI3PRESSED){
                ROI3SIZE = ROI3SIZE + 1;

        }
        if(ROI4PRESSED){
                ROI4SIZE = ROI4SIZE + 1;

        }
        if(ROI5PRESSED){
                ROI5SIZE = ROI5SIZE + 1;

        }
        if(ROI6PRESSED){
                ROI6SIZE = ROI6SIZE + 1;

        }




    }
    });
//END OF CAMERA FRAME SETUP--------------------------------------------------------------


//CONTROLS FRAME SETUP    
JFrame ControlsFrame = new JFrame("Controls");  
ControlsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
Panel ControlsPanel = new Panel();  
JButton ROI1setButton = new JButton("Set ROI1");
JButton ROI2setButton = new JButton("Set ROI2");
JButton ROI3setButton = new JButton("Set ROI3");
JButton ROI4setButton = new JButton("Set ROI4");
JButton ROI5setButton = new JButton("Set ROI5");
JButton ROI6setButton = new JButton("Set ROI6");

JButton startDetectionButton = new JButton("Start Detection");
JCheckBox showROIs = new JCheckBox( "show ROIs",false);


ROI1setButton.addActionListener((ActionEvent ae) -> {
    
    ROI1ISSETTABLE = true;
    ROI1PRESSED = true;
    ROI1RECTPREV = true;

    ROI2ISSETTABLE = false;
    ROI2PRESSED = false;

    ROI3ISSETTABLE = false;
    ROI3PRESSED = false;

    ROI4ISSETTABLE = false;
    ROI4PRESSED = false;


    ROI5ISSETTABLE = false;
    ROI5PRESSED = false;


    ROI6ISSETTABLE = false;
    ROI6PRESSED = false;


});
ROI2setButton.addActionListener((ActionEvent ae) -> {
    ROI2ISSETTABLE = true;
    ROI2PRESSED = true;
    ROI2RECTPREV = true;

    ROI1ISSETTABLE = false;
    ROI1PRESSED = false;


    ROI3ISSETTABLE = false;
    ROI3PRESSED = false;


    ROI4ISSETTABLE = false;
    ROI4PRESSED = false;


    ROI5ISSETTABLE = false;
    ROI5PRESSED = false;


    ROI6ISSETTABLE = false;
    ROI6PRESSED = false;

    
});
ROI3setButton.addActionListener((ActionEvent ae) -> {
    ROI3ISSETTABLE = true;
    ROI3PRESSED = true;
    ROI3RECTPREV = true;

    ROI1ISSETTABLE = false;
    ROI1PRESSED = false;


    ROI2ISSETTABLE = false;
    ROI2PRESSED = false;


    ROI4ISSETTABLE = false;
    ROI4PRESSED = false;


    ROI5ISSETTABLE = false;
    ROI5PRESSED = false;

    
    ROI6ISSETTABLE = false;
    ROI6PRESSED = false;


});
ROI4setButton.addActionListener((ActionEvent ae) -> {
    ROI4ISSETTABLE = true;
    ROI4PRESSED = true;
    ROI4RECTPREV = true;
    
    ROI1ISSETTABLE = false;
    ROI1PRESSED = false;


    ROI2ISSETTABLE = false;
    ROI2PRESSED = false;


    ROI3ISSETTABLE = false;
    ROI3PRESSED = false;


    ROI5ISSETTABLE = false;
    ROI5PRESSED = false;


    ROI6ISSETTABLE = false;
    ROI6PRESSED = false;


});
ROI5setButton.addActionListener((ActionEvent ae) -> {
    ROI5ISSETTABLE = true;
    ROI5PRESSED = true;
    ROI5RECTPREV = true;

    ROI1ISSETTABLE = false;
    ROI1PRESSED = false;


    ROI2ISSETTABLE = false;
    ROI2PRESSED = false;


    ROI3ISSETTABLE = false;
    ROI3PRESSED = false;


    ROI4ISSETTABLE = false;
    ROI4PRESSED = false;


    ROI6ISSETTABLE = false;
    ROI6PRESSED = false;


});
ROI6setButton.addActionListener((ActionEvent ae) -> {
    ROI6ISSETTABLE = true;
    ROI6PRESSED = true;
    ROI6RECTPREV = true;
    
    ROI1ISSETTABLE = false;
    ROI1PRESSED = false;


    ROI2ISSETTABLE = false;
    ROI2PRESSED = false;


    ROI3ISSETTABLE = false;
    ROI3PRESSED = false;


    ROI4ISSETTABLE = false;
    ROI4PRESSED = false;


    ROI5ISSETTABLE = false;
    ROI5PRESSED = false;


});
//----------------------------------------------------------------------------------------------------------------------------------------





//NEW DETECTION BUTTON SETUP
startDetectionButton.addActionListener((ActionEvent ev) -> {
 DETECTIONSTARTED = true;
 startDetectionButton.setEnabled(false);
 startDetectionButton.setText("Start Detection");
});




ControlsPanel.add(ROI1setButton);
ControlsPanel.add(ROI2setButton);
ControlsPanel.add(ROI3setButton);
ControlsPanel.add(ROI4setButton);
ControlsPanel.add(ROI5setButton);
ControlsPanel.add(ROI6setButton);
ControlsPanel.add(startDetectionButton);
ControlsPanel.add(showROIs);

ControlsFrame.setContentPane(ControlsPanel);      

ControlsFrame.setVisible(true);     
//END OF CONTROLS FRAME SETUP-------------------------------------------------------------------------------------------------------


//ROI1 FRAME SETUP 
JFrame ROI1Frame = new JFrame("ROI1");  
ROI1Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
Panel ROI1Panel = new Panel();     
ROI1Frame.setContentPane(ROI1Panel);      
ROI1Frame.setSize(500, 500);
ROI1Frame.setLocationRelativeTo(null);
//END OF ROI1 FRAME SETUP---------------------------------------------------------------------------------------------------

//ROI2 FRAME SETUP 
JFrame ROI2Frame = new JFrame("ROI2");  
ROI2Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
Panel ROI2Panel = new Panel();     
ROI2Frame.setContentPane(ROI2Panel);      
ROI2Frame.setSize(500, 500);
ROI2Frame.setLocationRelativeTo(null);
//END OF RO2 FRAME SETUP-------------------------------------------------------------------------------------------------------------

//ROI3 FRAME SETUP 
JFrame ROI3Frame = new JFrame("ROI3");  
ROI3Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
Panel ROI3Panel = new Panel();     
ROI3Frame.setContentPane(ROI3Panel);      
ROI3Frame.setSize(500, 500);
ROI3Frame.setLocationRelativeTo(null);
//END OF ROI3 FRAME SETUP-------------------------------------------------------------------------------------------------------------

//ROI4 FRAME SETUP 
JFrame ROI4Frame = new JFrame("ROI4");  
ROI4Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
Panel ROI4Panel = new Panel();     
ROI4Frame.setContentPane(ROI4Panel);      
ROI4Frame.setSize(500, 500);
ROI4Frame.setLocationRelativeTo(null);
//END OF ROI4 FRAME SETUP-------------------------------------------------------------------------------------------------------------

//ROI5 FRAME SETUP 
JFrame ROI5Frame = new JFrame("ROI5");  
ROI5Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
Panel ROI5Panel = new Panel();     
ROI5Frame.setContentPane(ROI5Panel);      
ROI5Frame.setSize(500, 500);
ROI5Frame.setLocationRelativeTo(null);
//END OF ROI5 EXPERIMENTS FRAME SETUP---------------------------------------------------------------------------------------------------

//ROI6 FRAME SETUP 
JFrame ROI6Frame = new JFrame("ROI6");  
ROI6Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
Panel ROI6Panel = new Panel();     
ROI6Frame.setContentPane(ROI6Panel);      
ROI6Frame.setSize(500, 500);
ROI6Frame.setLocationRelativeTo(null);
//END OF ROI6 FRAME SETUP----------------------------------------------------------------------------------------------------------------

VideoCapture capture =new VideoCapture("footage.mp4"); //open video file
//VideoCapture capture =new VideoCapture(0); //use webcam

Mat FeedMat=new Mat();
Mat roi1Mat = new Mat();
Mat roi2Mat = new Mat();
Mat roi3Mat = new Mat();
Mat roi4Mat = new Mat();
Mat roi5Mat = new Mat();
Mat roi6Mat = new Mat();

//LOOP INIZIALITATION (THE PROCESS WITHIN THIS LOOP WILL BE PERFORMED EVERY NEW FRAME);
capture.read(FeedMat);

CameraFrame.setSize(640,480);  
System.out.println(FeedMat.size());
showROIs.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
        if(showROIs.isSelected()){
        
        ROI1Frame.setVisible(true);
        ROI2Frame.setVisible(true);
        ROI3Frame.setVisible(true);
        ROI4Frame.setVisible(true);
        ROI5Frame.setVisible(true);
        ROI6Frame.setVisible(true);
        
        }
        if(!showROIs.isSelected()){
        
        ROI1Frame.setVisible(false);
        ROI2Frame.setVisible(false);
        ROI3Frame.setVisible(false);
        ROI4Frame.setVisible(false);
        ROI5Frame.setVisible(false);
        ROI6Frame.setVisible(false);
        
        }
        }
    });
if( capture.isOpened())  
{    		
    processor = new MixtureOfGaussianBackground(); 
    while( true ) 
    { 
        capture.read(FeedMat);  
        
        if( !FeedMat.empty() )  
        {
            
//THIS IS WHERE THE LOOP STARTS EVERYTHING INSIDE HERE IS DONE IN EVERY SINGLE FRAME

                     
//THIS BLOCK OF CODE SETS THE Region Of Interest RECTANGLE WITH THE MOUSE COORDINATES                 
if(ROI1ISSETTABLE){
ROI1X =  MOUSEX;   
ROI1Y =  MOUSEY;
ROI1WIDTH =  (int) (MOUSEX+ROI1SIZE*ROISRATIO);   
ROI1HEIGHT =  (int) (MOUSEY+ROI1SIZE);       

}

if(ROI2ISSETTABLE){
ROI2X =  MOUSEX;   
ROI2Y =  MOUSEY;
ROI2WIDTH =  (int) (MOUSEX+ROI2SIZE*ROISRATIO);   
ROI2HEIGHT =  (int) (MOUSEY+ROI2SIZE);       

}
if(ROI3ISSETTABLE){
ROI3X =  MOUSEX;   
ROI3Y =  MOUSEY;
ROI3WIDTH =  (int) (MOUSEX+ROI3SIZE*ROISRATIO);   
ROI3HEIGHT =  (int) (MOUSEY+ROI3SIZE);       

}
if(ROI4ISSETTABLE){
ROI4X =  MOUSEX;   
ROI4Y =  MOUSEY;
ROI4WIDTH =  (int) (MOUSEX+ROI4SIZE*ROISRATIO);   
ROI4HEIGHT =  (int) (MOUSEY+ROI4SIZE);       

}
if(ROI5ISSETTABLE){
ROI5X =  MOUSEX;   
ROI5Y =  MOUSEY;
ROI5WIDTH =  (int) (MOUSEX+ROI5SIZE*ROISRATIO);   
ROI5HEIGHT =  (int) (MOUSEY+ROI5SIZE);       

}
if(ROI6ISSETTABLE){
ROI6X =  MOUSEX;   
ROI6Y =  MOUSEY;
ROI6WIDTH =  (int) (MOUSEX+ROI6SIZE*ROISRATIO);   
ROI6HEIGHT =  (int) (MOUSEY+ROI6SIZE);       

}
//THIS BLOCK SET THE PREVIEW RECTANGLE FOR THE SETUPS
    if(ROI1RECTPREV){
        Imgproc.rectangle(FeedMat, new Point(ROI1X,ROI1Y), new Point(ROI1WIDTH,ROI1HEIGHT), ROI1BOXCOLOR);        

    }
    if(ROI2RECTPREV){
        Imgproc.rectangle(FeedMat, new Point(ROI2X,ROI2Y), new Point(ROI2WIDTH,ROI2HEIGHT), ROI2BOXCOLOR);        

    }
    if(ROI3RECTPREV){
        Imgproc.rectangle(FeedMat, new Point(ROI3X,ROI3Y), new Point(ROI3WIDTH,ROI3HEIGHT), ROI3BOXCOLOR);        

    }
    if(ROI4RECTPREV){
        Imgproc.rectangle(FeedMat, new Point(ROI4X,ROI4Y), new Point(ROI4WIDTH,ROI4HEIGHT), ROI4BOXCOLOR);        

    }
    if(ROI5RECTPREV){
        Imgproc.rectangle(FeedMat, new Point(ROI5X,ROI5Y), new Point(ROI5WIDTH,ROI5HEIGHT), ROI5BOXCOLOR);        

    }
    if(ROI6RECTPREV){
        Imgproc.rectangle(FeedMat, new Point(ROI6X,ROI6Y), new Point(ROI6WIDTH,ROI6HEIGHT), ROI6BOXCOLOR);        

    }

    

//END OF PREVIEW RECTANGLES BLOCK----------------------------------------------------------------------------------------------------------    

        if(LOCK4ROI1){
         roi1Mat = FeedMat.submat(ROI1RECT);
        }

        if(LOCK4ROI2){
         roi2Mat = FeedMat.submat(ROI2RECT);
        }

        if(LOCK4ROI3){
         roi3Mat = FeedMat.submat(ROI3RECT);
        }

        if(LOCK4ROI3){
         roi3Mat = FeedMat.submat(ROI3RECT);
        }

        if(LOCK4ROI4){
         roi4Mat = FeedMat.submat(ROI4RECT);
        }

        if(LOCK4ROI5){
         roi5Mat = FeedMat.submat(ROI5RECT);
        }

        if(LOCK4ROI6){
         roi6Mat = FeedMat.submat(ROI6RECT);
        }

        Imgproc.resize(FeedMat, FeedMat, new Size(CameraFrame.getWidth(),CameraFrame.getHeight()));
        
        
        
        if(DETECTIONSTARTED){
            if(!roi1Mat.empty()){
            Imgproc.resize(roi1Mat, roi1Mat, new Size(ROI1Frame.getWidth()-40,ROI1Frame.getHeight()-40));
            Mat processed = processor.process(roi1Mat);
            Mat hierarchy = new Mat();
            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
            Imgproc.findContours(processed, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
            int counter = 0;
            for(int i = 0; i<contours.size(); i++){
                double area = Imgproc.contourArea(contours.get(i));
                if(area >=4000 ){ //object size for detection
                    counter++;
                }
            }
            if(counter >=1){
                ROI1BOXCOLOR = new Scalar (0,255,0);        
                System.out.println("Car Detected in ROI 1");
                Imgproc.putText(FeedMat, "Car Detected in ROI 1", new Point(0,20), 1, 1, new Scalar(255,255,255));

            }
                        if(counter ==0){
                ROI1BOXCOLOR = new Scalar (0,0,255); 
                                CARCOUNT++;
            }
          contours.clear();
            ROI1Panel.setimagewithMat(roi1Mat);
            processed.release();
            hierarchy.release();
            
            }

            
            
            if(!roi2Mat.empty()){
            Imgproc.resize(roi2Mat, roi2Mat, new Size(ROI2Frame.getWidth()-40,ROI2Frame.getHeight()-40));
            Mat processed = processor.process(roi2Mat);
            Mat hierarchy = new Mat();
            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
            Imgproc.findContours(processed, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
            int counter = 0;
            for(int i = 0; i<contours.size(); i++){
                double area = Imgproc.contourArea(contours.get(i));
                if(area >=4000 ){
                    counter++;
                }
            }
            if(counter >=1){
                ROI2BOXCOLOR = new Scalar (0,255,0); 
                System.out.println("Car Detected in ROI 2");
                Imgproc.putText(FeedMat, "Car Detected in ROI 2", new Point(0,40), 1, 1, new Scalar(255,255,255));

                
            }
                        if(counter ==0){
                ROI2BOXCOLOR = new Scalar (0,0,255); 
                                CARCOUNT++;
            }
          contours.clear();

            ROI2Panel.setimagewithMat(roi2Mat);
            processed.release();
            
            }
            
            if(!roi3Mat.empty()){
            Imgproc.resize(roi3Mat, roi3Mat, new Size(ROI3Frame.getWidth()-40,ROI3Frame.getHeight()-40));
            Mat processed = processor.process(roi3Mat);
            Mat hierarchy = new Mat();
            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
            Imgproc.findContours(processed, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
            int counter = 0;
            for(int i = 0; i<contours.size(); i++){
                double area = Imgproc.contourArea(contours.get(i));
                if(area >=4000 ){
                    counter++;
                }
            }
            if(counter >=1){
                ROI3BOXCOLOR = new Scalar (0,255,0); 
                System.out.println("Car Detected in ROI 3");
                Imgproc.putText(FeedMat, "Car Detected in ROI 3", new Point(0,60), 1, 1, new Scalar(255,255,255));

                
            }
                        if(counter ==0){
                ROI3BOXCOLOR = new Scalar (0,0,255); 
            }
          contours.clear();

            ROI3Panel.setimagewithMat(roi3Mat);
            processed.release();
            
            }
             
            if(!roi4Mat.empty()){
            Imgproc.resize(roi4Mat, roi4Mat, new Size(ROI4Frame.getWidth()-40,ROI4Frame.getHeight()-40));
            Mat processed = processor.process(roi4Mat);
            Mat hierarchy = new Mat();
            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
            Imgproc.findContours(processed, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
            int counter = 0;
            for(int i = 0; i<contours.size(); i++){
                double area = Imgproc.contourArea(contours.get(i));
                if(area >=4000 ){
                    counter++;
                }
            }
            if(counter >=1){
                ROI4BOXCOLOR = new Scalar (0,255,0); 
                System.out.println("Car Detected in ROI 4");
                Imgproc.putText(FeedMat, "Car Detected in ROI 4", new Point(0,80), 1, 1, new Scalar(255,255,255));

                
            }
                        if(counter ==0){
                ROI4BOXCOLOR = new Scalar (0,0,255); 
            }
          contours.clear();

            ROI4Panel.setimagewithMat(roi4Mat);
            processed.release();
            
            }
  
            
            if(!roi5Mat.empty()){
            Imgproc.resize(roi5Mat, roi5Mat, new Size(ROI5Frame.getWidth()-40,ROI5Frame.getHeight()-40));
            Mat processed = processor.process(roi5Mat);
            Mat hierarchy = new Mat();
            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
            Imgproc.findContours(processed, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
            int counter = 0;
            for(int i = 0; i<contours.size(); i++){
                double area = Imgproc.contourArea(contours.get(i));
                if(area >=4000 ){
                    counter++;
                }
            }
            if(counter >=1){
                ROI5BOXCOLOR = new Scalar (0,255,0); 
                System.out.println("Car Detected in ROI 5");
                Imgproc.putText(FeedMat, "Car Detected in ROI 5", new Point(0,80), 1, 1, new Scalar(255,255,255));
            }
                        if(counter ==0){
                ROI5BOXCOLOR = new Scalar (0,0,255); 
            }
          contours.clear();
       //                 Imgproc.drawContours(roi1Mat, contours, -1, new Scalar(255,170,0));

            ROI5Panel.setimagewithMat(roi5Mat);
            processed.release();
            
            }
            
            if(!roi6Mat.empty()){
            Imgproc.resize(roi6Mat, roi6Mat, new Size(ROI6Frame.getWidth()-40,ROI6Frame.getHeight()-40));
            Mat processed = processor.process(roi6Mat);
            Mat hierarchy = new Mat();
            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
            Imgproc.findContours(processed, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
            int counter = 0;
            for(int i = 0; i<contours.size(); i++){
                double area = Imgproc.contourArea(contours.get(i));
                if(area >=4000 ){
                    counter++;
                }
            }
            if(counter >=1){
                ROI6BOXCOLOR = new Scalar (0,255,0); 
                System.out.println("Car Detected in ROI 6");
                Imgproc.putText(FeedMat, "Car Detected in ROI 6", new Point(0,100), 1, 1, new Scalar(255,255,255));
                
            }
                        if(counter ==0){
                ROI6BOXCOLOR = new Scalar (0,0,255); 
                                CARCOUNT++;
            }
          contours.clear();
            ROI6Panel.setimagewithMat(roi6Mat);
            processed.release();
            
            }        
        }
        
            ControlsFrame.setSize(280,300);  
            ControlsFrame.setLocation(CameraFrame.getX()+CameraFrame.getWidth(), CameraFrame.getY());
            panel1.setimagewithMat(FeedMat);  


               CameraFrame.repaint();  
               ROI6Frame.repaint();
               FeedMat.release();
               roi1Mat.release();
               roi2Mat.release();
               roi3Mat.release();
               roi4Mat.release();
               roi5Mat.release();
               roi6Mat.release();
               
            }
            else  
            {  
                capture.release();
               
            } 
                
                }

        }  
    }



	public static void main(String arg[]) throws InterruptedException{  
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  
		try {
			Calculations();
		} catch (AWTException e) {
		} 
	}
}
 
