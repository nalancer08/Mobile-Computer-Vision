//
//  ViewController.swift
//  MCVSample
//
//  Created by Strategic Impact Solutions 1 on 27/04/18.
//  Copyright © 2018 App Builders. All rights reserved.
//

import UIKit
import MobileComputerVision

class ViewController: UIViewController {

    override func viewDidLoad() {
        
        super.viewDidLoad();
        
        let image = UIImage(named: "ocr");
        if ((image) != nil) {
            print("Image exists");
        } else {
            print("Image doesnt exists");
        }
        
        /*let detector = TextDetector.init(controller: self);
        let text = detector.startWithImage(image: image).getText();
        print("Readed text :: ", text);*/
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}

