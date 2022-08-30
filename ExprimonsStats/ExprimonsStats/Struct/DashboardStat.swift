//
//  DashboardStat.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 19/07/2022.
//

import Foundation
import Alamofire
import SwiftyJSON

class DashboardStat:ObservableObject{
    @Published var nbUsers:Int?
    @Published var totalPointsCommunity:Int?
    @Published var nbPost:Int?
    @Published var nbVote:Int?
    init(nbUsers:Int,totalPointsCommunity:Int,nbPost:Int,nbVote:Int){
        self.nbUsers=nbUsers
        self.totalPointsCommunity=totalPointsCommunity
        self.nbPost=nbPost
        self.nbVote=nbVote
    }
}
struct Response: Codable {
  var data: [Data]
}
struct CommunityTitle:Hashable,Codable{
    let label:String
}

    

    
