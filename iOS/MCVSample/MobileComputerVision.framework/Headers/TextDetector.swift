//
//  TextDecoder.swift
//  MobileComputerVision
//
//  Created by Strategic Impact Solutions 1 on 26/04/18.
//  Copyright © 2018 App Builders. All rights reserved.
//

import UIKit
import GoogleMobileVision

open class TextDetector: MCV {
    
    private var mDetector:Detector!;
    private var mText:String = "";
    
    override init(controller: UIViewController, provider: String) {
        
        super.init(controller: controller, provider: provider);
        self.initDectector(type: Detector.text);
    }
    
    open func startWithImage(image:UIImage) {
        
        self.mDetector = self.getDetector();
        
        switch self.mProvider {
            
            case MCV.GMV:
                
                let d = self.mDetector.getDetectorProvider() as! GMVDetector?;
                let features = d?.features(in: image, options: nil);
                self.parseGMVFeatures(features: features as! [GMVTextBlockFeature])
            
            case .some(_):break
            case .none:break;
        }
    }
    
    private func parseGMVFeatures(features:[GMVTextBlockFeature]) {
        
        for feature in features {
            self.mText += feature.value;
        }
    }

    open func getText() -> String {
        return self.mText;
    }
}

// Procces:
//  textDetector.init() -> startWithImage() -> getText();
