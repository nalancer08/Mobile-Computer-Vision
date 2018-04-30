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
    
    public override init(controller: UIViewController, provider: String = MCV.GMV) {
        
        super.init(controller: controller, provider: provider);
        self.initDectector(type: Detector.text);
    }
    
    @discardableResult open func startWithImage(image:UIImage?) -> TextDetector {
        
        self.mDetector = self.getDetector();
        
        switch self.mProvider {
            
            case MCV.GMV:
                
                let d:GMVDetector! = self.mDetector.getDetectorProvider() as! GMVDetector?;
                let features = d?.features(in: image, options: nil)!;
                self.parseGMVFeatures(features: features as! [GMVTextBlockFeature]?);
            
            case .some(_):break
            case .none:break;
        }
        
        return self;
    }

    open func getText() -> String {
        return self.mText;
    }
    
    private func parseGMVFeatures(features:[GMVTextBlockFeature]!) {
        //
        if (features != nil) {
            for feature in features {
                self.mText += feature.value;
            }
        }
    }
}
