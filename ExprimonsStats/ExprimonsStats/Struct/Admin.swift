//
//  Admin.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 23/05/2022.
//

import Foundation


struct Admin:Hashable,Codable{
    let idAdmin:Int
    let firstName:String
    let lastName:String
    let email:String
    let password:String
}
struct Admindata:Hashable,Codable{
    let idAdmin:Int
    let email:String
    let password:String
    let idCommunity:Int
}

class AdminData:ObservableObject{
    @Published var  admins: [Admin] = []
    
    func fetch() { //fonction qui va contacter l'API et récuperer les données brute
            guard let url = URL(string: "https://www.titan-photography.com/admin/all") else { return
            }
            
            let task = URLSession.shared.dataTask(with: url) { [weak self] data, _, error in
                guard let data = data, error == nil else {
                    return
                }
                
                
                
                //conversion en JSON
                do{
                    let admins = try JSONDecoder().decode([Admin].self, from: data)
                    DispatchQueue.main.async {
                        self?.admins = admins
                    }
                }
                catch {
                    //erreur lors du parsing
                    print(error)
                }
            }
            
            task.resume()
        }
    
}

class AdminLogin:ObservableObject{
    @Published var  adminsa: [Admindata] = []
    
    func fetch() { //fonction qui va contacter l'API et récuperer les données brute
        // prepare json data
        
        let json: [String: Any] = ["email":"root@root.fr","password":"root"]
        let jsonData = try! JSONSerialization.data(withJSONObject: json)
        
        
        
        guard let url = URL(string: "https://www.titan-photography.com/admin/login") else { return
        }
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.httpBody = jsonData
        
        let task = URLSession.shared.dataTask(with: request) { [weak self] data, _, error in
            guard let data = data, error == nil else {
                return
            }
            
            
            
            
            //conversion en JSON
            do{
                let admins = try JSONDecoder().decode([Admindata].self, from: data)
                DispatchQueue.main.async {
                    self?.adminsa = admins
                    
                    
                }
            }
            catch {
                //erreur lors du parsing
                print(error)
            }
        }
        
        task.resume()
        }
    
}



