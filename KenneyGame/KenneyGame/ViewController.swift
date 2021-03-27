//
//  ViewController.swift
//  KenneyGame
//
//  Created by Yulai Miao on 26/3/21.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var TimerLabel: UILabel!
    @IBOutlet weak var ScoreLabel: UILabel!
    @IBOutlet weak var HighScoreLabel: UILabel!
    
    //timer var
    var timer = Timer()
    var imgTimer = Timer()

    //var
    var counter = 0
    var score = 0
    var highScore = 0
    
    
    //image group
    @IBOutlet weak var img1: UIImageView!
    @IBOutlet weak var img2: UIImageView!
    @IBOutlet weak var img3: UIImageView!
    @IBOutlet weak var img4: UIImageView!
    @IBOutlet weak var img5: UIImageView!
    @IBOutlet weak var img6: UIImageView!
    @IBOutlet weak var img7: UIImageView!
    @IBOutlet weak var img8: UIImageView!
    @IBOutlet weak var img9: UIImageView!
    
    var imgArray = [UIImageView]()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        ScoreLabel.text = "Score: \(score)"
        
        //load high score
        let storeScore = UserDefaults.standard.object(forKey: "highscore")
        if storeScore == nil {
            highScore = 0
            HighScoreLabel.text = "High Score: \(highScore)"
            
        }
        
        if let newScore = storeScore as? Int {
            highScore = newScore
            HighScoreLabel.text = "High Score: \(highScore)"
        }
        
        //Timer
        counter = 10
        TimerLabel.text = "Timer: \(counter)"
        timer = Timer.scheduledTimer(timeInterval: 1, target: self, selector: #selector(timerRun), userInfo: nil, repeats: true)
        
        imgTimer = Timer.scheduledTimer(timeInterval: 1, target: self, selector: #selector(hiddenImg), userInfo: nil, repeats: true)
        
        //Touch
        img1.isUserInteractionEnabled = true
        img2.isUserInteractionEnabled = true
        img3.isUserInteractionEnabled = true
        img4.isUserInteractionEnabled = true
        img5.isUserInteractionEnabled = true
        img6.isUserInteractionEnabled = true
        img7.isUserInteractionEnabled = true
        img8.isUserInteractionEnabled = true
        img9.isUserInteractionEnabled = true
        
        let gestRecongnizer1 = UITapGestureRecognizer(target: self, action: #selector(addScore))
        let gestRecongnizer2 = UITapGestureRecognizer(target: self, action: #selector(addScore))
        let gestRecongnizer3 = UITapGestureRecognizer(target: self, action: #selector(addScore))
        let gestRecongnizer4 = UITapGestureRecognizer(target: self, action: #selector(addScore))
        let gestRecongnizer5 = UITapGestureRecognizer(target: self, action: #selector(addScore))
        let gestRecongnizer6 = UITapGestureRecognizer(target: self, action: #selector(addScore))
        let gestRecongnizer7 = UITapGestureRecognizer(target: self, action: #selector(addScore))
        let gestRecongnizer8 = UITapGestureRecognizer(target: self, action: #selector(addScore))
        let gestRecongnizer9 = UITapGestureRecognizer(target: self, action: #selector(addScore))
        
        img1.addGestureRecognizer(gestRecongnizer1)
        img2.addGestureRecognizer(gestRecongnizer2)
        img3.addGestureRecognizer(gestRecongnizer3)
        img4.addGestureRecognizer(gestRecongnizer4)
        img5.addGestureRecognizer(gestRecongnizer5)
        img6.addGestureRecognizer(gestRecongnizer6)
        img7.addGestureRecognizer(gestRecongnizer7)
        img8.addGestureRecognizer(gestRecongnizer8)
        img9.addGestureRecognizer(gestRecongnizer9)
        
        //initialize img group
        imgArray = [img1, img2, img3, img4, img5, img6, img7, img8, img9]
        
        hiddenImg()
    }

    
    @objc func hiddenImg(){
        
        for hImage in imgArray {
            hImage.isHidden = true
        }
        
        let randomNum = Int(arc4random_uniform(UInt32(imgArray.count - 1)))
        imgArray[randomNum].isHidden = false
    }
    
    @objc func addScore(){
        
        score += 1
        ScoreLabel.text = "Score: \(score)"
        
    }
    
    @objc func timerRun(){
        
        if counter != 0 {
            counter -= 1
            TimerLabel.text = "Timer: \(counter)"
        } 
        
        if self.highScore < self.score {
            
            self.highScore = self.score
            HighScoreLabel.text = "High Score: \(self.highScore)"
            UserDefaults.standard.setValue(self.highScore, forKey: "highscore")
        }
        
        if counter == 0{
        //Alert Dialog
        let alarm = UIAlertController(title: "Oops!", message: "Time is up!", preferredStyle: UIAlertController.Style.alert)
        let okButton = UIAlertAction(title: "OK", style: UIAlertAction.Style.cancel)
        
        let replayButton = UIAlertAction(title: "Replay", style:UIAlertAction.Style.destructive){ (UIAlertAction) in
            //reset the game
            self.score = 0
            self.ScoreLabel.text = "Score: \(self.score)"
            self.counter = 10
            self.TimerLabel.text = "Timer: \(self.counter)"
            
            self.timer = Timer.scheduledTimer(timeInterval: 1, target: self, selector: #selector(self.timerRun), userInfo: nil, repeats: true)
            
            self.imgTimer = Timer.scheduledTimer(timeInterval: 1, target: self, selector: #selector(self.hiddenImg), userInfo: nil, repeats: true)
        }
            alarm.addAction(okButton)
            alarm.addAction(replayButton)
            
            self.present(alarm, animated: true, completion: nil)
            
            timer.invalidate()
            imgTimer.invalidate()
        }
    }
}

