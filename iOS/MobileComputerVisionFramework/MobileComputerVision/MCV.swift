//
//  MCV.swift
//  MobileComputerVision
//
//  Created by Strategic Impact Solutions 1 on 26/04/18.
//  Copyright © 2018 App Builders. All rights reserved.
//

import UIKit
import GoogleMobileVision

open class MCV {
    
    // -- Statics
    public static let GMV:String = "GMV";
    
    // -- Logic valriables
    private var mController:UIViewController!;
    open var mProvider:String!;
    private var mDector:Detector!;
    
    internal init(controller:UIViewController, provider:String = "GMV") {
        self.mController = controller;
        self.mProvider = provider;
    }
    
    internal func getController() -> UIViewController {
        return self.mController;
    }
    
    internal func initDectector(type:String) {
        self.mDector = Detector(provider: self.mProvider, detectorType: type);
    }
    
    internal func getDetector() -> Detector {
        return self.mDector;
    }
}

internal class Detector {
    
    // -- Statics
    public static let text:String = "GMVDetectorTypeText";
    public static let image:String = "GMVDetectorTypeText";
    public static let code:String = "GMVDetectorTypeText";
    
    // Provider detectors
    private var mProvider:String!;
    private var mDetectorType:String!;
    private var mDetector:Any!;
    private var gmvDetector:GMVDetector!;
    
    public init(provider:String, detectorType:String = Detector.text) {
        
        self.mProvider = provider;
        self.mDetectorType = detectorType;
        self.mDetector = self.initFactory();
    }
    
    private func initFactory() -> Any {
        
        switch mProvider {
            
            case MCV.GMV:
                
                switch mDetectorType {
                    
                    case Detector.text:
                        
                        self.gmvDetector = GMVDetector(ofType: self.mProvider, options: nil);
                        return self.gmvDetector;
                
                    case .some(_):break
                    case .none:break;
                }
            
            case .some(_):break
            case .none:break;
        }
        return "";
    }
    
    public func getDetectorProvider() -> Any! {
        
        switch self.mProvider {
            
            case MCV.GMV:
                
                return self.gmvDetector;
            
            case .some(_):break
            case .none:break;
        }
        return "";
    }
}
