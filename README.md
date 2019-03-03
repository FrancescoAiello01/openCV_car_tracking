# OpenCV Car Tracking

This project detects cars within a predefined region of the screen using background subtraction and a mixture of gaussian distribution filter.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Java SDK

### Installing

A step by step series of examples that tell you how to get a development env running

Clone the git repository
```
cd folder_of_choice
git clone https://github.com/FrancescoAiello01/openCV_car_tracking.git
```
Open project with IDE of choice


## Running Software

Steps to run the software with video files or a webcam

### Running with a video file

Navigate to line 655 in CarDetectionWGUI.java
```
VideoCapture capture =new VideoCapture("footage.mp4"); //Change this video file to your file of choice
```

### Running with webcam

Comment out line 655 and uncomment like 656
```
//VideoCapture capture =new VideoCapture("footage.mp4");
VideoCapture capture =new VideoCapture(0); //use webcam
```

## Deployment

Add additional notes about how to deploy this on a live system


## Built With

* [OpenCV](https://opencv.org/) - Image processing framework


## Contributing

Please feel free to initiate requests and report issues. However, please note, this project is not actively supported.


## Authors

* **Francesco Aiello** - *Initial work* - [FrancescoAiell01](https://github.com/FrancescoAiello01)


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
