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
    @Published var nbUsers:Int=0
    @Published var totalPointsCommunity:String="0"
    @Published var nbPost:Int=0
    @Published var nbVote:Int=0
    init(nbUsers:Int,totalPointsCommunity:String,nbPost:Int,nbVote:Int){
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

    
func refreshCommunityTitle()->CommunityTitle{
    //todo
    return CommunityTitle(label: "OUI")
}

    

