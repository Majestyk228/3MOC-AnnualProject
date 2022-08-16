//
//  DashboardStat.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 19/07/2022.
//

import Foundation

struct DashboardStat:Hashable,Codable{
    let nbUsers:Int
    let totalPointsCommunity:String
    let nbPost:Int
    let nbVote:Int
}

class DashboardStats:ObservableObject{
    @Published var  dashboardStats: [DashboardStat] = []
    
    func fetch()  {//fonction qui va contacter l'API et récuperer les données brute
        print("Doing a api call...")
        let url = URL(string: "https://www.titan-photography.com/community/stats")!
        var request = URLRequest(url: url)
        // Serialize HTTP Body data as JSON
        request.httpMethod="POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        let body: [String:AnyHashable] = [
            "idCommunity":"2"
        ]
        request.httpBody = try? JSONSerialization.data(withJSONObject: body, options: .fragmentsAllowed)
        let task = URLSession.shared.dataTask(with: request){data, _, error in
            guard let data = data ,error == nil else {
                return
            }
            do{
                let response = try JSONDecoder().decode([DashboardStat].self, from: data)
                print(response)
                DispatchQueue.main.async {
                    self.dashboardStats = response
                }
                
            }
            catch{
                print("OH NO")
                print(error)
                
            }

        }
        task.resume()
        
        
        
        }
    
}
    

