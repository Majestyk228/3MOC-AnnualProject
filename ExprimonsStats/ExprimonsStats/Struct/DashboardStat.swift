//
//  DashboardStat.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 19/07/2022.
//

import Foundation
import Alamofire

struct DashboardStat:Hashable,Codable{
    let nbUsers:Int
    let totalPointsCommunity:Int
    let nbPost:Int
    let nbVote:Int
}
struct CommunityTitle:Hashable,Codable{
    let label:String
}

func refreshDashboardStat(idCommunity:Int) async -> DashboardStat{
    //TODO
    var resp:DashboardStat
    resp = DashboardStat(nbUsers: 1, totalPointsCommunity: 1, nbPost: 1, nbVote: 1)
    
    
    
    let params: Parameters = [
            "idCommunity": idCommunity,
        ]
    await AF.request("https://www.titan-photography.com/community/stats", method: .post, parameters: params, encoding: JSONEncoding.default, headers: nil).validate(statusCode: 200 ..< 299).responseData { response in
            switch response.result {
                case .success(let data):
                    do {
                        guard let jsonObject = try JSONSerialization.jsonObject(with: data) as? [String: Any] else {
                            print("Error: Cannot convert data to JSON object")
                            return
                        }
                        
                        guard let prettyJsonData = try? JSONSerialization.data(withJSONObject: jsonObject, options: .prettyPrinted) else {
                            print("Error: Cannot convert JSON object to Pretty JSON data")
                            return
                        }
                        
                        guard let prettyPrintedJson = String(data: prettyJsonData, encoding: .utf8) else {
                            print("Error: Could print JSON in String")
                            return
                        }
                        print(prettyPrintedJson)
                        
                        resp=DashboardStat(nbUsers: jsonObject["nbUsers"] as! Int, totalPointsCommunity: jsonObject["totalPointsCommunity"] as! Int, nbPost: jsonObject["nbPost"] as! Int, nbVote: jsonObject["nbVote"] as! Int)
                        
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

    

