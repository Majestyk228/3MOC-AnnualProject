//
//  DashboardStat.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 19/07/2022.
//

import Foundation
import Alamofire
import SwiftyJSON

struct DashboardStat:Hashable,Codable{
    let nbUsers:Int?
    let totalPointsCommunity:Int?
    let nbPost:Int?
    let nbVote:Int?
}
struct Response: Codable {
  var data: [Data]
}
struct CommunityTitle:Hashable,Codable{
    let label:String
}

func refreshDashboardStat(idCommunity:Int) -> DashboardStat{
    //TODO
    var resp:DashboardStat
    resp = DashboardStat(nbUsers: 1, totalPointsCommunity: 1, nbPost: 1, nbVote: 1)
    
    
    
    let params: Parameters = [
            "idCommunity": idCommunity,
        ]
    let headers: HTTPHeaders = [
        //"token":UserDefaults.standard.string(forKey: "token")!
        "Content-Type":"application/json",
        "token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZFVzZXIiOjYsImlhdCI6MTY2MDc2ODQyNywiZXhwIjoxNjYxMzczMjI3fQ.vnQ-kuvxJnqyPRpibzt0gq5xjhM7K1vvc9dS3je6L9U"
    ]
     AF.request("https://www.titan-photography.com/community/stats", method: .post, parameters: params, encoding: JSONEncoding.default, headers: headers).validate(statusCode: 200 ..< 299).responseData { response in
            switch response.result {
                case .success(let json):
                
                    do {
                        let data = JSON(json)
                        resp=DashboardStat(nbUsers: data[0]["nbUsers"].int, totalPointsCommunity: data[0]["totalPointsCommunity"].int, nbPost: data[0]["nbPost"].int, nbVote: data[0]["nbVote"].int)
                        print(resp.nbUsers)
                        
                        
                        
                    } catch {
                        print("Error: Trying to convert JSON data to string")
                        return
                    }
                case .failure(let error):
                    print(error)
            }
        }
    
    return resp
}
func refreshCommunityTitle()->CommunityTitle{
    //todo
    return CommunityTitle(label: "OUI")
}

    

