<p align="center">
<img src="https://github.com/nalancer08/ABAIS/blob/master/logo.png">
</p>

# Mobile-Computer-Vision (MCV)

App Builders Mobile Computer Vision abstraction, for Android &amp; iOs

This project's actually a beta, it has a lot of issues, please read all the documentation before implementing into your projects.

Actually the library has 100% base in Google Mobile Vision API, over this, we construct a strong repertory of methods and interfaces to make easy the work.

The scope of this library it's create easiest OCR Readers, CodaBar &amp; QR Scanners and face recognitions with faster implementation.


## iOS Setup

### Only use the latest version

### Compile and use the framework

To compile the framework open the **MobileComputerVision.xcworkspace** file.
To begging to compile, first select **MobileComputerVision** project target and build ```command + b```, then select Generic iOS device and build ```commad + b```. 
After those steps, select **GMV** target with Generic iOS device and build ```commad + b```.

Then into your project folder gonna appear **MobileComputerVision.framework**, this file have to be added into your project app.

## Andoid Setup

### Install your dependencie

We don't gonna use jCenter global, so please add to the current link to mavenCentral()






























### Our architecture:

<p align="center">
  <img src="https://github.com/nalancer08/Mobile-Computer-Vision---MCV/blob/master/Images/Architecture/Arquitectura_General.png">
</p>

#### OCR module:

<p align="center">
  <img src="https://github.com/nalancer08/Mobile-Computer-Vision---MCV/blob/master/Images/Architecture/OCR_General.png">
</p>

#### FaceRecognition

#### CodeScanner

## Android Setup

It's highly recommended in your manifest activate "accelerate hardware", 'cause if it's possible to use de GPU to help the CPU, the process gonna be faster.

